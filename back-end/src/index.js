//const SwaggerUI = require('swagger-ui');
const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const converter = require('json-2-csv');
const path = require('path');
const cors = require('cors');
const bodyParser = require('body-parser');
const app = express();
const port = 8080;

let db = new sqlite3.Database(path.join(__dirname, '/../db/spotify.db'), (err) => {
  if (err) {
    console.error(err.message);
    return;
  }

  console.log('Connected to the local spotify database.');
});

app.use(cors());
app.use(bodyParser.json());
app.use(express.static(path.join(__dirname, '/../swagger')));

// GET request for track with specified ID
app.get('/tracks/:trackId', (req, res) => {
  db.get(`SELECT * FROM tracks WHERE id='${req.params.trackId}'`, async (err, row) => {
    if (err) {
      console.error(err.message);
      res.end();
      return;
    }
    if (!row) {
      res.sendStatus(404);
      res.end();
      return;
    }
    await typeRespond(req, res, row);
    res.end();
  });
});

// GET request for tracks with specified ID or specified track name
app.get('/tracks', (req, res) => {
  let trackName = req.query.trackName;
  let artistId = req.query.artistId;
  if (isDefined(trackName) == isDefined(artistId)) {
    res.sendStatus(400);
    res.end();
    return;
  }
  let sql = `SELECT * FROM tracks WHERE ${trackName ? `name LIKE \'%${trackName}%\'` : `id_artists LIKE \'%${artistId}%\'`} ORDER BY popularity DESC`;
  db.all(sql, async (err, rows) => {
    if (err) {
      console.error(err.message);
      res.end();
      return;
    }
    if (rows.length == 0) {
      res.sendStatus(204);
      res.end();
      return;
    }
    await typeRespond(req, res, rows);
    res.end();
  });
});

// Update track information based on ID and return the updated track body
app.put('/tracks/:trackId', (req, res) => {
  let trackId = req.params.trackId;
  let updates = Object.entries(req.body);
  let sql = `UPDATE tracks SET ${updates.map(([key, value]) => `${key} = ?`).join(', ')} WHERE id = ?`;
  let values = [...updates.map(([key, value]) => value), trackId];

  db.run(sql, values, (err) => {
    if (err) {
      console.error(err.message);
      res.sendStatus(500);
      res.end();
      return;
    }
    res.status(200);
  }).get(`SELECT * FROM tracks WHERE id='${trackId}'`, async (err, row) => {
    if (err) {
      console.error(err.message);
      res.end();
      return;
    }
    await typeRespond(req, res, row);
    res.end();
  });
});

// DELETE track querying with its ID
app.delete('/tracks/:trackId', (req, res) => {
  let trackId = req.params.trackId;
  let sql = `DELETE FROM tracks WHERE id = '${trackId}'`;

  db.run(sql, (err) => {
    if (err) {
      console.error(err.message);
      res.sendStatus(500);
      res.end();
      return;
    }
    res.sendStatus(200);
    res.end();
  });
});

//POST request to create a new track and insert it in the database
app.post('/tracks', (req, res) => {
  const track = req.body;
  const keys = Object.keys(track);
  const values = Object.values(track);

  let sql = `INSERT INTO tracks(${keys.join(', ')}) VALUES (${values.map(() => '?').join(', ')})`;
  db.run(sql, values, function(err) {
    if (err) {
      console.error(err.message);
      res.status(500).send('Error adding track');
      return;
    }
    // update the track with the auto-incremented ID
    let newId = this.lastID;
    let updateSql = `UPDATE tracks SET id = ${newId} WHERE rowid = ${newId}`;
    db.run(updateSql, (err) => {
        if (err) {
          console.error(err.message);
          res.status(500).send('Error updating track');
          return;
        }
        res.status(201).json({message: `Track added with ID: ${newId}`});
    });
  });
});
 
// DELETE request using artist's ID to delete all tracks from a specific artist
app.delete("/tracks", (req, res) => {
  let id = req.params.artistId;
  let sql = `DELETE FROM tracks WHERE id_artists LIKE \'%${id}%\'`;
  db.run(sql, id, (err) => {
    if (err) {
      console.error(err.message);
      res.status(500).send("Error deleting tracks");
      return;
    }
    res.status(200).send(`Tracks by artist ${id} were deleted successfully`);
  });
});

//GET top N artists in batches of size M
app.get("/artists/mostPopular/:year", async (req, res) => {
  let year = req.params.year;
  let batchSize = req.query.M;
  let songCount = req.query.N;
  let pageNumber = req.query.page;

  let sql = `SELECT * FROM artists ORDER BY popularity DESC LIMIT ?`;

  db.all(sql, [songCount], async (err, rows) => {
    if (err) {
      console.error(err.message);
      res.end();
      return;
    }
    if (rows.length == 0) {
      res.sendStatus(204);
      res.end();
      return;
    }
    await typeRespond(req, res, rows.slice(batchSize*pageNumber, batchSize*(parseInt(pageNumber)+1)));
    res.end();
  });
})

//GET top N tracks in batches of size M
app.get("/tracks/mostPopular/:year", async (req, res) => {
  let year = req.params.year;
  let batchSize = req.query.M;
  let songCount = req.query.N;
  let pageNumber = req.query.page;

  let sql = `SELECT * FROM tracks WHERE strftime('%Y', release_date) = ? ORDER BY popularity DESC LIMIT ?`;

  db.all(sql, [year, songCount], async (err, rows) => {
    if (err) {
      console.error(err.message);
      res.end();
      return;
    }
    if (rows.length == 0) {
      res.sendStatus(204);
      res.end();
      return;
    }
    await typeRespond(req, res, rows.slice(batchSize*pageNumber, batchSize*(parseInt(pageNumber)+1)));
    res.end();
  });
})

//GET artist summary including song_count, earliest and latest release and highest popularity
app.get("/artist/summary", async (req, res) => {
  let artistId = req.query.artistId;
  let song_count, earliest_release, latest_release, highest_popularity;
  
  try {
    song_count = await new Promise((resolve, reject) => {
      db.get(`SELECT COUNT(*) as count FROM tracks WHERE id_artists LIKE \'%${artistId}%\'`,(err, row) => {
        if (err) reject(err);
        if (!row) reject({message: 'artist not found'});
        resolve(row.count);
      });
    });
    earliest_release = await new Promise((resolve, reject) => {
      db.get(`SELECT t.* FROM tracks t
             WHERE id_artists LIKE \'%${artistId}%\'
             ORDER BY t.release_date ASC LIMIT 1`, (err, row) => {
               if (err) reject(err);
               if (!row) reject({message: 'artist not found'});
               resolve(row);
             });
    });
    latest_release = await new Promise((resolve, reject) => {
      db.get(`SELECT t.* FROM tracks t
             WHERE t.id_artists LIKE \'%${artistId}%\'
             ORDER BY t.release_date DESC LIMIT 1`, (err, row) => {
               if (err) reject(err);
               if (!row) reject({message: 'artist not found'});
               resolve(row);
             });
    });
    highest_popularity = await new Promise((resolve, reject) => {
      db.get(`SELECT MAX(popularity) as highest FROM tracks WHERE id_artists LIKE \'%${artistId}%\'`, (err, row) => {
        if (err) reject(err);
        if (!row) reject({message: 'artist not found'});
        resolve(row.highest);
      });
    });
    // Return the result to the client
    res.json({
      song_count,
      earliest_release,
      latest_release,
      highest_popularity
    });
  } catch (err) {
    if(err.message == 'artist not found') {
        res.status(404).json({ error: "artist not found" });
    } else {
        console.error(err);
        res.status(500).json({ error: "Internal Server Error" });
    }
  }
});

function isDefined(val) {
  return (typeof val != "undefined");
}

async function typeRespond(req, res, body) {
  switch (req.query.format ?? 'json') {
    case 'json':
      res.send(body);
      break;
    case 'csv':
      res.type('text/csv');
      await converter.json2csvAsync(body).then((csv)=> res.send(csv));
      break;
    default:
      res.status(400).send({"error-message": "Content-type not compatible (must be \'json\' or \'csv\')"});
      break;
  }
}

app.listen(port, () => {
  console.log(`Spotify dataset api listening on port ${process.env.BACKEND_PORT || '8080'}`);
});
//const SwaggerUI = require('swagger-ui');
const express = require('express');
const path = require('path');
const app = express();
const port = 3000

app.use(express.static(path.join(__dirname, '/../public')));

app.listen(port, () => {
  console.log(`Front-end listening on port ${process.env.FRONTEND_PORT || '3000'}`);
});
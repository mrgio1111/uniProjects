package nl.rug.oop.rpg;

import java.io.*;
import java.nio.file.Path;

/**
 * Class SaveGame. Provides the mechanics for the progress of the game to be saved.
 */
public class SaveGame {
    /**
     * Method used to save the game.
     * @param directory Folder of the saved game.
     * @param filename Name of the saved game.
     * @param object Is the saved object that is saved.
     */
    public static void save(String directory, String filename, Serializable object){
        File saveDirectory = new File("savedgames");
        saveDirectory.mkdir();

        Path location = Path.of(directory, filename);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(location.toString());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            System.err.println("Error with saving " + e.getMessage());
            e.printStackTrace();
            return;
        }
        System.out.println("Save successful!");
    }
}

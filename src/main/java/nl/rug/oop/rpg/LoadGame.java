package nl.rug.oop.rpg;

import java.io.*;
import java.nio.file.Path;

/**
 * Class LoadGame. Provides the mechanics for the progress of the game to be loaded.
 */
public class LoadGame {
    /**
     * Method used to load the game.
     * @param directory Folder of the saved game.
     * @param filename Name of the saved game.
     * @return Returns the loaded object.
     */
    public static Object load(String directory, String filename) {
        Path location = Path.of(directory, filename);
        try {
            FileInputStream fileInputStream = new FileInputStream(location.toString());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("Load successful!");
            return object;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error with loading " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}

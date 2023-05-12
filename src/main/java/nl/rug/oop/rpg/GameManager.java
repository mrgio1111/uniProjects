package nl.rug.oop.rpg;

import java.io.IOException;

public class GameManager {
    private Game currentGame;

    public GameManager(Game initialGame) {
        this.currentGame = initialGame;
    }

    public Game getCurrentGame() {
        return this.currentGame;
    }

    public void loadGame(String filename) {
        try {
            Game loadedGame = Game.load("savedgames", filename);
            if (loadedGame != null) {
                this.currentGame = loadedGame;
                System.out.println("Load Successful!");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

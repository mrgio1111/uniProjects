package nl.rug.oop.rpg;

/**
 * Main class of the program. It's where all the initialization happens.
 */
public class Main {
    /**
     * main method of the Main class.
     * @param args Receives an array of strings if need be.
     */
    public static void main(String[] args) {
        Room shire = new Room("The Shire looks green as ever. A celebration is taking place nearby.");
        Room hogwarts = new Room("Hagrid greets you. A game of Quidditch can be heard nearby.");
        Room momsHouse = new Room("The smell of fresh food fills your lungs. Your mom never looked better.");
        Player player = new Player("mrgio11", shire, 100, 10);
        BotTestDoor yourMom = new BotTestDoor("A wooden door. Looks like an entrance to your mom's house.", momsHouse);
        BadJokesDoor hogwartsGate = new BadJokesDoor("A metal gate. It leads to Hogwarts.", hogwarts);
        shire.addDoor(yourMom);
        shire.addDoor(hogwartsGate);
        Giant hagrid = new Giant("Hagrid is sitting on a bench.", hogwarts, 1000, 50);
        SlytherinStudent malfoy = new SlytherinStudent("Draco Malfoy in the shadows.", hogwarts, 60, 6);
        Mom mom = new Mom("Your mom is humming a melody while cleaning the dishes", momsHouse, 10000, 500);
        Dog gina = new Dog("Your lovely 10 year old dog getting some sleep on the couch snoring.", momsHouse, 15, 1);
        Wizard gandalf = new Wizard("Gandalf passes by heading to Shire for Bilbo's birthday.", shire, 50000, 1500);
        Hobbit worrywort = new Hobbit("Master Worrywort looks at you suspiciously.", shire, 150, 3);
        shire.addNPC(gandalf);
        shire.addNPC(worrywort);
        hogwarts.addNPC(hagrid);
        hogwarts.addNPC(malfoy);
        momsHouse.addNPC(mom);
        momsHouse.addNPC(gina);
        Enemy boar = new Enemy("A wild boar", shire, 14, 2);
        Enemy orc = new Enemy("A powerful hunter orc. Stay away!", shire, 300, 25);
        shire.addEnemies(boar);
        shire.addEnemies(orc);
        Game game = new Game(player);
        GameManager gameManager = new GameManager(game);
        game.addRoom(shire);
        game.addRoom(momsHouse);
        game.addRoom(hogwarts);
        game.populateMenuArray();
        gameManager.getCurrentGame().runGameMenu();
    }
}

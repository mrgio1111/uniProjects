package nl.rug.oop.rpg;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Class for Game. Contains the main Menu options stored in an array.
 */
public class Game implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    private List<Room> rooms;
    private Player player;
    private NPC npc;
    private Door door;
    private String[] interactMenuArray = new String[9];
    private List<String> files;
    private transient Scanner scanner = new Scanner(System.in);
    private Game savedGame;

    /**
     * Constructor for game.
     * @param player is the player of the game.
     */
    public Game(Player player) {
        rooms = new ArrayList<>();
        files = new ArrayList<>();
        this.player = player;
    }

    /**
     * Populates the main menu array since I can't do it in one line because of checkstyle.
     */
    public void populateMenuArray(){
        interactMenuArray[0] = "look around";
        interactMenuArray[1] = "look for a way out";
        interactMenuArray[2] = "Look for company";
        interactMenuArray[3] = "Look for enemies";
        interactMenuArray[4] = "Quicksave";
        interactMenuArray[5] = "Quickload";
        interactMenuArray[6] = "Save";
        interactMenuArray[7] = "Load";
        interactMenuArray[8] = "Close game";
    }

    /**
     * Returns the main menu array
     * with its index at the front.
     * @return Returns the main menu array.
     */
    public String returnMenuArray(){
        for (int i = 0; i < interactMenuArray.length; i++) {
            System.out.println("(" + i + ") " + interactMenuArray[i]);
        }
        return null;
    }

    /**
     * Iterates the array list that stores the names of the files.
     */
    public void filesList(){
        for (int i = 0; i < files.size(); i++) {
            System.out.println("(" + i + ") " + files.get(i));
        }
    }

    /**
     * The list of rooms in the game.
     */
    public void roomsList(){
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println("(" + i + ") " + rooms.get(i).inspect());
        }
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * The following method runs the Main Menu options of the game.
     */
    public void runGameMenu(){
        while (true) {
            System.out.println("What do you want to do?");
            returnMenuArray();
            int choice = scanner.nextInt();
            if (choice == 0) {
                player.getPlayerRoom().inspect();
            }
            if (choice == 1) {
                this.chooseDoors();
            }
            if (choice == 2) {
                this.chooseCompany();
            }
            if (choice == 3) {
                this.inspectEnemies();
            }
            if (choice == 4) {
                SaveGame saver = new SaveGame();
                saver.save("savedgames","quicksave.ser", this);
                if (files.contains("quicksave.ser")) {
                    continue;
                } else {
                    files.add("quicksave.ser");
                }
            }
            if (choice == 5) {
                loadGame("quicksave.ser");
            }
            if (choice == 6) {
                System.out.println("Enter a filename: ");
                SaveGame saver = new SaveGame();
                String filename = scanner.nextLine();
                saver.save("savedgames", scanner.nextLine() + ".ser", this);
                files.add(filename);
            }
            if (choice == 7) {
                loadTwo();
            }
            if (choice == 8) {
                exit(0);
            }
            if (choice > 8) {
                System.out.println("Please only choose the options offered.");
                continue;
            }
        }
    }

    /**
     * This methods prints the file list array list and lets the user choose the file of his choice to load.
     */
    public void loadTwo(){
        if (files.isEmpty()) {
            System.out.println("No saved files available!");
            runGameMenu();
        }
        System.out.println("Which file do you want to load?");
        filesList();
        chooseFile();
    }

    /**
     * This method is a continuation of the loadTwo method. Lets the user choose the appropriate file.
     */
    public void chooseFile(){
        int choice = scanner.nextInt();
        if (choice == 0 && files.size() >= 0) {
            loadGame(files.get(0));
        }
        if (choice == 1 && files.size() <= 1) {
            loadGame(files.get(1));
        }
        if (choice == 2 && files.size() <= 2) {
            loadGame(files.get(2));
        }
    }

    /**
     * This method calls the 'load' method.
     * @param filename is the name of the file.
     */
    public void loadGame(String filename){
        try {
            savedGame = load("savedgames", filename);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Load Successful!");
    }

    /**
     * This method laods the previously saved game of the user.
     * @param directory The folder of the saved game.
     * @param filename  The name of the saved game file.
     * @return  Returns the saved game.
     * @throws IOException it is used to throw a failure in case of an error in Input & Output operations.
     * @throws ClassNotFoundException an error is thrown when the class is not found in the classpath.
     */
    public static Game load(String directory, String filename) throws IOException, ClassNotFoundException {
        Path location = Path.of(directory, filename);
        try {
            FileInputStream fileInputStream = new FileInputStream(location.toString());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object savedObject = objectInputStream.readObject();

            if (savedObject instanceof Game) {
                return (Game) savedObject;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * This method prompts the user to choose what door to enter.
     */
    public void chooseDoors() {
        System.out.println("You look around for doors");
        System.out.println("You see: ");
        player.getPlayerRoom().doorsList();
        if (player.getPlayerRoom().getDoors().isEmpty()) {
            System.out.println("The room has no doors to enter.");
            runGameMenu();
        }
        System.out.println("Which door do you want to take? (-1 : Stay here)");
        while (true) {
            int choice = scanner.nextInt();
            if (choice == 0) {
                player.getPlayerRoom().getDoor(0).interact(player, rooms.get(1));
                System.out.println("You go through the door");
                break;
            }
            if (choice == 1) {
                player.getPlayerRoom().getDoor(1).interact(player, rooms.get(2));
                System.out.println("You go through the door");
                break;
            }
            if (choice == -1) {
                break;
            }
        }
    }

    /**
     * This method prompts the user to chooce NPC to interact with.
     */
    public void chooseCompany(){
        System.out.println("You look if there's someone here.");
        System.out.println("You see:");
        player.getPlayerRoom().npcList();
        if (player.getPlayerRoom().getNPCs().isEmpty()) {
            System.out.println("There's no one around to chat to.");
            runGameMenu();
        }
        System.out.println("With whom do you want to interact with? (-1 : do nothing)");
        while (true) {
            int choice = scanner.nextInt();
            if (choice == 0) {
                player.getPlayerRoom().getNPC(0).interact(player, player.getPlayerRoom());
                break;
            }
            if (choice == 1) {
                player.getPlayerRoom().getNPC(1).interact(player, player.getPlayerRoom());
                break;
            }
            if (choice == -1) {
                break;
            }
        }
    }

    /**
     * This method prompts the user to attack from a list of nearby enemies.
     */
    public void inspectEnemies(){
        System.out.println("You look for enemies ..");
        System.out.println("You see:");
        player.getPlayerRoom().enemyList();
        if (player.getPlayerRoom().getEnemies().isEmpty()) {
            System.out.println("No enemies around.");
            runGameMenu();
        }
        System.out.println("Which enemy do you want to inspect? (-1: No one)");
        int choice = scanner.nextInt();
        if (choice == 0) {
            System.out.println("Health: " + player.getPlayerRoom().getEnemy(0).getHealth());
            System.out.println("Damage: " + player.getPlayerRoom().getEnemy(0).getDamage());
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Your damage: " + player.getDamage());
            this.attackEnemy0();
        }
        if (choice == 1) {
            System.out.println("Health: " + player.getPlayerRoom().getEnemy(1).getHealth());
            System.out.println("Damage: " + player.getPlayerRoom().getEnemy(1).getDamage());
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Your damage: " + player.getDamage());
            this.attackEnemy1();
        }
        if (choice == -1) {
            runGameMenu();
        }
    }

    /**
     * This method is used for the player to attack the enemy stored at array index 0.
     */
    public void attackEnemy0(){
        System.out.println("Do you want to attack?");
        System.out.println("(0) Yes");
        System.out.println("(1) No");
        int choice = scanner.nextInt();
        if (choice == 1) {
            this.inspectEnemies();
        }
        while (true) {
            player.attack(player, player.getPlayerRoom().getEnemy(0));
            player.getPlayerRoom().getEnemy(0).attack(player, player.getPlayerRoom().getEnemy(0));
            if (player.getHealth() <= 0) {
                System.out.println("Game over, you died!");
                exit(0);
            }
            if (player.getPlayerRoom().getEnemy(0).getHealth() <= 0) {
                System.out.println("You killed the enemy!");
                System.out.println("Current health: " + player.getHealth());
                player.getPlayerRoom().removeEnemies(player.getPlayerRoom().getEnemy(0));
                break;
            }
        }
    }

    /**
     * This method is used for the player to attack the enemy stored at array index 1.
     */
    public void attackEnemy1(){
        System.out.println("Do you want to attack? (-1 : No one)");
        System.out.println("(0) Yes");
        System.out.println("(1) No");
        int choice = scanner.nextInt();
        if (choice == 1) {
            this.inspectEnemies();
        }
        while (true) {
            player.attack(player, player.getPlayerRoom().getEnemy(1));
            player.getPlayerRoom().getEnemy(1).attack(player, player.getPlayerRoom().getEnemy(1));
            if (player.getHealth() <= 0) {
                System.out.println("Game over, you died!");
                exit(0);
            }
            if (player.getPlayerRoom().getEnemy(1).getHealth() <= 0) {
                System.out.println("You killed the enemy!");
                System.out.println("Current health: " + player.getHealth());
                player.getPlayerRoom().removeEnemies(player.getPlayerRoom().getEnemy(1));
                break;
            }
        }
    }
}

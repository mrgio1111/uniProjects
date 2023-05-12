package nl.rug.oop.rpg;

import java.util.Scanner;

/**
 * Class for BotTestDoor. Extends abstract class Door.
 */
public class BotTestDoor extends Door{
    private transient Scanner scanner = new Scanner(System.in);

    public BotTestDoor(String description, Room room) {
        super(description, room);
    }

    public void interact(Player player, Room room){
        player.setPlayerRoom(room);
        botTest();
    }

    /**
     * Very relevant quiz question from door to user.
     */
    public void botTest(){
        System.out.println("This door checks if you're a bot or not by making you answer a very serious question");
        System.out.println("Question: \"Keep my wife's name out your f@#$ing mouth!\"");
        System.out.println("\"Who is the wise man that said this beautiful phrase?\"");
        System.out.println("(0) Mel Gibson");
        System.out.println("(1) Jamie Foxx");
        System.out.println("(2) Giorgos Sakkatos");
        System.out.println("(3) Maria Mura");
        System.out.println("(4) Will Smith");
        int choice = scanner.nextInt();
        if (choice == 4) {
            System.out.println("*slow ironic clap*");
            System.out.println("\"You can pass ..\"");
        } else {
            this.botTest();
        }

    }
}

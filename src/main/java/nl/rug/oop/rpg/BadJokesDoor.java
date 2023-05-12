package nl.rug.oop.rpg;

import java.util.Scanner;

/**
 * Class for BadJokesDoor. Extends abstract class Door.
 */
public class BadJokesDoor extends Door{
    private transient Scanner scanner = new Scanner(System.in);

    /**
     * Constructor for BadJokesDoor.
     * @param description Description of that door.
     * @param room Rppm that this door is palced in.
     */
    public BadJokesDoor(String description, Room room) {
        super(description, room);
    }

    public void interact(Player player, Room room){
        player.setPlayerRoom(room);
        doorIntro();
    }

    /**
     * The door introduces itself.
     */
    public void doorIntro(){
        System.out.println("\"Hello, I'm not your usual door. I'm a door that wants to be a standup comedian!\"");
        System.out.println("\"That means that you'll have to hear a few of my jokes to pass.\"");
        System.out.println("\"Do you accept the challenge?\"");
        System.out.println("(0) Yes");
        System.out.println("(1) No");
        int choice = scanner.nextInt();
        if (choice == 0) {
            badJokes();
        }
    }

    /**
     * The door says bad jokes.
     */
    public void badJokes(){
        System.out.println("Ok let's staaaart!");
        System.out.println("\"Why did the young girl fall of the swing?\"");
        System.out.println("(0) Because she slipped");
        System.out.println("(1) Because she was pushed off.");
        System.out.println("(2) Because the swing doesn't exist and she's delusional.");
        int choice = scanner.nextInt();
        System.out.println("\"No. Because she had no arms.\"");
        System.out.println("\"Ok that was a warm-up\"");
        System.out.println("\"Ok, let's dive deeper...\"");
        System.out.println("\"What does my dad have in common with Nemo?\"");
        System.out.println("(0) They're both fish???");
        System.out.println("(1) They both have stripes?");
        System.out.println("(2) I don't know..");
        choice = scanner.nextInt();
        System.out.println("\"Well .... They both can't be found.\"");
        System.out.println("\"Ok, I hope you're hangin in there! Last one!\"");
        System.out.println("\"What's the difference between a baby and a sweet potato?\"");
        System.out.println("(0) One's a human, one's a vegetable?");
        System.out.println("(1) Absolutely no idea");
        System.out.println("(2) Well, everything.");
        choice = scanner.nextInt();
        System.out.println("\"Well ... approximately 140 calories\"");
        System.out.println("\"Woohoo, each joke for a different audience today! Please pass!\"");
    }
}

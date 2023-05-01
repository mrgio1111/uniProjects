# RPG

In this assignment, you'll make your own text-based RPG. For the people who don't know what a text-based RPG is: it is an RPG that is played by interacting with a virtual world through text (`System.in` and `System.out`). This assignment will help you get familiar with inheritance and polymorphism.

In this RPG you'll be acting as the player. The game will be a sort of dungeon crawler where you make your way through all kinds of rooms, and you'll run into some of the most fantastic creatures (un)known to mankind. If you manage to reach the final room, you win eternal fame and glory (if you know how to program that).

Note that we will not explain everything in as much detail as in the first assignment. You will have to decide most of the implementation details for yourself.  Quite some things that you need for this assignment are explained in the reader, so please read through the entire chapter `OOP Concepts` before you start this assignment. It will make it significantly easier to understand and make.

You will be continuously extending your program with new functionality, so make sure that you pay good attention to your design. The better you design your program, the easier adding new functionality will be for you. This is probably one of your first (big) projects using OOP, so don't be afraid to ask help from your TA! We are there to help.

___

## 0. Before we begin

### Deadlines

This project consists of 2 parts. During this assignment, you will have two deadlines and demos:

- An intermediate deadline/demo. Here you do not need to submit a pull request, but - at the very least - **part 1 should be completed** before the `Assignment 2 Intermediate Deadline` mentioned in the schedule on Nestor. This means that you will need to make sure that the code for that part is on GitHub before that point. The (mandatory) intermediate demo for part 1 will take place a few days after this deadline. Should you not have completed the necessary requirements, then this will negatively influence your grade.

- The final deadline/demo. Both part 1 and part 2 (the whole assignment basically) should be completed before the `Assignment 2 Deadline` mentioned on Nestor.

Note that you are highly advised to complete more than just part 1 for the intermediate demo. Any additional functionality that you implement, you will be able to get feedback on. Additionally, it ensures that you do not have a lot of last-minute things to do before the final deadline.

### Setting up

Just as in the previous assignment, you will find the necessary files in the newly created `rpg` branch. This means that you should make sure that you are on the `rpg` branch before you start.

This `README` is available in your group's repository, the `2022_Assignments` repository, and on Nestor. Unfortunately, we cannot update the `README` in your group's repository in case we are fixing some mistake(s) in the assignment description. As such, if you find a mistake, check Nestor/`2022_Assignments` to see if it has already been fixed. Should any major changes happen (we hope not), then this will be communicated via an announcement on Nestor.

### Incremental Maintenance
This assignment has been divided into multiple section so that we can focus on a small subproblem at the time. This way it is easier to manage the changes. The programs we will be writing in this course will be a lot larger than the programs written so far in other programming courses you have followed, such as Imperative Programming or Algorithms and Data Structures. Therefore, it is important to maintain a pleasant working environment. We can do this by doing the following after each step:

- Adding comments (mainly Javadoc) to the code;
- Integrating the changes into the main program so they are visible and usable;
- Running `mvn clean compile` and making sure it builds successfully;
- Refactoring the code (remove duplicate code, ensure proper variable names etc.);
- Committing the changes;
- Pushing your commits;
- Giving yourself a pat on the back to stay motivated.

___

# Part 1

## 1.1 Rooms

Let's start with creating the most important part of our RPG: the player. We can do this by creating a class `Player`. Since we want our `Player` to have a magnificent name, the `Player` should have a field `name`.

Before we can start playing, we need something to walk around in. As mentioned before, our RPG world consists of rooms, so let's implement this by creating a class `Room`. A `Room` should have a field `description` which will be used to `inspect` a `Room`. Whenever a `Room` is inspected, it should print this descriptive piece of text to `System.out` using the `System.out.println` method. Later on, we will add more things to this `inspect` method.

Note that the point of having classes is (among others) reusability. Therefore, don't hardcode the room descriptions in `Room`, since this does not allow us to easily create rooms with different descriptions.
Instead, have the description string as a parameter in the constructor of `Room`. This means that wherever you initialise the `Room`, you can specify a description. This holds for almost all the classes we will be creating. In general, it is good practice to hardcode as little as possible, so try to conform to that.

Now that we have rooms and a player, we can connect the two. Since we play from the perspective of a `Player`, we do not let a `Room` keep track of the `Player`, but rather we let the `Player` keep track of the `Room` they are in.

**Requirements:**

- Create a `Player` class with a `name`.
- Create a `Room` class with a `description`.
- You should be able to `inspect` a `Room`. Doing so will print its description.
- The `Player` should keep track of what `Room` they are in.

## 1.2 A Simple Interaction Menu

We now have all the ingredients to create a basic program. Let's use the two classes mentioned above to create a simple program that can do some basic interaction. For now, you are allowed to do this in the `Main` class, but keep in mind that the `Main` class should ideally only be used for initialising. Later on, you will have to change this.

The program should print a simple options menu. The user can then enter a number that matches one of the options. Just as in assignment 0, we can read the input from the user with `Scanner`. More specifically, we want to read an integer from the input line, so once we have created a `Scanner` object, we can call the `nextInt()` method, which waits for the user to input an integer and returns it. Note that if the user did not input an integer, an error might occur.

For now, let's add an option to "look around". Whenever the player looks around, the `Room` the `Player` is in should be inspected. This will look something like this:

```
What do you want to do?
  (0) Look around
0
You see: A rather dusty room full of computers.
What do you want to do?
 (0) Look around
```

As you can see, this interaction menu should keep repeating itself. For now, you can use an infinite while loop for this. Later on, we can add conditions so that the loop stops if the `Player` wins (or dies).

**Requirements:**

- A simple interaction menu with the option to look around.
- When the the option "look around" is chosen, the `Room` the `Player` is in should be inspected.

## 1.3 Doors
Are you able to look around in the `Room`? Well done! So far, so good. It's starting to get boring in this particular `Room` after a while though, so we decide we want to escape it and go to another `Room`. This means we will need to have doors, so let's create a class for this.

**Requirements:**

- Create a `Door` class that is very similar to a `Room`: it has a description, and you should be able to `inspect` it.

### 1.4 Inspectable

By now, the `behaviour` of the `Door` and `Room` classes is very similar. They both share one property: they are inspectable. Let's generalise that property!

The OOP-way to indicate that a class has certain `behaviour` is via an interface. Interfaces are used to describe that a class **must** have a few methods, which can then be used by the outside world. The implementation of those methods, however, is not provided in the interface.
This means that the method(s) that are described in the interface can potentially not do what you expect them to do. Therefore be very concise and accurate with how you name your interface and the methods in your interface.

***
**Intermezzo: Why an interface and not an abstract class?**

You might have noticed that `Room` and `Door` also share the `description` field. In this case, it might also make sense to use an abstract class instead of an interface. This is a perfect example of a design decision. Why do we go with an interface here and not with an abstract class? Using an abstract class would reduce code duplication, right?

Correct, using an abstract class would indeed reduce code duplication. However, conceptually, `Room` and `Door` only share behaviour. Just because these particular two classes share a description, does not automatically mean that this description is always used when an object is `Inspectable`. For example, you might decide to add a chest to the game that starts attacking the player as soon as the player inspects it. In this case, you might end up with a redundant property, as `description` is not used when inspecting these chests. The point we are trying to make here is that the combination of `Inspectable` and the field `description` is not set in stone. We want to make our code as reusable as possible, so if future developers want to add such a chest, we do not want to force them to always add a description. As with everything: ask yourself the question of whether it makes sense for this thing to (always) be there.

Additionally, abstract classes limit the extendability of the class. Once you extend a given class, you cannot extend any other classes, whereas a class can implement multiple interfaces. To summarise: just because these two classes happen to share similarities with the implementation of `inspect`, does not mean that all future classes will/should.

Note that there is generally no uniquely best answer to this whole question. You simply have a few options and it is up to us as developers to choose the option that makes the most sense. We recommend you to read through the chapter in the reader about interfaces; this might clear some things up. Nevertheless, interfaces (and their usefulness) can be very difficult to understand early on. As you get more experience, you will start to notice just how useful they are ٩ʕ◕౪◕ʔو
***


The way that you specify methods in interfaces is extremely similar to the way that you write forward declarations for functions in C header files. In short, a basic interface is simply a list of unimplemented methods. In this case, there is only one method in there: `inspect()`. This means that our interface will look something like this:

```java
/**
 * An interface that classes can implement so that they can be inspected
 */
interface Inspectable {

    void inspect();

}
```

Our newly created interface `Inspectable` is now ready to be used! The way that we apply interfaces to classes is by using the `implements` keyword. Using our new interface in `Room` would be as easy as changing

```java
public class Room {
```

to

```java
public class Room implements Inspectable {
```

Let's apply the same changes to `Door`. These changes enforce the programmer to give `Door` and `Room` an `inspect()` method. Note that since we don't have those methods yet at this point, you will not be able to compile your program anymore. We will fix this in the next section.

**Requirements:**

- Create an interface `Inspectable` with an `inspect()` method.
- `Room` should implement `Inspectable`.
- `Door` should implement `Inspectable`.

### 1.5 Adding Doors

It is obviously nothing short of amazing that we have added a `Door` class, but we are not doing anything with it now. That is a bit sad, so let's fix that.

A `Room` can have multiple doors. A first intuition might be to add an array of `Door`s to `Room`. However, arrays are cumbersome to use in this case, since we need to store their size, keep track of indices, and all that stuff.
Instead, we are going to use a List. Lists are very nice, because they allow us to add and remove items very easily. In Java, we use a special type of list: `ArrayList` (There are other List types, but `ArrayList` is almost always a good choice).

We can add a list of `Door`s as follows: `private List<Door> doors`. The part between the angle brackets indicates what items should be in our `List`. In this case, we want a list containing `Door`s, so we added `Door`.

However, just as with any other object, we first need to initialise our list before we can use it. We can do this by adding the following to our constructor:

```java
doors = new ArrayList<>();
```

At this point, it might seem a bit confusing that we have a `List` field that we initialise to an `ArrayList`. This is an example of polymorphism; something that you will learn more about later on.

Let's also create a method `addDoor(Door door)` so that we can add doors to this list. Adding an item to an `ArrayList` can be done by calling `add(...)` on our List. This will add the door provided in the arguments to the end of the List.

Now that our rooms also have doors, let's extend the functionality of the inspect methods for rooms to also print the number of doors. Suppose our room has two doors, something like this would be shown:

```
What do you want to do?
  (0) Look around
0
You see: A rather dusty room full of computers. The room has 2 doors.
What do you want to do?
 (0) Look around
```

**Requirements:**

- Add a field `doors` to `Room`. The field should be a `List`.
- Initialise the List of doors in the constructor of `Room`.
- Add a method `addDoor(Door door)` to `Room` that adds a `Door` to the list of doors.
- Alter the `inspect()` method of `Room` in such a way that it also prints the number of doors.

### 1.6 More Gameplay Options

We are also going to add another gameplay option that allows us to see all the doors in a room. The option will be "look for a way out" and, if selected, displays all the doors in the room. The user can then select a door to interact with.

Suppose that we are in a room that has two doors, this would look something like this:

```
What do you want to do?
  (0) Look around
  (1) Look for a way out
1
You look around for doors. You see:
  (0) A mysterious red door
  (1) A black door
What do you want to do?
  (0) Look around
  (1) Look for a way out
```

As you can see, the interaction is now two levels deep. Whenever the user selects "look for a way out", it will show some new interactive possibilities. We will be adding more to this interaction menu, so be sure to use separate methods (or classes!) to handle all these different possibilities. This will make it a lot easier to read and (potentially) debug your code.

If you have done the main game loop in `Main` so far, now would be a good time to move this to a more dedicated class. You could, for example, create a class `Game` that executes the main game loop. Remember: `Main` should only be used for initialising.


**Requirements:**

- Add an option to the interaction menu that allows the `Player` to "look for a way out".
- When the option to "look for a way out" is selected, all the `Door`s of the room should be inspected.

### 1.7 Entering Doors

Obviously, we want to do more than just inspecting doors. So now we are going to add something that allows the `Player` to go through a `Door`. Whenever a `Player` `interacts` with a `Door` they should go through it. This means that the `Door` will need a method `interact()`.

In order to do this, we first need to connect a `Door` to another `Room`. We can do this by adding a field to `Door` for the `Room` behind it. In this case, our doors are one-way. So once we go through it, we cannot go back (you are free to change this later on).

In the `interact()` method of `Door`, we need to somehow tell our `Player` to change rooms. However, this is a bit difficult, since the `interact()` method belongs to `Door` and not to `Player`. Therefore, you should add a parameter `Player` to the `interact()` method, so that we can tell the player to switch rooms. There is no need to make the `Player` a field everywhere or to make it a global variable, which would be even worse.

Once you added the ability for a `Player` to go through a `Door` the interaction menu should look something like this:

```
What do you want to do?
  (0) Look around
  (1) Look for a way out
0
You see: A rather dusty room full of computers and two doors.
What do you want to do?
  (0) Look around
  (1) Look for a way out
1
You look around for doors.
You see:
  (0) A mysterious red door
  (1) A black door
Which door do you take? (-1 : stay here)
1
You go through the door
What do you want to do?
  (0) Look around
  (1) Look for a way out
0
You see: A dark room with dark doors
```

**Requirements:**

- A `Door` should be connected to a `Room` behind it.
- Add an `interact()` method to `Door`. When this method is called, the player should move to the `Room` behind that `Door`.
- The `Player` should be an argument of the `interact()` method.
- The interaction menu should be augmented with an option to select a `Door` to go through after looking around.

### 1.8 Interactable

We have now made a `Door` interactable. However, we will also be adding more things that we can interact with (one of them being NPCs). If we wanted to expand on this program even more, we could also have chests or items to interact with. In other words, a lot of these classes would share `behaviour`. And just as before, whenever we see a lot of these (unrelated) classes sharing `behaviour`, we use an interface.
So let's move this method into a new interface `Interactable`.

Now `Door` can implement this interface. The nice thing is, as mentioned before, that we can also use this for NPCs.

**Requirements:**

- Create an interface `Interactable` that contains the method `void interact(Player player)`.
- `Door` should implement this `Interactable` interface.

Before we continue, let's make sure that everything works so far. Test your program to see if there are any bugs in there. As you know, if you already have a lot of bugs early on, and you continue coding your program, they will start to affect each other, and everything will be one big mess. So take it slow and you'll have an easier time.


## 1.9 NPCs

Now it is finally time to add some more exciting interactions to our game!

We are going to add some NPCs (Non-Player Characters). To do this, we create an `NPC` class. For now, the `NPC` should only have a field `description`. The `Player` should be able to `inspect` and `interact` with an `NPC`, so make sure `NPC` implements both `Inspectable` and `Interactable`.

An `NPC` belongs to a `Room`, so let's also create a field to keep track of all the `NPC`s in a `Room`. Note that there can be multiple `NPC`s in a room, so we can use an `List` for this.

We will add more specific NPCs later, but for now, let's use an example implementation with a simple print statement in the `interact()` method to verify that `NPC` is working. The interaction menu should be augmented with an option "look for company" which should list all the `NPC`s. This should look something like this:

```
What do you want to do?
  (0) Look around
  (1) Look for a way out
  (2) Look for company
2
You look if there’s someone here.
You see:
  (0) A suspiciously happy looking orc
  (2) The kerstman
  (3) A dancing strawberry
Interact ? (-1 : do nothing)
1
The creature is asleep so you can’t interact with it.
What do you want to do?
  (0) Look around
  (1) Look for a way out
  (2) Look for company
```
If this works, awesome! We can now start to implement some more interesting `NPC`s.

**Requirements:**

- Create an `NPC` class with a field `description`.
- An `NPC` should be `Inspectable`.
- An `NPC` should be `Interactable`.
- A `Room` should (be able to) contain a number of `NPC`s.
- Create a new option in the interaction menu: "Look for company".
- When the option "look for company" is chosen, it should inspect all the NPCs in the room.

## 1.10 A Simple Combat System

What fun is an RPG without a little combat? So let's add this!
In order to have combat, we first need something to fight. So let's create an `Enemy` class. Since `Enemy` is an `NPC`, it should extend from `NPC`.

From now on, you will have to make the design decisions yourself. We will only give some requirements that your program should have and you are free to decide how to implement this. Try to make use of inheritance, abstract classes, and interfaces to create a proper design here!

Don't forget that you can add methods to `Player` which can be called by, for example, the `NPC`'s `interact()` method. This allows you to add a lot more besides the combat system. Think about things such as a money system or status effect. However, make sure that you have enough time to finish the other parts of this assignment. If you finished all that, you can go ham!

**Requirements:**

- `Player` should have damage and health.
- All `NPC`s should have damage and health.
- Only `Player` and `Enemy` should be attackable.
- The `Player` should be able to attack an `Enemy` and by doing this deal damage to that `Enemy`.
- An `Enemy` should be able to attack the `Player` and by doing this deal damage to the `Player`.
- The `Player` or an `NPC` should die when its health drops to or below 0.
- The game stops when the `Player` dies. When the game stops, it should print something along the lines of "Game Over!".
- A simple interaction menu for the combat. For example, when the `Player` interacts with an `Enemy` it should give the option to attack that enemy. The `Enemy` then attacks back etc.

> Now would also be a good time to run `mvn clean compile` to make sure your code quality is up to par.
> Also make sure that the code up to this point is on GitHub before the intermediate deadline.
___

# Part 2

In this part, you will be extending part 1, by adding multiple NPCs, different doors, and support for saving and loading. Before you start, make sure that you have finished all the requirements for Part 1.
If your program already does everything it needs to do, try to improve the quality of the design and code as much as possible. It will be far easier to build upon a program with good design and well-written code, than realising later on that you need to rewrite a large chunk of it. If your program does not yet do everything it needs to do (i.e. does not fulfill the requirements of Part 1), add this functionality first.

## 2.1 Multiple NPCs

Since we want our game to be a bit more interesting, we do not want boring generic NPCs in there. Therefore, make the class `NPC` abstract.
Now you can get as creative as you want!
Try to come up with another class that extends from `NPC`. Make sure it does something interesting! Some ideas could be:

- A healer that heals the `Player`.
- A trader that gives the `Player` an item (potentially in exchange for money).
- A wizard that buffs the `Player` and permanently increases their damage.

Note that, since `NPC` is now abstract, we no longer have to give specific implementation of `interact()` and `inspect()` in `NPC`. The details of these methods are specific to the classes that extend from `NPC` and should therefore be implemented in those specific subclasses.

**Requirements:**

- Make `NPC` an abstract class.
- Create at least 1 other class (besides `Enemy`) that extends from `NPC`.
- The class(es) should have distinct behaviour (different descriptions do not count; be creative!).

## 2.2 Multiple Doors

Before we continue to the fancy IO part of this assignment, there is one more thing we want you to add. Just as with NPCs, we also want to make the doors a bit more interesting. So create at least two classes that extend from `Door` and make sure that they do something interesting! Some examples could be:

- An evil door that the Player has to defeat before entering.
- A guardian door that requires all enemies to be dead before entering.
- A trap door that damages the player when going through.

**Requirements:**

- Create at least 2 classes that extend from `Door`.
- The classes should have distinct behaviour (different descriptions do not count; be creative!).

***

## 2.3  About Saving

In many programs, we can save data. The program does this by writing certain information to a file. Abstractly speaking, it is converting program data into a structured form, so that it can be converted back into the program data later.
This conversion of program data to structured data (saving) is referred to as serialization. The reverse process (loading) is called de-serialization. There are some formats available that store information in an organized manner such as YAML, JSON or XML. However, many programming languages also support the saving or loading of data natively. One of those languages is Java.

In the next few parts, we will make use of this native support for serialization to make save files. This way, we can have (quick)saving and (quick)loading in our game (which is kinda awesome right?)!

## Serialization in Java

Before we get started with saving, think about which class(es) you need to save in order to properly save the user's progress.
In order to save our classes, we will need to use the `Serializable` interface. Later on, this allows us to convert Java objects into a stream of bytes that can be saved in a file. This stream of bytes can then be reconstructed into Java objects later on.
More specifically: we are saving the state of our objects (since this is all we are interested in).

Any class that we want to save should implement this `Serializable` interface. If these classes already implement an interface, do not worry, since Java supports having multiple interfaces (unlike multiple inheritance).
The nice thing about the `Serializable` interface is that we do not have to implement any methods of this interface. It acts as a "marker" or "flag" for our classes that indicates to the JVM (Java Virtual Machine) that this class can be serialized.

When our class implements `Serializable` we can eventually use the `ObjectOutputStream` class to write the data of our object.
However, before we can do this, each class that implements `Serializable` should have a `serialVersionUID` field:

```java
/* 42L is just an example */
private static final long serialVersionUID = 42L;
```

This field is stored together with the other data of our object whenever we save. It is then used during loading to verify the version of the class we try to load is indeed the same as the version of the class we saved.
Not providing this field, will make the compiler generate a serialVersionUID for the class automatically. However, how this serialVersionUID is generated is highly sensitive to class details and can differ depending on the compiler implementation. As such, changing things in your program can change this UID and thus you will no longer be able to load old savefiles for it (despite that specific class not being modified). Therefore, it is good practice to explicitly define this UID for every `Serializable` class yourself. You can then modify this `serialVersionUID` yourself anytime you update the class in such a way that old savefiles are no longer compatible.

If we make a class implement `Serializable`, all of its fields should be `Serializable`. If we would try to serialize a class with fields that cannot be serialized (for example `Scanner`), it will throw a `NotSerializableException`:

```sh
java.io.NotSerializableException: java.util.Scanner
```

How can we prevent this error? Whenever there is a field that we do not want or need to serialize, we can define this field as `transient`:

```Java
/* This field will not be serialized */
private transient Scanner scanner;
```

Fields that refer to local resources such as IO streams and `Scanner` are not serialized, because the serialized objects can - hypothetically - also be used for network communication. If we then de-serialize these IO-related fields, they would not be valid on a different machine. The serialization process is fully recursive. This means that if the class you want to save contains a (non-transient) field that references another class, you need to ensure this class implements `Serializable` as well!

**Requirements:**

- Make every class you want to save implement `Serializable`.
- Give every class that implements `Serializable` a `serialVersionUID`.

### 2.4 New interact options

Before we start with the actual saving and loading, we are first going to add two options to the interaction menu: `QuickSave` and `QuickLoad`.
This will look something like this:

```
What do you want to do?
  (0) Look around
  (1) Look for a way out
  (2) Look for company
  (3) QuickSave
  (4) QuickLoad
```

For now, these actions do not need to do anything, but we are going to use them in a bit.

**Requirements:**

- Add an option `QuickSave` to the interaction menu.
- Add an option `QuickLoad` to the interaction menu.

### 2.5 Quicksaving and Quickloading

Now that we have set up our classes to be saved, it is time to move on to the actual saving part. You will have to decide for yourself where to put the code for this. When doing this, you should keep in mind that your classes should not be responsible for too many things at once (ideally only one thing; keep your classes short).

The very first thing that we have to do is set up a directory in which we will place our files. However, we do not want to do this manually, but rather let our program take care of this. That way, our program does not break if the user removes this directory.
We want to store our saves in a directory called `savedgames` and this directory should be located in the root of this assignment directory.
For this, we can use Java's `File` class. This class is an abstract representation of file and directory pathnames, so we can use this as a representation for both files and directories. Alternatively, you can also use the newer `Path` class to achieve the same thing.
We can create such an object using the following:

```Java
/* will create a File object that refers to the location "savedgames" in the root of your project directory */
File saveDirectory = new File("savedgames");
/* Alternatively: */
Path saveDirectory = Path.of("savedgames"); 
```

Now we need to ensure that this directory is created if it does not exist yet. We can use the following for this:

```Java
saveDirectory.mkdir();
/* Alternatively */
Files.createDirectory(saveDirectory);
```

This will create the directory if it does not exist and do nothing if it already exists. The `mkdir()` method returns a boolean value that indicates whether the creation of the directory was successful. If the directory already exists, it will return false. Since we do not really care about this, we can ignore this value for now.

Whenever the user chooses the option `QuickSave`, the program should save the state of the game in a file called `quicksave.ser` inside of this `savedgames` directory.
For this, you will need the classes [`FileOutputStream`](https://docs.oracle.com/javase/8/docs/api/java/io/FileOutputStream.html) and [`ObjectOutputStream`](https://docs.oracle.com/javase/8/docs/api/java/io/ObjectOutputStream.html).

Whenever the user chooses the option `QuickLoad`, the program should load the state of the game as it was saved in the file `quicksave.ser`.
For this you will need the classes [`FileInputStream`](https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html) and [`ObjectInputStream`](https://docs.oracle.com/javase/8/docs/api/java/io/ObjectInputStream.html).

The result of this serialization is a stream of bytes (VERY DIFFICULT TO READ FOR US HUMANS [-c°▥°]-c). Therefore we cannot easily verify whether saving was successful, so we will have to implement both saving and loading before we can verify that it works.

We need to make sure that we give appropriate feedback to the user if something went wrong (for example if the file does not exist). Usually a simple print statement should be enough. While printing the stack trace might be very useful for debugging, we do not want this in our final program, since we do not want our users to see this. Always give proper feedback to the user if something goes wrong. If you want to do this via the commandline, do not print to `System.out`, instead use `System.err`.

The details about how to save and load Objects are explained in the lecture about serialization.

**Requirements:**

- Save games should be stored in a directory called `savedgames` at the root of the assignment directory.
- The `savedgames` directory should be created if it does not exist yet.
- Choosing `QuickSave` should save the game in a file called `quicksave.ser`.
- Choosing `QuickLoad` should load the game as it was saved in the file `quicksave.ser`
- If something goes wrong during saving or loading, the program should print an informative message to the user.

**Functionality Warning**:
It could very well be that something broke because of the saving and loading process. Therefore, be sure to verify that all your program features still work after loading a game.

## 2.6 Named save and load files

Obviously, we also want to have the option to give our savegames custom names. Similarly, we also want to be able to load files with custom names.
So let's add two more options to our interaction menu: `Save` and `Load` (an example of the extended user interface can be found below).

Choosing the option `Save` should ask the user for a name for their new savegame. Note that the `.ser` extension should not be included in this filename.
Since the user can enter basically anything, we need to ensure that the provided filename satisfies some constraints (think of some yourself, just make sure the filename itself cannot produce an error). If there is an error, we need to give the user appropriate feedback about the file name (e.g. does the filename already exist? is it too long? forbidden characters?).
If there is no error with the filename, the program should save the game into the savefile with the name the user provided.

Choosing the option `Load` should display a list of savefiles in the `savedgames` directory. The user can then pick one of these options and the program should load that savefile.
It should only list the files with the `.ser` extension. The `quicksave.ser` file should also be included in this list.

> When implementing these two options, try to prevent code duplication with quicksave and quickload.

The interaction menu now could look something like this:

```
What do you want to do?
  (0) Look around
  (1) Look for a way out
  (2) Look for company
  (3) QuickSave
  (4) QuickLoad
  (5) Save
  (6) Load
5
Filename?
room1
Save successful
What do you want to do?
  (0) Look around
  (1) Look for a way out
  (2) Look for company
  (3) QuickSave
  (4) QuickLoad
  (5) Save
  (6) Load
3
Quicksave successful
What do you want to do?
  (-1) Give up
  (0) Look around
  (1) Look for a way out
  (2) Look for company
  (3) QuickSave
  (4) QuickLoad
  (5) Save
  (6) Load
6
Which file? (-1 : none)
  (0) room1.ser
  (1) quickSave.ser
0
Load successful
```

**Requirements:**

- Add an option `Save`.
- Add an option `Load`.
- Choosing `Save` should save the game in a file whose name is provided by the user.
- Choosing `Load` should display a list of savefiles to the user. It should then load the file selected by the user.

## 2.7 Error handling

At this point your program hopefully works as you intend it to work. However, what if the user does something stupid? For example, the user tries to input a menu option that does not exist or the user entered a filename that might produce errors. Make sure to have proper error handling for these kinds of scenarios. With proper error handling, we simply mean that you should inform the user that something went wrong and that (if possible) the program should keep running.

Remember that you should not have empty try/catch statements! An empty try/catch is extremely bad practice, because you are hiding the fact that an error occurred and continuing execution as normal. It is basically equivalent to putting a piece of tape on an engine warning light. If something goes wrong, you have no idea where it went wrong and why, so you are strongly recommended to inform the user if something goes wrong.

It is not just important that your program has all the functionality, but it should also be able to handle the exceptional scenarios. As such, it is very important to test your program for these kinds of scenarios. This also prevents the situation of you encountering an exception for the first time during the demo and having to say "I have not seen that before (◔ д◔)".

**Requirements:**
- Thoroughly test your program and add proper error handling.

___

# Final Requirements

As you know by now, design is a very important aspect of OOP. As a result, we want to impose two additional final requirements: there should be at least 2 different abstract classes and 1 additional interface (besides `Inspectable` and `Interactable`) in your program. These are not just some random arbitrary requirements. A well-designed program will already have these things if you follow all the steps up to this point.

Be sure to thoroughly test your code and verify that everything works properly. That way, you won't run into problems during the demo.

Finally, we have added a `questions.md` file once again. Make sure to answer the questions, as they count towards your grade.

**Requirements:**

- You should have at least 2 different abstract super classes.
- You should have at least 1 extra interface (besides `Inspectable` and `Interactable`).
- Fill in the peer evaluation form.
- Fill in the `questions.md`.

___

# Additional notes

## 1 Be Creative!

You are encouraged to add your own twist to the RPG! Create a real story or make the experience more enjoyable in whatever way you wish. Once you get the hang of it, it will be a lot of fun to think of new things and functionalities to add!
Think of fairies, trolls, weird items, characters from Lord of the Rings - the more interesting the story and the game, the more chance you have at getting bonus points! Just make sure that your program can at least do what is specified in the requirements. Also be sure to keep paying attention to the design of your program. While a very nice game is obviously amazing, we will be mainly looking at your code/design.

## 2 Free Tip

We obviously want you to get the highest grade possible, so here is a free tip to **bump up your grade**: go through the section `Common Mistakes` in the reader. You can increase the quality of your program quite a bit by going through this list and checking your program for every single one of these mistakes. It really can make quite the difference.

The majority of these should already be covered by the GitHub checks, but you might still find some useful information there.

___

## Handing in + Grading

When you are finished, create a pull request from the `rpg` branch into the `main` branch. Make sure that all the checks pass.

The point distribution for your grade will look as follows:

| Category      | Max points    |
| ------------- |:-------------:|
| Functionality | 5             |
| Design        | 3             |
| Code quality + documentation | 1   |
| Answers to the questions | 1   |
| Bonus         | 1             |

For design, we will be paying attention to things such as good use of inheritance, encapsulation, polymorphism etc.
Note that that functionality and design go together. We cannot judge your design properly if there is not sufficient functionality for us to judge. It is easy to submit a perfectly designed Hello World! program, but this will not get you any points.
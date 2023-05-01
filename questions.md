# Question 1

In step 1, you were asked to create a `Room` class with a description, which will be printed if inspected. Two software developers proposed two different implementations for the `Room` class.

The first developer proposed one implementation:
```java
public class Room {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
```
 
The second developer proposed another implementation:
```java
public class Room {
    public String description;
}
```
 
Both developers are discussing which implementation is better and why. Please answer the following question:

Which of these two implementations would you select? And why?

Justify your answer in at least 250 words. Please explain the consequences, benefits and drawbacks of each implementation and support it with an example.

___

Answer: We would select the first implementation. This is because the 1st implementation is more efficient and pragmatic. It contains a getter to get the description 
and a setter to set the description field.

As far as the 2nd implementation goes, there are a few risks with having variable fields set to public. First off, the encapsulation principle of the OOP is violated. So programming 
that way becomes more Imperative than Object Oriented. Additionally, having plenty public access modifiers makes the project bigger in size
and professionally speaking, making the program harder for clients to use. Moreover, having public fields makes it difficult to tamper with the code since
every variable is dependent on the other and since they're all public, consequently it's easier to mess up the project.
Some benefits of the use of the public modifier is potentially less code and easier access to fields. Might be an ideal choice in a very small project, though it's good
practice not to go for that method of accessing fields.

On the other hand however, the first implementation is a lot cleaner since the encapsulation principle is there, so it's much more Object Oriented than the 2nd implementation.
Additionally, there's no risk messing up something irrelevant in the code, since the 1st implementation has specific methods for specific actions. Also setting a field to private makes
only that class have access to that field and no other, meaning this way of programming is more secure and well-structured and potentially more probable to find this implementation in a
professional setting.

___

# Question 2

In step 2, you are asked to create two interfaces: `Inspectable` and `Interactable`.
Interfaces by definition do not have implementations. They consist of method signatures:

```java
interface Inspectable {
    void inspect();
}
```
A software developer claims that interfaces are not useful, because they do not contain implementations. Thus, we should just use classes, and we do not need to define empty interfaces.

What do you think about this opinion? Do you agree or disagree with this opinion?

Please justify your answer in at least 250 words, and support your justifications with an example.

___

Answer: We firmly disagree with this developer's viewpoint on interfaces. 
In our view, interfaces make the conceptual part of the code easier for us developers to understand. For example, it makes sense when creating a Zoo with many
animals to have different implementations of their "makeNoise" behaviour since all animals make noises in different ways. So conceptually,
it makes a lot of sense to use an interface and enforce a behaviour common to all animals, but with a unique implementation for each animal. It is still
possible to do this without an interface, but the design lacks that way. It's especially important to have good design in the job market since 
many developers take part on a single project, so having clean design, make all your lives easier and probably your co-workers will like you more and will be willing to
spend more time with you. So the benefits of using interfaces is much more broad than the benefits their implementation brings in a particular project :)
Now if that developer wanted to have the same implementation common in different classes, he should probably make an abstract class with that behaviour implemented 
and create child classes that will share that implementation of that particular behaviour. But still using an interface is even cleaner than that. Also interfaces help achieve multiple 
inheritance, since it's not possible to achieve that in the case of a single class. Finally, using an interface helps apply the principle of OOP because different closely related objects apply a behaviour
differently.

___

# Question 3

To save your game state, you were asked to use Java classes `FileOutputStream` and `ObjectOutputStream`.
These two classes are part of the Java libraries, and they are designed based on a specific design pattern.

Which design pattern do `FileOutputStream` and `ObjectOutputStream` implement?

Explain the roles of this design pattern and how `FileOutputStream` and `ObjectOutputStream` implement it. Also explain the benefit of implementing this design pattern.

___

Answer: The 'FileOutputStream' is used to write data in the form of bytes to the files. On the other hand, 'ObjectOutputStream' is used to write objects into a file. The benefit of this design pattern
is that it helps developers implement serialization quite effectively and cleanly. 'FileOutputStream' creates the path from the project to the desired save location and 'ObjectOutputStream' is the 'vehicle' that brings our 
file to the desired save location.

___
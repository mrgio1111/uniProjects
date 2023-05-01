# Report

Giorgos Sakkatos (s1234567)

## Introduction

> The program I created is a simulation that resolves army battles against different
> armies of different factions in a Lord of the rings theme. The user has the option to
> choose the amount of nodes displayed on the graph as well as the amount of
> edges that connect each node. Also, the user can add armies to these nodes and different
> events as well. These events occur randomly when an army is added to a node and also while
> the battle is taking place. The game ends when a winning team is left in 
> the graph or when a game-ending event takes place. The user has the ability to save the Simulation in
> a file. The game components are saved in Json format.
 
>Expected length: ~100 words

## Program design

>What stands out design-wise in my program is the implementation of the MVC pattern (Model, View, Listener) as well as the Observer pattern that further
> helps to implement the MVC pattern. In my MVC implementation, the model is responsible for the logic and the mechanics behind my program, 
> with the dominating file being Map.java which is where everything takes place and where everything comes together. A few files 
> in the model extend the ListenerSupport class so that they can notify the listeners about any changes happening.
> My view component is where the graph is drawn and what ultimately the user sees on his screen. It listens for updates in the model by the help of an interface
> "UpdateListener", and each time something changes the repaint() method is called and the graph is redrawn to implement the required updates.
> Finally my controller component is where all the buttons and the user interactions with my program take place. All the buttons on the screen are part of the
> controller. They all extend a built-in Java class that notifies the program that an action was performed.
> Additionally, the JOptionsPane component was particularly helpful in taking user input, as well as the JSplitPane to be able to successfully split
> the main Panel into two parts. Finally, the implementation of my Json functionality is worth a mention. I created an interface which is implemented 
> by the most important parts of the Model, and this interface implements a method that reads each class's components and converts them into Json format.

> Expected length: as much as you need to explain the above.

## Evaluation of the program

> *Discuss the stability of your implementation. What works well? Are there any bugs? Is everything tested properly? Are there still features that
> have not been implemented? Also, if you had the time, what improvements would you make to your implementation? Are there things which you would 
> have done completely differently?*

> Overall, I think that my program is stable. All functionalities are implemented correctly, they do what they're supposed to do and a relatively good design
> is present. At the same time, there definitely are things that I would like to improve given more time. Firstly, I would certainly improve the
> error handling aspect of my program. Unfortunately, not too much time was spent on that as my main focus was to implement as much functionality as possible. 
> Specifically, the part where the user adds Events or Armies to the game. I use an implementation of JOptionsPane that allows the user to enter a string and
> if the string matches the Army Faction name or the event, then that functionality is added. Of course the user can enter whatever text he likes (even though
> I've indicated what text has to be entered). That might result in a misuse of the program that should certainly have been accounted for by the developer responsible. 
> In hindsight, I would apply a different implementation of JOptionsPane where the user has to click buttons and not enter the text himself. Another aspect of my
> program that I would like to spend more time on is improving the functionality of selecting an Edge. What I did to implement that, is that I created an
> invisible rectangle that sits below where the edge connects one node with another node. This works well when the graph is not too populated but when the graph
> becomes very populated with nodes, these invisible rectangles overlap and selecting edges becomes quite hard. Another small functionality that was quite honestly
> forgotten is to add functionality to resolute battles of armies of opposite teams that appear in the same node. That could be easily implemented by looping 
> over the armies in the node and if they don't belong in the same team, they battle. 
> Finally, I would have liked to implement textures but in the absence of time I couldn't. This assignment is still a work in progress for me during the summer
> because I'm curious where my creativity will take me and probably also good practice for AOOP in the 2nd year. 
> PS: Insane LoTR fan. 

>Expected length: ~300-500 words

## Questions

Please answer the following questions:

1. In this assignment, the program should follow the Model View Controller (MVC) pattern. Please explain the design of the program in terms of the MVC pattern. Specifically try to answer the following questions:
   - MVC consists of three components: Model, view and controller. Can you please explain the role of each component? Please provide examples of these roles from the assignment. How are these three roles (i.e. Model, view and controller) are implemented in the assignment?
   - MVC enforces special constraints on the dependencies between its three components: Model, view and controller. Please explain these constraints, and why are they important?

___

Answer: The MVC pattern is used when a program has to implement some type of user interaction. The model could be seen as the "back-end" part of our program. That's 
where all the main logic of the program is applied, the fundamental structure of it. The view displays the model's data to the user. The view can acess the model's data
but it doesn't know what's the purpose of this data or how to manipulate it. It jsut displays it. The controller comes right in between the model and the view
bridging the gap between them. It listens to any event that is caused or triggered in the view and takes the necessary actions to execute it. The result of this execution of
this action is instantly visible in the view.
Im my implementation of the MVC pattern, I first make sure to separate the effect and reach of each component. The view shouldn't interact with the model with
any other way other than what's needed (to retrieve and display the data). The controller interacts with the model to bridge the gap and help the view reach the model's data.
The model exists on its own right, and it's the foundation of the program since without it, no data can be retrieved. 
Some files from my model extend a class that I created that contains a data structure for the listeners to exist in as well as a method to be called
when the model makes a substantial change and the view should be notified. The listeners in this data structure implemented at ListenerSupport file, are
the different files of the view component. DrawGraph and ObjectInfo listen for updates happening in the model so that they themselves, can display it to the user
through the view. When a change happens, then the repaint() method is called which is implemented through an interface and makes the necessary changes in the
view based on what was changed in the model.
There are some constraints on the MVC pattern. First off the only purpose of the model is to represent the data. It doesn't depend on the view or the controller
at all, meaning, it doesn't use any code from any classes that originate from the view or the controller. The view on the other hand depends on the model
and retrieves the data so it can be displayed to the user. The controller helps the view retrieve data from the model. 
If these constraints are broken, then all kinds of dependencies are created and this results in a very messy code which is very hard to debug and could
drive your fellow developers crazy. On the other hand if the MVC pattern is applied in a correct way, it leads to a very smooth user interaction and possibly
a fast development process where debugging is easy, as well as the addition of new features.

___

2. The Swing library provides the ability to create nested user interface components. In this assignment, you created multiple JPanel components on the user interface. These contain other user interface components to build-up a tree of user interface components.
Which design pattern does Swing implement to create a hierarchy of user interface components? Please explain this pattern and how it is implemented in Swing.

___

Answer: Swing implements the composite design pattern to produce a hierarchical tree that builds up all the necessary interface components present on the view.
The composite design pattern is especially useful when hierarchies of the user interface components need to be represented in a graph. When implemented,
it resembles a tree-like structures, but it works as a singular object.

___

3. The Observer pattern is useful to implement the MVC pattern. Can you please explain the relationship between the Observer pattern and the MVC pattern?
Please provide an example from the assignment on how the Observer pattern supports implementing the MVC pattern.

___

Answer: The main relationships in the MVC pattern are given by the Observer, Composite and the Strategy design patterns. The Observer pattern specifically, is 
implemented in the view which acts as the Observer and the Model which acts as the Observable object. That said, the Observer pattern plays an integral role
in the correct and successful implementation of the MVC pattern. They are always used in conjunction since they are so compatible together. 
As far as my assignment goes, the DrawGraph and the ObjectInfo are the listeners or Observers that listen to or observe for updates in the model and the model
is being Observed by the view.

___

## Process evaluation

> *Describe shortly the process that led to the final code and the report. What was easy, what was difficult? Did you make interesting mistakes? What have you learned from this assignment?*

> What was challenging was the Googling part since it took so much time to understand and successfully implement what I researched. At the same time,
> that was a very fun process because I was building something from scratch on my own and that's what made it so incredibly rewarding for me. Also, having that
> independence to do research online while building the project goes in par with what we'll be doing as professional developers. I'm not sure anything was easy
> for me about this, but that's the way it should be, because only through struggle we learn. I made a few interesting mistakes at first like violating 
> the MVC pattern because of my lack of a full understanding of it. Thankfully through research and the gradual build-up of the project I was able to comprehend
> how it's implemented and implemented it successfully. I certainly feel more skilled in Java than how skilled I was after the 1st assignment, and also this
> course was the most fun I've ever had programming. I certainly learned that OOP is way more enjoyable than Imperative programming for me.

> Expected length: ~150 words

## Conclusions

> *Add a very short summary/concluding remarks here*

>Overall I enjoyed this assignment very much and I'm looking forward for AOOP. 
> I would have liked to have a partner who was as interested as me in making a great project, but at the same time I feel that I became a better developer
> because of my partner not participating.
>I have to give credits to my TA Max for being so incredibly helpful with the dozens of issues I created on Github and always being fast
> with his responses. He helped immensely. And the tutorials provided by Niels where incredibly helpful as well. Cheers!
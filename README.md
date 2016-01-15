# BluePizzaDiagrams

The design of the tool

For the full design of the tool refer to the UML diagram located in the docs folder.

UML:

This project parses data after reading in the java files that constitute the project and makes use of a MetaDataLibrary that holds ClassVolume objects.  These ClassVolume projects contain books of methods and fields.  This organizational structure is used to hold the data parsed out of reading the java files for a particular project.  This information is then used to determine what arrows are drawn and between which classes these arrows are drawn.  The data is stored in this organized format to make it extensible in case you wish to add additional features.


Sequence Diagrams [Milestone 3]:

Big changes made to support sequence diagrams.  Design parser had to be changed of the existing tools mainly.  Allows for the creation of either the creation of sequence diagrams or UML diagrams now.  Since we needed some more information than we were already getting, we created a new visitor called the MethodInternalsVisitor for milestone 2 and then tacked on more code so it stored more information that could be used for the sequence diagram.  We also created a new class for storage called MethodCallParagraph that would allow us to store information regarding what methods called what to order the sequence of calls correctly on the sequence diagram.  All the storage classes had additional code added on that was necessary for the creation of sequence diagrams. 

Finally, we created a new Outputer called SDOutputer that acts as na outputer for sequence diagrams.  Since our design is easily extensible, it was quite simple to add a few classes and not have to edit much existing code to allow for the creation of sequence diagrams.  But a lot of code had to added here to support the drawing of sequence diagrams using SDEdit.

Stating who did what:

All contributions were pair programmed.  This can be seen in the commit messages to the project where these messages state that both users have made these commits (with 1 being the primary committer).  Apart from this, pushes have been made from schnipde.


Instructions on how to use your code

UML:
In the run configurations for the design parser write the names of the classes you want parsed as arguments.  Then use graphviz to open the file that is generated from the run.

Sequence Diagrams:
In the run configurations for the design parser put down the method name you want as arguments followed by the method you want and finally an argument for the arguments the method takes.  Follow this up with the depth you would like to go to on each call as shown in the example below.  Then use SDEdit to open the file that is generated from the run.

-s --class=java.util.Collections --method=shuffle -ajava.util.Collections --depth=2

IF YOU ARE USING LINUX [We are both using Linux]:

UML:
In the run configurations for the design parser write the names of the classes you want parsed as arguments. You can run test.sh in the dot folder and it will do everything for you.

Sequence Diagrams:
In the run configurations for the design parser put down the method name you want as arguments followed by the method you want and finally an argument for the arguments the method takes.  Follow this up with the depth you would like to go to on each call as shown in the example below.

-s --class=java.util.Collections --method=shuffle -ajava.util.Collections --depth=2

You can run test.sh in the sd folder and it will do everything for you.

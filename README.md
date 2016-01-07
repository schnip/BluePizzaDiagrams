# BluePizzaDiagrams

The design of the tool

For the full design of the tool refer to the UML diagram located in the docs folder.

This project parses data after reading in the java files that constitute the project and makes use of a MetaDataLibrary that holds ClassVolume objects.  These ClassVolume projects contain books of methods and fields.  This organizational structure is used to hold the data parsed out of reading the java files for a particular project.  This information is then used to determine what arrows are drawn and between which classes these arrows are drawn.  The data is stored in this organized format to make it extensible in case you wish to add additional features.

Stating who did what:

All contributions were pair programmed.  This can be seen in the commit messages to the project where these messages state that both users have made these commits (with 1 being the primary committer).  Apart from this, pushes have been made from schnipde.


Instructions on how to use your code

In the run configurations for the design parser write the names of the classes you want parsed as arguments.  Then use graphviz to open the file that is generated from the run.

IF YOU ARE USING LINUX:
You can run test.sh and it will do everything for you.

Treelib provides tree distance functions. We currently
provide the following functions:



Name: Tree edit distance (Tai's)
Algorithm: O(n^3) Demaine et al.
           O(n^4) Shasha and Zhang

Name: BDist      n-gram histogram distance
Algorithm: O(n)  Yang et al.

Name: mtd        Multi-set tree distance
Algorithm O(n)   Muller-Molina et al.


----------------------------------------------------------
For Developers
----------------------------------------------------------
Requirements:

1) Maven
2) Ant
3) Java
4) graphviz (optional for displaying class hierarquies)

To checkout the project do:
svn checkout svn+ssh://<developer_name>@svn.berlios.de/svnroot/repos/treelib/trunk treelib

To compile the project:
mvn compile

To test the project:
mvn test

To create the site of the project:
mvn site

To create an eclipse project:
mvn eclipse:eclipse

There is also a plugin for netbeans that opens maven projects.

package org.treelib;

/**
 * Interface that defines a general distance
 * function.
 * @author Arnoldo Jose Muller Molina
 *
 */
public interface Distance<O> {
	
	/**
	 * Calculate the distance between this object and another object
	 * 	 * @param objectB object to compare
	 * @return The distance of objectA and objectB
	 */
	double distance( O objectB);
	
	/**
	 * @return the number of nodes of the tree.
	 */
	int getNodeCount();

}

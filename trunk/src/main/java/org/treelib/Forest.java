package org.treelib;

import java.util.List;

/*
 Furia-chan: An Open Source software license violation detector.    
 Copyright (C) 2008 Kyushu Institute of Technology

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Interface for a forest.
 * @author Arnoldo Jose Muller Molina
 */

public interface Forest {

	/**
     * Deletes the right tree's root node.
     * @return The resulting Forest
     */
    public abstract Forest deleteRightTreeNode();

    /**
     * Deletes the left tree's root node.
     * @return The resulting Forest
     */
    public abstract Forest deleteLeftTreeNode();

    /**
     * 
     * @return true if the leftmost tree is the heavy path.
     */
    public abstract boolean isLeftHeavy();

    /**
     * Deletes the root node of the leftmost tree, and returns
     * the remaining forest (only leftmost tree)
     * @return leftmost tree without the root node.
     */
    public abstract Forest deleteRootOnLeftTreeAndGetLeftTree();

    /**
     * Delete the leftmost tree of this forest.
     * @return Left tree.
     */
    public abstract Forest deleteLeftTree();

    /**
     * @return True if this forest is a tree, otherwise false.
     */
    public abstract boolean isTree();

    /**
     * Deletes the rightmost tree of the forest
     * @return The deleted forest.
     */
    public abstract Forest deleteRightTree();

    /**
     * @return The rightmost tree of the forest.
     * 
     */
    public abstract TreeForStandardTed getRightTree();

    /**
     * @return The leftmost tree in the forest.
     */
    public abstract TreeForStandardTed getLeftTree();


    /**
     * @return the "topLight" of this forest. 
     * @see Demaine's paper.
     */
    public abstract List < Forest > topLight();

    /**
     * 
     * @return Number of nodes in the forest.
     */
    public abstract int getSize();

    /**
     * 
     * @return True if there are no elements in the forest.
     */
    public abstract boolean isNull();
    
    
    /**
     * @return A human-readable representation of the forest, for debugging purposes.
     */
    public abstract String prettyPrint();

    /**
     * @return The rightmost tree of the Forest without the root node
     */
    public abstract Forest deleteRootOnRightTreeAndGetRightTree();

    /**
     * 
     * @return The hash string that represents this forest.
     */
    public abstract String hashString();

}

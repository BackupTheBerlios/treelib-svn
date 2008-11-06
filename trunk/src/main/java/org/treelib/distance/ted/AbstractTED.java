package org.treelib.distance.ted;

import org.treelib.Distance;
import org.treelib.Tree;
import org.treelib.Forest;



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
 * AbstractTED Implements some common functions to all TED implementations.
 * @author Arnoldo Jose Muller Molina
 */

abstract public class AbstractTED implements Distance<AbstractTED>{
	
	public final int DeleteCost = 1;

    public final int RenameCost = 1;
    
    protected Forest tree;
    
    protected AbstractTED(Forest tree){
    	this.tree = tree;
    }
    
    public double distance(AbstractTED object){
    	return ted(this.tree, object.tree);
    }
    
    protected abstract int ted(Forest tree1, Forest tree2);

    protected int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    protected int min(int a, int b, int c, int d) {
        return min(min(a, b, c), d);
    }

    protected int min(int a, int b) {
        return Math.min(a, b);
    }

    protected int renameCost(Tree a, Tree b) {
        if (a.getText().equals(b.getText())) {
            return 0;
        } else {
            return RenameCost;
        }
    }

}

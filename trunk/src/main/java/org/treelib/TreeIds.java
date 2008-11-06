package org.treelib;

import java.util.BitSet;

import antlr.collections.AST;

import org.treelib.misc.IntegerHolder;

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
 * TreeIds ids that uniquely identify each node. Useful when creating
 * dynamic programming tables.
 * @author Arnoldo Jose Muller Molina
 */
public class TreeIds
        extends Tree {

    private int id = -1; // id used to uniquely identify this node

    private BitSet contains;

    public BitSet containedNodes() {
        assert contains != null;
        return contains;
    }

    public void updateContains() {
        if (contains == null) {
            this.updateIdInfo();
            updateContainsAux(null);
        }
    }

    public boolean containsNode(int i) {
        assert contains != null;
        return this.contains.get(i);
    }

    protected void updateContainsAux(BitSet parent) {
        TreeIds n = (TreeIds) this.getLeftmostChild();
        BitSet me = new BitSet();
        while (n != null) {
            me.set(n.getId());
            n.updateContainsAux(me);
            n = (TreeIds) n.getNextSibling();
        }
        this.contains = me;
        if (parent != null) {
            parent.or(me);// update the parent
        }
    }

    public int getId() {
        assert id != -1;
        return id;
    }

    public void updateIdInfo() {
        updateIdInfoAux(new IntegerHolder(0));
    }

    public void updateIdInfoAux(IntegerHolder i) {
        this.id = i.getValue();
        TreeIds n = (TreeIds) this.getLeftmostChild();
        while (n != null) {
            i.inc();
            n.updateIdInfoAux(i);
            n = (TreeIds) n.getNextSibling();
        }
    }

    
}

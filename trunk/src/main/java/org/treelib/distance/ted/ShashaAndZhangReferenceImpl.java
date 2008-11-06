package org.treelib.distance.ted;

import java.util.HashMap;

import org.treelib.Distance;
import org.treelib.Tree;
import org.treelib.TreeFactory;
import org.treelib.Forest;
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
 * ShashaAndZhangReferenceImpl Classic O(n^4) implementation of TED.
 * @author Arnoldo Jose Muller Molina
 */

public class ShashaAndZhangReferenceImpl
        extends AbstractTED  {

    private HashMap < String, IntegerHolder > cache;

    
    public int getNodeCount(){
    	return super.tree.getSize();
    }
    
    
    public ShashaAndZhangReferenceImpl(Forest tree) {
    	super(tree);
        init();
    }

    protected void init() {
        cache = new HashMap < String, IntegerHolder >();
        // we don't want to be filling in the default case lots of times...
        put(TreeFactory.createEmptySliceForest(), TreeFactory
                .createEmptySliceForest(), 0);
    }

    protected void init(int n, int m) {
        cache = new HashMap < String, IntegerHolder >(n * m);
        // we don't want to be filling in the default case lots of times...
        put(TreeFactory.createEmptySliceForest(), TreeFactory
                .createEmptySliceForest(), 0);
    }
    
    

    /**
     * puts the given value into the cache
     * @param a
     * @param b
     * @param value
     */
    protected void put(final Forest a, final Forest b, int value) {
        put(makeKey(a, b), value);
    }

    protected void put(String k, int value) {
        cache.put(k, new IntegerHolder(value));
    }

    /**
     * returns an integer with the given value of the cache, otherwise returns
     * -1
     * @param k
     * @return
     */
    protected int get(String k) {
        IntegerHolder r = cache.get(k);
        if (r == null) {
            return -1;
        } else {
            return r.getValue();
        }
    }

    protected String makeKey(final Forest a, final Forest b) {
        StringBuilder str = new StringBuilder(a.hashString());
        str.append(",");
        str.append(b.hashString());
        return str.toString();
    }

    protected int ted(final Forest a, final Forest b) {
        init(a.getSize(), b.getSize());
        return tedAux(a, b);
    }
    
    

    protected int tedAux(final Forest a, final Forest b) {
        int res;
        String key = makeKey(a, b);
        int v = get(key); // get catched value. I was catched. no need to do
                            // anything.
        if (v != -1) {
            res = v;
        } else if (a.isNull() && b.isNull()) {
            res = 0;
        } else if (!a.isNull() && b.isNull()) {
            res = tedAux(a.deleteRightTreeNode(), b) + DeleteCost;
        } else if (a.isNull() && !b.isNull()) {
            res = tedAux(a, b.deleteRightTreeNode()) + DeleteCost;
        } else {
            int v1 = tedAux(a.deleteRightTreeNode(), b) + DeleteCost;
            int v2 = tedAux(a, b.deleteRightTreeNode()) + DeleteCost;
            int v3 = tedAux(a.deleteRootOnRightTreeAndGetRightTree(), b
                    .deleteRootOnRightTreeAndGetRightTree())
                    + tedAux(a.deleteRightTree(), b.deleteRightTree())
                    + renameCost(a.getRightTree(), b.getRightTree());
            res = min(v1, v2, v3);
        }
        // I am not catched, store the catched value.
        if (v == -1) {
            put(key, res);
        }
        return res;
    }

    public int tedSliceAST(Tree a, Tree b) throws Exception {
        throw new Exception("cannot call this method in this class");
    }
}

package org.treelib.distance.bdist;

import java.util.Iterator;
import java.util.LinkedList;
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
 * Bib holds the post and pre count for the given bibBranch. (the branch triple
 * is stored in a hash table elsewhere)
 * @author Arnoldo Jose Muller Molina
 */

public class Bib {

    private int count = 0;

    private List < BibPosition > positions = new LinkedList < BibPosition >();

    protected void incCount() {
        count++;
    }

    public void addPosition(int pre, int pos) {
        incCount();
        this.positions.add(new BibPosition(pre, pos));
        assert count == positions.size();
    }

    /*
     * returns the maximum sized mapping for the given position
     */
    protected int getMaxPos(BibPosition p, int pr) {
        int res = 0;
        Iterator < BibPosition > it = positions.iterator();
        while (it.hasNext()) {
            BibPosition otherBib = it.next();
            if (p.withinRange(otherBib, pr)) {
                res++;
            }
        }
        return res;
    }

    public int getMaximumSizedMapping(Bib b, int pr) {
        Iterator < BibPosition > it = b.positions.iterator();
        int res = 0;
        while (it.hasNext()) {
            BibPosition otherBib = it.next();
            int t = this.getMaxPos(otherBib, pr);
            // if(t > res){
            // res = t;
            // }
            // or
            // res +=t;
            if (t > 0) {
                res++;
            }
        }
        return res;
    }

    public int getCount() {
        return this.count;
    }

    public boolean containsPosition(int pre, int pos) {
        Iterator < BibPosition > it = this.positions.iterator();
        boolean res = false;
        while (it.hasNext()) {
            BibPosition otherBib = it.next();
            if (otherBib.equivalent(pre, pos)) {
                res = true;
                break;
            }
        }
        return res;
    }

}

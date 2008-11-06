package org.treelib.distance.bdist;

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
 * BibPosition: pre and post positions for the given bibBranch.
 * @author Arnoldo Jose Muller Molina
 */

public class BibPosition {
    private int pre;

    private int pos;

    BibPosition(int pre, int pos) {
        this.pre = pre;
        this.pos = pos;
    }

    public boolean withinRange(BibPosition p, int pr) {
        return (Math.abs(this.pre - p.pre) <= pr)
                && (Math.abs(this.pos - p.pos) <= pr);
    }

    public boolean equivalent(int pre, int pos) {
        return this.pre == pre && this.pos == pos;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomas
 */
public class SkipListEntry<E extends Comparable<E>> {
    
    
    private final E elem;
    private List<SkipListEntry<E>> nextPointers = new ArrayList<>();

    SkipListEntry(E elem) {
        this.elem = elem;
    }

    E getElem() {
        return elem;
    }

   SkipListEntry<E> floor(E needle) {
        if (elem.compareTo(needle) == 0) {
            return this;
        } else if (elem.compareTo(needle) > 0) {
            return null;
        }
        assert elem.compareTo(needle) < 0;
        for (int i = nextPointers.size() - 1; i >= 0; i--) {
            SkipListEntry<E> next = nextPointers.get(i);
            if (next.getElem().compareTo(needle) <= 0) {
                return next;
            }
        }
        return this;
    }

    void setNextAtLevel(int i, SkipListEntry<E> newNext) {
        if (i > nextPointers.size()) {
            throw new IllegalArgumentException(
                i + " is out of range " + nextPointers.size()
            );
        } else if (i == nextPointers.size()) {
            addLevelPointingTo(newNext);
        }
        assert i < nextPointers.size();
        nextPointers.set(i, newNext);
    }

    void addLevelPointingTo(SkipListEntry<E> next) {
        nextPointers.add(next);
    }

    /** does not check if the level is in-bounds */
    SkipListEntry<E> getNextAtLevel(int level) {
        return nextPointers.get(level);
    }

    /**
     * special version of the "floor(T)" for being run on the list head
     *
     * Returns null if the list is empty.
     * Otw returns a list of prev nodes for all levels.
     */
    List<SkipListEntry<E>> headFloor(E elem) {
        if (nextPointers.isEmpty()) return null;
        int sparsestList = nextPointers.size() - 1;
        return this.floorFromLevel(sparsestList, elem);
    }

    private List<SkipListEntry<E>> floorFromLevel(int i, E elem) {
        ArrayList<SkipListEntry<E>> ret = new ArrayList<>();
        for (; i >= 0; i--) {
            E nextElem = nextPointers.get(i).getElem();
            if (nextElem != null && elem.compareTo(nextElem) > 0) break;
            ret.add(this);
        }
        if (i >= 0) ret.addAll(nextPointers.get(i).floorFromLevel(i, elem));
        return ret;
    }

    boolean contains(E e) {
        return this.containsStartingAtLevel(nextPointers.size() - 1, e);
    }

    private boolean containsStartingAtLevel(int i, E e) {
        for (; i >= 0; i--) {
            SkipListEntry<E> nextNode = nextPointers.get(i);
            if (nextNode.getElem() != null) {
                if (nextNode.getElem().compareTo(e) == 0) {
                    return true;
                } else if (nextNode.getElem().compareTo(e) < 0) {
                    return nextNode.containsStartingAtLevel(i, e);
                } /* else if next is greater than 0, continue iterating */
            } /* else if next is tail, continue iterating */
        }
        return false;
    }
     
}

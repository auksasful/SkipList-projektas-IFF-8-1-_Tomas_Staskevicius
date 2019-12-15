/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 * @author Tomas
 */
public class SkipList<E extends Comparable<E>> extends AbstractList<E>{
    
    protected static Random r = new Random();

    /** head terminal for all the linked-lists */
    private SkipListEntry<E> head = new SkipListEntry<>(null);
    /** tail terminal for all the linked-lists */
    private SkipListEntry<E> tail = new SkipListEntry<>(null);

    public SkipList(E[] something) {
        Collections.addAll(this, something);
    }

    public SkipList() {
    }

    @Override 
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            SkipListEntry<E> current = head;

            @Override public boolean hasNext() {
                return current.getNextAtLevel(0) != tail;
            }

            @Override public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                current = current.getNextAtLevel(0);
                return current.getElem();
            }
        };
    }

    @Override public int size() {
        final int[] size = {0};
        iterator().forEachRemaining(t -> size[0]++);
        return size[0];
    }

    /** NB: this is not thread safe or reentrant */
    @Override 
    public boolean add(E elem) {
        /* floor is empty when there are no less-or-equal elements.
         * It is null when the skip-list is empty */
        List<SkipListEntry<E>> prevNodes = floor(elem);
        if (prevNodes == null) {
            addWhenEmpty(elem);
            return true;
        } else if (prevNodes.isEmpty()) {
            addToBeginning(elem);
            return true;
        }
        /* add it after the floor */
       SkipListEntry<E> newNode = new SkipListEntry<>(elem);
        for (int level = 0; level == 0 || r.nextBoolean(); level++) {
            SkipListEntry<E> prevNode = level < prevNodes.size() ? prevNodes.get(level) : head;
            SkipListEntry<E> wasNext = level < prevNodes.size() ? prevNode.getNextAtLevel(level) : tail;
            prevNode.setNextAtLevel(level, newNode);
            newNode.setNextAtLevel(level, wasNext);
        }
        return true;
    }

    private void addToBeginning(E elem) {
        SkipListEntry<E> newFirst = new SkipListEntry<>(elem);
        for (int i = 0; i == 0 || r.nextBoolean(); i++) {
            SkipListEntry<E> wasFirst = head.getNextAtLevel(i);
            head.setNextAtLevel(i, newFirst);
            newFirst.setNextAtLevel(i, wasFirst);
        }
    }

    /* TODO could we use addToBeginning instead of this function? */
    private void addWhenEmpty(E elem) {
        SkipListEntry<E> n = new SkipListEntry<>(elem);

        // definitely add the first layer
        head.addLevelPointingTo(n);
        n.addLevelPointingTo(tail);

        // possibly keep adding layers
        while (r.nextBoolean()) {
            head.addLevelPointingTo(n);
            n.addLevelPointingTo(tail);
        }
    }

    /**
     * currently O(N);
     * we could get O(logN) by storing "window" sizes in next-pointers
     */
    @Override public E get(int index) {
        Iterator<E> iterator = iterator();
        while (index-- > 0) iterator.next();
        return iterator.next();
    }
    
    public E getNext(int currentIndex) {
        Iterator<E> iterator = iterator();
        E el = null;
        
       while (currentIndex-- > 0) el = iterator.next();
       el = iterator.next();
       if(iterator.hasNext()){
            el = iterator.next();
            return el;
       }
       else{
           E first = get(0);
           return first;
       }
    }
    
     public E getBack(int currentIndex) {
        E el = null;
        
       if(currentIndex > 0){
            el = get(currentIndex - 1);
            return el;
       }
       else{
          Iterator<E> iterator = iterator();
          while(iterator.hasNext()){
              el = iterator.next();
          }
           return el;
       }
    }
    
    @Override
     public int indexOf(Object o){
         int index = 0;
         
         E element = get(index);
         if(element.equals((E)o)){
             return index;
         }
         
         while(index <= size() - 1){
             if(!element.equals((E)o)){
                 element = getNext(index);
             }
             else{
                 return index;
             }
             index++;
         }
        return -1;
     } 
     

    @Override public boolean contains(Object o) {
        return head.contains((E) o);
    }

    private List<SkipListEntry<E>> floor(E elem) {
        List<SkipListEntry<E>> nodes = head.headFloor(elem);
        if (nodes != null) Collections.reverse(nodes);
        return nodes;
    }
  
}

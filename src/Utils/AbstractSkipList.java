/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Iterator;

/**
 *
 * @author Tomas
 */
public interface AbstractSkipList<E> {
    
     public Iterator<E> iterator();
     
     public int size();
     
     public boolean add(E elem);
     
     public E get(int index);
     
      public E getNext(int currentIndex);
      
      public E getBack(int currentIndex);
      
      public int indexOf(Object o);
      
      public boolean contains(Object o);
      
      public boolean remove(Object o);
      
      public E getNextLevel1(int currentIndex);
      
}

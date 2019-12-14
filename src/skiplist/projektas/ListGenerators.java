/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiplist.projektas;

import Utils.SkipList;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Tomas
 */
public class ListGenerators {
    
    static Random generator = new Random();

    static void generateList(LinkedList<DataObj> list, int size) {
        for (int i = 0; i < size; i++) {
            float a = generator.nextFloat();
            String v = Float.toString(a);
            list.add(new DataObj(v, i));
        }
    }
    
    static void generateList2(SkipList<DataObj> list, int size) {
        for (int i = 0; i < size; i++) {
            float a = generator.nextFloat();
            String v = Float.toString(a);
            list.add(new DataObj(v, i));
        }
    }
    
    static void generateIndexes(int[] indexes, int listSize) {
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = generator.nextInt(listSize);
        }
    }
}

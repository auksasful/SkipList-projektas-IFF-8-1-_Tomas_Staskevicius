/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiplist.projektas;

import Utils.SkipList;

/**
 *
 * @author Tomas
 */
public class TestProg {
      public static void main(String[] args)
   {

      SkipList S = new SkipList();
    DataObj elem1 = new DataObj("Petras", 168);
    DataObj elem2 = new DataObj("Jonas", 568);
    DataObj elem3 = new DataObj("Benius", 245);
      
      
      S.add(elem1);
      S.add(elem2);
      S.add(elem3);
      System.out.println(S.getBack(0));
      //System.out.println(S.getNext());
     // S.printHorizontal();
      
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

    //  S.put("ABC", 123);
      //S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

    //  S.put("DEF", 123);
      //S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

      //S.put("KLM", 123);
      //S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

     // S.put("HIJ", 123);
      //S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

      //S.put("GHJ", 123);
      //S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

   //   S.put("AAA", 123);
    //  S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");


   }
}

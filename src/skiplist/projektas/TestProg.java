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

      S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

      S.put("ABC", 123);
      S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

      S.put("DEF", 123);
      S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

      S.put("KLM", 123);
      S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

      S.put("HIJ", 123);
      S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

      S.put("GHJ", 123);
      S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

      S.put("AAA", 123);
      S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");


   }
}

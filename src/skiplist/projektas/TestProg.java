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
    DataObj elem3 = new DataObj("Benas", 245);
      
      
      S.add(elem1);
      S.add(elem2);
      S.add(elem3);
      System.out.println("Pridėti 3 elementai");
      System.out.println(S);
      
      System.out.println("\nPašalinamas elementas 245");
      S.remove(elem3);
      System.out.println(S);
      
      System.out.println("\nMetodas contains su elementu 568");
      System.out.println(S.contains(elem2));
      
      System.out.println("\n Metodas indexOf su elementu 168");
      System.out.println(S.indexOf(elem1));
      
      System.out.println("\n Metodas getBack nuo indekso 1");
      System.out.println(S.getBack(1));
      
      System.out.println("\n Metodas getNext nuo indekso 0");
      System.out.println(S.getNext(0));
      
      System.out.println("\n Metodas get indeksui 0");
      System.out.println(S.get(0));
      
      System.out.println("\n Metodas size()");
      System.out.println(S.size());
     

   }
}

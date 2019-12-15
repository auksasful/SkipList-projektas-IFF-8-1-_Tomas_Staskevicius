/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiplist.projektas;


//import edu.ktu.ds.lab3.gui.ValidationException;
//import edu.ktu.ds.lab3.utils.HashMap;
//import edu.ktu.ds.lab3.utils.HashType;
//import edu.ktu.ds.lab3.utils.Ks;
//import edu.ktu.ds.lab3.utils.ParsableHashMap;
//import edu.ktu.ds.lab3.utils.ParsableMap;
import Utils.SkipList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tomas
 */
public class SimpleBenchmark {
     public static final String FINISH_COMMAND = "                               ";
   // private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("edu.ktu.ds.lab3.Manogui.messages");

    private final Timekeeper timekeeper;

    private final String[] BENCHMARK_NAMES = {"linkedListAdd", "skipListAdd", "treeSetAdd", "linkedListContains", "skipListContains", "treeSetContains"};
    private final int[] COUNTS = {10000, 20000, 40000, 80000};

    private final Queue<String> chainsSizes = new LinkedList<>();
    
    private final SkipList skipList = new SkipList();
    private final LinkedList linkedList = new LinkedList();
    private final TreeSet treeSet = new TreeSet();

    /**
     * For console benchmark
     */
    public SimpleBenchmark() {
        timekeeper = new Timekeeper(COUNTS);
    }

    /**
     * For Gui benchmark
     *
     * @param resultsLogger
     * @param semaphore
     */
    public SimpleBenchmark(BlockingQueue<String> resultsLogger, Semaphore semaphore) {
        semaphore.release();
        timekeeper = new Timekeeper(COUNTS, resultsLogger, semaphore);
    }

    public static void main(String[] args) {
        executeTest();
    }

    public static void executeTest() {
        // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        System.out.println("Greitaveikos tyrimas:\n");
        new skiplist.projektas.SimpleBenchmark().startBenchmark();
    }

    public void startBenchmark() {
        try {
            benchmark();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void benchmark() throws InterruptedException {
        
          Runtime runtime = Runtime.getRuntime();
        try {
            chainsSizes.add("maxChainLength");
            chainsSizes.add("   kiekis      " + BENCHMARK_NAMES[0] + "   " + BENCHMARK_NAMES[1]);
            
              DataObj[] valuesArray = new DataObj[80000];
              
              for(int i = 0; i < valuesArray.length; i++){
                  DataObj newVal = new DataObj("data " + (i + 1), i + 1);
                  valuesArray[i] = newVal;
              }
            
            
            for (int k : COUNTS) {
                
                

              //  skipList.clear();
               // linkedList.clear();
                
                timekeeper.startAfterPause();
                
                
                
                
                
                timekeeper.start();
                
                 long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                 for (int i = 0; i < k; i++) {
                     linkedList.add(valuesArray[i]);
                 }
                 long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) - usedMemoryBefore;
                 timekeeper.finish(BENCHMARK_NAMES[0]);
                
                 
                // set = doubleHash.keySet().toArray();
                
               // timekeeper.start();
                usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                for (int i = 0; i < k; i++) {
                skipList.add(valuesArray[i]);
                }
                 long usedMemory2 = (runtime.totalMemory() - runtime.freeMemory()) - usedMemoryBefore;
                 timekeeper.finish(BENCHMARK_NAMES[1]);
                
                 //set = singleHash.keySet().toArray();
                 
                 //String key = Integer.toString(k/2);
                  
               // timekeeper.start();
               usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
               
               
               
                usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                for (int i = 0; i < k; i++) {
                treeSet.add(valuesArray[i]);
                }
                 long usedMemory3 = (runtime.totalMemory() - runtime.freeMemory()) - usedMemoryBefore;
                 timekeeper.finish(BENCHMARK_NAMES[2]);
                
                 //set = singleHash.keySet().toArray();
                 
                 //String key = Integer.toString(k/2);
                  
               // timekeeper.start();
               usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                
               
                
               for (int i = 0; i < k; i++) {
                    linkedList.contains(valuesArray[k/2]);
               }
                 long usedMemory4 = (runtime.totalMemory() - runtime.freeMemory()) - usedMemoryBefore;
                timekeeper.finish(BENCHMARK_NAMES[3]);
                 
                  
                 usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
               // timekeeper.start();
                
                 for (int i = 0; i < k; i++) {
                    skipList.contains(valuesArray[k/2]);
               }
                 long usedMemory5 = (runtime.totalMemory() - runtime.freeMemory()) - usedMemoryBefore;
                timekeeper.finish(BENCHMARK_NAMES[4]);
                
                
                
                 usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
               // timekeeper.start();
                
                 for (int i = 0; i < k; i++) {
                    treeSet.contains(valuesArray[k/2]);
               }
                 long usedMemory6 = (runtime.totalMemory() - runtime.freeMemory()) - usedMemoryBefore;
                timekeeper.finish(BENCHMARK_NAMES[5]);
               
               
                 System.out.println("Memory consumption: 1) " + usedMemory + " 2) " +
                     usedMemory2 + " 3) " + usedMemory3 + " 4) " + usedMemory4 + " 5) " + usedMemory5 + " 6) " + usedMemory6);
                 
                 timekeeper.seriesFinish();
            }

            StringBuilder sb = new StringBuilder();
            chainsSizes.forEach(p -> sb.append(p).append(System.lineSeparator()));
            timekeeper.logResult(sb.toString());
            timekeeper.logResult(FINISH_COMMAND);
        } catch (InterruptedException e) {
            timekeeper.logResult(e.getMessage());
        }
    }
    
      
    
    
    
    
    
    
}

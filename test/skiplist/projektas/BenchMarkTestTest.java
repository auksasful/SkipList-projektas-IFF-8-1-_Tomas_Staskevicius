/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiplist.projektas;

import Utils.SkipList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static skiplist.projektas.SimpleBenchmark.LIST_SIZES;

/**
 *
 * @author Tomas
 */
public class BenchMarkTestTest {
    
    static final int[] LIST_SIZES = {/*64_000,*/ 10_000, 20_000, 40_000, 80_000};
    
    
    
    public BenchMarkTestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void basicAPIChecks() throws Exception {
        List<String> skipList = new SkipList<>(new String[]{"something", "something"});
        List<String> comparison = new LinkedList<>(Arrays.asList("something", "something"));
        assertEquals(comparison.size(), skipList.size());
        assertEquals(comparison.contains("something"), skipList.contains("something"));
        assertEquals(comparison.add("abcd"), skipList.add("abcd"));
        assertEquals(comparison.contains("abcd"), skipList.contains("abcd"));
    }

    @Test
     public void shouldBeFasterThanArrayListForContains() throws Exception {
         
         System.out.format("%1$8s%2$16s%3$16s%n", "", "linked list", "skip list");
        for (int listSize : LIST_SIZES) {
            List<Integer> skipList = new SkipList<>();
            List<Integer> comparison = new LinkedList<>();
            Random r = new Random();
            int testSize = 1000;
            for (int i = 0; i < testSize; i++) {
                Integer nextInt = r.nextInt(80000);
                assertEquals(comparison.add(nextInt), skipList.add(nextInt));
            }
            Integer[] onesWeDidntAdd = new Integer[testSize];
            for (int i = 0; i < testSize; i++) {
                onesWeDidntAdd[i] = r.nextInt(80000);
            }
            boolean[] expectedResponses = new boolean[testSize];
            long expectedStart = System.nanoTime();
            for (Integer query : comparison) assertTrue(comparison.contains(query));
            for (int i = 0; i < onesWeDidntAdd.length; i++) {
                expectedResponses[i] = comparison.contains(onesWeDidntAdd[i]);
            }
            long expectedEnd = System.nanoTime();
            long realStart = System.nanoTime();
            for (Integer query : comparison) assertTrue(skipList.contains(query));
            for (int i = 0; i < onesWeDidntAdd.length; i++) {
                assertEquals(expectedResponses[i], skipList.contains(onesWeDidntAdd[i]));
            }
            long realEnd = System.nanoTime();
            System.out.printf("linked list: %d\n skip list: %d\n",
                expectedEnd - expectedStart,
                realEnd - realStart
            );
        }
    }
    
    
    
    
}

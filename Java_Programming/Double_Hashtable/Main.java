/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;



public class Main {
    public static void main(String[] args) throws FileNotFoundException {

    String[] targets = {"the", "Adventure", "fish", "murmured", "committed"};
    ChainedHashtable chainedTest = new ChainedHashtable();
    //String x = "/Users/mannypasquale/Desktop/Big.java";
    String x = "Big.java";
    Scanner input = new Scanner(new File(x));
    while (input.hasNext()) {
       chainedTest.put(input.next(), 1);
    }
    System.out.println("CHAINING\n--------\n");
        long startTime = System.currentTimeMillis();


        //...
        // 2. Look up and output the counts for targets.
        // ...
        for (String target : targets) {
            System.out.println(target + ": " + chainedTest.get(target));
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Time for Chaining: " + (endTime - startTime) + "ms.");

    
        DoubleHashtable doubleHashTest = new DoubleHashtable();
        Scanner input2 = new Scanner(new File(x));
        while (input2.hasNext()) {
            doubleHashTest.put(input2.next(), 1);
        }
        
    System.out.println("\n\nDOUBLE HASHING\n---------------\n");
        System.gc();
        startTime = System.currentTimeMillis();
        // ...
        // 4. Look up and output the counts for targets.
        // ...
        for (String target : targets) {
            System.out.println(target + ": " + doubleHashTest.get(target));
        }
        endTime = System.currentTimeMillis();
        System.out.println("Time for Double Hashing: " + (endTime - startTime) + "ms.");
    }
}
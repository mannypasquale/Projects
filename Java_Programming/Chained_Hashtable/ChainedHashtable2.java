/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.LinkedList;

/**
 *
 * @author mannypasquale
 */
public class ChainedHashtable extends HashData implements LabHashtable{

    private int size;
    private LinkedList[] table;
    //private HashData data;
    
    
    public ChainedHashtable(){
        table = new LinkedList[11];
        for (int i = 0; i < table.length; i++){
            table[i] = null;//linkedlist array is all null
            //System.out.println("created..." + i);
        }
        size = 0; //b/c we need to set the size to how many buckets are filled
        //System.out.println("the length of the array is " + table.length);
    }
    
    @Override
    public void put(Object key, Object value){
        
        if (size > 0.75 * table.length){
            reSize();
        }
        int hash = Math.abs(key.hashCode() % (table.length));
        HashData data = new HashData();
        data.key = key;
        data.value = value;
        //System.out.println("the hash code is... " + hash);
        if (table[hash] == null){//if the bucket is null then we make a new linked list
            table[hash] = new LinkedList<>();
            table[hash].add(data);//add the data which has the key and value
            size++;//increase the size bc it was null but now one bucket has something
            //System.out.println("adding a new LinkedList");
        }
        else {//(table[hash] != null){
       
                
            
            
            
            for (int i = 0; i < table[hash].size(); i ++){
                //HashData testData = new HashData();
                HashData testData = (HashData)table[hash].get(i);
                if (testData.key.equals(data.key)){
                    
                    testData.value = (int)testData.value + 1;
                    //(HashData)table[hash].get(i) = testData.value;
                    //table[hash].add(i, testData);
                    data.value = testData.value;
                    //System.out.println("added 1 to a value");
                    //System.out.println(testData.key);
                    return;
                }
                
    
            }
            table[hash].add(data);  
            //System.out.println("Adding data to linked list");
                
        }
            }
        
    
    
    @Override
    public Object get(Object key){
        int hash = Math.abs(key.hashCode() % (table.length));
        for (int i = 0; i < table[hash].size(); i++){
            //HashData testData = new HashData();
            HashData testData;
            testData = (HashData)table[hash].get(i);
            if (testData.key == key){
                return testData.value;
            }
        }
        
        return 0;
    }
    
    @Override
    public int getSize(){
        int testSize = 1;
        for (int index = 0; index < table.length; index ++){
            if (table[index] != null) testSize++;
        }
        return size;
    }
    
    
    @Override
    public Object[] getTable(){
    return table;
    }
    
    public void reSize(){
        //System.out.println("GOT TO RESIZE");
        LinkedList[] oldTable = table;
        table = new LinkedList[(oldTable.length)*2];
        size = 0;
        
        for(int i =0; i < oldTable.length; i++){
            //System.out.println("got to first for loop");
            if (oldTable[i] != null){
                //System.out.println("testing to see if oldTable for loop iteration"
                        //+ "is not null");
                int index = 0;
                
                while (index < (oldTable[i].size())){
                    //System.out.println("turns out its not null thus entered"
                            //+ " the while loop");
                    HashData tmpData;
                    tmpData = (HashData)oldTable[i].get(index);
                    System.out.println("current grabbed hashdata key is " + tmpData.key);
                    System.out.println("index of the linked list is " + index);
                    if (tmpData != null ){
                        
                        put(tmpData.key, tmpData.value);
                        //System.out.println(tmpData.key + "   " + tmpData.value);
                        
                        //System.out.println("Transferred a HashData item");
                        index++;
                    }
                    
                    //else if (tmpData != null && (int)tmpData.value > 1){
                    //    int newIndex = (int)tmpData.value;
                    //    while (newIndex > 1){
                      //      put(tmpData.key, 1);
                        //    newIndex = newIndex - 1;
                        //}
                        //index++;
                    //}
                    
                }
                
            
            }
        
        }
    
    //size = table.getSize();
    }
    
}

    
    
//you have to check to see if the current number of 
//array elements used is greater than 0.75 * table.length.
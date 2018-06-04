/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package hashtable;

/**
 *
 * @author mannypasquale
 */
public class DoubleHashtable implements LabHashtable {

// Variables used to manage the secondary hash function. You do not need to
// modify or reference these in your own code.
    private int currentArraySize;
    private int currentStepFactor;

// Variables used for the actual hashtable.
    private int size;
    private HashData[] table;

    public Object[] getTable() {
        return table;
    }

// No-arg constructor creates a hashtable with initial capacity of 11.
    public DoubleHashtable() {

// Default values for the instance data members.
        size = 0;
        currentArraySize = 0;
        currentStepFactor = 1;
        table = new HashData[199];
        // TO-DO: create the hashtable array as specified.
// ...
    }

// Check to see if the table is 75% full; if so, make a new array of size equal
// to **THE NEAREST PRIME NUMBER AFTER** twice the current array size. 
    private void resizeIfNecessary() {
        if (size >= 0.75 * table.length) {
            resize();
        }

    }

    /**
     * A helper method to compute the step factor needed for double-hashing. We
     * cache the last step factor, so we don't have to recalculate with every
     * hash.
     *
     * @return the current step factor to use in calculating the secondary hash
     * value.
     */
    private int stepFactor() {
        if (table.length != currentArraySize) {
            currentStepFactor = Primes.nextPrime(Math.max(30, table.length / 1000))
                    % table.length;
            currentArraySize = table.length;
        }
        return currentStepFactor;
    }

// Insert a new key/value pair into the hashtable using double-hashing.
    @Override
    public void put(Object key, Object value) {
// TO-DO: make a new HashData object, and insert it into the table.
// ...
        boolean NotPlaced = true;
        HashData data = new HashData();
        data.key = key;
        data.value = value;
        int index = Math.abs(key.hashCode() % table.length);
        int step = Math.abs(stepFactor() - Math.abs(key.hashCode()) % table.length);
        if (step == 0) {
            step = 1;
        }
        while (NotPlaced) {
            if (table[index] == null) {
                table[index] = data;
                NotPlaced = false;
				size++;
            } else {
                if (data.key.equals(table[index].key)) {
                    table[index].value = (int) table[index].value + 1;
                    NotPlaced = false;
                } else {
                    index = index + step;
                    index %= table.length;
                }
            }
        }
// After inserting check to see if resizing is necessary
        resizeIfNecessary();
    }

// Try to find the object value associated with the requested key; return the
// object value if found, or null if the key is not in the table.
    @Override
    public Object get(Object key) {
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] != null) {
            return table[index].value;
        }
        return 0;
    }

    @Override
    public int getSize() {
        int count = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                count++;
            }
        }
        return count;
    }

// Returns the total number of key/value pairs currently stored in this hashtable.
    public void resize() {
        HashData newData = new HashData();
        HashData[] oldTable = table;
        int newTableSize = Primes.nextPrime(table.length * 2);
        HashData[] newTable = new HashData[newTableSize];
        table = newTable;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                newData.key = oldTable[i].key;
                newData.value = oldTable[i].value;
                put(newData.key, newData.value);
            }
        }
    }
}

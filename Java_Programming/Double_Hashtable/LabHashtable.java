/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author mannypasquale
 */
public interface LabHashtable {
    
    public void put(Object key, Object value);
    public Object get(Object key);
    public int getSize();
    public Object[] getTable();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiplist.projektas;

/**
 *
 * @author Tomas
 */
public class DataObj implements Comparable<DataObj>{
    public String name;
    public int ID;
    
    public DataObj(String name, int id){
        this.name = name;
        this.ID = id;
    }
    
    @Override
    public String toString(){
        return ID + " " + name; 
    }
    
    
    @Override
    public int compareTo(DataObj t) {
       if(this.ID > t.ID){
           return 1;
       }
      else if(this.ID > t.ID){
          return -1;
      }
      else{
          return 0;
      }
       //To change body of generated methods, choose Tools | Templates.
    }

    
}

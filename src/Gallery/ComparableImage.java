/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gallery;

import javafx.scene.image.Image;

/**
 *
 * @author Tomas
 */
public class ComparableImage  implements Comparable<ComparableImage>{
    int id;
    Image img;
    
    ComparableImage(int id, Image img){
        this.id = id;
        this.img = img;
    }
    
    
    @Override
    public String toString(){
        return id + " " + img; 
    }
    
    
    @Override
    public int compareTo(ComparableImage t) {
       if(this.id > t.id){
           return 1;
       }
      else if(this.id > t.id){
          return -1;
      }
      else{
          return 0;
      }
       //To change body of generated methods, choose Tools | Templates.
    }
}

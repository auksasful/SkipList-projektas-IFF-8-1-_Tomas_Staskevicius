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
    Image img;
    
    ComparableImage(Image img){
        this.img = img;
    }
    
    
    @Override
    public String toString(){
        return "" + img; 
    }
    
    
    @Override
    public int compareTo(ComparableImage t) {
       return this.img.toString().compareTo(t.img.toString());

    }
}

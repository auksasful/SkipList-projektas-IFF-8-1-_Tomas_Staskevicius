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
    String imageName;
    
    ComparableImage(Image img, String name){
        this.img = img;
        this.imageName = name;
    }
    
    
    @Override
    public String toString(){
        return "" + img.toString(); 
    }
    
    
    @Override
    public int compareTo(ComparableImage t) {
       return this.imageName.compareTo(t.imageName);
    }
}

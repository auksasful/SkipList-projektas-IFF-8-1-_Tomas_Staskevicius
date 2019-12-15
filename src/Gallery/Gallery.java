/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gallery;

import Utils.SkipList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author Tomas
 */
public class Gallery extends Application{
    SkipList<ComparableImage> imgList = new SkipList();
    int curId = -1;
    
     public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
            Image im = new Image(new FileInputStream("images\\javafx.jpg"));
            Image im1 = new Image(new FileInputStream("images\\javafx1.jpg"));
            Image im2 = new Image(new FileInputStream("images\\javafx2.jpg"));
            Image im3 = new Image(new FileInputStream("images\\javafx3.jpg"));
            imgList.add(new ComparableImage(im, "javafx.jpg"));
            imgList.add(new ComparableImage(im1, "javafx1.jpg"));
            imgList.add(new ComparableImage(im2, "javafx2.jpg"));
            imgList.add(new ComparableImage(im3, "javafx3.jpg"));
            curId = 0;
            ImageView img = new ImageView(imgList.get(curId).img);
            //img.setFitHeight(400);
            //img.setFitWidth(400);
            img.setFitHeight(400);
            img.setFitWidth(400);
            img.setPreserveRatio(true);
            TextArea searchValueField = new TextArea();
            Button searchBtn = new Button("Paieška");
            searchBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
                                String fieldValue = searchValueField.getText();
                            try {
                                ComparableImage searchObj = new ComparableImage(new Image(new FileInputStream("images\\" + fieldValue)), fieldValue);
                                if(imgList.contains(searchObj)){
                                    img.setImage(searchObj.img);
                                }
                                else{
                                    imgList.add(searchObj);
                                    img.setImage(searchObj.img);
                                }
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Gallery.class.getName()).log(Level.SEVERE, null, ex);
                            }
                               
			}
		});
            Button btn1 = new Button("LEFT");
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
                                img.setImage(imgList.getBack(curId).img);
                                if(curId < 1){
                                    curId = imgList.size() - 1;
                                }else{
                                    curId--;
                                }
			}
		});
		
                
		Button btn2 = new Button("RIGHT");    
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
                                img.setImage(imgList.getNext(curId).img);
                                 if(curId >= imgList.size() - 1){
                                    curId = 0;
                                }else{
                                    curId++;
                                }
			}
		});
                
            FlowPane root = new FlowPane();
            root.getChildren().addAll(searchValueField, searchBtn, btn1,img, btn2);
            
		Scene scene = new Scene(root, 1000, 1000);
                
                stage.setTitle("Galerija - Tomas Staškevičius IFF -8/1");
                stage.setResizable(false);
		stage.setScene(scene);
		
		stage.show();
	}
    
    
}

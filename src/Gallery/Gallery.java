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
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
            
            Text validityText = new Text();
            Text currentImgText = new Text();
            Text imageIndexText = new Text();
            currentImgText.setText("Esamas paveiksliukas: " + imgList.get(curId).imageName);
            imageIndexText.setText("Esamas indeksas: " + curId);
            
            TextField searchValueField = new TextField();
            Button searchBtn = new Button("Paieška");
            searchBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
                                String fieldValue = searchValueField.getText();
                            try {
                                ComparableImage searchObj = new ComparableImage(new Image(new FileInputStream("images\\" + fieldValue)), fieldValue);
                                if(imgList.contains(searchObj)){
                                    img.setImage(imgList.get(imgList.indexOf(searchObj)).img);
                                    curId = imgList.indexOf(searchObj);
                                    validityText.setText("Paveiksliukas jau yra sąraše, perkeliama į jo poziciją.");
                                    validityText.setFill(Color.GREEN);
                                    currentImgText.setText("Esamas paveiksliukas: " + searchObj.imageName);
                                    imageIndexText.setText("Esamas indeksas: " + curId);
                                }
                                else{
                                    imgList.add(searchObj);
                                    
                                    img.setImage(imgList.get(imgList.indexOf(searchObj)).img);
                                    curId = imgList.indexOf(searchObj);
                                    validityText.setText("Paveiksliuko nėra sąraše. Paveiksliukas pridedamas į sąrašą ir pereinama į jo poziciją.");
                                     validityText.setFill(Color.GREEN);
                                    currentImgText.setText("Esamas paveiksliukas: " + searchObj.imageName);
                                    imageIndexText.setText("Esamas indeksas: " + curId);
                                }
                            } catch (FileNotFoundException ex) {
                                 validityText.setText("Paveiksliuko nėra failų aplankale!");
                                 validityText.setFill(Color.RED);
                                //Logger.getLogger(Gallery.class.getName()).log(Level.SEVERE, null, ex);
                            }
                               
			}
		});
            Button btn1 = new Button("KAIRĖN");
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
                                ComparableImage nxt = imgList.getBack(curId);
                                img.setImage(nxt.img);
                                if(curId < 1){
                                    curId = imgList.size() - 1;
                                }else{
                                    curId--;
                                }
                                currentImgText.setText("Esamas paveiksliukas: " + nxt.imageName);
                                imageIndexText.setText("Esamas indeksas: " + curId);
                                validityText.setText("Paeita į kairę");
                                validityText.setFill(Color.GREEN);
                                
			}
		});
		
                
		Button btn2 = new Button("DEŠINĖN");    
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
                                ComparableImage bck = imgList.getNext(curId);
                                img.setImage(bck.img);
                                 if(curId >= imgList.size() - 1){
                                    curId = 0;
                                }else{
                                    curId++;
                                }
                                 
                                currentImgText.setText("Esamas paveiksliukas: " + bck.imageName);
                                imageIndexText.setText("Esamas indeksas: " + curId);
                                validityText.setText("Paeita į dešinę");
                                validityText.setFill(Color.GREEN);
			}
		});
                
                
                
                
                
            BorderPane root = new BorderPane();
            
            HBox hBox1 = new HBox();
            hBox1.getChildren().addAll(searchValueField, searchBtn);
            
            root.setTop(hBox1);
            
            
            
            HBox hBox2 = new HBox();
            hBox2.getChildren().addAll(img);
            root.setCenter(hBox2);
            
            HBox hBox3 = new HBox();
            hBox3.getChildren().addAll(btn1);
            root.setLeft(hBox3);
            
             HBox hBox4 = new HBox();
            hBox4.getChildren().addAll(btn2);
            root.setRight(hBox4);
            
           VBox vBox1 = new VBox();
            
            
          

            vBox1.getChildren().add(currentImgText);

            vBox1.getChildren().add(imageIndexText);

            

            vBox1.getChildren().add(validityText);

            root.setBottom(vBox1);
            
            //root.getChildren().addAll(searchValueField, searchBtn, btn1,img, btn2, validityText);
            
		Scene scene = new Scene(root, 550, 500);
                
                stage.setTitle("Galerija - Tomas Staškevičius IFF -8/1");
                stage.setResizable(false);
		stage.setScene(scene);
		
		stage.show();
	}
    
    
}

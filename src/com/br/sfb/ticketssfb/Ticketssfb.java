/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sfb.ticketssfb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author johnpc
 */
public class Ticketssfb extends Application {
               
    //define your offsets here
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
  public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Dashboard.fxml"));
         //you can use underdecorated or transparent.
        stage.initStyle(StageStyle.TRANSPARENT);
      
       
       //grab your root here
             root.setOnMousePressed((MouseEvent event) -> {
                 xOffset = event.getSceneX();
                 yOffset = event.getSceneY();
        });
        
        //move around here
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
       
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        
        stage.setScene(scene); 
        

        stage.show();
          
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        launch(args);
        
        
    }
    
}

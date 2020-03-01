/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sfb.ticketssfb.controle;

import com.br.sfb.ticketssfb.Ticketssfb;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;


/**
 *
 * @author Edidelson
 */
public class FXMLGenerico {
Node overviewPage ;
    
    /**
     * Shows the person overview scene.
     *
     * @param rootLayout
     * @param fxml
     */
    public void showPersonOverview(Pane rootLayout, String fxml) {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader = new FXMLLoader(Ticketssfb.class.getResource(fxml));
            rootLayout.getChildren().clear();
            //System.out.println(loader.load().toString());
            overviewPage = loader.load();
            rootLayout.getChildren().add(overviewPage);
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }
    public void showPersonOverview(Pane rootLayout, String fxml, Object controller) {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader = new FXMLLoader(Ticketssfb.class.getResource(fxml));
            rootLayout.getChildren().clear();
            //System.out.println(loader.load().toString());
            loader.setController(controller);
            overviewPage = loader.load();
            rootLayout.getChildren().add(overviewPage);
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }
  
public Node returnNode(){
return this.overviewPage;
}
}

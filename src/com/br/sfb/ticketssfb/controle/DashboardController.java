/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sfb.ticketssfb.controle;

import com.br.sfb.ticketssfb.CarregarEmail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author johnpc
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    String n = "oii";
    
     @FXML
    public AnchorPane paneCentral;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 CarregarEmail email = new CarregarEmail();
    }    
    @FXML
    void btTickets(ActionEvent event) {
        FXMLGenerico generico = new FXMLGenerico();
        
        HomeTicketsController tc = new HomeTicketsController();
        generico.showPersonOverview(paneCentral, "fxml/HomeTickets.fxml");
 }
     @FXML
    void btUsuario(ActionEvent event) {
    FXMLGenerico generico = new FXMLGenerico();
    generico.showPersonOverview(paneCentral, "fxml/Usuario.fxml");
        
    }
    @FXML
    void btRotulo(ActionEvent event) {
FXMLGenerico generico = new FXMLGenerico();
generico.showPersonOverview(paneCentral, "fxml/Rotulos.fxml");        

    }
    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    private void maximize(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if(stage.isFullScreen())
        stage.setFullScreen(false);
        else
        stage.setFullScreen(true);    
    }
    @FXML 
      private void exit(ActionEvent event) {        
     System.exit(0);
      }
}

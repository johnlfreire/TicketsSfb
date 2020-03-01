/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sfb.ticketssfb.controle;

import com.br.sfb.ticketssfb.enumerator.Atribuicoes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;


/**
 * FXML Controller class
 *
 * @author johnpc
 */
public class HomeTicketsController implements Initializable {

    /**
     * Initializes the controller class.
     */

 @FXML
 private Pane PaneCentralHome;
 
 @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }  
    @FXML
    void testehome(ActionEvent event) {
   FXMLGenerico generico = new FXMLGenerico();
        
    }
@FXML
void todosTickes(ActionEvent event) {
   FXMLGenerico generico = new FXMLGenerico();
   TicketsController ticketsController = new TicketsController();
   ticketsController.strTabela = "atribuicao";
   ticketsController.atribuicao= Atribuicoes.RECEBIDO;
   generico.showPersonOverview(PaneCentralHome, "fxml/Tickets.fxml",ticketsController);
   }
       @FXML
    void TicketsLixeira(ActionEvent event) {
   FXMLGenerico generico = new FXMLGenerico();
   TicketsController ticketsController = new TicketsController();
   ticketsController.strTabela = "atribuicao";
   ticketsController.atribuicao= Atribuicoes.ARQUIVADO;
   generico.showPersonOverview(PaneCentralHome, "fxml/Tickets.fxml",ticketsController);
    }

    @FXML
    void TicketsSpam(ActionEvent event) {
   FXMLGenerico generico = new FXMLGenerico();
   TicketsController ticketsController = new TicketsController();
   ticketsController.strTabela = "atribuicao";
   ticketsController.atribuicao= Atribuicoes.SPAM;
   generico.showPersonOverview(PaneCentralHome, "fxml/Tickets.fxml",ticketsController);
    }

    @FXML
    void meustickets(ActionEvent event) {
 FXMLGenerico generico = new FXMLGenerico();
   TicketsController ticketsController = new TicketsController();
   ticketsController.strTabela = "usuario";
   ticketsController.atribuicao= Atribuicoes.RECEBIDO;
   generico.showPersonOverview(PaneCentralHome, "fxml/Tickets.fxml",ticketsController);
    }

    @FXML
    void ticketsImportantes(ActionEvent event) {

    }

    @FXML
    void ticketsRascunho(ActionEvent event) {
   FXMLGenerico generico = new FXMLGenerico();
   TicketsController ticketsController = new TicketsController();
   ticketsController.strTabela = "atribuicao";
   ticketsController.atribuicao= Atribuicoes.RASCUNHO;
   generico.showPersonOverview(PaneCentralHome, "fxml/Tickets.fxml",ticketsController);
    }

   public void testehomes() {
   FXMLGenerico generico = new FXMLGenerico();
        
       
    }
    @FXML
    void atribuir(ActionEvent event) {
        System.out.println();
    }
    
    

}

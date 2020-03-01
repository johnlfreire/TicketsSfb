/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sfb.ticketssfb.controle;

import com.br.sfb.ticketssfb.modelo.ModeloTicket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;


/**
 * FXML Controller class
 *
 * @author johnpc
 */
public class TicketController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private WebView webView;
    private ModeloTicket ticket; 
    private WebEngine webEngine;


    public TicketController(ModeloTicket ticket){
    this.ticket = ticket;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
carregarconteudo();
    }    
    @FXML
    void Enviar(ActionEvent event) {

    }
   void carregarconteudo(){  
   webEngine = webView.getEngine();
   webEngine.loadContent(ticket.getConteudo());
   }
      @FXML
void goBack(ActionEvent event)
{ 
  final WebHistory history = webEngine.getHistory();
  ObservableList<WebHistory.Entry> entryList = history.getEntries();
  int currentIndex = history.getCurrentIndex();

  Platform.runLater(() -> 
  {
    history.go(entryList.size() > 1 
      && currentIndex > 0
            ? -1
            : 0); 
  });        
}
 @FXML
void goForward(ActionEvent event)
{
  final WebHistory history = webEngine.getHistory();   
  ObservableList<WebHistory.Entry> entryList2 = history.getEntries();
  int currentIndex = history.getCurrentIndex();

  Platform.runLater(() -> 
  {
    history.go(entryList2.size() > 1
      && currentIndex < entryList2.size() - 1
                    ? 1
                    : 0); 
  });    
}
}

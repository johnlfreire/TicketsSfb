/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sfb.ticketssfb.controle;

import com.br.sfb.ticketssfb.dao.DAOTicket;
import com.br.sfb.ticketssfb.dao.DAOUsuario;
import com.br.sfb.ticketssfb.enumerator.Atribuicoes;
import com.br.sfb.ticketssfb.modelo.ModeloTicket;
import com.jfoenix.controls.JFXCheckBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author johnpc
 */
public class TicketsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<?> tableTickets;
    @FXML
    private Pane PaneCentralHome;
    @FXML
    private AnchorPane paneCentral;
    Atribuicoes atribuicao;
    String strTabela;
    private ModeloTicket ticket; 
 @FXML
    private Menu atribuir;


    ArrayList<Long> listTicket = new ArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        criarTabela();
        CarregarTabela(strTabela, atribuicao);
        MenuItem menu = new  MenuItem("CRC");
        MenuItem menu2 = new MenuItem("CRC");
        MenuItem menu4 = new MenuItem("CRC");
        MenuItem menu3 = new MenuItem("CRC");
        atribuir.getItems().add(menu);
        atribuir.getItems().add(menu2);
        atribuir.getItems().add(menu3);
        atribuir.getItems().add(menu4);
        
    }

    public void CarregarTabela(String str, Atribuicoes atribuicao) {
        tableTickets.getItems().clear();
        List<ModeloTicket> model2 = null;
        DAOTicket tck = new DAOTicket();
        if (str == "atribuicao") {
            model2 = tck.pesquisarAtribuicao(atribuicao);
        }
        if (str == "usuario") {
            model2 = tck.pesquisarticketUsuario(atribuicao, new DAOUsuario().pesquisarUsuario("nova@nova.com").get(0).getCodigo());
        }
        if (str == "time") {
            model2 = tck.pesquisarticketUsuario(atribuicao, new DAOUsuario().pesquisarUsuario("nova@nova.com").get(0).getCodigo());
        }
        if (str == "rotulo") {
            model2 = tck.pesquisarticketUsuario(atribuicao, new DAOUsuario().pesquisarUsuario("nova@nova.com").get(0).getCodigo());
        }
        ObservableList n;
        n = FXCollections.observableArrayList(model2);
        tableTickets.setItems(FXCollections.observableArrayList(n));
    }

    public void criarTabela() {
       
    
        TableColumn colunaCheck = new TableColumn<>("");
        TableColumn colunaRemetente = new TableColumn<>("REMETENTE");
        TableColumn colunaAssunto = new TableColumn<>("ASSUNTO");
        TableColumn colunaData = new TableColumn<>("DATA ENVIO");
        colunaRemetente.setCellValueFactory(new PropertyValueFactory<>("remetente"));
        colunaAssunto.setCellValueFactory(new PropertyValueFactory<>("assunto"));
        colunaCheck.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaCheck.setCellFactory(new Callback<TableColumn<ModeloTicket, Long>, TableCell<ModeloTicket, Long>>() {
            @Override
            public TableCell<ModeloTicket, Long> call(TableColumn<ModeloTicket, Long> column) {
               final TableCell<ModeloTicket, Long> cell = new TableCell<ModeloTicket, Long>() {
                    
                      final JFXCheckBox btn = new JFXCheckBox();

                    @Override
                    public void updateItem(Long item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                            if(btn.isSelected())
                                listTicket.add(item);
                            else
                                listTicket.remove(item);
                            });
                            setGraphic(btn);
                            setAlignment(Pos.CENTER);
                            setText(null);
                        }
                    }
                };
return cell;
            }
        }
           );
        
        colunaRemetente.setMinWidth(200);
        colunaAssunto.setMinWidth(449);
        colunaData.setMinWidth(98);
        tableTickets.getColumns().addAll(colunaCheck,colunaRemetente, colunaAssunto, colunaData);
        tableTickets.setEditable(true);

    }

    public void responderEmail(MouseEvent event) throws IOException {
       ticket  = (ModeloTicket) tableTickets.getSelectionModel().getSelectedItem();
        if(ticket != null){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene n = stage.getScene();
        System.out.println(n.getRoot());
        BorderPane border = (BorderPane) n.getRoot();
        System.out.println(border.getChildren().get(2));
        AnchorPane pane = (AnchorPane) border.getChildren().get(2);
        System.out.println(pane);
        BorderPane border2 = (BorderPane) pane.getChildren().get(0);
        Pane p = (Pane) border2.getChildren().get(1);
        p.getChildren().clear();
        FXMLGenerico fxml = new FXMLGenerico();
        TicketController ticketcontroller = new TicketController(ticket);
        fxml.showPersonOverview(p, "fxml/Ticket.fxml", ticketcontroller);
        }
//n1.getChildren().clear();

       
    }
    

    @FXML
    void btAtribuir(ActionEvent event) {
        System.out.println("com.br.sfb.ticketssfb.controle.TicketsController.btAtribuir()");
    }
}

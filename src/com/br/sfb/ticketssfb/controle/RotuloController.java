/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sfb.ticketssfb.controle;

/**
 *
 * @author johnpc
 */
 

import com.br.sfb.ticketssfb.dao.DAORotulo;
import com.br.sfb.ticketssfb.modelo.ModeloRotulo;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class RotuloController implements Initializable  {

    private ModeloRotulo rotulo;
    private DAORotulo daoRotulo;
    @FXML
    private JFXTextField tfRotulo;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML
    void btSalvarRotulo(ActionEvent event) {
       rotulo = new ModeloRotulo();
       rotulo.setRotulo(tfRotulo.getText());
       daoRotulo = new DAORotulo();
       daoRotulo.adicionar(rotulo);
       
    }
}

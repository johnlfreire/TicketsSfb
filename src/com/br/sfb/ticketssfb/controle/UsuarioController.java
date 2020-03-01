/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sfb.ticketssfb.controle;

import com.br.sfb.ticketssfb.dao.DAOUsuario;
import com.br.sfb.ticketssfb.modelo.ModeloUsuario;
import com.br.sfb.ticketssfb.util.Servico;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;


/**
 * FXML Controller class
 *
 * @author johnpc
 */
public class UsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
     ModeloUsuario usuario;
     DAOUsuario daoUsuario;
     
     @FXML
    private ImageView ivImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    

   @FXML
    void btAdicionarImagem(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Escolher Foto");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*jpeg"));
        Optional<File> file = Optional.ofNullable(chooser.showOpenDialog(null));
        try {
            if (file.isPresent() && megabytes(file.get()) <= 1) {
                BufferedImage bi = ImageIO.read(file.get());
                Image image = SwingFXUtils.toFXImage(bi, null);
                ivImage.setImage(image);
                ivImage.setPreserveRatio(true);
            } else {

            }

        } catch (IOException e) {

        }
    }
    @FXML
    void btSalvarUsuario(ActionEvent event) {
     usuario = new ModeloUsuario();
     usuario.setNome("");
     usuario.setEmail("");
     usuario.setPassword("");
     usuario.setAvatar(ivImage.imageProperty().isNull().get() ? null : Servico.imageToByte(ivImage.getImage()));
     daoUsuario =  new DAOUsuario();
     daoUsuario.adicionar(usuario);
        
        
    }
    private double megabytes(File file) {
        double bytes = file.length();
        double kilobytes = bytes / 1024;
        return kilobytes / 1024;
    }
}

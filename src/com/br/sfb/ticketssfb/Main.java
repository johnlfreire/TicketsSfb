package com.br.sfb.ticketssfb;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
 //w w w  .  jav  a 2s  .com
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("HTML");
        stage.setWidth(500);
        stage.setHeight(500);
        Scene scene = new Scene(new Group());
    
        VBox root = new VBox();     
 
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        
     
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: white");
        String host = "imap.googlemail.com";
            String port = "993";
            String userName = "";
            String password = "";
            String saveDirectory = System.getProperty("user.dir") + "/Attachment";
            System.out.println(saveDirectory);
        try {
            Files.createDirectories(Paths.get(saveDirectory));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println(saveDirectory);
            EmailAttachmentReceiver receiver = new EmailAttachmentReceiver();
            receiver.setSaveDirectory(saveDirectory);
            
             //scrollPane.setContent(browser);
            
           
       
try{
     ImageView image = new ImageView();
    byte[] im = receiver.downloadEmailAttachments(host, port, userName, password).getBytes();
InputStream is = new ByteArrayInputStream(im);
                BufferedImage bi = ImageIO.read(is);
                
                Image ivImage = SwingFXUtils.toFXImage(bi, null);                
                image.setImage(ivImage);
           scrollPane.setContent(image);
         
 }
 catch(Exception e){
    String erro = e.toString();
}
              
           
             
            webEngine.loadContent(receiver.downloadEmailAttachments(host, port, userName, password));
         
        
        root.getChildren().addAll(scrollPane);
        scene.setRoot(root);
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    private double megabytes(File file) {
        double bytes = file.length();
        double kilobytes = bytes / 1024;
        return kilobytes / 1024;
    }
}
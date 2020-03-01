/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sfb.ticketssfb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johnpc
 */
public class CarregarEmail {

    /**
     * @param args the command line arguments
     */
    public CarregarEmail() {
            EmailAttachmentReceiver1 receiver = new EmailAttachmentReceiver1();
            String userName = "";
            String password = "";
            String saveDirectory = System.getProperty("user.dir") + "/Attachment";
            System.out.println(saveDirectory);
        try {
            Files.createDirectories(Paths.get(saveDirectory));
        } catch (IOException ex) {
            Logger.getLogger(CarregarEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println(saveDirectory);
  
            receiver.setSaveDirectory(saveDirectory);
            receiver.downloadEmailAttachments(userName, password);
    }
    
}

package com.br.sfb.ticketssfb;


import com.sun.mail.imap.IMAPSSLStore;
import com.sun.mail.util.BASE64DecoderStream;
import java.io.BufferedReader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

 
/**
 * This program demonstrates how to download e-mail messages and save
 * attachments into files on disk.
 *
 * @author www.codejava.net
 *
 */
public class EmailAttachmentReceiver {
    private String saveDirectory;
 
    /**
     * Sets the directory where attached files will be stored.
     * @param dir absolute path of the directory
     */
    public void setSaveDirectory(String dir) {
        this.saveDirectory = dir;
    }
 
    /**
     * Downloads new messages and saves attachments to disk if any.
     * @param host
     * @param port
     * @param userName
     * @param password
     */
    public String downloadEmailAttachments(String host, String port,
        String userName, String password) {
        Properties props = new Properties();
        props.setProperty("mail.imaps.host", "imap.gmail.com");
        props.setProperty("mail.imaps.port", "993");
        props.setProperty("mail.imaps.connectiontimeout", "5000");
        props.setProperty("mail.imaps.timeout", "5000");
 
        Session session = Session.getDefaultInstance(props);
      URLName url = new URLName("imap", "imap.googlemail.com", 993, "",
                "", "");
        try {
            // connects to the message store
            Store store = new IMAPSSLStore(session, url);
            store.connect();
 
            // opens the inbox folder
            Folder inbox = store.getFolder("NOVO");
            inbox.open(Folder.READ_ONLY);
 
            // fetches new messages from server
            Message[] arrayMessages = inbox.getMessages();
 
            for (int i = 0; i < arrayMessages.length; i++) {
                Message message = arrayMessages[i];
                Address[] fromAddress = message.getFrom();
                String from = fromAddress[0].toString();
                String subject = message.getSubject();
                String sentDate = message.getSentDate().toString();
                
                String contentType = message.getContentType();
                String messageContent = "";
 
               // Armazena o nome do arquivo anexo, separado por vírgula
                String attachFiles = "";
 
                if (contentType.contains("multipart")) {                    
               // Verifica se o conteúdo contém anexos
                    Multipart multiPart = (Multipart) message.getContent();
                    int numberOfParts = multiPart.getCount();
                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                            // esta parte é anexo
                            String fileName = part.getFileName();
                            attachFiles += fileName + ", ";
                            System.out.println("ID == "+ part.getContentID());
                            part.saveFile(saveDirectory + File.separator + fileName);
                            
                                                        
                            System.out.println("---------------------------------");
                             
                           
                            System.out.println("---------------------------------");
                            BASE64DecoderStream base64DecoderStream = (BASE64DecoderStream) part.getContent();
                            Reader reader = new InputStreamReader(base64DecoderStream,"UTF8");
                            
                            return readContent(reader);
                        } else {
                            // esta parte pode ser o conteúdo da mensagem
                     
                            //messageContent = ;
                        
                            //System.out.println(logMimeMessage(multiPart));
                            
                        }
                    }
 
                    if (attachFiles.length() > 1) {
                        attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
                    }
                } else if (contentType.contains("text/plain")
                        || contentType.contains("text/html")) {
                    Object content = message.getContent();
                    if (content != null) {
                        messageContent = content.toString();
                    }
                }
 
                // print out details of each message
                System.out.println("Message #" + (i + 1) + ":");
                System.out.println("\t From: " + from);
                System.out.println("\t Subject: " + subject);
                System.out.println("\t Sent Date: " + sentDate);
                System.out.println("\t Message: " + messageContent);
                System.out.println("\t Attachments: " + attachFiles);
            }
 
            // disconnect
            inbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for pop3.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
 
    /**
     * Runs this program with Gmail POP3 server
     */
    public static void main(String[] args) {
        try {
            String host = "imap.googlemail.com";
            String port = "993";
            String userName = "";
            String password = "";
            String saveDirectory = System.getProperty("user.dir") + "/Attachment";
            System.out.println(saveDirectory);
            Files.createDirectories(Paths.get(saveDirectory));
            System.out.println(saveDirectory);
            EmailAttachmentReceiver receiver = new EmailAttachmentReceiver();
            receiver.setSaveDirectory(saveDirectory);
            receiver.downloadEmailAttachments(host, port, userName, password);
        } catch (IOException ex) {
            Logger.getLogger(EmailAttachmentReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
      
 public String logMimeMessage(Multipart msg) throws MessagingException, IOException {
    
      MimeMultipart mine = (MimeMultipart) msg;
     ByteArrayOutputStream out = new ByteArrayOutputStream();
     
    try {
        mine.writeTo(out);
         String textoOriginal = out.toString();
      int  sinalInicial = 0;
       int  sinalFinal= 0;
        for (int i = 0; i < textoOriginal.length(); i++) {
        if(textoOriginal.charAt(i) == '<'){
            
            if(sinalInicial==0){
            sinalInicial=i ;
            }
         }if(textoOriginal.charAt(i) == '>'){
            sinalFinal=i;
         }
          }
       return textoOriginal.substring((sinalInicial-1), (sinalFinal+1));
    } catch (IOException e) {
       
    }  
         return out.toString();   
    }
 
 public String logMimeMessage(Multipart msg, MimeBodyPart part) throws MessagingException, IOException {
  Reader reader = null;
      MimeMultipart mine = (MimeMultipart) msg;
     ByteArrayOutputStream out = new ByteArrayOutputStream();
     BodyPart body = mine.getBodyPart(part.getContentID());
     BASE64DecoderStream base64DecoderStream = (BASE64DecoderStream) body.getContent();
     reader = new InputStreamReader(base64DecoderStream,"UTF8");
     
     System.out.println(readContent(reader));  
 return readContent(reader);
 }
 
 
 private  String readContent(Reader reader)
			throws MessagingException {
		BufferedReader br = null;
		StringBuilder sb = null;
		try {
			String line;
			br =  new BufferedReader(reader);
			sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			}
			// delete the last "\r\n"
			sb.delete(sb.length() - 2, sb.length());
			return sb.toString();
		} catch (IOException e) {
			throw new MessagingException("Failed to read data from the source",
					e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
			if (sb != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
	}
     }
 
        
  

 

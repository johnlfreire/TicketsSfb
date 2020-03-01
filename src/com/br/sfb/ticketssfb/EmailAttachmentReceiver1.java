package com.br.sfb.ticketssfb;

import com.br.sfb.ticketssfb.dao.DAOTicket;
import com.br.sfb.ticketssfb.enumerator.Atribuicoes;
import com.br.sfb.ticketssfb.modelo.ModeloTicket;
import com.br.sfb.ticketssfb.util.Servico;
import com.sun.mail.imap.IMAPSSLStore;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

/**
 * This program demonstrates how to download e-mail messages and save
 * attachments into files on disk.
 *
 * @author www.codejava.net
 *
 */
public class EmailAttachmentReceiver1 {

    private String saveDirectory;
    private DAOTicket ticketDao;
    private ModeloTicket ticketModel;

    /**
     * Sets the directory where attached files will be stored.
     *
     * @param dir absolute path of the directory
     */
    public void setSaveDirectory(String dir) {
        this.saveDirectory = dir;
    }

    /**
     * Downloads new messages and saves attachments to disk if any.
     *
     * @param userName
     * @param password
     */
    public void downloadEmailAttachments(String userName, String password) {

        Properties props = new Properties();
        props.setProperty("mail.imaps.host", "imap.gmail.com");
        props.setProperty("mail.imaps.port", "993");
        props.setProperty("mail.imaps.connectiontimeout", "5000");
        props.setProperty("mail.imaps.timeout", "5000");
        Session session = Session.getDefaultInstance(props);
        URLName url = new URLName("imap", "imap.googlemail.com", 993, "", "", "");
        try (Store store = new IMAPSSLStore(session, url)) {
            // connects to the message store
            store.connect();

            // opens the inbox folder
            Folder inbox = store.getFolder("NOVO");
            inbox.open(Folder.READ_WRITE);

            // fetches new messages from server
            //Message[] arrayMessages = inbox.getMessages();
            Message[] arrayMessages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            for (Message message : arrayMessages) {
                Address[] fromAddress = message.getFrom();
                String from = fromAddress[0].toString();
                String subject = message.getSubject();
                String sentDate = message.getSentDate().toString();
                String contentType = message.getContentType();
                String messageContent = "";
                Address[] recipients = message.getAllRecipients();
                String destinatario = recipients[0].toString();
                // Armazena o nome do arquivo anexo, separado por vírgula
                String attachFiles = "";
                
                if (contentType.contains("multipart")) {
                    // Verifica se o conteúdo contém anexos
                    Multipart multiPart = (Multipart) message.getContent();
                    int numberOfParts = multiPart.getCount();
                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
                        ticketModel = new ModeloTicket();
                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                            // esta parte é anexo
                            String fileName = part.getFileName();
                            attachFiles += fileName + ", ";
                            part.saveFile(saveDirectory + File.separator + fileName);
                        } else {
                            // esta parte pode ser o conteúdo da mensagem
                            messageContent = part.getContent().toString();
                            if (messageContent.contains("javax.mail.internet.MimeMultipart")) {
                                messageContent = Servico.logMimeMessage(multiPart);
                            }
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
               // System.out.println("Message #" + (i + 1) + ":");
                System.out.println("\t From: " + from);
                ticketModel.setRemetente(from);
                System.out.println("\t Subject: " + subject);
                ticketModel.setAssunto(subject);
                  System.out.println("\t Sent Date: " + sentDate);
                ticketModel.setDestinatarios(destinatario);
                 System.out.println("\t Message: " + messageContent);
                ticketModel.setAtribuicao(Atribuicoes.RECEBIDO);
                 System.out.println("\t Attachments: " + attachFiles);               
                ticketModel.setConteudo(Servico.removeEmojiAndSymbolFromString(messageContent));
                ticketDao = new DAOTicket();
                ticketDao.adicionar(ticketModel);
                message.setFlag(Flags.Flag.SEEN, true);
                // Date data = new Date(sentDate);
                //ticketModel.setDataDeEnvio();
            }  
            
            inbox.close(false);
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");           
        } catch (IOException ex) {
            System.out.println("Could not connect to the message store");
        }

    }
   

    
}

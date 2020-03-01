/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sfb.ticketssfb.util;


import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMultipart;
import javax.validation.constraints.NotNull;
/**
 *
 * @author johnpc
 */
public abstract  class Servico {
   
    
    
    public static Image byteToImage(byte[] img) throws IOException {
        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(img));
        return SwingFXUtils.toFXImage(bi, null);
    }

    /**
     * Converte uma imagem em um array de byte
     *
     * @param image
     * @return byte[]
     */
    public static byte[] imageToByte(Image image) {
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream s = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, "jpg", s);
        } catch (IOException ex) {
            
        }
        return s.toByteArray();
    }
   
    public static void maxField(final TextField textField, final Integer length) {
        
        try{
        textField.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
            if (newValue.length() > length) {
                textField.setText(oldValue);
            }
        });
        }catch (IllegalArgumentException e) {
                    e.printStackTrace();
          }catch (Exception e) {
                    System.out.println("com.br.sfbLoja.util.Servico.maxField()");
          }
    }
    public static void onlyNumber(@NotNull final TextField text) {
        text.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent t) -> {
            if (t.getCharacter().matches("[a-zA-Z\\s,.]+$")) {
                text.setStyle("-fx-focus-color: #FF0012;");
                t.consume();
            } else {
                text.setStyle(null);
            }
        });
        text.setStyle(null);
    }
     
     public static String removeEmojiAndSymbolFromString(String content) {
        String utf8tweet = "";
        try {
            byte[] utf8Bytes = content.getBytes(
                    "UTF-8");

            utf8tweet = new String(
                    utf8Bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Pattern unicodeOutliers
                = Pattern.compile(
                        "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                        Pattern.UNICODE_CASE
                        | Pattern.CANON_EQ
                        | Pattern.CASE_INSENSITIVE
                );
        Matcher unicodeOutlierMatcher
                = unicodeOutliers.matcher(
                        utf8tweet);

        utf8tweet
                = unicodeOutlierMatcher.replaceAll(
                        " ");
        return utf8tweet;
    } 
     
      public static String logMimeMessage(Multipart msg) throws MessagingException, IOException {

        MimeMultipart mine = (MimeMultipart) msg;
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            mine.writeTo(out);
            String textoOriginal = out.toString();
            int sinalInicial = 0;
            int sinalFinal = 0;
            for (int i = 0; i < textoOriginal.length(); i++) {
                if (textoOriginal.charAt(i) == '<') {

                    if (sinalInicial == 0) {
                        sinalInicial = i;
                    }
                }
                if (textoOriginal.charAt(i) == '>') {
                    sinalFinal = i;
                }
            }
            return textoOriginal.substring((sinalInicial - 1), (sinalFinal + 1));
        } catch (IOException e) {

        }
        return out.toString();
    }
}

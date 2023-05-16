package com.example.redes;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class UtilidadeEmail {

    /**
     * Utility method to send simple HTML email
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    public static void enviarEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //definir cabeçalhos de mensagem
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "Lucas"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "Redes");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("A mensagem está pronta");
            Transport.send(msg);

            System.out.println("E-mail enviado com sucesso!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void enviarEmailAnexo(Session session, String toEmail, String subject, String body){
        try{
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "Lucas"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            // Criar a parte do corpo da mensagem
            BodyPart messageBodyPart = new MimeBodyPart();

            // Preencher a mensagem
            messageBodyPart.setText(body);

            // Criar uma mensagem de várias partes para anexo
            Multipart multipart = new MimeMultipart();

            // Definir parte da mensagem de texto
            multipart.addBodyPart(messageBodyPart);

            // A segunda parte é o anexo
            messageBodyPart = new MimeBodyPart();
            String filename = "arquivo.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Envie as partes completas da mensagem
            msg.setContent(multipart);

            // Enviar mensagem
            Transport.send(msg);
            System.out.println("E-mail com anexo enviado com sucesso!!");
        }catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void enviarEmailImagem(Session session, String toEmail, String subject, String body){
        try{
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "Lucas"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            // Criar a parte do corpo da mensagem
            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setText(body);

            // Criar uma mensagem de várias partes para anexo
            Multipart multipart = new MimeMultipart();

            // Definir parte da mensagem de texto
            multipart.addBodyPart(messageBodyPart);

            // A segunda parte é o anexo da imagem
            messageBodyPart = new MimeBodyPart();
            String filename = "amandaelucas.png";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            //O truque é adicionar o cabeçalho content-id aqui
            messageBodyPart.setHeader("Content-ID", "image_id");
            multipart.addBodyPart(messageBodyPart);

            //terceira parte para exibir a imagem no corpo do e-mail
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("<h1>Imagem</h1>" +
                    "<img src='cid:image_id'>", "text/html");
            multipart.addBodyPart(messageBodyPart);

            //Definir a mensagem de várias partes para a mensagem de e-mail
            msg.setContent(multipart);

            // Enviar mensagem
            Transport.send(msg);
            System.out.println("E-mail com imagem enviado com sucesso!!");
        }catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}

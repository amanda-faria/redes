package com.example.redes;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class EnviarEmail {

    /**
     Outgoing Mail (SMTP) Server
     requires TLS or SSL: smtp.gmail.com (use authentication)
     Use Authentication: Yes
     Port for SSL: 465
     */
    public static void main(String[] args) {
        final String fromEmail = "lucasalves0495@gmail.com"; //Inserir aqui o seu e-mail gmail.
        final String password = "lphinbiznhexqmgr"; // Inserir aqui não a sua senha do e-mail mas a senha do App que o google gera.
        final String toEmail = "lucasfaria.redes@gmail.com"; // E-mail destinatário.

        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        Authenticator auth = new Authenticator() {
            //substituir o método getPasswordAuthentication
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Session created");

//        Cada um desses métodos representa um e-mail, então, se você tirar o comentário dos três, serão enviados três e-mails,
//        um simples com texto, outro com um arquivo txt junto e outro com uma imagem png.

        UtilidadeEmail.enviarEmail(session, toEmail,"SSLEmail Testing Subject", "Teste de corpo do e-mail");

        UtilidadeEmail.enviarEmailAnexo(session, toEmail,"SSLEmail Testing Subject with Attachment", "SSLEmail Testing Body with Attachment");

        UtilidadeEmail.enviarEmailImagem(session, toEmail,"SSLEmail Testing Subject with Image", "SSLEmail Testing Body with Image");

    }

}

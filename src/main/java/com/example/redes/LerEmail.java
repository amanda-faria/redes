package com.example.redes;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import java.util.Properties;

public class LerEmail {
    public static final String USERNAME = "lucasfaria.redes@gmail.com"; //Inserir aqui o seu e-mail gmail.
    public static final String PASSWORD = "qfcabyhikbfjcrgm"; // Inserir aqui não a sua senha do e-mail mas a senha do App que o google gera.

    public static void main(String[] args) throws Exception {
        // 1. Propriedades de configuração para a sessão de correio.
        Properties props = new Properties();
        props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.pop3.socketFactory.fallback", "false");
        props.put("mail.pop3.socketFactory.port", "995");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.host", "pop.gmail.com");
        props.put("mail.pop3.user", LerEmail.USERNAME);
        props.put("mail.store.protocol", "pop3");
        props.put("mail.pop3.ssl.protocols", "TLSv1.2");

        // 2. Cria um objeto  javax.mail.Authenticator object.
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(LerEmail.USERNAME, LerEmail.PASSWORD);
            }
        };

        // 3. Criando sessão de email.
        Session session = Session.getDefaultInstance(props, auth);

        // 4. Obtenha o provedor da loja POP3 e conecte-se à loja.
        Store store = session.getStore("pop3");
        store.connect("pop.gmail.com", LerEmail.USERNAME, LerEmail.PASSWORD);

        // 5. Obtenha a pasta e abra a pasta INBOX na loja.
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        // 6. Recupere as mensagens da pasta.
        Message[] messages = inbox.getMessages();
        for (Message message : messages) {
            System.out.println("De: " + message.getFrom()[0].toString());
            System.out.println("Recebido: " + message.getSentDate());
            System.out.println("Assunto: " + message.getSubject());
//            message.writeTo(System.out);

            String contentType = message.getContentType();
            StringBuilder messageContent= new StringBuilder();

            if (contentType.contains("multipart")) {
                Multipart multiPart = (Multipart) message.getContent();
                int numberOfParts = multiPart.getCount();
                for (int partCount = 0; partCount < numberOfParts; partCount++) {
                    MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                    String partContentType = part.getContentType();
                    if (partContentType.contains("text/plain")) {
                        messageContent = new StringBuilder(part.getContent().toString());
                        break;
                    } else if (partContentType.contains("text/html")) {
                        String html = part.getContent().toString();
                        messageContent = new StringBuilder(Jsoup.parse(html).text());
                    }
                }
            } else if (contentType.contains("text/plain")) {
                Object content = message.getContent();
                if (content != null) {
                    messageContent = new StringBuilder(content.toString());
                }
            } else if (contentType.contains("text/html")) {
                String html = message.getContent().toString();
                messageContent = new StringBuilder(Jsoup.parse(html).text());
            }

            System.out.println("Mensagem: " + messageContent);
        }

        // 7. Fechar pasta
        inbox.close(false);
        store.close();
    }
}

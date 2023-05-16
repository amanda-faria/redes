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

        System.out.println("Iniciando SSLEmail");
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
        System.out.println("A sessão foi criada");

//        Cada um desses métodos representa um e-mail, então, se você tirar o comentário dos três, serão enviados três e-mails,
//        um simples com texto, outro com um arquivo txt junto e outro com uma imagem png.

        UtilidadeEmail.enviarEmail(session, toEmail,"Teste - Enviar um e-mail SSLEmail", "Teste de corpo do e-mail, 1914 translation by H. Rackham\n" +
                "\"But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?\"");

        UtilidadeEmail.enviarEmailAnexo(session, toEmail,"Teste - Enviar um conteúdo de arquivo SSLEmail", "SSLEmail Teste - Enviiar um conteúdo de arquivo");

        UtilidadeEmail.enviarEmailImagem(session, toEmail,"Teste - Enviar uma imagem SSLEmail", "SSLEmail Teste - Enviar uma imagem");

    }

}

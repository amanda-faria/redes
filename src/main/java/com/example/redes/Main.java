package com.example.redes;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class Main extends JFrame {

    private JButton enviarEMailButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JCheckBox enviarImagemCheckBox;
    private JCheckBox enviarArquivoCheckBox;
    private JLabel Cc;
    private JLabel Assunto;
    private JLabel Mensagem;
    private JPanel MainPanel;
    final String password = "lphinbiznhexqmgr";


    public Main() {

        setContentPane(MainPanel);
        setTitle("Simple Email APP");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        System.out.println("Iniciando SSLEmail");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        System.out.println("SSLEmail iniciado");

        enviarEMailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Authenticator auth = new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(textField2.getText(), password);
                    }
                };

                Session session = Session.getDefaultInstance(props, auth);
                System.out.println("A sessão foi criada");

                if (enviarImagemCheckBox.isSelected() == true) {
                    UtilidadeEmail.enviarEmailImagem(session, textField1.getText(), textField4.getText(), textField5.getText());
                }

                if (enviarArquivoCheckBox.isSelected() == true) {
                    UtilidadeEmail.enviarEmailAnexo(session, textField1.getText(), textField4.getText(), textField5.getText());
                }

                if (enviarImagemCheckBox.isSelected() == false && enviarArquivoCheckBox.isSelected() == false) {
                    UtilidadeEmail.enviarEmail(session, textField1.getText(), textField4.getText(), textField5.getText());
                }
            }
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}

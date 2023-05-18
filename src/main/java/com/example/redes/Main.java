package com.example.redes;

import javax.swing.*;

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

//    JTextField emailDestinatario = new JTextField();

    public Main () {
//        add(emailDestinatario);
//        emailDestinatario.setText("lucasalves0495@gmail.com");
        setContentPane(MainPanel);
        setTitle("Simple Email APP");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}

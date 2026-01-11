package view;

import javax.swing.*;

import ui.ButtonPdr;

public class EntradaEmpresa extends JFrame {

    public EntradaEmpresa() {
        //tela de login - entrada cnpj e senha - entrada pra p/n login

        setTitle("Empresa");
        //setVisible(true);
        setSize( 800, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); //centralizar

        JLabel label = new JLabel("Bem-vindo de volta");
        add(label);

        ButtonPdr entrar = new ButtonPdr("Entrar");
    }
}

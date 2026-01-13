package view;

import javax.swing.*;
import java.awt.*;

import model.Cliente;
import ui.ButtonPdr;
import ui.EPLabel;
import ui.Gradient;

public class MenuCliente extends JFrame {

    private Cliente cliente;

    public MenuCliente(Cliente cliente) {
        this.cliente = cliente;

        setTitle("Área do Cliente");
        setSize(800, 520);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Gradient fundo = new Gradient(
                new Color(0xFF, 0xE5, 0xA5),
                new Color(0xFF, 0xE5, 0xA5)
        );
        setContentPane(fundo);
        fundo.setLayout(new GridBagLayout());

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);
        painel.setPreferredSize(new Dimension(300, 300));
        fundo.add(painel);

        EPLabel titulo = EPLabel.titulo("Bem-vindo, " + cliente.getNome());
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);

        painel.add(Box.createVerticalStrut(40));

        // 🔹 BOTÃO RESERVAR
        ButtonPdr btnReservar = new ButtonPdr("Reservar Vaga");
        btnReservar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(btnReservar);

        painel.add(Box.createVerticalStrut(20));

        // 🔹 BOTÃO CANCELAR
        ButtonPdr btnCancelar = new ButtonPdr("Cancelar Reserva");
        btnCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(btnCancelar);

        painel.add(Box.createVerticalStrut(20));

        // 🔹 BOTÃO SAIR
        ButtonPdr btnSair = new ButtonPdr("Sair");
        btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(btnSair);

        // 🔹 EVENTOS
        btnReservar.addActionListener(e -> {
            TelaReservarVaga tela = new TelaReservarVaga(cliente);
            tela.setVisible(true);
        });

        btnCancelar.addActionListener(e -> {
            TelaCancelarReserva tela = new TelaCancelarReserva(cliente);
            tela.setVisible(true);
        });


        btnSair.addActionListener(e -> dispose());
    }
}

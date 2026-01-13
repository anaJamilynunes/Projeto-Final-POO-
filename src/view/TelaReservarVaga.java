package view;

import javax.swing.*;
import java.awt.*;

import model.Cliente;
import ui.ButtonPdr;
import ui.EPLabel;
import ui.Gradient;

public class TelaReservarVaga extends JFrame {

    private Cliente cliente;

    public TelaReservarVaga(Cliente cliente) {
        this.cliente = cliente;

        setTitle("Reservar Vaga");
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
        painel.setPreferredSize(new Dimension(350, 300));
        fundo.add(painel);

        EPLabel titulo = EPLabel.titulo("Reservar Vaga");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);

        painel.add(Box.createVerticalStrut(30));

        EPLabel info = EPLabel.pequeno(
                "Aqui serão listadas as vagas disponíveis"
        );
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(info);

        painel.add(Box.createVerticalStrut(40));

        ButtonPdr btnConfirmar = new ButtonPdr("Confirmar Reserva");
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(btnConfirmar);

        ButtonPdr btnVoltar = new ButtonPdr("Voltar");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(Box.createVerticalStrut(15));
        painel.add(btnVoltar);

        btnConfirmar.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Reserva em breve 😄"
                )
        );

        btnVoltar.addActionListener(e -> dispose());
    }
}

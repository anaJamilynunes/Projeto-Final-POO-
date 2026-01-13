package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import model.Cliente;
import model.Reserva;
import ui.ButtonPdr;
import ui.EPLabel;
import ui.Gradient;

public class TelaCancelarReserva extends JFrame {

    private Cliente cliente;
    private JList<Reserva> listaReservas;
    private DefaultListModel<Reserva> modelLista;

    public TelaCancelarReserva(Cliente cliente) {
        this.cliente = cliente;

        setTitle("Cancelar Reserva");
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
        painel.setPreferredSize(new Dimension(400, 350));
        fundo.add(painel);

        EPLabel titulo = EPLabel.titulo("Suas Reservas");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);

        painel.add(Box.createVerticalStrut(20));

        modelLista = new DefaultListModel<>();
        listaReservas = new JList<>(modelLista);
        listaReservas.setVisibleRowCount(5);

        JScrollPane scroll = new JScrollPane(listaReservas);
        scroll.setPreferredSize(new Dimension(350, 150));
        painel.add(scroll);

        painel.add(Box.createVerticalStrut(20));

        ButtonPdr btnCancelar = new ButtonPdr("Cancelar Reserva");
        btnCancelar.addActionListener(e -> {
            TelaCancelarReserva tela = new TelaCancelarReserva(cliente);
            tela.setVisible(true);
        });


        ButtonPdr btnVoltar = new ButtonPdr("Voltar");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(Box.createVerticalStrut(15));
        painel.add(btnVoltar);

        carregarReservas();

        btnCancelar.addActionListener(e -> cancelarReserva());
        btnVoltar.addActionListener(e -> dispose());
    }

    private void carregarReservas() {
        modelLista.clear();
        List<Reserva> reservas = cliente.getReservas();

        if (reservas.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Você não possui reservas.",
                    "Aviso",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        for (Reserva r : reservas) {
            modelLista.addElement(r);
        }
    }

    private void cancelarReserva() {
        Reserva reservaSelecionada = listaReservas.getSelectedValue();

        if (reservaSelecionada == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma reserva.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        reservaSelecionada.cancelar();
        cliente.liberarVaga(reservaSelecionada);
        modelLista.removeElement(reservaSelecionada);

        JOptionPane.showMessageDialog(
                this,
                "Reserva cancelada com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}

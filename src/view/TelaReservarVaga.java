package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import model.SistemaEstacionamento;
import model.Empresa;
import model.Vaga;
import ui.ButtonPdr;
import ui.EPLabel;
import ui.Gradient;

public class TelaReservarVaga extends JFrame {

    private SistemaEstacionamento sistema;
    private JList<Vaga> listaVagas;
    private DefaultListModel<Vaga> modelLista;

    public TelaReservarVaga(SistemaEstacionamento sistema) {
        this.sistema = sistema;

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
        painel.setPreferredSize(new Dimension(400, 350));
        fundo.add(painel);

        EPLabel titulo = EPLabel.titulo("Vagas Disponíveis");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);

        painel.add(Box.createVerticalStrut(20));

        modelLista = new DefaultListModel<>();
        listaVagas = new JList<>(modelLista);
        listaVagas.setVisibleRowCount(6);

        JScrollPane scroll = new JScrollPane(listaVagas);
        scroll.setPreferredSize(new Dimension(350, 150));
        painel.add(scroll);

        painel.add(Box.createVerticalStrut(20));

        ButtonPdr btnReservar = new ButtonPdr("Reservar Vaga");
        btnReservar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(btnReservar);

        ButtonPdr btnCancelar = new ButtonPdr("Cancelar Reserva");
        btnCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(btnCancelar);

        painel.add(Box.createVerticalStrut(10));

        ButtonPdr btnVoltar = new ButtonPdr("Voltar");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(btnVoltar);

        carregarVagas();

        // EVENTOS
        btnReservar.addActionListener(e -> reservar());

        btnCancelar.addActionListener(e -> cancelarReserva());

        btnVoltar.addActionListener(e -> {
            new IndexView(sistema).setVisible(true);
            dispose();
        });

        setVisible(true);
    }

    // CARREGA SOMENTE VAGAS DISPONÍVEIS
    private void carregarVagas() {
        modelLista.clear();

        List<Vaga> vagasDisponiveis = new ArrayList<>();

        for (Empresa empresa : sistema.getEmpresas()) {
            for (Vaga vaga : empresa.getVagas()) {
                if (vaga.vagaDisponivel()) {
                    vagasDisponiveis.add(vaga);
                }
            }
        }

        if (vagasDisponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Não há vagas disponíveis no momento.",
                    "Aviso",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        for (Vaga vaga : vagasDisponiveis) {
            modelLista.addElement(vaga);
        }
    }

    //RESERVA UMA VAGA
    private void reservar() {
        if (sistema.getVagaReservada() != null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Você já possui uma reserva.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Vaga vagaSelecionada = listaVagas.getSelectedValue();

        if (vagaSelecionada == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma vaga.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        sistema.reservarVaga(vagaSelecionada);
        new CadastroCliente(sistema, vagaSelecionada);
        dispose();
    }

    //CANCELA RESERVA
    private void cancelarReserva() {
        if (sistema.getVagaReservada() == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Você não tem reserva.",
                    "Aviso",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        sistema.cancelarReserva();

        JOptionPane.showMessageDialog(
                this,
                "Reserva cancelada com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        carregarVagas();
    }
}

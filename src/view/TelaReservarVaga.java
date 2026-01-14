package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import model.SistemaEstacionamento;
import model.Cliente;
import model.Empresa;
import model.Vaga;
import ui.ButtonPdr;
import ui.EPLabel;
import ui.Gradient;

public class TelaReservarVaga extends JFrame {

    private SistemaEstacionamento sistema;
    private Cliente cliente;
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

    //RESErva
    private void reservar() {
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

    // Solicitar horário
    String horarioStr = JOptionPane.showInputDialog(
            this,
            "Informe o horário da reserva (HH:mm):"
    );

    if (horarioStr == null || horarioStr.isEmpty()) return;

    java.time.LocalTime horario;
    try {
        horario = java.time.LocalTime.parse(horarioStr);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Horário inválido! Use HH:mm");
        return;
    }
    new CadastroCliente(sistema, vagaSelecionada, horario);

    // opcional: fecha a tela atual
    dispose();

    // Confirmar reserva no sistema
     try {
        sistema.confirmarReserva(cliente, vagaSelecionada, horario);
        JOptionPane.showMessageDialog(this, "Reserva realizada com sucesso!");

        // Atualiza lista de vagas
        carregarVagas();

        // Fecha a tela ou mantém aberta para reservar mais
        // dispose(); 
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao reservar: " + e.getMessage());
    }
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

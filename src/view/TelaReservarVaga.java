package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
            new Color(253,210,120),
            new Color(169,113,66) 
        );
        setContentPane(fundo);
        fundo.setLayout(new BorderLayout());

        //btn de voltar
        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelTopo.setOpaque(false);
        painelTopo.setBorder(new EmptyBorder(10, 10, 0, 0));
        
        ImageIcon img1 = new ImageIcon("src/img/btnvoltar.png"); 
        Image imgIcon = img1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  
        JLabel btnVoltar = new JLabel(new ImageIcon(imgIcon)); 
        btnVoltar.setAlignmentX(Component.RIGHT_ALIGNMENT);
     
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVoltar.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 0)); 
        
        btnVoltar.addMouseListener(new java.awt.event.MouseAdapter() { 
             @Override 
            public void mouseClicked(java.awt.event.MouseEvent e) {
            //SistemaEstacionamento sistema = ArquivoUtil.carregarSistema();
            new IndexView(sistema).setVisible(true);
            dispose();
            }
        }); 
        painelTopo.add(btnVoltar);
        fundo.add(painelTopo, BorderLayout.NORTH);
        //

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

        //comentando para se adequal ao tamanho da tela
        // JScrollPane scroll = new JScrollPane(listaVagas);
        // scroll.setPreferredSize(new Dimension(350, 150));
        // painel.add(scroll);

        JScrollPane scroll = new JScrollPane(listaVagas);

        Dimension size = new Dimension(350, 150);
        scroll.setPreferredSize(size);
        scroll.setMaximumSize(size);   // impede BoxLayout de esticar
        scroll.setMinimumSize(size);   // impede de encolher

        painel.add(scroll);


        painel.add(Box.createVerticalStrut(20));

        ButtonPdr btnReservar = new ButtonPdr("Reservar Vaga");
        btnReservar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(btnReservar);

        painel.add(Box.createVerticalStrut(10));

        ButtonPdr btnCancelar = new ButtonPdr("Cancelar Reserva");
        btnCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(btnCancelar);

        painel.add(Box.createVerticalStrut(10));

        painel.add(Box.createVerticalStrut(10));

        // ButtonPdr btnVoltar = new ButtonPdr("Voltar");
        // btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        // painel.add(btnVoltar);

        carregarVagas();

        // EVENTOS
        btnReservar.addActionListener(e -> reservar());

        btnCancelar.addActionListener(e -> cancelarReserva());

        // btnVoltar.addActionListener(e -> {
        //     new IndexView(sistema).setVisible(true);
        //     dispose();
        // });

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
//      try {
//         sistema.confirmarReserva(cliente, vagaSelecionada, horario);
//         JOptionPane.showMessageDialog(this, "Reserva realizada com sucesso!");

//         // Atualiza lista de vagas
//         carregarVagas();

//         // Fecha a tela ou mantém aberta para reservar mais
//         // dispose(); 
//  } catch (Exception e) {
//     //     JOptionPane.showMessageDialog(this, "Erro ao reservar: " + e.getMessage());
//      }
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

package view;

import model.Empresa;
import model.Reserva;
import model.SistemaEstacionamento;

import ui.Gradient;
import ui.EPLabel;
import ui.ButtonPdr;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class TelaRelatorioEmpresa extends JFrame {

    private Empresa empresa;
    private SistemaEstacionamento sistema;
    private JTable tabela;
    private EPLabel lblResumoVagas;


    public TelaRelatorioEmpresa(SistemaEstacionamento sistema, Empresa empresa) {
        this.sistema = sistema;
        this.empresa = empresa;

        setTitle("Relatório de Reservas - " + empresa.getNome());
        setSize(800, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponents();
        carregarHistorico();

        setVisible(true);
    }

    private void initComponents() {

        Gradient fundo = new Gradient(
                new Color(253, 210, 120),
                new Color(169, 113, 66)
        );
        setContentPane(fundo);
        fundo.setLayout(new BorderLayout());

        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelTopo.setOpaque(false);
        painelTopo.setBorder(new EmptyBorder(10, 10, 0, 0));

        ImageIcon img = new ImageIcon("src/img/btnvoltar.png");
        Image icon = img.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel btnVoltar = new JLabel(new ImageIcon(icon));
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new TelaEmpresaNintendo(sistema, empresa);
                dispose();
            }
        });

        painelTopo.add(btnVoltar);
        fundo.add(painelTopo, BorderLayout.NORTH);

        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.Y_AXIS));
        painelCentro.setOpaque(false);
        painelCentro.setBorder(new EmptyBorder(10, 40, 10, 40));
        fundo.add(painelCentro, BorderLayout.CENTER);

        EPLabel titulo = EPLabel.titulo("Histórico de Reservas");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCentro.add(titulo);

        painelCentro.add(Box.createVerticalStrut(15));

        lblResumoVagas = new EPLabel("");
        lblResumoVagas.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCentro.add(lblResumoVagas);


        painelCentro.add(Box.createVerticalStrut(10));


        tabela = new JTable();
        tabela.setRowHeight(25);
        tabela.setEnabled(false);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setPreferredSize(new Dimension(700, 280));
        painelCentro.add(scroll);

        painelCentro.add(Box.createVerticalStrut(15));

    }

    private void carregarHistorico() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Cliente", "Placa", "Horário", "Status"}, 0
        );

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");

        for (Reserva r : sistema.getReservas()) {
            if (!r.getEmpresa().equals(empresa)) continue;

            model.addRow(new Object[]{
                    r.getCliente().getNome(),
                    r.getCliente().getPlaca(),
                    r.getHorario().format(fmt),
                    r.estadoReserva() ? "Ativa" : "Liberada"
            });
        }

        int totalVagas = empresa.getVagas().size();
        int disponiveis = 0;

        for (var v : empresa.getVagas()) {
            if (v.vagaDisponivel()) {
                disponiveis++;
            }
        }

        lblResumoVagas.setText(
                "Total de vagas: " + totalVagas + " | Disponíveis: " + disponiveis
        );

        tabela.setModel(model);
    }
}

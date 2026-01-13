package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Empresa;
import model.Vaga;
import model.SistemaEstacionamento;
import ui.ButtonPdr;

public class TelaEmpresaNintendo extends JFrame {

    private Empresa empresa;
    private SistemaEstacionamento sistema;
    private JTable tabelaVagas;
    private DefaultTableModel modeloTabela;
    private JLabel lblResumo;

    public TelaEmpresaNintendo(SistemaEstacionamento sistema, Empresa empresa) {
        this.empresa = empresa;
        this.sistema = sistema;

        setTitle("Painel da Empresa");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY);
        setContentPane(mainPanel);

        // Título
        JLabel titulo = new JLabel("Painel da Empresa", SwingConstants.CENTER);
        titulo.setFont(new Font("Monospaced", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(titulo, BorderLayout.NORTH);

        // Resumo no topo
        lblResumo = new JLabel("", SwingConstants.CENTER);
        lblResumo.setFont(new Font("Monospaced", Font.BOLD, 16));
        lblResumo.setForeground(Color.WHITE);
        mainPanel.add(lblResumo, BorderLayout.NORTH);

        // Tabela de vagas
        String[] colunas = {"Número", "Status", "Ocupante"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaVagas = new JTable(modeloTabela);
        tabelaVagas.setFont(new Font("Monospaced", Font.PLAIN, 14));
        tabelaVagas.setRowHeight(25);
        JScrollPane scroll = new JScrollPane(tabelaVagas);
        mainPanel.add(scroll, BorderLayout.CENTER);

        // Rodapé com botões
        JPanel footer = new JPanel();
        footer.setBackground(Color.DARK_GRAY);

        ButtonPdr btnAdicionar = new ButtonPdr("Adicionar Vaga");
        btnAdicionar.addActionListener(e -> adicionarVaga());

        ButtonPdr btnEditarEmpresa = new ButtonPdr("Editar Dados Empresa");
        btnEditarEmpresa.addActionListener(e -> editarEmpresa());

        ButtonPdr btnRelatorio = new ButtonPdr("Gerar Relatório");
        btnRelatorio.addActionListener(e -> gerarRelatorio());

        footer.add(btnAdicionar);
        footer.add(btnEditarEmpresa);
        footer.add(btnRelatorio);
        mainPanel.add(footer, BorderLayout.SOUTH);

        atualizarTabela();
        setVisible(true);
    }

    // ------------------ MÉTODOS ------------------

    private void adicionarVaga() {
        int numero = empresa.getVagas().size() + 1;
        Vaga vaga = new Vaga(numero, empresa);
        empresa.adicionarVaga(vaga);
        salvarSistema();
        atualizarTabela();
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        int ocupadas = 0;
        for (Vaga v : empresa.getVagas()) {
            String status = v.vagaDisponivel() ? "Livre" : "Ocupada";
            String ocupante = v.vagaDisponivel() ? "-" : "Cliente X"; // associar cliente real
            if (!v.vagaDisponivel()) ocupadas++;
            modeloTabela.addRow(new Object[]{v.getNumero(), status, ocupante});
        }

        int total = empresa.getVagas().size();
        int livres = total - ocupadas;
        lblResumo.setText("Total: " + total + " | Ocupadas: " + ocupadas + " | Livres: " + livres +
                " | Horário: 08:00 - 18:00 | Dia: Segunda à Sexta");
    }

    private void editarEmpresa() {
        JOptionPane.showMessageDialog(this, "Função de editar dados da empresa ainda não implementada!");
    }

    private void gerarRelatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("Relatório da Empresa:\n\n");
        for (Vaga v : empresa.getVagas()) {
            sb.append("Vaga ").append(v.getNumero())
                    .append(" - ").append(v.vagaDisponivel() ? "Livre" : "Ocupada")
                    .append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Relatório", JOptionPane.INFORMATION_MESSAGE);
    }

    private void salvarSistema() {
        System.out.println("Sistema salvo!");
        // Chamar método real de salvar sistema
    }
}

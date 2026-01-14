package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import model.Cliente;
import model.SistemaEstacionamento;
import model.Vaga;
import model.ArquivoUtil;

import ui.ButtonPdr;
import ui.EPLabel;
import ui.Gradient;
import ui.TextField;

public class CadastroCliente extends JFrame {

    private SistemaEstacionamento sistema;
    private Vaga vaga;

    public CadastroCliente(SistemaEstacionamento sistema, Vaga vaga, java.time.LocalTime horario) {
        this.sistema = sistema;
        this.vaga = vaga;

        setTitle("Cadastro do Cliente");
        setSize(800, 520);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Gradient fundo = new Gradient(
            new Color(253, 210, 120),
            new Color(169, 113, 66)
        );
        setContentPane(fundo);
        fundo.setLayout(new BorderLayout());

        /* btn voltar*/
        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelTopo.setOpaque(false);
        painelTopo.setBorder(new EmptyBorder(10, 10, 0, 0));

        ImageIcon img1 = new ImageIcon("src/img/btnvoltar.png");
        Image imgIcon = img1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel btnVoltar = new JLabel(new ImageIcon(imgIcon));

        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new TelaReservarVaga(sistema).setVisible(true);
                dispose();
            }
        });

        painelTopo.add(btnVoltar);
        fundo.add(painelTopo, BorderLayout.NORTH);

        /* painel centraç */
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);
        fundo.add(painel, BorderLayout.CENTER);

        EPLabel titulo = EPLabel.titulo("Cadastro do Cliente");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);
        painel.add(Box.createVerticalStrut(20));

        adicionarCampo(painel, "Nome", new TextField());
        TextField tfNome = (TextField) painel.getComponent(painel.getComponentCount() - 1);

        painel.add(Box.createVerticalStrut(10));

        adicionarCampo(painel, "CPF", new TextField());
        TextField tfCpf = (TextField) painel.getComponent(painel.getComponentCount() - 1);

        painel.add(Box.createVerticalStrut(10));

        adicionarCampo(painel, "Placa do Veículo", new TextField());
        TextField tfPlaca = (TextField) painel.getComponent(painel.getComponentCount() - 1);

        painel.add(Box.createVerticalStrut(10));

        EPLabel lblTipo = new EPLabel("Tipo do Veículo");
        lblTipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(lblTipo);
        painel.add(Box.createVerticalStrut(10));

        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Carro", "Moto"});
        padronizar(cbTipo);
        painel.add(cbTipo);

        painel.add(Box.createVerticalStrut(20));

        ButtonPdr btnCadastrar = new ButtonPdr("Cadastrar");
        padronizar(btnCadastrar);
        painel.add(btnCadastrar);

        btnCadastrar.addActionListener(e -> {

            String nome = tfNome.getText().trim();
            String cpf = tfCpf.getText().trim();
            String placa = tfPlaca.getText().trim();
            String tipoVeiculo = (String) cbTipo.getSelectedItem();

            if (nome.isEmpty() || cpf.isEmpty() || placa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

            if (!cpf.matches("\\d{12}")) {
                JOptionPane.showMessageDialog(
                    this,
                    "CPF inválido. O CPF deve conter exatamente 12 números."
                );
                return;
            }

            Cliente cliente = new Cliente(nome, cpf, placa, tipoVeiculo);
            sistema.cadastrarUsuario(cliente);

            try {
                sistema.fazerReserva(cliente, vaga.getEmpresa(), vaga, horario);
                ArquivoUtil.salvarSistema(sistema);

                JOptionPane.showMessageDialog(this, "Reserva realizada com sucesso!");
                new IndexView(sistema).setVisible(true);
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao realizar reserva.");
            }
        });

        setVisible(true);
    }

    /* metodos pra auxiliar no paddrao */

    private void padronizar(JComponent c) {
        Dimension d = c.getPreferredSize();
        c.setMaximumSize(d);
        c.setMinimumSize(d);
        c.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void adicionarCampo(JPanel painel, String texto, TextField campo) {
        EPLabel label = new EPLabel(texto);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(label);

        padronizar(campo);
        painel.add(campo);
    }
}

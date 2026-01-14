package view;

import javax.swing.*;
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
            new Color(253,210,120),
            new Color(169,113,66) 
        );
        setContentPane(fundo);
        fundo.setLayout(new GridBagLayout());

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);
        painel.setPreferredSize(new Dimension(320, 400));
        fundo.add(painel);

        EPLabel titulo = EPLabel.titulo("Cadastro do Cliente");
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(titulo);
        painel.add(Box.createVerticalStrut(20));

        EPLabel lblNome = new EPLabel("Nome");
        painel.add(lblNome);
        TextField tfNome = new TextField();
        painel.add(tfNome);

        painel.add(Box.createVerticalStrut(10));

        EPLabel lblCpf = new EPLabel("CPF");
        painel.add(lblCpf);
        TextField tfCpf = new TextField();
        painel.add(tfCpf);

        painel.add(Box.createVerticalStrut(10));

        EPLabel lblPlaca = new EPLabel("Placa do Veículo");
        painel.add(lblPlaca);
        TextField tfPlaca = new TextField();
        painel.add(tfPlaca);

        painel.add(Box.createVerticalStrut(10));

        EPLabel lblTipo = new EPLabel("Tipo do Veículo");
        painel.add(lblTipo);

        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Carro", "Moto"});
        painel.add(cbTipo);

        painel.add(Box.createVerticalStrut(20));

        ButtonPdr btnCadastrar = new ButtonPdr("Cadastrar");
        painel.add(btnCadastrar);

        btnCadastrar.addActionListener(e -> {

            String nome = tfNome.getText();
            String cpf = tfCpf.getText();
            String placa = tfPlaca.getText();
            String tipoVeiculo = (String) cbTipo.getSelectedItem();

            if (nome.isEmpty() || cpf.isEmpty() || placa.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Preencha todos os campos.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cliente cliente = new Cliente(nome, cpf, placa, tipoVeiculo);
            sistema.cadastrarUsuario(cliente);

            try {
                sistema.fazerReserva(cliente, vaga.getEmpresa(), vaga, horario);
                ArquivoUtil.salvarSistema(sistema);

                JOptionPane.showMessageDialog(this,
                        "Reserva realizada com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Cliente não cadastrado.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        });

        setVisible(true);
    }
}

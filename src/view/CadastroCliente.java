package view;

import model.Cliente;


import TO.ClienteTO;

import javax.swing.*;
import java.awt.*;

import ui.ButtonPdr;
import ui.EPLabel;
import ui.Gradient;
import ui.TextField;

public class CadastroCliente extends JFrame {

    public CadastroCliente() {

        setTitle("Cadastro do Cliente");
        setSize(800, 520);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fundo em gradiente
        Gradient fundo = new Gradient(
                new Color(0xFF, 0xE5, 0xA5),
                new Color(0xFF, 0xE5, 0xA5)
        );
        setContentPane(fundo);
        fundo.setLayout(new GridBagLayout());

        // Painel central
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);
        painel.setPreferredSize(new Dimension(320, 400));
        fundo.add(painel);

        // Título
        EPLabel titulo = EPLabel.titulo("Cadastro do Cliente");
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(titulo);
        painel.add(Box.createVerticalStrut(20));

        // NOME
        EPLabel lblNome = new EPLabel("Nome");
        lblNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(lblNome);

        TextField tfNome = new TextField();
        tfNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(tfNome);
        painel.add(Box.createVerticalStrut(10));

        // CPF
        EPLabel lblCpf = new EPLabel("CPF");
        lblCpf.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(lblCpf);

        TextField tfCpf = new TextField();
        tfCpf.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(tfCpf);
        painel.add(Box.createVerticalStrut(10));

        // PLACA
        EPLabel lblPlaca = new EPLabel("Placa do Veículo");
        lblPlaca.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(lblPlaca);

        TextField tfPlaca = new TextField();
        tfPlaca.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(tfPlaca);
        painel.add(Box.createVerticalStrut(10));

        // TIPO DE VEÍCULO
        EPLabel lblTipo = new EPLabel("Tipo do Veículo");
        lblTipo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(lblTipo);

        String[] tipos = {"Carro", "Moto"};
        JComboBox<String> cbTipo = new JComboBox<>(tipos);
        cbTipo.setMaximumSize(new Dimension(280, 40));
        cbTipo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(cbTipo);
        painel.add(Box.createVerticalStrut(20));

        // BOTÃO CADASTRAR
        ButtonPdr btnCadastrar = new ButtonPdr("Cadastrar");
        btnCadastrar.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(btnCadastrar);

        // EVENTO DO BOTÃO
        btnCadastrar.addActionListener(e -> {

            String nome = tfNome.getText();
            String cpf = tfCpf.getText();
            String placa = tfPlaca.getText();
            String tipoVeiculo = (String) cbTipo.getSelectedItem();

            if (nome.isEmpty() || cpf.isEmpty() || placa.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Preencha todos os campos.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // 🔹 Cliente REAL do sistema
            Cliente cliente = new Cliente(
                    nome,
                    cpf,
                    placa,
                    tipoVeiculo
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente cadastrado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // 🔹 Abre o menu do cliente
            MenuCliente menu = new MenuCliente(cliente);
            menu.setVisible(true);

            dispose();
        });
    }

    // MAIN DE TESTE (SÓ PARA VOCÊ)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CadastroCliente().setVisible(true);
        });
    }
}

package view;

import java.awt.*;
import javax.swing.*;

import view.TelaEmpresa;


import ui.EPLabel;
import ui.TextField;
import ui.ButtonPdr;
import ui.Gradient;

import model.SistemaEstacionamento;
import model.Empresa;
import model.ArquivoUtil;

public class TelaDadosEmpresa extends JFrame {

    private SistemaEstacionamento sistema;
    private TextField txtNome;
    private TextField txtCnpj;

  

    public TelaDadosEmpresa(SistemaEstacionamento sistema) {
        this.sistema = sistema;

        Gradient painel2 = new Gradient(
                new Color(0xFF, 0xE5, 0xA5),
                new Color(0xFF, 0xE5, 0xA5)
        );

        setContentPane(painel2);
        painel2.setLayout(new GridBagLayout());

        setTitle("Cadastro de Empresa");
        setSize(800, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);
        painel2.add(painel);

        ImageIcon img = new ImageIcon("src/img/user.png");
        Image imagem = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(imagem));
        labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelImagem.setBorder(BorderFactory.createEmptyBorder(30, 0, 15, 0));
        painel.add(labelImagem);

        EPLabel titulo = EPLabel.titulo("Cadastro da Empresa");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);

        painel.add(Box.createVerticalStrut(15));

        EPLabel labelNome = new EPLabel("Nome da Empresa");
        labelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(labelNome);

        txtNome = new TextField();
        painel.add(txtNome);

        painel.add(Box.createVerticalStrut(20));

        EPLabel labelCnpj = new EPLabel("CNPJ");
        labelCnpj.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(labelCnpj);

        txtCnpj = new TextField();
        painel.add(txtCnpj);

        painel.add(Box.createVerticalStrut(30));

        ButtonPdr btnCadastrar = new ButtonPdr("Cadastrar");
        btnCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(btnCadastrar);

        btnCadastrar.addActionListener(e -> cadastrar());

        setVisible(true);
    }

    private void cadastrar() {
        if (txtNome.getText().isEmpty() || txtCnpj.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }
    
        Empresa empresa = new Empresa();
        empresa.setNome(txtNome.getText());
        empresa.setCnpj(txtCnpj.getText());

        sistema.cadastrarUsuario(empresa);
        ArquivoUtil.salvarSistema(sistema);

        JOptionPane.showMessageDialog(this, "Empresa cadastrada com sucesso!");
        new TelaEmpresa(sistema, empresa);
        dispose();
        }
}

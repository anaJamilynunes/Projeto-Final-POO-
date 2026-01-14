package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.Empresa;
import view.TelaEmpresaNintendo;
import ui.EPLabel;
import ui.TextField;
import ui.ButtonPdr;
import ui.Gradient;
import model.SistemaEstacionamento;
import model.ArquivoUtil;

public class TelaDadosEmpresa extends JFrame {

    private SistemaEstacionamento sistema;
    private TextField txtNome;
    private TextField txtCnpj;
    private JPasswordField txtSenha;
    private Empresa empresa;

    public TelaDadosEmpresa(SistemaEstacionamento sistema, Empresa empresa) {
        this.sistema = sistema;
        this.empresa = empresa;

        Gradient painel2 = new Gradient(
            new Color(253,210,120),
            new Color(169,113,66) 
        );

        setContentPane(painel2);
        painel2.setLayout(new BorderLayout());

                //painel pra seta de voltar
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
            new EntradaEmpresa(sistema);
            dispose();
            } 
        }); 

        painelTopo.add(btnVoltar);
        painel2.add(painelTopo, BorderLayout.NORTH);

        setTitle("Cadastro de Empresa");
        setSize(800, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);
        painel2.add(painel, BorderLayout.CENTER);

        ImageIcon img = new ImageIcon("src/img/user.png");
        Image imagem = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(imagem));
        labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelImagem.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        painel.add(labelImagem);

        EPLabel titulo = EPLabel.titulo("Cadastro da Empresa");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);

        painel.add(Box.createVerticalStrut(10));

        EPLabel labelNome = new EPLabel("Nome da Empresa");
        labelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(labelNome);

        txtNome = new TextField();
        txtNome.setMaximumSize(txtNome.getPreferredSize());
        painel.add(txtNome);
        // TextField txtNome = new TextField(); 
        // painel.add(txtNome);

        painel.add(Box.createVerticalStrut(10));

        EPLabel labelCnpj = new EPLabel("CNPJ");
        labelCnpj.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(labelCnpj);

        txtCnpj = new TextField();
        txtCnpj.setMaximumSize(txtCnpj.getPreferredSize());
        painel.add(txtCnpj);

        painel.add(Box.createVerticalStrut(10));

        //validação

        EPLabel labelSenha = new EPLabel("Palavra Passe");
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(labelSenha);

        //omissao da senha
        TextField modelo = new TextField();
        Dimension padrao = modelo.getPreferredSize();
        txtSenha = new JPasswordField(8);
        TextField.aplicarEstilo(txtSenha);

        txtSenha.setPreferredSize(padrao);
        txtSenha.setMaximumSize(padrao);
        txtSenha.setMinimumSize(padrao);
        painel.add(txtSenha);


        //validação

        painel.add(Box.createVerticalStrut(10));
        ButtonPdr btnCadastrar = new ButtonPdr("Cadastrar");

        Dimension d = btnCadastrar.getPreferredSize();
        btnCadastrar.setMaximumSize(d);
        btnCadastrar.setMinimumSize(d);
        btnCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(btnCadastrar);

        btnCadastrar.addActionListener(e -> cadastrar());

        setVisible(true);
    }

        private void cadastrar() {
        String nome = txtNome.getText().trim();
        String cnpj = txtCnpj.getText().trim();
        //String senha = txtSenha.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();

        if (nome.isEmpty() || cnpj.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        if (!cnpj.matches("\\d{14}")) {
            JOptionPane.showMessageDialog(this, "CNPJ deve conter 14 números!");
            return;
        }

        if (!senha.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(this, "Senha deve conter exatamente 8 números!");
            return;
        }

        if (sistema.buscarEmpresaPorCnpj(cnpj) != null) {
            JOptionPane.showMessageDialog(this, "Este CNPJ já está cadastrado!");
            return;
            }

        Empresa empresa = new Empresa();
        empresa.setNome(nome);
        empresa.setCnpj(cnpj);
        empresa.setSenha(senha);

        sistema.cadastrarUsuario(empresa);
        ArquivoUtil.salvarSistema(sistema);

        JOptionPane.showMessageDialog(this, "Empresa cadastrada com sucesso!");
        new TelaEmpresaNintendo(sistema, empresa);
        dispose();
    }

}


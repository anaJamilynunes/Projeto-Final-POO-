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
import model.Empresa;
import model.ArquivoUtil;

public class EditarEmpresa extends JFrame {

    private SistemaEstacionamento sistema;
    private Empresa empresa;
    private TextField txtNome;
    private TextField txtCnpj;
    private JPasswordField txtSenha;

    public EditarEmpresa(SistemaEstacionamento sistema, Empresa empresa) {
        this.empresa = empresa;
        this.sistema = sistema;

        Gradient painel2 = new Gradient(
            new Color(253,210,120),
            new Color(169,113,66) 
        );

        setContentPane(painel2);
        painel2.setLayout(new GridBagLayout());

        setTitle("Dados da Empresa");
        setVisible(true);
        setSize(800, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);
        painel2.add(painel);

        ImageIcon img1 = new ImageIcon("src/img/btnvoltar.png"); 
        Image imgIcon = img1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  
        JLabel btnVoltar = new JLabel(new ImageIcon(imgIcon)); 
        btnVoltar.setAlignmentX(Component.RIGHT_ALIGNMENT);

        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVoltar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0)); 
        
            btnVoltar.addMouseListener(new java.awt.event.MouseAdapter() { 
             @Override 
            public void mouseClicked(java.awt.event.MouseEvent e) {
            //SistemaEstacionamento sistema = ArquivoUtil.carregarSistema();
            new EntradaEmpresa(sistema);
            dispose();
            }
        }); 
        painel.add(btnVoltar);

        ImageIcon img = new ImageIcon("src/img/user.png");
        Image imagem = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(imagem));
        labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelImagem.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        painel.add(labelImagem);

        EPLabel titulo = EPLabel.titulo("Editar dados da Empresa");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);

        painel.add(Box.createVerticalStrut(15));

        EPLabel labelNome = new EPLabel("Nome da Empresa");
        labelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(labelNome);

        txtNome = new TextField();
        painel.add(txtNome);

        painel.add(Box.createVerticalStrut(15));

        EPLabel labelCnpj = new EPLabel("CNPJ");
        labelCnpj.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(labelCnpj);

        txtCnpj = new TextField();
        painel.add(txtCnpj);

        painel.add(Box.createVerticalStrut(15));

        //validação

        EPLabel labelSenha = new EPLabel("Palavra Passe");
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(labelSenha);
        //omissao da senha
        
        txtSenha = new JPasswordField(8);
        
        txtSenha.setHorizontalAlignment(JTextField.CENTER);
        txtSenha.setBackground(new Color(0xFFF4D6)); // amarelo muito claro
        txtSenha.setForeground(new Color(0x7A1022)); // vinho
        txtSenha.setCaretColor(new Color(0x7A1022));

        txtSenha.setBorder(new EmptyBorder(8, 12, 8, 12)); // padding interno
        txtSenha.setPreferredSize(new Dimension(280, 40));
        painel.add(txtSenha);

        //validação

        painel.add(Box.createVerticalStrut(15));

        ButtonPdr btnCadastrar = new ButtonPdr("Editar");
        btnCadastrar.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(btnCadastrar);

        ButtonPdr btnDeletar = new ButtonPdr("Deletar Empresa");
        btnDeletar.setAlignmentX(Component.RIGHT_ALIGNMENT);
        painel.add(btnDeletar);

        btnCadastrar.addActionListener(e -> editar());
        btnDeletar.addActionListener(e -> deletar());

        setVisible(true);
    }

    private void editar() {
    String nome = txtNome.getText().trim();
    String senha = new String(txtSenha.getPassword()).trim();

    if (nome.isEmpty() || senha.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
        return;
    }

    if (!senha.matches("\\d{8}")) {
        JOptionPane.showMessageDialog(this, "Senha deve conter exatamente 8 números!");
        return;
    }

    empresa.setNome(nome);
    empresa.setSenha(senha);

    ArquivoUtil.salvarSistema(sistema);

    JOptionPane.showMessageDialog(this, "Dados atualizados com sucesso!");
        new TelaEmpresaNintendo(sistema, empresa);
        dispose();
    }

    private void deletar() {
        int resp = JOptionPane.showConfirmDialog(
            this,
            "Tem certeza que deseja apagar esta empresa?\nTodos os dados serão perdidos!",
            "Confirmação",
            JOptionPane.YES_NO_OPTION
        );

        if (sistema.empresaTemReservaAtiva(empresa.getCnpj())) {
            JOptionPane.showMessageDialog(this,
                "Não é possível excluir.\nExistem vagas ocupadas ou reservas ativas.");
            return;
        }

        if (resp == JOptionPane.YES_OPTION) {
            sistema.removerEmpresa(empresa.getCnpj());
            ArquivoUtil.salvarSistema(sistema);

            JOptionPane.showMessageDialog(this, "Empresa removida!");
            new EntradaEmpresa(sistema);
            dispose();
        }
        sistema.removerEmpresa(empresa.getCnpj());
        System.out.println("Após remover: " + sistema.buscarEmpresaPorCnpj(empresa.getCnpj()));
        //se null empresa deletada ok!
    }



}

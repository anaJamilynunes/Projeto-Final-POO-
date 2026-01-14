package view; 
import java.awt.*; 

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
//import model.ArquivoUtil;
import model.SistemaEstacionamento;
import model.Empresa;
import view.*;
import TO.EmpresaTO;
import view.TelaEmpresaNintendo;
import ui.EPLabel; 
import ui.TextField; 
import ui.ButtonPdr; 
import ui.Gradient; 

public class EntradaEmpresa extends JFrame {
    private SistemaEstacionamento sistema;
    private Empresa empresa;
    public EntradaEmpresa(SistemaEstacionamento sistema) { //tela de login - entrada cnpj e senha - entrada pra p/n login 
    this.sistema = sistema;
    
        //gradiente background 
    Gradient painel2 = new Gradient( 
        //new Color(0xFD, 0xD2, 0x78),
            new Color(253,210,120),
            new Color(169,113,66) 
        ); 
        setContentPane(painel2); 
        painel2.setLayout(new BorderLayout());
        
        //fazer o botao de voltar
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
            new IndexView(sistema);
            dispose();
            }
        }); 
        painelTopo.add(btnVoltar);
        painel2.add(painelTopo, BorderLayout.NORTH);

        //---------


        setTitle("Login Empresa"); 
        setVisible(true); 
        setSize( 800, 500); 
        setResizable(false); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null); 
        
        JPanel painel = new JPanel(); 
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); 
        painel.setOpaque(false); // deixa o degradê aparecer 
        painel2.add(painel); 
        
        ImageIcon img = new ImageIcon("src/img/user.png"); 
        Image imagem = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); 
        JLabel labelImagem = new JLabel(new ImageIcon(imagem)); 
        labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        labelImagem.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); 
        
        painel.add(labelImagem); 
 
        EPLabel titulo = EPLabel.titulo("Bem-vindo de volta!"); 
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT); 
        painel.add(titulo); 
        
        painel.add(Box.createVerticalStrut(15)); 

        
        EPLabel labelCNPJ = new EPLabel("CNPJ"); 
        labelCNPJ.setAlignmentX(Component.CENTER_ALIGNMENT); 
        painel.add(labelCNPJ); 

        TextField tfCnpj = new TextField();  
        tfCnpj.setMaximumSize(tfCnpj.getPreferredSize());
        painel.add(tfCnpj); 

        painel.add(Box.createVerticalStrut(20)); 
        EPLabel labelSenha = new EPLabel("Palavra-Passe"); 
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT); 
        painel.add(labelSenha); 
        //parte que omite a senha

        TextField modelo = new TextField();
        Dimension padrao = modelo.getPreferredSize();

        JPasswordField tfSenha = new JPasswordField(8);
        TextField.aplicarEstilo(tfSenha);

        tfSenha.setPreferredSize(padrao);
        tfSenha.setMaximumSize(padrao);
        tfSenha.setMinimumSize(padrao);
        //tfSenha.setMaximumSize(tfSenha.getPreferredSize());

        painel.add(tfSenha);

        
        painel.add(Box.createVerticalStrut(10)); 
        
        //label clicavel + hover 
        EPLabel labelCriarLogin = new EPLabel("Não tem login?"); 
        
        labelCriarLogin.setAlignmentX(Component.CENTER_ALIGNMENT); 
        labelCriarLogin.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        painel.add(labelCriarLogin); 
        
        Color normal = new Color(107, 74, 53); // mesma do botão 
        Color hover = new Color(253,210,120); // hover do botão 
        
        
        labelCriarLogin.setForeground(normal); 
        labelCriarLogin.addMouseListener(new java.awt.event.MouseAdapter() { 
            
            @Override 
            public void mouseEntered(java.awt.event.MouseEvent e) { 
                labelCriarLogin.setForeground(hover); 
            } 
            @Override 
            public void mouseExited(java.awt.event.MouseEvent e) { 
                labelCriarLogin.setForeground(normal); 
            } @Override 
            public void mouseClicked(java.awt.event.MouseEvent e) {
            //SistemaEstacionamento sistema = ArquivoUtil.carregarSistema();
            new TelaDadosEmpresa(sistema, empresa);
            dispose();
            }
        }); 
        
    painel.add(Box.createVerticalStrut(10)); 

    ButtonPdr entrar = new ButtonPdr("Entrar");
    Dimension d = entrar.getPreferredSize();
    entrar.setMaximumSize(d); 
    entrar.setMinimumSize(d); 
    entrar.setAlignmentX(Component.CENTER_ALIGNMENT);

    //inicio da validacao
    entrar.addActionListener(e -> {

    String cnpj = tfCnpj.getText().trim();
    String senha = tfSenha.getText().trim();
    //String senha = new String(tfSenha.getPassword()).trim();

    // valida CNPJ 
    if (!cnpj.matches("\\d{14}")) {
        JOptionPane.showMessageDialog(this, "CNPJ inválido. Use 14 números.");
        return;
    }

    //valida senha
    if (!senha.matches("\\d{8}")) {
        JOptionPane.showMessageDialog(this, "A senha deve ter exatamente 8 números.");
        return;
    }

    //SistemaEstacionamento sistema = ArquivoUtil.carregarSistema();
    
    Empresa empresa = sistema.buscarEmpresaPorCnpj(cnpj);

    if (empresa == null) {
        JOptionPane.showMessageDialog(this, "Empresa não encontrada.");
        return;
    }

    // verifica senha
    if (!senha.equals(empresa.getSenha())) {
        JOptionPane.showMessageDialog(this, "Senha incorreta.");
        return;
    }

    // login OK
    new TelaEmpresaNintendo(sistema, empresa);
    dispose();
});


    /*entrar.addActionListener(e -> {
    SistemaEstacionamento sistema = ArquivoUtil.carregarSistema();
    Empresa empresa = sistema.buscarEmpresaPorCnpj(tfCnpj.getText());

    if (empresa != null) {
        new TelaEmpresa(sistema, empresa);
        dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Empresa não encontrada.");
    }
    });*/

    painel.add(entrar);


    } 
}
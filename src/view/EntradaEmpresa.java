package view; 
import java.awt.*; 

import javax.swing.*; 

import model.ArquivoUtil;
import model.SistemaEstacionamento;
import model.Empresa;
import ui.EPLabel; 
import ui.TextField; 
import ui.ButtonPdr; 
import ui.Gradient; 
public class EntradaEmpresa extends JFrame {
     public EntradaEmpresa() { //tela de login - entrada cnpj e senha - entrada pra p/n login 
     //gradiente background 
     Gradient painel2 = new Gradient( 
        //new Color(0xFD, 0xD2, 0x78),
         new Color(138, 111, 90), 
         new Color(138, 111, 90) 
        ); 
        setContentPane(painel2); 
        painel2.setLayout(new GridBagLayout()); // centralizar 
        setTitle("Empresa"); 
        //setVisible(true); 
        setSize( 800, 500); 
        setResizable(false); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null); 
        //centralizar 
        
        JPanel painel = new JPanel(); 
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); 
        painel.setOpaque(false); // deixa o degradê aparecer 
        painel2.add(painel); 
        
        ImageIcon img = new ImageIcon("src/img/user.png"); 
        Image imagem = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); 
        JLabel labelImagem = new JLabel(new ImageIcon(imagem)); 
        labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        labelImagem.setBorder(BorderFactory.createEmptyBorder(30, 0, 15, 0)); 
        
        painel.add(labelImagem); 
        EPLabel titulo = EPLabel.titulo("Bem-vindo de volta!"); 
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT); 
        painel.add(titulo); 
        
        painel.add(Box.createVerticalStrut(15)); 
        
        EPLabel labelCNPJ = new EPLabel("CNPJ"); 
        labelCNPJ.setAlignmentX(Component.CENTER_ALIGNMENT); 
        painel.add(labelCNPJ); 
        TextField tfCnpj = new TextField(); 
        painel.add(tfCnpj); 
        
        painel.add(Box.createVerticalStrut(20)); 
        EPLabel labelSenha = new EPLabel("Palavra-Passe"); 
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT); 
        painel.add(labelSenha); 
        TextField tfSenha = new TextField(); 
        painel.add(tfSenha); 
        
        painel.add(Box.createVerticalStrut(20)); 
        
        //label clicavel + hover 
        EPLabel labelCriarLogin = new EPLabel("Não tem login?"); 
        
        labelCriarLogin.setAlignmentX(Component.CENTER_ALIGNMENT); 
        labelCriarLogin.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        painel.add(labelCriarLogin); 
        
        Color normal = new Color(0x7A1022); // mesma do botão 
        Color hover = new Color(0xA3182D); // hover do botão 
        
        
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
            SistemaEstacionamento sistema = ArquivoUtil.carregarSistema();
            new TelaDadosEmpresa(sistema);
            dispose();
}
        }); 
        
        painel.add(Box.createVerticalStrut(10)); 
        ButtonPdr entrar = new ButtonPdr("Entrar");
entrar.setAlignmentX(Component.CENTER_ALIGNMENT);

entrar.addActionListener(e -> {
    SistemaEstacionamento sistema = ArquivoUtil.carregarSistema();
    Empresa empresa = sistema.buscarEmpresaPorCnpj(tfCnpj.getText());

    if (empresa != null) {
        new TelaEmpresaNintendo(sistema, empresa);
        dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Empresa não encontrada.");
    }
});

painel.add(entrar);


    } 
    }
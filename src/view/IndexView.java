package view;
import model.SistemaEstacionamento;
import model.ArquivoUtil;
import view.TelaReservarVaga;

import javax.swing.*;
import java.awt.*;
import ui.ButtonPdr;
import viewListeners.ListenerBtnEmpresa;
import ui.Gradient;

public class IndexView extends JFrame{
    public IndexView(){
        //JFrame jFrame = new JFrame("Home");
        setTitle("Home");
        //setVisible(true);
        setSize( 800, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null); //centralizar

        JPanel painel = new JPanel();
 

        //gradiente background
        Gradient painel2 = new Gradient(
            //new Color(0xFD, 0xD2, 0x78),  
            new Color(138, 111, 90),
            new Color(138, 111, 90)  
        );

        setContentPane(painel2);


        //img

        ImageIcon img = new ImageIcon("src/img/EPsemBG.png");
        Image imagem = img.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imgRedimensionada = new ImageIcon(imagem);

        JLabel labelImagem = new JLabel(imgRedimensionada);
        labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(labelImagem);
        painel.add(Box.createRigidArea(new Dimension(0, 100)));
        labelImagem.setBorder(BorderFactory.createEmptyBorder(60, 0, 20, 0));
       
        // logo pequena
  
        //botoess
        ButtonPdr botao = new ButtonPdr("Empresa");
        botao.addActionListener(new ListenerBtnEmpresa(this));
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);

        ButtonPdr botao2 = new ButtonPdr("Cliente");
        botao2.setBounds(0, 0, 200, 30);
        botao2.setAlignmentX(Component.CENTER_ALIGNMENT);

        botao2.addActionListener(e -> {
            SistemaEstacionamento sistema = ArquivoUtil.carregarSistema();
            new TelaReservarVaga(sistema);
            dispose();
        });

        painel.setOpaque(false); //painel ficar opaco
        painel.add(botao);
        painel.add(Box.createVerticalStrut(20));
        painel.add(botao2);

        add(painel);


    }   
}

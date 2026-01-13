package ui;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TextField extends JTextField{
        public TextField() {
        setFont(new Font("Segoe UI", Font.PLAIN, 15));
        setBackground(new Color(0xFFF4D6)); // amarelo muito claro
        setForeground(new Color(0x7A1022)); // vinho
        setCaretColor(new Color(0x7A1022));

        setBorder(new EmptyBorder(8, 12, 8, 12)); // padding interno
        setPreferredSize(new Dimension(280, 40));
    }
}

//  TextField tfCnpj = new TextField();  
//         painel.add(tfCnpj);

        painel.add(Box.createVerticalStrut(20)); 
        EPLabel labelSenha = new EPLabel("Palavra-Passe"); 
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT); 
        painel.add(labelSenha); 
        //parte que omite a senha
        JPasswordField tfSenha = new JPasswordField(8);
        
        tfSenha.setHorizontalAlignment(JTextField.CENTER);
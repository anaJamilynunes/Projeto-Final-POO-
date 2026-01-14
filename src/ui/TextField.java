package ui;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TextField extends JTextField{
        public TextField() {
        // setFont(new Font("Segoe UI", Font.PLAIN, 15));
        // setBackground(new Color(0xFFF4D6)); // amarelo muito claro
        // setForeground(new Color(0x7A1022)); // vinho
        // setCaretColor(new Color(0x7A1022));

        // setBorder(new EmptyBorder(8, 12, 8, 12)); // padding interno
        // setPreferredSize(new Dimension(280, 40));
        aplicarEstilo(this);
    }

    public static void aplicarEstilo(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        campo.setBackground(new Color(0xFFF4D6));
        campo.setForeground(new Color(0x7A1022));
        campo.setCaretColor(new Color(0x7A1022));
        campo.setBorder(new EmptyBorder(8, 12, 8, 12));
        campo.setPreferredSize(new Dimension(280, 40));
    }
}

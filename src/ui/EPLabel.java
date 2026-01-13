package ui;

import javax.swing.*;
import java.awt.*;

public class EPLabel extends JLabel {

    public EPLabel(String texto) {
        super(texto);

        setFont(new Font("Segoe UI", Font.BOLD, 16));
        setForeground(new Color(107, 74, 53)); // marrom
    }

    // para títulos
    public static EPLabel titulo(String texto) {
        EPLabel label = new EPLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 26));
        label.setForeground(new Color(107, 74, 53));
        return label;
    }

    // para texto comum
    public static EPLabel pequeno(String texto) {
        EPLabel label = new EPLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(new Color(107, 74, 53));
        return label;
    }
}

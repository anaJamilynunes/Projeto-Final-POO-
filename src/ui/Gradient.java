package ui;

import javax.swing.*;
import java.awt.*;

public class Gradient extends JPanel {

    private Color cor1;
    private Color cor2;

    public Gradient(Color cor1, Color cor2) {
        this.cor1 = cor1;
        this.cor2 = cor2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        GradientPaint gp = new GradientPaint(
                0, 0, cor1,     // canto superior
                0, height, cor2 // canto inferior
        );

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}
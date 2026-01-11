package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonPdr extends JButton {

    private Color normal = new Color(0x7A1022);   // vinho
    private Color hover  = new Color(0xA3182D);   // vinho claro
    private Color text   = new Color(0xFDD278);   // dourado

    public ButtonPdr(String texto) {
        super(texto);

        setFont(new Font("Segoe UI", Font.BOLD, 16));
        setForeground(text);
        setBackground(normal);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setPreferredSize(new Dimension(180, 45));

        // Hover
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(hover);
                repaint();
            }

            public void mouseExited(MouseEvent e) {
                setBackground(normal);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        super.paintComponent(g);
        g2.dispose();
    }
}

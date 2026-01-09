package view;

import javax.swing.SwingUtilities;

public class Exec {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            IndexView index = new IndexView();
            index.setVisible(true);
        });
    }
}
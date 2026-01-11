package view;

import javax.swing.SwingUtilities;

import ui.ButtonPdr;

public class Exec {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            IndexView index = new IndexView();
            index.setVisible(true);
        });

    }
}
package view;

import javax.swing.*;
import javax.swing.JFrame;

public class IndexView extends JFrame{
    public IndexView(){
        //JFrame jFrame = new JFrame("Home");
        setTitle("Home");
        //setVisible(true);
        setSize( 800, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //centralizar


    }   
}

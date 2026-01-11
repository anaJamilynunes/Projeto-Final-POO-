package viewListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.EntradaEmpresa;

public class ListenerBtnEmpresa implements ActionListener {
    JFrame telaAtual;

    public ListenerBtnEmpresa(JFrame telaAtual) {
        this.telaAtual = telaAtual;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Botão Empresa clicado!");
        // Abre a tela Empresa
        EntradaEmpresa tela = new EntradaEmpresa();
        tela.setVisible(true);
        telaAtual.dispose();//fechar a tela
    }
}

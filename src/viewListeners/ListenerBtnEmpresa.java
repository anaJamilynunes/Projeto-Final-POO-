package viewListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import model.SistemaEstacionamento;
import view.EntradaEmpresa;

public class ListenerBtnEmpresa implements ActionListener {
    JFrame telaAtual;
    private SistemaEstacionamento sistema;

    public ListenerBtnEmpresa(JFrame telaAtual, SistemaEstacionamento sistema) {
        this.telaAtual = telaAtual;
        this.sistema = sistema;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Botão Empresa clicado!");
        // Abre a tela Empresa
        EntradaEmpresa tela = new EntradaEmpresa(sistema);
        tela.setVisible(true);
        telaAtual.dispose();//fechar a tela
    }
}

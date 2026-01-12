package view;

import javax.swing.*;
import java.awt.*;
import model.*;

import ui.ButtonPdr;
import ui.Gradient;
import ui.EPLabel;

public class TelaEmpresa extends JFrame {

    private SistemaEstacionamento sistema;
    private Empresa empresa;
    private JTextArea area;

    public TelaEmpresa(SistemaEstacionamento sistema, Empresa empresa) {
        this.sistema = sistema;
        this.empresa = empresa;

        // Gradiente de fundo
        Gradient painel2 = new Gradient(new Color(0xFF, 0xE5, 0xA5), new Color(0xFF, 0xE5, 0xA5));
        setContentPane(painel2);
        painel2.setLayout(new GridBagLayout());

        setTitle("Área da Empresa - " + empresa.getNome());
        setSize(800, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel2.add(painel);

        EPLabel titulo = EPLabel.titulo("Bem-vindo, " + empresa.getNome() + "!");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);

        painel.add(Box.createVerticalStrut(20));

        // Área de vagas
        area = new JTextArea(15, 40);
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        painel.add(scroll);

        painel.add(Box.createVerticalStrut(20));

        // Botão adicionar vaga
        ButtonPdr btnAddVaga = new ButtonPdr("Adicionar Vaga");
        btnAddVaga.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAddVaga.addActionListener(e -> adicionarVaga());
        painel.add(btnAddVaga);

        // Botão atualizar
        ButtonPdr btnAtualizar = new ButtonPdr("Atualizar");
        btnAtualizar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAtualizar.addActionListener(e -> atualizarLista());
        painel.add(btnAtualizar);

        atualizarLista();

        setVisible(true);
    }

    private void adicionarVaga() {
        String numero = JOptionPane.showInputDialog("Número da vaga:");
        try {
            int n = Integer.parseInt(numero);
            empresa.adicionarVaga(new Vaga(n, empresa));
            ArquivoUtil.salvarSistema(sistema);
            atualizarLista();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número inválido.");
        }
    }

    private void atualizarLista() {
        area.setText("");
        for (Vaga v : empresa.getVagas()) {
            area.append("Vaga " + v.getNumero() + " - " + (v.vagaDisponivel() ? "Disponível" : "Ocupada") + "\n");
        }
        area.append("\nTotal disponíveis: " + empresa.vagasDisponiveis());
    }
}

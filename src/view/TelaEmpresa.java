package view;
import javax.swing.*;
import java.awt.BorderLayout;

import model.SistemaEstacionamento;
import model.Vaga;
import model.Empresa;
import model.ArquivoUtil;
public class TelaEmpresa extends JFrame {

    private Empresa empresa;
    private SistemaEstacionamento sistema;
    private JTextArea area;

    public TelaEmpresa(SistemaEstacionamento sistema, Empresa empresa) {
        this.sistema = sistema;
        this.empresa = empresa;

        setTitle("Área da Empresa - " + empresa.getNome());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnAddVaga = new JButton("Adicionar Vaga");
        JButton btnAtualizar = new JButton("Atualizar");

        btnAddVaga.addActionListener(e -> adicionarVaga());
        btnAtualizar.addActionListener(e -> atualizarLista());

        area = new JTextArea();
        area.setEditable(false);

        JPanel topo = new JPanel();
        topo.add(btnAddVaga);
        topo.add(btnAtualizar);

        add(topo, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);

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
            area.append(
                "Vaga " + v.getNumero() +
                " - " + (v.vagaDisponivel() ? "Disponível" : "Ocupada") +
                "\n"
            );
        }

        area.append("\nTotal disponíveis: " + empresa.vagasDisponiveis());
    }
}

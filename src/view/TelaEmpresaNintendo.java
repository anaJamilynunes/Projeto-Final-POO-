package view;

import model.ArquivoUtil;
import view.IndexView;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Empresa;
import model.Vaga;
import model.SistemaEstacionamento;
import ui.ButtonPdr;

public class TelaEmpresaNintendo extends JFrame {

    private JPanel painelVagas;       // Painel onde as vagas vão aparecer
    private Empresa empresa;
    private SistemaEstacionamento sistema;

    public TelaEmpresaNintendo(SistemaEstacionamento sistema, Empresa empresa) {
        this.empresa = empresa;
        this.sistema = sistema;

        setTitle("Vagas Disponíveis");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Fundo da tela
        JLabel fundo = new JLabel(new ImageIcon("src/img/fundo_nintendo.png"));
        fundo.setLayout(new BorderLayout());
        setContentPane(fundo);

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout());
        fundo.add(mainPanel, BorderLayout.CENTER);

        // Painel customizado para o título estilo Nintendo
JPanel tituloFundo = new JPanel() {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Anti-aliasing desligado para deixar pixelado
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        // Cor de fundo estilo Nintendo
        g2.setColor(new Color(0x8B0000)); // vermelho escuro
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Borda pixelada (2 camadas de "pixels")
        g2.setColor(new Color(0xFFD700)); // dourado
        for (int i = 0; i < 4; i++) {
            g2.drawRect(i, i, getWidth() - 1 - 2*i, getHeight() - 1 - 2*i);
        }

        g2.dispose();
    }
};

// Layout do título
tituloFundo.setLayout(new BorderLayout());
tituloFundo.setPreferredSize(new Dimension(0, 80)); // altura do painel

// Label do título
JLabel titulo = new JLabel("Painel da Empresa", SwingConstants.CENTER);
titulo.setFont(new Font("Monospaced", Font.BOLD, 28));
titulo.setForeground(Color.WHITE);
tituloFundo.add(titulo, BorderLayout.CENTER);

// Adiciona no topo do mainPanel
mainPanel.add(tituloFundo, BorderLayout.NORTH);


        // Painel central com vagas
        painelVagas = new JPanel();
        painelVagas.setOpaque(false);
        painelVagas.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JScrollPane scroll = new JScrollPane(painelVagas);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        mainPanel.add(scroll, BorderLayout.CENTER);

        // Rodapé com botões
        JPanel footer = new JPanel();
        footer.setOpaque(false);

// Botão adicionar vaga
        ButtonPdr btnAdicionar = new ButtonPdr("Adicionar Vaga");
        btnAdicionar.addActionListener(e -> adicionarVaga());
        footer.add(btnAdicionar);

//BOTÃO VOLTAR
        ButtonPdr btnVoltar = new ButtonPdr("Voltar");
        btnVoltar.addActionListener(e -> {
            ArquivoUtil.salvarSistema(sistema);
            new IndexView().setVisible(true);
            dispose();
        });
        footer.add(btnVoltar);

        mainPanel.add(footer, BorderLayout.SOUTH);


        atualizarVagas(); // Mostra as vagas existentes
        setVisible(true);
    }

    // Adiciona uma vaga nova
    private void adicionarVaga() {
        int numero = empresa.getVagas().size() + 1;
        Vaga vaga = new Vaga(numero, empresa);
        empresa.adicionarVaga(vaga);
        atualizarVagas();
        salvarSistema();
    }

    // Atualiza visualmente o painel de vagas
    private void atualizarVagas() {
        painelVagas.removeAll();
        for (Vaga vaga : empresa.getVagas()) {
            JLabel lbl = new JLabel("Vaga " + vaga.getNumero() + (vaga.vagaDisponivel() ? " - Livre" : " - Ocupada"));
            lbl.setFont(new Font("Monospaced", Font.BOLD, 16));
            lbl.setForeground(vaga.vagaDisponivel() ? Color.GREEN : Color.RED);
            painelVagas.add(lbl);
        }
        painelVagas.revalidate();
        painelVagas.repaint();
    }

    // Salva alterações no sistema
    private void salvarSistema() {
        ArquivoUtil.salvarSistema(sistema);
    }

}

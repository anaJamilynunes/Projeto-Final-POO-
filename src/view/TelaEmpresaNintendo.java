package view;

import java.awt.*;
import javax.swing.*;
import model.*;

public class TelaEmpresaNintendo extends JFrame {
    private SistemaEstacionamento sistema;
    private Empresa empresa;
    private JPanel gridPainel;
    private JLabel backgroundLabel;

    
    private ImageIcon blocoLivre;
    private ImageIcon blocoOcupado;

    public TelaEmpresaNintendo(SistemaEstacionamento sistema, Empresa empresa) {
        this.sistema = sistema;
        this.empresa = empresa;

        setTitle("Painel da Empresa - Nintendo Style");
        setSize(600, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // fundo
        try {
            ImageIcon fundo = new ImageIcon("src/img/fundo_nintendo.png");
            Image imagem = fundo.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
            fundo = new ImageIcon(imagem);
            backgroundLabel = new JLabel(fundo);
            backgroundLabel.setLayout(new BorderLayout());
            setContentPane(backgroundLabel);
        } catch (Exception e) {
            System.out.println("Imagem de fundo não encontrada!");
        }

        // carregar ícones dos blocos
        blocoLivre = new ImageIcon(new ImageIcon("src/img/bloco_verde.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        blocoOcupado = new ImageIcon(new ImageIcon("src/img/bloco_vermelho.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        // Título
        JLabel titulo = new JLabel("Painel de Vagas");
        titulo.setFont(new Font("PressStart2P", Font.BOLD, 16));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setForeground(new Color(255, 215, 0));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        backgroundLabel.add(titulo, BorderLayout.NORTH);

        // Grid de vagas
        gridPainel = new JPanel();
        gridPainel.setOpaque(false); // transparente para mostrar fundo
        gridPainel.setLayout(new GridLayout(5, 5, 5, 5));
        backgroundLabel.add(gridPainel, BorderLayout.CENTER);

        // Rodapé com botão de adicionar vaga
        JButton btnAdicionar = new JButton("Adicionar Vaga");
        btnAdicionar.setFont(new Font("PressStart2P", Font.BOLD, 12));
        btnAdicionar.setBackground(new Color(0xFF5555));
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setOpaque(true);
        btnAdicionar.setBorderPainted(false);
        btnAdicionar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnAdicionar.addActionListener(e -> adicionarVaga());

        JPanel painelRodape = new JPanel();
        painelRodape.setOpaque(false); // transparente
        painelRodape.add(btnAdicionar);
        backgroundLabel.add(painelRodape, BorderLayout.SOUTH);

        atualizarVagas();
        setVisible(true);
    }

    private void adicionarVaga() {
        String input = JOptionPane.showInputDialog(this, "Número da vaga:");
        if (input == null) return;
        try {
            int numero = Integer.parseInt(input);
            empresa.adicionarVaga(new Vaga(numero, empresa));
            ArquivoUtil.salvarSistema(sistema);
            atualizarVagas();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número inválido!");
        }
    }

    private void atualizarVagas() {
        gridPainel.removeAll();
    
        // Se não houver vagas, mostrar aviso
        if (empresa.getVagas().isEmpty()) {
            JLabel aviso = new JLabel("Nenhuma vaga cadastrada");
            aviso.setForeground(Color.WHITE);
            aviso.setHorizontalAlignment(SwingConstants.CENTER);
            gridPainel.setLayout(new BorderLayout());
            gridPainel.add(aviso, BorderLayout.CENTER);
        } else {
            gridPainel.setLayout(new GridLayout(5, 5, 5, 5));
            for (Vaga v : empresa.getVagas()) {
                JButton botao = new JButton();
                botao.setIcon(v.vagaDisponivel() ? blocoLivre : blocoOcupado);
                botao.setOpaque(false);
                botao.setContentAreaFilled(false);
                botao.setBorderPainted(false);
    
                botao.addActionListener(ev -> {
                    if (v.vagaDisponivel()) v.ocupar();
                    else v.liberar();
                    atualizarVagas();
                    ArquivoUtil.salvarSistema(sistema);
                });
    
                gridPainel.add(botao);
            }
        }
    
        gridPainel.revalidate();
        gridPainel.repaint();
    }
    
}

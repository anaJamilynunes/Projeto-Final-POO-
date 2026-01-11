public class TelaCadastroEmpresa extends JFrame {

    private SistemaEstacionamento sistema;
    private JTextField txtNome;
    private JTextField txtCnpj;

    public TelaCadastroEmpresa(SistemaEstacionamento sistema) {
        this.sistema = sistema;

        setTitle("Cadastro de Empresa");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        txtNome = new JTextField(20);
        txtCnpj = new JTextField(20);

        JButton btnCadastrar = new JButton("Cadastrar Empresa");

        btnCadastrar.addActionListener(e -> cadastrar());

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Nome da Empresa:"));
        panel.add(txtNome);
        panel.add(new JLabel("CNPJ:"));
        panel.add(txtCnpj);
        panel.add(new JLabel());
        panel.add(btnCadastrar);

        add(panel);
        setVisible(true);
    }

    private void cadastrar() {
        if (txtNome.getText().isEmpty() || txtCnpj.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        Empresa empresa = new Empresa();
        empresa.setNome(txtNome.getText());
        // empresa.setCnpj(txtCnpj.getText()); // se você usar

        sistema.cadastrarUsuario(empresa);
        ArquivoUtil.salvarSistema(sistema);

        JOptionPane.showMessageDialog(this, "Empresa cadastrada com sucesso!");

        dispose();
    }
}

package model;
public class Exec {
    public static void main(String[] args) {

        SistemaEstacionamento sistema = ArquivoUtil.carregarSistema();

        Cliente c1 = new Cliente();
        c1.setNome("João");

        Empresa e1 = new Empresa();
        e1.setNome("Shopping Center");

        Vaga v1 = new Vaga(1, e1);
        e1.adicionarVaga(v1);

        sistema.cadastrarUsuario(c1);
        sistema.cadastrarUsuario(e1);

        //noemi
        try {
            sistema.fazerReserva(c1, e1, v1);
        } catch (VagaIndisponivelException e) {
            System.out.println(e.getMessage());
        }

        ArquivoUtil.salvarSistema(sistema);

        System.out.println("Sistema rodando corretamente!");
    }

}

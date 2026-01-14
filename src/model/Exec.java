package model;
public class Exec {
    public static void main(String[] args) {

        SistemaEstacionamento sistema = ArquivoUtil.carregarSistema();

        Cliente c1 = new Cliente(
                "João",
                "123.456.789-00",
                "ABC-1234",
                "Carro"
        );

        Empresa e1 = new Empresa();
        e1.setNome("Shopping Center");


        Usuario u1 = new Cliente(
        "João",
        "123.456.789-00",
        "ABC-1234",
        "Carro"
        );

        Usuario u2 = new Empresa();
        u2.setNome("Shopping Center");
                
        sistema.cadastrarUsuario(c1);
        sistema.cadastrarUsuario(e1);
        sistema.cadastrarUsuario(u1);
        sistema.cadastrarUsuario(u2);
        
        e1.adicionarVaga();
        e1.adicionarVaga();

        Vaga vagaDisponivel = e1.getVagas().get(0);

         try {
            java.time.LocalTime agora = java.time.LocalTime.now();
            sistema.fazerReserva(c1, e1, vagaDisponivel, agora);

            System.out.println("Reserva realizada com sucesso!");
        } catch (VagaIndisponivelException e) {
            System.out.println("Erro ao reservar: " + e.getMessage());
        }

        ArquivoUtil.salvarSistema(sistema);

        System.out.println("Sistema rodando corretamente!");
    }

}

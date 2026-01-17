package model;

import java.util.Scanner;

public class Exec {
    public static void main(String[] args) {

        SistemaEstacionamento sistema = ArquivoUtil.carregarSistema();
        Scanner sc = new Scanner(System.in);

        Usuario[] usuarios = new Usuario[3];
        int index = 0;

        while (index < usuarios.length) {
            System.out.println("\nCadastrar usuário " + (index + 1));
            System.out.println("1 - Cliente");
            System.out.println("2 - Empresa");
            System.out.print("Escolha: ");
            int opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            if (opcao == 1) {
                System.out.print("Nome: ");
                String nome = sc.nextLine();

                System.out.print("CPF: ");
                String cpf = sc.nextLine();

                System.out.print("Placa do veículo: ");
                String placa = sc.nextLine();

                System.out.print("Tipo do veículo: ");
                String tipo = sc.nextLine();

                Cliente cliente = new Cliente(nome, cpf, placa, tipo);
                usuarios[index] = cliente;
                sistema.cadastrarUsuario(cliente);

                // Verificar se já existe empresa para fazer reserva
                Empresa empresa = null;
                for (Usuario u : usuarios) {
                    if (u instanceof Empresa) {
                        empresa = (Empresa) u;
                        break;
                    }
                }

                if (empresa != null) {
                    // Criar vagas se não tiver
                    if (empresa.getVagas().isEmpty()) {
                        empresa.adicionarVaga();
                        empresa.adicionarVaga();
                    }

                    Vaga vagaDisponivel = empresa.getVagas().get(0);

                    try {
                        java.time.LocalTime agora = java.time.LocalTime.now();
                        sistema.fazerReserva(cliente, empresa, vagaDisponivel, agora);
                        System.out.println("Reserva realizada com sucesso para " + cliente.getNome());
                    } catch (VagaIndisponivelException e) {
                        System.out.println("Erro ao reservar: " + e.getMessage());
                    }
                } else {
                    System.out.println("Nenhuma empresa cadastrada ainda. Cadastre uma empresa primeiro para fazer reservas.");
                }

                index++;

            } else if (opcao == 2) {
                System.out.print("Nome da empresa: ");
                String nome = sc.nextLine();

                Empresa empresa = new Empresa();
                empresa.setNome(nome);

                usuarios[index] = empresa;
                sistema.cadastrarUsuario(empresa);

                System.out.println("Empresa cadastrada com sucesso: " + empresa.getNome());
                index++;

            } else {
                System.out.println("Opção inválida!");
            }
        }

        ArquivoUtil.salvarSistema(sistema);
        System.out.println("\nSistema rodando corretamente!");
        sc.close();
    }
}

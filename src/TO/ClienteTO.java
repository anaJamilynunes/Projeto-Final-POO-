package TO;

public class ClienteTO {

    private String nome;
    private String cpf;
    private String placa;
    private String tipoVeiculo;

    public ClienteTO(String nome, String cpf, String placa, String tipoVeiculo) {
        this.nome = nome;
        this.cpf = cpf;
        this.placa = placa;
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }
}

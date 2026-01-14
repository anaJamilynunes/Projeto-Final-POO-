package model;
import java.util.ArrayList;
import java.util.List;

public class Empresa extends Usuario{
    private String cnpj;
    private String senha;
    private int limiteVagas = 20;
    private ArrayList<Vaga> vagas = new ArrayList<>();
    private List<RegistroReserva> historicoReservas = new ArrayList<>();
    
    @Override
    public String tipoUser(){
        return "Empresa";
    }
    /*  a classe pai Usuario, pode ter como user a empresa e o cliente
    por isso é ideal diferenciar suas particularidades
    */
    
    public String getCnpj() {
    return cnpj;
    }
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
//VAGas
   
public boolean podeAdicionarVaga() {
        return vagas.size() < limiteVagas;
    }

    public boolean adicionarVaga() {
        if (!podeAdicionarVaga()) return false;
        vagas.add(new Vaga(vagas.size() + 1, this));
        return true;
    }


    public void adicionarVaga(Vaga v) {
        if (v != null && podeAdicionarVaga()) {
            vagas.add(v);
        }
    }


    public int vagasDisponiveis() {
        int cont = 0;
        for (Vaga v : vagas) {
            if (v.vagaDisponivel()) {
                cont++;
            }
        }
        return cont;
    }

    public ArrayList<Vaga> getVagas() {
        return vagas;
    }

    public int getLimiteVagas() {
        return limiteVagas;
    }

    public void setLimiteVagas(int limiteVagas) {
        this.limiteVagas = limiteVagas;
    }

    public void adicionarRegistro(RegistroReserva registro) {
    historicoReservas.add(registro);
    }

    public List<RegistroReserva> getHistoricoReservas() {
        return historicoReservas;
    }
//Quem oferece disponibilidade de vagas de estacionamento


}



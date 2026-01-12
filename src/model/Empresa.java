package model;
import java.util.ArrayList;

public class Empresa extends Usuario{
    private String cnpj;
    private ArrayList<Vaga> vagas = new ArrayList<>();

//Quem oferece disponibilidade de vagas de estacionamento

    @Override
    public String tipoUser(){
        return "Empresa";
    };
/*  a classe pai Usuario, pode ter como user a empresa e o cliente
    por isso é ideal diferenciar suas particularidades
*/

    public void adicionarVaga(Vaga vaga){
        if(vaga != null){
            vagas.add(vaga);
        }
    }

    public int vagasDisponiveis(){
        int cont = 0;

        for(int i = 0; i < vagas.size(); i++){
            if(vagas.get(i).vagaDisponivel()){
                cont++;
            }
        }
        return cont;
    }

    public ArrayList<Vaga> getVagas(){
        return vagas;
    }
    public String getCnpj() {
    return cnpj;
}

public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
}


}



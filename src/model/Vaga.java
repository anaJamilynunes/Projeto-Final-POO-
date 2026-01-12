package model;
import java.io.Serializable;
public class Vaga implements Serializable{
    int numero;
    boolean ocupada;
    private Empresa empresa;

    public Vaga(int numero, Empresa empresa){
        this.numero = numero;
        this.ocupada = false;
        this.empresa = empresa;
    }

    public boolean vagaDisponivel(){
        return !ocupada;
    };

    public void ocupar(){
        if(!ocupada){
            ocupada = true;
            System.out.println("Vaga " + numero + " foi ocupada!");
        }
    }

    public void liberar(){
        if(ocupada){
            ocupada = false;
            System.out.println("Vaga " + numero + " foi liberada!");
        }
    }

    public int getNumero(){
        return numero;
    }

    public Empresa getEmpresa() {
        return empresa;
    }
    public void setEmpresa(Empresa empresa) {
    this.empresa = empresa;
}

}
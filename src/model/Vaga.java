public class Vaga{
    int numero;
    boolean ocupada;
    private Empresa empresa;

    public Vaga(int numero){
        this.numero = numero;
        this.ocupada = false;
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
}
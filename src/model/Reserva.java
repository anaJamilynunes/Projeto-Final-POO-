public class Reserva implements GerenciarR{
    Cliente cliente;
    Empresa empresa;
    Vaga vaga;
    boolean vagaAtiva;

    public Reserva(Cliente cliente, Empresa empresa, Vaga vaga){
        this.cliente = cliente;
        this.empresa = empresa;
        this.vaga = vaga;
        this.vagaAtiva = true;

        //para que quando a reserva for criada a vaga seja ocupada
        this.vaga.ocupar();
    }
    @Override
    public void cancelar(){
        if(vagaAtiva){
            vagaAtiva = false;
            this.vaga.liberar();
            System.out.println("Reserva do cliente " + cliente.getNome() + " cancelada com sucesso!");
        }else{
            System.out.println("Esta reserva está inativa!");
        }
    }
    //retorna se a vaga está ou não ativa
    @Override
    public boolean estadoReserva(){
        return vagaAtiva;
    }

    
}
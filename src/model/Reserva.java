package model;

import java.io.Serializable;
import java.time.LocalTime;
public class Reserva implements GerenciarR, Serializable{
    Cliente cliente;
    private Empresa empresa;
    Vaga vaga;
    boolean vagaAtiva;
    private LocalTime horario;
    private RegistroReserva registro;

    public Reserva(Cliente cliente, Empresa empresa, Vaga vaga, LocalTime horario){
        this.cliente = cliente;
        this.empresa = empresa;
        this.vaga = vaga;
        this.vagaAtiva = true;
        this.horario = horario;

        //para que quando a reserva for criada a vaga seja ocupada
        this.vaga.ocupar(cliente.getNome(), horario);
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setRegistro(RegistroReserva registro) {
    this.registro = registro;
    }   

    public RegistroReserva getRegistro() {
        return registro;
    }

    public Cliente getCliente() { return cliente; }
    public LocalTime getHorario() { return horario; }
    
}
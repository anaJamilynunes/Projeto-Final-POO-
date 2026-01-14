package model;
import java.time.LocalTime;
import java.io.Serializable;
public class Vaga implements Serializable{
    private int numero;
    private boolean ocupada;
    private Empresa empresa;
    private String cliente; // Nome do cliente que reservou
    private LocalTime horaReserva; // horário reservado

    public Vaga(int numero, Empresa empresa){
        this.numero = numero;
        this.empresa = empresa;
        this.ocupada = false;
        this.cliente = null;
        this.horaReserva = null;
        
    }

    public boolean vagaDisponivel(){
        return !ocupada;
    }

    public boolean isOcupada() {
        return ocupada;
    }

     public void ocupar(String cliente, LocalTime hora) {
        if(!ocupada){
            ocupada = true;
            this.cliente = cliente;
            this.horaReserva = hora;
        }
    }

    public void liberar() {
        this.ocupada = false;
        this.cliente = null;
        this.horaReserva = null;
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


public String getCliente() {
    return cliente;
}

public LocalTime getHoraReserva() {
    return horaReserva;
}

 @Override
    public String toString() {
        return (empresa != null ? empresa.getNome() : "Sem empresa") +
               " | Vaga " + numero +
               (ocupada ? " - Ocupada por: " + cliente + " (" + horaReserva + ")" : " - Livre");
    }
}
package model;

import java.time.LocalTime;

public class RegistroReserva {

    private Cliente cliente;
    private Vaga vaga;
    private String placa;
    private LocalTime horario;
    private boolean ativa;

    public RegistroReserva(Cliente cliente, Vaga vaga, LocalTime horario) {
        this.cliente = cliente;
        this.vaga = vaga;
        this.placa = cliente.getPlaca();
        this.horario = horario;
        this.ativa = true;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public String getPlaca() {
        return placa;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void finalizar() {
        this.ativa = false;
    }
}

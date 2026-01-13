package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    private String cpf;
    private String placa;
    private String tipoVeiculo;

    ArrayList<Reserva> reservas = new ArrayList<>();

    public Cliente(String nome, String cpf, String placa, String tipoVeiculo) {
        super(nome); // chama o construtor de Usuario
        this.cpf = cpf;
        this.placa = placa;
        this.tipoVeiculo = tipoVeiculo;
    }

    @Override
    public String tipoUser() {
        return "Cliente";
    }

    /* o cliente apenas faz reservas
       quem gerencia as vagas é apenas o sistema
    */

    public void reservarVaga(Reserva reservaAD) {
        reservas.add(reservaAD);
    }

    public void liberarVaga(Reserva reservaLB) {
        reservas.remove(reservaLB);
    }

    public List<Reserva> getReservas() {
        return reservas;
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

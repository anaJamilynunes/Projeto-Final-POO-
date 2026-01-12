package model;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario{
    ArrayList<Reserva> reservas = new ArrayList<>();

    @Override
    public String tipoUser(){
        return "Cliente";
    }

    /*o clinte apenas faz reservas
    quem gerencia as vagas é apenas o sitema
    */

    public void reservarVaga(Reserva reservaAD){
        reservas.add(reservaAD);
    }

    public void liberarVaga(Reserva reservaLB){
        reservas.remove(reservaLB);
    }

    public List<Reserva> getReservas(){
        return reservas;
    }


}
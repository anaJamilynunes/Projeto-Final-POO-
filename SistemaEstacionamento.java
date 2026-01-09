import java.io.Serializable;
import java.util.ArrayList;

public class SistemaEstacionamento implements Serializable{
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Empresa> empresas = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Reserva> reservas = new ArrayList<>();
    

    public void cadastrarUsuario(Usuario user){
        usuarios.add(user);
        //diferenciar cliente de empresa com o instanceof
        //mas também adiciona-lo aos usuários

        if(user instanceof Empresa){
            empresas.add((Empresa) user);
        }else if(user instanceof Cliente){
            clientes.add((Cliente) user);
        }
    }

    public ArrayList<Vaga> buscarVagasDisponiveis(){
        ArrayList<Vaga> vagasDisponiveis = new ArrayList<>();

        for(int i = 0; i < empresas.size(); i++){
            Empresa e1 = empresas.get(i);

            for(int j = 0; j < e1.getVagas().size(); j++){
                Vaga v = e1.getVagas().get(j);

                if(v.vagaDisponivel()){
                    vagasDisponiveis.add(v);
                }
            }
        }

        return vagasDisponiveis;
    }
//noemi
    public Reserva fazerReserva(Cliente c, Empresa e, Vaga v)
        throws VagaIndisponivelException {

    if (!v.vagaDisponivel()) {
        throw new VagaIndisponivelException();
    }

    Reserva novaReserva = new Reserva(c, e, v);
    reservas.add(novaReserva);
    c.reservarVaga(novaReserva);
    return novaReserva;
}

//noemi
    public Reserva fazerReserva(Cliente c, Vaga v)
            throws VagaIndisponivelException {
        return fazerReserva(c, v.getEmpresa(), v);
    }


    public void liberarReserva(Reserva reserva){
        reserva.cancelar();
        reservas.remove(reserva);
    }

    public boolean existeVagaDisponivel(){
        ArrayList<Vaga> listaVDisponivel = buscarVagasDisponiveis();

        if(listaVDisponivel.size() > 0){
            return true;
        }

        return false;
    }

    public int qtdVagasDisponiveis(){
        return buscarVagasDisponiveis().size();
    }


}
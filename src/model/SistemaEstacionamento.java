package model;
import java.time.LocalTime;
import java.io.Serializable;
import java.util.ArrayList;

public class SistemaEstacionamento implements Serializable{
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Empresa> empresas = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Reserva> reservas = new ArrayList<>();

    private Vaga vagaReservada;

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

    public Reserva fazerReserva(Cliente c, Empresa e, Vaga v, java.time.LocalTime horario)
        throws VagaIndisponivelException {

    if (!v.vagaDisponivel()) {
        throw new VagaIndisponivelException();
    }
java.time.LocalTime agora = java.time.LocalTime.now();
Reserva novaReserva = new Reserva(c, e, v, horario);

    reservas.add(novaReserva);
    c.reservarVaga(novaReserva);
    return novaReserva;
}

    public Reserva fazerReserva(Cliente c, Vaga v)
            throws VagaIndisponivelException {
       java.time.LocalTime agora = java.time.LocalTime.now();
    return fazerReserva(c, v.getEmpresa(), v, agora);
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

    public Empresa buscarEmpresaPorCnpj(String cnpj) {
        for (Empresa e : empresas) {
            if (e.getCnpj() != null && e.getCnpj().equals(cnpj)) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<Empresa> getEmpresas() {
        return empresas;
    }

    public Vaga getVagaReservada() {
        return vagaReservada;
    }

    public void reservarVaga(Vaga vaga) {
        this.vagaReservada = vaga;
    }

    public void cancelarReserva() {
        if (vagaReservada != null) {
            vagaReservada.liberar();
            vagaReservada = null;
        }
    }
     public void confirmarReserva(Cliente cliente, Vaga vaga, java.time.LocalTime horario)
        throws VagaIndisponivelException {

    if (!vaga.vagaDisponivel()) {
        throw new VagaIndisponivelException();
    }

    // Cria a reserva (o construtor já ocupa a vaga)
    Reserva reserva = new Reserva(cliente, vaga.getEmpresa(), vaga, horario);

    // Registra no sistema e no cliente
    reservas.add(reserva);
    cliente.reservarVaga(reserva);

    // ✅ ESTA É A LINHA IMPORTANTE DA SOLUÇÃO MÍNIMA:
    this.vagaReservada = vaga;
}

    
    //a diferença é que busca vaga ativa por empresa especifica
    public boolean empresaTemReservaAtiva(String cnpjEmpresa) {
        for (Reserva r : reservas) {
            if (r.getEmpresa().getCnpj().equals(cnpjEmpresa) &&
            r.estadoReserva()) {
                return true;
            }
        }
        return false;
    }
    
    
    public boolean removerEmpresa(String cnpj) {
        return empresas.removeIf(e -> e.getCnpj().equals(cnpj));
    }
    
    
    
    
}

public class VagaIndisponivelException extends Exception {

    public VagaIndisponivelException() {
        super("Erro: a vaga escolhida já está ocupada.");
    }

    public VagaIndisponivelException(String mensagem) {
        super(mensagem);
    }
}

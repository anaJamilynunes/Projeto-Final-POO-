package model;
import java.io.*;

public class ArquivoUtil {

    public static void salvarSistema(SistemaEstacionamento sistema) {
        try {
            ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream("src/estacionamento.dat"));
            out.writeObject(sistema);
            out.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo.");
        }
    }

    public static SistemaEstacionamento carregarSistema() {
        try {
            ObjectInputStream in =
                new ObjectInputStream(new FileInputStream("src/estacionamento.dat"));
            SistemaEstacionamento s = (SistemaEstacionamento) in.readObject();
            in.close();
            return s;
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado. Criando novo sistema.");
            return new SistemaEstacionamento();
        }
    }
}

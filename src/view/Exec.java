package view; 
import javax.swing.SwingUtilities;
import view.*;
import model.ArquivoUtil;
import model.SistemaEstacionamento;
import ui.ButtonPdr; 
public class Exec { 
    public static void main(String[] args) { 
        // SwingUtilities.invokeLater(() -> { 
        //     IndexView index = new IndexView(); index.setVisible(true); 
        // }); 
            SwingUtilities.invokeLater(() -> {
            // 1️⃣ Carrega o sistema UMA única vez
            SistemaEstacionamento sistema = ArquivoUtil.carregarSistema();

            // 2️⃣ Abre a primeira tela passando o mesmo sistema
            IndexView index = new IndexView(sistema);
            index.setVisible(true);
        });


        } 
}
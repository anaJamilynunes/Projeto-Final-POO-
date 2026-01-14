package view; 
import javax.swing.SwingUtilities;
import view.*;
import model.ArquivoUtil;
import model.SistemaEstacionamento;
import ui.ButtonPdr; 
public class Exec { 
    public static void main(String[] args) { 
            SwingUtilities.invokeLater(() -> {
            SistemaEstacionamento sistema = ArquivoUtil.carregarSistema();

            if(sistema == null){
                sistema = new SistemaEstacionamento();
            }

            IndexView index = new IndexView(sistema);
            index.setVisible(true);
        });


        } 
}
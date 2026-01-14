package model;
import java.io.Serializable;
public abstract class Usuario implements Serializable{
    private String nome;
    private int id;
    
    private static int contadorUsuarios = 0;
    //atributo de classe

/*Usuarios:
Empresas e Clientes
*/

    public Usuario(){
        this.nome = "";
        this.id = contadorUsuarios;
        contadorUsuarios++;
    }

    //o id deve ser atualizado conforme a entrada de novos user

    public Usuario(String nome){
        this.nome = nome;
        this.id = contadorUsuarios;
        contadorUsuarios++;
    }

    public abstract String tipoUser();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
 
}
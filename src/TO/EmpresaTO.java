package TO;

public class EmpresaTO {
    //login e gerenciamente do sistema por 
    //parte da empresa
    private String cnpj;
    private int senha;

    public EmpresaTO(){};

    public EmpresaTO(String cnpj, int senha) {
        this.cnpj = cnpj;
        this.senha = senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }
    

}

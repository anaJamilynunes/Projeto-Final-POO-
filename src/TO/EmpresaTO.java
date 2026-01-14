package TO;

public class EmpresaTO {
    //login e gerenciamente do sistema por 
    //parte da empresa
    private String cnpj;
    private String senha;

    public EmpresaTO(){}

    public EmpresaTO(String cnpj, String senha) {
        this.cnpj = cnpj;
        this.senha = senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    

}

package primeiroprojeto.model.bean;

/**
 *
 * @author Gabriel_SA
 */
public class Telefone {
    private String telefone;
    private int matricula_fk;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getMatricula_fk() {
        return matricula_fk;
    }

    public void setMatricula_fk(int matricula_fk) {
        this.matricula_fk = matricula_fk;
    }   
}

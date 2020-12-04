package primeiroprojeto.model.bean;

/**
 *
 * @author Gabriel_SA
 */
public class Emprestimo {
    
    private String situacao,
                   data_emprestimo,
                   hora_emprestimo,
                   data_devolucao;
    private int id_resp_fk,
                id_item_loc,
                matricula_func_fk,
                id;    

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(String data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public String getHora_emprestimo() {
        return hora_emprestimo;
    }

    public void setHora_emprestimo(String hora_emprestimo) {
        this.hora_emprestimo = hora_emprestimo;
    }

    public String getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(String data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public int getId_resp_fk() {
        return id_resp_fk;
    }

    public void setId_resp_fk(int id_resp_fk) {
        this.id_resp_fk = id_resp_fk;
    }

    public int getId_item_loc() {
        return id_item_loc;
    }

    public void setId_item_loc(int id_item_loc) {
        this.id_item_loc = id_item_loc;
    }

    public int getMatricula_func_fk() {
        return matricula_func_fk;
    }

    public void setMatricula_func_fk(int matricula_func_fk) {
        this.matricula_func_fk = matricula_func_fk;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}

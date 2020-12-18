package primeiroprojeto.model.bean;

import java.sql.Date;

/**
 *
 * @author Gabriel_SA
 */
public class Emprestimo {
    
    private Date
                data_emprestimo,
                data_devolucao;
    private int id_resp_fk,
                id_item_loc,
                id_espaco_loc,
                matricula_func_fk,
                id;    
    private boolean status;

    public Date getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(Date data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Date data_devolucao) {
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

    public int getId_espaco_loc() {
        return id_espaco_loc;
    }

    public void setId_espaco_loc(int id_espaco_loc) {
        this.id_espaco_loc = id_espaco_loc;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}

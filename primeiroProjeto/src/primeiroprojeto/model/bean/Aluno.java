/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeiroprojeto.model.bean;

/**
 *
 * @author Pedro Henrique
 */
public class Aluno {
    private int matricula;
    private String nome;
    private String rua;
    private boolean ativo;
    private int id_resp_fk;
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }
    
    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public int getId_resp_fk() {
        return id_resp_fk;
    }

    public void setId_resp_fk(int id_resp_fk) {
        this.id_resp_fk = id_resp_fk;
    }
}

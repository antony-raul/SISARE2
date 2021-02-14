package primeiroprojeto.model.bean;

/**
 *
 * @author Gabriel_SA
 */
public class Itens_locacao {
    private boolean status;//ativo
    private int id,quantidade;//tipo_fk
    private String nome;

    
    public boolean getStatus() {
        return status;
    }
    

    public void setStatus(boolean status) {
        this.status = status;
    }

//    public boolean isAtivo() {
//        return ativo;
//    }
//
//    public void setAtivo(boolean ativo) {
//        this.ativo = ativo;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
//    public int getTipo_fk() {
//        return tipo_fk;
//    }
//
//    public void setTipo_fk(int tipo_fk) {
//        this.tipo_fk = tipo_fk;
//    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }  

    @Override
    public String toString() {
        return nome;
    }
    
    
}

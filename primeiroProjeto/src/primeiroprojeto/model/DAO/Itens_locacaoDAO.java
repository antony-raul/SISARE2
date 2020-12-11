package primeiroprojeto.model.DAO;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import primeiroprojeto.connection.ConnectionFactory;
import primeiroprojeto.model.bean.Itens_locacao;
/**
 *
 * @author Gabriel_SA
 */
public class Itens_locacaoDAO {
    public void create(Itens_locacao i) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO itens_locacao "
                + "(status,id,quantidade,nome) VALUES (?,?,?,?)");
            stmt.setBoolean(1,i.getStatus());
            //stmt.setBoolean(2,i.getAtivo());
            stmt.setInt(2,i.getId());
            stmt.setInt(3,i.getQuantidade());
            //stmt.setInt(5,i.getTipo_fk());
            stmt.setString(4,i.getNome());
                        
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao salvar!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
    public List<Itens_locacao> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Itens_locacao> itens = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(" SELECT * FROM itens_locacao");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Itens_locacao item = new Itens_locacao();
                item.setStatus(rs.getBoolean("status"));
                //item.setAtivo(rs.getBoolean("ativo"));
                item.setId(rs.getInt("id"));
                item.setQuantidade(rs.getInt("quantidade"));
                //item.setTipo_fk(rs.getInt("tipo_fk"));
                item.setNome(rs.getString("nome"));                    
                itens.add(item);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return itens;
    }
    
    public void update(Itens_locacao i) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE itens_locacao SET "
                + "status = ?,ativo = ?,quantidade = ?,tipo_fk = ?,nome = ? WHERE id = ?");
            stmt.setBoolean(1,i.getStatus());
            //stmt.setBoolean(2,i.getAtivo());            
            stmt.setInt(3,i.getQuantidade());
            //stmt.setInt(4,i.getTipo_fk());
            stmt.setString(5,i.getNome());
            stmt.setInt(6,i.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    public void delete(Itens_locacao i) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE * from Itens_locacao WHERE id = ?");
            stmt.setInt(1,i.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao exlcuir!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }    
}

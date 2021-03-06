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
                + "status = ?,quantidade = ?,nome = ? WHERE id = ?");
            stmt.setBoolean(1,i.getStatus());
            //stmt.setBoolean(2,i.getAtivo());            
            stmt.setInt(2,i.getQuantidade());
            //stmt.setInt(4,i.getTipo_fk());
            stmt.setString(3,i.getNome());
            stmt.setInt(4,i.getId());
            
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


    public void block(Itens_locacao i) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("UPDATE itens_locacao SET status = ?,quantidade = ?,nome = ? WHERE id = ?");
            stmt.setBoolean(1, false);
            stmt.setInt(2, i.getQuantidade());
            stmt.setString(3, i.getNome());
            stmt.setInt(4, i.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Bloqueado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao bloquear!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void desbloquear(Itens_locacao i) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("UPDATE itens_locacao SET status = ? WHERE id = ?");
            
            stmt.setBoolean(1, true);
            stmt.setInt(2, i.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Desbloqueado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao desbloquear!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public String selectItem(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String nomeItem = "";
        
        try {
            stmt = con.prepareStatement("SELECT * FROM itens_locacao WHERE id = ?");
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                nomeItem = rs.getString("nome");
            }
            
                      
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return nomeItem;
    }
    
    public int selectIDItem(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idItem;
        idItem = 0;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM itens_locacao WHERE nome = ?");
            stmt.setString(1,nome);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                idItem = rs.getInt("id");
            }
            
                      
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return idItem;
    }
    
    public int amountItens() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int size = 0;
        
        try {
            
            stmt = con.prepareStatement("SELECT COUNT(*) AS id FROM itens_locacao");
            rs = stmt.executeQuery();
            
            
            while(rs.next()){
                size = rs.getInt("id");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao contar!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

        return size;
    }
    
}

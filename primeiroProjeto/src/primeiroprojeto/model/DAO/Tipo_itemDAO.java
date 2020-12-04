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
import primeiroprojeto.model.bean.Tipo_item;
/**
 *
 * @author Gabriel_SA
 */
public class Tipo_itemDAO {
    public void create(Tipo_item t) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO tipo_item "
                + "(nome,tipo) VALUES (?,?)");
            stmt.setString(1,t.getNome());
            stmt.setInt(2,t.getTipo());
                        
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao salvar!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
    public List<Tipo_item> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Tipo_item> tipos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(" SELECT * FROM tipo_item");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Tipo_item tipo = new Tipo_item();
                tipo.setNome(rs.getString("nome"));
                tipo.setTipo(rs.getInt("tipo"));
                
                tipos.add(tipo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return tipos;
    }
    
    public void update(Tipo_item t) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE tipo_item SET "
                + "nome= ? WHERE tipo = ?");
            stmt.setString(1,t.getNome());
            stmt.setInt(2,t.getTipo());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    public void delete(Tipo_item t) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE * from tipo_item WHERE tipo = ?");
            stmt.setInt(1,t.getTipo());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao exlcuir!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
}

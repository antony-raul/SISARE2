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
import primeiroprojeto.model.bean.Publico_externo;
/**
 *
 * @author Gabriel_SA
 */
public class Publico_externoDAO {
    public void create(Publico_externo p) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO publico_externo "
                + "(numero_documento,id_resp) VALUES (?,?)");
            stmt.setString(1,p.getNumero_documento());
            stmt.setInt(2,p.getId_resp());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao salvar!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
    public List<Publico_externo> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Publico_externo> emprestimos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(" SELECT * FROM publico_externo");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Publico_externo emprestimo = new Publico_externo();
                emprestimo.setNumero_documento(rs.getString("numero_documento"));                
                emprestimo.setId_resp(rs.getInt("id_resp"));
                
                emprestimos.add(emprestimo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return emprestimos;
    }
    
    public void update(Publico_externo p) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE publico_externo SET "
                + "numero_documento ? WHERE id_resp = ?");
            stmt.setString(1,p.getNumero_documento());
            stmt.setInt(2,p.getId_resp());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    public void delete(Publico_externo p) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE * from publico_externo WHERE id = ?");
            stmt.setInt(1,p.getId_resp());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao exlcuir!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
}

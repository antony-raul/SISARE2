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
import primeiroprojeto.model.bean.Telefone;
/**
 *
 * @author Gabriel_SA
 */
public class TelefoneDAO {
    public void create(Telefone tl) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO telefone "
                + "(telefone,matricula_fk) VALUES (?,?)");
            stmt.setString(1,tl.getTelefone());
            stmt.setInt(2,tl.getMatricula_fk());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao salvar!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
    public List<Telefone> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Telefone> telefones = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(" SELECT * FROM telefone");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Telefone telefone = new Telefone();
                telefone.setTelefone(rs.getString("telfone"));
                telefone.setMatricula_fk(rs.getInt("matricula_fk"));
                
                telefones.add(telefone);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return telefones;
    }
    
    public void update(Telefone tl) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE telefone SET "
                + "telefone = ? WHERE matricula_fk = ?");
            stmt.setString(1,tl.getTelefone());
            stmt.setInt(2,tl.getMatricula_fk());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    public void delete(Telefone tl) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE * from telefone WHERE matricula_fk = ?");
            stmt.setInt(1,tl.getMatricula_fk());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao exlcuir!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
}

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
import primeiroprojeto.model.bean.Email_func;
/**
 *
 * @author Gabriel_SA
 */
public class Email_funcDAO {
    public void create(Email_func e) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO email_func "
                + "(email,matricula_fk) VALUES (?,?)");
            stmt.setString(1,e.getEmail());
            stmt.setInt(2,e.getMatricula_fk());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao salvar!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
    public List<Email_func> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Email_func> emails = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(" SELECT * FROM email_func");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Email_func email = new Email_func();
                email.setEmail(rs.getString("email"));                
                email.setMatricula_fk(rs.getInt("matricula_fk"));
                
                emails.add(email);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return emails;
    }
    
    public void update(Email_func e) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE email_func SET "
                + "email = ? WHERE matricula_fk = ?");
            stmt.setString(1,e.getEmail());
            stmt.setInt(2,e.getMatricula_fk());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    public void delete(Email_func e) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE * from email_func WHERE matricula_fk = ?");
            stmt.setInt(1,e.getMatricula_fk());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao exlcuir!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
}

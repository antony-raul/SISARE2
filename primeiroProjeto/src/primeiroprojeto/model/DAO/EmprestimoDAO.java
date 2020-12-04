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
import primeiroprojeto.model.bean.Emprestimo;
/*
 *
 * @author Gabriel_S4
 */
public class EmprestimoDAO {
    public void create(Emprestimo e) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO emprestimo "
                + "(situacao,data_emprestimo,hora_emprestimo,data_devolucao,id_resp_fk,id_item_loc,matricula_func_fk,id) VALUES (?,?,?,?,?,?,?,?)");
            stmt.setString(1,e.getSituacao());
            stmt.setString(2,e.getData_emprestimo());
            stmt.setString(3,e.getHora_emprestimo());
            stmt.setString(4,e.getData_devolucao());
            stmt.setInt(5,e.getId_resp_fk());
            stmt.setInt(6,e.getId_item_loc());
            stmt.setInt(7,e.getMatricula_func_fk());
            stmt.setInt(8,e.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao salvar!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
    public List<Emprestimo> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Emprestimo> emprestimos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(" SELECT * FROM emprestimo");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setSituacao(rs.getString("situacao"));
                emprestimo.setData_emprestimo(rs.getString("data_emprestimo"));
                emprestimo.setHora_emprestimo(rs.getString("hora_emprestimo"));
                emprestimo.setData_devolucao(rs.getString("data_devolucao"));
                emprestimo.setId_resp_fk(rs.getInt("id_resp_fk"));
                emprestimo.setId_item_loc(rs.getInt("id_item_loc"));
                emprestimo.setMatricula_func_fk(rs.getInt("matricula_func_fk"));
                emprestimo.setId(rs.getInt("id"));
                
                emprestimos.add(emprestimo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return emprestimos;
    }
    
    public void update(Emprestimo e) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE emprestimo SET "
                + "situacao = ?,data_emprestimo = ?,hora_emprestimo = ? ,data_devolucao = ? ,id_resp_fk,id_item_loc = ? ,matricula_func_fk = ? WHERE id = ?");
            stmt.setString(1,e.getSituacao());
            stmt.setString(2,e.getData_emprestimo());
            stmt.setString(3,e.getHora_emprestimo());
            stmt.setString(4,e.getData_devolucao());
            stmt.setInt(5,e.getId_resp_fk());
            stmt.setInt(6,e.getId_item_loc());
            stmt.setInt(7,e.getMatricula_func_fk());
            stmt.setInt(8,e.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    public void delete(Emprestimo e) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE * from emprestimo WHERE id = ?");
            stmt.setInt(1,e.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao exlcuir!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
 }
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
                + "(data_emprestimo,data_devolucao,id_resp_fk,id_item_loc,id_espaco_loc,matricula_func_fk,id,status) VALUES (?,?,?,?,?,?,?,?)");
            
            
            stmt.setDate(1, e.getData_emprestimo());
            stmt.setDate(2, e.getData_devolucao());
            stmt.setInt(3,e.getId_resp_fk());
            stmt.setInt(4,e.getId_item_loc());
            stmt.setInt(5, e.getId_item_loc());
            stmt.setInt(6,e.getMatricula_func_fk());
            stmt.setInt(7,e.getId());
            stmt.setBoolean(8, e.isStatus());
            
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
                
                emprestimo.setData_emprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setData_devolucao(rs.getDate("data_devolucao"));
                emprestimo.setId_resp_fk(rs.getInt("id_resp_fk"));
                emprestimo.setId_item_loc(rs.getInt("id_item_loc"));
                emprestimo.setId_item_loc(rs.getInt("id_espacos_loc"));
                emprestimo.setMatricula_func_fk(rs.getInt("matricula_func_fk"));
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setStatus(rs.getBoolean("status"));
                
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
                + "data_emprestimo = ?,data_devolucao = ? ,id_resp_fk,id_item_loc = ?,id_espaco_loc = ? ,matricula_func_fk = ?,status = ? WHERE id = ?");
            
            
            stmt.setDate(1, e.getData_emprestimo());
            stmt.setDate(2,e.getData_devolucao());
            stmt.setInt(3,e.getId_resp_fk());
            stmt.setInt(4,e.getId_item_loc());
            stmt.setInt(5, e.getId_espaco_loc());
            stmt.setInt(6,e.getMatricula_func_fk());
            stmt.setBoolean(7, e.isStatus());
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
            stmt = con.prepareStatement("DELETE FROM emprestimo WHERE id = ?");
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
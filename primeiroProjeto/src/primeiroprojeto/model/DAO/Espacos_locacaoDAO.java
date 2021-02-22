/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeiroprojeto.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import primeiroprojeto.connection.ConnectionFactory;
import primeiroprojeto.model.bean.Espacos_locacao;

/**
 *
 * @author kaio
 */
public class Espacos_locacaoDAO {
    public void create(Espacos_locacao e) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO espacos_locacao "
                + "(status,id,nome) VALUES (?,?,?)");
            stmt.setBoolean(1,e.isStatus());
            stmt.setInt(2,e.getId());
            stmt.setString(3,e.getNome());
                        
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao salvar!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
    public List<Espacos_locacao> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Espacos_locacao> espacos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM espacos_locacao");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Espacos_locacao espaco = new Espacos_locacao();
                espaco.setStatus(rs.getBoolean("status"));
                espaco.setId(rs.getInt("id"));
                espaco.setNome(rs.getString("nome"));                    
                espacos.add(espaco);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return espacos;
    }
    
    public void update(Espacos_locacao e) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE espacos_locacao SET id = ?, nome = ?, status = ? WHERE id = ?");
            stmt.setInt(1,e.getId());
            stmt.setString(2,e.getNome());
            stmt.setBoolean(3,e.isStatus());
            stmt.setInt(4,e.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    public void delete(Espacos_locacao e) {        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE * from espacos_locacao WHERE id = ?");
            stmt.setInt(1,e.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!!!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao exlcuir!!!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }


    public void block(Espacos_locacao e) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("UPDATE espacos_locacao SET nome = ?, status = ? WHERE id = ?");
            stmt.setString(1, e.getNome());
            stmt.setBoolean(2, false);
            stmt.setInt(3, e.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Bloqueado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao bloquear!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public int selectIDEspaco(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idEspaco;
        idEspaco = 0;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM espacos_locacao WHERE nome = ?");
            stmt.setString(1,nome);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                idEspaco = rs.getInt("id");
            }
            
                      
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return idEspaco;
    }
    
    public int amountEspacos() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int size = 0;
        
        try {
            
            stmt = con.prepareStatement("SELECT COUNT(*) as id FROM espacos_locacao");
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

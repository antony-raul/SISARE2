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
import primeiroprojeto.model.bean.Funcionario;

/**
 *
 * @author kaio
 */
public class FuncionarioDAO {
    public void create(Funcionario f) {
        Connection con = ConnectionFactory.getConnection;
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("INSERT INTO funcionario (matricula,nome,senha) VALUES (?,?,?)");
            stmt.setInt(1, f.getMatricula());
            stmt.setString(2, f.getNome());
            stmt.setString(3, f.getSenha());
            
            stmt.executeUpdate();
            
            System.out.println("salvo com sucesso");
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no cadastro!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Funcionario> read() {
        Connection con = ConnectionFactory.getConnection;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        
        List<Funcionario> funcionarios = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                
                funcionario.setMatricula(rs.getInt("matricula"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSenha(rs.getString("senha"));
                funcionarios.add(funcionario);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return funcionarios; 
    }
    
    public void update(Funcionario f) {
        Connection con = ConnectionFactory.getConnection;
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("UPDATE funcionario nome = ?, matricula = ?, senha = ? WHERE matricula = ?");
            stmt.setInt(1, f.getMatricula());
            stmt.setString(2, f.getNome());
            stmt.setString(3, f.getSenha());
            stmt.setInt(4, f.getMatricula());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Funcionario f) {
        Connection con = ConnectionFactory.getConnection;
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("DELETE FROM funcionario WHERE matricula = ?");
            stmt.setInt(1, f.getMatricula());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean checkLogin(String matricula,String senha){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean check = false;
        
        try{
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE matricula = ? and senha = ?");
            stmt.setString(1, matricula);
            stmt.setString(2,senha);
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        return check;
    }
}

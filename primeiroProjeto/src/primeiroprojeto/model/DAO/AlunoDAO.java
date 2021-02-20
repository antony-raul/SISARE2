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
import primeiroprojeto.model.bean.Aluno;

/**
 *
 * @author Pedro Henrique
 */
public class AlunoDAO {
    public void create(Aluno a) {
         Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("INSERT INTO aluno (matricula, nome, rua, curso, numero, ativo)VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, a.getMatricula());
            stmt.setString(2, a.getNome());
            stmt.setString(3, a.getRua());
            stmt.setString(4, a.getCurso());
            stmt.setInt(5, a.getNumero());
            stmt.setBoolean(6, a.isAtivo());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no cadastro!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Aluno> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        
        List<Aluno> alunos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM aluno");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Aluno aluno = new Aluno();
                
                aluno.setMatricula(rs.getInt("matricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setRua(rs.getString("rua"));
                aluno.setCurso(rs.getString("curso"));
                aluno.setNumero(rs.getInt("numero"));
                aluno.setAtivo(rs.getBoolean("ativo"));
                alunos.add(aluno);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return alunos; 
    }
    
    public int amount() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int size = 0;
        
        try {
            
            stmt = con.prepareStatement("SELECT COUNT(*) AS matricula FROM aluno");
            rs = stmt.executeQuery();
            
            
            while(rs.next()){
                size = rs.getInt("matricula");;
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao contar!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

        return size;
    }
    
    public void update(Aluno a) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("UPDATE aluno SET matricula = ?, nome = ?, rua = ?, curso = ?, numero = ?, ativo = ? WHERE matricula = ?");
            stmt.setInt(1, a.getMatricula());
            stmt.setString(2, a.getNome());
            stmt.setString(3, a.getRua());
            stmt.setString(4, a.getCurso());
            stmt.setInt(5, a.getNumero());
            stmt.setBoolean(6, a.isAtivo());
            stmt.setInt(7, a.getMatricula());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void block(Aluno a) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("UPDATE aluno SET ativo = ? WHERE matricula = ?");
            
            stmt.setBoolean(1, false);
            stmt.setInt(2, a.getMatricula());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Bloqueado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao bloquear!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void desbloquear(Aluno a) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("UPDATE aluno SET ativo = ? WHERE matricula = ?");
            
            stmt.setBoolean(1, true);
            stmt.setInt(2, a.getMatricula());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Desbloqueado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao desbloquear!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void blockAtraso(Aluno a) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("UPDATE aluno SET ativo = ? WHERE matricula = ?");
            
            stmt.setBoolean(1, false);
            stmt.setInt(2, a.getMatricula());
            
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Aluno a) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("DELETE FROM aluno WHERE matricula = ?");
            stmt.setInt(1, a.getMatricula());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
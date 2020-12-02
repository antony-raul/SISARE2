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
         Connection con = ConnectionFactory.getConnection;
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("INSTERT INTO aluno (matricula, nome, rua, ativo, id_resp_fk)VALUES(?, ?, ?, ?, ?)");
            stmt.setInt(1, a.getMatricula());
            stmt.setString(2, a.getNome());
            stmt.setString(3, a.getRua());
            stmt.setBoolean(4, a.getAtivo());
            stmt.setInt(5, a.getId_resp_fk());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no cadastro!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Aluno> read() {
        Connection con = ConnectionFactory.getConnection;
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
                aluno.setAtivo(rs.getBoolean("ativo"));
                aluno.setId_resp_fk(rs.getInt("id_resp_fk"));
                alunos.add(aluno);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return alunos; 
    }
    
    public void update(Aluno a) {
        Connection con = ConnectionFactory.getConnection;
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("UPDATE aluno matriula = ?, nome = ?, rua = ?, ativo = ?, id_resp_fk = ? WHERE matricula = ?");
            stmt.setInt(1, a.getMatricula());
            stmt.setString(2, a.getNome());
            stmt.setString(3, a.getRua());
            stmt.setBoolean(4, a.getAtivo());
            stmt.setInt(5, a.getId_resp_fk());
            stmt.setInt(6, a.getMatricula());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Aluno a) {
        Connection con = ConnectionFactory.getConnection;
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
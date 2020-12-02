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
import primeiroprojeto.model.bean.Responsavel;

/**
 *
 * @author Pedro Henrique
 */
public class ResponsavelDAO {
    public void create(Responsavel r) {
        Connection con = ConnectionFactory.getConnection;
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("INSTERT INTO responsavel (nome, id_rep)VALUES(?, ?)");
            stmt.setString(1, r.getNome());
            stmt.setInt(2, r.getId_resp());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no cadastro!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Responsavel> read() {
        Connection con = ConnectionFactory.getConnection;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        
        List<Responsavel> responsaveis = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM responsavel");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Responsavel responsavel = new Responsavel();
                
                responsavel.setId_resp(rs.getInt("id_resp"));
                responsavel.setNome(rs.getString("nome"));
                responsaveis.add(responsavel);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ResponsavelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return responsaveis; 
    }
    
    public void update(Responsavel r) {
        Connection con = ConnectionFactory.getConnection;
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("UPDATE responsavel nome = ?, id_resp = ? WHERE id_resp = ?");
            stmt.setString(1, r.getNome());
            stmt.setInt(2, r.getId_resp());
            stmt.setInt(2, r.getId_resp());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Responsavel r) {
        Connection con = ConnectionFactory.getConnection;
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("DELETE FROM responsavel WHERE id_resp = ?");
            stmt.setInt(1, r.getId_resp());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!"+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}

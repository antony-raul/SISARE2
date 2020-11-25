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
import java.util.logging.Level;
import java.util.logging.Logger;
import primeiroprojeto.connection.ConnectionFactory;
import primeiroprojeto.model.bean.User;

/**
 *
 * @author raulz
 */
public class UserDAO {
    
    public void create(User u){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt  = con.prepareStatement("INSERT INTO user (userName,pass)VALUES(?,?)");
            
            stmt.setString(1,u.getUserName());
            stmt.setString(2, u.getPass());
            
            stmt.executeUpdate();
            
            System.out.println("salvo com sucesso");
            
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar"+ex);
            
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean checkLogin(String userName,String pass){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean check = false;
        
        try{
            stmt = con.prepareStatement("SELECT * FROM user WHERE userName = ? and pass = ?");
            stmt.setString(1, userName);
            stmt.setString(2,pass);
            
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

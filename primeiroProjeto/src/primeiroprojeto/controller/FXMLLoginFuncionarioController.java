/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeiroprojeto.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import primeiroprojeto.model.DAO.FuncionarioDAO;
import primeiroprojeto.model.DAO.UserDAO;
import primeiroprojeto.model.bean.User;

/**
 *
 * @author raulz
 */
public class FXMLLoginFuncionarioController implements Initializable {
    
    @FXML
    private TextField matricula;
    @FXML
    private TextField senha;
    String matFuncLog;
    

    
    @FXML
    private void BotaoLoginFuc(ActionEvent event) throws IOException  {
        
        FuncionarioDAO  dao = new FuncionarioDAO();
        
        
        if(dao.checkLogin(matricula.getText() , senha.getText())){
            System.out.println(" Acesso permitido");
            
            matFuncLog = matricula.getText();
            
            
           
        Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLTelaPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setIconified(false);
        stage.setTitle("Tela Principal");
        stage.show();
        }else{
            System.out.println(" Acesso Negado");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falha no Login!");
            alert.setHeaderText("Ocorreu um erro");
            alert.setContentText("Usuario ou senha incorreta\nTente fazer login novamente ");
            alert.show();
        }
        
    }

    
    
    
    
    @FXML
    private void handleNovaJanela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLLoginAdmin.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setIconified(false);
        stage.setTitle("Login Admin");
        stage.show();
    }
    
    
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

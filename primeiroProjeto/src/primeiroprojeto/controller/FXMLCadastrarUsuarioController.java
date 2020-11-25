/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeiroprojeto.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import primeiroprojeto.model.DAO.UserDAO;
import primeiroprojeto.model.bean.User;

/**
 *
 * @author raulz
 */
public class FXMLCadastrarUsuarioController implements Initializable {
    
    @FXML
    private TextField userName;
    @FXML
    private TextField pass;
    private Stage stage;
    boolean flagConfirmacao = false;

    public boolean isFlagConfirmacao() {
        return flagConfirmacao;
    }

    public void setFlagConfirmacao(boolean flagConfirmacao) {
        this.flagConfirmacao = flagConfirmacao;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private void Botao(ActionEvent event) {
        
        User us = new User();
        UserDAO dao = new UserDAO();
        
        us.setUserName(userName.getText());
        us.setPass(pass.getText());
        dao.create(us);
        //Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //alert.setTitle("Cadastro de usuario!");
        //alert.setHeaderText("Usuario cadastrado com sucesso");
        //alert.setContentText(" ");
        //alert.show();
        setFlagConfirmacao(true);
        getStage().close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

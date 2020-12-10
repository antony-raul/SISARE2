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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import primeiroprojeto.model.DAO.FuncionarioDAO;
import primeiroprojeto.model.bean.Funcionario;

/**
 *
 * @author raulz
 */
public class FXMLCadastrarUsuarioController implements Initializable {
    @FXML
    private TextField nomeFunc;
    @FXML
    private TextField senhaFunc;
    @FXML
    private TextField matriculaFunc;
    @FXML
    private javafx.scene.control.Button btnCancelar;

    @FXML
    private void btnCreate(ActionEvent event) {
        
        Funcionario f = new Funcionario();
        FuncionarioDAO fdao = new FuncionarioDAO();
        
        f.setMatricula(Integer.parseInt(matriculaFunc.getText()));
        f.setNome(nomeFunc.getText());
        f.setSenha(senhaFunc.getText());
        
        fdao.create(f);
        
        nomeFunc.setText("");
        senhaFunc.setText("");
        matriculaFunc.setText("");
    }
    
    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
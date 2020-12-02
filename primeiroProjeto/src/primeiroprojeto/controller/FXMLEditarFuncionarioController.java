/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeiroprojeto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import primeiroprojeto.model.DAO.FuncionarioDAO;
import primeiroprojeto.model.bean.Funcionario;

/**
 *
 * @author kaio
 */
public class FXMLEditarFuncionarioController {
    @FXML
    private TextField nomeFunc;
    @FXML
    private TextField senhaFunc;
    @FXML
    private TextField matriculaFunc;
    
    @FXML
    private void btnUpdate(ActionEvent event){
        
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        
        Funcionario f = (Funcionario) stage.getUserData();
        FuncionarioDAO fdao = new FuncionarioDAO();
        
        f.setMatricula(Integer.parseInt(matriculaFunc.getText()));
        f.setNome(nomeFunc.getText());
        f.setSenha(senhaFunc.getText());
        
        fdao.update(f);
                
    }
}

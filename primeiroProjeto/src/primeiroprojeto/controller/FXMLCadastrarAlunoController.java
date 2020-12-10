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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import primeiroprojeto.model.DAO.AlunoDAO;
import primeiroprojeto.model.bean.Aluno;

/**
 * FXML Controller class
 *
 * @author kaio
 */
public class FXMLCadastrarAlunoController implements Initializable {
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField nomeAluno;
    @FXML
    private TextField cursoAluno;
    @FXML
    private TextField matriculaAluno;
    @FXML
    private TextField ruaAluno;
    @FXML
    private TextField numAluno;
    
    
    private void btnConfirmar(ActionEvent event) {
        
        Aluno aluno = new Aluno();
        AlunoDAO alunoDAO = new AlunoDAO();
        
        aluno.setMatricula(Integer.parseInt(matriculaAluno.getText()));
        aluno.setNome(nomeAluno.getText());
        aluno.setCurso(cursoAluno.getText());
        aluno.setRua(ruaAluno.getText());
        aluno.setNumero(Integer.parseInt(numAluno.getText()));
        
        
        //Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //alert.setTitle("Cadastro de usuario!");
        //alert.setHeaderText("Usuario cadastrado com sucesso");
        //alert.setContentText(" ");
        //alert.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private TextField nomeAluno;
    @FXML
    private TextField cursoAluno;
    @FXML
    private TextField matriculaAluno;
    @FXML
    private TextField ruaAluno;
    @FXML
    private TextField numAluno;
    @FXML
    private javafx.scene.control.Button btnCancelar;
  
    
    @FXML
    private void btnCadastrarAluno(ActionEvent event) {
        
        Aluno aluno = new Aluno();
        AlunoDAO alunoDAO = new AlunoDAO();
        
        aluno.setMatricula(Integer.parseInt(matriculaAluno.getText()));
        aluno.setNome(nomeAluno.getText());
        aluno.setCurso(cursoAluno.getText());
        aluno.setRua(ruaAluno.getText());
        aluno.setNumero(Integer.parseInt(numAluno.getText()));
        aluno.setAtivo(true);
        
        
        alunoDAO.create(aluno);
        
        nomeAluno.setText("");
        matriculaAluno.setText("");
        cursoAluno.setText("");
        numAluno.setText("");
        ruaAluno.setText("");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTelaPrincipal.fxml"));
        FXMLTelaPrincipalController controller =  loader.getController();
        controller.refreshTables();
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

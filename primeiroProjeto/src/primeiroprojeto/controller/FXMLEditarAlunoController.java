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
import javafx.scene.Node;
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
public class FXMLEditarAlunoController implements Initializable {
    @FXML
    private TextField nomeAluno;
    @FXML
    private TextField cursoAluno;
    @FXML
    private TextField ruaAluno;
    @FXML
    private TextField numAluno;
    @FXML
    private javafx.scene.control.Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void btnEditarAluno(ActionEvent event){
        
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        
        Aluno aluno = (Aluno) stage.getUserData();
        AlunoDAO alunoDAO = new AlunoDAO();
        
        aluno.setNome(nomeAluno.getText());
        aluno.setCurso(cursoAluno.getText());
        aluno.setRua(ruaAluno.getText());
        aluno.setNumero(Integer.parseInt(numAluno.getText()));
        
        alunoDAO.update(aluno);
        
        nomeAluno.setText("");
        numAluno.setText("");
        ruaAluno.setText("");
        cursoAluno.setText("");
                
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
}

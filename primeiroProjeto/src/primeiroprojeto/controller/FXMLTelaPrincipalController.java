/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeiroprojeto.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import primeiroprojeto.model.DAO.AlunoDAO;
import primeiroprojeto.model.bean.Aluno;

/**
 * FXML Controller class
 *
 * @author raulz
 */
public class FXMLTelaPrincipalController implements Initializable {
    @FXML
    private TableView<Aluno> tableView;
    @FXML
    private TableColumn<Aluno, String> nomeCol;
    @FXML
    private TableColumn<Aluno, Integer> matriculaCol;
    @FXML
    private TableColumn<Aluno, String> ruaCol;
    @FXML
    private TableColumn<Aluno, Integer> numeroCol;
    @FXML
    private TableColumn<Aluno, String> cursoCol;
    @FXML
    private TableColumn<Aluno, String> statusCol;
    
    ObservableList<Aluno> observableList;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAlunos();
    }
    
    private void loadAlunos() {
        nomeCol.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
        matriculaCol.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("matricula"));
        ruaCol.setCellValueFactory(new PropertyValueFactory<Aluno, String>("rua"));
        numeroCol.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("numero"));
        cursoCol.setCellValueFactory(new PropertyValueFactory<Aluno, String>("curso"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Aluno, String>("status"));
        
        AlunoDAO alunoDAO = new AlunoDAO();
        
        observableList = FXCollections.observableArrayList(alunoDAO.read());
        tableView.refresh();
        tableView.setItems(observableList);
    }
    
}

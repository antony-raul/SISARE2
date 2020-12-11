/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeiroprojeto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import primeiroprojeto.model.DAO.AlunoDAO;
import primeiroprojeto.model.DAO.Itens_locacaoDAO;
import primeiroprojeto.model.bean.Aluno;
import primeiroprojeto.model.bean.Itens_locacao;

/**
 * FXML Controller class
 *
 * @author raulz
 */
public class FXMLTelaPrincipalController implements Initializable {
    @FXML
    private TableView<Aluno> tableView;
    @FXML
    private TableView<Itens_locacao> MaterialView;
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
    private TableColumn<Aluno, Boolean> statusCol;
    @FXML
    private TableColumn<Itens_locacao, Integer> IDMatCol;

    @FXML
    private TableColumn<Itens_locacao, String> NomeMatCol;

    @FXML
    private TableColumn<Itens_locacao, Integer> QtdMatCol;
    
    ObservableList<Aluno> observableList;
    ObservableList<Itens_locacao> observableListMat;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAlunos();
        loadMateriais();
    }
    
    private void loadAlunos() {
        nomeCol.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
        matriculaCol.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("matricula"));
        ruaCol.setCellValueFactory(new PropertyValueFactory<Aluno, String>("rua"));
        numeroCol.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("numero"));
        cursoCol.setCellValueFactory(new PropertyValueFactory<Aluno, String>("curso"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Aluno, Boolean>("status"));
        
        AlunoDAO alunoDAO = new AlunoDAO();
        
        observableList = FXCollections.observableArrayList(alunoDAO.read());
        tableView.refresh();
        tableView.setItems(observableList);
    }
    
    @FXML
    private void handleJanelaCadastrarAluno() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLCadastrarAluno.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setIconified(false);
        stage.setTitle("Cadastrar Aluno");
        stage.show();
    }
    
    @FXML
    private void bloquearAluno(ActionEvent event) {
        Aluno selectedItem = tableView.getSelectionModel().getSelectedItem();
        AlunoDAO alunoDAO = new AlunoDAO();
        
        if (selectedItem != null) {   
            alunoDAO.block(selectedItem);
            tableView.refresh();
        }
        
    }
    
    @FXML
    private void handleJanelaEditarAluno(ActionEvent event) throws IOException {
        Aluno selectedItem = tableView.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) {
            Node node = (Node) event.getSource();
            
            Stage stage = (Stage) node.getScene().getWindow();
            
            Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLEditarAluno.fxml"));
            
            stage.setUserData(selectedItem);
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setIconified(false);
            stage.setTitle("Editar Usuário");
            stage.show();
        }
        
    }
    
    @FXML
    private void handleJanelaCadastrarMaterial() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLCadastrarMaterial.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setIconified(false);
        stage.setTitle("Cadastrar Material");
        stage.show();
    }
    
    private void loadMateriais() {
        //Itens_locacao
        IDMatCol.setCellValueFactory(new PropertyValueFactory<Itens_locacao, Integer>("id"));
        NomeMatCol.setCellValueFactory(new PropertyValueFactory<Itens_locacao, String>("nome"));
        QtdMatCol.setCellValueFactory(new PropertyValueFactory<Itens_locacao, Integer>("quantidade"));
        
        Itens_locacaoDAO itemDao = new Itens_locacaoDAO();
        observableListMat = FXCollections.observableArrayList(itemDao.read());
        MaterialView.refresh();
        MaterialView.setItems(observableListMat);
        
        
        
        
        
     
    }
    
}

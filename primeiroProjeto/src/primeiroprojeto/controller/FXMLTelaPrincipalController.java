/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeiroprojeto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import primeiroprojeto.model.DAO.EmprestimoDAO;
import primeiroprojeto.model.DAO.Espacos_locacaoDAO;
import primeiroprojeto.model.DAO.Itens_locacaoDAO;
import primeiroprojeto.model.bean.Aluno;
import primeiroprojeto.model.bean.Emprestimo;
import primeiroprojeto.model.bean.Espacos_locacao;
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
    private TableView<Espacos_locacao> espacosTableView;
    @FXML
    private TableView<Emprestimo> emprestimosTableView;
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
    
    @FXML
    private TableColumn<Espacos_locacao, Integer> idEspacCol;
    @FXML
    private TableColumn<Espacos_locacao, String> nomeEspacCol;
    @FXML
    private TableColumn<Espacos_locacao, Boolean> statusEspacCol;
    
    @FXML
    private TableColumn<Emprestimo, Integer> matEmprestCol;
    @FXML
    private TableColumn<Emprestimo, Date> dataEmprestCol;
    @FXML
    private TableColumn<Emprestimo, Boolean> statusEmprestCol;
    
    ObservableList<Aluno> observableList;
    ObservableList<Itens_locacao> observableListMat;
    ObservableList<Espacos_locacao> observableListEspacos;
    ObservableList<Emprestimo> observableListEmprestimos;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAlunos();
        loadMateriais();
        loadEspacos();
        loadEmprestimos();
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
        Aluno selectedAluno = tableView.getSelectionModel().getSelectedItem();
        AlunoDAO alunoDAO = new AlunoDAO();
        
        if (selectedAluno != null) {   
            alunoDAO.block(selectedAluno);
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
    
    
    @FXML
    private void handleJanelaEditarMaterial(ActionEvent event) throws IOException {
        Itens_locacao selectedItem = MaterialView.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) {
            Node node = (Node) event.getSource();
            
            Stage stage = (Stage) node.getScene().getWindow();
            
            Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLEditarMaterial.fxml"));
            
            stage.setUserData(selectedItem);
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setIconified(false);
            stage.setTitle("Editar Material");
            stage.show();
        }
        
    }
    
    @FXML
    private void bloquearItem(ActionEvent event) {
        Itens_locacao selectedItem = MaterialView.getSelectionModel().getSelectedItem();
        Itens_locacaoDAO itemDAO = new Itens_locacaoDAO();
        
        if (selectedItem != null) {   
            itemDAO.block(selectedItem);
            MaterialView.refresh();
        }
        
    }
    
    private void loadEspacos() {
        idEspacCol.setCellValueFactory(new PropertyValueFactory<Espacos_locacao, Integer>("id"));
        nomeEspacCol.setCellValueFactory(new PropertyValueFactory<Espacos_locacao, String>("nome"));
        statusEspacCol.setCellValueFactory(new PropertyValueFactory<Espacos_locacao, Boolean>("status"));
        
        Espacos_locacaoDAO espacoDAO = new Espacos_locacaoDAO();
        
        observableListEspacos = FXCollections.observableArrayList(espacoDAO.read());
        tableView.refresh();
        espacosTableView.setItems(observableListEspacos);
    }
    
    @FXML
    private void bloquearEspaco(ActionEvent event) {
        Espacos_locacao selectedEspaco = espacosTableView.getSelectionModel().getSelectedItem();
        Espacos_locacaoDAO espacoDAO = new Espacos_locacaoDAO();
        
        if (selectedEspaco != null) {   
            espacoDAO.block(selectedEspaco);
            espacosTableView.refresh();
        }
        
    }
    
    @FXML
    private void handleJanelaCadastrarEspaco() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLCadastrarEspaco.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setIconified(false);
        stage.setTitle("Cadastrar Espaço");
        stage.show();
    }
    
    @FXML
    private void handleJanelaEditarEspaco(ActionEvent event) throws IOException {
        Espacos_locacao selectedItem = espacosTableView.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) {
            Node node = (Node) event.getSource();
            
            Stage stage = (Stage) node.getScene().getWindow();
            
            Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLEditarEspaco.fxml"));
            
            stage.setUserData(selectedItem);
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setIconified(false);
            stage.setTitle("Editar Espaço");
            stage.show();
        }
        
    }
    
    @FXML
    private void handleJanelaCadastrarEmprestimo() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLCadastrarEmprestimo.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setIconified(false);
        stage.setTitle("Cadastrar Emprestimo");
        stage.show();
    }
    
    private void loadEmprestimos() {
        matEmprestCol.setCellValueFactory(new PropertyValueFactory<Emprestimo, Integer>("id_resp_fk"));
        dataEmprestCol.setCellValueFactory(new PropertyValueFactory<Emprestimo, Date>("data_devolucao"));
        statusEmprestCol.setCellValueFactory(new PropertyValueFactory<Emprestimo, Boolean>("status"));
        
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        
        observableListEmprestimos = FXCollections.observableArrayList(emprestimoDAO.read());
        tableView.refresh();
        emprestimosTableView.setItems(observableListEmprestimos);
    }
    
    @FXML
    private void deletarEmprestimo(ActionEvent event) {
        Emprestimo selectedItem = emprestimosTableView.getSelectionModel().getSelectedItem();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        
        if (selectedItem != null) {   
            emprestimoDAO.delete(selectedItem);
            emprestimosTableView.refresh();
        }
        
    }
    
    @FXML
    private void handleJanelaDetalhesEmprestimos(ActionEvent event) throws IOException {
        Emprestimo selectedItem = emprestimosTableView.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) {
            Node node = (Node) event.getSource();
            
            Stage stage = (Stage) node.getScene().getWindow();
            
            Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLDetalhesEmprestimo.fxml"));
            
            stage.setUserData(selectedItem);
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setIconified(false);
            stage.setTitle("Detalhes do Empréstimo");
            stage.show();
        }
        
    }
}

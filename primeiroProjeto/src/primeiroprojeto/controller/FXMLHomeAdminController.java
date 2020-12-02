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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import primeiroprojeto.model.DAO.FuncionarioDAO;
import primeiroprojeto.model.bean.Funcionario;

/**
 *
 * @author kaio
 */
public class FXMLHomeAdminController {
    @FXML
    private TableView<Funcionario> tableView;
    
    @FXML
    private TableColumn<Funcionario, String> nomeCol;
    
    @FXML
    private TableColumn<Funcionario, String> matriculaCol;


    
    public void initialize(URL location, ResourceBundle resources) {
        nomeCol.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
        matriculaCol.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("matricula"));

        tableView.getItems().setAll(funcionarioList());
    }
    
    private ObservableList<Funcionario> funcionarioList() {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        
        return FXCollections.observableArrayList(funcionarioDAO.read());
    }
    
    @FXML
    private void btnDelete(ActionEvent event) {
        Funcionario selectedItem = tableView.getSelectionModel().getSelectedItem();

        tableView.getItems().remove(selectedItem);
        
    }
    
    @FXML
    private void handleNovaJanela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLCadastrarUsuario.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setIconified(false);
        stage.setTitle("Cadastrar Usuário");
        stage.show();
    }
}
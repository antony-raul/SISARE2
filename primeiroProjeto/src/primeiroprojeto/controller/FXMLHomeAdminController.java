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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    @FXML
    private Button btnDelete;
    
    public void initialize(URL location, ResourceBundle resources) {
        nomeCol.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
        matriculaCol.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("matricula"));

        tableView.getItems().setAll(funcionarioList());
    }
    
    private ObservableList<Funcionario> funcionarioList() {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        
        return FXCollections.observableArrayList(funcionarioDAO.read());
    }
    
    public void deleteRow() {
        Funcionario selectedItem = tableView.getSelectionModel().getSelectedItem();
        
        btnDelete.setOnAction(e -> {
            tableView.getItems().remove(selectedItem);
        });
        
    }
}

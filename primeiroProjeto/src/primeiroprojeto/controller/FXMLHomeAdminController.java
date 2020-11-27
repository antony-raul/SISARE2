/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeiroprojeto.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import primeiroprojeto.model.DAO.FuncionarioDAO;
import primeiroprojeto.model.bean.Funcionario;


/**
 *
 * @author kaio
 */
public class FXMLHomeAdminController {
    
    @FXML
    private Button create;
    private Button update;
    private Button delete;
    
    @FXML private TableColumn nomeCol ;
    @FXML private TableColumn matriculaCol ;
   
    public void readTable(){
        FuncionarioDAO fdao = new FuncionarioDAO();
        
        for (Funcionario f : fdao.read()) {
            
            nomeCol.setCellValueFactory(new PropertyValueFactory("nome"));
            matriculaCol.setCellValueFactory(new PropertyValueFactory("matricula"));

        }
    } 
    
}

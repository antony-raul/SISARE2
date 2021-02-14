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
import primeiroprojeto.model.DAO.EmprestimoDAO;
import primeiroprojeto.model.bean.Emprestimo;

/**
 * FXML Controller class
 *
 * @author kaio
 */
public class FXMLDetalhesEmprestimoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadEmprestimo();
    }    
    
    private void loadEmprestimo(){
        //Node node = (Node) event.getSource();
        //Stage stage = (Stage) node.getScene().getWindow();
        
        //Emprestimo emprestimo = (Emprestimo) stage.getUserData();
        //EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
                
    }
}

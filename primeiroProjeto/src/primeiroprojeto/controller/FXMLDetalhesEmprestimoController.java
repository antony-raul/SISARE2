/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeiroprojeto.controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
    @FXML
    private TextField matricula;
    @FXML
    private TextField matriculaFunc;
    @FXML
    private TextField dataEntrega;
    @FXML
    private TextField dataEmprestimo;

    Emprestimo emprestimo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        matricula.setDisable(true);
        matricula.setStyle("-fx-opacity: 1;");
        matricula.setText(""+this.emprestimo.getId_resp_fk());
        
        matriculaFunc.setDisable(true);
        matriculaFunc.setStyle("-fx-opacity: 1;");
        matriculaFunc.setText(""+this.emprestimo.getMatricula_func_fk());
        
        dataEmprestimo.setDisable(true);
        dataEmprestimo.setStyle("-fx-opacity: 1;");
        dataEmprestimo.setText(""+this.emprestimo.getData_emprestimo());
        
        dataEntrega.setDisable(true);
        dataEntrega.setStyle("-fx-opacity: 1;");
        dataEntrega.setText(""+this.emprestimo.getData_devolucao());
        
        if (this.emprestimo.getId_espaco_loc() == 0) {
            
        }
    }    


    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }
    
    
}

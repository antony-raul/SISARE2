/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeiroprojeto.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import primeiroprojeto.model.DAO.EmprestimoDAO;
import primeiroprojeto.model.DAO.Itens_locacaoDAO;
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
    @FXML
    private TextField itenEspaco;
    @FXML
    private Button btnCancelar;

    Emprestimo emprestimo;
    
    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
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
            Itens_locacaoDAO itemDao = new Itens_locacaoDAO();
            int id = this.emprestimo.getId_item_loc();
            String nome = itemDao.selectItem(id);

            itenEspaco.setDisable(true);
            itenEspaco.setStyle("-fx-opacity: 1;");
            itenEspaco.setText(nome);
        }
        
    }
    
    

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }
    
    
}

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import primeiroprojeto.model.DAO.EmprestimoDAO;
import primeiroprojeto.model.bean.Emprestimo;

/**
 * FXML Controller class
 *
 * @author raulz
 */
public class FXMLCadastrarEmprestimoController implements Initializable {
    @FXML
    private TextField matricula;
    
    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<?> opMaterial;

    @FXML
    private DatePicker dataEntrega;

    @FXML
    private ComboBox<?> opEspacos;

    @FXML
    private RadioButton tipoMaterial;

    @FXML
    private RadioButton tipoEspaco;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btnResevar(ActionEvent event) {
        Emprestimo emprestimo = new Emprestimo();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        
        
        
        matricula.setText("");
        
                
        
    }
    
    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}

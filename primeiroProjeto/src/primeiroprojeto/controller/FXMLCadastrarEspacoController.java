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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import primeiroprojeto.model.DAO.Espacos_locacaoDAO;
import primeiroprojeto.model.bean.Espacos_locacao;

/**
 * FXML Controller class
 *
 * @author kaio
 */
public class FXMLCadastrarEspacoController implements Initializable {
    @FXML
    private TextField nomeEspaco;
    @FXML
    private TextField idEspaco;
    @FXML
    private javafx.scene.control.Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCadastrarEspaco(ActionEvent event) {
        Espacos_locacao espaco = new Espacos_locacao();
        Espacos_locacaoDAO espacosDAO = new Espacos_locacaoDAO();
        
        espaco.setId(Integer.parseInt(idEspaco.getText()));
        espaco.setNome(nomeEspaco.getText());
        espaco.setStatus(true);
        
        
        espacosDAO.create(espaco);
        
        nomeEspaco.setText("");
        idEspaco.setText("");
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
}

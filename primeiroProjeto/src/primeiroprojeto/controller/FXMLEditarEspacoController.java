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
import primeiroprojeto.model.DAO.Espacos_locacaoDAO;
import primeiroprojeto.model.bean.Espacos_locacao;

/**
 * FXML Controller class
 *
 * @author kaio
 */
public class FXMLEditarEspacoController implements Initializable {
    @FXML
    private Button btnConfirmar;
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
    private void btnEditarEspaco(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        
        Espacos_locacao espaco = (Espacos_locacao) stage.getUserData();
        Espacos_locacaoDAO espacoDAO = new Espacos_locacaoDAO();
        
        nomeEspaco.setText(espaco.getNome());
        
        espaco.setNome(nomeEspaco.getText());
        
        espacoDAO.update(espaco);
        
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
}

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
import primeiroprojeto.model.DAO.AlunoDAO;
import primeiroprojeto.model.DAO.Itens_locacaoDAO;
import primeiroprojeto.model.bean.Aluno;
import primeiroprojeto.model.bean.Itens_locacao;

/**
 * FXML Controller class
 *
 * @author kaio
 */
public class FXMLCadastrarMaterialController implements Initializable {
    @FXML
    private Button btnCancelar;

    @FXML
    private TextField nomeItem;

    @FXML
    private TextField QTD;

    @FXML
    private TextField ID;
  
    
    @FXML
    private void btnCadastrarMaterial(ActionEvent event) {
        
        Itens_locacao item = new Itens_locacao();
        Itens_locacaoDAO itemDAO = new Itens_locacaoDAO();
        
        item.setNome(nomeItem.getText());
        item.setQuantidade(Integer.parseInt(QTD.getText()));
        item.setId(Integer.parseInt(ID.getText()));
        item.setStatus(true);
        
        
        itemDAO.create(item);
        
        
        
 
    }
    
    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

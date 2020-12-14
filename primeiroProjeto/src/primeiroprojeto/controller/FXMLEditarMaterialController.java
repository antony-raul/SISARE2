/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeiroprojeto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import primeiroprojeto.model.DAO.Itens_locacaoDAO;
import primeiroprojeto.model.bean.Itens_locacao;

/**
 *
 * @author kaio
 */
public class FXMLEditarMaterialController {
    @FXML
    private Button btnCancelar;

    @FXML
    private TextField nomeItem;

    @FXML
    private TextField QTD;
    
    
    @FXML
    private void btnUpdate(ActionEvent event){
        
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        
        Itens_locacao i = (Itens_locacao) stage.getUserData();
        Itens_locacaoDAO idao = new Itens_locacaoDAO();
       
        
        i.setNome(nomeItem.getText());
        i.setQuantidade(Integer.parseInt(QTD.getText()));
        
        idao.update(i);
        
        nomeItem.setText("");
        QTD.setText("");
                
    }
    
    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}

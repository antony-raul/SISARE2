/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeiroprojeto.controller;

import static java.lang.String.format;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import primeiroprojeto.model.DAO.EmprestimoDAO;
import primeiroprojeto.model.DAO.Espacos_locacaoDAO;
import primeiroprojeto.model.DAO.Itens_locacaoDAO;
import primeiroprojeto.model.bean.Emprestimo;
import primeiroprojeto.model.bean.Espacos_locacao;
import primeiroprojeto.model.bean.Itens_locacao;

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
    private ComboBox<Itens_locacao> opMaterial;

    @FXML
    private DatePicker dataEntrega;

    @FXML
    private ComboBox<Espacos_locacao> opEspacos;

    @FXML
    private RadioButton tipoMaterial;

    @FXML
    private RadioButton tipoEspaco;
    
    @FXML
    private ToggleGroup tipo;
    
    //private List<Espacos_locacao> espacos = new ArrayList<>();
    ObservableList<Espacos_locacao> observableListEspacos;
    ObservableList<Itens_locacao> observableListMateriais;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarEspacos();
        carregarMaterias();
        
    }    
    
    @FXML
    private void btnResevar(ActionEvent event) throws ParseException {
        Emprestimo emprestimo = new Emprestimo();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        
        emprestimo.setData_emprestimo(getDateTime());
        emprestimo.setId_resp_fk(Integer.parseInt(matricula.getText()));
        emprestimo.setData_devolucao(getDatePicker());
        emprestimo.setId_item_loc(7);
        emprestimo.setMatricula_func_fk(201220);
        emprestimo.setId_espaco_loc(43);
        emprestimo.setId(5);
        
        emprestimoDAO.create(emprestimo);
        
        //matricula.setText("");
        
        
        //RadioButton radio = (RadioButton) tipo.getSelectedToggle();
        //System.out.println(radio.getText());
    }

    private java.sql.Date getDatePicker() {
        LocalDate localDate = dataEntrega.getValue();
        java.sql.Date dateSql = java.sql.Date.valueOf(localDate);

        return dateSql;
    }
    
    @FXML
    private java.sql.Date getDateTime(){
        Date date = new Date();
        java.sql.Date dateSql = new java.sql.Date(date.getTime());
        
        return dateSql;
    }
    
    @FXML
    private void carregarEspacos(){
        
        Espacos_locacaoDAO espacoDAO = new Espacos_locacaoDAO();
        
        observableListEspacos = FXCollections.observableArrayList(espacoDAO.read());
        
        opEspacos.setItems(observableListEspacos);
    }
    @FXML
    private void carregarMaterias(){
        
        Itens_locacaoDAO materialDAO = new Itens_locacaoDAO();
        
        observableListMateriais = FXCollections.observableArrayList(materialDAO.read());
        
        opMaterial.setItems(observableListMateriais);
    }
    
    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}

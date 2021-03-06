/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeiroprojeto.controller;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import primeiroprojeto.model.DAO.AlunoDAO;
import primeiroprojeto.model.DAO.EmprestimoDAO;
import primeiroprojeto.model.DAO.Espacos_locacaoDAO;
import primeiroprojeto.model.DAO.Itens_locacaoDAO;
import primeiroprojeto.model.bean.Aluno;
import primeiroprojeto.model.bean.Emprestimo;
import primeiroprojeto.model.bean.Espacos_locacao;
import primeiroprojeto.model.bean.Funcionario;
import primeiroprojeto.model.bean.Itens_locacao;

/**
 * FXML Controller class
 *
 * @author raulz
 */
public class FXMLTelaPrincipalController implements Initializable {
    @FXML
    private Text numAluno;
    @FXML
    private Text numEspacos;
    @FXML
    private Text numMateriais;
    @FXML
    private TableView<Aluno> tableView;
    @FXML
    private TableView<Itens_locacao> MaterialView;
    @FXML
    private TableView<Espacos_locacao> espacosTableView;
    @FXML
    private TableView<Emprestimo> emprestimosTableView;
    @FXML
    private TableView<Emprestimo> historicoTableView;
    @FXML
    private TableColumn<Aluno, String> nomeCol;
    @FXML
    private TableColumn<Aluno, Integer> matriculaCol;
    @FXML
    private TableColumn<Aluno, String> ruaCol;
    @FXML
    private TableColumn<Aluno, Integer> numeroCol;
    @FXML
    private TableColumn<Aluno, String> cursoCol;
    @FXML
    private TableColumn<Aluno, Boolean> statusCol;
    
    @FXML
    private TableColumn<Itens_locacao, Integer> IDMatCol;
    @FXML
    private TableColumn<Itens_locacao, String> NomeMatCol;
    @FXML
    private TableColumn<Itens_locacao, Integer> QtdMatCol;
    @FXML
    private TableColumn<Itens_locacao, Boolean> StatusMatCol;
    
    @FXML
    private TableColumn<Espacos_locacao, Integer> idEspacCol;
    @FXML
    private TableColumn<Espacos_locacao, String> nomeEspacCol;
    @FXML
    private TableColumn<Espacos_locacao, Boolean> statusEspacCol;
    
    @FXML
    private TableColumn<Emprestimo, Integer> matEmprestCol;
    @FXML
    private TableColumn<Emprestimo, Date> dataEmprestCol;
    @FXML
    private TableColumn<Emprestimo, Boolean> statusEmprestCol;
    
    @FXML
    private TableColumn<Emprestimo, Integer> matHistCol;
    @FXML
    private TableColumn<Emprestimo, Date> dataHistCol;
    @FXML
    private TableColumn<Emprestimo, Boolean> statusHistCol;
    
    
    ObservableList<Aluno> observableList;
    ObservableList<Itens_locacao> observableListMat;
    ObservableList<Espacos_locacao> observableListEspacos;
    ObservableList<Emprestimo> observableListEmprestimos;
    ObservableList<Emprestimo> observableListHistorico;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAlunos();
        loadMateriais();
        loadEspacos();
        loadEmprestimos();
        loadHistorico();
        numberOf();
    }
    
    public void refreshTables() {
        tableView.getItems().clear();
        loadAlunos();
        tableView.refresh();
        //numberOf();
        
        /*MaterialView.refresh();
        emprestimosTableView.refresh();
        espacosTableView.refresh();*/
    }
    
    private void loadAlunos() {
        nomeCol.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
        matriculaCol.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("matricula"));
        ruaCol.setCellValueFactory(new PropertyValueFactory<Aluno, String>("rua"));
        numeroCol.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("numero"));
        cursoCol.setCellValueFactory(new PropertyValueFactory<Aluno, String>("curso"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Aluno, Boolean>("ativo"));
        
        AlunoDAO alunoDAO = new AlunoDAO();
        
        observableList = FXCollections.observableArrayList(alunoDAO.read());
        tableView.setItems(observableList);
    }
    
    private void numberOf() {
        AlunoDAO alunoDAO = new AlunoDAO();
        Itens_locacaoDAO itens_locacaoDAO = new Itens_locacaoDAO();
        Espacos_locacaoDAO espacoDAO = new Espacos_locacaoDAO();
        
        numAluno.setText(""+alunoDAO.amountAlunos());
        numMateriais.setText(""+itens_locacaoDAO.amountItens());
        numEspacos.setText(""+espacoDAO.amountEspacos());
    }

    
    @FXML
    private void handleJanelaCadastrarAluno() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLCadastrarAluno.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setIconified(false);
        stage.setTitle("Cadastrar Aluno");
        stage.show();
    }
    
    @FXML
    private void bloquearAluno(ActionEvent event) {
        Aluno selectedAluno = tableView.getSelectionModel().getSelectedItem();
        AlunoDAO alunoDAO = new AlunoDAO();
        
        if (selectedAluno != null) {   
            alunoDAO.block(selectedAluno);
            tableView.refresh();
        }
        
    }
    
    @FXML
    private void desbloquearAluno(ActionEvent event) {
        Aluno selectedAluno = tableView.getSelectionModel().getSelectedItem();
        AlunoDAO alunoDAO = new AlunoDAO();
        
        if (selectedAluno != null) {   
            alunoDAO.desbloquear(selectedAluno);
            tableView.refresh();
        }
        
    }
    
    @FXML
    private void handleJanelaEditarAluno(ActionEvent event) throws IOException {
        Aluno selectedItem = tableView.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) {
            Node node = (Node) event.getSource();
            
            Stage stage = (Stage) node.getScene().getWindow();
            
            Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLEditarAluno.fxml"));
            
            stage.setUserData(selectedItem);
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setIconified(false);
            stage.setTitle("Editar Usuário");
            stage.show();
        }
        
    }
    
    @FXML
    private void handleJanelaCadastrarMaterial() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLCadastrarMaterial.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setIconified(false);
        stage.setTitle("Cadastrar Material");
        stage.show();
    }
    
    private void loadMateriais() {
        //Itens_locacao
        IDMatCol.setCellValueFactory(new PropertyValueFactory<Itens_locacao, Integer>("id"));
        NomeMatCol.setCellValueFactory(new PropertyValueFactory<Itens_locacao, String>("nome"));
        QtdMatCol.setCellValueFactory(new PropertyValueFactory<Itens_locacao, Integer>("quantidade"));
        StatusMatCol.setCellValueFactory(new PropertyValueFactory<Itens_locacao, Boolean>("status"));
        
        Itens_locacaoDAO itemDao = new Itens_locacaoDAO();
        observableListMat = FXCollections.observableArrayList(itemDao.read());
        MaterialView.setItems(observableListMat);
        
    }
    
    
    @FXML
    private void handleJanelaEditarMaterial(ActionEvent event) throws IOException {
        Itens_locacao selectedItem = MaterialView.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) {
            Node node = (Node) event.getSource();
            
            Stage stage = (Stage) node.getScene().getWindow();
            
            Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLEditarMaterial.fxml"));
            
            stage.setUserData(selectedItem);
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setIconified(false);
            stage.setTitle("Editar Material");
            stage.show();
        }
        
    }
    
    @FXML
    private void bloquearItem(ActionEvent event) {
        Itens_locacao selectedItem = MaterialView.getSelectionModel().getSelectedItem();
        Itens_locacaoDAO itemDAO = new Itens_locacaoDAO();
        
        if (selectedItem != null) {   
            itemDAO.block(selectedItem);
            MaterialView.refresh();
        }
        
    }
    
    @FXML
    private void desbloquearItem(ActionEvent event) {
        Itens_locacao selectedItem = MaterialView.getSelectionModel().getSelectedItem();
        Itens_locacaoDAO itemDAO = new Itens_locacaoDAO();
        
        if (selectedItem != null) {   
            itemDAO.desbloquear(selectedItem);
            tableView.refresh();
        }
        
    }
    
    private void loadEspacos() {
        idEspacCol.setCellValueFactory(new PropertyValueFactory<Espacos_locacao, Integer>("id"));
        nomeEspacCol.setCellValueFactory(new PropertyValueFactory<Espacos_locacao, String>("nome"));
        statusEspacCol.setCellValueFactory(new PropertyValueFactory<Espacos_locacao, Boolean>("status"));
        
        Espacos_locacaoDAO espacoDAO = new Espacos_locacaoDAO();
        
        observableListEspacos = FXCollections.observableArrayList(espacoDAO.read());
        espacosTableView.setItems(observableListEspacos);
    }
    
    @FXML
    private void bloquearEspaco(ActionEvent event) {
        Espacos_locacao selectedEspaco = espacosTableView.getSelectionModel().getSelectedItem();
        Espacos_locacaoDAO espacoDAO = new Espacos_locacaoDAO();
        
        if (selectedEspaco != null) {   
            espacoDAO.block(selectedEspaco);
            espacosTableView.refresh();
        }
        
    }
    
    @FXML
    private void desbloquearEspaco(ActionEvent event) {
        Espacos_locacao selectedEspaco = espacosTableView.getSelectionModel().getSelectedItem();
        Espacos_locacaoDAO espacoDAO = new Espacos_locacaoDAO();
        
        if (selectedEspaco != null) {   
            espacoDAO.desbloquear(selectedEspaco);
            espacosTableView.refresh();
        }
        
    }
    
    @FXML
    private void handleJanelaCadastrarEspaco() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLCadastrarEspaco.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setIconified(false);
        stage.setTitle("Cadastrar Espaço");
        stage.show();
    }
    
    @FXML
    private void handleJanelaEditarEspaco(ActionEvent event) throws IOException {
        Espacos_locacao selectedItem = espacosTableView.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) {
            Node node = (Node) event.getSource();
            
            Stage stage = (Stage) node.getScene().getWindow();
            
            Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLEditarEspaco.fxml"));
            
            stage.setUserData(selectedItem);
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setIconified(false);
            stage.setTitle("Editar Espaço");
            stage.show();
        }
        
    }
    
    @FXML
    private void handleJanelaCadastrarEmprestimo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/primeiroprojeto/view/FXMLCadastrarEmprestimo.fxml"));
        
        Node node = (Node) event.getSource();
        Stage stg = (Stage) node.getScene().getWindow();
        
        Funcionario funcionario = (Funcionario) stg.getUserData();
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setUserData(funcionario);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setIconified(false);
        stage.setTitle("Cadastrar Emprestimo");
        stage.show();
    }
    
    private void loadEmprestimos() {
        matEmprestCol.setCellValueFactory(new PropertyValueFactory<Emprestimo, Integer>("id_resp_fk"));
        dataEmprestCol.setCellValueFactory(new PropertyValueFactory<Emprestimo, Date>("data_devolucao"));
        statusEmprestCol.setCellValueFactory(new PropertyValueFactory<Emprestimo, Boolean>("status"));
        
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        Clock cl = Clock.systemUTC();
        LocalDate lt = LocalDate.now();
        
        Date date = new Date();
        Aluno aluno = new Aluno();
        AlunoDAO alunoDao = new AlunoDAO();
        
        
        
        observableListEmprestimos = FXCollections.observableArrayList(emprestimoDAO.read());
        observableListEmprestimos.forEach(emprestimo->{
            
            if(emprestimo.getData_devolucao().before(date)) {
                    
                    aluno.setMatricula(emprestimo.getId_resp_fk());
                    alunoDao.blockAtraso(aluno);
            }
            return ;
        }
            
        );
        
        emprestimosTableView.setItems(observableListEmprestimos);
    }
    
    @FXML
    private void deletarEmprestimo(ActionEvent event) {
        Emprestimo selectedItem = emprestimosTableView.getSelectionModel().getSelectedItem();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        
        if (selectedItem != null) {   
            emprestimoDAO.delete(selectedItem);
            emprestimosTableView.refresh();
        }
        
    }
    
    @FXML
    private void devolverEmprestimo(ActionEvent event){
        Emprestimo selectedEmprestimo = emprestimosTableView.getSelectionModel().getSelectedItem();
        EmprestimoDAO emprestimoDao = new EmprestimoDAO();
        
        if (selectedEmprestimo != null) {   
            emprestimoDao.block(selectedEmprestimo);
            emprestimosTableView.refresh();
        }
    }
    
    @FXML
    private void handleJanelaDetalhesEmprestimos(ActionEvent event) throws IOException {
        Emprestimo selectedItem = emprestimosTableView.getSelectionModel().getSelectedItem();
        
        
        if (selectedItem != null) {
            Node node = (Node) event.getSource();
            
            Stage stage = (Stage) node.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(getClass().getResource("/primeiroprojeto/view/FXMLDetalhesEmprestimo.fxml"));
            
            FXMLDetalhesEmprestimoController controller = new FXMLDetalhesEmprestimoController();
            
            controller.setEmprestimo(selectedItem);
            
            loader.setController(controller);
            
            Parent root = loader.load();
            
            stage.setUserData(selectedItem);
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setIconified(false);
            stage.setTitle("Detalhes do Empréstimo");
            stage.show();
            
        }
        
    }
    
    private void loadHistorico() {
        matHistCol.setCellValueFactory(new PropertyValueFactory<Emprestimo, Integer>("id_resp_fk"));
        dataHistCol.setCellValueFactory(new PropertyValueFactory<Emprestimo, Date>("data_devolucao"));
        statusHistCol.setCellValueFactory(new PropertyValueFactory<Emprestimo, Boolean>("status"));
        
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

        observableListHistorico = FXCollections.observableArrayList(emprestimoDAO.readHistorico());
        historicoTableView.getSortOrder().add(dataHistCol);
        historicoTableView.setItems(observableListHistorico);
    }
    
    @FXML
    private void handleJanelaDetalhesEmprestimosHist(ActionEvent event) throws IOException {
        Emprestimo selectedItem = historicoTableView.getSelectionModel().getSelectedItem();
        
        
        if (selectedItem != null) {
            Node node = (Node) event.getSource();
            
            Stage stage = (Stage) node.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(getClass().getResource("/primeiroprojeto/view/FXMLDetalhesEmprestimo.fxml"));
            
            FXMLDetalhesEmprestimoController controller = new FXMLDetalhesEmprestimoController();
            
            controller.setEmprestimo(selectedItem);
            
            loader.setController(controller);
            
            Parent root = loader.load();
            
            stage.setUserData(selectedItem);
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setIconified(false);
            stage.setTitle("Detalhes do Empréstimo");
            stage.show();
            
        }
        
    }
    
    @FXML
    private void gerarRelatorio(ActionEvent event) {
        Document doc = new Document();

        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:/Users/Public/Documents/Relatorio.pdf"));
            
            doc.open();
            
            Font font1 = FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD);
            Paragraph chapterTitle = new Paragraph("Relatório de Empréstimos", font1);
            Chapter chapter = new Chapter(chapterTitle, 1);
            chapter.setNumberDepth(0);
            doc.add(chapter);
            
            doc.add( Chunk.NEWLINE );
            Paragraph sectionTable1 = new Paragraph("Empréstimos Ativos");
            Section section = chapter.addSection(sectionTable1);
            doc.add(section);
            doc.add( Chunk.NEWLINE );
            
            List<Emprestimo> emprestimos = new EmprestimoDAO().read();
            List<Emprestimo> emprestimosFalse = new EmprestimoDAO().readHistorico();
            
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell(new Paragraph("Aluno"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Matrícula"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell cell3 = new PdfPCell(new Paragraph("Data de Empréstimo"));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell cell4 = new PdfPCell(new Paragraph("Data de Devolução"));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell cell5 = new PdfPCell(new Paragraph("Material/Espaço"));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            
            
            //tabela finalizado
            
            PdfPTable tableFinal = new PdfPTable(5);
            tableFinal.setWidthPercentage(100);
            
            
            tableFinal.addCell(cell1);
            tableFinal.addCell(cell2);
            tableFinal.addCell(cell3);
            tableFinal.addCell(cell4);
            tableFinal.addCell(cell5);
            
            AlunoDAO alunoDao = new AlunoDAO(); 
            Itens_locacaoDAO itensDao = new Itens_locacaoDAO();
            Espacos_locacaoDAO espacoDao = new Espacos_locacaoDAO();
            
            for(int x=0; x<emprestimos.size(); x++) {
                String nomeAluno = alunoDao.selectNomeAluno(emprestimos.get(x).getId_resp_fk());
                doc.add(table.addCell(new PdfPCell((new Paragraph(nomeAluno)))));

                String matriculaAluno = ""+emprestimos.get(x).getId_resp_fk();
                doc.add(table.addCell(new PdfPCell((new Paragraph(matriculaAluno)))));


                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
                String dataEmprestimo = dateFormat.format(emprestimos.get(x).getData_emprestimo());  
                doc.add(table.addCell(new PdfPCell((new Paragraph(dataEmprestimo)))));

                String dataDevolucao = dateFormat.format(emprestimos.get(x).getData_devolucao());  
                doc.add(table.addCell(new PdfPCell((new Paragraph(dataDevolucao)))));

                if(emprestimos.get(x).getId_espaco_loc() == 0){
                    String nomeItem = itensDao.selectItem(emprestimos.get(x).getId_item_loc());
                    doc.add(table.addCell(new PdfPCell((new Paragraph(nomeItem)))));
                }else{
                    String nomeEspaco = espacoDao.selectNomeEspaco(emprestimos.get(x).getId_espaco_loc());
                    doc.add(table.addCell(new PdfPCell((new Paragraph(nomeEspaco)))));
                }
            }
            
            
            for(int x=0; x<emprestimosFalse.size(); x++) {
                String nomeAluno = alunoDao.selectNomeAluno(emprestimos.get(x).getId_resp_fk());
                doc.add(tableFinal.addCell(new PdfPCell((new Paragraph(nomeAluno)))));

                String matriculaAluno = ""+emprestimos.get(x).getId_resp_fk();
                doc.add(tableFinal.addCell(new PdfPCell((new Paragraph(matriculaAluno)))));


                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
                String dataEmprestimo = dateFormat.format(emprestimos.get(x).getData_emprestimo());  
                doc.add(tableFinal.addCell(new PdfPCell((new Paragraph(dataEmprestimo)))));

                String dataDevolucao = dateFormat.format(emprestimos.get(x).getData_devolucao());  
                doc.add(tableFinal.addCell(new PdfPCell((new Paragraph(dataDevolucao)))));

                if(emprestimos.get(x).getId_espaco_loc() == 0){
                    String nomeItem = itensDao.selectItem(emprestimos.get(x).getId_item_loc());
                    doc.add(tableFinal.addCell(new PdfPCell((new Paragraph(nomeItem)))));
                }else{
                    String nomeEspaco = espacoDao.selectNomeEspaco(emprestimos.get(x).getId_espaco_loc());
                    doc.add(tableFinal.addCell(new PdfPCell((new Paragraph(nomeEspaco)))));
                }
            }

            doc.add(table);
            doc.add( Chunk.NEWLINE );
            
            Paragraph sectionTable2 = new Paragraph("Empréstimos Finalizados");
            Section section2 = chapter.addSection(sectionTable2);
            doc.add(section2);
            doc.add( Chunk.NEWLINE );
            doc.add(tableFinal);
       
            doc.close();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Relatório Gerado com Sucesso na Pasta de Documentos Públicos");
            alert.show();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLTelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FXMLTelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
    
}

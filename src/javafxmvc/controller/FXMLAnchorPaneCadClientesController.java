/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxmvc.model.dao.ClienteDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.Cliente;

/**
 * FXML Controller class
 *
 * @author INFORMATICA
 */
public class FXMLAnchorPaneCadClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<Cliente, String> tableNome;
    @FXML
    private TableColumn<Cliente, String> tableCpf;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnRemover;
    @FXML
    private Label lblCodCliente;
    @FXML
    private Label lblNomeCliente;
    @FXML
    private Label lblCpfCliente;
    @FXML
    private Label lblTelCliente;
    
    private List<Cliente> listClientes;
    private ObservableList<Cliente> obsListClientes;
    
    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        carregarTableViewClientes();
        
        tableViewClientes.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> selcionarItemClientes(newValue));
    }
    
    public void carregarTableViewClientes(){
        tableNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        listClientes = clienteDAO.listar();
        
        obsListClientes = FXCollections.observableArrayList(listClientes);
        tableViewClientes.setItems(obsListClientes);
    }

    public void selcionarItemClientes(Cliente cliente) {
        if(cliente != null){
            lblCodCliente.setText(String.valueOf(cliente.getCdCliente()));
            lblNomeCliente.setText(cliente.getNome());
            lblCpfCliente.setText(cliente.getCpf());
            lblTelCliente.setText(cliente.getTelefone());
        }else{
            lblCodCliente.setText("");
            lblNomeCliente.setText("");
            lblCpfCliente.setText("");
            lblTelCliente.setText("");
        }
    }
    
    @FXML
    public void handleBtnInserir() throws IOException {
        Cliente cliente = new Cliente();
        boolean btnConfirmarClicked = showFXMLAnchorPaneCadClientesDialog(cliente);
        if (btnConfirmarClicked){
            clienteDAO.inserir(cliente);
            carregarTableViewClientes();
        }
    }
    
    @FXML
    public void handleBtnAlterar()throws IOException {
        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
        if (cliente != null){
            boolean btnConfirmarClicked = showFXMLAnchorPaneCadClientesDialog(cliente);
            if (btnConfirmarClicked){
                clienteDAO.alterar(cliente);
                carregarTableViewClientes();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na tabela!");
            alert.show();
        }
    }
    
    @FXML
    public void handleBtnRemover() throws IOException{
        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
        if (cliente != null){
            //Mensagem de confirma��o para apagar uma informa��o
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletar informação");
            alert.setHeaderText(null);
            alert.setContentText("Deseja apagar as informações do cliente?");

            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                clienteDAO.remover(cliente);
                carregarTableViewClientes();
            }else{
                alert.close();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na tabela!");
            alert.show();
        }
    }
    
    public boolean showFXMLAnchorPaneCadClientesDialog(Cliente cliente) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadClientesDialogController.class.getResource("/javafxmvc/view/FXMLAnchorPaneCadClientesDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        //Criando um Estágio de Dialogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Clientes");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //Setando o cliente no Controller.
        FXMLAnchorPaneCadClientesDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);
        
        //mostra o Dialog e espera até que o usuario feche
        dialogStage.showAndWait();
        
        return controller.isBtnConfirmarClicked();
    }
    
}
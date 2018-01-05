/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private void selcionarItemClientes(Cliente cliente) {
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
    
}

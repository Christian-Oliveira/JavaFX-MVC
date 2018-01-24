package javafxmvc.controller;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
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
import javafxmvc.model.dao.ItemDeVendaDAO;
import javafxmvc.model.dao.ProdutoDAO;
import javafxmvc.model.dao.VendaDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.Venda;

/**
 * FXML Controller class
 *
 * @author Christian
 */
public class FXMLAnchorPaneProcessosVendasController implements Initializable {

    @FXML
    private TableView<Venda> tabelVendas;
    @FXML
    private TableColumn<Venda, LocalDate> columnCodigo;
    @FXML
    private TableColumn<Venda, Integer> columnData;
    @FXML
    private TableColumn<Venda, Venda> columnCliente;
    @FXML
    private Label lblCodigo;
    @FXML
    private Label lblData;
    @FXML
    private Label lblValor;
    @FXML
    private Label lblPago;
    @FXML
    private Label lblCliente;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnRemover;
    
    private List<Venda> listVendas;
    private ObservableList<Venda> obsListVendas;
    
    //Atributos para manipulação de Bando de Dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final VendaDAO vendaDAO = new VendaDAO();
    private final ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vendaDAO.setConnection(connection);
        carregarTabelViewVendas();
    }   
    
    public void carregarTabelViewVendas(){
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("cdVenda"));
        columnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        columnCliente.setCellValueFactory(new PropertyValueFactory<>("id_cdCliente"));
        
        listVendas = vendaDAO.listar();
        
        obsListVendas = FXCollections.observableArrayList(listVendas);
        tabelVendas.setItems(obsListVendas);
    }
    
}

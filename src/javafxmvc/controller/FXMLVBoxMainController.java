package javafxmvc.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class FXMLVBoxMainController implements Initializable {

    @FXML
    private MenuItem cadClientes;
    @FXML
    private MenuItem proVendas;
    @FXML
    private MenuItem grafVendasMes;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuItem relProdutoEstoque;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void handleMenuItemCadClientes() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafxmvc/view/FXMLAnchorPaneCadClientes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
}

package javafxmvc.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLAnchorPaneCadClientesDialogController implements Initializable {

    @FXML
    private Label lblNomeCliente;
    @FXML
    private Label lblCpfCliente;
    @FXML
    private Label lblTelCliente;
    @FXML
    private TextField txtNomeCliente;
    @FXML
    private TextField txtCpfCliente;
    @FXML
    private TextField txtTelCliente;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}

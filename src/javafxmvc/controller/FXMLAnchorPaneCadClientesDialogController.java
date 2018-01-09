package javafxmvc.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxmvc.model.domain.Cliente;

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
    
    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    private Cliente cliente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    /**
     * @return the dialogStage
     */
    public Stage getDialogStage() {
        return dialogStage;
    }

    /**
     * @param dialogStage the dialogStage to set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * @return the btnConfirmarClicked
     */
    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    /**
     * @param btnConfirmarClicked the btnConfirmarClicked to set
     */
    public void setBtnConfirmarClicked(boolean btnConfirmarClicked) {
        this.btnConfirmarClicked = btnConfirmarClicked;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.txtNomeCliente.setText(cliente.getNome());
        this.txtCpfCliente.setText(cliente.getCpf());
        this.txtTelCliente.setText(cliente.getTelefone());
    }
    
    @FXML
    public void handleBtnConfirmar(){
        if (validarEntradaDados()){
            cliente.setNome(txtNomeCliente.getText());
            cliente.setCpf(txtCpfCliente.getText());
            cliente.setTelefone(txtTelCliente.getText());

            btnConfirmarClicked = true;
            dialogStage.close();
        }
    }
    
    @FXML
    public void handleBtnCancelar(){
        dialogStage.close();
    }
    
    private boolean validarEntradaDados() {
        String errorMessage = "";
        
        if (txtNomeCliente.getText() == null || txtNomeCliente.getText().length() == 0){
            errorMessage += "Nome Invalido!\n";
        }
        if (txtCpfCliente.getText() == null || txtCpfCliente.getText().length() == 0){
            errorMessage += "CPF Invalido!\n";
        }
        if (txtTelCliente .getText() == null || txtTelCliente.getText().length() == 0){
            errorMessage += "Telefone Invalido!\n";
        }
        
        if (errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos Invalidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}

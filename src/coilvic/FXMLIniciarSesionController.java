package coilvic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLIniciarSesionController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private PasswordField pfContraseña;
    @FXML
    private TextField tfNombreUsuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicBtnIniciarSesion(ActionEvent event) {
        //hola esto es una prueba atte tony jiji 
    }
    
}

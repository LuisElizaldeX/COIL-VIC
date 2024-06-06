/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 21/05/2024
* Descripción: Controlador para la página etapa de colaboracion
*/

package coilvic.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FXMLEtapasColaboracionController  
        extends FXMLPaginaPrincipalCoordinadorCOILController {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());
    }    
    
}

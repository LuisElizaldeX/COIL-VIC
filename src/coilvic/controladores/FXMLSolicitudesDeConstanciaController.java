/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 04/06/2024
* Descripción: Controlador de página de solicitudes de constancia
*/

package coilvic.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class FXMLSolicitudesDeConstanciaController 
    extends FXMLPaginaPrincipalCoordinadorCOILController{

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private TableView<?> tvSolicitudes;
    @FXML
    private TableColumn<?, ?> colColaboracion;
    @FXML
    private TableColumn<?, ?> colProfesor;
    @FXML
    private Button btnRevisarSolicitud;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());
    }    

    @FXML
    private void clicBtnRevisarSolicitud(ActionEvent event) {
        
    }
    
}

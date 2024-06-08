/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 04/06/2024
* Descripción: Controlador de página de solicitudes de constancia
*/

package coilvic.controladores;

import coilvic.modelo.pojo.SolicitudConstancia;
import coilvic.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLSolicitudesDeConstanciaController 
    extends FXMLPaginaPrincipalCoordinadorCOILController{

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private TableView<SolicitudConstancia> tvSolicitudes;
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
        SolicitudConstancia solicitud = 
                tvSolicitudes.getSelectionModel().getSelectedItem();
        if(solicitud != null){
            try { 
                FXMLLoader accesoControlador = new FXMLLoader(coilvic.COILVIC.class.getResource(
                "vistas/FXMLColaboracionCOIL.fxml"));
                Parent vista = accesoControlador.load();
                FXMLColaboracionCOILController colaboracion = accesoControlador.getController();
                //colaboracion.configurarDatosColaboracionCOIl(solicitud);
                Stage escenarioPrincipal = (Stage) btnRevisarSolicitud.getScene().getWindow();
                Scene escenaPrincipal = new Scene(vista); 
                escenarioPrincipal.setScene(escenaPrincipal);
                escenarioPrincipal.setTitle("Solicitud de constancia");
                escenarioPrincipal.show();
        }catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        } else {
            Utilidades.mostrarAlertaSimple("Selecciona una solicitud de constancia", ""
                    + "Para revisar una solicutd de constancia debes seleccionar una de la lista.", 
                    Alert.AlertType.WARNING);
        }
    }
    
}

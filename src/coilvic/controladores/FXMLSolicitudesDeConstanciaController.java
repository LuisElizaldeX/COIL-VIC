/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 04/06/2024
* Descripción: Controlador de página de solicitudes de constancia
*/

package coilvic.controladores;

import coilvic.modelo.dao.SolicitudConstanciaDAO;
import coilvic.modelo.pojo.SolicitudConstancia;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn colColaboracion;
    @FXML
    private TableColumn colProfesor;
    @FXML
    private Button btnRevisarSolicitud;
    
    private ObservableList<SolicitudConstancia> solicitudConstancia;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());
        configurarTabla();
        cargarDatosColaboracion();
    }

    private void configurarTabla(){
        colColaboracion.setCellValueFactory(new PropertyValueFactory("colaboracion"));
        colProfesor.setCellValueFactory(new PropertyValueFactory("profesorUV"));
    }
    
    private void cargarDatosColaboracion(){
        solicitudConstancia = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta =
                SolicitudConstanciaDAO.obtenerSolicitudesConstancia();
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            ArrayList<SolicitudConstancia> solicitudesConstanciaBD = 
                    (ArrayList<SolicitudConstancia>) respuesta.get("SolicitudConstancia");
            solicitudConstancia.addAll(solicitudesConstanciaBD);
            tvSolicitudes.setItems(solicitudConstancia);
        }else{
            Utilidades.mostrarAlertaSimple("Error", 
                    ""+respuesta.get(Constantes.KEY_MENSAJE),
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clicBtnRevisarSolicitud(ActionEvent event) {
        SolicitudConstancia solicitud = 
                tvSolicitudes.getSelectionModel().getSelectedItem();
        if(solicitud != null){
            try { 
                FXMLLoader accesoControlador = new FXMLLoader(coilvic.COILVIC.class.getResource(
                "vistas/FXMLSolicitudDeConstancia.fxml"));
                Parent vista = accesoControlador.load();
                FXMLSolicitudDeConstanciaController solicitudConstancia = accesoControlador.getController();
                solicitudConstancia.configurarDatosColaboracionCOIL(solicitud);
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

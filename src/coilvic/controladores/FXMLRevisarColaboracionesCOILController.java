/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 18/05/2024
* Descripción: Controlador para la página Revisar colaboraciones COIL
*/

package coilvic.controladores;

import coilvic.modelo.dao.OfertaColaboracionUVDAO;
import coilvic.modelo.pojo.OfertaColaboracionUV;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FXMLRevisarColaboracionesCOILController 
        extends FXMLPaginaPrincipalCoordinadorCOILController {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
    
    private ObservableList<OfertaColaboracionUV> ofertasColaboracionUV;
    
    @FXML
    private TableView<OfertaColaboracionUV> tvColaboraciones;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colExperienciaEducativa;
    @FXML
    private TableColumn colPeriodo;
    @FXML
    private Button btnRevisarColaboracion;
    @FXML
    private TableColumn colFechaInicio;
    @FXML
    private TableColumn colFechaFin;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());
        configurarTabla();
        cargarDatosColaboracion();
    }    
    
    private void configurarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colExperienciaEducativa.setCellValueFactory
            (new PropertyValueFactory("experienciaEducativa"));
        colPeriodo.setCellValueFactory(new PropertyValueFactory("periodo"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory("fechaFin"));
    }
    
    private void cargarDatosColaboracion(){
        ofertasColaboracionUV = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta =
                OfertaColaboracionUVDAO.obtenerOfertasColaboracionUV();
        
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            ArrayList<OfertaColaboracionUV> ofertasColaboracionUVBD = 
                    (ArrayList<OfertaColaboracionUV>) respuesta.get("OfertasColaboracionUV");
            ofertasColaboracionUV.addAll(ofertasColaboracionUVBD);
            tvColaboraciones.setItems(ofertasColaboracionUV);
        }else{
            Utilidades.mostrarAlertaSimple("Error", 
                    ""+respuesta.get(Constantes.KEY_MENSAJE),
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clicBtnRevisarOfertaColaboracionUV(ActionEvent event) {
        OfertaColaboracionUV ofertaSeleccionada = 
                tvColaboraciones.getSelectionModel().getSelectedItem();
        if(ofertaSeleccionada != null){
            try { 
                FXMLLoader accesoControlador = new FXMLLoader(coilvic.COILVIC.class.getResource(
                "vistas/FXMLColaboracionCOIL.fxml"));
                Parent vista = accesoControlador.load();
                FXMLColaboracionCOILController colaboracion = accesoControlador.getController();
                colaboracion.configurarDatosColaboracionCOIl(ofertaSeleccionada);
                Stage escenarioPrincipal = (Stage) btnRevisarColaboracion.getScene().getWindow();
                Scene escenaPrincipal = new Scene(vista); 
                escenarioPrincipal.setScene(escenaPrincipal);
                escenarioPrincipal.setTitle("Colaboracion COIL");
                escenarioPrincipal.show();
        }catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        } else {
            Utilidades.mostrarAlertaSimple("Selecciona una colaboracion", ""
                    + "Para revisar una colaboracion debes seleccionar una de la lista.", 
                    Alert.AlertType.WARNING);
        }
    }
    
}

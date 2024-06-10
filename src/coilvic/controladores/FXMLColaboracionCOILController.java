/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 18/05/2024
* Descripción: Controlador de página ColaboracionCOIL
*/

package coilvic.controladores;

import coilvic.modelo.dao.OfertaColaboracionUVDAO;
import coilvic.modelo.pojo.OfertaColaboracionUV;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLColaboracionCOILController extends FXMLPaginaPrincipalCoordinadorCOILController {
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
 
    OfertaColaboracionUV ofertaColaboracion;
    
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbDescripcion;
    @FXML
    private Label lbFechaInicio;
    @FXML
    private Label lbFechaFin;
    @FXML
    private Label lbProfesorUV;
    @FXML
    private Label lbExperienciaEducativa;
    @FXML
    private Label lbRegion;
    @FXML
    private Label lbAreaAcademica;
    @FXML
    private Label lbEstado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());
    }
    
    public void configurarDatosColaboracionCOIl(OfertaColaboracionUV ofertaColaboracion){
        this.ofertaColaboracion=ofertaColaboracion;
        lbNombre.setText(ofertaColaboracion.getNombre());
        lbDescripcion.setText(ofertaColaboracion.getDescripcion());
        lbExperienciaEducativa.setText(ofertaColaboracion.getExperienciaEducativa());
        lbProfesorUV.setText(ofertaColaboracion.getProfesorUV());
        lbAreaAcademica.setText(ofertaColaboracion.getNombreAreaAcademica());
        lbRegion.setText(ofertaColaboracion.getCampus());
        lbEstado.setText(ofertaColaboracion.getEstado());
        lbFechaInicio.setText(ofertaColaboracion.getFechaInicio());
        lbFechaFin.setText(ofertaColaboracion.getFechaFin());
    }
    
    private void cambiarVentana(){
        try{
            Stage escenarioPrincipal = (Stage) lbNombreSesion.getScene().getWindow();
            Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                    getResource("vistas/FXMLRevisarColaboracionesCOIL.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.setTitle("Revisar colaboraciones COIL");
            escenarioPrincipal.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @FXML
    private void clicBtnAprobarColaboracion(ActionEvent event) {
        HashMap<String, Object> respuesta = OfertaColaboracionUVDAO.aprobarColaboracion
            (ofertaColaboracion.getIdOfertaColaboracionUV());
        if( !(boolean)respuesta.get(Constantes.KEY_ERROR)){
            Utilidades.mostrarAlertaSimple("Colaboracion aprobada", "" + 
                    respuesta.get(Constantes.KEY_MENSAJE) , Alert.AlertType.INFORMATION);
            cambiarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al aprobar", "" + 
                    respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void clicBtnRechazarColaboracion(ActionEvent event) {
        HashMap<String, Object> respuesta = OfertaColaboracionUVDAO.rechazarColaboracion
            (ofertaColaboracion.getIdOfertaColaboracionUV());
        if( !(boolean)respuesta.get(Constantes.KEY_ERROR)){
            Utilidades.mostrarAlertaSimple("Colaboracion rechazada", "" + 
                    respuesta.get(Constantes.KEY_MENSAJE) , Alert.AlertType.INFORMATION);
            cambiarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al rechazar", "" + 
                    respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);

        }
    }
}


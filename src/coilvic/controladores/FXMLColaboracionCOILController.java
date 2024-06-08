/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 18/05/2024
* Descripción: Controlador de página ColaboracionCOIL
*/

package coilvic.controladores;

import coilvic.modelo.dao.OfertaColaboracionUVDAO;
import coilvic.modelo.pojo.CoordinadorCOIL;
import coilvic.modelo.pojo.OfertaColaboracionUV;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.SingletonCoordinadorCOIL;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLColaboracionCOILController extends FXMLPaginaPrincipalCoordinadorCOILController {
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private TextArea taNombre;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private TextArea taProfesorUV;
    @FXML
    private TextArea taExperienciaEducativa;
    @FXML
    private TextArea taPeriodo;
    @FXML
    private TextArea taEstado;
    
    OfertaColaboracionUV ofertaColaboracion;
    
    @FXML
    private Button btnAprobarColaboracion;
    @FXML
    private Button btnRechazarColaboracion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());
    }
    
    public void configurarDatosColaboracionCOIl(OfertaColaboracionUV ofertaColaboracion){
        this.ofertaColaboracion=ofertaColaboracion;
        taNombre.setText(ofertaColaboracion.getNombre());
        taDescripcion.setText(ofertaColaboracion.getDescripcion());
        taExperienciaEducativa.setText(ofertaColaboracion.getExperienciaEducativa());
        taProfesorUV.setText(ofertaColaboracion.getProfesorUV());
        //taPeriodo.setText(ofertaColaboracion.getPeriodo());
        taEstado.setText(ofertaColaboracion.getEstado());
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


/*
* Autor: Josué Melgarejo García
* Fecha de creación: 07/06/2024
* Descripción: Controlador para justificar una cancelación de colaboración
*/
package coilvic.controladores;

import coilvic.modelo.dao.ColaboracionDAO;
import coilvic.observador.ObservadorColaboraciones;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.Utilidades;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class FXMLJustificarCancelacionController implements Initializable {
    
    private int idColaboracion;
    private ObservadorColaboraciones observador;
    
    @FXML
    private TextArea taJustificacion;
    @FXML
    private Label lbError;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void inicializarValores(int idColaboracion, ObservadorColaboraciones observador){
        this.idColaboracion = idColaboracion;
        this.observador = observador;
    }

    @FXML
    private void btnClicCancelar(ActionEvent event) {
        cerrarVentana();
    }

    @FXML
    private void btnClicAceptar(ActionEvent event) {
        if (agregarJustificacion()) {
            cerrarVentana();
            cancelarColaboracion();
        }
    }
    
    private boolean agregarJustificacion(){
        if (taJustificacion != null && !taJustificacion.getText().isEmpty()) {
            HashMap<String, Object> respuesta = 
                    ColaboracionDAO.agregarJustificacionColaboracion(idColaboracion, 
                            taJustificacion.getText());
            boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
            if (!isError) {
                Utilidades.mostrarAlertaSimple("Justificación registrada",
                        "" + respuesta.get(Constantes.KEY_MENSAJE), 
                        Alert.AlertType.INFORMATION);
                return true;
            } else {
                Utilidades.mostrarAlertaSimple("Error al registrar justificación", "" + 
                        respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
            }
        } else {
            lbError.setText("Por favor, introduzca su justificación antes de continuar");
        }
        return false;
    }
    
    private void cerrarVentana(){
        ((Stage) taJustificacion.getScene().getWindow()).close();
    }
    
    private void cancelarColaboracion(){
        HashMap<String, Object> respuesta = ColaboracionDAO.cancelarColaboracion(idColaboracion);
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            Utilidades.mostrarAlertaSimple("Colaboración cancelada", 
                    ""+respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.INFORMATION);
            observador.operacionExitosa("Cancelación");
        } else {
            Utilidades.mostrarAlertaSimple("Error al cancelar", 
                    ""+respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }
    
}

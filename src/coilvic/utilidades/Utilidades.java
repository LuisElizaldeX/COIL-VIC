package coilvic.utilidades;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


public class Utilidades {
    
    public static void mostrarAlertaSimple(String titulo, String mensaje,
        Alert.AlertType tipo) {
        Alert alertaSimple = new Alert(tipo);
        alertaSimple.setTitle(titulo);
        alertaSimple.setContentText(mensaje);
        alertaSimple.setHeaderText(null);
        alertaSimple.showAndWait();
    }
    
    public static boolean mostrarAlertaConfirmacion(String titulo, String mensaje) {
        Alert alertaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacion.setTitle(titulo);
        alertaConfirmacion.setContentText(mensaje);
        alertaConfirmacion.setHeaderText(null);
        
        Optional<ButtonType> botonClic = alertaConfirmacion.showAndWait(); 
        
        return (botonClic.get() == ButtonType.OK);
    }
    
}

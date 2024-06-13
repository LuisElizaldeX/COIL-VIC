/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 07/06/2024
 * Descripción: Clase de utilidades para confgigurar metodos reciclados
 */

package coilvic.utilidades;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
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
    
    public static FXMLLoader obtenerLoader(String ruta){
        return  new FXMLLoader(coilvic.COILVIC.class.getResource(ruta));
    }
    
     public static boolean correoValido(String correo){
        if(correo!=null && !correo.isEmpty()){
              Pattern patronCorreo = 
                      Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
              Matcher macthPatron = patronCorreo.matcher(correo);
              return macthPatron.find();
        }else{
            return false;
        }
    }
     
    public static boolean matriculaValida(String matricula) {
        if (matricula != null && !matricula.isEmpty()) {
            Pattern patronMatricula = Pattern.compile("^zS.*");
            Matcher matchPatron = patronMatricula.matcher(matricula);
            return matchPatron.find();
        } else {
            return false;
        }
    }
    
}

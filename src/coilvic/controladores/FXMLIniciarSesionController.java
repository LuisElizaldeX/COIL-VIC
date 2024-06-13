/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 13/05/2024
* Descripción: Controlador de la vista Iniciar Sesion
*/

package coilvic.controladores;

import coilvic.COILVIC;
import coilvic.modelo.dao.CoordinadorCOILDAO;
import coilvic.modelo.dao.ProfesorUVDAO;
import coilvic.modelo.dao.SesionDAO;
import coilvic.modelo.pojo.CoordinadorCOIL;
import coilvic.modelo.pojo.RespuestaCoordinadorCOIL;
import coilvic.modelo.pojo.RespuestaLogin;
import coilvic.modelo.pojo.RespuestaProfesorUV;
import coilvic.utilidades.SingletonCoordinadorCOIL;
import coilvic.utilidades.SingletonProfesorUV;
import coilvic.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLIniciarSesionController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private PasswordField pfContraseña;
    @FXML
    private TextField tfNombreUsuario;
    @FXML
    private Button btnIiciarSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicBtnIniciarSesion(ActionEvent event) {
        String usuario = tfNombreUsuario.getText().trim();
        String contraseña = pfContraseña.getText().trim();
        if(!usuario.isEmpty() && !contraseña.isEmpty()){
            RespuestaLogin respuesta = SesionDAO.IniciarSesionUsuario(usuario, 
                    contraseña);
            if(!respuesta.isError()){
                if(respuesta.getUsuario().getIdTipoUsuario() == 1){ 
                    RespuestaCoordinadorCOIL coordinador = CoordinadorCOILDAO.
                            obtenerInformacionCoordinadorCOIL(
                                    respuesta.getUsuario().getIdUsuario());
                    SingletonCoordinadorCOIL.getInstancia().
                            setCoordinadorCOIL(coordinador.getCoordinador());
                    Utilidades.mostrarAlertaSimple("Bienvenido(a)", 
                    "Bienvenido(a) "+ coordinador.getCoordinador().toString() +" al sistema...",   
                Alert.AlertType.INFORMATION);
                    irPantallaPrincipalCoordinadorCOIL();
                }else{
                    RespuestaProfesorUV profesor = ProfesorUVDAO.
                            obtenerInformacionProfesorUV(
                                    respuesta.getUsuario().getIdUsuario());
                    SingletonProfesorUV.getInstancia().
                            setProfesorUV(profesor.getProfesoruv());
                    Utilidades.mostrarAlertaSimple("Bienvenido(a)", 
                    "Bienvenido(a) "+ profesor.getProfesoruv().toString() +" al sistema...",   
                Alert.AlertType.INFORMATION);
                    irPantallaPrincipalProfesorUV(); 
                }
            }else{
                 Utilidades.mostrarAlertaSimple("Credenciales incorrectas", 
                             "El usuario y/o contraseña no son correctos, "
                             + "por favor verifica la información", 
                        Alert.AlertType.WARNING);
            }
        }else{
            Utilidades.mostrarAlertaSimple("Datos vacíos", 
                            "Por favor, ingrese los datos solicitados", 
                            Alert.AlertType.WARNING);
        }
    }
    
    private void irPantallaPrincipalCoordinadorCOIL(){
        try { 
        FXMLLoader accesoControlador = new FXMLLoader(COILVIC.class.getResource(
                "vistas/FXMLPaginaPrincipalCoordinadorCOIL.fxml"));
        Parent vista = accesoControlador.load();
        Stage escenarioPrincipal = (Stage) btnIiciarSesion.getScene().getWindow();
        Scene escenaPrincipal = new Scene(vista); 
        escenarioPrincipal.setScene(escenaPrincipal);
        escenarioPrincipal.setTitle("Pagina principal CoordinadorCOIL");
        escenarioPrincipal.show();
        }catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void irPantallaPrincipalProfesorUV(){
        try { 
        FXMLLoader accesoControlador = new FXMLLoader(COILVIC.class.getResource(
                "vistas/FXMLPaginaPrincipalProfesorUV.fxml"));
        Parent vista = accesoControlador.load();
        Stage escenarioPrincipal = (Stage) btnIiciarSesion.getScene().getWindow();
        Scene escenaPrincipal = new Scene(vista); 
        escenarioPrincipal.setScene(escenaPrincipal);
        escenarioPrincipal.setTitle("Pagina principal ProfesorUV");
        escenarioPrincipal.show();
        }catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}

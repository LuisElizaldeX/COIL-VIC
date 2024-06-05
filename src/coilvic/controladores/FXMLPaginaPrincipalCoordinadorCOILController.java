/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 18/05/2024
* Descripción: Controlador de página principal de CoordinadorCOIL
*/

package coilvic.controladores;

import coilvic.modelo.pojo.CoordinadorCOIL;
import coilvic.utilidades.SingletonCoordinadorCOIL;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FXMLPaginaPrincipalCoordinadorCOILController implements Initializable {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
    
    CoordinadorCOIL coordinador = SingletonCoordinadorCOIL.getInstancia().getCoordinadorCOIL();
    
    @FXML
    private Label lbNombreBienvenida;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());
        lbNombreBienvenida.setText(coordinador.toString());
    }    

    @FXML
    protected void clicImgCerrarSesion(MouseEvent event) {
        try{
            Stage escenarioPrincipal = (Stage) imgCerrarSesion.getScene().getWindow();
            Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                    getResource("vistas/FXMLIniciarSesion.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.setTitle("Inicio de sesión");
            escenarioPrincipal.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @FXML
    protected void clicBtnIrOfertasColaboracionCOIL(ActionEvent event) {
    }

    @FXML
    protected void clicBtnirPaginaPrincipal(ActionEvent event) {
        try{
            Stage escenarioPrincipal = (Stage) imgCerrarSesion.getScene().getWindow();
            Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                    getResource("vistas/FXMLPaginaPrincipalCoordinadorCOIL.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.setTitle("Pagina principal CoordinadorCOIL");
            escenarioPrincipal.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @FXML
    protected void clicBtnIrRegistrarOfertaColaboracion(ActionEvent event) {
    }

    @FXML
    protected void clicBtnIrHistorialColaboracion(ActionEvent event) {
         try{
            Stage escenarioPrincipal = (Stage) imgCerrarSesion.getScene().getWindow();
            Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                    getResource("vistas/FXMLHistorialDeColaboraciones.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.setTitle("Historial de colaboraciones");
            escenarioPrincipal.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @FXML
    protected void clicBtnRevisarColaboracionesCOIL(ActionEvent event) {
        try{
            Stage escenarioPrincipal = (Stage) imgCerrarSesion.getScene().getWindow();
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
    protected void clicBtnIrCancelarColaboracionCOIL(ActionEvent event) {
    }

    @FXML
    protected void clicBtnIrNumeralia(ActionEvent event) {
        try{
            Stage escenarioPrincipal = (Stage) imgCerrarSesion.getScene().getWindow();
            Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                    getResource("vistas/FXMLConsultarNumeralia.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.setTitle("Consultar numeralia");
            escenarioPrincipal.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @FXML
    protected void clicBtnIrRevisarSolicitudesDeConstancia(ActionEvent event) {
        try{
            Stage escenarioPrincipal = (Stage) imgCerrarSesion.getScene().getWindow();
            Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                    getResource("vistas/FXMLSolicitudesDeConstancia.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.setTitle("Revisar solicitudes de constancia");
            escenarioPrincipal.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
}

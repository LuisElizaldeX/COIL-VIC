/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 21/05/2024
* Descripción: Controlador para la página Historial de colaboraciones
*/

package coilvic.controladores;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FXMLHistorialDeColaboracionesController 
        extends FXMLPaginaPrincipalCoordinadorCOILController {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private TableView<?> tvColaboraciones;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colExperienciaEducativa;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());
    }    

    @FXML
    private void clicBtnVerHistorial(ActionEvent event) {
         try{  
            Stage escenarioPrincipal = (Stage) imgCerrarSesion.getScene().getWindow();
            Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                    getResource("vistas/FXMLEtapasColaboracion.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.setTitle("Etapas colaboración");
            escenarioPrincipal.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
}

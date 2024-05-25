/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 17/05/2024
* Descripción: Controlador de página principal de profesor UV
*/

package coilvic.controladores;

import coilvic.modelo.pojo.ProfesorUV;
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
import coilvic.utilidades.SingletonProfesorUV;

public class FXMLPaginaPrincipalProfesorUVController implements Initializable {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btnPrincipal;
    @FXML
    private Label lbNombreSesion;
    
    ProfesorUV profesor = SingletonProfesorUV.getInstancia().getProfesorUV();
    
    @FXML
    private Label lbNombreBienvenida;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(profesor.toString());
        lbNombreBienvenida.setText(profesor.toString());
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
                    getResource("vistas/FXMLPaginaPrincipalProfesorUV.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.setTitle("Pagina principal ProfesorUV");
            escenarioPrincipal.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    

    @FXML
    protected void clicBtnIrRegistrarOfertaColaboracion(ActionEvent event) {
    }

    @FXML
    protected void clicBtnIrRegistrarColaboracionCOIL(ActionEvent event) {
       try{
            Stage escenarioPrincipal = (Stage) imgCerrarSesion.getScene().getWindow();
            Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                    getResource("vistas/FXMLRegistrarColaboracionDirecta.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.setTitle("Registro colaboracion");
            escenarioPrincipal.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    
    
    }
    
    
    
}

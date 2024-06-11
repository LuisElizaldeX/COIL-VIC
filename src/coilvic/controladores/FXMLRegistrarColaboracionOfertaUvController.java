/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coilvic.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author zS21022065
 */
public class FXMLRegistrarColaboracionOfertaUvController extends FXMLPaginaPrincipalProfesorUVController {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btnPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private TextField tfPais;
    @FXML
    private TextField tfApellidosProfesorExterno;
    @FXML
    private TextField tfIdioma;
    @FXML
    private TextField tfUniversidad;
    @FXML
    private TextField tfCarrera;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfNombreProfesorExterno;
    @FXML
    private TextField TfMateriaE;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfNumeroDeEstudiantes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

    @FXML
    private void btnAceptarRegistroColaboracion(ActionEvent event) {
    }

    @FXML
    private void btnCancelarRegistroColaboracion(ActionEvent event) {
    }
    
}

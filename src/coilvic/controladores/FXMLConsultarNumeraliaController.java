/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 18/05/2024
* Descripción: Controlador para la página Consultar numeralia
*/

package coilvic.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FXMLConsultarNumeraliaController extends FXMLPaginaPrincipalCoordinadorCOILController {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private TableView<?> tvNumeralia;
    @FXML
    private TableColumn<?, ?> colRegion;
    @FXML
    private TableColumn<?, ?> colAlumnos;
    @FXML
    private TableColumn<?, ?> colProfesores;
    @FXML
    private ComboBox<?> cbFiltroBusqueda;
    

    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());
    }    
    
}

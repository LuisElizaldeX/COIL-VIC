/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 07/06/2024
* Descripción: Controlador de página de solicitud de constancia
*/

package coilvic.controladores;

import coilvic.modelo.pojo.SolicitudConstancia;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FXMLSolicitudDeConstanciaController 
        extends FXMLPaginaPrincipalCoordinadorCOILController{

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbExperienciaEducativa;
    @FXML
    private Label lbDescripicion;
    @FXML
    private Label lbProfesorUV;
    @FXML
    private Label lbEstado;
    
    SolicitudConstancia solicitudConstancia;
    
    @FXML
    private TableView<?> tvEstudiantes;
    @FXML
    private TableColumn<?, ?> colEstudiantes;
    @FXML 
    private TableColumn<?, ?> colMatricula;
    @FXML
    private Label lbProfesorExterno;
    @FXML
    private Label lbProgramaEducativo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());
    }

    public void configurarDatosColaboracionCOIl(SolicitudConstancia solicitud){
        this.solicitudConstancia=solicitud;
        lbNombre.setText(solicitud.getColaboracion());
        lbDescripicion.setText(solicitud.getDescripcion());
        lbExperienciaEducativa.setText(solicitud.getExperienciaEducativa());
        lbProfesorUV.setText(solicitud.getProfesorUV());
        lbEstado.setText(solicitud.getEstado());
    }

    @FXML
    private void clicBtnGenerarConstancia(ActionEvent event) {
    }
    
}

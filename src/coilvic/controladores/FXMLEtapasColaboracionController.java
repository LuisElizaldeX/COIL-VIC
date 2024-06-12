/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 21/05/2024
* Descripción: Controlador para la página etapa de colaboracion
*/

package coilvic.controladores;

import coilvic.modelo.pojo.Colaboracion;
import coilvic.utilidades.Utilidades;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLEtapasColaboracionController extends FXMLPaginaPrincipalCoordinadorCOILController {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
    
    Colaboracion colaboracion;
    
    @FXML
    private Label lbMateria;
    @FXML
    private Label lbNombreColaboracion;
    @FXML
    private Label lbJustificacion;
    @FXML
    private Label lbFechaInicio;
    @FXML
    private Label lbFechaCierre;
    @FXML
    private Label lbProfesorUV;
    @FXML
    private Label lbProfesorExterno;
    @FXML
    private Label lbNumEstudiantes;
    @FXML
    private Label lbMateriaOferta;
    @FXML
    private Label lbFechaInicioOferta;
    @FXML
    private Label lbFechaCierreOferta;
    @FXML
    private Label lbProfesorUVOferta;
    @FXML
    private Label lbProfesorExternoOferta;
    @FXML
    private Label lbNombreColaboracionOferta;
    @FXML
    private Label lbNombreColaboracionEfectuada;
    @FXML
    private Label lbNombreColaboracionCancelada;
    @FXML
    private Label lbMateriaCurso;
    @FXML
    private Label lbNombreEvidencia;

    private byte[] archivo;
    
    @FXML
    private Pane paneEfectuada;
    @FXML
    private Pane paneCancelada;
    @FXML
    private Label lbEfectuada;
    @FXML
    private Label lbCancelada;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());
    }

     public void configurarDatosColaboracionCOIL(Colaboracion colaboracion){
        this.colaboracion=colaboracion;
        lbNombreColaboracion.setText(colaboracion.getNombre());
        lbNombreColaboracionOferta.setText(colaboracion.getNombre());
        lbNombreColaboracionEfectuada.setText(colaboracion.getNombre());
        lbNombreColaboracionCancelada.setText(colaboracion.getNombre());
        lbFechaInicio.setText(colaboracion.getFechaInicio());
        lbFechaInicioOferta.setText(colaboracion.getFechaInicioOferta());
        lbFechaCierre.setText(colaboracion.getFechaFin());
        lbFechaCierreOferta.setText(colaboracion.getFechaFinOferta());
        lbProfesorUV.setText(colaboracion.getProfesorUV());
        lbProfesorUVOferta.setText(colaboracion.getProfesorUV());
        lbProfesorExterno.setText(colaboracion.getProfesorExterno());
        lbProfesorExternoOferta.setText(colaboracion.getProfesorExterno());
        lbMateria.setText(colaboracion.getExperienciaEducativa());
        lbMateriaOferta.setText(colaboracion.getExperienciaEducativa());
        lbMateriaCurso.setText(colaboracion.getExperienciaEducativa());
        lbNumEstudiantes.setText(colaboracion.getNumeroEstudiantes());
        
        if(colaboracion.getIdEstadoColaboracion() == 2){
            archivo = colaboracion.getEvidencia();
            lbNombreEvidencia.setText(colaboracion.getNombreEvidencia());
            paneEfectuada.setVisible(true);
            lbEfectuada.setVisible(true);
        }
        
        if(colaboracion.getIdEstadoColaboracion() == 3){
            lbJustificacion.setText(colaboracion.getJustificacion());
            paneCancelada.setVisible(true);
            lbCancelada.setVisible(true);
        }
        
    }
     

    @FXML
    private void clicImgEvidencia(MouseEvent event) {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar evidencia");
        FileChooser.ExtensionFilter extFilter = 
                new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(archivo);
                fos.flush();
                Utilidades.mostrarAlertaSimple("Archivo exportado",
                    "El archivo fue exportado correctamente",
                    Alert.AlertType.INFORMATION);
            } catch (IOException e) {
                Utilidades.mostrarAlertaSimple("Error al exportar",
                    e.getMessage(),
                    Alert.AlertType.ERROR);
            }
        }
    }
    
}

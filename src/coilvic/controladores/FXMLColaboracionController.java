package coilvic.controladores;

import coilvic.modelo.dao.ArchivoDAO;
import coilvic.modelo.pojo.Archivo;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.Utilidades;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLColaboracionController extends FXMLPaginaPrincipalProfesorUVController {
    private Colaboracion colaboracion;
    private File archivoSeleccionado;
    
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btnPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private Label lbNombreColaboracion;
    @FXML
    private Label lbPeriodo;
    @FXML
    private Label lbDescripcion;
    @FXML
    private Label lbNombreProfesorUV;
    @FXML
    private Label lbRegion;
    @FXML
    private Label lbCarrera;
    @FXML
    private Label lbExperienciaEducativa;
    @FXML
    private Label lbTelefono;
    @FXML
    private Label lbIdiomaProfesorUV;
    @FXML
    private Label lbCorreoProfesorUV;
    @FXML
    private Label lbPresentacionProfEx;
    @FXML
    private Label lbNombreProfEx;
    @FXML
    private Label lbCorreoProfEx;
    @FXML
    private Label lbMateriaProfEx;
    @FXML
    private Label lbCarreraProfEx;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void inicializarValores(Colaboracion colaboracion){
        this.colaboracion = colaboracion;
    }

    @FXML
    private void btnClicRegresar(ActionEvent event) {
        try{
           Stage escenarioPrincipal = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLPaginaPrincipalProfesorUV.fxml"));
           Scene escenaPrincipal = new Scene(root);
           escenarioPrincipal.setScene(escenaPrincipal);
           escenarioPrincipal.setTitle("Pagina principal ProfesorUv");
           escenarioPrincipal.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @FXML
    private void btnClicCancelarColaboracion(ActionEvent event) {
    }

    @FXML
    private void btnClicCerrarColaboracion(ActionEvent event) {
    }


    @FXML
    private void btnClicSubirArchivo(ActionEvent event) {
        seleccionarArchivo();
    }
    
    private void seleccionarArchivo(){
        FileChooser dialogoSeleccion = new FileChooser();
        dialogoSeleccion.setTitle("Seleccionar syllabus");
        String etiquetaTipoArchivo = "*.pdf";
        FileChooser.ExtensionFilter filtroArchivo = new FileChooser.ExtensionFilter(etiquetaTipoArchivo,"*.pdf");
        dialogoSeleccion.getExtensionFilters().add(filtroArchivo);
        Stage escenarioActual = (Stage) lbNombreProfesorUV.getScene().getWindow();
        archivoSeleccionado = dialogoSeleccion.showOpenDialog(escenarioActual);
        if(archivoSeleccionado != null){   
            try {
                Archivo archivo = new Archivo();
                byte[] archivoBytes = Files.readAllBytes(archivoSeleccionado.toPath());
                archivo.setNombre(archivoSeleccionado.getName());
                archivo.setArchivoCol(archivoBytes);
                archivo.setIdTipoArchivo(1);
                subirArchivo(archivo);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void subirArchivo(Archivo archivo){
        HashMap<String, Object> respuesta = ArchivoDAO.subirArchivo(archivo);
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            Utilidades.mostrarAlertaSimple("Archivo guardado", "El syllabus se ha subido correctamente", Alert.AlertType.INFORMATION);
        } else {
            Utilidades.mostrarAlertaSimple("Error al subir archivo", "" + respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }

}

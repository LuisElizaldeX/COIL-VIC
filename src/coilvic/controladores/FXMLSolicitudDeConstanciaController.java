/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 07/06/2024
* Descripción: Controlador de página de solicitud de constancia
*/

package coilvic.controladores;

import coilvic.modelo.dao.SolicitudConstanciaDAO;
import coilvic.modelo.pojo.SolicitudConstancia;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.Utilidades;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    private TableView<SolicitudConstancia> tvEstudiantes;
    @FXML
    private TableColumn colEstudiantes;
    @FXML 
    private TableColumn colMatricula;
    @FXML
    private Label lbProfesorExterno;
    @FXML
    private Label lbProgramaEducativo;
    
    private byte[] archivo;

    private ObservableList<SolicitudConstancia> solicitudConstanciaInformacion;
    @FXML
    private Label lbNombreEvidencia;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());
        configurarTabla();
    }

    public void configurarDatosColaboracionCOIL(SolicitudConstancia solicitud){
        this.solicitudConstancia=solicitud;
        lbNombre.setText(solicitud.getColaboracion());
        lbDescripicion.setText(solicitud.getDescripcion());
        lbExperienciaEducativa.setText(solicitud.getExperienciaEducativa());
        lbProfesorUV.setText(solicitud.getProfesorUV());
        lbProfesorExterno.setText(solicitud.getProfesorExterno());
        lbProgramaEducativo.setText(solicitud.getProgramaEducativo());
        lbEstado.setText(solicitud.getEstado());
        lbNombreEvidencia.setText(solicitud.getNombreArchivo());
        archivo = solicitud.getArchivo();
        cargarDatosEstudiantesColaboracion(solicitud.getIdColaboracion());
    }
    
    private void configurarTabla(){
        colEstudiantes.setCellValueFactory(new PropertyValueFactory("estudiante"));
        colMatricula.setCellValueFactory(new PropertyValueFactory("matricula"));
    }
    
    private void cargarDatosEstudiantesColaboracion(int idColaboracion){
        solicitudConstanciaInformacion = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta = SolicitudConstanciaDAO.
                obtenerEstudiantesColaboracion(idColaboracion);
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            ArrayList<SolicitudConstancia> solicitudesConstanciaBD = 
                    (ArrayList<SolicitudConstancia>) respuesta.get("Estudiantes");
            solicitudConstanciaInformacion.addAll(solicitudesConstanciaBD);
            tvEstudiantes.setItems(solicitudConstanciaInformacion);
        }else{
            Utilidades.mostrarAlertaSimple("Error", 
                    ""+respuesta.get(Constantes.KEY_MENSAJE),
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clicBtnGenerarConstancia(ActionEvent event) {
        String encabezadoColaboracion = "Colaboracion, Experiencia educativa, Programa educativo, "
                + "Profesor UV, Profesor externo, Descripcion";
        String encabezadoEstudiantes = "Estudiante, Matricula";
        DirectoryChooser directorio = new DirectoryChooser();
        directorio.setTitle("Selecciona una carpeta");
        File directorioDestino = 
                directorio.showDialog(imgCerrarSesion.getScene().getWindow());
        if (directorioDestino != null) {
            try {
                String ruta = directorioDestino.getAbsolutePath() + "/Constancia.csv";
                File archivoExportado = new File(ruta);
                final String BOM = "\uFEFF";
                try (Writer archivoEscritura = new BufferedWriter(new OutputStreamWriter
                    (new FileOutputStream(archivoExportado), StandardCharsets.UTF_8))) {
                    archivoEscritura.write(BOM);
                    archivoEscritura.write(encabezadoColaboracion);
                    archivoEscritura.write("\n");
                    String filaColaboracion = String.format("%s,%s,%s,%s,%s,%s",
                        solicitudConstancia.getColaboracion(),
                        solicitudConstancia.getExperienciaEducativa(),
                        solicitudConstancia.getProgramaEducativo(),
                        solicitudConstancia.getProfesorUV(),
                        solicitudConstancia.getProfesorExterno(),
                        solicitudConstancia.getDescripcion());
                    archivoEscritura.write(filaColaboracion);
                    archivoEscritura.write("\n\n");
                    archivoEscritura.write(encabezadoEstudiantes);
                    archivoEscritura.write("\n");
                    for (SolicitudConstancia solicitud : solicitudConstanciaInformacion) {
                        String filaEstudiante = String.format("%s,%s",
                            solicitud.getEstudiante(),
                            solicitud.getMatricula());
                        archivoEscritura.write(filaEstudiante);
                        archivoEscritura.write("\n");
                    }
                }
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

    @FXML
    private void clicArchivoEvidencia(MouseEvent event) {
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

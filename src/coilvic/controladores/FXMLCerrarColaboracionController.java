package coilvic.controladores;

import coilvic.modelo.dao.ArchivoDAO;
import coilvic.modelo.dao.EstudianteDAO;
import coilvic.modelo.pojo.Archivo;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.modelo.pojo.Estudiante;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.Utilidades;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLCerrarColaboracionController extends FXMLPaginaPrincipalProfesorUVController {
    
    private ObservableList<Estudiante> estudiantes;
    private Colaboracion colaboracion;
    private File archivoSeleccionado;
    private Archivo archivo = new Archivo();
    
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btnPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private TableView<Estudiante> tvEstudiantes;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colMatricula;
    @FXML
    private Label lbMensajeEvidencia;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnEliminarEstudiantes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        configurarBtnEliminarEstudiantes();
    }    
    
    public void inicializarValores(Colaboracion colaboracion){
        this.colaboracion = colaboracion;
        cargarDatosEstudiantes();
    }
    
    private void configurarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));
        colMatricula.setCellValueFactory(new PropertyValueFactory("matricula"));
        tvEstudiantes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    
    private void configurarBtnEliminarEstudiantes(){
        tvEstudiantes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Estudiante>(){
            @Override
            public void changed(ObservableValue<? extends Estudiante> observable, Estudiante oldValue, Estudiante newValue) {
                if (newValue != null) {
                    btnEliminarEstudiantes.setDisable(false);
                } 
            }
            
        });
    }
    
    private void cargarDatosEstudiantes(){
        estudiantes = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta = EstudianteDAO.obtenerEstudiantes(colaboracion.getIdColaboracion());
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            ArrayList<Estudiante> estudiantesBD = (ArrayList<Estudiante>) respuesta.get("estudiantes");
            estudiantes.addAll(estudiantesBD);
            tvEstudiantes.setItems(estudiantes);
        } else {
            Utilidades.mostrarAlertaSimple("Error", "" + respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void btnClicSubirEvidencia(ActionEvent event) {
        seleccionarArchivo();
    }
    
    private void seleccionarArchivo(){
        FileChooser dialogoSeleccion = new FileChooser();
        dialogoSeleccion.setTitle("Seleccionar evidencia");
        String etiquetaTipoArchivo = "*.pdf";
        FileChooser.ExtensionFilter filtroArchivo = new FileChooser.ExtensionFilter(etiquetaTipoArchivo,"*.pdf");
        dialogoSeleccion.getExtensionFilters().add(filtroArchivo);
        Stage escenarioActual = (Stage) lbMensajeEvidencia.getScene().getWindow();
        archivoSeleccionado = dialogoSeleccion.showOpenDialog(escenarioActual);
        if(archivoSeleccionado != null){   
            if (pesoSoportado()) {
                try {
                    byte[] archivoBytes = Files.readAllBytes(archivoSeleccionado.toPath());
                    archivo.setNombre(archivoSeleccionado.getName());
                    archivo.setArchivoCol(archivoBytes);
                    archivo.setIdTipoArchivo(2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                lbMensajeEvidencia.setStyle("-fx-text-fill: green");
                lbMensajeEvidencia.setText("Evidencia " + archivo.getNombre() + " registrada");
            } else {
                Utilidades.mostrarAlertaSimple("Peso excedido", "El archivo seleccionado es mayor a los 20MB", Alert.AlertType.WARNING);
            }
        } 
    }
    
    private boolean pesoSoportado(){
        long pesoArchivo = archivoSeleccionado.length();
        return pesoArchivo < Constantes.PESO_MAXIMO;
    }
    
    private void subirArchivo(Archivo archivo){
        HashMap<String, Object> respuesta = ArchivoDAO.subirArchivo(archivo);
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            
            Utilidades.mostrarAlertaSimple("Archivo guardado", "La evidencia se ha subido correctamente", Alert.AlertType.INFORMATION);
        } else {
            Utilidades.mostrarAlertaSimple("Error al subir archivo", "" + respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnClicGuardar(ActionEvent event) {
    }

    @FXML
    private void btnClicCancelar(ActionEvent event) {
        irColaboracion(colaboracion);
    }

    @FXML
    private void btnClicAceptar(ActionEvent event) {
    }

    @FXML
    private void btnClicEliminarEstudiantes(ActionEvent event) {
        ObservableList<Estudiante> estudiantesSeleccionados = tvEstudiantes.getSelectionModel().getSelectedItems();
        if(estudiantesSeleccionados != null){
            boolean respuesta = Utilidades.mostrarAlertaConfirmacion("Eliminar estudiantes", 
                    "¿Seguro que desea eliminar a los estudiantes seleccionados?");
            if(respuesta){
                boolean isError = false;
                for (Estudiante estudiante : estudiantesSeleccionados){
                    boolean resultado = eliminarEstudiante(estudiante.getIdEstudiante());
                    if(resultado){
                        isError = true;
                        break;
                    }
                }
                
                if(!isError){
                    Utilidades.mostrarAlertaSimple("Estudiantes eliminados", 
                            "Los estudiantes se han eliminado correctamente", Alert.AlertType.INFORMATION);
                } 
            }
        }
        cargarDatosEstudiantes();
    }
    
    private boolean eliminarEstudiante(int idEstudiante){
        HashMap<String, Object> respuesta = EstudianteDAO.eliminarEstudiante(idEstudiante, colaboracion.getIdColaboracion());
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(isError){
            Utilidades.mostrarAlertaSimple("Error al eliminar", 
                    ""+respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
        return isError;
    }
    
    private void irColaboracion(Colaboracion colaboracion){
        try {
            Stage escenario = (Stage) imgCerrarSesion.getScene().getWindow();
            FXMLLoader loader = Utilidades.obtenerLoader("vistas/FXMLColaboracion.fxml");
            Parent root = loader.load();
            FXMLColaboracionController controlador = loader.getController();
            controlador.inicializarValores(colaboracion);
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Colaboracion");
            escenario.show();
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

}

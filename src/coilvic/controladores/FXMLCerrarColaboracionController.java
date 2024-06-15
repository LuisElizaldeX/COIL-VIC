/*
* Autor: Josué Melgarejo García
* Fecha de creación: 02/06/2024
* Descripción: Controlador para cerrar una colaboración por parte del profesor uv
*/
package coilvic.controladores;

import coilvic.modelo.dao.ArchivoDAO;
import coilvic.modelo.dao.ColaboracionDAO;
import coilvic.modelo.dao.EstudianteDAO;
import coilvic.modelo.pojo.Archivo;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.modelo.pojo.Estudiante;
import coilvic.observador.ObservadorColaboraciones;
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
    
    private ObservadorColaboraciones observador;
    private ObservableList<Estudiante> estudiantes;
    private ObservableList<Estudiante> estudiantesEliminadosTemp;
    private Colaboracion colaboracion;
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
    @FXML
    private Button btnSubirEvidencia;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        configurarBtnEliminarEstudiantes();
        estudiantesEliminadosTemp = FXCollections.observableArrayList();
    }    
    
    public void inicializarValores(Colaboracion colaboracion, ObservadorColaboraciones observador){
        this.colaboracion = colaboracion;
        this.observador = observador;
        cargarDatosEstudiantes();
        configurarSubirEvidencia();
    }
    
    private void configurarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));
        colMatricula.setCellValueFactory(new PropertyValueFactory("matricula"));
        tvEstudiantes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    
    private void configurarBtnEliminarEstudiantes(){
        tvEstudiantes.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<Estudiante>(){
            @Override
            public void changed(ObservableValue<? extends Estudiante> observable, 
                    Estudiante oldValue, Estudiante newValue) {
                btnEliminarEstudiantes.setDisable(newValue == null);
            }
        });
    }
    
    private void configurarSubirEvidencia() {
        int idArchivo = colaboracion.getIdArchivo();
        if (idArchivo != 0) {
            btnSubirEvidencia.setDisable(true);
            btnAceptar.setDisable(false);
            Archivo archivo = obtenerArchivo(idArchivo);
            if (archivo != null) {
                lbMensajeEvidencia.setStyle("-fx-text-fill: green");
                lbMensajeEvidencia.setText("Evidencia " + archivo.getNombre() + " registrada");
            } else {
                lbMensajeEvidencia.setStyle("-fx-text-fill: red");
                lbMensajeEvidencia.setText("Error al obtener el archivo");
            }
        }
    }

    
    private void cargarDatosEstudiantes(){
        estudiantes = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta = 
                EstudianteDAO.obtenerEstudiantes(colaboracion.getIdColaboracion());
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            ArrayList<Estudiante> estudiantesBD = 
                    (ArrayList<Estudiante>) respuesta.get("estudiantes");
            estudiantes.addAll(estudiantesBD);
            tvEstudiantes.setItems(estudiantes);
        } else {
            Utilidades.mostrarAlertaSimple("Error", 
                    "" + respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.WARNING);
        }
    } 

    @FXML
    private void btnClicSubirEvidencia(ActionEvent event) {
        File archivoSeleccionado = obtenerArchivoSeleccionado();
        if (archivoSeleccionado != null) {
            if (pesoSoportado(archivoSeleccionado)) {
                guardarArchivo(archivoSeleccionado);
            } else {
                Utilidades.mostrarAlertaSimple("Peso excedido", 
                        "El archivo seleccionado es mayor a los 20MB", 
                        Alert.AlertType.WARNING);
            }
        }
    }
    
    private File obtenerArchivoSeleccionado(){
        FileChooser dialogoSeleccion = new FileChooser();
        dialogoSeleccion.setTitle("Seleccionar evidencia");
        String etiquetaTipoArchivo = "*.pdf";
        FileChooser.ExtensionFilter filtroArchivo = 
                new FileChooser.ExtensionFilter(etiquetaTipoArchivo,"*.pdf");
        dialogoSeleccion.getExtensionFilters().add(filtroArchivo);
        Stage escenarioActual = (Stage) lbMensajeEvidencia.getScene().getWindow();
        return dialogoSeleccion.showOpenDialog(escenarioActual);
    }
        
    private boolean pesoSoportado(File archivoSeleccionado){
        return archivoSeleccionado.length() < Constantes.PESO_MAXIMO;
    }
    
    private void guardarArchivo(File archivoSeleccionado) {
        if (archivoSeleccionado != null) {
            try {
                byte[] archivoBytes = Files.readAllBytes(archivoSeleccionado.toPath());
                archivo.setNombre(archivoSeleccionado.getName());
                archivo.setArchivoCol(archivoBytes);
                archivo.setIdTipoArchivo(Constantes.ID_ARCHIVO_EVIDENCIA);
                lbMensajeEvidencia.setStyle("-fx-text-fill: green");
                lbMensajeEvidencia.setText("Evidencia " + archivo.getNombre() + " registrada");
                btnAceptar.setDisable(false);
                btnGuardar.setDisable(false);
            } catch (IOException ex) {
                ex.printStackTrace();
                lbMensajeEvidencia.setStyle("-fx-text-fill: red");
                lbMensajeEvidencia.setText("Error al leer el archivo");
            }
        }
    }

    private boolean isErrorSubirArchivo(Archivo archivo) {
        HashMap<String, Object> respuesta = ArchivoDAO.subirArchivo(archivo);
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if (!isError) {
            archivo.setIdArchivo((int) respuesta.get("idArchivo"));
        } else {
            System.err.println(respuesta.get(Constantes.KEY_MENSAJE));
        }
        return isError;
    }

    private boolean isErrorRegistrarArchivo(int idColaboracion, int idArchivo) {
        HashMap<String, Object> respuesta = 
                ArchivoDAO.registrarArchivoEnColaboracion(idColaboracion, idArchivo);
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if (!isError) {
            colaboracion.setIdArchivo(idArchivo);
        } else {
            System.out.println("Error al registrar archivo en la colaboración: " + 
                    respuesta.get(Constantes.KEY_MENSAJE));
        }
        return isError;
    }

    @FXML
    private void btnClicGuardar(ActionEvent event) {
        if (!isErrorGuardarCambios()) {
            Utilidades.mostrarAlertaSimple("Cambios guardados",
                    "Se han guardado los cambios correctamente", 
                    Alert.AlertType.INFORMATION);
            irColaboracion();
        } else {
            Utilidades.mostrarAlertaSimple("Error al guardar",
                    "Hubo un problema al guardar los cambios favor de intentarlo más tarde",
                    Alert.AlertType.INFORMATION);
        }
    }
    
    private boolean isErrorGuardarCambios() {
        if (estudiantesEliminadosTemp != null && !estudiantesEliminadosTemp.isEmpty()) {
            for (Estudiante estudiante : estudiantesEliminadosTemp) {
                if (isErrorEliminarEstudiante(estudiante.getIdEstudiante()))
                    return true;
            }
        }
        
        if (archivo != null && archivo.getArchivoCol() != null){
            if (!isErrorSubirArchivo(archivo)){
                return isErrorRegistrarArchivo(colaboracion.getIdColaboracion(), 
                        archivo.getIdArchivo());
            }
            return true;
        }
        
        return false;
    }

    @FXML
    private void btnClicCancelar(ActionEvent event) {
        irColaboracion();
    }

    @FXML
    private void btnClicAceptar(ActionEvent event) {
        if (!isErrorGuardarCambios()){
            cerrarColaboracion();
        } 
    }
    
    private void cerrarColaboracion(){
        HashMap<String, Object> respuesta = 
                ColaboracionDAO.cerrarColaboracion(colaboracion.getIdColaboracion());
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if (!isError) {
            Utilidades.mostrarAlertaSimple("Colaboración cerrada", 
                   "" + respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.INFORMATION);
            if (observador != null) {
                observador.operacionExitosa("Cerrar colaboración");
            }
            irColaboracion();
        } else {
            Utilidades.mostrarAlertaSimple("Error al cerrar colaboración", 
                    "" + respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.WARNING);
        }
    }
    
    @FXML
    private void btnClicEliminarEstudiantes(ActionEvent event) {
        ObservableList<Estudiante> estudiantesSeleccionados
                = tvEstudiantes.getSelectionModel().getSelectedItems();
        if (estudiantesSeleccionados != null) {
            boolean respuesta = Utilidades.mostrarAlertaConfirmacion("Eliminar estudiantes",
                    "¿Seguro que desea eliminar a los estudiantes seleccionados?");
            if (respuesta) {
                estudiantesEliminadosTemp.addAll(estudiantesSeleccionados);
                estudiantes.removeAll(estudiantesSeleccionados);
                Utilidades.mostrarAlertaSimple("Estudiantes eliminados temporalmente",
                        "Los estudiantes se eliminarán de la colaboración "
                        + "hasta que de clic en Aceptar o Guardar",
                        Alert.AlertType.INFORMATION);
                btnGuardar.setDisable(false);
            }
        }
    }
    
    private boolean isErrorEliminarEstudiante(int idEstudiante){
        HashMap<String, Object> respuesta = EstudianteDAO.eliminarEstudiante(idEstudiante, 
                colaboracion.getIdColaboracion());
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR); 
        System.out.println(Constantes.KEY_MENSAJE);
        return isError;
    }
    
    private void irColaboracion(){
        Colaboracion colaboracionActualizada = obtenerColaboracionActualizada();
        try {
            Stage escenario = (Stage) imgCerrarSesion.getScene().getWindow();
            FXMLLoader loader = Utilidades.obtenerLoader("vistas/FXMLColaboracion.fxml");
            Parent root = loader.load();
            FXMLColaboracionController controlador = loader.getController();
            controlador.inicializarValores(colaboracionActualizada);
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Colaboracion");
            escenario.show();
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    private Archivo obtenerArchivo(int idArchivo){
        HashMap<String, Object> respuesta = ArchivoDAO.obtenerArchivo(idArchivo);
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if (!isError) {
            return (Archivo) respuesta.get("archivo");
        }
        return null;
    }
    
    private Colaboracion obtenerColaboracionActualizada(){
        HashMap<String, Object> respuesta = 
                ColaboracionDAO.obtenerColaboracion(colaboracion.getIdColaboracion());
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if (!isError) {
            return (Colaboracion) respuesta.get("colaboracion");
        }
        return null;
    }
}
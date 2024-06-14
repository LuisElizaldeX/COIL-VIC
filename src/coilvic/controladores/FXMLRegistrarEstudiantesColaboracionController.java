/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 11/06/2024
* Descripción: Controlador para registrar estudiantes en una colaboracion externa
*/

package coilvic.controladores;

import coilvic.modelo.dao.ColaboracionDAO;
import coilvic.modelo.dao.EstudianteDAO;
import coilvic.modelo.pojo.Estudiante;
import coilvic.observador.ObservadorColaboraciones;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class FXMLRegistrarEstudiantesColaboracionController implements Initializable {

    @FXML
    private TextField tfNombreEsudiante;
    @FXML
    private TextField tfMatricula;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    
    private int numeroEstudiantes;
    private int colaboracion;
    
    private List<Estudiante> listaEstudiantes = new ArrayList<>();
    
    private ObservadorColaboraciones obsevador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // Impedir cierre al hacer clic en la "X"
        Platform.runLater(() -> {
            Stage stage = (Stage) tfNombreEsudiante.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                event.consume(); // Consume el evento para evitar el cierre
                Utilidades.mostrarAlertaSimple("Operación no permitida", 
                        "No puede cerrar esta ventana hasta que complete la operación", Alert.AlertType.WARNING);
            });
        });
    }  
    
    
    public void inicializarValores(int numeroEstudiantes, int colaboracion, 
            ObservadorColaboraciones observador){
        this.numeroEstudiantes = numeroEstudiantes;
        this.colaboracion = colaboracion;
        this.obsevador = observador;
    }
    
    
    private boolean validarCamposVacios(){
        boolean esValido = true;
        if(tfNombreEsudiante.getText().trim().isEmpty() || 
                tfApellidoPaterno.getText().trim().isEmpty()
                || tfApellidoMaterno.getText().trim().isEmpty()
                || tfMatricula.getText().trim().isEmpty()){
            esValido=false;
            Utilidades.mostrarAlertaSimple("Datos incompletos", 
                    "Los datos no están completos" , Alert.AlertType.WARNING);
        }
        return esValido;
    }
    
    
    private boolean validarDatos(){
        boolean datosValidos=true;
        String nombre = tfNombreEsudiante.getText().trim();
        String apellidoPaterno = tfApellidoPaterno.getText().trim();
        String apellidoMaterno = tfApellidoMaterno.getText().trim();
        String matricula = tfMatricula.getText().trim();
        
        
        if(apellidoMaterno.length() > 45 || apellidoPaterno.length() > 45
                || nombre.length() > 45 || matricula.length() > 10
                || !Utilidades.matriculaValida(matricula)){
            datosValidos = false;
            Utilidades.mostrarAlertaSimple("Datos inválidos", 
                    "Los datos son inválidos" , Alert.AlertType.WARNING);
        }
        return datosValidos;
    }

    
    @FXML
    private void clicBtnRegistrar(ActionEvent event) {
        if(validarCamposVacios() && validarDatos()){
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre(tfNombreEsudiante.getText());
            estudiante.setApellidoPaterno(tfApellidoPaterno.getText());
            estudiante.setApellidoMaterno(tfApellidoMaterno.getText());
            estudiante.setMatricula(tfMatricula.getText());
        
            if (listaEstudiantes.size() < numeroEstudiantes) {
                listaEstudiantes.add(estudiante);
                limpiarDatos();
                if (listaEstudiantes.size() == numeroEstudiantes) {
                    registrarEstudiantes();
                }
            }
        }
    }
    
    
    private void limpiarDatos(){
        tfNombreEsudiante.clear();
        tfMatricula.clear();
        tfApellidoPaterno.clear();
        tfApellidoMaterno.clear();
    }
    
    
    private void registrarEstudiantes() {
        boolean errorEnRegistro = false;
        for (Estudiante estudiante : listaEstudiantes) {
            HashMap<String, Object> respuesta = EstudianteDAO.registrarEstudiante(estudiante);
            if (!(boolean) respuesta.get(Constantes.KEY_ERROR)) {
                int idEstudiante = (int) respuesta.get("idEstudiante");
                ColaboracionDAO.registrarColaboracionEstudiante(colaboracion, 
                        idEstudiante);
            }else{
                errorEnRegistro = true;
                Utilidades.mostrarAlertaSimple("Error al registrar", (String) 
                        respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
                break;
            }
        }

        if (!errorEnRegistro) {
            Utilidades.mostrarAlertaSimple("Registro exitoso", 
                    "Todos los estudiantes se han registrado correctamente.", 
                    Alert.AlertType.INFORMATION);
            listaEstudiantes.clear(); 
            cerrarVentana();
            obsevador.operacionExitosa("ColaboracionExterna");
        }
    }
    
    
    private void cerrarVentana(){
        Stage stage = (Stage) tfNombreEsudiante.getScene().getWindow();
        stage.close();
    }
    
    
}

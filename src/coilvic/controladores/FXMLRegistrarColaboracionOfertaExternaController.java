/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 08/06/2024
* Descripción: Controlador para la página registrar colaboracion oferta externa
*/

package coilvic.controladores;

import coilvic.modelo.dao.ArchivoDAO;
import coilvic.modelo.dao.CatalogoDAO;
import coilvic.modelo.dao.ColaboracionDAO;
import coilvic.modelo.dao.OfertaColaboracionExternaDAO;
import coilvic.modelo.pojo.Archivo;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.modelo.pojo.Idioma;
import coilvic.modelo.pojo.ProfesorUV;
import coilvic.observador.ObservadorColaboraciones;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.SingletonProfesorUV;
import coilvic.utilidades.Utilidades;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLRegistrarColaboracionOfertaExternaController 
        extends FXMLPaginaPrincipalProfesorUVController implements ObservadorColaboraciones {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btnPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfNumeroDePersonal;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfNumeroDeEstudiantes;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFin;
    
    private Archivo archivo = new Archivo();
    
    String estiloError = "-fx-text-fill: red;";
    
    @FXML
    private Label lbTextoArchivo;
    
    ProfesorUV profesorUv = SingletonProfesorUV.getInstancia().getProfesorUV();
    
    @FXML
    private TextField tfApellidosProfesorUV;
    @FXML
    private TextField tfNombreProfesorUV;
    
    private int numeroDeEstudiantes;
    
    Colaboracion colaboracion;
    
    private ObservableList<Idioma> idiomas;
    
    private byte[] syllabus;
    
    @FXML
    private ComboBox<Idioma> cbIdioma;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(profesorUv.toString());
        cargarIdiomas();
    }    

    public void inicializarValoresColaboracion(Colaboracion colaboracion){
        this.colaboracion = colaboracion;
    }
   
    @FXML
    private void btnAceptarRegistroColaboracion(ActionEvent event) {
        if(validarCamposVacios() && validarDatos()){
            colaboracion.setFechaInicio(dpFechaInicio.getValue().toString());
            colaboracion.setFechaFin(dpFechaFin.getValue().toString());
            colaboracion.setIdIdioma
                (cbIdioma.getSelectionModel().getSelectedItem().getIdIdioma());
            HashMap<String, Object> respuestaArchivo = ArchivoDAO.subirArchivo(archivo);
            if (!(boolean) respuestaArchivo.get(Constantes.KEY_ERROR)) {
                int idArchivo = (int) respuestaArchivo.get("idArchivo");
                HashMap<String, Object> respuesta = 
                ColaboracionDAO.guardarColaboracionExterna(colaboracion, idArchivo);
                if( !(boolean)respuesta.get(Constantes.KEY_ERROR)){
                    try {
                        int idColaboracion = (int) respuesta.get("idColaboracion");
                        Stage escenario = new Stage();
                        FXMLLoader loader = 
                        Utilidades.obtenerLoader
                            ("vistas/FXMLRegistrarEstudiantesColaboracion.fxml");
                        Parent root = loader.load();
                        FXMLRegistrarEstudiantesColaboracionController controlador = 
                            loader.getController();
                        controlador.inicializarValores
                      (numeroDeEstudiantes, idColaboracion, this);
                        Scene escena = new Scene(root);
                        escenario.setScene(escena);
                        escenario.setTitle("Registrar estudiantes");
                        escenario.initModality(Modality.APPLICATION_MODAL);
                        escenario.showAndWait();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }else{
                    Utilidades.mostrarAlertaSimple("Error al guardar", "" + 
                        respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
                }
            }else{
                Utilidades.mostrarAlertaSimple("Error al guardar", "" + 
                        respuestaArchivo.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
            }
        }
    }
    
    private void actualizarEstadoOferta(int idOfertaColaboracionExterna){
        HashMap<String, Object> respuestaOferta = 
                OfertaColaboracionExternaDAO.actualizarEstadoOferta(idOfertaColaboracionExterna);
        if((boolean)respuestaOferta.get(Constantes.KEY_ERROR)){
             Utilidades.mostrarAlertaSimple("Error al guardar", "" + 
                        respuestaOferta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnCancelarRegistroColaboracion(ActionEvent event) {
        try{
           Stage ofertasExternas = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLOfertasColaboracionExternas.fxml"));
           Scene escenaOfertasExternas = new Scene(root);
           ofertasExternas.setScene(escenaOfertasExternas);
           ofertasExternas.setTitle("Ofertas colaboracion externas");
           ofertasExternas.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    private boolean validarCamposVacios(){
        boolean esValido = true;
        if(tfNombreProfesorUV.getText().isEmpty() || tfApellidosProfesorUV.getText().isEmpty()
                || tfCorreo.getText().isEmpty()
                || tfNumeroDePersonal.getText().isEmpty()
                || tfTelefono.getText().isEmpty()
                || tfNumeroDeEstudiantes.getText().isEmpty() || dpFechaInicio.getValue() == null
                || dpFechaFin.getValue() == null ||  archivo.getArchivoCol().length == 0
                || cbIdioma.getSelectionModel().isEmpty()){
            esValido=false;
            Utilidades.mostrarAlertaSimple("Datos incompletos", 
                    "Los datos no están completos" , Alert.AlertType.WARNING);
        }
        return esValido;
    }
    
    private boolean validarDatos(){
        boolean datosValidos=true;
        String nombre = tfNombreProfesorUV.getText();
        String apellidos = tfApellidosProfesorUV.getText();
        numeroDeEstudiantes = Integer.parseInt(tfNumeroDeEstudiantes.getText());
        String numeroPersonal = tfNumeroDePersonal.getText();
        String correo = tfCorreo.getText();
        String telefono = tfTelefono.getText();
        LocalDate fechaInicio = dpFechaInicio.getValue();
        LocalDate fechaFin = dpFechaFin.getValue();
        
        if(!Utilidades.correoValido(correo) || telefono.length()!=10 || apellidos.length() > 45
                || numeroDeEstudiantes<2 || numeroDeEstudiantes>30 
                || numeroPersonal.length()!= 15 || nombre.length() > 45
                || fechaInicio.isAfter(fechaFin) || fechaFin.isBefore(fechaFin)){
            datosValidos = false;
            Utilidades.mostrarAlertaSimple("Datos inválidos", 
                    "Los datos son inválidos" , Alert.AlertType.WARNING);
        }
        return datosValidos;
    }

    @FXML
    private void btnAdjuntarSyllabus(ActionEvent event) {
        FileChooser dialogoArchivo = new FileChooser();
        dialogoArchivo.setTitle("Selecciona un archivo");
        FileChooser.ExtensionFilter filtroArchivo = 
                new FileChooser.ExtensionFilter("Archivos PDF (*.pdf)", "*.pdf");
        dialogoArchivo.getExtensionFilters().add(filtroArchivo);
        Stage escenarioActual = (Stage) btnPrincipal.getScene().getWindow();
        File archivoSeleccionado = dialogoArchivo.showOpenDialog(escenarioActual);
        if (archivoSeleccionado != null) {
            try {
                syllabus = Files.readAllBytes(archivoSeleccionado.toPath());
                archivo.setArchivoCol(syllabus);
                archivo.setNombre(archivoSeleccionado.getName());
                archivo.setIdTipoArchivo(1);
                if (syllabus.length > 20 * 1024 * 1024) { 
                    lbTextoArchivo.setText("Peso máximo de 20MB");
                    lbTextoArchivo.setStyle(estiloError);
                }
                lbTextoArchivo.setVisible(true);
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        }
    }
    
    private void cargarIdiomas(){
        idiomas = FXCollections.observableArrayList();
        idiomas.addAll((ArrayList<Idioma>)CatalogoDAO.obtenerIdiomas().get("idiomas"));
        cbIdioma.setItems(idiomas);
    }

    @Override
    public void operacionExitosa(String tipoOperacion) {
        try {
           actualizarEstadoOferta(colaboracion.getIdColaboracion());
           Stage ofertasExternas = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLOfertasColaboracionExternas.fxml"));
           Scene escenaOfertasExternas = new Scene(root);
           ofertasExternas.setScene(escenaOfertasExternas);
           ofertasExternas.setTitle("Ofertas colaboracion externas");
           ofertasExternas.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

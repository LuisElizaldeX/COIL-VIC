/*
* Autor: Erick Utrera Cornejo
* Fecha de creación: 13/06/2024
* Descripción: Controlador para la página registrar colaboracion de oferta UV
*/
package coilvic.controladores;

import coilvic.modelo.dao.ArchivoDAO;
import coilvic.modelo.dao.CatalogoDAO;
import coilvic.modelo.dao.ColaboracionDAO;
import coilvic.modelo.dao.OfertaColaboracionExternaDAO;
import coilvic.modelo.dao.OfertaColaboracionUVDAO;
import coilvic.modelo.pojo.Archivo;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.modelo.pojo.Idioma;
import coilvic.modelo.pojo.ProfesorExterno;
import coilvic.modelo.pojo.ProfesorUV;
import coilvic.modelo.pojo.Universidad;
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


public class FXMLRegistrarColaboracionOfertaUvController 
        extends FXMLPaginaPrincipalProfesorUVController implements ObservadorColaboraciones {
    ProfesorUV profesorUv = SingletonProfesorUV.getInstancia().getProfesorUV();
    private Archivo archivo = new Archivo();
    private int numeroDeEstudiantes;
    private byte[] syllabus;
    String estiloError = "-fx-text-fill: red;";
    private ObservableList<Idioma> idiomas;
    Colaboracion colaboracion;

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
    private TextField tfUniversidad;
    @FXML
    private TextField tfCarrera;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfNombreProfesorExterno;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfNumeroDeEstudiantes;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFin;
    @FXML
    private ComboBox<Idioma> cbIdioma;
    @FXML
    private TextField tfMateria;
    @FXML
    private Label lbTextoArchivo;
    

  
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
                
                Universidad universidad = obtenerInformacionUniversidad();
                OfertaColaboracionExternaDAO.guardarUniversidad(universidad);
                
                ProfesorExterno profesorExterno = obtenerInformacionProfesorExterno(); 
                OfertaColaboracionExternaDAO.guardarProfesorExterno(profesorExterno); 
                
                HashMap<String, Object> respuestaColaboracion = 
                ColaboracionDAO.guardarColaboracionUv(colaboracion, idArchivo);
                
                if( !(boolean)respuestaColaboracion.get(Constantes.KEY_ERROR)){
                    try {
                        int idColaboracion = (int) respuestaColaboracion.get("idColaboracion");
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
                        respuestaColaboracion.get(Constantes.KEY_MENSAJE), 
                            Alert.AlertType.ERROR);
                }
            }else{
                Utilidades.mostrarAlertaSimple("Error al guardar", "" + 
                        respuestaArchivo.get(Constantes.KEY_MENSAJE), 
                        Alert.AlertType.ERROR);
            }
        }
    }
    
    
    private void actualizarEstadoOferta(int idOfertaColaboracionUv){
        HashMap<String, Object> respuestaOferta = 
                OfertaColaboracionUVDAO.actualizarEstadoOferta(idOfertaColaboracionUv);
        
        if((boolean)respuestaOferta.get(Constantes.KEY_ERROR)){
             Utilidades.mostrarAlertaSimple("Error al guardar", "" + 
                        respuestaOferta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnCancelarRegistroColaboracion(ActionEvent event) {
        try{
           Stage ofertasUv = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLOfertasColaboracionUv.fxml"));
           Scene escenaOfertasUv = new Scene(root);
           ofertasUv.setScene(escenaOfertasUv);
           ofertasUv.setTitle("Ofertas colaboracion externas");
           ofertasUv.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
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
    
    
    private boolean validarCamposVacios(){
        boolean esValido = true;
        if(tfNombreProfesorExterno.getText().trim().isEmpty() 
                || tfApellidosProfesorExterno.getText().trim().isEmpty()
                || tfPais.getText().trim().isEmpty()
                || tfCorreo.getText().trim().isEmpty()
                || tfUniversidad.getText().trim().isEmpty()
                || tfCarrera.getText().trim().isEmpty()
                || tfMateria.getText().trim().isEmpty()
                || tfTelefono.getText().trim().isEmpty()
                || tfNumeroDeEstudiantes.getText().trim().isEmpty() 
                || dpFechaInicio.getValue() == null
                || dpFechaFin.getValue() == null ||  archivo.getNombre() == null
                || cbIdioma.getValue() == null){
            esValido=false;
            Utilidades.mostrarAlertaSimple("Datos incompletos", 
                    "Los datos no están completos" , Alert.AlertType.WARNING);
        }
        return esValido;
    }
    
    
    private boolean validarDatos(){
        boolean datosValidos=true;
        String nombre = tfNombreProfesorExterno.getText();
        String apellidos = tfApellidosProfesorExterno.getText();
        numeroDeEstudiantes = Integer.parseInt(tfNumeroDeEstudiantes.getText());
        String correo = tfCorreo.getText();
        String telefono = tfTelefono.getText();
        LocalDate fechaInicio = dpFechaInicio.getValue();
        LocalDate fechaFin = dpFechaFin.getValue();
        
        if(!Utilidades.correoValido(correo) || telefono.length()!=10 || apellidos.length() > 45
                || numeroDeEstudiantes<2 || numeroDeEstudiantes>30 
                || nombre.length() > 45 || fechaInicio.isAfter(fechaFin) 
                || fechaFin.isBefore(fechaFin)){
            datosValidos = false;
            Utilidades.mostrarAlertaSimple("Datos inválidos", 
                    "Los datos son inválidos" , Alert.AlertType.WARNING);
        }
        return datosValidos;
    }
    
    
    private void cargarIdiomas(){
        idiomas = FXCollections.observableArrayList();
        idiomas.addAll((ArrayList<Idioma>)CatalogoDAO.obtenerIdiomas().get("idiomas"));
        cbIdioma.setItems(idiomas);
    }
    
    
    private ProfesorExterno obtenerInformacionProfesorExterno(){
        ProfesorExterno profesorExterno = new ProfesorExterno();
       
        profesorExterno.setNombre(tfNombreProfesorExterno.getText());
        profesorExterno.setApellidos(tfApellidosProfesorExterno.getText());
        profesorExterno.setPais(tfPais.getText());
        profesorExterno.setCarrera(tfCarrera.getText());
        profesorExterno.setMateria(tfMateria.getText());
        profesorExterno.setCorreo(tfCorreo.getText());
        profesorExterno.setTelefono(tfTelefono.getText());
        return profesorExterno;
    }
    
    
    private Universidad obtenerInformacionUniversidad(){
        Universidad universidad = new Universidad();
        universidad.setNombre(tfUniversidad.getText());
        universidad.setIdUniversidad(universidad.getIdUniversidad());

        return universidad;
    }
    
    
    @Override
    public void operacionExitosa(String tipoOperacion) {
        try {
           actualizarEstadoOferta(colaboracion.getIdColaboracion());
           Stage ofertasUv = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLOfertasColaboracionUv.fxml"));
           Scene escenaOfertasUv = new Scene(root);
           ofertasUv.setScene(escenaOfertasUv);
           ofertasUv.setTitle("Ofertas colaboracion UV");
           ofertasUv.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

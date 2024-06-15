/*
* Autor: Erick Utrera Cornejo
* Fecha de creación: 14/06/2024
* Descripción: Controlador para el registro de una colaboracion directa  
*/
package coilvic.controladores;

import coilvic.modelo.dao.ArchivoDAO;
import coilvic.modelo.dao.CatalogoDAO;
import coilvic.modelo.dao.ColaboracionDAO;
import coilvic.modelo.dao.ObtenerOfertaColaboracionUVDAO;
import coilvic.modelo.dao.OfertaColaboracionExternaDAO;
import coilvic.modelo.dao.OfertaColaboracionUVDAO;
import coilvic.modelo.pojo.Archivo;
import coilvic.modelo.pojo.AreaAcademica;
import coilvic.modelo.pojo.Campus;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.modelo.pojo.Dependencia;
import coilvic.modelo.pojo.ExperienciaEducativa;
import coilvic.modelo.pojo.Idioma;
import coilvic.modelo.pojo.Periodo;
import coilvic.modelo.pojo.ProfesorExterno;
import coilvic.modelo.pojo.ProfesorUV;
import coilvic.modelo.pojo.ProgramaEducativo;
import coilvic.modelo.pojo.Universidad;
import coilvic.observador.ObservadorColaboraciones;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.SingletonProfesorUV;
import coilvic.utilidades.Utilidades;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class FXMLRegistrarColaboracionDirectaController 
        extends FXMLPaginaPrincipalProfesorUVController implements ObservadorColaboraciones {
    ProfesorUV profesorUv = SingletonProfesorUV.getInstancia().getProfesorUV();
    private ObservableList<ProgramaEducativo> programasEducativos;
    private ObservableList<AreaAcademica> areasAcademicas;
    private ObservableList<Campus> campus;
    private Archivo archivo = new Archivo();
    private int numeroDeEstudiantes;
    private ObservableList<Idioma> idiomas;
    private byte[] syllabus;
    String estiloError = "-fx-text-fill: red;";
    
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btnPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private Label lbNombreProfesorUv;
    @FXML
    private Label lbNumeroDePersonal;
    @FXML
    private Label lbTelefono;
    @FXML
    private Label lbApellidosProfesorUv;
    @FXML
    private Label lbCorreo;
    @FXML
    private TextField tfNombreColaboracion;
    @FXML
    private TextField tfNombreDependencia;
    @FXML
    private ComboBox<Campus> cbCampus;
    @FXML
    private TextField tfMunicipio;
    @FXML
    private ComboBox<AreaAcademica> cbAreaAcademica;
    @FXML
    private ComboBox<ProgramaEducativo> cbProgramaEducativo;
    @FXML
    private TextField tfExperienciaEducativa;
    @FXML
    private TextField tfNombreProfesorExterno;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfCarrera;
    @FXML
    private TextField tfApellidos;
    @FXML
    private TextField tfUniversidad;
    @FXML
    private TextField tfPais;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfNumeroDeEstudiantes;
    @FXML
    private TextField tfMateria;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private ComboBox<Idioma> cbIdioma;
    @FXML
    private Label lbTextoArchivo;
    @FXML
    private DatePicker dpFechaFin;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(profesor.toString());
        inicializarValoresProfesorUv();
        cargarIdiomas();
        cargarCampus();
        configurarSeleccionCampus();  
         
    }    
        
    private void inicializarValoresProfesorUv(){
        ProfesorUV profesor = SingletonProfesorUV.getInstancia().getProfesorUV();
        lbNombreProfesorUv.setText(profesor.getNombre());
        lbApellidosProfesorUv.setText(profesor.getApellidos());
        lbNumeroDePersonal.setText(Integer.toString(profesor.getNumeroPersonal()));
        lbCorreo.setText(profesor.getCorreo());
        lbTelefono.setText(profesor.getTelefono()); 
    }

    @FXML
    private void btnCancelarRegistroColaboracion(ActionEvent event) {
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
    private void btnAceptarRegistroColaboracion(ActionEvent event) {
        if(validarCamposVacios() && validarDatos()){
            HashMap<String, Object> respuestaArchivo = ArchivoDAO.subirArchivo(archivo);
            
            if (!(boolean) respuestaArchivo.get(Constantes.KEY_ERROR)) {
                int idArchivo = (int) respuestaArchivo.get("idArchivo");
                
                Dependencia dependencia = obtenerInformacionDependencia();
                OfertaColaboracionUVDAO.guardarDependencia(dependencia);
                
                Periodo periodo = obtenerInformacionPeriodo();
                OfertaColaboracionUVDAO.guardarPeriodo(periodo);
                
                ExperienciaEducativa experienciaEducativa = 
                        obtenerInformacionExperienciaEducativa();
                OfertaColaboracionUVDAO.guardarExperienciaEducativa(experienciaEducativa); 
                
                Universidad universidad = obtenerInformacionUniversidad();
                OfertaColaboracionExternaDAO.guardarUniversidad(universidad);
                
                ProfesorExterno profesorExterno = obtenerInformacionProfesorExterno();
                OfertaColaboracionExternaDAO.guardarProfesorExterno(profesorExterno);
                
                Colaboracion colaboracion = inicializarValoresColaboracion();
                
                HashMap<String, Object> respuestaColaboracion = 
                ColaboracionDAO.guardarColaboracion(colaboracion, idArchivo);
                
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
    
    
    @FXML
    private void btnAdjuntarSyllabu(ActionEvent event) {
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
                || tfNombreColaboracion.getText().trim().isEmpty()
                || tfNombreDependencia.getText().trim().isEmpty()
                || tfMunicipio.getText().trim().isEmpty()
                || tfExperienciaEducativa.getText().trim().isEmpty()
                || tfApellidos.getText().trim().isEmpty()
                || tfPais.getText().trim().isEmpty()
                || tfCorreo.getText().trim().isEmpty()
                || tfUniversidad.getText().trim().isEmpty()
                || tfCarrera.getText().trim().isEmpty()
                || tfMateria.getText().trim().isEmpty()
                || tfTelefono.getText().trim().isEmpty()
                || tfNumeroDeEstudiantes.getText().trim().isEmpty() 
                || dpFechaInicio.getValue() == null
                || dpFechaFin.getValue() == null ||  archivo.getNombre() == null
                || cbIdioma.getValue() == null
                || cbCampus.getValue() == null
                || cbAreaAcademica.getValue() == null
                || cbProgramaEducativo.getValue() == null){
            esValido=false;
            Utilidades.mostrarAlertaSimple("Datos incompletos", 
                    "Los datos no están completos" , Alert.AlertType.WARNING);
        }
        return esValido;
    }
    
    
    private boolean validarDatos(){
        boolean datosValidos=true;
        String nombre = tfNombreProfesorExterno.getText();
        String apellidos = tfApellidos.getText();
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
    
    
    private Dependencia obtenerInformacionDependencia(){
        Dependencia dependencia = new Dependencia();
        dependencia.setNombre(tfNombreDependencia.getText());
        dependencia.setMunicipio(tfMunicipio.getText());
        dependencia.setIdCampus
        (cbCampus.getSelectionModel().getSelectedItem().getIdCampus());
        dependencia.setIdProgramaEducativo
        (cbProgramaEducativo.getSelectionModel().
                getSelectedItem().getIdProgramaEducativo());

        return dependencia;
    }
    
    
    private ExperienciaEducativa obtenerInformacionExperienciaEducativa(){
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        experienciaEducativa.setNombre(tfExperienciaEducativa.getText());

        return experienciaEducativa;
    }
    
    
    private Periodo obtenerInformacionPeriodo(){
        Periodo periodo = new Periodo();
        periodo.setFechaInicio(dpFechaInicio.getValue().toString());
        periodo.setFechaFin(dpFechaFin.getValue().toString());

        return periodo;
    }
    
    
    private Universidad obtenerInformacionUniversidad(){
        Universidad universidad = new Universidad();
        universidad.setNombre(tfUniversidad.getText());
        universidad.setIdUniversidad(universidad.getIdUniversidad());

        return universidad;
    }
    
    
    private ProfesorExterno obtenerInformacionProfesorExterno(){
        ProfesorExterno profesorExterno = new ProfesorExterno();
        profesorExterno.setNombre(tfNombreProfesorExterno.getText());
        profesorExterno.setApellidos(tfApellidos.getText());
        profesorExterno.setPais(tfPais.getText());
        profesorExterno.setCarrera(tfCarrera.getText());
        profesorExterno.setMateria(tfMateria.getText());
        profesorExterno.setCorreo(tfCorreo.getText());
        profesorExterno.setTelefono(tfTelefono.getText());
        
        return profesorExterno;
    }
    
    
    private Colaboracion inicializarValoresColaboracion(){
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setNombre(tfNombreColaboracion.getText());
        colaboracion.setIdProfesorUV(profesor.getIdProfesorUV());
        colaboracion.setFechaInicio(dpFechaInicio.getValue().toString());
        colaboracion.setFechaFin(dpFechaFin.getValue().toString());
        colaboracion.setIdIdioma
        (cbIdioma.getSelectionModel().getSelectedItem().getIdIdioma());
        
        return colaboracion;
    }
  
    
    private void cargarCampus(){
        campus = FXCollections.observableArrayList();
        campus.addAll((ArrayList<Campus>)
                ObtenerOfertaColaboracionUVDAO.obtenerCampus().get("campus"));
        cbCampus.setItems(campus); 
    }
    
    
    private void configurarSeleccionCampus(){
        cbCampus.valueProperty().addListener(new ChangeListener<Campus>(){
            
            @Override
            public void changed(ObservableValue<? 
                    extends Campus> observable, Campus oldValue, Campus newValue){
                if(newValue != null ){
                    cargarAreasAcademicas(newValue.getIdCampus());
                    cbProgramaEducativo.getItems().clear();
                }
            }
        });
    }
    
    
    private void cargarAreasAcademicas(int idCampus){
        areasAcademicas = FXCollections.observableArrayList();
        areasAcademicas.addAll((ArrayList<AreaAcademica>)ObtenerOfertaColaboracionUVDAO.
                obtenerAreasAcademicas(idCampus).get("areasAcademicas"));
        cbAreaAcademica.setItems(areasAcademicas);
        configurarSeleccionAreasAcademicas(idCampus);
    }
    
    
    private void configurarSeleccionAreasAcademicas(int idCampus){                   
        cbAreaAcademica.valueProperty().addListener(new ChangeListener<AreaAcademica>(){
            @Override
            public void changed(ObservableValue<? extends AreaAcademica> observable, 
                    AreaAcademica oldValue, AreaAcademica newValue){
                if(newValue != null ){
                    cargarProgramasEducativos(newValue.getIdAreaAcademica(), idCampus);
                }
            }
        });
    }
    
    
    private void cargarProgramasEducativos(int idAreaAcademica, int idCampus){
        programasEducativos = FXCollections.observableArrayList();
        programasEducativos.addAll((ArrayList<ProgramaEducativo>)
                ObtenerOfertaColaboracionUVDAO.obtenerProgramasEducativos
        (idAreaAcademica, idCampus).get("programasEducativos"));
        cbProgramaEducativo.setItems(programasEducativos);
        
    }
    
    
    @Override
    public void operacionExitosa(String tipoOperacion) {
        try {
           Stage escenarioPrincipal = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLPaginaPrincipalProfesorUV.fxml"));
           Scene escenaPrincipal = new Scene(root);
           escenarioPrincipal.setScene(escenaPrincipal);
           escenarioPrincipal.setTitle("Pagina principal ProfesorUv");
           escenarioPrincipal.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




       
    
}

/*
* Autor: Jose Antonio Zarate De Leo
* Fecha de creación: 29/05/2024
* Descripción: Controlador para el registro de una colaboracion directa  
*/
package coilvic.controladores;

import coilvic.modelo.dao.ColaboracionDAO;
import coilvic.modelo.dao.ObtenerOfertaColaboracionUVDAO;
import coilvic.modelo.pojo.AreaAcademica;
import coilvic.modelo.pojo.Campus;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.modelo.pojo.Dependencia;
import coilvic.modelo.pojo.ExperienciaEducativa;
import coilvic.modelo.pojo.ProfesorExterno;
import coilvic.modelo.pojo.ProfesorUV;
import coilvic.modelo.pojo.ProgramaEducativo;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.SingletonProfesorUV;
import coilvic.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zS21022065
 */
public class FXMLRegistrarColaboracionDirectaController extends FXMLPaginaPrincipalProfesorUVController {
    
    private int idProfesorUV;
    private int idProfesorExterno;
    private ObservableList<ProgramaEducativo> programasEducativos;
    private ObservableList<AreaAcademica> areasAcademicas;
    private ObservableList<Campus> campus;
    
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btnPrincipal;
    @FXML
    private Label lbNombreSesion;
    ProfesorUV profesorUv = SingletonProfesorUV.getInstancia().getProfesorUV();
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
    private TextField TfMateriaE;
    @FXML
    private TextField tfNombreProfesorExterno;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfCarrera;
    @FXML
    private TextField tfApellidosProfesorExterno;
    @FXML
    private TextField tfUniversidad;
    @FXML
    private TextField tfPais;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfIdioma;
    @FXML
    private TextField tfNumeroDeEstudiantes;
    @FXML
    private DatePicker dpFechaDeInicio;
    @FXML
    private DatePicker dpFechaDeCierre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(profesor.toString());
        inicializarValoresProfesorUv();
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
        try {
        Stage stage = (Stage) btnPrincipal.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/coilvic/vistas/FXMLPaginaPrincipalProfesorUV.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Página Principal Profesor UV");
        stage.show();
    } catch (IOException e) {
        System.out.println("Error al cargar la vista: " + e.getMessage());
    }
        
    }
    
   private boolean validarCampos() {
    // Verificar que los TextField no estén vacíos
    if (esTextoVacio(tfNombreColaboracion) ||
        esTextoVacio(tfNombreDependencia) ||
        esTextoVacio(tfMunicipio) ||
        esTextoVacio(tfExperienciaEducativa) ||
        esTextoVacio(TfMateriaE) ||
        esTextoVacio(tfNombreProfesorExterno) ||
        esTextoVacio(tfTelefono) ||
        esTextoVacio(tfCarrera) ||
        esTextoVacio(tfApellidosProfesorExterno) ||
        esTextoVacio(tfUniversidad) ||
        esTextoVacio(tfPais) ||
        esTextoVacio(tfCorreo) ||
        esTextoVacio(tfIdioma) ||
        esTextoVacio(tfNumeroDeEstudiantes)) {
        return false;
    }
    
    // Verificar que los ComboBox tengan una selección
    if (cbCampus.getValue() == null ||
        cbAreaAcademica.getValue() == null ||
        cbProgramaEducativo.getValue() == null) {
        return false;
    }
    
    // Verificar que los DatePicker tengan una fecha seleccionada
    if (dpFechaDeInicio.getValue() == null ||
        dpFechaDeCierre.getValue() == null) {
        return false;
    }
    
    // Todos los campos están válidos
    return true;
}

private boolean esTextoVacio(TextField textField) {
    return textField.getText().trim().isEmpty();
}





    
    


    @FXML
private void btnAceptarRegistroColaboracion(ActionEvent event) throws SQLException {
   if (validarCampos()) {
        try {
            // Obtener los valores de los campos
            String nombreColaboracion = tfNombreColaboracion.getText();
            String municipio = tfMunicipio.getText();
            String experienciaEducativa = tfExperienciaEducativa.getText();
            String materiaE = TfMateriaE.getText(); 
            String idioma = tfIdioma.getText();
            int numeroEstudiantes = Integer.parseInt(tfNumeroDeEstudiantes.getText());
            String fechaInicio = dpFechaDeInicio.getValue().toString();
            String fechaFin = dpFechaDeCierre.getValue().toString();
            
            // Guardar la colaboración utilizando el método guardarColaboracion de ColaboracionDAO
            HashMap<String, Object> resultado = ColaboracionDAO.guardarColaboracion(
                profesorUv.getIdProfesorUV(),
                nombreColaboracion,
                municipio,
                experienciaEducativa,
                materiaE,
                idioma,
                numeroEstudiantes,
                fechaInicio,
                fechaFin
            );
            
            // Verificar si se guardó la colaboración correctamente
            boolean error = (boolean) resultado.get(Constantes.KEY_ERROR);
            if (!error) {
                Utilidades.mostrarAlertaSimple("Éxito", "La colaboración se ha guardado exitosamente", Alert.AlertType.INFORMATION);
                limpiarFormulario();
            } else {
                String mensajeError = (String) resultado.get(Constantes.KEY_MENSAJE);
                Utilidades.mostrarAlertaSimple("Error", "Hubo un error al guardar la colaboración: " + mensajeError, Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejar la excepción adecuadamente
            Utilidades.mostrarAlertaSimple("Error", "Hubo un error al guardar la colaboración: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    } else {
        Utilidades.mostrarAlertaSimple("Error", "Los datos son inválidos o están incompletos.", Alert.AlertType.ERROR);
    }
    
}
private void limpiarFormulario() {
    tfNombreColaboracion.clear();
    // Limpiar otros campos...
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
  private Dependencia obtenerInformacionDependencia(){
        Dependencia dependencia = new Dependencia();
        dependencia.setNombre(tfNombreDependencia.getText());
        dependencia.setMunicipio(tfMunicipio.getText());
        dependencia.setIdCampus(cbCampus.getSelectionModel().getSelectedItem().getIdCampus());
        dependencia.setIdProgramaEducativo
        (cbProgramaEducativo.getSelectionModel().getSelectedItem().getIdProgramaEducativo());

        return dependencia;
    }
  
      private ExperienciaEducativa obtenerInformacionExperienciaEducativa(){
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        experienciaEducativa.setNombre(tfExperienciaEducativa.getText());
        

        return experienciaEducativa;
    }
      
       private ProfesorExterno obtenerInformacionProfesorExterno(){
        ProfesorExterno profesorExterno = new ProfesorExterno();
       
        profesorExterno.setNombre(tfNombreProfesorExterno.getText());
        profesorExterno.setApellidos(tfApellidosProfesorExterno.getText());
        profesorExterno.setPais(tfPais.getText());
        profesorExterno.setCarrera(tfCarrera.getText());
        profesorExterno.setMateria(TfMateriaE.getText());
        profesorExterno.setCorreo(tfCorreo.getText());
        profesorExterno.setTelefono(tfTelefono.getText());
        
        return profesorExterno;
    }
       
    
}

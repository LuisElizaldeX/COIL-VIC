/*
* Autor: Erick Utrera Cornejo
* Fecha de creación: 29/05/2024
* Descripción: Controlador para registrar oferta de colaboración UV 
*/

package coilvic.controladores;

import coilvic.modelo.dao.ObtenerOfertaColaboracionUVDAO;
import coilvic.modelo.dao.OfertaColaboracionUVDAO;
import coilvic.modelo.pojo.AreaAcademica;
import coilvic.modelo.pojo.AreaAcademica_Campus;
import coilvic.modelo.pojo.Campus;
import coilvic.modelo.pojo.Dependencia;
import coilvic.modelo.pojo.ExperienciaEducativa;
import coilvic.modelo.pojo.OfertaColaboracionUV;
import coilvic.modelo.pojo.Periodo;
import coilvic.modelo.pojo.ProfesorUV;
import coilvic.modelo.pojo.ProgramaEducativo;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.SingletonProfesorUV;
import coilvic.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class FXMLRegistrarOfertaColaboracionUvController 
        extends FXMLPaginaPrincipalProfesorUVController {
    private ObservableList<AreaAcademica> areasAcademicas;
    private ObservableList<ProgramaEducativo> programasEducativos;
    private ObservableList<Campus> campus;
    
    @FXML
    private Label lbNombreSesion;
    ProfesorUV profesorUv = SingletonProfesorUV.getInstancia().getProfesorUV();
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btnPrincipal;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbApellidos;
    @FXML
    private Label lbNumeroPersonal;
    @FXML
    private Label lbCorreo;
    @FXML
    private Label lbTelefono;
    @FXML
    private TextField tfNombre;
    @FXML
    private ComboBox<AreaAcademica> cbAreaAcademica;
    @FXML
    private ComboBox<ProgramaEducativo> cbProgramaEducativo;
    @FXML
    private TextArea tfDescripcion;
    @FXML
    private TextField tfExperienciaEducativa;
    @FXML
    private TextField tfCreditos;
    @FXML
    private TextArea tfDescripcionEe;
    @FXML
    private ComboBox<Campus> cbCampus;
    @FXML
    private TextField tfNombreDependencia;
    @FXML
    private TextField tfMunicipio;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFin;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(profesorUv.toString());
        inicializarValoresProfesorUv();
        cargarCampus();
        configurarSeleccionCampus();   
    }

    
    private void inicializarValoresProfesorUv(){
        ProfesorUV profesor = SingletonProfesorUV.getInstancia().getProfesorUV();
        lbNombre.setText(profesor.getNombre());
        lbApellidos.setText(profesor.getApellidos());
        lbNumeroPersonal.setText(Integer.toString(profesor.getNumeroPersonal()));
        lbCorreo.setText(profesor.getCorreo());
        lbTelefono.setText(profesor.getTelefono()); 
    }
    
    
    @FXML
    private void btnClicRegistrar(ActionEvent event) {
        if(validarCampos()){
            Periodo periodo = obtenerInformacionPeriodo();
            Dependencia dependencia = obtenerInformacionDependencia();
            ExperienciaEducativa experienciaEducativa = obtenerInformacionExperienciaEducativa();
            OfertaColaboracionUV ofertaColaboracionUV = obtenerInformacionOfertaColaboracionUV();
            
            guardarOfertaColaboracionUV(dependencia, periodo, experienciaEducativa, 
                    ofertaColaboracionUV);
            clicBtnIrPaginaPrincipal(event);
            
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Los datos son invalidos", 
                    Alert.AlertType.ERROR); 
        }
    }

    @FXML
    private void btnClicCancelar(ActionEvent event) {
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
    
    
    private void guardarOfertaColaboracionUV
        (Dependencia dependencia, Periodo periodo, ExperienciaEducativa experienciaEducativa, 
            OfertaColaboracionUV ofertaColaboracionUV){
        HashMap<String, Object> respuestaDependencia = 
                OfertaColaboracionUVDAO.guardarDependencia(dependencia);
        HashMap<String, Object> respuestaPeriodo = OfertaColaboracionUVDAO.guardarPeriodo(periodo);
        HashMap<String, Object> respuestaExperienciaEducativa = 
                OfertaColaboracionUVDAO.guardarExperienciaEducativa(experienciaEducativa);
        HashMap<String, Object> respuestaOfertaColaboracionUV = 
                OfertaColaboracionUVDAO.guardarOfertaColaboracionUv(ofertaColaboracionUV);

        if(!(boolean)respuestaOfertaColaboracionUV.get(Constantes.KEY_ERROR)){
            Utilidades.mostrarAlertaSimple("Oferta UV guardada", ""+
                    respuestaOfertaColaboracionUV.get(Constantes.KEY_MENSAJE),                    
                    Alert.AlertType.INFORMATION);
            
        }else{
            Utilidades.mostrarAlertaSimple("Error al guardar", ""+
                    respuestaOfertaColaboracionUV.get(Constantes.KEY_MENSAJE), 
                    Alert.AlertType.ERROR);  
        }
    }
    
    
    private OfertaColaboracionUV obtenerInformacionOfertaColaboracionUV (){
        OfertaColaboracionUV ofertaColaboracionUV = new OfertaColaboracionUV();
        ofertaColaboracionUV.setNombre(tfNombre.getText());
        ofertaColaboracionUV.setDescripcion(tfDescripcion.getText());
        ofertaColaboracionUV.setIdProfesorUV(profesor.getIdProfesorUV());

        return ofertaColaboracionUV;
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
        experienciaEducativa.setCreditos(Integer.parseInt(tfCreditos.getText()));
        experienciaEducativa.setDescripcion(tfDescripcionEe.getText());

        return experienciaEducativa;
    }
    
    
    private Periodo obtenerInformacionPeriodo(){
        Periodo periodo = new Periodo();
        periodo.setFechaInicio(dpFechaInicio.getValue().toString());
        periodo.setFechaFin(dpFechaFin.getValue().toString());

        return periodo;
    }
    
    
    private boolean validarCampos(){    
        if(tfNombre.getText().trim().isEmpty() || tfNombreDependencia.getText().trim().isEmpty() || 
                dpFechaInicio.getValue() == null || dpFechaFin.getValue() == null || 
                tfMunicipio.getText().trim().isEmpty() || cbCampus.getValue() == null 
                || cbAreaAcademica.getValue() == null || 
                cbProgramaEducativo.getValue() == null || tfDescripcion.getText().trim().isEmpty() 
                || tfExperienciaEducativa.getText().trim().isEmpty() || 
                tfCreditos.getText().trim().isEmpty() || tfCreditos.getText().trim().isEmpty() 
                || validarCreditos()==false || tfDescripcionEe.getText().trim().isEmpty()){
            
            return false;
            
        }else{
            return true; 
        }       
    }
    
    
    private boolean validarCreditos() {
        String input = tfCreditos.getText();
        try {
            Integer.parseInt(input);
            return true;
            
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

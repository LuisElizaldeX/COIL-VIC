/*
* Autor: Erick Utrera Cornejo
* Fecha de creación: 29/05/2024
* Descripción: Controlador para modificar oferta de colaboración UV
*/
package coilvic.controladores;

import coilvic.modelo.dao.ObtenerOfertaColaboracionUVDAO;
import coilvic.modelo.dao.OfertaColaboracionUVDAO;
import coilvic.modelo.pojo.AreaAcademica;
import coilvic.modelo.pojo.AreaAcademica_Campus;
import coilvic.modelo.pojo.Campus;
import coilvic.modelo.pojo.OfertaColaboracionUV;
import coilvic.modelo.pojo.ProgramaEducativo;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.stage.Stage;


public class FXMLModificarOfertaColaboracionUvController 
        extends FXMLPaginaPrincipalProfesorUVController {
    private ObservableList<AreaAcademica> areasAcademicas;
    private ObservableList<ProgramaEducativo> programasEducativos;
    private ObservableList<Campus> campus;
    private OfertaColaboracionUV ofertaColaboracionUV;

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btnPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfNombreDependencia;
    @FXML
    private TextField tfMunicipio;
    @FXML
    private ComboBox<Campus> cbCampus;
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
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFin;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCampus();
        configurarSeleccionCampus();
    }

    
    public void cargarInformacionOferta(OfertaColaboracionUV ofertaColaboracionUV){
        tfNombre.setText(ofertaColaboracionUV.getNombre());
        tfNombreDependencia.setText(ofertaColaboracionUV.getNombreDependencia());
        tfMunicipio.setText(ofertaColaboracionUV.getMunicipio());
        tfDescripcion.setText(ofertaColaboracionUV.getDescripcion());
        tfExperienciaEducativa.setText(ofertaColaboracionUV.getExperienciaEducativa());
        tfCreditos.setText(ofertaColaboracionUV.getCreditos());
        tfDescripcionEe.setText(ofertaColaboracionUV.getDescripcionEe());
        
        LocalDate fechaCalendarioInicio = LocalDate.parse(ofertaColaboracionUV.getFechaInicio(), 
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dpFechaInicio.setValue(fechaCalendarioInicio);
        
        LocalDate fechaCalendarioFin = LocalDate.parse(ofertaColaboracionUV.getFechaFin(), 
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dpFechaFin.setValue(fechaCalendarioFin);
        
        Campus campusSeleccionado = new Campus(ofertaColaboracionUV.getIdCampus(), 
                ofertaColaboracionUV.getCampus()); 
        for(Campus elemento : cbCampus.getItems()){
            if(elemento.getIdCampus() == campusSeleccionado.getIdCampus()){
                cbCampus.setValue(elemento);
                break;
            }
        }
        
        AreaAcademica areacademicaSeleccionada = 
                new AreaAcademica(ofertaColaboracionUV.getIdAreaAcademica(), 
                ofertaColaboracionUV.getNombreAreaAcademica());
        for(AreaAcademica elemento : cbAreaAcademica.getItems()){
            if(elemento.getIdAreaAcademica() == areacademicaSeleccionada.getIdAreaAcademica()){
                cbAreaAcademica.setValue(elemento);
                break;
            }
        }
        
        ProgramaEducativo programaEducativoSeleccionado = new ProgramaEducativo();
        programaEducativoSeleccionado.setIdProgramaEducativo
        (ofertaColaboracionUV.getIdProgramaEducativo());
        programaEducativoSeleccionado.setNombre
        (ofertaColaboracionUV.getNombreProgramaEducativo());
        for(ProgramaEducativo elemento : cbProgramaEducativo.getItems()){
            if(elemento.getIdProgramaEducativo() 
                    == programaEducativoSeleccionado.getIdProgramaEducativo()){
                cbProgramaEducativo.setValue(elemento);
                break;
            }
        }

    }
    
    
    public OfertaColaboracionUV obtenerInformacionOferta(){
        ofertaColaboracionUV.setNombre(tfNombre.getText());
        ofertaColaboracionUV.setNombreDependencia(tfNombreDependencia.getText());
        ofertaColaboracionUV.setMunicipio(tfMunicipio.getText());
        ofertaColaboracionUV.setIdCampus
        (cbCampus.getSelectionModel().getSelectedItem().getIdCampus());
        ofertaColaboracionUV.setIdProgramaEducativo(
        cbProgramaEducativo.getSelectionModel().getSelectedItem().getIdProgramaEducativo());
        ofertaColaboracionUV.setDescripcion(tfDescripcion.getText());
        ofertaColaboracionUV.setExperienciaEducativa(tfExperienciaEducativa.getText());
        ofertaColaboracionUV.setCreditos(tfCreditos.getText());
        ofertaColaboracionUV.setDescripcionEe(tfDescripcionEe.getText());
        ofertaColaboracionUV.setFechaInicio(dpFechaInicio.getValue().toString());
        ofertaColaboracionUV.setFechaFin(dpFechaFin.getValue().toString());
        
        return ofertaColaboracionUV;
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
        areasAcademicas.addAll((ArrayList<AreaAcademica>)
                ObtenerOfertaColaboracionUVDAO.obtenerAreasAcademicas(idCampus).get
        ("areasAcademicas"));
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
    
    
    public void setOfertaColaboracionUV(OfertaColaboracionUV ofertaColaboracionUV){
        this.ofertaColaboracionUV = ofertaColaboracionUV;
    }
    

    @FXML
    private void btnClicAceptar(ActionEvent event) {
        if(validarCampos()){
            OfertaColaboracionUV ofertaUv = obtenerInformacionOferta();
            actualizarOfertaColaboracionUv(ofertaUv);
            
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Los datos son invalidos", 
                    Alert.AlertType.ERROR); 
        }
    }

    
    @FXML
    private void btnClicCancelar(ActionEvent event) {
        irOfertaUv(ofertaColaboracionUV); 
    }
    
    
    private void actualizarOfertaColaboracionUv(OfertaColaboracionUV ofertaUv){
         HashMap<String, Object> respuesta = 
                 OfertaColaboracionUVDAO.actualizarOfertaColaboracionUV(ofertaUv);

        if(!(boolean)respuesta.get(Constantes.KEY_ERROR)){
            Utilidades.mostrarAlertaSimple("Oferta actualizada", 
                    "Oferta de colaboracion COIL modificada con éxito", 
                    Alert.AlertType.INFORMATION);
            
            try{
                Stage ofertasUv = (Stage) imgCerrarSesion.getScene().getWindow();
                 Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLOfertasColaboracionUv.fxml"));
                Scene escenaOfertasUv = new Scene(root);
                ofertasUv.setScene(escenaOfertasUv);
                ofertasUv.setTitle("Ofertas colaboracion UV");
                ofertasUv.show();
            
            }catch(IOException e){
                System.out.println("Error: "+e.getMessage());
            }
            
        }else{
            Utilidades.mostrarAlertaSimple("Error al actualizar", ""+
                    respuesta.get(Constantes.KEY_MENSAJE), 
                    Alert.AlertType.ERROR);
        } 
    }
    
    
    private boolean validarCampos(){    
        if(tfNombre.getText().trim().isEmpty() || tfNombreDependencia.getText().trim().isEmpty() 
                || dpFechaInicio.getValue() == null || dpFechaFin.getValue() == null || 
                tfMunicipio.getText().trim().isEmpty() || cbCampus.getValue() == null 
                || cbAreaAcademica.getValue() == null || cbProgramaEducativo.getValue() == null || 
                tfDescripcion.getText().trim().isEmpty() || 
                tfExperienciaEducativa.getText().trim().isEmpty() || 
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
    
    
    private void irOfertaUv(OfertaColaboracionUV ofertaUvSeleccionada){
        try{
           Stage ofertaUV = (Stage) imgCerrarSesion.getScene().getWindow();
           
           FXMLLoader loader = Utilidades.obtenerLoader
        ("vistas/FXMLConsultarOfertaColaboracionUv_Profesor.fxml");
           Parent root = loader.load();
            
            FXMLConsultarOfertaColaboracionUv_ProfesorController controlador=loader.getController();
            controlador.inicializarValores(ofertaUvSeleccionada);
            controlador.setOfertaColaboracionUV(ofertaUvSeleccionada);
           
           Scene escenaOfertaUV = new Scene(root);
           ofertaUV.setScene(escenaOfertaUV);
           ofertaUV.setTitle("Oferta de colaboración UV");
           ofertaUV.show();
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    } 
}

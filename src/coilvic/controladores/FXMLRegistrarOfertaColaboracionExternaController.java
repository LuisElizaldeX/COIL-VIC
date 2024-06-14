/*
* Autor: Erick Utrera Cornejo
* Fecha de creación: 29/05/2024
* Descripción: Controlador para registrar oferta de colaboración externa 
*/

package coilvic.controladores;

import coilvic.modelo.dao.OfertaColaboracionExternaDAO;
import coilvic.modelo.pojo.OfertaColaboracionExterna;
import coilvic.modelo.pojo.ProfesorExterno;
import coilvic.modelo.pojo.Universidad;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class FXMLRegistrarOfertaColaboracionExternaController 
        extends FXMLPaginaPrincipalCoordinadorCOILController {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidos;
    @FXML
    private TextField tfPais;
    @FXML
    private TextField tfUniversidad;
    @FXML
    private TextField tfCarrera;
    @FXML
    private TextField tfMateria;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfNombreOfertaExterna;
    @FXML
    private TextField tfPeriodo;
    @FXML
    private TextArea tfDescripcion;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());

    }    

    @FXML
    private void btnClicRegistrar(ActionEvent event) {
        if(validarCampos()){
            Universidad universidad = obtenerInformacionUniversidad();
            ProfesorExterno profesorExterno = obtenerInformacionProfesorExterno();
            OfertaColaboracionExterna ofertaExterna = obtenerInformacionOfertaColaboracionExterna();
        
            guardarOfertaColaboracionExterna(universidad, profesorExterno, ofertaExterna);
            clicBtnirPaginaPrincipal(event);
            
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
                    getResource("vistas/FXMLPaginaPrincipalCoordinadorCOIL.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.setTitle("Pagina principal CoordinadorCOIL");
            escenarioPrincipal.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    
    private void guardarOfertaColaboracionExterna(Universidad universidad, 
            ProfesorExterno profesorExterno, OfertaColaboracionExterna ofertaExterna){
                OfertaColaboracionExternaDAO.guardarUniversidad(universidad);
                OfertaColaboracionExternaDAO.guardarProfesorExterno(profesorExterno);                                  
        HashMap<String, Object> respuestaOfertaExterna = 
                OfertaColaboracionExternaDAO.guardarOfertaExterna(ofertaExterna);
        
        if(!(boolean)respuestaOfertaExterna.get(Constantes.KEY_ERROR)){
            Utilidades.mostrarAlertaSimple("Oferta externa guardada", ""+
                    respuestaOfertaExterna.get(Constantes.KEY_MENSAJE),                    
                    Alert.AlertType.INFORMATION);
            
        }else{
            Utilidades.mostrarAlertaSimple("Error al guardar", ""+
                    respuestaOfertaExterna.get(Constantes.KEY_MENSAJE), 
                    Alert.AlertType.ERROR);  
        }
    }
    
    
    private Universidad obtenerInformacionUniversidad(){
        Universidad universidad = new Universidad();
        universidad.setNombre(tfUniversidad.getText());
        universidad.setIdUniversidad(universidad.getIdUniversidad());

        return universidad;
    }
    
    
    private ProfesorExterno obtenerInformacionProfesorExterno(){
        ProfesorExterno profesorExterno = new ProfesorExterno();
       
        profesorExterno.setNombre(tfNombre.getText());
        profesorExterno.setApellidos(tfApellidos.getText());
        profesorExterno.setPais(tfPais.getText());
        profesorExterno.setCarrera(tfCarrera.getText());
        profesorExterno.setMateria(tfMateria.getText());
        profesorExterno.setCorreo(tfCorreo.getText());
        profesorExterno.setTelefono(tfTelefono.getText());
        
        return profesorExterno;
    }
    
    
    private OfertaColaboracionExterna obtenerInformacionOfertaColaboracionExterna(){
        OfertaColaboracionExterna ofertaExterna = new OfertaColaboracionExterna();
        
        ofertaExterna.setNombre(tfNombreOfertaExterna.getText());
        ofertaExterna.setPeriodo(tfPeriodo.getText());
        ofertaExterna.setDescripcion(tfDescripcion.getText());
        ofertaExterna.setIdProfesorExterno
        (obtenerInformacionProfesorExterno().getIdProfesorExterno());
        
        return ofertaExterna;
    }
    
    private boolean validarCampos(){    
        if(tfNombre.getText().trim().isEmpty() || tfApellidos.getText().trim().isEmpty() || 
                tfPais.getText().trim().isEmpty() || tfUniversidad.getText().trim().isEmpty() || 
                tfCarrera.getText().trim().isEmpty() || tfMateria.getText().trim().isEmpty() 
                || tfCorreo.getText().trim().isEmpty() || tfTelefono.getText().trim().isEmpty() 
                || tfNombreOfertaExterna.getText().trim().isEmpty() || 
                tfPeriodo.getText().trim().isEmpty() || tfDescripcion.getText().trim().isEmpty()){
            
            return false;
            
        }else{
            return true; 
        }       
    }
    
}

package coilvic.controladores;

import coilvic.modelo.pojo.OfertaColaboracionExterna;
import coilvic.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class FXMLModificarOfertaColaboracionExternaController extends FXMLPaginaPrincipalCoordinadorCOILController {
    private OfertaColaboracionExterna ofertaColaboracionExterna;

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private TextField tfNombreOfertaExterna;
    @FXML
    private TextField tfPeriodo;
    @FXML
    private TextArea tfDescripcion;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    
    public void cargarInformacionOferta(OfertaColaboracionExterna ofertaColaboracionExterna){
        tfNombreOfertaExterna.setText(ofertaColaboracionExterna.getNombre());
        tfPeriodo.setText(ofertaColaboracionExterna.getPeriodo());
        tfDescripcion.setText(ofertaColaboracionExterna.getDescripcion());
    }
    
    
    public void setOfertaColaboracionExterna(OfertaColaboracionExterna ofertaColaboracionExterna){
        this.ofertaColaboracionExterna = ofertaColaboracionExterna;
    }
 

    @FXML
    private void btnClicAceptar(ActionEvent event) {
    }

    @FXML
    private void btnClicCancelar(ActionEvent event) {
        irOfertaExterna(ofertaColaboracionExterna);

    }
    
    
    private void irOfertaExterna(OfertaColaboracionExterna ofertaExternaSeleccionada){
        try{
           Stage ofertaExterna = (Stage) imgCerrarSesion.getScene().getWindow();
           
           FXMLLoader loader = Utilidades.obtenerLoader("vistas/FXMLConsultarOfertaColaboracionExterna_Coordinador.fxml");
           Parent root = loader.load();
            
            FXMLConsultarOfertaColaboracionExterna_CoordinadorController controlador = loader.getController();
            controlador.inicializarValores(ofertaExternaSeleccionada);
            controlador.setOfertaColaboracionExterna(ofertaExternaSeleccionada);
           
           Scene escenaOfertaExterna = new Scene(root);
           ofertaExterna.setScene(escenaOfertaExterna);
           ofertaExterna.setTitle("Oferta de colaboración externa");
           ofertaExterna.show();
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    } 


    
}

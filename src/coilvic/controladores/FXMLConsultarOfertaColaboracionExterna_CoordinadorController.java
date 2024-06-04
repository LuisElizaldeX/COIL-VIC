package coilvic.controladores;

import coilvic.modelo.pojo.OfertaColaboracionExterna;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXMLConsultarOfertaColaboracionExterna_CoordinadorController extends FXMLPaginaPrincipalCoordinadorCOILController {

    @FXML
    private Text txtNombreOfertaExterna;
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private Label lbPeriodo;
    @FXML
    private Label lbDescripcion;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbApellidos;
    @FXML
    private Label lbPais;
    @FXML
    private Label lbUniversidad;
    @FXML
    private Label lbCarrera;
    @FXML
    private Label lbMateria;
    @FXML
    private Label lbIdioma;
    @FXML
    private Label lbCorreo;
    @FXML
    private Label lbTelefono;
    @FXML
    private Button btPrincipal;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());

    } 
    
    public void inicializarValores(OfertaColaboracionExterna ofertaColaboracionExterna){
        txtNombreOfertaExterna.setText(ofertaColaboracionExterna.getNombre());
        lbPeriodo.setText(ofertaColaboracionExterna.getPeriodo());
        lbDescripcion.setText(ofertaColaboracionExterna.getDescripcion());
        lbNombre.setText(ofertaColaboracionExterna.getNombreProfesorExterno());
        lbApellidos.setText(ofertaColaboracionExterna.getApellidos());
        lbPais.setText(ofertaColaboracionExterna.getPais());
        lbUniversidad.setText(ofertaColaboracionExterna.getNombreUniversidad());
        lbCarrera.setText(ofertaColaboracionExterna.getCarrera());
        lbMateria.setText(ofertaColaboracionExterna.getMateria());
        lbIdioma.setText(ofertaColaboracionExterna.getIdioma());
        lbCorreo.setText(ofertaColaboracionExterna.getCorreo());
        lbTelefono.setText(ofertaColaboracionExterna.getTelefono());
    }
    

    @FXML
    private void btnClicRegresar(ActionEvent event) {
        try{
            Stage escenarioConsultarOfertas = (Stage) imgCerrarSesion.getScene().getWindow();
            Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                    getResource("vistas/FXMLConsultarOfertas.fxml"));
            Scene consultarOfertas = new Scene(root);
            escenarioConsultarOfertas.setScene(consultarOfertas);
            escenarioConsultarOfertas.setTitle("Ofertas de colaboracion");
            escenarioConsultarOfertas.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }       
    }


    @FXML
    private void btnClicModificar(ActionEvent event) {
    }
}

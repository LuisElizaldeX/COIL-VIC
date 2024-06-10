/*
* Autor: Erick Utrera Cornejo
* Fecha de creación: 29/05/2024
* Descripción: Controlador para consulta de oferta de colaboración UV por el coordinador
*/

package coilvic.controladores;

import coilvic.modelo.pojo.CoordinadorCOIL;
import coilvic.modelo.pojo.OfertaColaboracionUV;
import coilvic.modelo.pojo.ProfesorUV;
import coilvic.utilidades.SingletonCoordinadorCOIL;
import coilvic.utilidades.SingletonProfesorUV;
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


public class FXMLConsultarOfertaColaboracionUv_CoordinadorController 
        extends FXMLPaginaPrincipalCoordinadorCOILController {
    CoordinadorCOIL coordinador = SingletonCoordinadorCOIL.getInstancia().getCoordinadorCOIL();

    @FXML
    private Text txtNombreOfertaUv;
    @FXML
    private Label lbMunicipio;
    @FXML
    private Label lbCampus;
    @FXML
    private Label lbDependencia;
    @FXML
    private Label lbAreaAcademica;
    @FXML
    private Label lbProgramaEducativo;
    @FXML
    private Label lbDescripcion;
    @FXML
    private Label lbNombreEe;
    @FXML
    private Label lbCreditos;
    @FXML
    private Label lbDescripcionEe;
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
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private Label lbFechaInicio;
    @FXML
    private Label lbFechaFin;

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString()); 
    }   
    
    public void inicializarValores(OfertaColaboracionUV ofertaColaboracionUV){
        txtNombreOfertaUv.setText(ofertaColaboracionUV.getNombre());
        lbMunicipio.setText(ofertaColaboracionUV.getMunicipio());
        lbCampus.setText(ofertaColaboracionUV.getCampus());
        lbDependencia.setText(ofertaColaboracionUV.getNombreDependencia());
        lbAreaAcademica.setText(ofertaColaboracionUV.getNombreAreaAcademica());
        lbProgramaEducativo.setText(ofertaColaboracionUV.getNombreProgramaEducativo());
        lbDescripcion.setText(ofertaColaboracionUV.getDescripcion());
        
        lbNombreEe.setText(ofertaColaboracionUV.getExperienciaEducativa());
        lbCreditos.setText(ofertaColaboracionUV.getCreditos());
        lbFechaInicio.setText(ofertaColaboracionUV.getFechaInicio());
        lbFechaFin.setText(ofertaColaboracionUV.getFechaFin());
        lbDescripcionEe.setText(ofertaColaboracionUV.getDescripcionEe());
        
        lbNombre.setText(ofertaColaboracionUV.getNombreProfesorUv());
        lbApellidos.setText(ofertaColaboracionUV.getApellidos());
        lbNumeroPersonal.setText(String.valueOf(ofertaColaboracionUV.getNumeroPersonal()));
        lbCorreo.setText(ofertaColaboracionUV.getCorreo());
        lbTelefono.setText(String.valueOf(ofertaColaboracionUV.getTelefono()));
    }

    @FXML
    private void btnClicRegresar(ActionEvent event) {
        try{
            Stage escenarioConsultarOfertasUV = (Stage) imgCerrarSesion.getScene().getWindow();
            Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                    getResource("vistas/FXMLConsultarOfertas.fxml"));
            Scene consultarOfertasUV = new Scene(root);
            escenarioConsultarOfertasUV.setScene(consultarOfertasUV);
            escenarioConsultarOfertasUV.setTitle("Ofertas colaboracion UV");
            escenarioConsultarOfertasUV.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
}

/*
* Autor: Erick Utrera Cornejo
* Fecha de creación: 29/05/2024
* Descripción: Controlador para consultar tabla de ofertas de colaboración UV por el profesor 
*/

package coilvic.controladores;

import coilvic.modelo.dao.OfertaColaboracionUVDAO;
import coilvic.modelo.pojo.OfertaColaboracionUV;
import coilvic.modelo.pojo.ProfesorUV;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.SingletonProfesorUV;
import coilvic.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class FXMLOfertasColaboracionUvController extends FXMLPaginaPrincipalProfesorUVController {
    private ObservableList<OfertaColaboracionUV> ofertasColaboracionUV;
    @FXML
    private Label lbNombreSesion;
    ProfesorUV profesorUv = SingletonProfesorUV.getInstancia().getProfesorUV();
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colDependencia;
    @FXML
    private TableColumn colProgramaEducativo;
    @FXML
    private TableColumn colEstado;
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btnPrincipal;
    @FXML
    private TableView<OfertaColaboracionUV> tvOfertasColaboracionUv;
    @FXML
    private TableColumn colFechaInicio;
    @FXML
    private TableColumn colFechaFin;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(profesorUv.toString());
        configurarTabla();
        cargarDatosOfertasColaboracionUv();
    }

    private void configurarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colDependencia.setCellValueFactory(new PropertyValueFactory("nombreDependencia"));
        colProgramaEducativo.setCellValueFactory
        (new PropertyValueFactory("nombreProgramaEducativo"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory("fechaFin"));
        colEstado.setCellValueFactory(new PropertyValueFactory("estado"));
    } 
    
    
    private void cargarDatosOfertasColaboracionUv(){
        ofertasColaboracionUV = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta =
                OfertaColaboracionUVDAO.obtenerTodaOfertaColaboracionUV();
        
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            ArrayList<OfertaColaboracionUV> ofertasColaboracionUVBD = 
                    (ArrayList<OfertaColaboracionUV>) respuesta.get("OfertasColaboracionUV");
            ofertasColaboracionUV.addAll(ofertasColaboracionUVBD);
            tvOfertasColaboracionUv.setItems(ofertasColaboracionUV);
        }else{
            Utilidades.mostrarAlertaSimple("Error", 
                    ""+respuesta.get(Constantes.KEY_MENSAJE),
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnClicVerOferta(ActionEvent event) {
        OfertaColaboracionUV ofertaUvSeleccionada= 
                tvOfertasColaboracionUv.getSelectionModel().getSelectedItem();
        if(ofertaUvSeleccionada != null){
            irOfertaUv(ofertaUvSeleccionada);
            
        }else{
            Utilidades.mostrarAlertaSimple("Selecciona una oferta de colaboracion externa", 
                    "Para consultar una oferta primero debes "
                    +"seleccionarla de la tabla", Alert.AlertType.WARNING);
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

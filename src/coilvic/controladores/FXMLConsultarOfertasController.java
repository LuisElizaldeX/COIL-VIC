/*
* Autor: Erick Utrera Cornejo
* Fecha de creación: 29/05/2024
* Descripción: Controlador para consultar tabla de ofertas de colaboración por el coordinador
*/

package coilvic.controladores;

import coilvic.modelo.dao.OfertaColaboracionExternaDAO;
import coilvic.modelo.dao.OfertaColaboracionUVDAO;
import coilvic.modelo.pojo.OfertaColaboracionExterna;
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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class FXMLConsultarOfertasController extends FXMLPaginaPrincipalCoordinadorCOILController {
    private ObservableList<OfertaColaboracionExterna> ofertasColaboracionExternas;
    private ObservableList<OfertaColaboracionUV> ofertasColaboracionUV;

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private TableView<OfertaColaboracionUV> tvOfertasUv;
    @FXML
    private TableView<OfertaColaboracionExterna> tvOfertasExternas;
    @FXML
    private TableColumn colNombreUv;
    @FXML
    private TableColumn colDependencia;
    @FXML
    private TableColumn colPeriodo;
    @FXML
    private TableColumn colEstado;
    @FXML
    private TableColumn colEstadoUv;
    @FXML
    private TableColumn colNombreExterno;
    @FXML
    private TableColumn colProgramaEducativo;
    @FXML
    private TableColumn colFechaInicio;
    @FXML
    private TableColumn colFechaFin;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());
        configurarTablas();
        cargarDatosOfertasColaboracionExternas();
        cargarDatosOfertasColaboracionUv();

    }    

    private void configurarTablas(){
        colNombreExterno.setCellValueFactory(new PropertyValueFactory("nombre"));
        colPeriodo.setCellValueFactory(new PropertyValueFactory("periodo"));
        colEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        
        colNombreUv.setCellValueFactory(new PropertyValueFactory("nombre"));
        colDependencia.setCellValueFactory(new PropertyValueFactory("nombreDependencia"));
        colProgramaEducativo.setCellValueFactory
        (new PropertyValueFactory("nombreProgramaEducativo"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory("fechaFin"));
        colEstadoUv.setCellValueFactory(new PropertyValueFactory("estado"));
    }
    
    private void cargarDatosOfertasColaboracionExternas(){
        ofertasColaboracionExternas = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta = 
                OfertaColaboracionExternaDAO.obtenerOfertaColaboracionExterna();
        
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        
        if(!isError){
            ArrayList<OfertaColaboracionExterna> ofertasColaboracionExternasBD =  
                    (ArrayList<OfertaColaboracionExterna>) 
                    respuesta.get("ofertasColaboracionExternas");
            
            ofertasColaboracionExternas.addAll(ofertasColaboracionExternasBD);
            tvOfertasExternas.setItems(ofertasColaboracionExternas);
            
            
        }else{
            Utilidades.mostrarAlertaSimple("Error", "" + 
                    respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.WARNING);
        }
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
            tvOfertasUv.setItems(ofertasColaboracionUV);
        }else{
            Utilidades.mostrarAlertaSimple("Error", 
                    ""+respuesta.get(Constantes.KEY_MENSAJE),
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnClicVerOferta(ActionEvent event) {
        OfertaColaboracionExterna ofertaExternaSeleccionada = 
                tvOfertasExternas.getSelectionModel().getSelectedItem();
        OfertaColaboracionUV ofertaUvSeleccionada = tvOfertasUv.getSelectionModel().getSelectedItem();
        
        if(ofertaExternaSeleccionada != null){
            irOfertaExterna(ofertaExternaSeleccionada);
            
        }else if(ofertaUvSeleccionada != null){
            irOfertaUv(ofertaUvSeleccionada);
            
        }else{
            Utilidades.mostrarAlertaSimple("Selecciona una oferta de colaboracion externa", 
                    "Para consultar una oferta primero debes seleccionarla de la tabla", 
                    Alert.AlertType.WARNING);
        }
        
    }
    
    private void irOfertaExterna(OfertaColaboracionExterna ofertaExternaSeleccionada){
        try{
           Stage ofertaExterna = (Stage) imgCerrarSesion.getScene().getWindow();
           
           FXMLLoader loader = Utilidades.obtenerLoader
        ("vistas/FXMLConsultarOfertaColaboracionExterna_Coordinador.fxml");
           Parent root = loader.load();
            
            FXMLConsultarOfertaColaboracionExterna_CoordinadorController controlador = 
                    loader.getController();
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
    
    
    private void irOfertaUv(OfertaColaboracionUV ofertaUvSeleccionada){
        try{
           Stage ofertaUv = (Stage) imgCerrarSesion.getScene().getWindow();
           
            FXMLLoader loader = Utilidades.obtenerLoader
        ("vistas/FXMLConsultarOfertaColaboracionUv_Coordinador.fxml");
            Parent root = loader.load();
            FXMLConsultarOfertaColaboracionUv_CoordinadorController controlador = 
                    loader.getController();
            controlador.inicializarValores(ofertaUvSeleccionada);
           
           Scene escenaOfertaUv = new Scene(root);
           ofertaUv.setScene(escenaOfertaUv);
           ofertaUv.setTitle("Oferta de colaboración UV");
           ofertaUv.show();
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }   
}

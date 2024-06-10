/*
* Autor: Erick Utrera Cornejo
* Fecha de creación: 29/05/2024
* Descripción: Controlador para consultar tabla de ofertas de colaboración externas por el profesor 
*/

package coilvic.controladores;

import coilvic.modelo.dao.OfertaColaboracionExternaDAO;
import coilvic.modelo.pojo.OfertaColaboracionExterna;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class FXMLOfertasColaboracionExternasController 
        extends FXMLPaginaPrincipalProfesorUVController {
    private ObservableList<OfertaColaboracionExterna> ofertasColaboracionExternas;
    
    @FXML
    private Label lbNombreSesion;
    ProfesorUV profesorUv = SingletonProfesorUV.getInstancia().getProfesorUV();
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btnPrincipal;
    @FXML
    private TableColumn colPeriodo;
    @FXML
    private TableColumn colEstado;
    @FXML
    private TableColumn colNombreExterno;
    @FXML
    private TableView<OfertaColaboracionExterna> tvOfertasExternas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(profesorUv.toString());
        configurarTabla();
        cargarDatosOfertasColaboracionExternas();
    }    

    
    private void configurarTabla(){
        colNombreExterno.setCellValueFactory(new PropertyValueFactory("nombre"));
        colPeriodo.setCellValueFactory(new PropertyValueFactory("periodo"));
        colEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        
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

    
    @FXML
    private void btnClicVerOferta(ActionEvent event) {
        OfertaColaboracionExterna ofertaExternaSeleccionada= 
                tvOfertasExternas.getSelectionModel().getSelectedItem();
        
        if(ofertaExternaSeleccionada != null){
            irOfertaExterna(ofertaExternaSeleccionada);
            
        }else{
            Utilidades.mostrarAlertaSimple("Selecciona una oferta de colaboracion externa", 
                    "Para consultar una oferta primero debes "
                    +"seleccionarla de la tabla", Alert.AlertType.WARNING);
        }
    }
    
    
    private void irOfertaExterna(OfertaColaboracionExterna ofertaExternaSeleccionada){
        try{
           Stage ofertaExterna = (Stage) imgCerrarSesion.getScene().getWindow();
           
           FXMLLoader loader = Utilidades.obtenerLoader
        ("vistas/FXMLConsultarOfertaColaboracionExterna_Profesor.fxml");
           Parent root = loader.load();
            
            FXMLConsultarOfertaColaboracionExterna_ProfesorController controlador = 
                    loader.getController();
            controlador.inicializarValores(ofertaExternaSeleccionada);
           
           Scene escenaOfertaExterna = new Scene(root);
           ofertaExterna.setScene(escenaOfertaExterna);
           ofertaExterna.setTitle("Oferta de colaboración externa");
           ofertaExterna.show();
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }

    }

 
}

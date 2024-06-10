
package coilvic.controladores;

import coilvic.modelo.dao.ColaboracionDAO;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.observador.ObservadorColaboraciones;
import coilvic.utilidades.Constantes;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLCancelarColaboracionCoordinadorController extends FXMLPaginaPrincipalCoordinadorCOILController implements ObservadorColaboraciones{
    
    private ObservableList<Colaboracion> colaboraciones;
    
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private TableView<Colaboracion> tvColaboraciones;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colMateria;
    @FXML
    private TableColumn colFechaInicio;
    @FXML
    private TableColumn colFechaCierre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarDatosColaboracion();
    }    
    
    private void configurarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colMateria.setCellValueFactory(new PropertyValueFactory("experienciaEducativa"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory("fechaInicio"));
        colFechaCierre.setCellValueFactory(new PropertyValueFactory("fechaFin"));
    }
    
    private void cargarDatosColaboracion(){
        colaboraciones = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta = ColaboracionDAO.obtenerColaboracionesEnCurso();
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        
        if(!isError){
            ArrayList<Colaboracion> colaboracionesBD = (ArrayList<Colaboracion>) respuesta.get("colaboraciones");
            colaboraciones.addAll(colaboracionesBD);
            tvColaboraciones.setItems(colaboraciones);
        } else {
            Utilidades.mostrarAlertaSimple("Error", ""+respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.WARNING);
        }
    }
    
    @FXML
    private void btnClicCancelarColaboracion(ActionEvent event) {
        Colaboracion colaboracionSeleccionada = tvColaboraciones.getSelectionModel().getSelectedItem();
        
        if(colaboracionSeleccionada != null){
            if (colaboracionSeleccionada.getEstadoColaboracion().equals("En curso")) {
                irJustificarCancelacion(colaboracionSeleccionada.getIdColaboracion());
            } else {
                Utilidades.mostrarAlertaSimple("Error al cancelar", "Esta colaboración ya se encuentra cancelada o registrada", Alert.AlertType.WARNING);
            }
        } else {
            Utilidades.mostrarAlertaSimple("Seleccionar colaboración", 
                    "Para cancelar una colaboración primero debe seleccionar una de la tabla", Alert.AlertType.WARNING);
        }
    }
    
    private void irJustificarCancelacion(int idColaboracion){
        try{
            Stage escenarioSecundario = new Stage();
            FXMLLoader loader = Utilidades.obtenerLoader("vistas/FXMLJustificarCancelacion.fxml");
            Parent root = loader.load();
            FXMLJustificarCancelacionController controlador = loader.getController();
            controlador.inicializarValores(idColaboracion,this);
            
            Scene escenaPrincipal = new Scene(root);
            escenarioSecundario.setScene(escenaPrincipal);
            escenarioSecundario.setTitle("Justificar cancelación");
            escenarioSecundario.initModality(Modality.APPLICATION_MODAL);
            escenarioSecundario.showAndWait();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @Override
    public void operacionExitosa(String tipoOperacion) {
        System.out.println("Operacion: " + tipoOperacion);
        cargarDatosColaboracion();
    }
    
}

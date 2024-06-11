/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 21/05/2024
* Descripción: Controlador para la página Historial de colaboraciones
*/

package coilvic.controladores;

import coilvic.modelo.dao.ColaboracionDAO;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FXMLHistorialDeColaboracionesController 
        extends FXMLPaginaPrincipalCoordinadorCOILController {

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
    private TableColumn colExperienciaEducativa;
    
    @FXML
    private TextField tfNombreColaboracion;
    
    ObservableList<Colaboracion> colaboraciones;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());
        configurarTabla();
        cargarDatosColaboracion();
        configurarBusquedaColaboracion();
        int maxLength = 45;
        UnaryOperator<TextFormatter.Change> textLimiter = change -> {
            if (change.getControlNewText().length() <= maxLength) {
                return change;
            }
            return null;
        };
        tfNombreColaboracion.setTextFormatter(new TextFormatter<>(textLimiter));
    }
    
    private void configurarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colExperienciaEducativa.setCellValueFactory
            (new PropertyValueFactory("experienciaEducativa"));
    }
    
    private void cargarDatosColaboracion(){
        colaboraciones = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta =
                ColaboracionDAO.obtenerColaboraciones();
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            ArrayList<Colaboracion> ofertasColaboracionUVBD = 
                    (ArrayList<Colaboracion>) respuesta.get("colaboraciones");
            colaboraciones.addAll(ofertasColaboracionUVBD);
            tvColaboraciones.setItems(colaboraciones);
        }else{
            Utilidades.mostrarAlertaSimple("Error", 
                    ""+respuesta.get(Constantes.KEY_MENSAJE),
                    Alert.AlertType.ERROR);
        } 
    }
    
    private void configurarBusquedaColaboracion(){
        if(colaboraciones.size()>0){
            FilteredList<Colaboracion> filtroPacientes = 
                    new FilteredList<>(colaboraciones, p -> true);
            tfNombreColaboracion.textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, 
                        String newValue) {
                     filtroPacientes.setPredicate(p -> {
                         if(newValue == null || newValue.isEmpty()){
                             return true;
                         }
                         String palabrafiltro = newValue.toLowerCase();
                         if(p.getNombre().toLowerCase().contains(palabrafiltro)){
                             return true;
                         }
                         return false;
                     });
                }
            });
            SortedList<Colaboracion> listaOrdenada = new SortedList<>(filtroPacientes);
            listaOrdenada.comparatorProperty().bind(tvColaboraciones.comparatorProperty());
            tvColaboraciones.setItems(listaOrdenada);
        }
    }

    @FXML
    private void clicBtnVerHistorial(ActionEvent event) {
        Colaboracion colaboracionSeleccionada = 
                tvColaboraciones.getSelectionModel().getSelectedItem();
        if(colaboracionSeleccionada != null){
            try{  
                FXMLLoader accesoControlador = new FXMLLoader(coilvic.COILVIC.class.getResource(
                "vistas/FXMLEtapasColaboracion.fxml"));
                Parent vista = accesoControlador.load();
                FXMLEtapasColaboracionController colaboracion = accesoControlador.getController();
                colaboracion.configurarDatosColaboracionCOIL(colaboracionSeleccionada);
                Stage escenarioPrincipal = (Stage) imgCerrarSesion.getScene().getWindow();
                Scene escenaPrincipal = new Scene(vista); 
                escenarioPrincipal.setScene(escenaPrincipal);
                escenarioPrincipal.setTitle("Etapas de colaboracion");
                escenarioPrincipal.show();
            }catch(IOException e){
                System.out.println("Error: "+e.getMessage());
            }
        }else{
              Utilidades.mostrarAlertaSimple("Selecciona una colaboracion", ""
                + "Para revisar las etapas de una colaboracion debes seleccionar una de la lista.", 
                    Alert.AlertType.WARNING);
        }
    }

}

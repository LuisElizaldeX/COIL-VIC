/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 18/05/2024
* Descripción: Controlador para la página Consultar numeralia
*/

package coilvic.controladores;

import coilvic.modelo.dao.NumeraliaDAO;
import coilvic.modelo.pojo.Numeralia;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.Utilidades;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;

public class FXMLConsultarNumeraliaController extends FXMLPaginaPrincipalCoordinadorCOILController {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private TableView<Numeralia> tvNumeralia;
    @FXML
    private TableColumn colRegion;
    @FXML
    private TableColumn colAlumnos;
    @FXML
    private TableColumn colProfesores;
    @FXML
    private ComboBox<String> cbFiltroBusqueda;
    
    private ObservableList<Numeralia> numeralia;
    private ObservableList<String> opciones;
    
    @FXML
    private TableView<Numeralia> tvNumeraliaAA;
    @FXML
    private TableColumn colAreaAcademica;
    @FXML
    private TableColumn colAlumnosAA;
    @FXML
    private TableColumn colProfesoresAA;
    
    private String encabezado;

    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(coordinador.toString());
        InicializarDatosOpciones();
        cbFiltroBusqueda.setValue("Región");
        configurarTablaNumeraliaRegion();
        configurarTablaNumeraliaAreaAcademica();
        cargarDatosNumeraliaPorRegion();
        cbFiltroBusqueda.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                    String newValue) {
                if(newValue == "Área académica"){
                    tvNumeraliaAA.setVisible(true);
                    tvNumeralia.setVisible(false);
                    cargarDatosNumeraliaPorAreaAcademica();
                }else{
                    tvNumeraliaAA.setVisible(false);
                    tvNumeralia.setVisible(true);
                    cargarDatosNumeraliaPorRegion();
                }
            } 
        });
    }
    
    private void InicializarDatosOpciones(){
        opciones = FXCollections.observableArrayList();
        opciones.add("Región");
        opciones.add("Área académica");
        cbFiltroBusqueda.setItems(opciones);
    }
    
     private void configurarTablaNumeraliaRegion(){
        colRegion.setCellValueFactory(new PropertyValueFactory("region"));
        colProfesores.setCellValueFactory
            (new PropertyValueFactory("profesores"));
        colAlumnos.setCellValueFactory(new PropertyValueFactory("alumnos"));
    }

    private void cargarDatosNumeraliaPorRegion(){
        numeralia = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta =
                NumeraliaDAO.obtenerNumeraliaPorRegion();
        
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            ArrayList<Numeralia> numeraliaBD = 
                    (ArrayList<Numeralia>) respuesta.get("Numeralia");
            numeralia.addAll(numeraliaBD);
            tvNumeralia.setItems(numeralia);
        }else{
            Utilidades.mostrarAlertaSimple("Error", 
                    ""+respuesta.get(Constantes.KEY_MENSAJE),
                    Alert.AlertType.ERROR);
        }
    }
    
    private void configurarTablaNumeraliaAreaAcademica(){
        colAreaAcademica.setCellValueFactory(new PropertyValueFactory("areaAcademica"));
        colProfesoresAA.setCellValueFactory
            (new PropertyValueFactory("profesores"));
        colAlumnosAA.setCellValueFactory(new PropertyValueFactory("alumnos"));
    }

    private void cargarDatosNumeraliaPorAreaAcademica(){
        numeralia = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta =
                NumeraliaDAO.obtenerNumeraliaPorAreaAcademica();
        
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            ArrayList<Numeralia> numeraliaBD = 
                    (ArrayList<Numeralia>) respuesta.get("Numeralia");
            numeralia.addAll(numeraliaBD);
            tvNumeraliaAA.setItems(numeralia);
        }else{
            Utilidades.mostrarAlertaSimple("Error", 
                    ""+respuesta.get(Constantes.KEY_MENSAJE),
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clicBtnGenerarReporte(ActionEvent event) {
        if(tvNumeralia.isVisible()){
            encabezado = "Region, Alumnos, Profesores";
        }else{
            encabezado = "Area academica, Alumnos, Profesores";
        }
        DirectoryChooser directorio = new DirectoryChooser();
        directorio.setTitle("Selecciona una carpeta");
        File directorioDestino = 
                directorio.showDialog(imgCerrarSesion.getScene().getWindow());
        if(directorioDestino != null){
            try{
                String ruta = directorioDestino.getAbsolutePath()+"/Numeralia.csv";
                File archivoExportado = new File(ruta);
                Writer archivoEscritura = new BufferedWriter(new FileWriter(archivoExportado));
                archivoEscritura.write(encabezado);
                if(tvNumeraliaAA.isVisible()){
                    for (Numeralia numeralia : numeralia){
                    String fila = String.format("\n%s,%s,%s", 
                            numeralia.getAreaAcademica(), numeralia.getAlumnos(), 
                            numeralia.getProfesores());
                    archivoEscritura.write(fila);
                    }
                }else{
                    for (Numeralia numeralia : numeralia){
                    String fila = String.format("\n%s,%s,%s", 
                            numeralia.getRegion(), numeralia.getAlumnos(), 
                            numeralia.getProfesores());
                    archivoEscritura.write(fila);
                    }
                }
                archivoEscritura.close();
                Utilidades.mostrarAlertaSimple("Archivo exportado", 
                        "El archivo fue exportado correctamente", 
                        Alert.AlertType.INFORMATION);
            }catch(IOException e){
                  Utilidades.mostrarAlertaSimple("Error al exportar", 
                        e.getMessage(), 
                        Alert.AlertType.ERROR);
            }
        }
    }
    
}

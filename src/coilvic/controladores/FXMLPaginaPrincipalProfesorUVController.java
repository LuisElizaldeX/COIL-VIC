<<<<<<< HEAD
/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 17/05/2024
* Descripción: Controlador de página principal de profesor UV
*/

package coilvic.controladores;

import coilvic.modelo.dao.ColaboracionDAO;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.modelo.pojo.ProfesorUV;
import coilvic.utilidades.Constantes;
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
import javafx.stage.Stage;
import coilvic.utilidades.SingletonProfesorUV;
import coilvic.utilidades.Utilidades;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;


public class FXMLPaginaPrincipalProfesorUVController implements Initializable {
    ProfesorUV profesor = SingletonProfesorUV.getInstancia().getProfesorUV();
    private ObservableList<Colaboracion> colaboraciones;
    
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btnPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private Label lbNombreBienvenida;
    @FXML
    private TableView<Colaboracion> tvColaboraciones;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colMateria;
    @FXML
    private TableColumn colPeriodo;
    @FXML
    private TableColumn colEstado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(profesor.toString());
        lbNombreBienvenida.setText(profesor.toString());
        configurarTabla();
        cargarDatosColaboracion();
    }    
    
    private void configurarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
       
        //colMateria.setCellValueFactory(new PropertyValueFactory("materia"));
    
        colMateria.setCellValueFactory(new PropertyValueFactory("experienciaEducativa"));
        colPeriodo.setCellValueFactory(new PropertyValueFactory("fechaInicio"));
        colEstado.setCellValueFactory(new PropertyValueFactory("estadoColaboracion"));
    }
    
    private void cargarDatosColaboracion(){
        colaboraciones = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta = ColaboracionDAO.obtenerColaboracionesProfesorUV
        (profesor.getIdProfesorUV());
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            ArrayList<Colaboracion> colaboracionesBD = 
                    (ArrayList<Colaboracion>) respuesta.get("colaboraciones");
            colaboraciones.addAll(colaboracionesBD);
            tvColaboraciones.setItems(colaboraciones);
        } else {
            Utilidades.mostrarAlertaSimple("Error", "" + 
                    respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.WARNING);
        }
    }
    
    

    @FXML
    protected void clicImgCerrarSesion(MouseEvent event) {
        try{
            Stage escenarioPrincipal = (Stage) imgCerrarSesion.getScene().getWindow();
            Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                    getResource("vistas/FXMLIniciarSesion.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.setTitle("Inicio de sesión");
            escenarioPrincipal.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @FXML
    protected void clicBtnIrPaginaPrincipal(ActionEvent event) {
        try{
           Stage escenarioPrincipal = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLPaginaPrincipalProfesorUV.fxml"));
           Scene escenaPrincipal = new Scene(root);
           escenarioPrincipal.setScene(escenaPrincipal);
           escenarioPrincipal.setTitle("Pagina principal ProfesorUv");
           escenarioPrincipal.show();
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    @FXML
    protected void clicBtnIrOfertasColaboracionUv(ActionEvent event) {
        try{
           Stage ofertasUv = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLOfertasColaboracionUv.fxml"));
           Scene escenaOfertasUv = new Scene(root);
           ofertasUv.setScene(escenaOfertasUv);
           ofertasUv.setTitle("Ofertas colaboracion UV");
           ofertasUv.show();
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    
    @FXML
    protected void clicBtnIrOfertasColaboracionExternas(ActionEvent event) {
        try{
           Stage ofertasExternas = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLOfertasColaboracionExternas.fxml"));
           Scene escenaOfertasExternas = new Scene(root);
           ofertasExternas.setScene(escenaOfertasExternas);
           ofertasExternas.setTitle("Ofertas colaboracion externas");
           ofertasExternas.show();
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @FXML
    protected void clicBtnIrRegistrarOfertaColaboracion(ActionEvent event) {
       try{
           Stage registrarOfertaUv = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLRegistrarOfertaColaboracionUv.fxml"));
           Scene escenaRegistrarOfertaUv = new Scene(root);
           registrarOfertaUv.setScene(escenaRegistrarOfertaUv);
           registrarOfertaUv.setTitle("Registrar oferta de colaboracion UV");
           registrarOfertaUv.show();
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @FXML
    protected void clicBtnIrRegistrarColaboracion(ActionEvent event) {
        try{
           Stage registrarOfertaUv = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLRegistrarColaboracionDirecta.fxml"));
           Scene escenaRegistrarOfertaUv = new Scene(root);
           registrarOfertaUv.setScene(escenaRegistrarOfertaUv);
           registrarOfertaUv.setTitle("Registrar colaboracion Directa");
           registrarOfertaUv.show();
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @FXML
    private void seleccionarColaboracion(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
            Colaboracion colaboracionSeleccionada = 
                    tvColaboraciones.getSelectionModel().getSelectedItem();
            if(colaboracionSeleccionada != null){
                irColaboracion(colaboracionSeleccionada);
            }
        }
    }
    
    private void irColaboracion(Colaboracion colaboracion){
        try {
            Stage escenario = (Stage) imgCerrarSesion.getScene().getWindow();
            FXMLLoader loader = Utilidades.obtenerLoader("vistas/FXMLColaboracion.fxml");
            Parent root = loader.load();
            FXMLColaboracionController controlador = loader.getController();
            controlador.inicializarValores(colaboracion);
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Colaboracion");
            escenario.show();
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

    
    
    
=======
/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 17/05/2024
* Descripción: Controlador de página principal de profesor UV
*/

package coilvic.controladores;

import coilvic.modelo.dao.ColaboracionDAO;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.modelo.pojo.ProfesorUV;
import coilvic.utilidades.Constantes;
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
import javafx.stage.Stage;
import coilvic.utilidades.SingletonProfesorUV;
import coilvic.utilidades.Utilidades;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;


public class FXMLPaginaPrincipalProfesorUVController implements Initializable {
    ProfesorUV profesor = SingletonProfesorUV.getInstancia().getProfesorUV();
    private ObservableList<Colaboracion> colaboraciones;
    
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btnPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private Label lbNombreBienvenida;
    @FXML
    private TableView<Colaboracion> tvColaboraciones;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colEstado;
    @FXML
    private TableColumn colFechaInicio;
    @FXML
    private TableColumn colFechaFin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(profesor.toString());
        lbNombreBienvenida.setText(profesor.toString());
        cargarDatosColaboracion();
        configurarTabla();
    }    
    
    private void configurarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory("fechaFin"));
        colEstado.setCellValueFactory(new PropertyValueFactory("estadoColaboracion"));
    }
    
    private void cargarDatosColaboracion(){
        colaboraciones = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta = ColaboracionDAO.
                obtenerColaboracionesProfesorUV(profesor.getIdProfesorUV());
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            ArrayList<Colaboracion> colaboracionesBD = 
                    (ArrayList<Colaboracion>) respuesta.get("colaboraciones");
            colaboraciones.addAll(colaboracionesBD);
            tvColaboraciones.setItems(colaboraciones);
        } else {
            Utilidades.mostrarAlertaSimple("Error", "" + 
                    respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.WARNING);
        }
    }

    @FXML
    protected void clicImgCerrarSesion(MouseEvent event) {
        try{
            Stage escenarioPrincipal = (Stage) imgCerrarSesion.getScene().getWindow();
            Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                    getResource("vistas/FXMLIniciarSesion.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.setTitle("Inicio de sesión");
            escenarioPrincipal.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @FXML
    protected void clicBtnIrPaginaPrincipal(ActionEvent event) {
        try{
           Stage escenarioPrincipal = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLPaginaPrincipalProfesorUV.fxml"));
           Scene escenaPrincipal = new Scene(root);
           escenarioPrincipal.setScene(escenaPrincipal);
           escenarioPrincipal.setTitle("Pagina principal ProfesorUv");
           escenarioPrincipal.show();
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    @FXML
    protected void clicBtnIrOfertasColaboracionUv(ActionEvent event) {
        try{
           Stage ofertasUv = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLOfertasColaboracionUv.fxml"));
           Scene escenaOfertasUv = new Scene(root);
           ofertasUv.setScene(escenaOfertasUv);
           ofertasUv.setTitle("Ofertas colaboracion UV");
           ofertasUv.show();
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    
    @FXML
    protected void clicBtnIrOfertasColaboracionExternas(ActionEvent event) {
        try{
           Stage ofertasExternas = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLOfertasColaboracionExternas.fxml"));
           Scene escenaOfertasExternas = new Scene(root);
           ofertasExternas.setScene(escenaOfertasExternas);
           ofertasExternas.setTitle("Ofertas colaboracion externas");
           ofertasExternas.show();
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @FXML
    protected void clicBtnIrRegistrarOfertaColaboracion(ActionEvent event) {
       try{
           Stage registrarOfertaUv = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLRegistrarOfertaColaboracionUv.fxml"));
           Scene escenaRegistrarOfertaUv = new Scene(root);
           registrarOfertaUv.setScene(escenaRegistrarOfertaUv);
           registrarOfertaUv.setTitle("Registrar oferta de colaboracion UV");
           registrarOfertaUv.show();
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @FXML
    protected void clicBtnIrRegistrarColaboracion(ActionEvent event) {
        try{
           Stage registrarOfertaUv = (Stage) imgCerrarSesion.getScene().getWindow();
           Parent root = FXMLLoader.load(coilvic.COILVIC.class.
                   getResource("vistas/FXMLRegistrarColaboracionDirecta.fxml"));
           Scene escenaRegistrarOfertaUv = new Scene(root);
           registrarOfertaUv.setScene(escenaRegistrarOfertaUv);
           registrarOfertaUv.setTitle("Registrar colaboracion Directa");
           registrarOfertaUv.show();
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @FXML
    private void seleccionarColaboracion(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
            Colaboracion colaboracionSeleccionada = 
                    tvColaboraciones.getSelectionModel().getSelectedItem();
            if(colaboracionSeleccionada != null){
                irColaboracion(colaboracionSeleccionada);
            }
        }
    }
    
    private void irColaboracion(Colaboracion colaboracion){
        try {
            Stage escenario = (Stage) imgCerrarSesion.getScene().getWindow();
            FXMLLoader loader = Utilidades.obtenerLoader("vistas/FXMLColaboracion.fxml");
            Parent root = loader.load();
            FXMLColaboracionController controlador = loader.getController();
            controlador.inicializarValores(colaboracion);
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Colaboracion");
            escenario.show();
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

    
    
    
>>>>>>> bb2eb4d4b57b0338997fb71529e2795b7c7e04b7
}
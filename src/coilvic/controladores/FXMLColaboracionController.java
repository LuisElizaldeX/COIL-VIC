package coilvic.controladores;

import coilvic.modelo.dao.ColaboracionDAO;
import coilvic.modelo.dao.ProfesorExternoDAO;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.modelo.pojo.ProfesorExterno;
import coilvic.modelo.pojo.ProfesorUV;
import coilvic.observador.ObservadorColaboraciones;
import coilvic.utilidades.Constantes;
import coilvic.utilidades.SingletonProfesorUV;
import coilvic.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLColaboracionController extends FXMLPaginaPrincipalProfesorUVController 
        implements ObservadorColaboraciones{
    private Colaboracion colaboracion;
    
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btnPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private Label lbNombreColaboracion;
    @FXML
    private Label lbFechas;
    @FXML
    private Label lbDescripcion;
    @FXML
    private Label lbPresentacionProfesorUV;
    @FXML
    private Label lbNombreProfesorUV;
    @FXML
    private Label lbCarrera;
    @FXML
    private Label lbExperienciaEducativa;
    @FXML
    private Label lbTelefono;
    @FXML
    private Label lbIdioma;
    @FXML
    private Label lbCorreoProfesorUV;
    @FXML
    private Label lbPresentacionProfEx;
    @FXML
    private Label lbNombreProfEx;
    @FXML
    private Label lbCorreoProfEx;
    @FXML
    private Label lbMateriaProfEx;
    @FXML
    private Label lbCarreraProfEx;
    @FXML
    private Label lbPaisProfEx;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void inicializarValores(Colaboracion colaboracion){
        this.colaboracion = colaboracion;
        cargarDatosEtiquetas();
    }

    @FXML
    private void btnClicRegresar(ActionEvent event) {
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
    private void btnClicCancelarColaboracion(ActionEvent event) {
        if (colaboracion.getIdEstadoColaboracion() == 1) {
            irJustificarCancelacion(colaboracion.getIdColaboracion());
        } else {
            Utilidades.mostrarAlertaSimple("Error al cancelar", "Esta colaboración ya se "
                    + "encuentra cancelada o finalizada", Alert.AlertType.WARNING);
        } 
    }
    
    private void cargarDatosEtiquetas(){
        ProfesorExterno profesorExterno = 
                obtenerProfesorExterno(colaboracion.getIdColaboracion());
        lbNombreColaboracion.setText(colaboracion.getNombre());
        lbFechas.setText("Fecha de inicio: "+colaboracion.getFechaInicio()
                +"\nFecha de fin: "+colaboracion.getFechaFin());
        lbIdioma.setText("Idioma de la colaboración: "+colaboracion.getIdioma());
        lbDescripcion.setText(colaboracion.getDescripcion());
        lbPresentacionProfesorUV.setText("Profesor de la Universidad Veracruzana");
        lbNombreProfesorUV.setText("Nombre: "+colaboracion.getProfesorUV());       
        lbExperienciaEducativa.setText("Asignatura: "+colaboracion.getExperienciaEducativa());
        lbTelefono.setText("Teléfono: "+profesor.getTelefono());
        lbCorreoProfesorUV.setText("Correo-e: "+profesor.getCorreo());
        lbNombreProfEx.setText("Nombre: "+profesorExterno.getNombre()
                +" "+profesorExterno.getApellidos());
        lbPresentacionProfEx.setText("Profesor de la "+profesorExterno.getNombreUniversidad());
        lbPaisProfEx.setText("País: "+profesorExterno.getPais());
        lbCorreoProfEx.setText("Correo-e: "+profesorExterno.getCorreo());
        lbMateriaProfEx.setText("Materia: "+profesorExterno.getMateria());
        lbCarreraProfEx.setText("Carrera: "+profesorExterno.getCarrera());
    }

    private void irJustificarCancelacion(int idColaboracion){
        try{
            Stage escenarioSecundario = new Stage();
            FXMLLoader loader = 
                    Utilidades.obtenerLoader("vistas/FXMLJustificarCancelacion.fxml");
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

    @FXML
    private void btnClicCerrarColaboracion(ActionEvent event) {
        if (colaboracion.getIdEstadoColaboracion() == 1) {
            irCerrarColaboracion(colaboracion);
        } else {
            Utilidades.mostrarAlertaSimple("Error al cerrar colaboracion", "Esta colaboración"
                    + " ya se encuentra cancelada o finalizada", Alert.AlertType.WARNING);
        }
    }

    private void irCerrarColaboracion(Colaboracion colaboracion){
        try{
            Stage escenarioPrincipal = (Stage) lbCarrera.getScene().getWindow();
            FXMLLoader loader = Utilidades.obtenerLoader("vistas/FXMLCerrarColaboracion.fxml");
            Parent root = loader.load();
            FXMLCerrarColaboracionController controlador = loader.getController();
            controlador.inicializarValores(colaboracion, this);
            
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.setTitle("Cerrar colaboración");
            escenarioPrincipal.show();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    private Colaboracion obtenerColaboracionActualizada(){
        HashMap<String, Object> respuesta = 
                ColaboracionDAO.obtenerColaboracion(colaboracion.getIdColaboracion());
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if (!isError){
            return (Colaboracion) respuesta.get("colaboracion");
        } else {
            System.out.println("No se encontró una colaboración actualizada");
        }
        return null;
    }
    
    private ProfesorExterno obtenerProfesorExterno(int idColaboracion){
        HashMap<String, Object> respuesta = 
                ProfesorExternoDAO.obtenerProfesorExternoPorColaboracion(idColaboracion);
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if (!isError) {
            return (ProfesorExterno) respuesta.get("profesorExterno");
        } else {
            System.out.println("No se encontró al profesor externo");
        }
        return null;
    }

    @Override
    public void operacionExitosa(String tipoOperacion) {
        Colaboracion colaboracionActualizada = obtenerColaboracionActualizada();
        if (colaboracionActualizada != null) {
            inicializarValores(colaboracionActualizada);
        }
    }

}

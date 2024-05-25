/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coilvic.controladores;

import coilvic.modelo.dao.ColaboracionDAO;
import coilvic.modelo.pojo.AreaAcademica;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.modelo.pojo.ProgramaEducativo;
import coilvic.utilidades.Constantes;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zS21022065
 */
public class FXMLRegistrarColaboracionDirectaController extends FXMLPaginaPrincipalProfesorUVController {
    
    private int idProfesorUV;
    private ObservableList<ProgramaEducativo> programasEducativos;
    private ObservableList<AreaAcademica> areasAcademicas;
    
    
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Button btnPrincipal;
    @FXML
    private Label lbNombreSesion;
    @FXML
    private Label lbNombreProfesorUv;
    @FXML
    private Label lbApellidosProfesorUV;
    @FXML
    private Label lbNumeroDePersonal;
    @FXML
    private Label lbTelefono;
    @FXML
    private Label lbApellidosProfesorUv;
    @FXML
    private Label lbCorreo;
    @FXML
    private TextField tfNombreColaboracion;
    @FXML
    private TextField tfFechaDeInicio;
    @FXML
    private TextField tfFechaDeCierre;
    @FXML
    private TextField tfNombreDependencia;
    @FXML
    private ComboBox<?> cbCampus;
    @FXML
    private TextField tfMunicipio;
    @FXML
    private ComboBox<?> cbAreaAcademica;
    @FXML
    private ComboBox<?> cbProgramaEducativo;
    @FXML
    private TextField tfAnioDeInicioDeOperacion;
    @FXML
    private TextField tfExperienciaEducativa;
    @FXML
    private TextField TfMateriaE;
    @FXML
    private TextField tfNombreProfesorExterno;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfCarrera;
    @FXML
    private TextField tfApellidosProfesorExterno;
    @FXML
    private TextField tfUniversidad;
    @FXML
    private TextField tfPais;
    @FXML
    private TextField tfCorrep;
    @FXML
    private TextField tfIdioma;
    @FXML
    private TextField tfNumeroDeEstudiantes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreSesion.setText(profesor.toString());
    }    

    @FXML
    protected void clicImgCerrarSesion(MouseEvent event) {
    }

    @FXML
    protected void clicBtnIrOfertasColaboracionCOIL(ActionEvent event) {
    }

    @FXML
    protected void clicBtnirPaginaPrincipal(ActionEvent event) {
        try {
            Stage escenarioPrincipal = (Stage) btnPrincipal.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/coilvic/vistas/FXMLPaginaPrincipalProfesorUV.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.setTitle("Página Principal Profesor UV");
            escenarioPrincipal.show();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }

    @FXML
    protected void clicBtnIrRegistrarOfertaColaboracion(ActionEvent event) {
        
    }
    


    @FXML
    private void btnCancelarRegistroColaboracion(ActionEvent event) {
        try {
        Stage stage = (Stage) btnPrincipal.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/coilvic/vistas/FXMLPaginaPrincipalProfesorUV.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Página Principal Profesor UV");
        stage.show();
    } catch (IOException e) {
        System.out.println("Error al cargar la vista: " + e.getMessage());
    }
        
    }
    
    
    
    

    @FXML
    private void btnAceptarRegistroColaboracion(ActionEvent event) {
    
    
    }
    
     
    
}

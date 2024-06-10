/*
* Autor: Erick Utrera Cornejo
* Fecha de creación: 29/05/2024
* Descripción: Clase del modelo de AreaAcademica_Campus
*/

package coilvic.modelo.pojo;

public class AreaAcademica_Campus {
    private int idAreaAcademica;
    private int idCampus;
    private String nombreAreaAcademica;
    private String nombreCampus;

    public AreaAcademica_Campus() {
    }

    public AreaAcademica_Campus
        (String nombreAreaAcademica, String nombreCampus, int idAreaAcademica, int idCampus) {
        this.nombreAreaAcademica = nombreAreaAcademica;
        this.nombreCampus = nombreCampus;
        this.idAreaAcademica = idAreaAcademica;
        this.idCampus = idCampus;
        
    }

    public String getNombreAreaAcademica() {
        return nombreAreaAcademica;
    }

    public void setNombreAreaAcademica(String nombreAreaAcademica) {
        this.nombreAreaAcademica = nombreAreaAcademica;
    }

    public String getNombreCampus() {
        return nombreCampus;
    }

    public void setNombreCampus(String nombreCampus) {
        this.nombreCampus = nombreCampus;
    }

    public int getIdAreaAcademica() {
        return idAreaAcademica;
    }

    public void setIdAreaAcademica(int idAreaAcademica) {
        this.idAreaAcademica = idAreaAcademica;
    }

    public int getIdCampus() {
        return idCampus;
    }

    public void setIdCampus(int idCampus) {
        this.idCampus = idCampus;
    }
    
    
    
    
    
}

/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase del modelo de area academica 
 */

package coilvic.modelo.pojo;

public class AreaAcademica {
    private int idAreaAcademica;
    private String nombre;

    public AreaAcademica() {
    }

    public AreaAcademica(int idAreaAcademica, String nombre) {
        this.idAreaAcademica = idAreaAcademica;
        this.nombre = nombre;
    }

    public int getIdAreaAcademica() {
        return idAreaAcademica;
    }

    public void setIdAreaAcademica(int idAreaAcademica) {
        this.idAreaAcademica = idAreaAcademica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}

/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase del modelo programa educativo
 */

package coilvic.modelo.pojo;

public class ProgramaEducativo {
    private int idProgramaEducativo;
    private int idAreaAcademica;
    private String nombre;
    private String fechaInicioOperacion;

    public ProgramaEducativo() {
    }

    public ProgramaEducativo(int idProgramaEducativo, int idAreaAcademica, String nombre, String fechaInicioOperacion) {
        this.idProgramaEducativo = idProgramaEducativo;
        this.idAreaAcademica = idAreaAcademica;
        this.nombre = nombre;
        this.fechaInicioOperacion = fechaInicioOperacion;
    }

    public int getIdProgramaEducativo() {
        return idProgramaEducativo;
    }

    public void setIdProgramaEducativo(int idProgramaEducativo) {
        this.idProgramaEducativo = idProgramaEducativo;
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

    public String getFechaInicioOperacion() {
        return fechaInicioOperacion;
    }

    public void setFechaInicioOperacion(String fechaInicioOperacion) {
        this.fechaInicioOperacion = fechaInicioOperacion;
    }

    @Override
    public String toString() {
        return nombre;
    }  
}

/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase del modelo de oferta de colaboracion UV
 */

package coilvic.modelo.pojo;

public class OfertaColaboracionUV {
    private int idOfertaColaboracionUV;
    private int idExperienciaEducativa;
    private int idProfesorUV;
    private int idEstadoOfertaColaboracionUV;
    private String descripcion;
    private String nombre;
    private String experienciaEducativa;
    private String profesorUV;
    private String estado;
    private String periodo;

    public OfertaColaboracionUV() {
    }

    public OfertaColaboracionUV(int idOfertaColaboracionUV, int idExperienciaEducativa, 
            int idProfesorUV, int idEstadoOfertaColaboracionUV, String descripcion, String nombre, 
            String experienciaEducativa, String profesorUV, String estado, String periodo) {
        this.idOfertaColaboracionUV = idOfertaColaboracionUV;
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.idProfesorUV = idProfesorUV;
        this.idEstadoOfertaColaboracionUV = idEstadoOfertaColaboracionUV;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.experienciaEducativa = experienciaEducativa;
        this.profesorUV = profesorUV;
        this.estado = estado;
        this.periodo = periodo;
    }

    public int getIdOfertaColaboracionUV() {
        return idOfertaColaboracionUV;
    }

    public void setIdOfertaColaboracionUV(int idOfertaColaboracionUV) {
        this.idOfertaColaboracionUV = idOfertaColaboracionUV;
    }

    public int getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public void setIdExperienciaEducativa(int idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public int getIdProfesorUV() {
        return idProfesorUV;
    }

    public void setIdProfesorUV(int idProfesorUV) {
        this.idProfesorUV = idProfesorUV;
    }

    public int getIdEstadoOfertaColaboracionUV() {
        return idEstadoOfertaColaboracionUV;
    }

    public void setIdEstadoOfertaColaboracionUV(int idEstadoOfertaColaboracionUV) {
        this.idEstadoOfertaColaboracionUV = idEstadoOfertaColaboracionUV;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public void setExperienciaEducativa(String experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
    }

    public String getProfesorUV() {
        return profesorUV;
    }

    public void setProfesorUV(String profesorUV) {
        this.profesorUV = profesorUV;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
}

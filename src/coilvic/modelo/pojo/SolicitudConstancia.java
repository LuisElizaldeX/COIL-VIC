/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase del modelo de solicitud de constancia
 */

package coilvic.modelo.pojo;

public class SolicitudConstancia {
    private int idSolicitudConstancia;
    private int idColaboracion;
    private int idEstadoConstancia;
    private String estado;
    private String fechaSolcitud;
    private String colaboracion;
    private String profesorUV;
    private String profesorExterno;
    private String periodo;
    private String experienciaEducativa;
    private String programaEducativo;
    private String descripcion;
    private String estudiante;
    private String matricula;
    
    public SolicitudConstancia() {
    }

    public SolicitudConstancia(int idSolicitudConstancia, int idColaboracion, 
            int idEstadoConstancia, String estado, String fechaSolcitud, String colaboracion, 
            String profesorUV, String periodo, String profesorExterno, 
            String experienciaEducativa, String programaEducativo, String descripcion,
            String estudiante, String matricula) {
        this.idSolicitudConstancia = idSolicitudConstancia;
        this.idColaboracion = idColaboracion;
        this.idEstadoConstancia = idEstadoConstancia;
        this.estado = estado;
        this.fechaSolcitud = fechaSolcitud;
        this.colaboracion = colaboracion;
        this.profesorUV = profesorUV;
        this.periodo = periodo;
        this.profesorExterno = profesorExterno;
        this.experienciaEducativa = experienciaEducativa;
        this.programaEducativo = programaEducativo;
        this.descripcion = descripcion;
        this.estudiante = estudiante;
        this.matricula = matricula;
    }

    

    public int getIdSolicitudConstancia() {
        return idSolicitudConstancia;
    }

    public void setIdSolicitudConstancia(int idSolicitudConstancia) {
        this.idSolicitudConstancia = idSolicitudConstancia;
    }

    public int getIdColaboracion() {
        return idColaboracion;
    }

    public void setIdColaboracion(int idColaboracion) {
        this.idColaboracion = idColaboracion;
    }

    public int getIdEstadoConstancia() {
        return idEstadoConstancia;
    }

    public void setIdEstadoConstancia(int idEstadoConstancia) {
        this.idEstadoConstancia = idEstadoConstancia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaSolcitud() {
        return fechaSolcitud;
    }

    public void setFechaSolcitud(String fechaSolcitud) {
        this.fechaSolcitud = fechaSolcitud;
    }

    public String getColaboracion() {
        return colaboracion;
    }

    public void setColaboracion(String colaboracion) {
        this.colaboracion = colaboracion;
    }

    public String getProfesorUV() {
        return profesorUV;
    }

    public void setProfesorUV(String profesorUV) {
        this.profesorUV = profesorUV;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getProfesorExterno() {
        return profesorExterno;
    }

    public void setProfesorExterno(String profesorExterno) {
        this.profesorExterno = profesorExterno;
    }

    public String getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public void setExperienciaEducativa(String experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
    }

    public String getProgramaEducativo() {
        return programaEducativo;
    }

    public void setProgramaEducativo(String programaEducativo) {
        this.programaEducativo = programaEducativo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
}

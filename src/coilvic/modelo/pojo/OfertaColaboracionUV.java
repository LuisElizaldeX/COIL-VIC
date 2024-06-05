/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase del modelo de oferta de colaboracion UV
 */

package coilvic.modelo.pojo;

public class OfertaColaboracionUV {
    private int idAreaAcademica;
    private int idCampus;
    private int idProgramaEducativo;
    private int idDependencia;
    private int idOfertaColaboracionUV;
    private int idExperienciaEducativa;
    private int idProfesorUV;
    private int idEstadoOfertaColaboracionUV;
    private String descripcion;
    private String nombre;
    private String estado;
    private String periodo;
    private String experienciaEducativa;
    private String creditos;
    private String descripcionEe;
    private String nombreDependencia;
    private String municipio;
    private String nombreProgramaEducativo;
    private String nombreAreaAcademica;
    private String profesorUV;
    private String campus;
    
    private String nombreProfesorUv;
    private String apellidos;
    private int numeroPersonal;
    private String correo;
    private int telefono;
    
   
    public OfertaColaboracionUV() {
    }

    public OfertaColaboracionUV(int idAreaAcademica, int idCampus, int idProgramaEducativo, int idDependencia, int idOfertaColaboracionUV, 
            int idExperienciaEducativa, int idProfesorUV, int idEstadoOfertaColaboracionUV, String descripcion, String nombre, String estado, 
            String periodo, String experienciaEducativa, String creditos, String descripcionEe, String nombreDependencia, String municipio, 
            String nombreProgramaEducativo, String nombreAreaAcademica, String profesorUV, String campus, String nombreProfesorUv, String apellidos, 
            int numeroPersonal, String correo, int telefono) {
        this.idAreaAcademica = idAreaAcademica;
        this.idCampus = idCampus;
        this.idProgramaEducativo = idProgramaEducativo;
        this.idDependencia = idDependencia;
        this.idOfertaColaboracionUV = idOfertaColaboracionUV;
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.idProfesorUV = idProfesorUV;
        this.idEstadoOfertaColaboracionUV = idEstadoOfertaColaboracionUV;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.estado = estado;
        this.periodo = periodo;
        this.experienciaEducativa = experienciaEducativa;
        this.creditos = creditos;
        this.descripcionEe = descripcionEe;
        this.nombreDependencia = nombreDependencia;
        this.municipio = municipio;
        this.nombreProgramaEducativo = nombreProgramaEducativo;
        this.nombreAreaAcademica = nombreAreaAcademica;
        this.profesorUV = profesorUV;
        this.campus = campus;
        this.nombreProfesorUv = nombreProfesorUv;
        this.apellidos = apellidos;
        this.numeroPersonal = numeroPersonal;
        this.correo = correo;
        this.telefono = telefono;
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

    public int getIdProgramaEducativo() {
        return idProgramaEducativo;
    }

    public void setIdProgramaEducativo(int idProgramaEducativo) {
        this.idProgramaEducativo = idProgramaEducativo;
    }

    public int getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(int idDependencia) {
        this.idDependencia = idDependencia;
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

    public String getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public void setExperienciaEducativa(String experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getDescripcionEe() {
        return descripcionEe;
    }

    public void setDescripcionEe(String descripcionEe) {
        this.descripcionEe = descripcionEe;
    }

    public String getNombreDependencia() {
        return nombreDependencia;
    }

    public void setNombreDependencia(String nombreDependencia) {
        this.nombreDependencia = nombreDependencia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNombreProgramaEducativo() {
        return nombreProgramaEducativo;
    }

    public void setNombreProgramaEducativo(String nombreProgramaEducativo) {
        this.nombreProgramaEducativo = nombreProgramaEducativo;
    }

    public String getNombreAreaAcademica() {
        return nombreAreaAcademica;
    }

    public void setNombreAreaAcademica(String nombreAreaAcademica) {
        this.nombreAreaAcademica = nombreAreaAcademica;
    }

    public String getProfesorUV() {
        return profesorUV;
    }

    public void setProfesorUV(String profesorUV) {
        this.profesorUV = profesorUV;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getNombreProfesorUv() {
        return nombreProfesorUv;
    }

    public void setNombreProfesorUv(String nombreProfesorUv) {
        this.nombreProfesorUv = nombreProfesorUv;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getNumeroPersonal() {
        return numeroPersonal;
    }

    public void setNumeroPersonal(int numeroPersonal) {
        this.numeroPersonal = numeroPersonal;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    
    


    
    
    
}

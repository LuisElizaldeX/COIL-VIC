/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase del modelo estado de oferta de colaboracion externa
 */

package coilvic.modelo.pojo;

public class OfertaColaboracionExterna {
    private ProfesorExterno profesorExterno;
    private int idOfertaColaboracionExterna;
    private int idProfesorExterno;
    private int idEstadoOfertaColaboracionExterna;
    private String nombre;
    private String periodo;
    private String descripcion;
    private String estado;
    private String nombreProfesorExterno;
    private String apellidos;
    private String correo;
    private String pais;
    private String telefono;
    private String materia;
    private String carrera;
    private String idioma;
    private String nombreUniversidad;          

    
    public OfertaColaboracionExterna(){
    }

    public OfertaColaboracionExterna(ProfesorExterno profesorExterno, int idOfertaColaboracionExterna, int idProfesorExterno, 
            int idEstadoOfertaColaboracionExterna, String nombre, String periodo, String descripcion, String estado, String nombreProfesorExterno, 
            String apellidos, String correo, String pais, String telefono, String materia, String carrera, String idioma, String nombreUniversidad) {
        this.profesorExterno = profesorExterno;
        this.idOfertaColaboracionExterna = idOfertaColaboracionExterna;
        this.idProfesorExterno = idProfesorExterno;
        this.idEstadoOfertaColaboracionExterna = idEstadoOfertaColaboracionExterna;
        this.nombre = nombre;
        this.periodo = periodo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.nombreProfesorExterno = nombreProfesorExterno;
        this.apellidos = apellidos;
        this.correo = correo;
        this.pais = pais;
        this.telefono = telefono;
        this.materia = materia;
        this.carrera = carrera;
        this.idioma = idioma;
        this.nombreUniversidad = nombreUniversidad;
    }

    public ProfesorExterno getProfesorExterno() {
        return profesorExterno;
    }

    public void setProfesorExterno(ProfesorExterno profesorExterno) {
        this.profesorExterno = profesorExterno;
    }

    public int getIdOfertaColaboracionExterna() {
        return idOfertaColaboracionExterna;
    }

    public void setIdOfertaColaboracionExterna(int idOfertaColaboracionExterna) {
        this.idOfertaColaboracionExterna = idOfertaColaboracionExterna;
    }

    public int getIdProfesorExterno() {
        return idProfesorExterno;
    }

    public void setIdProfesorExterno(int idProfesorExterno) {
        this.idProfesorExterno = idProfesorExterno;
    }

    public int getIdEstadoOfertaColaboracionExterna() {
        return idEstadoOfertaColaboracionExterna;
    }

    public void setIdEstadoOfertaColaboracionExterna(int idEstadoOfertaColaboracionExterna) {
        this.idEstadoOfertaColaboracionExterna = idEstadoOfertaColaboracionExterna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreProfesorExterno() {
        return nombreProfesorExterno;
    }

    public void setNombreProfesorExterno(String nombreProfesorExterno) {
        this.nombreProfesorExterno = nombreProfesorExterno;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getNombreUniversidad() {
        return nombreUniversidad;
    }

    public void setNombreUniversidad(String nombreUniversidad) {
        this.nombreUniversidad = nombreUniversidad;
    }
    
    
    @Override
    public String toString() {
        return  nombre;
    }
    

    
    
    
    
    
    
}

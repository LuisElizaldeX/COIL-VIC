package coilvic.modelo.pojo;

public class Colaboracion {
    private Integer idColaboracion;
    private String nombre;
    private Integer idProfesorExterno;
    private Integer idExperienciaEducativa;
    private Integer idPeriodo;
    private Integer idEstadoColaboracion;
    private Integer idSyllabus;
    private Integer idProfesorUV;
    private Integer idIdioma;
    private String fechaInicio;
    private String fechaFin;
    private String tipoColaboracion;
    private String justificacion;
    private String profesorExterno;
    private String experienciaEducativa;
    private String periodo;
    private String estadoColaboracion;
    private byte[] syllabus;
    private String profesorUV;
    private String idioma;

    public Colaboracion() {
    }

    public Colaboracion(Integer idColaboracion, String nombre, Integer idProfesorExterno, 
            Integer idExperienciaEducativa, Integer idPeriodo, Integer idEstadoColaboracion, 
            Integer idSyllabus, Integer idProfesorUV, Integer idIdioma, String fechaInicio, 
            String fechaFin, String tipoColaboracion, String justificacion, String profesorExterno, 
            String experienciaEducativa, String periodo, String estadoColaboracion, byte[] syllabus, 
            String profesorUV, String idioma) {
        this.idColaboracion = idColaboracion;
        this.nombre = nombre;
        this.idProfesorExterno = idProfesorExterno;
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.idPeriodo = idPeriodo;
        this.idEstadoColaboracion = idEstadoColaboracion;
        this.idSyllabus = idSyllabus;
        this.idProfesorUV = idProfesorUV;
        this.idIdioma = idIdioma;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoColaboracion = tipoColaboracion;
        this.justificacion = justificacion;
        this.profesorExterno = profesorExterno;
        this.experienciaEducativa = experienciaEducativa;
        this.periodo = periodo;
        this.estadoColaboracion = estadoColaboracion;
        this.syllabus = syllabus;
        this.profesorUV = profesorUV;
        this.idioma = idioma;
    }

    

    public Integer getIdColaboracion() {
        return idColaboracion;
    }

    public void setIdColaboracion(Integer idColaboracion) {
        this.idColaboracion = idColaboracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdProfesorExterno() {
        return idProfesorExterno;
    }

    public void setIdProfesorExterno(Integer idProfesorExterno) {
        this.idProfesorExterno = idProfesorExterno;
    }

    public Integer getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public void setIdExperienciaEducativa(Integer idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdEstadoColaboracion() {
        return idEstadoColaboracion;
    }

    public void setIdEstadoColaboracion(Integer idEstadoColaboracion) {
        this.idEstadoColaboracion = idEstadoColaboracion;
    }

    public Integer getIdSyllabus() {
        return idSyllabus;
    }

    public void setIdSyllabus(Integer idSyllabus) {
        this.idSyllabus = idSyllabus;
    }

    public Integer getIdProfesorUV() {
        return idProfesorUV;
    }

    public void setIdProfesorUV(Integer idProfesorUV) {
        this.idProfesorUV = idProfesorUV;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTipoColaboracion() {
        return tipoColaboracion;
    }

    public void setTipoColaboracion(String tipoColaboracion) {
        this.tipoColaboracion = tipoColaboracion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
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

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getEstadoColaboracion() {
        return estadoColaboracion;
    }

    public void setEstadoColaboracion(String estadoColaboracion) {
        this.estadoColaboracion = estadoColaboracion;
    }

    public byte[] getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(byte[] syllabus) {
        this.syllabus = syllabus;
    }

    public String getProfesorUV() {
        return profesorUV;
    }

    public void setProfesorUV(String profesorUV) {
        this.profesorUV = profesorUV;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    
    
}

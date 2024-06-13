package coilvic.modelo.pojo;

public class Colaboracion {
    private Integer idColaboracion;
    private String nombre;
    private Integer idProfesorExterno;
    private Integer idExperienciaEducativa;
    private Integer idPeriodo;
    private Integer idEstadoColaboracion;
    private Integer idArchivo;
    private Integer idProfesorUV;
    private Integer idIdioma;
    private String numeroEstudiantes;
    private String fechaInicio;
    private String fechaFin;
    private String fechaInicioOferta;
    private String fechaFinOferta;
    private String tipoColaboracion;
    private String justificacion;
    private String profesorExterno;
    private String experienciaEducativa;
    private String periodo;
    private String estadoColaboracion;
    private byte[] syllabus;
    private byte[] evidencia;
    private String nombreEvidencia;
    private String profesorUV;
    private String idioma;
    private String materia;
    private String descripcion;

    public Colaboracion() {
    }

    public Colaboracion(Integer idColaboracion, String nombre, Integer idProfesorExterno, 
            Integer idExperienciaEducativa, Integer idPeriodo, Integer idEstadoColaboracion, 
            Integer idArchivo, Integer idProfesorUV, Integer idIdioma, String numeroEstudiantes, 
            String fechaInicio,String fechaFin, String fechaInicioOferta, String fechaFinOferta, 
            String tipoColaboracion, String justificacion, String profesorExterno,
            String experienciaEducativa, String periodo, String estadoColaboracion, byte[] syllabus, 
            byte[] evidencia, String nombreEvidencia, String profesorUV, String idioma, 
            String materia, String descripcion) {
        this.idColaboracion = idColaboracion;
        this.nombre = nombre;
        this.idProfesorExterno = idProfesorExterno;
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.idPeriodo = idPeriodo;
        this.idEstadoColaboracion = idEstadoColaboracion;
        this.idArchivo = idArchivo;
        this.idProfesorUV = idProfesorUV;
        this.idIdioma = idIdioma;
        this.numeroEstudiantes = numeroEstudiantes;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaInicioOferta = fechaInicioOferta;
        this.fechaFinOferta = fechaFinOferta;
        this.tipoColaboracion = tipoColaboracion;
        this.justificacion = justificacion;
        this.profesorExterno = profesorExterno;
        this.experienciaEducativa = experienciaEducativa;
        this.periodo = periodo;
        this.estadoColaboracion = estadoColaboracion;
        this.syllabus = syllabus;
        this.evidencia = evidencia;
        this.nombreEvidencia = nombreEvidencia;
        this.profesorUV = profesorUV;
        this.idioma = idioma;
        this.materia = materia;
        this.descripcion = descripcion;
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

    public Integer getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public Integer getIdProfesorUV() {
        return idProfesorUV;
    }

    public void setIdProfesorUV(Integer idProfesorUV) {
        this.idProfesorUV = idProfesorUV;
    }

    public byte[] getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(byte[] evidencia) {
        this.evidencia = evidencia;
    }

    public String getNombreEvidencia() {
        return nombreEvidencia;
    }

    public void setNombreEvidencia(String nombreEvidencia) {
        this.nombreEvidencia = nombreEvidencia;
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

    public String getFechaInicioOferta() {
        return fechaInicioOferta;
    }

    public void setFechaInicioOferta(String fechaInicioOferta) {
        this.fechaInicioOferta = fechaInicioOferta;
    }

    public String getFechaFinOferta() {
        return fechaFinOferta;
    }

    public void setFechaFinOferta(String fechaFinOferta) {
        this.fechaFinOferta = fechaFinOferta;
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

    public String getNumeroEstudiantes() {
        return numeroEstudiantes;
    }

    public void setNumeroEstudiantes(String numeroEstudiantes) {
        this.numeroEstudiantes = numeroEstudiantes;
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

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}

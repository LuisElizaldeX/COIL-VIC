package coilvic.modelo.pojo;

public class Archivo {
    private Integer idArchivo;
    private String nombre;
    private byte[] archivoCol;
    private Integer idTipoArchivo;

    public Archivo() {
    }

    public Archivo(Integer idArchivo, String nombre, byte[] archivoCol, Integer idTipoArchivo) {
        this.idArchivo = idArchivo;
        this.nombre = nombre;
        this.archivoCol = archivoCol;
        this.idTipoArchivo = idTipoArchivo;
    }

    public Integer getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getArchivoCol() {
        return archivoCol;
    }

    public void setArchivoCol(byte[] archivoCol) {
        this.archivoCol = archivoCol;
    }

    public Integer getIdTipoArchivo() {
        return idTipoArchivo;
    }

    public void setIdTipoArchivo(Integer idTipoArchivo) {
        this.idTipoArchivo = idTipoArchivo;
    }
    
    
}

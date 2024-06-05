package coilvic.modelo.pojo;

public class Dependencia {
    private int idDependencia;
    private int idCampus;
    private int idProgramaEducativo;
    private String nombre;
    private String municipio;

    public Dependencia() {
    }

    public Dependencia(int idDependencia, int idCampus, int idProgramaEducativo, String nombre, String municipio) {
        this.idDependencia = idDependencia;
        this.idCampus = idCampus;
        this.idProgramaEducativo = idProgramaEducativo;
        this.nombre = nombre;
        this.municipio = municipio;
    }

    public int getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(int idDependencia) {
        this.idDependencia = idDependencia;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}

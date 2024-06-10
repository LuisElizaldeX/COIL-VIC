/*
* Autor: Erick Utrera Cornejo
* Fecha de creación: 29/05/2024
* Descripción: Clase del modelo de Campus
*/

package coilvic.modelo.pojo;

public class Campus {
    private int idCampus;
    private String nombre;

    public Campus(){
    }
    
    public Campus(int idCampus, String nombre) {
        this.idCampus = idCampus;
        this.nombre = nombre;
    }

    public int getIdCampus() {
        return idCampus;
    }

    public void setIdCampus(int idCampus) {
        this.idCampus = idCampus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
    
    
    
}

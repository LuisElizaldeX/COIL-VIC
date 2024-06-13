/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 12/06/2024
 * Descripción: Clase del modelo de idioma
 */

package coilvic.modelo.pojo;

public class Idioma {
    private int idIdioma;
    private String lengua;

    public Idioma() {
    }

    public Idioma(int idIdioma, String lengua) {
        this.idIdioma = idIdioma;
        this.lengua = lengua;
    }

    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getLengua() {
        return lengua;
    }

    public void setLengua(String lengua) {
        this.lengua = lengua;
    }

    @Override
    public String toString() {
        return lengua;
    }
    
}

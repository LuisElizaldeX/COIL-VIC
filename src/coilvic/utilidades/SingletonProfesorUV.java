/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 18/05/2024
* Descripción: Clase para modelar el objeto SingletonProfesorUV,una clase estatica de la instancia
*/

package coilvic.utilidades;

import coilvic.modelo.pojo.ProfesorUV;

public class SingletonProfesorUV {
     private static SingletonProfesorUV instancia;
    private ProfesorUV profesor;
    
    private SingletonProfesorUV() {
        
    }
    
    public static SingletonProfesorUV getInstancia() {
        if (instancia == null) {
            instancia = new SingletonProfesorUV();
        }
        return instancia;
    }
    
    public ProfesorUV getProfesorUV() {
        return profesor;
    }
    
    public void setProfesorUV(ProfesorUV profesor) {
        this.profesor = profesor;
    }
    
}

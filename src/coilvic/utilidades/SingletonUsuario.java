/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase singleton para usuario 
 */
package coilvic.utilidades;

import coilvic.modelo.pojo.Usuario;

public class SingletonUsuario {
    
    private static SingletonUsuario instancia;
    private Usuario usuario;
    
    private SingletonUsuario() {
        
    }
    
    public static SingletonUsuario getInstancia() {
        if (instancia == null) {
            instancia = new SingletonUsuario();
        }
        return instancia;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}


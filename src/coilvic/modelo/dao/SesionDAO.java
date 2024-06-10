/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 17/05/2024
* Descripción: Clase SesionDAO para obtener el usuario con el que se inicia sesion desde la base    
  de datos
*/

package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.RespuestaLogin;
import coilvic.modelo.pojo.Usuario;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SesionDAO {
    
    public static RespuestaLogin IniciarSesionUsuario(String nombre, String password){
        RespuestaLogin respuesta = new RespuestaLogin();
        Connection conexion = ConexionBD.obtenerConexion();
        if(conexion != null){
            try 
            {
                String consulta = "SELECT Usuario.idUsuario, Usuario.nombre, Usuario.contraseña, "
                        + "Usuario.idTipoUsuario, TipoUsuario.tipoDeUsuario FROM Usuario "
                        + "INNER JOIN TipoUsuario ON Usuario.idTipoUsuario = "
                        + "TipoUsuario.idTipoUsuario "
                        + "WHERE nombre = ? "
                        + "AND contraseña = ?;";
                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                prepararSentencia.setString(1, nombre);
                prepararSentencia.setString(2, password);
                ResultSet resultadoSentencia = prepararSentencia.executeQuery();
                if (resultadoSentencia.next()) 
                {
                    respuesta.setError(false);
                    respuesta.setMensaje("Usuario identificado correctamente");
                    Usuario usuarioSesion = new Usuario();
                    usuarioSesion.setIdUsuario
        (resultadoSentencia.getInt("idUsuario"));
                    usuarioSesion.setNombre(resultadoSentencia.getString("nombre"));
                    usuarioSesion.setContrasena
        (resultadoSentencia.getString("contraseña"));
                    usuarioSesion.setIdTipoUsuario
        (resultadoSentencia.getInt("idTipoUsuario"));
                    usuarioSesion.setTipoUsuario
        (resultadoSentencia.getString("tipoDeUsuario"));
                    respuesta.setUsuario(usuarioSesion);
                }else{
                    respuesta.setError(true);
                    respuesta.setMensaje("Informacion ingresada incorrecta");
                }
                conexion.close();
            } catch (SQLException ex) {
                respuesta.setError(true);
                respuesta.setMensaje(ex.getMessage());
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
    
}

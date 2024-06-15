/*
* Autor: Josué Melgarejo García
* Fecha de creación: 01/06/2024
* Descripción: Clase ProfesorExternoDAO para obtener al ProfesorExterno
*/
package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.ProfesorExterno;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ProfesorExternoDAO {
    
    public static HashMap<String, Object> 
        obtenerProfesorExternoPorColaboracion(int idColaboracion){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                String consulta = "SELECT pe.idProfesorExterno, pe.nombre, apellidos, correo, "
                        + "pais, telefono, materia, carrera, u.nombre AS universidad "
                        + "FROM profesorExterno pe "
                        + "JOIN colaboracion c ON pe.idProfesorExterno = c.idProfesorExterno "
                        + "JOIN universidad u ON pe.idUniversidad = u.idUniversidad "
                        + "WHERE idColaboracion = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idColaboracion);
                ResultSet resultado = prepararSentencia.executeQuery();
                ProfesorExterno profesorExterno = new ProfesorExterno();
                if(resultado.next()){     
                    profesorExterno.setNombre(resultado.getString("nombre"));
                    profesorExterno.setApellidos
        (resultado.getString("apellidos"));
                    profesorExterno.setCorreo(resultado.getString("correo"));
                    profesorExterno.setPais(resultado.getString("pais"));
                    profesorExterno.setTelefono(resultado.getString("telefono"));
                    profesorExterno.setMateria(resultado.getString("materia"));
                    profesorExterno.setCarrera(resultado.getString("carrera"));
                    profesorExterno.setNombreUniversidad(resultado.
                            getString("universidad"));
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("profesorExterno", profesorExterno);
                conexionBD.close();
            } catch (SQLException e) {
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        } else {
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
            
        }
}

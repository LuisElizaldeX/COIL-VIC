/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 28/05/2024
* Descripción: Clase SesionDAO para obtener al estudiante para modificar, 
* crear y eliminar su informacion
*/

package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.Estudiante;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class EstudianteDAO {
    public static HashMap<String, Object> obtenerEstudiantes(int idColaboracion){
        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try {
                String consulta = "SELECT e.idEstudiante, concat(e.nombre, ' ', e.apellidoPaterno, "
                        + "' ', e.apellidoMaterno) AS nombreCompleto, e.matricula "
                        + "FROM colaboracion_estudiante ce "
                        + "JOIN estudiante e ON ce.idEstudiante = e.idEstudiante "
                        + "JOIN colaboracion c ON ce.idColaboracion = c.idColaboracion "
                        + "where c.idColaboracion = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idColaboracion);
                ResultSet resultado = prepararSentencia.executeQuery();
                List<Estudiante> estudiantes = new ArrayList<>();
                while(resultado.next()){
                    Estudiante estudiante = new Estudiante();
                    estudiante.setIdEstudiante
        (resultado.getInt("idEstudiante"));
                    estudiante.setNombreCompleto(resultado.
                            getString("nombreCompleto"));
                    estudiante.setMatricula(resultado.getString("matricula"));
                    estudiantes.add(estudiante);
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("estudiantes", estudiantes);
                conexionBD.close();
            } catch (SQLException e) {
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        } else {
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
    
    
    public static HashMap<String, Object> eliminarEstudiante(int idEstudiante, int idColaboracion){
        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try {
                String consulta = "DELETE FROM colaboracion_estudiante "
                        + "WHERE idColaboracion = ? AND idEstudiante = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idColaboracion);
                prepararSentencia.setInt(2, idEstudiante);
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas > 0){
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "El estudiante ha sido eliminado correctamente");
                } else {
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "Lo sentimos, hubo un error al eliminar al estudiante, "
                                    + "favor de intentarlo más tarde");
                }
                conexionBD.close();
            } catch (SQLException e) {
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        } else {
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
    
    
    public static HashMap<String, Object> registrarEstudiante(Estudiante estudiante) {
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if (conexionBD != null) {
            try {
                String consulta = 
                    "INSERT INTO estudiante (nombre, matricula, apellidoPaterno, apellidoMaterno) "
                        + "VALUES (?, ?, ?, ?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement
                    (consulta, Statement.RETURN_GENERATED_KEYS);
                prepararSentencia.setString(1, estudiante.getNombre());
                prepararSentencia.setString(2, estudiante.getMatricula());
                prepararSentencia.setString(3, estudiante.getApellidoPaterno());
                prepararSentencia.setString(4, estudiante.getApellidoMaterno());
            int filasInsertadas = prepararSentencia.executeUpdate();
            if (filasInsertadas > 0) {
                ResultSet generatedKeys = prepararSentencia.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idEstudiante = generatedKeys.getInt(1);
                    respuesta.put("idEstudiante", idEstudiante);
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put(Constantes.KEY_MENSAJE, 
                        "El alumno se ha registro de manera exitosa.");
            } else {
                respuesta.put(Constantes.KEY_MENSAJE, 
                        "No se pudo registrar al estudiante.");
            }
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

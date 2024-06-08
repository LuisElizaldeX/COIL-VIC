package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ColaboracionDAO {
    public static HashMap<String, Object> obtenerColaboracionesEnCurso(){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try {
                String consulta = "SELECT idColaboracion, c.nombre, concat(profex.nombre, ' ', profex.apellidos) AS profesorExterno, ee.nombre AS experienciaEducativa, "
                        + "ec.estado AS estadoColaboracion, concat(puv.nombre, ' ', puv.apellidos) AS profesorUV, fechaInicio, fechaFin, "
                        + "tipoColaboracion, i.lengua AS idioma "
                        + "FROM colaboracion c "
                        + "JOIN profesorexterno profex ON c.idProfesorExterno = profex.idProfesorExterno "
                        + "JOIN experienciaeducativa ee ON c.idExperienciaEducativa = ee.idExperienciaEducativa "
                        + "JOIN estadocolaboracion ec ON c.idEstadoColaboracion = ec.idEstadoColaboracion "
                        + "JOIN profesoruv puv ON c.idProfesorUV = puv.idProfesorUV "
                        + "JOIN idioma i ON c.idIdioma = i.idIdioma "
                        + "WHERE c.idEstadoColaboracion = 1";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                List<Colaboracion> colaboraciones = new ArrayList<>();
                while(resultado.next()){
                    Colaboracion colaboracion = new Colaboracion();
                    colaboracion.setIdColaboracion(resultado.getInt("idColaboracion"));
                    colaboracion.setNombre(resultado.getString("nombre"));
                    colaboracion.setProfesorExterno(resultado.getString("profesorExterno"));
                    colaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                    colaboracion.setEstadoColaboracion(resultado.getString("estadoColaboracion"));
                    colaboracion.setProfesorUV(resultado.getString("profesorUV"));
                    colaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                    colaboracion.setFechaFin(resultado.getString("fechaFin"));
                    colaboracion.setTipoColaboracion(resultado.getString("tipoColaboracion"));
                    colaboracion.setIdioma(resultado.getString("idioma"));
                    colaboraciones.add(colaboracion);
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("colaboraciones", colaboraciones);
                conexionBD.close();
            } catch (SQLException e) {
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        } else {
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
    
    public static HashMap<String, Object> cancelarColaboracion(int idColaboracion){
        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try {
                String consulta = "UPDATE colaboracion SET idEstadoColaboracion = 3 WHERE idColaboracion = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idColaboracion);
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas > 0){
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, "El proceso de cancelación se ha realizado con éxito");
                } else {
                    respuesta.put(Constantes.KEY_MENSAJE,
                            "Lo sentimos, hubo un error al cancelar la colaboración, favor de intentarlo más tarde");
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
    
    public static HashMap<String, Object> agregarJustificacionColaboracion(int idColaboracion, String justificacion){
        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try {
                String consulta = "UPDATE colaboracion SET justificacion = ? WHERE idColaboracion = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, justificacion);
                prepararSentencia.setInt(2, idColaboracion);
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas > 0){
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, "La justificación se ha añadido correctamente");
                } else {
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "Lo sentimos, hubo un error al añadir la justificación, favor de intentarlo más tarde");
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

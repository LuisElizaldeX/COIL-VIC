/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 08/06/2024
* Descripción: Clase SolicitudConstanciaDAO para obtener la informacion de las solicitudes de las
* constancias
*/

package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.SolicitudConstancia;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SolicitudConstanciaDAO {
    
    public static HashMap<String, Object> obtenerSolicitudesConstancia() {
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if (conexionBD != null) {
            try {
                String consulta = 
                "SELECT sc.idColaboracion, c.nombre AS colaboracion, c.descripcion, "
                + "CONCAT(puv.nombre, ' ', puv.apellidos) AS profesoruv, "
                + "CONCAT(pex.nombre, ' ', pex.apellidos) AS profesorexterno, "
                + "ee.nombre AS experienciaEducativa, pe.nombre AS programaEducativo "        
                + "FROM solicitudconstancia sc "
                + "JOIN colaboracion c ON sc.idColaboracion = c.idColaboracion "
                + "JOIN profesoruv puv ON c.idProfesoruv = puv.idProfesoruv "
                + "JOIN profesorexterno pex ON c.idProfesorexterno = pex.idProfesorexterno "
                + "JOIN experienciaeducativa ee "
                + "ON c.idExperienciaEducativa = ee.idExperienciaEducativa "
                + "JOIN dependencia d ON ee.idDependencia = d.idDependencia "
                + "JOIN programaeducativo pe ON d.idProgramaEducativo = pe.idProgramaEducativo "       
                + "WHERE c.idEstadoColaboracion = 2 AND idEstadoConstancia = 1";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                List<SolicitudConstancia> solicitudConstancia = new ArrayList();
                while (resultado.next()) {
                    SolicitudConstancia solicitud = new SolicitudConstancia();
                    solicitud.setIdColaboracion
                        (resultado.getInt("idColaboracion"));
                    solicitud.setColaboracion
                        (resultado.getString("colaboracion"));
                    solicitud.setDescripcion
                        (resultado.getString("descripcion"));
                    solicitud.setExperienciaEducativa
                    (resultado.getString("experienciaEducativa"));
                    solicitud.setProgramaEducativo
                    (resultado.getString("programaEducativo"));
                    solicitud.setProfesorUV
                        (resultado.getString("profesoruv"));
                    solicitud.setProfesorExterno
                        (resultado.getString("profesorexterno"));
                    solicitudConstancia.add(solicitud);
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("SolicitudConstancia", solicitudConstancia);
                conexionBD.close();
            } catch (SQLException e) {
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        } else {
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
    
    public static HashMap<String, Object> obtenerEstudiantesColaboracion(int idColaboracion) {
        Connection conexionBD = ConexionBD.obtenerConexion();
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        if (conexionBD != null) {
            try {
                String consulta = 
                    "SELECT CONCAT(es.nombre, ' ', es.apellidoPaterno, ' ', es.apellidoMaterno) "
                        + "AS nombreEstudiante, "
                        + "es.matricula "
                        + "FROM colaboracion c "
                        + "JOIN colaboracion_estudiante ce ON c.idColaboracion = ce.idColaboracion "
                        + "JOIN estudiante es ON ce.idEstudiante = es.idEstudiante "
                        + "WHERE c.idColaboracion = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idColaboracion);
                ResultSet resultado = prepararSentencia.executeQuery();
                List<SolicitudConstancia> solicitud = new ArrayList();
                while (resultado.next()) {
                    SolicitudConstancia solicitudConstancia = new SolicitudConstancia();
                    solicitudConstancia.setEstudiante
                        (resultado.getString("nombreEstudiante"));
                    solicitudConstancia.setMatricula
                        (resultado.getString("matricula"));
                    solicitud.add(solicitudConstancia);
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("Estudiantes", solicitud);
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

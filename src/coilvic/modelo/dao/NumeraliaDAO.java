/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 05/06/2024
* Descripción: Clase DAO para manejar el acceso, guardado, modificacion y 
  eliminacion de los datos de la clase Numeralia
*/

package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.Numeralia;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class NumeraliaDAO {
    
    public static HashMap<String, Object> obtenerNumeraliaPorRegion() {
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT c.nombre AS campus, COUNT(DISTINCT col.idProfesorUV) + "
                + "COUNT(DISTINCT col.idProfesorExterno) AS total_profesores, "
                + "COUNT(DISTINCT ce.idEstudiante) AS total_estudiantes " 
                + "FROM colaboracion col " 
                + "JOIN experienciaeducativa ee "
                + "ON col.idExperienciaEducativa = ee.idExperienciaEducativa " 
                + "JOIN dependencia dep ON ee.idDependencia = dep.idDependencia " 
                + "JOIN campus c ON dep.idCampus = c.idCampus "
                + "LEFT JOIN colaboracion_estudiante ce ON col.idColaboracion = ce.idColaboracion "
                + "WHERE col.idEstadoColaboracion = 2 "
                + "GROUP BY c.nombre";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                List<Numeralia> numeralia = new ArrayList();
                while (resultado.next()) {
                    Numeralia num = new Numeralia();
                    num.setRegion(resultado.getString("campus"));
                    num.setProfesores(resultado.getInt("total_profesores"));
                    num.setAlumnos(resultado.getInt("total_estudiantes"));
                    numeralia.add(num);
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("Numeralia", numeralia);
                conexionBD.close();
            } catch (SQLException e) {
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        } else {
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
    
    public static HashMap<String, Object> obtenerNumeraliaPorAreaAcademica() {
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT aa.nombre AS area_academica, "
                + "COUNT(DISTINCT col.idProfesorUV) + COUNT(DISTINCT col.idProfesorExterno) "
                + "AS total_profesores, "
                + "COUNT(DISTINCT ce.idEstudiante) AS total_estudiantes "
                + "FROM colaboracion col "
                + "JOIN experienciaeducativa ee "
                + "ON col.idExperienciaEducativa = ee.idExperienciaEducativa "
                + "JOIN dependencia dep ON ee.idDependencia = dep.idDependencia "
                + "JOIN programaeducativo pe ON dep.idProgramaEducativo = pe.idProgramaEducativo "
                + "JOIN areaacademica aa ON pe.idAreaAcademica = aa.idAreaAcademica "
                + "LEFT JOIN colaboracion_estudiante ce ON col.idColaboracion = ce.idColaboracion "
                + "WHERE col.idEstadoColaboracion = 2 "
                + "GROUP BY aa.nombre";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                List<Numeralia> numeralia = new ArrayList();
                while (resultado.next()) {
                    Numeralia num = new Numeralia();
                    num.setAreaAcademica(resultado.
                            getString("area_academica"));
                    num.setProfesores(resultado.getInt("total_profesores"));
                    num.setAlumnos(resultado.getInt("total_estudiantes"));
                    numeralia.add(num);
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("Numeralia", numeralia);
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

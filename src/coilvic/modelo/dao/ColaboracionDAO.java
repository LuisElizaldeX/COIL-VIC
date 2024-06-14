/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 02/06/2024
* Descripción: Clase ColaboracionDAO para obtener la colaboracion, para modificar, 
* crear y eliminar su informacion
*/

package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                String consulta = "SELECT idColaboracion, c.nombre, c.idProfesorExterno, "
                        + "concat(profex.nombre, ' ', profex.apellidos) AS profesorExterno, "
                        + "c.idExperienciaEducativa, ee.nombre AS experienciaEducativa, "
                        + "c.idEstadoColaboracion, ec.estado AS estadoColaboracion, "
                        + "idArchivo, c.idProfesorUV, concat(puv.nombre, ' ', puv.apellidos) "
                        + "AS profesorUV, fechaInicio, fechaFin, "
                        + "tipoColaboracion, c.idIdioma, i.lengua AS idioma "
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
                    colaboracion.setIdProfesorExterno(resultado.getInt("idProfesorExterno"));
                    colaboracion.setProfesorExterno(resultado.getString("profesorExterno"));
                    colaboracion.setIdExperienciaEducativa(resultado.getInt("idExperienciaEducativa"));
                    colaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                    colaboracion.setIdEstadoColaboracion(resultado.getInt("idEstadoColaboracion"));
                    colaboracion.setEstadoColaboracion(resultado.getString("estadoColaboracion"));
                    colaboracion.setIdArchivo(resultado.getInt("idArchivo"));
                    colaboracion.setIdProfesorUV(resultado.getInt("idProfesorUV"));
                    colaboracion.setProfesorUV(resultado.getString("profesorUV"));
                    colaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                    colaboracion.setFechaFin(resultado.getString("fechaFin"));
                    colaboracion.setTipoColaboracion(resultado.getString("tipoColaboracion"));
                    colaboracion.setIdIdioma(resultado.getInt("idIdioma"));
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

    
    public static HashMap<String, Object> obtenerColaboracion(int idColaboracion) {
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT idColaboracion, c.nombre, c.idProfesorExterno, "
                        + "concat(profex.nombre, ' ', profex.apellidos) AS profesorExterno, "
                        + "c.idExperienciaEducativa, ee.nombre AS experienciaEducativa, "
                        + "c.idEstadoColaboracion, ec.estado AS estadoColaboracion, "
                        + "idArchivo, c.idProfesorUV, concat(puv.nombre, ' ', puv.apellidos) "
                        + "AS profesorUV, fechaInicio, fechaFin, "
                        + "tipoColaboracion, c.idIdioma, i.lengua AS idioma "
                        + "FROM colaboracion c "
                        + "JOIN profesorexterno profex ON c.idProfesorExterno = profex.idProfesorExterno "
                        + "JOIN experienciaeducativa ee ON c.idExperienciaEducativa = ee.idExperienciaEducativa "
                        + "JOIN estadocolaboracion ec ON c.idEstadoColaboracion = ec.idEstadoColaboracion "
                        + "JOIN profesoruv puv ON c.idProfesorUV = puv.idProfesorUV "
                        + "JOIN idioma i ON c.idIdioma = i.idIdioma "
                        + "WHERE idColaboracion = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idColaboracion);
                ResultSet resultado = prepararSentencia.executeQuery();
                if (resultado.next()) {
                    Colaboracion colaboracion = new Colaboracion();
                    colaboracion.setIdColaboracion(resultado.getInt("idColaboracion"));
                    colaboracion.setNombre(resultado.getString("nombre"));
                    colaboracion.setIdProfesorExterno(resultado.getInt("idProfesorExterno"));
                    colaboracion.setProfesorExterno(resultado.getString("profesorExterno"));
                    colaboracion.setIdExperienciaEducativa(resultado.getInt("idExperienciaEducativa"));
                    colaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                    colaboracion.setIdEstadoColaboracion(resultado.getInt("idEstadoColaboracion"));
                    colaboracion.setEstadoColaboracion(resultado.getString("estadoColaboracion"));
                    colaboracion.setIdArchivo(resultado.getInt("idArchivo"));
                    colaboracion.setIdProfesorUV(resultado.getInt("idProfesorUV"));
                    colaboracion.setProfesorUV(resultado.getString("profesorUV"));
                    colaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                    colaboracion.setFechaFin(resultado.getString("fechaFin"));
                    colaboracion.setTipoColaboracion(resultado.getString("tipoColaboracion"));
                    colaboracion.setIdIdioma(resultado.getInt("idIdioma"));
                    colaboracion.setIdioma(resultado.getString("idioma"));

                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put("colaboracion", colaboracion);
                } else {
                    respuesta.put(Constantes.KEY_MENSAJE, "No se encontró la colaboración.");
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
    
    
    public static HashMap<String, Object> cerrarColaboracion(int idColaboracion){
        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try {
                String consulta = "UPDATE colaboracion SET idEstadoColaboracion = 2 WHERE idColaboracion = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idColaboracion);
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas > 0){
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "La colaboración se ha cerrado exitosamente");
                } else {
                    respuesta.put(Constantes.KEY_MENSAJE,
                            "Lo sentimos, hubo un error al cerrar la colaboración, "
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
    
    
    public static HashMap<String, Object> obtenerColaboracionesProfesorUV(int idProfesorUV){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try {
                String consulta = "SELECT idColaboracion, c.nombre, c.idProfesorExterno, "
                        + "concat(profex.nombre, ' ', profex.apellidos) AS profesorExterno, "
                        + "c.idExperienciaEducativa, ee.nombre AS experienciaEducativa, "
                        + "c.idEstadoColaboracion, ec.estado AS estadoColaboracion, "
                        + "idArchivo, c.idProfesorUV, concat(puv.nombre, ' ', puv.apellidos) "
                        + "AS profesorUV, fechaInicio, fechaFin, "
                        + "tipoColaboracion, c.idIdioma, i.lengua AS idioma "
                        + "FROM colaboracion c "
                        + "JOIN profesorexterno profex ON c.idProfesorExterno = profex.idProfesorExterno "
                        + "JOIN experienciaeducativa ee ON c.idExperienciaEducativa = ee.idExperienciaEducativa "
                        + "JOIN estadocolaboracion ec ON c.idEstadoColaboracion = ec.idEstadoColaboracion "
                        + "JOIN profesoruv puv ON c.idProfesorUV = puv.idProfesorUV "
                        + "JOIN idioma i ON c.idIdioma = i.idIdioma "
                        + "WHERE c.idProfesorUV = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idProfesorUV);
                ResultSet resultado = prepararSentencia.executeQuery();
                List<Colaboracion> colaboraciones = new ArrayList<>();
                while(resultado.next()){
                    Colaboracion colaboracion = new Colaboracion();
                    colaboracion.setIdColaboracion(resultado.getInt("idColaboracion"));
                    colaboracion.setNombre(resultado.getString("nombre"));
                    colaboracion.setIdProfesorExterno(resultado.getInt("idProfesorExterno"));
                    colaboracion.setProfesorExterno(resultado.getString("profesorExterno"));
                    colaboracion.setIdExperienciaEducativa(resultado.getInt("idExperienciaEducativa"));
                    colaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                    colaboracion.setIdEstadoColaboracion(resultado.getInt("idEstadoColaboracion"));
                    colaboracion.setEstadoColaboracion(resultado.getString("estadoColaboracion"));
                    colaboracion.setIdArchivo(resultado.getInt("idArchivo"));
                    colaboracion.setIdProfesorUV(resultado.getInt("idProfesorUV"));
                    colaboracion.setProfesorUV(resultado.getString("profesorUV"));
                    colaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                    colaboracion.setFechaFin(resultado.getString("fechaFin"));
                    colaboracion.setTipoColaboracion(resultado.getString("tipoColaboracion"));
                    colaboracion.setIdIdioma(resultado.getInt("idIdioma"));
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

    
public static HashMap<String, Object> guardarColaboracion(int idProfesorExterno, 
        String nombreColaboracion, String experienciaEducativa, String fechaInicio, String fechaFin, 
        String idioma, int idProfesorUV, String fechaInicio1, String fechaFin1) {
    HashMap<String, Object> respuesta = new LinkedHashMap<>();
    respuesta.put(Constantes.KEY_ERROR, true);
    Connection conexionBD = ConexionBD.obtenerConexion();
    
    if (conexionBD != null) {
        try {
            String consulta = "INSERT INTO colaboracion (nombre, idProfesorExterno, idExperienciaEducativa, idEstadoColaboracion, idProfesoruv, fechaInicio, fechaFin, idIdioma) VALUES (?, ?, ?, 1, ?, ?, ?, ?)";
            PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
            prepararSentencia.setString(1, nombreColaboracion);
            prepararSentencia.setInt(2, idProfesorExterno);
            prepararSentencia.setString(3, experienciaEducativa);
            prepararSentencia.setInt(4, idProfesorUV);
            prepararSentencia.setString(5, fechaInicio);
            prepararSentencia.setString(6, fechaFin);
            prepararSentencia.setString(7, idioma);
            
            int filasInsertadas = prepararSentencia.executeUpdate();
            if (filasInsertadas > 0) {
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put(Constantes.KEY_MENSAJE, "La colaboración se ha guardado exitosamente");
            } else {
                respuesta.put(Constantes.KEY_MENSAJE, "No se pudo insertar la colaboración.");
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


    public static HashMap<String, Object> guardarColaboracionExterna(Colaboracion colaboracion, 
            int idArchivo) {
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if (conexionBD != null) {
            try {
                String consulta = 
                    "INSERT INTO colaboracion (nombre, idProfesorExterno, idEstadoColaboracion, "
                        + "idProfesoruv, fechaInicio, fechaFin, descripcion, idArchivo, idIdioma) "
                        + "VALUES (?, ?, 1, ?, ?, ?, ?, ?, ?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement
                    (consulta, Statement.RETURN_GENERATED_KEYS);
                prepararSentencia.setString(1, colaboracion.getNombre());
                prepararSentencia.setInt(2, colaboracion.getIdProfesorExterno());
                prepararSentencia.setInt(3, colaboracion.getIdProfesorUV());
                prepararSentencia.setString(4, colaboracion.getFechaInicio());
                prepararSentencia.setString(5, colaboracion.getFechaFin());
                prepararSentencia.setString(6, colaboracion.getDescripcion());
                prepararSentencia.setInt(7, idArchivo);
                prepararSentencia.setInt(8, colaboracion.getIdIdioma());
                int filasInsertadas = prepararSentencia.executeUpdate();
                if (filasInsertadas > 0) {
                    ResultSet generatedKeys = prepararSentencia.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int idColaboracion = generatedKeys.getInt(1);
                        respuesta.put("idColaboracion", idColaboracion);
                    }
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "La colaboración se ha guardado exitosamente");
                } else {
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "No se pudo insertar la colaboración.");
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
    
    
    public static HashMap<String, Object> guardarColaboracionUv(Colaboracion colaboracion, 
            int idArchivo) {
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if (conexionBD != null) {
            try {
                String consulta = 
                    "INSERT INTO colaboracion (nombre, idExperienciaEducativa, idEstadoColaboracion, "
                        + "idProfesoruv, fechaInicio, fechaFin, descripcion, idArchivo, idIdioma, "
                        + "idProfesorexterno) " 
                        +  "VALUES (?, ?, 1, ?, ?, ?, ?, ?, ?, "
                        + "(SELECT idProfesorExterno FROM profesorexterno "
                        + "ORDER BY idProfesorExterno DESC LIMIT 1));";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement
                    (consulta, Statement.RETURN_GENERATED_KEYS);
                prepararSentencia.setString(1, colaboracion.getNombre());
                prepararSentencia.setInt(2, colaboracion.getIdExperienciaEducativa());
                prepararSentencia.setInt(3, colaboracion.getIdProfesorUV());
                prepararSentencia.setString(4, colaboracion.getFechaInicio());
                prepararSentencia.setString(5, colaboracion.getFechaFin());
                prepararSentencia.setString(6, colaboracion.getDescripcion());
                prepararSentencia.setInt(7, idArchivo);
                prepararSentencia.setInt(8, colaboracion.getIdIdioma());
                int filasInsertadas = prepararSentencia.executeUpdate();
                if (filasInsertadas > 0) {
                    ResultSet generatedKeys = prepararSentencia.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int idColaboracion = generatedKeys.getInt(1);
                        respuesta.put("idColaboracion", idColaboracion);
                    }
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "La colaboración se ha guardado exitosamente");
                } else {
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "No se pudo insertar la colaboración.");
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
    
    
    public static HashMap<String, Object> registrarColaboracionEstudiante(int idColaboracion, 
            int idEstudiante) {
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if (conexionBD != null) {
            try {
                String consulta = 
                "INSERT INTO colaboracion_estudiante (idColaboracion, idEstudiante) VALUES (?, ?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idColaboracion);
                prepararSentencia.setInt(2, idEstudiante);
                int filasInsertadas = prepararSentencia.executeUpdate();
                if (filasInsertadas > 0) {
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, 
                        "El registro de alumnos en colaboracion se ha guardado exitosamente");
                }else {
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "No se pudo guardar el alumno en la colaboracion.");
                }
                conexionBD.close();
            } catch (SQLException e) {
               respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        }else {
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }

    
    public static HashMap<String, Object> obtenerColaboraciones(){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try {
                String consulta = 
                "SELECT c.idColaboracion, c.idEstadoColaboracion, c.nombre, c.fechaInicio, "
              + "c.fechaFin, c.justificacion, per.fechaInicio AS inicio, per.fechaFin AS final, "
              + "ar.nombre AS nombreArchivo, ar.archivocol AS archivo, "
              + "CONCAT(puv.nombre, ' ', puv.apellidos) AS ProfesorUV, "
              + "CONCAT(pe.nombre, ' ', pe.apellidos) AS ProfesorExterno, "
              + "ee.nombre AS ExperienciaEducativa, COUNT(ce.idEstudiante) AS NumeroEstudiantes "
              + "FROM colaboracion c "
              + "JOIN profesoruv puv ON c.idProfesoruv = puv.idProfesoruv "
              + "JOIN experienciaeducativa ee "
              + "ON c.idExperienciaEducativa = ee.idExperienciaEducativa "
              + "JOIN periodo per ON ee.idPeriodo = per.idPeriodo "
              + "JOIN profesorexterno pe ON c.idProfesorexterno = pe.idProfesorexterno "
              + "LEFT JOIN archivo ar ON c.idArchivo = ar.idArchivo "
              + "LEFT JOIN colaboracion_estudiante ce ON c.idColaboracion = ce.idColaboracion "
              + "GROUP BY c.idColaboracion, c.nombre, c.fechaInicio, c.fechaFin, c.justificacion, "
              + "ar.nombre, ar.archivocol, puv.nombre, puv.apellidos, pe.nombre, "
              + "pe.apellidos, ee.nombre";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                List<Colaboracion> colaboraciones = new ArrayList<>();
                
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter outputFormatter = 
                        DateTimeFormatter.ofPattern("dd-MM-yyyy");
                
                while(resultado.next()){
                    Colaboracion colaboracion = new Colaboracion();
                    colaboracion.setIdColaboracion
                        (resultado.getInt("idColaboracion"));
                    colaboracion.setIdEstadoColaboracion
                        (resultado.getInt("idEstadoColaboracion"));
                    colaboracion.setNombre(resultado.getString("nombre"));
                    colaboracion.setProfesorExterno
                        (resultado.getString("ProfesorExterno"));
                    colaboracion.setExperienciaEducativa
                        (resultado.getString("ExperienciaEducativa"));
                    colaboracion.setProfesorUV
                        (resultado.getString("ProfesorUV"));
                    
                    String fechaInicioOfertaBD = resultado.getString("inicio");
                    if (fechaInicioOfertaBD != null) {
                        LocalDate fechaInicioOferta = 
                            LocalDate.parse(fechaInicioOfertaBD, inputFormatter);
                            colaboracion.setFechaInicioOferta
                                (fechaInicioOferta.format(outputFormatter));
                    } else {
                        colaboracion.setFechaInicioOferta(null); 
                    }
                    
                    String fechaInicioColBD = resultado.getString("fechaInicio");
                    if (fechaInicioColBD != null) {
                        LocalDate fechaInicio = 
                            LocalDate.parse(fechaInicioColBD, inputFormatter);
                        colaboracion.setFechaInicio
                                (fechaInicio.format(outputFormatter));
                    } else {
                        colaboracion.setFechaInicio(null); 
                    }
                    
                    String fechaFinOfertaBD = resultado.getString("final");
                    if (fechaFinOfertaBD != null) {
                        LocalDate fechaFinOferta = 
                            LocalDate.parse(fechaFinOfertaBD, inputFormatter);
                        colaboracion.setFechaFinOferta
                                (fechaFinOferta.format(outputFormatter));
                    } else {
                        colaboracion.setFechaFinOferta(null); 
                    }
                    
                    String fechaFinBD = resultado.getString("fechaFin");
                    if (fechaFinBD != null) {
                        LocalDate fechaFinOferta = 
                            LocalDate.parse(fechaFinBD, inputFormatter);
                        colaboracion.setFechaFin
                                (fechaFinOferta.format(outputFormatter));
                    } else {
                        colaboracion.setFechaFin(null); 
                    }
                   
                    colaboracion.setJustificacion
                        (resultado.getString("justificacion"));
                    colaboracion.setNumeroEstudiantes
                        (resultado.getString("NumeroEstudiantes"));
                    colaboracion.setNombreEvidencia
                        (resultado.getString("nombreArchivo"));
                    colaboracion.setEvidencia
                        (resultado.getBytes("archivo"));
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
}

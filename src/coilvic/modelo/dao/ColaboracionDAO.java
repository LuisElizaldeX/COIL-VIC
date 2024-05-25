/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.Colaboracion;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author zS21022065
 */
public class ColaboracionDAO {
    public static HashMap<String, Object> guardarColaboracion(Colaboracion colaboracion){
    HashMap<String, Object> respuesta = new HashMap<>();
    respuesta.put(Constantes.KEY_ERROR, true);
    Connection conexionBD = ConexionBD.obtenerConexion();
    if(conexionBD != null){
        try {
            String sentencia = "INSERT INTO colaboracion (nombre, "
                    + "idProfesor, idEstudiante, fechaInicio, fechaFin)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
            prepararSentencia.setString(1, colaboracion.getNombre());
            prepararSentencia.setString(2, colaboracion.getDescripcion());
            prepararSentencia.setInt(3, colaboracion.getIdProfesor());
            prepararSentencia.setInt(4, colaboracion.getIdEstudiante());
            prepararSentencia.setDate(5, (Date) colaboracion.getFechaInicio());
            prepararSentencia.setDate(6, (Date) colaboracion.getFechaFin());
            int filasAfectadas = prepararSentencia.executeUpdate();
            if (filasAfectadas > 0) {
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put(Constantes.KEY_MENSAJE, "Colaboración guardada correctamente");
            } else {
                respuesta.put(Constantes.KEY_MENSAJE, "Hubo un error al guardar la colaboración");
            }
            conexionBD.close();
        } catch (SQLException ex) {
            respuesta.put(Constantes.KEY_MENSAJE, ex.getMessage());
        }
    } else {
        respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
    }
    return respuesta;
}

    
}

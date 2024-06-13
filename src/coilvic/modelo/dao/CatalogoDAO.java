/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 12/06/2024
* Descripción: Clase CatalogoDAO para obtener informacion de las clases
*/

package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.Idioma;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CatalogoDAO {
    
    public static HashMap<String, Object> obtenerIdiomas(){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try {
                String consulta = "SELECT idIdioma, lengua FROM idioma";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList<Idioma> idiomas = new ArrayList<>();
                while(resultado.next()){
                    Idioma idioma = new Idioma();
                    idioma.setIdIdioma(resultado.getInt("idIdioma"));
                    idioma.setLengua(resultado.getString("lengua"));
                    idiomas.add(idioma);
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("idiomas", idiomas);
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

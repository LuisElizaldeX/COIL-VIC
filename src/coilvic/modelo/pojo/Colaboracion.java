/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coilvic.modelo.pojo;

/**
 *
 * @author zS21022065
 */


import java.sql.Date;

public class Colaboracion {
    private Integer idColaboracion;
    private String nombre;
    private String descripcion;
    private Integer idProfesor;
    private Integer idEstudiante;
    private Date fechaInicio;
    private Date fechaFin;

    public Colaboracion() {
    }

    public Colaboracion(Integer idColaboracion, String nombre, String descripcion, Integer idProfesor, Integer idEstudiante, Date fechaInicio, Date fechaFin) {
        this.idColaboracion = idColaboracion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idProfesor = idProfesor;
        this.idEstudiante = idEstudiante;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getIdColaboracion() {
        return idColaboracion;
    }

    public void setIdColaboracion(Integer idColaboracion) {
        this.idColaboracion = idColaboracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}


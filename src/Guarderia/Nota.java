
package Guarderia;

import java.time.LocalDate;

public class Nota 
{
    int cod_nota;
    int numeroMatricula;
    LocalDate fecha;
    String descripcionNota;
    String actividad;

    
    int calificacion;

    public Nota()
    {}
    
  

    public Nota(int cod_matricula,LocalDate fecha,String actividad,String descripcionNota,int calificacion)
    {
       
        this.numeroMatricula = cod_matricula;
        this.fecha = fecha;
        this.actividad = actividad;
        this.descripcionNota = descripcionNota;
        this.calificacion =  calificacion;
    }
    public Nota(int cod_nota,int cod_matricula,LocalDate fecha,String actividad,String descripcionNota,int calificacion)
    {
        this.cod_nota = cod_nota;
        this.numeroMatricula = cod_matricula;
        this.fecha = fecha;
        this.actividad = actividad;
        this.descripcionNota = descripcionNota;
        this.calificacion =  calificacion;
    }
    public int getCod_nota() {
        return cod_nota;
    }

    public void setCod_nota(int cod_nota) {
        this.cod_nota = cod_nota;
    }
     public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcionNota() {
        return descripcionNota;
    }

    public void setDescripcionNota(String descripcionNota) {
        this.descripcionNota = descripcionNota;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }
    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}


package Guarderia;

import java.time.LocalDate;


public class Mensualidad 
{

    private int cod_mensualidad;
    private int numeroMatricula;
    private LocalDate fecha;

    
    private String estado;
    private int valorMensualidad;
    
    public Mensualidad( int numeroMatricula, LocalDate fecha,String estado, int valorMensualidad)
    {
        
        this.numeroMatricula = numeroMatricula;
        this.fecha = fecha;
        this.estado = estado;
        this.valorMensualidad = valorMensualidad;
    }
    public Mensualidad(int cod_mensualidad, int numeroMatricula, LocalDate fecha,String estado, int valorMensualidad)
    {
        this.cod_mensualidad = cod_mensualidad;
        this.numeroMatricula = numeroMatricula;
        this.fecha = fecha;
        this.estado = estado;
        this.valorMensualidad = valorMensualidad;
    }

    
    public int getCod_mensualidad() {
        return cod_mensualidad;
    }

    public void setCod_mensualidad(int cod_mensualidad) {
        this.cod_mensualidad = cod_mensualidad;
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
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getValorMensualidad() {
        return valorMensualidad;
    }

    public void setValorMensualidad(int valorMensualidad) {
        this.valorMensualidad = valorMensualidad;
    }
}

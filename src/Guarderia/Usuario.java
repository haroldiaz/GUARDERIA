
package Guarderia;

import java.time.LocalDate;


public class Usuario 
{

    
    int codigo;
    String correo;
    String contra;
    LocalDate fechaIngreso;
    String nombreCompleto;
    String tipoUsuario;

    public Usuario()
    {}
    
    public Usuario(int codigo, String correo, String contra, LocalDate fechaIngreso, String nombreCompleto, String tipoUsuario) {
        this.codigo = codigo;
        this.correo = correo;
        this.contra = contra;
        this.fechaIngreso = fechaIngreso;
        this.nombreCompleto = nombreCompleto;
        this.tipoUsuario = tipoUsuario;
    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    
}


package Guarderia;

import java.time.LocalDate;


public class Matricula 
{

    
    int numeroMatricula;
    String nombre;
    String apellido;
    int numeroRegistroCivil;
    String tipoSangre;
    String Sexo;
    LocalDate fechaNacimiento;
    LocalDate fechaIngreso;
    String grado;
    
    //String personaResponsable;
 
    public Matricula(int numeroMatricula, String nombre, String apellido, int numeroRegistroCivil, String tipoSangre, String Sexo, LocalDate fechaNacimiento, LocalDate fechaIngreso, String grado) {
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroRegistroCivil = numeroRegistroCivil;
        this.tipoSangre = tipoSangre;
        this.Sexo = Sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.grado = grado;
    }

   public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNumeroRegistroCivil() {
        return numeroRegistroCivil;
    }

    public void setNumeroRegistroCivil(int numeroRegistroCivil) {
        this.numeroRegistroCivil = numeroRegistroCivil;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }
    
}

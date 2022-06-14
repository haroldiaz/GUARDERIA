
package Guarderia;


public class PadreFamilia
{

   private int documento;
   private int numeroMatricula;
   private String nombre;
   private String direccion;
   private String telefono;
   private String parentesco;
   
   public PadreFamilia(int documento, int numeroMatricula, String nombre, String direccion, String numeroTelefono, String parentezco) {
        this.documento = documento;
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = numeroTelefono;
        this.parentesco = parentezco;
    }
   
    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

}

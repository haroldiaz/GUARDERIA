
package Guarderia;


public class Docente 
{
    private int documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String estudios;
    private String gradoAsignado;
    
    public Docente()
    {}

    public Docente(int documento,String _nombre, String _apellido,String _telefono, String correo, String estudios, String gradoAsignado) 
    {
        //super(nombre, apellido);
        this.documento = documento;
        this.nombre = _nombre;
        this.apellido = _apellido;
        this.telefono = _telefono;
        this.correo = correo;
        this.estudios = estudios;
        this.gradoAsignado = gradoAsignado;
    }
   

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }
    
    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre =nombre;
    }
    public String getApellido()
     {
        return this.apellido;
    }

    public void setApellido(String apellido) 
    {
        this.apellido =apellido;
    }
    
   public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public String getGradoAsignado() {
        return gradoAsignado;
    }

    public void setGradoAsignado(String gradoAsignado) {
        this.gradoAsignado = gradoAsignado;
    }


    
    
}

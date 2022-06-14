package BaseDatos;

import Guarderia.Docente;
import Guarderia.Matricula;
import Guarderia.Mensualidad;
import Guarderia.Nota;
import Guarderia.PadreFamilia;
import Guarderia.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BaseDatos {

    String BD = "jdbc:postgresql://localhost:5432/proyecto_prueba";
    String usuario = "postgres";
    String pass = "12345";

    Connection conectar = null;

    Statement instruccion = null;
    ResultSet resultados = null;

    public BaseDatos()
    {
       // String usuario = "postgres";
       // String pass = "12345";
    }
    public BaseDatos(String usuario,String pass)
    {
        this.usuario = usuario;
        this.pass = pass;
    }

    public void conectar() {
        try {
            conectar = DriverManager.getConnection(BD, usuario, pass);

            System.out.println(" base de datos conectada ");

        } catch (Exception e) {
            System.out.println(" error al conceta " + e);

        }

    }

    
    public List<Matricula> SELECT_MATRICULAS()
    {
        List<Matricula> lista = new ArrayList<>();
        try {
            instruccion = conectar.createStatement();

            String query = "SELECT * FROM  MATRICULA";
            resultados = instruccion.executeQuery(query);

           
            while (resultados.next()) {

                String fecha = resultados.getDate("fechaNacimiento") + "";
                LocalDate fechaNacimiento = LocalDate.parse(fecha);

                String fecha2 = resultados.getDate("fechaIngresoGuarderia") + "";
                LocalDate fechaIngresoGuarderia = LocalDate.parse(fecha2);

                Matricula n = new Matricula(resultados.getInt("numeroMatricula"),
                        resultados.getString("nombre"),
                        resultados.getString("apellido"),
                        resultados.getInt("numeroRegistroCivil"),
                        resultados.getString("tipoSangre"),
                        resultados.getString("sexo"),
                        fechaNacimiento,
                        fechaIngresoGuarderia,
                        resultados.getString("Grado")
                );

                lista.add(n);
            }
            
            instruccion.close();
            return lista;

        } catch (SQLException e) {
            System.out.println("ERROR SELECT " + e);
        }

        return lista;
    }

    public List<Nota> SELECT_NOTAS() {
        List<Nota> lista = new ArrayList<>();
        try {
            instruccion = conectar.createStatement();

            String query = "SELECT * FROM  NOTAS";
            resultados = instruccion.executeQuery(query);

            while (resultados.next()) {

                String fecha = resultados.getDate("fecha") + "";
                LocalDate date = LocalDate.parse(fecha);
                Nota n = new Nota(resultados.getInt("cod_nota"),resultados.getInt("cod_matricula"), date, resultados.getString("actividad"),
                        resultados.getString("descripcion"),resultados.getInt("calificacion"));

                lista.add(n);
            }

            return lista;

        } catch (SQLException e) {
            System.out.println("ERROR SELECT " + e);
        }

        return lista;
    }

    public List<Docente> SELECT_DOCENTES() {
        List<Docente> lista = new ArrayList<>();
        try {
            instruccion = conectar.createStatement();

            String query = "SELECT * FROM  DOCENTES";
            resultados = instruccion.executeQuery(query);

            while (resultados.next()) {

                Docente n = new Docente(resultados.getInt("Documento"),
                        resultados.getString("nombre"),
                        resultados.getString("apellido"),
                        resultados.getString("telefono"),
                        resultados.getString("correo"),
                        resultados.getString("Estudios"),
                        resultados.getString("gradoAsignado")
                );

                lista.add(n);
            }

            return lista;

        } catch (SQLException e) {
            System.out.println("ERROR SELECT " + e);
        }

        return lista;
    }

    public List<Usuario> SELECT_USUARIO() {
        List<Usuario> lista = new ArrayList<>();
        try {
            instruccion = conectar.createStatement();

            String query = "SELECT * FROM  USUARIOS";
            resultados = instruccion.executeQuery(query);

           
            while (resultados.next()) {
                String fecha2 = resultados.getDate("fechaIngreso") + "";
                LocalDate fechaIngreso = LocalDate.parse(fecha2);

                Usuario n = new Usuario(resultados.getInt("codigousuario"),
                        resultados.getString("correo"),
                        resultados.getString("contra"),
                        fechaIngreso,
                        resultados.getString("nombreCompleto"),
                        resultados.getString("tipoUsuario")
                );

                lista.add(n);
            }

            return lista;

        } catch (SQLException e) {
            System.out.println("ERROR SELECT " + e);
        }

        return lista;
    }

    public List<PadreFamilia> SELECT_PADREFAMILIA()
    {
        List<PadreFamilia> lista = new ArrayList<>();
        try {
            instruccion = conectar.createStatement();

            String query = "SELECT * FROM  PERSONARESPONSABLE";
            resultados = instruccion.executeQuery(query);

           
            while (resultados.next())
            {
               
                PadreFamilia n = new PadreFamilia(resultados.getInt("documento"),
                        resultados.getInt("numeroMatricula"),
                        resultados.getString("nombre"),
                        resultados.getString("direccion"),
                        resultados.getString("telefono"),
                        resultados.getString("parentesco")
                );

                lista.add(n);
            }

            return lista;

        } catch (SQLException e) {
            System.out.println("ERROR SELECT " + e);
        }

        return lista;
    }
    
    public List<Mensualidad> SELECT_MENSUALIDAD()
    {
        List<Mensualidad> lista = new ArrayList<>();
        try {
            instruccion = conectar.createStatement();

            String query = "SELECT * FROM  MENSUALIDAD";
            resultados = instruccion.executeQuery(query);

           
            while (resultados.next())
            {
               String fecha2 = resultados.getDate("fechaPago") + "";
               LocalDate fechaPago = LocalDate.parse(fecha2);
                
                
                Mensualidad n = new Mensualidad(resultados.getInt("Cod_mensualidad"),
                                                resultados.getInt("numeroMatricula"),
                                                fechaPago,
                                                resultados.getString("estado"),
                                                resultados.getInt("valorMensualidad"));

                lista.add(n);
            }

            return lista;

        } catch (SQLException e) {
            System.out.println("ERROR SELECT " + e);
        }

        return lista;
    }
    
    public void EJECUTE_QUERY(String query) 
    {
        try 
        {
            instruccion = conectar.createStatement();
            resultados = instruccion.executeQuery(query);
         
           
            System.out.println("--SE EJECUTO- CORRECTAMENTE-");

        } catch (SQLException e)
        {
            System.out.println("ERROR EJECUTE_QUERY -> " + e);
        }

    }

}

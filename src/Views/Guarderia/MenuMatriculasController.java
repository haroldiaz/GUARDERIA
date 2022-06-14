package Views.Guarderia;

import BaseDatos.BaseDatos;
import Guarderia.Matricula;
import Principal.Main;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class MenuMatriculasController implements Initializable {

    Main ventana;
    @FXML
    private TableView<Matricula> tabla;

    @FXML
    private TableColumn numeroMatricula;
    @FXML
    private TableColumn nombres;
    @FXML
    private TableColumn apellido;
    @FXML
    private TableColumn numeroRegistro;
    @FXML
    private TableColumn tipoSangre;
    @FXML
    private TableColumn tipoSexo;
    
    @FXML
    private TableColumn fechaNacimiento;
    @FXML
    private TableColumn fechaIngreso;
    @FXML
    private TableColumn grado;
   
    
    
    @FXML
    private JFXTextField campo_numeroMatricula;
    @FXML
    private JFXTextField campo_nombre;
    @FXML
    private JFXTextField campo_apellido;
    @FXML
    private JFXTextField campo_registroCivil;
    @FXML
    private JFXDatePicker campo_fechaNacimiento;
    @FXML
    private JFXDatePicker campo_ingresoGuarderia;
    
    @FXML
    private JFXComboBox<String> campo_sexo;
    @FXML
    private JFXComboBox<String> campo_tipoSangre;
    @FXML
    private JFXComboBox<String> campo_grado;

    
    ObservableList<Matricula> lista = FXCollections.observableArrayList();
    List<Matricula> listaMatriculas = new ArrayList<>();
    BaseDatos bd = new BaseDatos();
    
    //------------------PADRE DE FAMILIA------------
    @FXML
    private JFXTextField campo_documento;
    @FXML
    private JFXTextField campo_nombrepadre;
    @FXML
    private JFXTextField campo_direccion;
    @FXML
    private JFXTextField campo_telefono;
    @FXML
    private JFXTextField campo_parentesco;
    
    //------------------CAMPO USUARIO-------------------
    @FXML
    private JFXTextField campo_correo;
    @FXML
    private JFXTextField campo_contra;
    @FXML
    private JFXDatePicker campo_ingreso;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //conectar a base de datoss
        bd.conectar();
       //agregar columnas a la tabla
       agregarColum();
       agregarElementosTabla();
       llenarCampos();

    }

    public void setVentanaPrincipal(Main ventana) {
        this.ventana = ventana;
    }

    void agregarColum() {

        numeroMatricula.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Matricula, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Matricula, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNumeroMatricula());
            }
        });
        nombres.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Matricula, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Matricula, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNombre());
            }
        });
        apellido.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Matricula, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Matricula, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getApellido());
            }
        });

        numeroRegistro.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Matricula, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Matricula, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNumeroRegistroCivil());
            }
        });
        tipoSangre.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Matricula, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Matricula, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTipoSangre());
            }
        });
        tipoSexo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Matricula, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Matricula, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getSexo());
            }
        });
        fechaNacimiento.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Matricula, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Matricula, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFechaNacimiento());
            }
        });
        fechaIngreso.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Matricula, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Matricula, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFechaIngreso());
            }
        });
        grado.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Matricula, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Matricula, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getGrado());
            }
        });
        
        tabla.setItems(lista);
    }
    
    void agregarElementosTabla()
    {
        
        listaMatriculas = bd.SELECT_MATRICULAS();
        
        for (int i = 0; i < listaMatriculas.size(); i++) 
        {
           lista.add(listaMatriculas.get(i));
        }
        
        
    }
    
    void llenarCampos()
    {
     
     campo_tipoSangre.getItems().add("A+");
     campo_tipoSangre.getItems().add("A-");
     campo_tipoSangre.getItems().add("O+");
     campo_tipoSangre.getItems().add("O-");
     campo_tipoSangre.getItems().add("B+");
     campo_tipoSangre.getItems().add("B-");
     campo_tipoSangre.getItems().add("AB+");
     campo_tipoSangre.getItems().add("AB-");
    
     //seleccionar siempre el rector
    //campo_tipoSangre.setValue("A+");
    
    //mostrar un elemento seleccionado
     System.out.println(campo_tipoSangre.getValue());
     
    campo_sexo.getItems().add("MASCULINO");
    campo_sexo.getItems().add("FEMENINO");
    
    
    campo_grado.getItems().add("Iniciacion");
    campo_grado.getItems().add("Parvulos");
    campo_grado.getItems().add("Prejardin");
    campo_grado.getItems().add("Jardin");
    campo_grado.getItems().add("Transicion");
       
    }
    
    @FXML
    public void ver()
    {
        
        //poner en la clase 
        if (!tabla.getSelectionModel().isEmpty())
        {
           

            String numeroMatricula = tabla.getSelectionModel().getSelectedItems().get(0).getNumeroMatricula()+ "";
            campo_numeroMatricula.setText(numeroMatricula);

            String nombre = tabla.getSelectionModel().getSelectedItems().get(0).getNombre();
            campo_nombre.setText(nombre);
            
            String apellido = tabla.getSelectionModel().getSelectedItems().get(0).getApellido();
            campo_apellido.setText(apellido);
            
            String registrocivil = tabla.getSelectionModel().getSelectedItems().get(0).getNumeroRegistroCivil() + "";
            campo_registroCivil.setText(registrocivil);
            
            String tiposangre = tabla.getSelectionModel().getSelectedItems().get(0).getTipoSangre();
            campo_tipoSangre.setValue(tiposangre);
            
            String sexo = tabla.getSelectionModel().getSelectedItems().get(0).getSexo();
            campo_sexo.setValue(sexo);
            
            LocalDate fechanacimiento = tabla.getSelectionModel().getSelectedItems().get(0).getFechaNacimiento();
            campo_fechaNacimiento.setValue(fechanacimiento);
            
            LocalDate fechaingreso = tabla.getSelectionModel().getSelectedItems().get(0).getFechaIngreso();
            campo_ingresoGuarderia.setValue(fechaingreso);
            
            String grado = tabla.getSelectionModel().getSelectedItems().get(0).getGrado();
            campo_grado.setValue(grado);
            
            
        }
        
        
    }

    @FXML
    public void INSERT()
    {
        
        String numeroMatricula = campo_numeroMatricula.getText();
       
        
        String nombre    = campo_nombre.getText();
        String apellido  = campo_apellido.getText();
        String registroCivil  = campo_registroCivil.getText();
    
        String tipoSangre    = campo_tipoSangre.getValue();
        String sexo  = campo_sexo.getValue();
        
        String fechaNacimiento = campo_fechaNacimiento.getValue()+"";
        LocalDate datefechanacimiento = campo_fechaNacimiento.getValue();
      
        //String fechaIngresoGuarderia = campo_fechaIngresoGuarderia.getText();
        LocalDate dateingreso = campo_ingresoGuarderia.getValue();
        
        String grado = campo_grado.getValue();
        
        //----------PADRE FAMILIA--------------------
        String documentos    = campo_documento.getText();
        String nombres    = campo_nombrepadre.getText();
        String direccion = campo_direccion.getText();
        String telefono  = campo_telefono.getText();
        String parentesco = campo_parentesco.getText();
        
        
        //-------------USUARIO-----------
        String correo    = campo_correo.getText();
        String contra = campo_contra.getText();
        LocalDate fecha = campo_ingreso.getValue();
        
        if(datosMatricula() || datosPadrefamilia() || datosUsuario())
        {
            mensaje("ERROR FALTAN DATOS", "LLENE TODOS LOS CAMPOS");
        
        }
        else
        {
            int matricula = Integer.parseInt(numeroMatricula);
            int numeroregistro = Integer.parseInt(registroCivil);
            int cont = 0;
            for (int i = 0; i < lista.size(); i++)
            {
                
                Matricula get = lista.get(i);
                if (get.getNumeroMatricula()== matricula) cont++;
                
                
            }
            if (cont == 0)
            {
                    int matriculas = Integer.parseInt(numeroMatricula);
                    //-------------MATRICULA-----------
                    String sql = 
                    " INSERT INTO MATRICULA(NumeroMatricula,Nombre,Apellido,numeroRegistroCivil,tipoSangre,sexo,fechaNacimiento,FechaIngresoGuarderia,Grado)"
                    + " VALUES ("+ matriculas+",'"+ nombre+"','"+ apellido+"', "+ numeroMatricula+",'"+ tipoSangre+"','"+ sexo+"','"+ datefechanacimiento+"','"+dateingreso+"','"+grado+"');";
                     System.out.println(sql);

                    bd.EJECUTE_QUERY(sql);
                    Matricula n = new Matricula(matricula,nombre,apellido,numeroregistro,tipoSangre,sexo,datefechanacimiento,dateingreso,grado);
                    lista.add(n);
                    
                    //-----------PADRE FAMILIA----------
                    int documento = Integer.parseInt(documentos);
                    

                    String query = "INSERT INTO personaResponsable(documento,numeroMatricula,nombre,direccion,Telefono,parentesco)\n" +
                                    "VALUES("+documento+","+matricula+",'"+nombres+"','"+direccion+"','"+telefono+"','"+parentesco+"'); ";

                    System.out.println(query);
                    bd.EJECUTE_QUERY(query);
                    
                    
                    //-----------USUARIO------------
                    String user = "INSERT INTO USUARIOS(codigoUsuario,correo,contra,fechaIngreso,nombreCompleto,tipoUsuario)"
                                    +" VALUES("+documento+",'"+correo+"','"+contra+"','"+fecha+"','"+nombres+"','PADREFAMILIA');";
                    System.out.println(user);
                    bd.EJECUTE_QUERY(user);

                    mensaje("COMPLETADO", "SE HA REGISTRADO LA MATRICULA");
                }
                else
                {
                     mensaje("ERROR", "EL NUMERO DE MATRICULA ESTA REGISTRADO");
                }
            
        }
        
        
    }
    
    boolean datosMatricula()
    {
        String numeroMatricula = campo_numeroMatricula.getText();
        String nombre    = campo_nombre.getText();
        String apellido  = campo_apellido.getText();
        String registroCivil  = campo_registroCivil.getText();
        String tipoSangre    = campo_tipoSangre.getValue();
        String sexo  = campo_sexo.getValue();
        String fechaNacimiento = campo_fechaNacimiento.getValue()+"";
        LocalDate datefechanacimiento = campo_fechaNacimiento.getValue();
        LocalDate dateingreso = campo_ingresoGuarderia.getValue();
        String grado = campo_grado.getValue();
        
        if(numeroMatricula.isEmpty() || nombre.isEmpty()  || apellido.isEmpty()|| 
           registroCivil.isEmpty() || tipoSangre.isEmpty() || sexo.isEmpty() || fechaNacimiento.isEmpty()||
           datefechanacimiento == null || dateingreso == null || grado.isEmpty()
                )
        {
            return true;
        }
        
    return false;
    }
    
    boolean datosPadrefamilia()
    {
   
        String documento    = campo_documento.getText();
        String nombre    = campo_nombrepadre.getText();
        String direccion = campo_direccion.getText();
        String telefono  = campo_telefono.getText();
        String parentesco = campo_parentesco.getText();
        
        if(documento.isEmpty() || nombre.isEmpty() ||  direccion.isEmpty() || 
                telefono.isEmpty() || parentesco.isEmpty()
                )
        {
        return true;
        } 
        
        return false;
    }
    
    boolean datosUsuario()
    {
    
        String correo    = campo_correo.getText();
        String contra = campo_contra.getText();
        LocalDate fecha = campo_ingreso.getValue();
        if (correo.isEmpty() || contra.isEmpty() || fecha == null)
        {
         return true;
        }
        
        return false;
    }
    
    @FXML
    public void UPDATE()
    {
       
       String numeroMatricula = campo_numeroMatricula.getText();
       
        
        String nombre    = campo_nombre.getText();
        String apellido  = campo_apellido.getText();
        String registroCivil  = campo_registroCivil.getText();
        
        
        String tipoSangre    = campo_tipoSangre.getValue();
        String sexo  = campo_sexo.getValue();
        
        String fechaNacimiento = campo_fechaNacimiento.getValue()+"";
        LocalDate datefechanacimiento = campo_fechaNacimiento.getValue();
      
        //String fechaIngresoGuarderia = campo_fechaIngresoGuarderia.getText();
        LocalDate dateingreso = campo_ingresoGuarderia.getValue();
        
        String grado = campo_grado.getValue();
        
   
        
         if(numeroMatricula.isEmpty() || nombre.isEmpty()  || apellido.isEmpty()|| 
           registroCivil.isEmpty())
        {
            mensaje("ERROR FALTAN DATOS", "LLENE TODOS LOS CAMPOS");
        
        }
        else
        {
        
        if (!tabla.getSelectionModel().isEmpty()) 
        {
            int codigo =(int) tabla.getSelectionModel().getSelectedItem().getNumeroMatricula(); 
            System.out.println(codigo);
           
            int numeroregistro = Integer.parseInt(registroCivil);
            
            //VALIDAR SI NO ESTAN VACIOS
            lista.set(tabla.getSelectionModel().getSelectedIndex(), 
            new Matricula(codigo,nombre,apellido,numeroregistro,tipoSangre,sexo,datefechanacimiento,dateingreso,grado));
            
            String SET   = "NOMBRE = " +"'"+ nombre+"',"
                          +"APELLIDO = " +"'"+ apellido+"',"
                          +"NUMEROREGISTROCIVIL = " +"'"+ numeroregistro+"',"
                          +"TIPOSANGRE = " +"'"+ tipoSangre+"',"
                          +"SEXO = " +"'"+ sexo+"',"
                          +"FECHANACIMIENTO = " +"'"+ datefechanacimiento+"',"
                          +"FECHAINGRESOGUARDERIA = " +"'"+ dateingreso+"',"
                          +"GRADO = " +"'"+ grado+"'";

            //COMO CAMBIAR EL VALOR DE UNA LLABVE PRIMARIA
                
                
                String WHERE = "NUMEROMATRICULA = " + codigo;
                String query = "UPDATE " +"MATRICULA"+" SET "+ SET +" WHERE "+ WHERE;
                System.out.println(query);
               
                
                bd.EJECUTE_QUERY(query);
                mensaje("ACTUALIZACION CORRECTA", "SE ACTUALIZARON LOS DATOS CORRECTAMENTE");
            
            
            }else{
        
                mensaje("ERROR", "NO SELECCIONO NINGUNA MATRICULA");
            }
        
        }
        
        
       
        
    }
    
    
    @FXML
    public void DELETE()
    {
        
        
        if (!tabla.getSelectionModel().isEmpty()) 
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("ELIMINAR DOCENTE");

            ImageView img = new ImageView("Imagenes/Campos/delete-circle.png");
            img.setFitHeight(90f);
            img.setFitWidth(90f);
            alert.setGraphic(img);
            alert.setTitle("Eliminar Docente");
            alert.setContentText("¿Deseas realmente Eliminar el Docente?");
            //alert.showAndWait();
            Optional<ButtonType> action = alert.showAndWait();
        
            if (action.get() == ButtonType.OK) 
            {  
            
            int codigo =(int) tabla.getSelectionModel().getSelectedItem().getNumeroMatricula();
            System.out.println(codigo);
            String sql ="DELETE FROM MATRICULA  WHERE NUMEROMATRICULA = "+ codigo + ";";
            System.out.println(sql);
            bd.EJECUTE_QUERY(sql);
            
            lista.remove(tabla.getSelectionModel().getSelectedItem());
             mensaje("EXITO", "SE ELIMINO LA MATRICULA");
            }
        }
        else
        {
            mensaje("ERROR", "NO SELECCIONO NINGUNA MATRICULA");
        }
        
    
    }
    
    
    void mensaje(String titulo, String info) 
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(info);
        alert.showAndWait();
    }
    
    
    
    @FXML
    public void limpiar()
    {
      
       campo_numeroMatricula.setText("");
       campo_nombre.setText("");
       campo_apellido.setText("");
       campo_registroCivil.setText("");
       campo_tipoSangre.setValue("");
       campo_sexo.setValue("");
       
       campo_fechaNacimiento.setValue(LocalDate.now());
       campo_ingresoGuarderia.setValue(LocalDate.now());
       campo_grado.setValue("");
    }

}

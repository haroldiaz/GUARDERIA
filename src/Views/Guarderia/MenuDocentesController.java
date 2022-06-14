package Views.Guarderia;

import BaseDatos.BaseDatos;
import Guarderia.Docente;
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

public class MenuDocentesController implements Initializable {

    @FXML
    private TableView<Docente> tabla;
    
    @FXML
    private TableColumn documento;
    @FXML
    private TableColumn nombre;
    @FXML
    private TableColumn apellido;
    @FXML
    private TableColumn telefono;
    @FXML
    private TableColumn correo;
    @FXML
    private TableColumn estudios;
    @FXML
    private TableColumn grados;
    
    
    @FXML
    private JFXTextField campo_documento;
    @FXML
    private JFXTextField campo_nombre;
    @FXML
    private JFXTextField campo_apellido;
    @FXML
    private JFXTextField campo_telefono;
    @FXML
    private JFXTextField campo_estudios;
    @FXML
    private JFXComboBox<String> campo_grado;
    
    
    //--------------USUARIO------------
    @FXML
    private JFXTextField campo_correo;
    @FXML
    private JFXTextField  campo_contra;
    @FXML
    private JFXDatePicker campo_fecha;
    
    
    ObservableList<Docente> lista = FXCollections.observableArrayList();
    List<Docente> listaNotas = new ArrayList<>();
    
    BaseDatos bd = new BaseDatos();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        bd.conectar();
        insertarColum();
        agregarElementosTabla();
    }

    void insertarColum()
    {
        

        documento.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Docente, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Docente, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDocumento());
            }
        });
        nombre.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Docente, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Docente, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNombre());
            }
        });
        apellido.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Docente, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Docente, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getApellido());
            }
        });
        telefono.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Docente, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Docente, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTelefono());
            }
        });
        
        correo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Docente, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Docente, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCorreo());
            }
        });
        estudios.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Docente, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Docente, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getEstudios());
            }
        });
        grados.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Docente, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Docente, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getGradoAsignado());
            }
        });
         tabla.setItems(lista);

    }
    
    void agregarElementosTabla()
    {
        
        campo_grado.getItems().add("Iniciacion");
        campo_grado.getItems().add("Parvulos");
        campo_grado.getItems().add("Prejardin");
        campo_grado.getItems().add("Jardin");
        campo_grado.getItems().add("Transicion");
        
        
        listaNotas = bd.SELECT_DOCENTES();
        
        for (int i = 0; i < listaNotas.size(); i++) 
        {
           lista.add(listaNotas.get(i));
        }
       
        
        
    }
   
    @FXML
    public void ver()
    {
        
        //poner en la clase 
        if (!tabla.getSelectionModel().isEmpty())
        {
           

            String documento = tabla.getSelectionModel().getSelectedItems().get(0).getDocumento()+ "";
            campo_documento.setText(documento);

            String nombre = tabla.getSelectionModel().getSelectedItems().get(0).getNombre();
            campo_nombre.setText(nombre);
            
            String apellido = tabla.getSelectionModel().getSelectedItems().get(0).getApellido();
            campo_apellido.setText(apellido);
            
            String telefono = tabla.getSelectionModel().getSelectedItems().get(0).getTelefono();
            campo_telefono.setText(telefono);
            
            String correo = tabla.getSelectionModel().getSelectedItems().get(0).getCorreo();
            campo_correo.setText(correo);
            
            String estudios = tabla.getSelectionModel().getSelectedItems().get(0).getEstudios();
            campo_estudios.setText(estudios);
            
            String grado = tabla.getSelectionModel().getSelectedItems().get(0).getGradoAsignado();
            campo_grado.setValue(grado);
            
            
            int codigo =tabla.getSelectionModel().getSelectedItem().getDocumento(); 
            System.out.println(codigo);
        }
        
        
    }
    @FXML
    public void INSERT()
    {
        
        String documento = campo_documento.getText();
        
        
        String nombre    = campo_nombre.getText();
        String apellido  = campo_apellido.getText();
        String telefono  = campo_telefono.getText();
        String estudios  = campo_estudios.getText();
        String gradoAsignado = campo_grado.getValue();
        
        
        String correo    = campo_correo.getText();
        String nombreCompleto = nombre +" " + apellido;
        String contra = campo_contra.getText();
        LocalDate fecha = campo_fecha.getValue();
        
        if(documento.isEmpty() || nombre.isEmpty()  || apellido.isEmpty()|| 
           telefono.isEmpty()  || correo.isEmpty()|| estudios.isEmpty() || contra.isEmpty())
        {
            mensaje("ERROR FALTAN DATOS", "LLENE TODOS LOS CAMPOS",Alert.AlertType.ERROR);
        
        }
        else
        {
            if (esNumerico(documento))
            {
                
            int doc = Integer.parseInt(documento);
            int cont = 0;
            for (int i = 0; i < lista.size(); i++)
            {
                Docente get = lista.get(i);
                if (get.getDocumento() == doc) cont++;
                
                
            }
            if (cont == 0)
            {
                    String sql = 
                    "BEGIN;   savepoint uno;   "+"\n"
                    +"INSERT INTO DOCENTES(Documento,Nombre,Apellido,Telefono,correo,estudios,gradoAsignado)"
                    + " VALUES ("+ doc+",'"+ nombre+"','"+ apellido+"','"+ telefono+"','"+ correo+"','"+ estudios+"','"+ gradoAsignado+"');" 
                    + "\n"
                    +"INSERT INTO USUARIOS(codigoUsuario,correo,contra,fechaIngreso,nombreCompleto,tipoUsuario)"
                    +" VALUES("+doc+",'"+correo+"','"+contra+"','"+fecha+"','"+nombreCompleto+"','DOCENTE');"
                    + "\n"
                    + "COMMIT;"  
                    + "\n"
                    + "ROLLBACK TO UNO;";         
                    
                    
                    Docente n = new Docente(doc,nombre,apellido,telefono,correo,estudios,gradoAsignado);
                    lista.add(n);
                    
                    String usuario ="\n"+"\n" + "EL USUARIO ES:"+"\n"+"\n" + "CORREO ->  " + correo + "\n"+"CONTRASENA ->  "+contra;
                    mensaje("COMPLETADO", "SE HA REGISTRADO EL DOCENTE " + usuario,Alert.AlertType.INFORMATION);
                    
                    bd.EJECUTE_QUERY(sql);
                    
                }
                else
                {
                      mensaje("ERROR", "EL DOCUMENTO DEL DOCENTE ESTA REGISTRADO",Alert.AlertType.ERROR);
                }
            }else
            {
                mensaje("ERROR", "EL DOCUMENTO NO ES UN NUMERO VALIDO",Alert.AlertType.ERROR);
            }
            
        }
        
        
    }
    
    boolean esNumerico(String str)
    {
        for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i)))
                {
                   return false;
                }
            }
        //RETURNA VERDADERO SI UN VALOR ES UN NUMERO
        return true;
    }

    //cone.UPDATE("login", "nombre", "diaz", "id", "11");
    @FXML
    public void UPDATE()
    {
        String documento = campo_documento.getText();
        
        
        String nombre    = campo_nombre.getText();
        String apellido  = campo_apellido.getText();
        String telefono  = campo_telefono.getText();
        String correo    = campo_correo.getText();
        String estudios  = campo_estudios.getText();
        String gradoAsignado = campo_grado.getValue();
        
   
        
        if (documento.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||
            telefono.isEmpty() || correo.isEmpty() || estudios.isEmpty())
        {
            
             mensaje("ERROR FALTAN DATOS", "LLENE TODOS LOS CAMPOS",Alert.AlertType.ERROR);
        }
        else
        {
        
        if (!tabla.getSelectionModel().isEmpty()) 
        {
            
            if (esNumerico(documento))
            {
                int doc = Integer.parseInt(documento);
                int cont = 0;
                for (int i = 0; i < lista.size(); i++)
                {
                    Docente get = lista.get(i);
                    if (get.getDocumento() == doc) cont++;


                }
                    if (cont == 1) 
                    {

                        int codigo =(int) tabla.getSelectionModel().getSelectedItem().getDocumento(); 
                        System.out.println(codigo);

                        //VALIDAR SI NO ESTAN VACIOS
                        lista.set(tabla.getSelectionModel().getSelectedIndex(), 
                        new Docente(doc,nombre,apellido,telefono,correo,estudios,gradoAsignado));

                        String SET   = "NOMBRE = " +"'"+ nombre+"',"
                                      +"APELLIDO = " +"'"+ apellido+"',"
                                      +"TELEFONO = " +"'"+ telefono+"',"
                                      +"CORREO = " +"'"+ correo+"',"
                                      +"ESTUDIOS = " +"'"+ estudios+"',"
                                      +"GRADOASIGNADO = " +"'"+ gradoAsignado+"'";

                        //COMO CAMBIAR EL VALOR DE UNA LLABVE PRIMARIA


                            String query =   "BEGIN;  " + "\n" 
                                            + "SAVEPOINT UNO;  " + "\n" 
                                            + "UPDATE DOCENTES  "+ "\n" 
                                            + "SET "+ SET +"" +"\n" 
                                            + "WHERE DOCUMENTO = " +codigo+ ";   " +"\n" 
                                           // + "ROLLBACK TO UNO;" +"\n" 
                                            + "COMMIT;    ";
                                           
                            System.out.println(query);


                            bd.EJECUTE_QUERY(query);
                            mensaje("ACTUALIZACION CORRECTA", "SE ACTUALIZARON LOS DATOS CORRECTAMENTE",Alert.AlertType.CONFIRMATION);


                    }else
                    {
                            mensaje("ERROR", "EL DOCUMENTO NO SE ENCUENTRA REGISTRADO",Alert.AlertType.ERROR);
                    }
                }
                else{

                        mensaje("ERROR", "EL DOCUMENTO NO ES UN NUMERO VALIDO",Alert.AlertType.ERROR);

                    }
            }else
            {
               mensaje("ERROR", "NO SELECCIONO NINGUN DOCENTE",Alert.AlertType.ERROR);
            }
        }
       
       
        
    }
    
    @FXML
    public void DELETE()
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
        
     
        if (!tabla.getSelectionModel().isEmpty()) 
        {
            
            if (action.get() == ButtonType.OK) 
            {   
                int documento =(int) tabla.getSelectionModel().getSelectedItem().getDocumento();
                System.out.println(documento);
                String sql ="DELETE FROM DOCENTES  WHERE DOCUMENTO = "+ documento + ";";
                System.out.println(sql);
                bd.EJECUTE_QUERY(sql);

                lista.remove(tabla.getSelectionModel().getSelectedItem());
                mensaje("EXITO", "SE ELIMINO EL DOCENTE",Alert.AlertType.INFORMATION);
            }
        
        }else
        {
            mensaje("ERROR", "NO SELECCIONO NINGUNA DOCENTE",Alert.AlertType.ERROR);
        }
        
    
    }
    void mensaje(String titulo, String info,Alert.AlertType tipo) 
    {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(info);
        alert.showAndWait();
    }
    
  
    @FXML
    public void CLEAR()
    {
     //-----docente
      campo_documento.setText("");
      campo_nombre.setText("");
      campo_apellido.setText("");
      campo_telefono.setText("");
     //correo
      campo_estudios.setText("");
      campo_grado.setValue("");
      
      
      //-----Usuario
      campo_correo.setText("");
      campo_contra.setText("");
      //nombrecompleto
      //fechaIngreso
      
    }
}

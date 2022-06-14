
package Views.Guarderia;

import BaseDatos.BaseDatos;
import Guarderia.Usuario;
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


public class MenuUsuariosController implements Initializable 
{

    @FXML
    private TableView<Usuario> tabla;
    @FXML
    private TableColumn correo;
    @FXML
    private TableColumn contra;
    @FXML
    private TableColumn fechaingreso;
    @FXML
    private TableColumn nombrecompleto;
    @FXML
    private TableColumn tipoUsuario;
   
   
    @FXML
    private JFXTextField campo_correo;
    @FXML
    private JFXTextField campo_contra;
    @FXML
    private JFXTextField campo_nombre;
    @FXML
    private JFXDatePicker campo_fechaIngresa;
    @FXML
    private JFXComboBox<String> campo_tipoUsuario;
    
    ObservableList<Usuario> lista = FXCollections.observableArrayList();
    List<Usuario> listaUsuarios = new ArrayList<>();
    BaseDatos bd = new BaseDatos();
    
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
    
    void agregarColum()
    {
        tabla.setEditable(false);
        correo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Usuario, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Usuario, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCorreo());
            }
        });
        contra.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Usuario, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Usuario, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getContra());
            }
        });
        
        fechaingreso.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Usuario, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Usuario, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFechaIngreso());
            }
        });
        
        nombrecompleto.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Usuario, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Usuario, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNombreCompleto());
            }
        });
        
        tipoUsuario.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Usuario, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Usuario, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTipoUsuario());
            }
        });
        tabla.setItems(lista);
    
    
    }
     
    void agregarElementosTabla()
    {
        
        listaUsuarios = bd.SELECT_USUARIO();
        System.out.println(listaUsuarios.size());
        for (int i = 0; i < listaUsuarios.size(); i++) 
        {
           lista.add(listaUsuarios.get(i));
        }
        //seleccionar todo de la base de datos
        //bd.SELECT("","NOTAS");
        
    }
    
    void llenarCampos()
    {
    campo_tipoUsuario.getItems().add("RECTOR");
    campo_tipoUsuario.getItems().add("DOCENTE");
    campo_tipoUsuario.getItems().add("PADREFAMILIA");
    }
     
    @FXML
    public void INSERT()
    {
       
        String correo = campo_correo.getText();
        String contra = campo_contra.getText();
        LocalDate fechaIngreso = campo_fechaIngresa.getValue();
        String nombre = campo_nombre.getText();
        String tipoUsuario = campo_tipoUsuario.getValue() + "";
        
        if(correo.isEmpty() || contra.isEmpty() || nombre.isEmpty())
        {
            mensaje("ERROR FALTAN DATOS", "LLENE TODOS LOS CAMPOS",Alert.AlertType.ERROR);
        
        }
        else
        {
            String query = "INSERT INTO USUARIOS (correo,contra,fechaIngreso,nombreCompleto,tipoUsuario)"+
                            "VALUES" +"('"+correo+"','"+contra+"','"+fechaIngreso+"','"+nombre+"','"+tipoUsuario+"')";
            System.out.println(query);
            
            bd.EJECUTE_QUERY(query);
            Usuario n = new Usuario(1,correo,contra,fechaIngreso,nombre,tipoUsuario);
            lista.add(n);
            
            mensaje("COMPLETADO", "SE HA REGISTRADO EL USUARIO",Alert.AlertType.CONFIRMATION);
        }
        
        
    }
    
    @FXML
    public void ver()
    {
        
        //poner en la clase 
        if (!tabla.getSelectionModel().isEmpty())
        {
            int codigo =tabla.getSelectionModel().getSelectedItem().getCodigo(); 
            
            String actividad = tabla.getSelectionModel().getSelectedItems().get(0).getCorreo();
            campo_correo.setText(actividad);

            String descripcion = tabla.getSelectionModel().getSelectedItems().get(0).getContra();
            campo_contra.setText(descripcion);
            
            LocalDate fecha =  tabla.getSelectionModel().getSelectedItems().get(0).getFechaIngreso();
            campo_fechaIngresa.setValue(fecha);     
            
            String nombre = tabla.getSelectionModel().getSelectedItems().get(0).getNombreCompleto();
            campo_nombre.setText(nombre);
            
            String tipo = tabla.getSelectionModel().getSelectedItems().get(0).getTipoUsuario();
            campo_tipoUsuario.setValue(tipo);
        }
        
        
    }
    
   
    @FXML
    public void UPDATE()
    {
        String correo = campo_correo.getText();
        String contra  = campo_contra.getText();
        
        String fechaIngreso = campo_fechaIngresa.getValue()+"";
        LocalDate fecha = campo_fechaIngresa.getValue();
        
        String nombreCompleto    = campo_nombre.getText();
        String tipoUsuario  = campo_tipoUsuario.getValue();
        
        if(correo.isEmpty() || contra.isEmpty()  || nombreCompleto.isEmpty())
        {
            mensaje("ERROR FALTAN DATOS", "LLENE TODOS LOS CAMPOS",Alert.AlertType.ERROR);
        
        }
        else
        {
        
        if (!tabla.getSelectionModel().isEmpty()) 
        {
           
            //VALIDAR SI NO ESTAN VACIOS
            lista.set(tabla.getSelectionModel().getSelectedIndex(), 
            new Usuario(1,correo,contra,fecha,nombreCompleto,tipoUsuario));
            
            String SET   ="correo = " +"'"+ correo+"',"
                          +"contra = " +"'"+ contra+"',"
                          +"fechaIngreso = " +"'"+ fecha+"',"
                          +"nombreCompleto = " +"'"+ nombreCompleto+"',"
                          +"tipoUsuario = "+"'"+tipoUsuario+"'";
                    
                String WHERE = "correo = " +"'"+ correo+"'";
                String query = "UPDATE " +"USUARIOS"+" SET "+ SET +" WHERE "+ WHERE;
                System.out.println(query);
               
                
                bd.EJECUTE_QUERY(query);
                mensaje("ACTUALIZACION CORRECTA", "SE ACTUALIZARON LOS DATOS CORRECTAMENTE",Alert.AlertType.INFORMATION);
            
            
            }else{
        
                mensaje("ERROR", "NO SELECCIONO NINGUN USUARIO",Alert.AlertType.ERROR);
            }
        
        }
       
       
        
    }
    
    @FXML
    public void DELETE()
    {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ELIMINAR USUARIO");
        
        ImageView img = new ImageView("Imagenes/Campos/delete-circle.png");
        img.setFitHeight(90f);
        img.setFitWidth(90f);
        alert.setGraphic(img);
        alert.setTitle("Eliminar Usuario");
        alert.setContentText("¿Deseas realmente Eliminar el Usuario?");
        //alert.showAndWait();
        Optional<ButtonType> action = alert.showAndWait();
        
        if (!tabla.getSelectionModel().isEmpty()) 
        {
          
            if (action.get() == ButtonType.OK) 
        {  
            
            String correo = tabla.getSelectionModel().getSelectedItem().getCorreo();
            System.out.println(correo);
            String sql ="DELETE FROM USUARIOS  WHERE correo = '"+ correo + "';";
            System.out.println(sql);
            
            bd.EJECUTE_QUERY(sql);
            
            lista.remove(tabla.getSelectionModel().getSelectedItem());
             mensaje("EXITO", "SE ELIMINO EL USUARIO",Alert.AlertType.INFORMATION);
        }
        }
        else
        {
            mensaje("ERROR", "NO SELECCIONO NINGUN USUARIO",Alert.AlertType.ERROR);
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
    public void limpiar()
    {
      
       campo_correo.setText("");
       campo_contra.setText("");
       campo_fechaIngresa.setValue(LocalDate.now());
       campo_nombre.setText("");
       campo_tipoUsuario.setValue("");
    }
}

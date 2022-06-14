
package Views.Guarderia;

import BaseDatos.BaseDatos;
import Guarderia.Docente;
import Guarderia.Matricula;
import Guarderia.Nota;
import Principal.GuardarDatos;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.text.Text;
import javafx.util.Callback;


public class MenuNotasController implements Initializable {

    @FXML
    private TableColumn  fecha;
    @FXML
    private TableColumn  descripcion;
    @FXML
    private TableColumn actividad;
    @FXML
    private TableColumn matricula;
    @FXML
    private TableColumn calificacion;
    @FXML
    private TableView<Nota> tabla;
    
    @FXML
    private JFXDatePicker campo_fecha;
    @FXML
    private JFXTextField campo_actividad;
    @FXML
    private JFXTextArea campo_descripcion;
    
    ObservableList<Nota> lista = FXCollections.observableArrayList();
    List<Nota> listaNotas = new ArrayList<>();
    BaseDatos bd = new BaseDatos();
    
    
    @FXML
    private JFXComboBox<String> campo_matricula;
   
    @FXML
    private JFXComboBox<String> campo_calificacion;
    @FXML
    private Text campo_nombre;
    @FXML
    private Text campo_apellido;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       //conectar a base de datoss
        bd.conectar();
       //agregar columnas a la tabla
       agregarColum();
       agregarElementosTabla();
        
    }   
    
    void agregarColum()
    {
        tabla.setEditable(false);
         
        matricula.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Nota, Integer>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Nota, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNumeroMatricula());
            }
        });
        fecha.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Nota, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Nota, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFecha());
            }
        });
        descripcion.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Nota, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Nota, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDescripcionNota());
            }
        });
        
        actividad.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Nota, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Nota, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getActividad());
            }
        });
        calificacion.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Nota, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Nota, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCalificacion());
            }
        });
        tabla.setItems(lista);
    
    
    }
    
    void agregarElementosTabla()
    {
        
        listaNotas = bd.SELECT_NOTAS();
        
        for (int i = 0; i < listaNotas.size(); i++) 
        {
           lista.add(listaNotas.get(i));
        }
        
        GuardarDatos cargar = new GuardarDatos();
        cargar.cargarDatos("User");
        List<String> usuario = cargar.getLista();
        
        String tipo = usuario.get(2);
        System.out.println(tipo);
        
        List<Docente> listaDocente = bd.SELECT_DOCENTES();
       
        List<Matricula> listaMatricula = bd.SELECT_MATRICULAS();
       
        if (listaMatricula.size() > 0) 
        {
            if (tipo.equals("RECTOR"))
        {
            for (int i = 0; i < listaMatricula.size(); i++)
            {
                Matricula get = listaMatricula.get(i);
                campo_matricula.getItems().add(get.getNumeroMatricula()+"");
            }
        }else
            {
                String doc =usuario.get(0).toString();
                int documento = Integer.parseInt(doc);
                for (int j = 0; j < listaDocente.size(); j++)
                {
                    Docente docente = listaDocente.get(j);

                    System.out.println("docente --> " + docente.getGradoAsignado());

                        if (docente.getDocumento()== documento)
                        {
                            System.out.println("docente entro--> " + docente.getGradoAsignado());
                            for (int i = 0; i < listaMatricula.size(); i++) 
                            {

                             Matricula matricula = listaMatricula.get(i);
                              System.out.println("Matricula --> " + matricula.getGrado());
                               if(docente.getGradoAsignado().equals(matricula.getGrado()))
                                 {
                                 System.out.println(docente.getGradoAsignado());
                                 campo_matricula.getItems().add(matricula.getNumeroMatricula() + "");
                                 }
                            }


                        }//for matricuka

                }//for docente
            }
        }
        
        campo_calificacion.getItems().add("0");
        campo_calificacion.getItems().add("1");
        campo_calificacion.getItems().add("2");
        campo_calificacion.getItems().add("3");
        campo_calificacion.getItems().add("4"); 
        campo_calificacion.getItems().add("5"); 
        
    }
    
    @FXML
    void  verNombreMatricula()
    {
        List<Matricula> lista_matricula = bd.SELECT_MATRICULAS();
        
        String matricula = campo_matricula.getValue();
        
        GuardarDatos datos = new GuardarDatos();
        List<String> lista= datos.getLista();
        
        if (!campo_matricula.equals(""))
        {
            for (int i = 0; i < lista_matricula.size(); i++)
            {
                Matricula mat = lista_matricula.get(i);
                String numero = mat.getNumeroMatricula()+"";

                if (matricula.equals(numero))
                {
                    campo_nombre.setText(mat.getNombre());
                    campo_apellido.setText(mat.getApellido());
                }

            }
        }
        
    }
    
    
    @FXML
    public void ver()
    {
        
        //poner en la clase 
        if (!tabla.getSelectionModel().isEmpty())
        {
            campo_matricula.setValue(tabla.getSelectionModel().getSelectedItems().get(0).getNumeroMatricula()+"");
            
            LocalDate fecha =  tabla.getSelectionModel().getSelectedItems().get(0).getFecha();
           
            campo_fecha.setValue(fecha);

            String actividad = tabla.getSelectionModel().getSelectedItems().get(0).getActividad();
            campo_actividad.setText(actividad);

            String descripcion = tabla.getSelectionModel().getSelectedItems().get(0).getDescripcionNota();
            campo_descripcion.setText(descripcion);
            
            int codigo =tabla.getSelectionModel().getSelectedItem().getCod_nota(); 
            System.out.println(codigo);
            
            String calificacion = tabla.getSelectionModel().getSelectedItem().getCalificacion() + "";
            campo_calificacion.setValue(calificacion);
            
        }
        
        
    }
  
    @FXML
    public void INSERT()
    {
        int matricula =Integer.parseInt(campo_matricula.getValue());
        String fecha = campo_fecha.getValue() + "";
        LocalDate date = campo_fecha.getValue();
        String actividad = campo_actividad.getText();
        String descripcion = campo_descripcion.getText();
        String calificacion = campo_calificacion.getValue();
        
        if(fecha.isEmpty() || actividad.isEmpty() || descripcion.isEmpty() || campo_calificacion.getValue() == null)
        {
            mensaje("ERROR FALTAN DATOS", "LLENE TODOS LOS CAMPOS");
        
        }
        else
        {
            listaNotas = bd.SELECT_NOTAS();
            int cod_nota = listaNotas.size()+2;
           
            int cali = Integer.parseInt(calificacion);
            System.out.println("cod nota  " + cod_nota);
            bd.EJECUTE_QUERY("INSERT INTO NOTAS (cod_nota,cod_matricula,Fecha,actividad,descripcion,calificacion) VALUES ("+cod_nota+","+matricula+",'"+date+"','"+actividad+"','"+descripcion+"',"+cali+")");
         
            Nota n = new Nota(matricula,date,actividad,descripcion,cali);
            lista.add(n);
            
            mensaje("COMPLETADO", "SE HA REGISTRADO LA NOTA");
        }
        
        
    }
    
    @FXML
    public void UPDATE()
    {
        LocalDate fecha = campo_fecha.getValue();
        String date =campo_fecha.getValue()+"";
        String actividad = campo_actividad.getText();
        
        String descripcion = campo_descripcion.getText();
        
        String calificacion = campo_calificacion.getValue();
        if (date.isEmpty() || actividad.isEmpty() || descripcion.isEmpty() || campo_calificacion.getValue() == null)
        {
            
             mensaje("ERROR FALTAN DATOS", "LLENE TODOS LOS CAMPOS");
        }
        else
        {
        
        if (!tabla.getSelectionModel().isEmpty()) 
        {
            int codigo =(int) tabla.getSelectionModel().getSelectedItem().getCod_nota(); 
            System.out.println(codigo);
            
            int cali = Integer.parseInt(calificacion);
            //VALIDAR SI NO ESTAN VACIOS
            lista.set(tabla.getSelectionModel().getSelectedIndex(), 
            new Nota(codigo,fecha,actividad,descripcion,cali));
            
            try
            {
               
                String SET   = "FECHA = " +"'"+ fecha+"'" +","
                               +"ACTIVIDAD = " +"'"+ actividad+"'" +","
                               +"DESCRIPCION = " +"'"+ descripcion+"'";
                String WHERE = "COD_NOTA = " + codigo;
                String query = "UPDATE " +"NOTAS"+" SET "+ SET +" WHERE "+ WHERE;
                System.out.println(query);
               
                
                bd.EJECUTE_QUERY(query);
                mensaje("ACTUALIZACION CORRECTA", "SE ACTUALIZARON LOS DATOS CORRECTAMENTE");
                
            } catch (Exception e) 
            {
                System.out.println("error");
            }
            
            }//else
        
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
            
            
            int codigo =(int) tabla.getSelectionModel().getSelectedItem().getCod_nota();
            System.out.println(codigo);
            String sql ="DELETE FROM NOTAS  WHERE COD_NOTA = "+ codigo + ";";
            System.out.println(sql);
            bd.EJECUTE_QUERY(sql);
            
            lista.remove(tabla.getSelectionModel().getSelectedItem());
             mensaje("EXITO", "SE ELIMINO LA NOTA");
            }
        }
        else
        {
            mensaje("ERROR", "NO SELECCIONO NINGUNA NOTA");
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
    
    public void CLEAR()
    {
       campo_fecha.setValue(LocalDate.now());
        
       campo_actividad.setText("");
        
       campo_descripcion.setText("");
    }
    
}

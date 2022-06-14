
package Views.Guarderia;

import BaseDatos.BaseDatos;
import Guarderia.Matricula;
import Guarderia.PadreFamilia;
import com.jfoenix.controls.JFXComboBox;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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


public class MenuPersonaResponsableController implements Initializable
{

    @FXML
    private JFXComboBox<String> campo_matricula;
    @FXML
    private JFXTextField campo_documento;
    @FXML
    private JFXTextField campo_nombres;
    @FXML
    private JFXTextField campo_direccion;
    @FXML
    private JFXTextField campo_telefono;
    @FXML
    private JFXTextField campo_parentesco;
    
    @FXML
    private TableView<PadreFamilia> tabla;
    @FXML
    private TableColumn matricula;
    @FXML
    private TableColumn documento;
    @FXML
    private TableColumn nombre;
    @FXML
    private TableColumn direccion;
    @FXML
    private TableColumn telefono;
    @FXML
    private TableColumn parentesco;
    
    
    BaseDatos bd = new BaseDatos();
    List<PadreFamilia> listaPadres = new ArrayList<>();
    ObservableList<PadreFamilia> lista = FXCollections.observableArrayList();
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
    
    void agregarColum() {

        matricula.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PadreFamilia, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PadreFamilia, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNumeroMatricula());
            }
        });
        documento.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PadreFamilia, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PadreFamilia, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDocumento());
            }
        });
        nombre.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PadreFamilia, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PadreFamilia, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNombre());
            }
        });

        direccion.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PadreFamilia, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PadreFamilia, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDireccion());
            }
        });
        telefono.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PadreFamilia, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PadreFamilia, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTelefono());
            }
        });
        parentesco.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PadreFamilia, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PadreFamilia, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getParentesco());
            }
        });
        
     
        tabla.setItems(lista);
    }
    
    void agregarElementosTabla()
    {
        
        listaPadres = bd.SELECT_PADREFAMILIA();
        
        if (listaPadres.size() > 0) 
        {
            for (int i = 0; i < listaPadres.size(); i++) 
            {
               lista.add(listaPadres.get(i));
            }

        }
         //----matriculas
            List<Matricula>listaMatricula = bd.SELECT_MATRICULAS();
        if (listaMatricula.size() > 0)
            {
               for (int i = 0; i < listaMatricula.size(); i++) 
                {
                    Matricula get = listaMatricula.get(i);
                    //if(get.getGrado() == Usuario_grado)
                     campo_matricula.getItems().add(get.getNumeroMatricula() + "");

                } 
            }
        
    }
    
    @FXML
    void  verNombreMatricula()
    {
        List<Matricula> lista_matricula = bd.SELECT_MATRICULAS();
        
        String matricula = campo_matricula.getValue();
        
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
    void ver()
    {
     if (!tabla.getSelectionModel().isEmpty())
        {
        String matricula = tabla.getSelectionModel().getSelectedItems().get(0).getNumeroMatricula()+ "";
        campo_matricula.setValue(matricula);
        
        String documento = tabla.getSelectionModel().getSelectedItems().get(0).getDocumento()+ "";
        campo_documento.setText(documento);
        
        String nombre =    tabla.getSelectionModel().getSelectedItems().get(0).getNombre()+ "";
        campo_nombres.setText(nombre);
        
        String direccion =  tabla.getSelectionModel().getSelectedItems().get(0).getDireccion()+ "";
        campo_direccion.setText(direccion);
                
        String telefono =  tabla.getSelectionModel().getSelectedItems().get(0).getTelefono()+ "";
        campo_telefono.setText(telefono);
        
        String parentesco =  tabla.getSelectionModel().getSelectedItems().get(0).getParentesco()+ "";
        campo_parentesco.setText(parentesco);
                                        
        }
    
    }
    
    @FXML
    void INSERT()
    {
        String numeroMatricula = campo_matricula.getValue();
       
        String documentos    = campo_documento.getText();
        String nombre    = campo_nombres.getText();
        String direccion = campo_direccion.getText();
        String telefono  = campo_telefono.getText();
        String parentesco = campo_parentesco.getText();
        
        if (nombre.isEmpty() || documentos.isEmpty()|| direccion.isEmpty() || telefono.isEmpty() || parentesco.isEmpty() || numeroMatricula.isEmpty())
        {
            mensaje("ERROR FALTAN DATOS", "LLENE TODOS LOS CAMPOS");
            
        }else
        {
            int documento = Integer.parseInt(documentos);
            int matricula = Integer.parseInt(numeroMatricula);

            PadreFamilia padre = new PadreFamilia(documento,matricula,nombre,direccion,telefono,parentesco);
            lista.add(padre);
             
            String query = "INSERT INTO personaResponsable(documento,numeroMatricula,nombre,direccion,Telefono,parentesco)\n" +
                            "VALUES("+documento+","+matricula+",'"+nombre+"','"+direccion+"','"+telefono+"','"+parentesco+"'); ";

            System.out.println(query);

            bd.EJECUTE_QUERY(query);
            mensaje("REGISTRO CORRECTO", "SE REGISTRO LA PERSONA RESPONSABLE CORRECTAMENTE");

          
        
        }
    
    }
    
    @FXML
    void UPDATE()
    {
        String numeroMatricula = campo_matricula.getValue();
       
        String documentos    = campo_documento.getText();
        String nombre    = campo_nombres.getText();
        String direccion = campo_direccion.getText();
        String telefono  = campo_telefono.getText();
        String parentesco = campo_parentesco.getText();
        
        if (nombre.isEmpty() || documentos.isEmpty()|| direccion.isEmpty() || telefono.isEmpty() || parentesco.isEmpty())
        {
            mensaje("ERROR FALTAN DATOS", "LLENE TODOS LOS CAMPOS");
            
        }else
        {
            if (!tabla.getSelectionModel().isEmpty()) 
            {
                
            
            int documento = Integer.parseInt(documentos);
            int matricula = Integer.parseInt(numeroMatricula);

            lista.set(tabla.getSelectionModel().getSelectedIndex(), 
            new PadreFamilia(documento,matricula,nombre,direccion,telefono,parentesco));
            
            
            String query = "UPDATE  personaResponsable " +"\n"+
                           "SET numeroMatricula = "+matricula+","+
                            "nombre = "+"'"+nombre+"',"+
                            "direccion = "+ "'"+direccion+"',"+
                            "telefono = '"+telefono+"',"+
                            "parentesco = '"+parentesco+"'"+"\n"+
                            "WHERE documento = "+documento;
            System.out.println(query);

            bd.EJECUTE_QUERY(query);
            mensaje("ACTULIZACION CORRECTO", "SE ACTUALIZARION LOS DATOS DE LA PERSONA RESPONSABLE");
            }
            else
            {
                mensaje("ERROR", "NO SELECCIONO NINGUNA PERSONA RESPONSABLE");
            
            }
          
        
        }
    
    }
    
    
    @FXML
    void DELETE()
    {
     if (!tabla.getSelectionModel().isEmpty()) 
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("ELIMINAR PERSONA RESPONSABLE");

            ImageView img = new ImageView("Imagenes/Campos/delete-circle.png");
            img.setFitHeight(90f);
            img.setFitWidth(90f);
            alert.setGraphic(img);
            alert.setTitle("Eliminar Persona Responsable");
            alert.setContentText("¿Deseas realmente Eliminar La persona Responsable?");
            //alert.showAndWait();
            Optional<ButtonType> action = alert.showAndWait();
        
            if (action.get() == ButtonType.OK) 
            {  
            
                int documento =(int) tabla.getSelectionModel().getSelectedItem().getDocumento();
                System.out.println(documento);
                String sql ="DELETE FROM PERSONARESPONSABLE  WHERE documento = "+ documento + ";";
                System.out.println(sql);
                
                bd.EJECUTE_QUERY(sql);
                lista.remove(tabla.getSelectionModel().getSelectedItem());
                 mensaje("EXITO", "SE ELIMINO LA PERSONA RESPONSABLE");
            }
        }
        else
        {
            mensaje("ERROR", "NO SELECCIONO NINGUNA PERSONA RESPONSABLE");
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
    void CLEAR()
    {
        campo_documento.setText("");
        campo_nombres.setText("");
        campo_direccion.setText("");
        campo_telefono.setText("");
        campo_parentesco.setText("");
    }
    
   
}

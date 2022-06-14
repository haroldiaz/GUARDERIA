
package Views.Docente;

import BaseDatos.BaseDatos;
import Guarderia.Docente;
import Guarderia.Matricula;
import Guarderia.Mensualidad;
import Principal.GuardarDatos;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.util.Callback;


public class MenuPagosMensualidadController implements Initializable 
{

    @FXML
    private JFXComboBox<String> campo_matricula;
    @FXML
    private JFXComboBox<String> campo_estado;
    @FXML
    private JFXTextField campo_valormensualidad;
    @FXML
    private JFXDatePicker campo_fecha;

   
    @FXML
    private TableView<Mensualidad> tabla;
    @FXML
    private TableColumn Nomatricula;
    @FXML
    private TableColumn fechaPago;
    @FXML
    private TableColumn Estado;
    @FXML
    private TableColumn valorMatricula;
    
    ObservableList<Mensualidad> lista = FXCollections.observableArrayList();
    BaseDatos bd = new BaseDatos();
    
    List<Mensualidad>  listaMensualidad = new ArrayList();
    List<Matricula>  listaMatricula = new ArrayList();
    @FXML
    private Text campo_nombre;
    @FXML
    private Text campo_apellido;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        bd.conectar();
        llenarColomn();
        llenarCampos();
        agregarElementosTabla();
    } 
    
    void llenarColomn()
    {
        Nomatricula.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mensualidad, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Mensualidad, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNumeroMatricula());
            }
        });
        fechaPago.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mensualidad, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Mensualidad, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFecha());
            }
        });
        Estado.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mensualidad, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Mensualidad, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getEstado());
            }
        });

        valorMatricula.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mensualidad, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Mensualidad, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getValorMensualidad());
            }
        });
        
        tabla.setItems(lista);
    }

    void llenarCampos()
    {
        listaMatricula = bd.SELECT_MATRICULAS();
        List<Docente> listaDocente = bd.SELECT_DOCENTES();
        
        GuardarDatos cargar = new GuardarDatos();
        cargar.cargarDatos("User");
        List<String> usuario = cargar.getLista();
        
        String doc =usuario.get(0).toString();
        
        int documento = Integer.parseInt(doc);
        System.out.println("" + doc);
        
        
        if (listaMatricula.size() > 0)
        {
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
        
        
        campo_estado.getItems().add("PAGO");
        campo_estado.getItems().add("NO PAGO");
        
        
    }
    
    void agregarElementosTabla()
    {
                               
        listaMensualidad = bd.SELECT_MENSUALIDAD();
        
        for (int i = 0; i < listaMensualidad.size(); i++) 
        {
           lista.add(listaMensualidad.get(i));
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
    public void pagarMensualidad()
    {
        String matricula = campo_matricula.getValue();
        LocalDate fecha = campo_fecha.getValue();
        String estado = campo_estado.getValue()+"";
        String valor = campo_valormensualidad.getText();
        
        System.out.println(matricula);
        if(campo_matricula.getValue() == null || fecha == null || estado == null || valor.isEmpty())
        {
            mensaje("ERROR", "LLENE TODOS LOS CAMPOS", Alert.AlertType.ERROR);
        }else
        {
            int valorMensualidad = Integer.parseInt(valor);
            int matri = Integer.parseInt(matricula);
            
            Mensualidad m = new Mensualidad(matri, fecha, estado, valorMensualidad);
            lista.add(m);
            
            String query = "BEGIN"+"\n"
                         + "savepoint uno;"+"\n"
                         + " INSERT INTO MENSUALIDAD(numeroMatricula,fechaPago,Estado,valorMensualidad)"+"\n"
                         + "VALUES(" +matricula+",'"+fecha+"','"+estado+"',"+valorMensualidad+");"+"\n"
                         + "COMMIT  "+"\n"
                         + "Rollback to uno; ";   
            bd.EJECUTE_QUERY(query);
            
            mensaje("EXITO", "SE REGISTRO LA MENSUALIDAD", Alert.AlertType.INFORMATION);
        }
    }
    
    void mensaje(String titulo, String info, Alert.AlertType tipo) 
    {
        Alert alert =new  Alert(tipo);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(info);
        alert.showAndWait();
    }
    
    @FXML
    void CLEAR()
    {
      campo_matricula.setValue("");
      campo_fecha.setValue(LocalDate.now());
      campo_matricula.setValue("");
      campo_valormensualidad.setText("");
    }
}


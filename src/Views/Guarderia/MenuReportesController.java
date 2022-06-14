
package Views.Guarderia;

import BaseDatos.BaseDatos;
import Guarderia.Matricula;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;


public class MenuReportesController implements Initializable {

    @FXML
    private TableView<Matricula> tabla;
    @FXML
    private TableColumn numeroMatricula;
    @FXML
    private TableColumn nombre;
    @FXML
    private TableColumn apellidos;
    @FXML
    private TableColumn numeroRegistro;
    @FXML
    private TableColumn tipoSangre;
    @FXML
    private TableColumn sexo;
    @FXML
    private TableColumn fechaNacimiento;
    @FXML
    private TableColumn fechaIngreso;
    @FXML
    private TableColumn grado;
    @FXML
    private JFXComboBox<String> campo_grado;

    ObservableList<Matricula> lista = FXCollections.observableArrayList();
    List<Matricula> listaMatriculas = new ArrayList<>();
    BaseDatos bd = new BaseDatos();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        bd.conectar();
        listaMatriculas = bd.SELECT_MATRICULAS();
        
        
        llenarColum();
        llenarCampos();
    }    
    
    void llenarCampos()
    {
    campo_grado.getItems().add("Iniciacion");
    campo_grado.getItems().add("Parvulos");
    campo_grado.getItems().add("Prejardin");
    campo_grado.getItems().add("Jardin");
    campo_grado.getItems().add("Transicion");
    }
    
     void llenarColum()
    {
        numeroMatricula.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Matricula, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Matricula, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNumeroMatricula());
            }
        });
        nombre.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Matricula, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Matricula, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNombre());
            }
        });
        apellidos.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Matricula, String>, ObservableValue<String>>() {
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
        sexo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Matricula, String>, ObservableValue<String>>() {
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
        
        
    }
     
     
    @FXML
    public void reporte()
    {
        
        System.out.println("bton");
        String gradoSeleccionado = campo_grado.getValue();
        
       
        
        for (int i = 0; i < listaMatriculas.size(); i++)
        {
            Matricula get = listaMatriculas.get(i);
            //System.out.println(get.getNombre());
            
            if (get.getGrado().equals(gradoSeleccionado))
            {
                System.out.println(get.getApellido());
                lista.add(get);
            }
        }
        
        tabla.setItems(lista);
        
    
    }
    @FXML
    public void limpiar()
    {
        tabla.getItems().clear();
        lista.clear();
    }
}

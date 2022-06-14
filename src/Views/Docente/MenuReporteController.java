
package Views.Docente;

import BaseDatos.BaseDatos;
import Guarderia.Docente;
import Guarderia.Matricula;
import Principal.GuardarDatos;
import java.net.URL;
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
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;



public class MenuReporteController implements Initializable {

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
  
    ObservableList<Matricula> lista = FXCollections.observableArrayList();
    
    BaseDatos bd = new BaseDatos();
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       bd.conectar();
       
       agregarElementosTabla();
       agregarColum();
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
        
        List<Matricula>listaMatricula = bd.SELECT_MATRICULAS();
        
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
                                lista.add(matricula);
                             }
                        }
                    
                
                    }//for matricuka
                
            }//for docente
        }
        
    }
    
}


package Views.PadreFamilia;

import BaseDatos.BaseDatos;
import Guarderia.Nota;
import Guarderia.PadreFamilia;
import Guarderia.Usuario;
import Principal.GuardarDatos;
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


public class ConsultaNotasController implements Initializable
{

    @FXML
    private TableView<Nota> tabla;
    @FXML
    private TableColumn fecha;
    @FXML
    private TableColumn actividad;
    @FXML
    private TableColumn descripcion;
    @FXML
    private TableColumn calificacion;

    ObservableList<Nota> lista = FXCollections.observableArrayList();
    
    List<PadreFamilia> listaPadre = new ArrayList<>();
    BaseDatos bd = new BaseDatos();
    
    GuardarDatos cargar = new GuardarDatos();
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       bd.conectar();
       agregarColum();
       
       
       consultarNotas();
    }    
    
    void agregarColum()
    {
        tabla.setEditable(false);
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
    
    void consultarNotas()
    {
        
        cargar.cargarDatos("User");
        cargar.printList();
       
        List<String> lista1 = cargar.getLista();
        
        System.out.println("Codigo       ->" + lista1.get(0));
        System.out.println("CORREO       ->" + lista1.get(1));
        System.out.println("TIPO USUARIO ->" + lista1.get(2));
        
        int documento = Integer.parseInt(lista1.get(0));
        
        
        listaPadre = bd.SELECT_PADREFAMILIA();
        List<Nota> listanota = bd.SELECT_NOTAS();
        
        
        for (int i = 0; i < listaPadre.size(); i++)
        {
            PadreFamilia padre = listaPadre.get(i);
            System.out.println("padre -> " + padre.getNombre());
            System.out.println("Matricula -> " + padre.getNumeroMatricula());
            
            if (documento == padre.getDocumento())
            {
                for (int j = 0; j < listanota.size(); j++)
                {
                    Nota nota = listanota.get(j);

                    if (padre.getNumeroMatricula() == nota.getNumeroMatricula()) 
                    {
                        lista.add(nota);
                    }
                }   
            }
            
        }
        
    
    
    }
    
}

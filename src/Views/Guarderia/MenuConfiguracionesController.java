
package Views.Guarderia;

import BaseDatos.BaseDatos;
import Guarderia.Usuario;
import Principal.GuardarDatos;
import Principal.Main;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;


public class MenuConfiguracionesController implements Initializable {

    
    Main ventana;
    @FXML
    private JFXTextField campo_nombre;
    @FXML
    private JFXTextField campo_correo;
    @FXML
    private JFXTextField campo_contra;
    @FXML
    private Text campo_fecha;
    
    
    GuardarDatos cargar = new GuardarDatos();
    BaseDatos bd = new  BaseDatos();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        bd.conectar();
        user();
    }    
    
    
    void user()
    {
        
        cargar.cargarDatos("User");
        cargar.printList();
        List<String> lista = cargar.getLista();
        
        System.out.println("CORREO ->" + lista.get(1));
        System.out.println("TIPO USUARIO ->" + lista.get(1));
        
        String correo = lista.get(1);
        System.out.println(correo);
        
        List<Usuario> listauser = bd.SELECT_USUARIO();
        
        for (int i = 0; i < listauser.size(); i++)
        {
            Usuario get = listauser.get(i);
            System.out.println(get.getCorreo());
            
            if (correo.equals(get.getCorreo())) 
            {
                System.out.println("igual");
                campo_nombre.setText(get.getNombreCompleto());
                campo_correo.setText(get.getCorreo());
                campo_contra.setText(get.getContra());
                campo_fecha.setText(get.getFechaIngreso()+"");
                
            }
        }
        
    
    
    }
    
}

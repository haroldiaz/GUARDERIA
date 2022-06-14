package Views.Logins;

import BaseDatos.BaseDatos;
import Principal.GuardarDatos;
import Guarderia.Usuario;
import Principal.Main;
import Views.PadreFamilia.PadreFamiliaMenuController;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;


public class LoginController implements Initializable {

    Main ventana;

    @FXML
    private JFXTextField campo_correo;

    @FXML
    private JFXPasswordField campo_contra;

 
    
    List<Usuario> listaUsuarios = new ArrayList<>();
    BaseDatos bd = new BaseDatos();
    @FXML
    private JFXCheckBox mostrarContra;
    @FXML
    private JFXTextField text_contra;

    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
      
        bd.conectar();
      
        listaUsuarios = bd.SELECT_USUARIO();
        
        text_contra.setVisible(false);
    
    }

  
    public void setVentanaPrincipal(Main ventana) {
        this.ventana = ventana;
    }

    @FXML
    void mostrarContra()
    {
      if (mostrarContra.isSelected()) 
      {
        text_contra.setText(campo_contra.getText());
        campo_contra.setText(text_contra.getText());
        text_contra.setVisible(true);
        campo_contra.setVisible(false);
        return;
       }
      
        campo_contra.setVisible(true);
        campo_contra.setText(text_contra.getText());
        text_contra.setVisible(false);

    }
    
    @FXML
    public void login() 
    {

        String correo = campo_correo.getText().toLowerCase();
        String contra = campo_contra.getText().toLowerCase();

        System.out.println("correo --> "  + correo);
        if (validarCamposVacios()) 
        {
            for (int i = 0; i < listaUsuarios.size(); i++)
            {
                Usuario get = listaUsuarios.get(i);

                if (get.getCorreo().equals(correo) && get.getContra().equals(contra)) 
                {
                    
                    String datos = get.getCodigo()+"\n"+
                                   correo         +"\n"+
                                   get.getTipoUsuario(); 
                    GuardarDatos data = new GuardarDatos();
                    data.guardarDatos("User",datos,false);
                    System.out.println("se guardaron los datos");
                   
                    switch (get.getTipoUsuario()) 
                    {
                        
                        case "RECTOR":
                            this.ventana.RectorMenu();
                            break;
                        case "DOCENTE":
                            this.ventana.DocenteMenu();
                            break;
                        case "PADREFAMILIA":
                            this.ventana.PadreMenu();
                            break;
                        default:
                            System.out.println("selecciona un campo");
                            break;
                    }

                }

            }//for
          
        }else 
        {
          mess("Error Usuario ", "El Usuario no existe");
        } 

    }//login

    boolean validarCamposVacios() {

        //si los campos no estan vacios continue
        if (campo_correo.getText().equals("") && campo_contra.getText().equals("")) {
            mess("Campos Vacios", "Por favor llenar todos los campos");
        } else if (campo_correo.getText().equals("")) {
            mess("Campos Vacios", "No ingreso ningun Correo");
        } else if (campo_contra.getText().equals("")) {
            mess("Campos Vacios", "No ingreso ninguna Contraseña");
        } else {
            return true;
        }

        return false;
    }

    void mess(String titulo, String info) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(info);
        alert.showAndWait();
    }

    ///guardar el usuario en un txt
    //comprobar si quiere que losdatos de sesion se conserven
    //nombre contra  guardar true o false  //tipo usuario  profesor admin
    //comprbar sila conexion esta actia en la base de datos
    //dependiendo del tipo de usuario se pasa a la scena

    
}

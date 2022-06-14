
package Views.PadreFamilia;

import BaseDatos.BaseDatos;
import Guarderia.Usuario;
import Principal.GuardarDatos;
import Principal.Main;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class PadreFamiliaMenuController implements Initializable
{

    Main ventana;
    
    @FXML
    private JFXButton btn_config;
    @FXML
    private ImageView img_notas;
    @FXML
    private ImageView img_config;
    @FXML
    private JFXButton btn_notas;
    
    
    GuardarDatos cargar = new GuardarDatos();
    BaseDatos bd = new  BaseDatos();
    @FXML
    private Text txt_nombre;
    
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
        
        System.out.println("Codigo ->" + lista.get(0));
        System.out.println("CORREO ->" + lista.get(1));
        System.out.println("TIPO USUARIO ->" + lista.get(2));
        
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
               txt_nombre.setText(get.getNombreCompleto() +"");
            }else
            {
                System.out.println("nada");
            }
        }
        
    
    
    }
    
    public void setVentanaPrincipal(Main ventana)
    {
     this.ventana = ventana;
    }
     

    @FXML
    public void consultarNotas()
    {
      
        abrirVentanas("PadreFamilia/ConsultaNotas");
    }
    
    @FXML
    public void abrirConfig()
    {
        abrirVentanas("Guarderia/MenuConfiguraciones");
    }
    
   
    
    
    //------------------LLAMADA DEL MAIN
    void abrirVentanas(String nombre)
    {
     try 
        {
        Stage stage  = new Stage();
        
        Parent root = FXMLLoader.load(getClass().getResource("/Views/"+nombre+".fxml"));

        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
       
        
        //stage.initStyle(StageStyle.UNDECORATED);
        // stage.initStyle(StageStyle.TRANSPARENT);
         
        
        //no deja abrir otra tiene un boton salir
        stage.initStyle(StageStyle.UTILITY);
        
        
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex)
        {
            System.out.println("--ERROR -" + nombre + ex);
        }
       
    
    
    }
    
    
    
    //-------------------CAMBIO DE COLOR-----------
    //onmauseentered
  
    @FXML
    void cambiarColorNotas()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/nota_blanco.png");
       
        btn_notas.setStyle("-fx-background-color:" + " #0000ffff");
        img_notas.setImage(img);
        btn_notas.setTextFill(Paint.valueOf("white"));
    }
    
    @FXML
    void btonNotas()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/nota.png");
        btn_notas.setStyle("-fx-background-color:white");
        img_notas.setImage(img);
        btn_notas.setTextFill(Paint.valueOf("BLACK"));
    }
    @FXML
    void cambiarColorConfig()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/usuario_blanco.png");
        btn_config.setStyle("-fx-background-color:" + " #0000ffff");
        img_config.setImage(img);
        btn_config.setTextFill(Paint.valueOf("white"));
    }
    
    @FXML
    void btonConfig()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/usuario.png");
        img_config.setImage(img);
        btn_config.setStyle("-fx-background-color:white");
        btn_config.setTextFill(Paint.valueOf("BLACK"));
    }
    
    
    @FXML
    void cerrarSesion()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("CERRAR SESION");
        
        ImageView img = new ImageView("Imagenes/Campos/login.png");
        img.setFitHeight(90f);
        img.setFitWidth(90f);
        alert.setGraphic(img);
        alert.setTitle("");
        alert.setContentText("¿Deseas Cerrar Sesion?");
        //alert.showAndWait();
        Optional<ButtonType> action = alert.showAndWait();
        
        // Si hemos pulsado en aceptar
        
        if (action.get() == ButtonType.OK) 
        {  
            ventana.LOGIN();
        }
        
    
    }
}

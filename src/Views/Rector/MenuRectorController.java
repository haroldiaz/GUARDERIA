
package Views.Rector;

import Principal.Main;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MenuRectorController implements Initializable {
    
    Main ventana;
    @FXML
    private JFXButton btn_docentes;
    @FXML
    private ImageView img_docente;
    @FXML
    private JFXButton btn_matriculas;
    @FXML
    private ImageView img_matriculas;
    @FXML
    private JFXButton btn_notas;
    @FXML
    private ImageView img_notas;
    @FXML
    private JFXButton btn_reporte;
    @FXML
    private ImageView img_reporte;
    private JFXButton btn_config;
    private ImageView img_config;
    @FXML
    private JFXButton btn_usuarios;
    @FXML
    private ImageView img_usuarios;
    @FXML
    private JFXButton btn_persona;
    @FXML
    private ImageView img_persona;
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       
    }    
    
    public void setVentanaPrincipal(Main ventana)
    {
     this.ventana = ventana;
    }
    
    @FXML
    void abrirUsuarios()
    {
         abrirVentanas("Guarderia/MenuUsuarios");
       
    }
    
    @FXML
    void abrirDocentes()
    {
         abrirVentanas("Guarderia/MenuDocentes");
       
    }
    
    @FXML
    void abrirMatriculas()
    {
       
       //abrirVentanas("PadreFamilia/Matricular");
       abrirVentanas("Guarderia/MenuMatriculas");
       
    }
    @FXML
    void abrirPersonasReponsable()
    {
       
       //abrirVentanas("PadreFamilia/Matricular");
       abrirVentanas("Guarderia/MenuPersonaResponsable");
       
    }
    
    @FXML
    void abrirNotas()
    {
      abrirVentanas("Guarderia/MenuNotas");
    }
    
    @FXML
    void abrirReportes()
    {
       abrirVentanas("Guarderia/MenuReportes");
    }
  
    void abrirConfiguraciones()
    {
       
       //abrirVentanas("PadreFamilia/Matricular");
       abrirVentanas("Guarderia/MenuConfiguraciones");
       
    }
    
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
    
    
    //------------CAMBIAR COLORES-----------------
    
    @FXML
    void cambiarColorUsuarios()
    {
        //blanco
        Image img;
        img = new Image("Imagenes/Guarderia/usuario_blanco.png");
       
        btn_usuarios.setStyle("-fx-background-color:" + " #0000ffff");
        img_usuarios.setImage(img);
        btn_usuarios.setTextFill(Paint.valueOf("white"));
    }
    
    //on mause exity
    @FXML
    void btonUsuario()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/usuario.png");
        
        btn_usuarios.setStyle("-fx-background-color:white");
        img_usuarios.setImage(img);
        btn_usuarios.setTextFill(Paint.valueOf("BLACK"));
    }
    
    @FXML
    void cambiarColorDocentes()
    {
        //blanco
        Image img;
        img = new Image("Imagenes/Guarderia/docente_blanco.png");
       
        btn_docentes.setStyle("-fx-background-color:" + " #0000ffff");
        img_docente.setImage(img);
        btn_docentes.setTextFill(Paint.valueOf("white"));
    }
    
    //on mause exity
    @FXML
    void btonDocente()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/docente.png");
        
        btn_docentes.setStyle("-fx-background-color:white");
        img_docente.setImage(img);
        btn_docentes.setTextFill(Paint.valueOf("BLACK"));
    }
    
    
    @FXML
    void cambiarColorMatriculas()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/matricula_blanco.png");
       
        btn_matriculas.setStyle("-fx-background-color:" + " #0000ffff");
        img_matriculas.setImage(img);
        btn_matriculas.setTextFill(Paint.valueOf("white"));
    }
    
    //on mause exity
    @FXML
    void btonMatriculas()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/matricula.png");
        
        btn_matriculas.setStyle("-fx-background-color:white");
        img_matriculas.setImage(img);
        btn_matriculas.setTextFill(Paint.valueOf("BLACK"));
    }
    
    @FXML
    void cambiarColorNotas()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/nota_blanco.png");
       
        btn_notas.setStyle("-fx-background-color:" + " #0000ffff");
        img_notas.setImage(img);
        btn_notas.setTextFill(Paint.valueOf("white"));
    }
    
    //on mause exity
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
    void cambiarColorPersona()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/persona_blanca.png");
       
        btn_persona.setStyle("-fx-background-color:" + " #0000ffff");
        img_persona.setImage(img);
        btn_persona.setTextFill(Paint.valueOf("white"));
    }
    
    //on mause exity
    @FXML
    void btonPersona()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/persona.png");
        
        btn_persona.setStyle("-fx-background-color:white");
        img_persona.setImage(img);
        btn_persona.setTextFill(Paint.valueOf("BLACK"));
    }
    @FXML
    void cambiarColorReporte()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/reporte_blanco.png");
       
        btn_reporte.setStyle("-fx-background-color:" + " #0000ffff");
        img_reporte.setImage(img);
        btn_reporte.setTextFill(Paint.valueOf("white"));
    }
    
    //on mause exity
    @FXML
    void btonReporte()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/reporte.png");
        
        btn_reporte.setStyle("-fx-background-color:white");
        img_reporte.setImage(img);
        btn_reporte.setTextFill(Paint.valueOf("BLACK"));
    }
    void cambiarColorConfig()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/config_blanco.png");
       
        btn_config.setStyle("-fx-background-color:" + " #0000ffff");
        img_config.setImage(img);
        btn_config.setTextFill(Paint.valueOf("white"));
    }
    
    //on mause exity
    void btonConfig()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/config.png");
        
        btn_config.setStyle("-fx-background-color:white");
        img_config.setImage(img);
        btn_config.setTextFill(Paint.valueOf("BLACK"));
    }

}

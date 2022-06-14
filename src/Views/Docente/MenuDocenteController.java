
package Views.Docente;

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


public class MenuDocenteController implements Initializable {

    Main ventana;
    @FXML
    private JFXButton btn_pagos;
    @FXML
    private JFXButton btn_notas;
    @FXML
    private JFXButton btn_reporte;
    @FXML
    private JFXButton btn_config;
    @FXML
    private ImageView img_pagos;
    @FXML
    private ImageView img_notas;
    @FXML
    private ImageView img_reporte;
    @FXML
    private ImageView img_config;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
    public void setVentanaPrincipal(Main ventana)
    {
     this.ventana = ventana;
    }
    
    @FXML
    void abrirNotas()
    {
        abrirVentanas("Guarderia/MenuNotas");
    } 
     
    
    @FXML
    void abrirMensualidad()
    {
        abrirVentanas("Docente/MenuPagosMensualidad");
    } 
    
    @FXML
    void abrirReporte()
    {
        abrirVentanas("Docente/MenuReporte");
    }
     @FXML
    void abrirConfig()
    {
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
    
    
    
    //-------------------------CAMBIO COLOR-----------------
    @FXML
    void cambiarColorPagos()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/pagos_blanco.png");
       
        btn_pagos.setStyle("-fx-background-color:" + " #0000ffff");
        img_pagos.setImage(img);
        btn_pagos.setTextFill(Paint.valueOf("white"));
    }
    
    //on mause exity
     @FXML
    void btonMatricula()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/pagos.png");
        
        btn_pagos.setStyle("-fx-background-color:white");
        img_pagos.setImage(img);
        btn_pagos.setTextFill(Paint.valueOf("BLACK"));
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
     
    @FXML
    void cambiarColorConfig()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/usuario_blanco.png");
       
        btn_config.setStyle("-fx-background-color:" + " #0000ffff");
        img_config.setImage(img);
        btn_config.setTextFill(Paint.valueOf("white"));
    }
    
    //on mause exity
     @FXML
    void btonConfig()
    {
        Image img;
        img = new Image("Imagenes/Guarderia/usuario.png");
        
        btn_config.setStyle("-fx-background-color:white");
        img_config.setImage(img);
        btn_config.setTextFill(Paint.valueOf("BLACK"));
    }
     
}

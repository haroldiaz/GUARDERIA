package Principal;

import Views.Docente.MenuDocenteController;
import Views.Guarderia.MenuMatriculasController;
import Views.Logins.LoginController;

import Views.PadreFamilia.PadreFamiliaMenuController;
import Views.Rector.MenuRectorController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public Stage primaryStage;
    private BorderPane rootLayout;
    Image icono = new Image("/Imagenes/Icono/icono.png");
    
    
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.getIcons().add(icono);
        primaryStage.setTitle("GUARDERIA");

        //-------LOGIN--------
        //LOGIN();
        //crearCuenta();

        //-------RECTOR
        RectorMenu();
      
        
        //-----DOCENTE---
        //DocenteMenu();
       
       
        //----PADRE FAMILIA-----
        ///PadreMenu();
       
        
    }

    public void LOGIN() {

        try {
            //Para cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Views/Logins/Login.fxml"));
            rootLayout = (BorderPane) loader.load();

            //Crear la escena
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();

            //Creamos el controlador de la ventana
            LoginController ventanaAbierta = loader.getController();
            ventanaAbierta.setVentanaPrincipal(this);

            primaryStage.show();
        } catch (IOException ex) {
            System.out.println("--ERROR Login-" + ex);
        }
    }


    //--------USUARIO ADMIN RECTOR--------
    public void RectorMenu() {

        try {
            //Para cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Views/Rector/MenuRector.fxml"));
            rootLayout = (BorderPane) loader.load();

            //Crear la escena
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();

            //Creamos el controlador de la ventana
            MenuRectorController ventanaAbierta = loader.getController();
            ventanaAbierta.setVentanaPrincipal(this);

            primaryStage.show();
        } catch (IOException ex)
        {
            System.out.println("--ERROR rector-" + ex);
        }
    }

    //--------USUARIO DOCENTE--------
    public void DocenteMenu() {

        try {
            //Para cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Views/Docente/MenuDocente.fxml"));
            rootLayout = (BorderPane) loader.load();

            //Crear la escena
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.centerOnScreen();

            //Creamos el controlador de la ventana
            MenuDocenteController ventanaAbierta = loader.getController();
            ventanaAbierta.setVentanaPrincipal(this);

            primaryStage.show();
        } catch (IOException ex) {
            System.out.println("--ERROR rector-" + ex);
        }
    }

    //--------USUARIO PADRE DE FAMILIA
    public void PadreMenu() 
    {

        try {
            //Para cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Views/PadreFamilia/PadreFamiliaMenu.fxml"));
            rootLayout = (BorderPane) loader.load();

            //Crear la escena
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();

            //Creamos el controlador de la ventana
            PadreFamiliaMenuController ventanaAbierta = loader.getController();
            ventanaAbierta.setVentanaPrincipal(this);

            primaryStage.show();
        } catch (IOException ex) {
            System.out.println("--ERROR USUARIO PADRE-" + ex);
        }
    }

  
  
    public static void main(String[] args) 
    {
        launch(args);
    }

}

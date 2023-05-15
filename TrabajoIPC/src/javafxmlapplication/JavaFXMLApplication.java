package javafxmlapplication;

import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class JavaFXMLApplication extends Application {
    
    private static Scene scene;
    private static HashMap<String, Parent> roots = new HashMap<>();
    private static HashMap<String, Object> controllers = new HashMap<>();
    
    public static void setRoot(Parent root){
        scene.setRoot(root);
        Stage st = (Stage) scene.getWindow();
    }
    
    public static void setRoot(String clave){
        Parent root = roots.get(clave);
        if(root != null){
            setRoot(root);
            
            Stage stage = (Stage) scene.getWindow() ;
            switch(clave){

            case "PaginaPrincipal":
                stage.setMinWidth(640);
                stage.setMinHeight(440);
                break;
            case "ReservarPistas":
                stage.setMinWidth(775);
                stage.setMinHeight(462);
                break;
            case  "MisReservas" :
                stage.setMinWidth(788);
                stage.setMinHeight(528);
                break;
            case  "PaginaInicio":
                stage.setMinWidth(817);
                stage.setMinHeight(658);
                break;
            case "PistaEspecifica":
                stage.setMinWidth(760);
                stage.setMinHeight(560);
                break;
            case "RegistroUsuario":
                stage.setMinWidth(922);
                stage.setMinHeight(760);
                break;
            case "IniciarSesion":
                stage.setMinWidth(700);
                stage.setMinHeight(550);
                break;
            }
        }else{
            System.err.printf("No se encuentra la escena: %s", clave);
        }
    }
    
    public static Object getController(String clave){
        Object controller = controllers.get(clave);
        if(controller != null){
            return controller;
        }else{
            System.err.printf("No se encuentra el controller: %s", clave);
            return null;
        }
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        //Colocamos las ventanas en el hashmap
        Parent root;
        FXMLLoader loader;
        
        loader = new FXMLLoader(getClass().getResource("PaginaPrincipal.fxml"));
        root = loader.load();
        roots.put("PaginaPrincipal", root);
        controllers.put("PaginaPrincipal", loader.getController());
        
        loader = new FXMLLoader(getClass().getResource("ReservarPistas.fxml"));
        root = loader.load();
        roots.put("ReservarPistas", root);
        controllers.put("ReservarPistas", loader.getController());
        
        loader = new FXMLLoader(getClass().getResource("MisReservas.fxml"));
        root = loader.load();
        roots.put("MisReservas", root);
        controllers.put("MisReservas", loader.getController());
        
        loader = new FXMLLoader(getClass().getResource("PaginaInicio.fxml"));
        root = loader.load();
        roots.put("PaginaInicio", root);
        controllers.put("PaginaInicio", loader.getController());
        
        loader = new FXMLLoader(getClass().getResource("RegistroUsuario.fxml"));
        root = loader.load();
        roots.put("RegistroUsuario", root);
        controllers.put("RegistroUsuario", loader.getController());
        
        
        loader = new FXMLLoader(getClass().getResource("CambioDatos.fxml"));
        root = loader.load();
        roots.put("CambioDatos", root);
        controllers.put("CambioDatos", loader.getController());
        
        loader = new FXMLLoader(getClass().getResource("IniciarSesion.fxml"));
        root = loader.load();
        roots.put("IniciarSesion", root);
        controllers.put("IniciarSesion", loader.getController());
        
        scene = new Scene(roots.get("PaginaInicio"));
        
        stage.setMinWidth(817);
        stage.setMinHeight(658);
        
        stage.setScene(scene);
        stage.setTitle("GreenBall");
        stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
        
    }   
}
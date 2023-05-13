
package javafxmlapplication;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import model.Club;


public class RegistroUsuario implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField tel;
    @FXML
    private TextField nick;
    @FXML
    private PasswordField pwd;
    @FXML
    private PasswordField pwd1;
    @FXML
    private ImageView foto;
    @FXML
    private TextField tar1;
    @FXML
    private TextField tar2;
    @FXML
    private TextField tar3;
    @FXML
    private TextField tar4;
    @FXML
    private TextField csv;
    @FXML
    private HBox userAlert;
    @FXML
    private HBox pwdAlert;
    @FXML
    private HBox pwd1Alert;
    @FXML
    private ImageView pwdAlertImage;
    @FXML
    private ImageView flechaIzq;
    @FXML
    private ImageView flechaDer;
    @FXML
    private HBox colliderFoto;
    
    private Image[] imageArray = new Image[12];
    int i;
    private Club greenBall = null;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Obtención de la instancia Club
        try{
            greenBall = Club.getInstance();
        }catch(Exception e){
            System.err.println(e.toString());
        }
        
        
        
        imageArray[0] = new Image("avatars/default.png", 140, 150, false, true);
        imageArray[1] = new Image("avatars/men.png", 140, 150, false, true);
        imageArray[2] = new Image("avatars/men2.png", 140, 150, false, true);
        imageArray[3] = new Image("avatars/men3.png", 140, 150, false, true);
        imageArray[4] = new Image("avatars/men4.png", 140, 150, false, true);
        imageArray[5] = new Image("avatars/men5.png", 140, 150, false, true);
        imageArray[6] = new Image("avatars/woman.png", 140, 150, false, true);
        imageArray[7] = new Image("avatars/woman2.png", 140, 150, false, true);
        imageArray[8] = new Image("avatars/woman3.png", 140, 150, false, true);
        imageArray[9] = new Image("avatars/woman4.png", 140, 150, false, true);
        imageArray[10] = new Image("avatars/woman5.png", 140, 150, false, true);
        imageArray[11] = new Image("avatars/woman6.png", 140, 150, false, true);
        
        //Este es el setImage que me da error
        foto.setImage(imageArray[0]);
        i = 0;
        if(foto == null){ System.out.println("Foto null");}
        
        //Esconder flechas
        flechaIzq.setVisible(false);
        flechaDer.setVisible(false);
        
        boolean antesFoto = false;
        
        foto.setOnMouseEntered(event -> {
            flechaIzq.setVisible(true);
            flechaDer.setVisible(true);
        });
        
        colliderFoto.setOnMouseExited(event -> {
            flechaIzq.setVisible(false);
            flechaDer.setVisible(false);
        });
        
        flechaDer.setOnMouseExited(event -> {
            flechaIzq.setVisible(false);
            flechaDer.setVisible(false);
        });
        
        flechaIzq.setOnMouseExited(event -> {
            flechaIzq.setVisible(false);
            flechaDer.setVisible(false);
        });
    }

    @FXML
    private void aceptar(){
        try{
            greenBall.registerMember(nombre.getText(), apellidos.getText(), tel.getText(), nick.getText(), pwd.getText(), tar1.getText() + tar2.getText() + tar3.getText() + tar4.getText(), Integer.parseInt(csv.getText()), foto.getImage());
            JavaFXMLApplication.setRoot("PaginaPrincipal");
        }catch(Exception e){
            
        }
        
    }

    @FXML
    private void cancelar() {
        JavaFXMLApplication.setRoot("IniciarSesion");
    }
    
    @FXML
    private void derImage(){
        i++;
        if(i >= 12){i = 0;}
        foto.setImage(imageArray[i]);
    }
    
    @FXML
    private void izqImage(){
        i--;
        if(i < 0){i = 11;}
        foto.setImage(imageArray[i]);
    }
    
    @FXML
    private void volver(){
        JavaFXMLApplication.setRoot("IniciarSesion");
    }
    
    @FXML
    private void buscarImage(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");
        
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null){
            Image image = null;
            try{
                image = new Image("file:" + selectedFile.getAbsolutePath(), 140, 150, false, true);
            }catch(Exception e){
                System.err.println(e.toString());
            }
            
            foto.setImage(image);
        }
        
    }
}
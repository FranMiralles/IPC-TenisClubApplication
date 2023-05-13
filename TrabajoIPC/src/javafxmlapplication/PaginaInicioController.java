/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import model.Club;
import model.Court;

public class PaginaInicioController implements Initializable {

    @FXML
    private Label NombrePista;
    @FXML
    private ImageView imagenPista;
    
    private Image[] imageArray = new Image[3];
    private String[] nombreArray = new String[6];
    int i;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //Obtención de la instancia Club
        Club greenBall = null;
        try{
            greenBall = Club.getInstance();
        }catch(Exception e){
            System.err.println(e.toString());
        }
        
        
        imageArray[0] = new Image("images/pista1.png");
        imageArray[1] = new Image("images/pista2.png");
        imageArray[2] = new Image("images/pista3.png");

        
        List<Court> listaPistas = greenBall.getCourts();
        for(int i = 0; i < listaPistas.size(); i++){
            nombreArray[i] = listaPistas.get(i).getName();
        }
        NombrePista.setText(nombreArray[0]);
        imagenPista.setImage(imageArray[0]);
        i = 0;
    }    
    
    @FXML
    private void derImage(){
        i++;
        if(i >= 6){ i = 0;}
        NombrePista.setText(nombreArray[i]);
        if(i > 2) {imagenPista.setImage(imageArray[i - 3]);}
        else{ imagenPista.setImage(imageArray[i]);}
        
    }
    
    @FXML
    private void izqImage(){
        i--;
        if(i < 0){ i = 5;}
        NombrePista.setText(nombreArray[i]);
        if(i > 2) {imagenPista.setImage(imageArray[i - 3]);}
        else{ imagenPista.setImage(imageArray[i]);}
    }
    
    @FXML
    private void iniciarSesion(){
        JavaFXMLApplication.setRoot("IniciarSesion");
    }
}
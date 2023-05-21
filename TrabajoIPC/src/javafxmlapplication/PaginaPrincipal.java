package javafxmlapplication;


import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import model.*;


public class PaginaPrincipal implements Initializable {

    @FXML
    private HBox userFeatures;
    @FXML
    private Label user;
    @FXML
    private ImageView perfil;
    @FXML
    private Label name;
    @FXML
    private Label cerrarSesion;
    @FXML
    private VBox bordeFoto;
    
    private Member member;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userFeatures.setVisible(false);
        
        user.setOnMouseEntered(event -> {
            user.setUnderline(true);
            user.setTextFill(Color.LIGHTBLUE);
            //user.setStyle("-fx-font-weight: bold");
        });

        user.setOnMouseExited(event -> {
            user.setUnderline(false);
            user.setTextFill(Color.WHITE);
            //user.setStyle("<font-weight>: regular");
        });
        
        perfil.setOnMouseEntered(event -> {
           bordeFoto.setStyle("-fx-background-color: lightblue");
        });
        
        perfil.setOnMouseExited(event -> {
            bordeFoto.setStyle("-fx-background-color: gray");
        });
    }
    
    @FXML
    private void activeToolBar(){
        if(userFeatures.isVisible()){
            userFeatures.setVisible(false);
            
        }else{
            userFeatures.setVisible(true);
        }
    }
    
    @FXML
    private void goMisReservas(){
        JavaFXMLApplication.setRoot("MisReservas");
        MisReservas controller = (MisReservas) JavaFXMLApplication.getController("MisReservas");
        controller.cambiarUser(member);
        controller.actualizarTabla();
    }
    
    @FXML
    private void goReservar(){
        JavaFXMLApplication.setRoot("ReservarPistas");
        ReservarPistas controller = (ReservarPistas) JavaFXMLApplication.getController("ReservarPistas");
        controller.cambioMember(member);
    }
    
    @FXML
    private void goInicio(){
        JavaFXMLApplication.setRoot("PaginaInicio");
        PaginaInicioController controller = (PaginaInicioController)JavaFXMLApplication.getController("PaginaInicio");
        controller.actualizarTabla();
    }
    
    public void cambiarUser(Member m){
        member = m;
        user.setText(member.getNickName());
        name.setText(member.getName());
        perfil.setImage(member.getImage());
        
    }

	public String passUser(){
        return member.getNickName();
    }

    @FXML
    private void goMisDatos(MouseEvent event) {
        JavaFXMLApplication.setRoot("CambioDatos");
        CambioDatos controller = (CambioDatos) JavaFXMLApplication.getController("CambioDatos");
        controller.cambiarUser(member);
    }
    
}
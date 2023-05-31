package javafxmlapplication;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    @FXML
    private Label reservaTitulo;
    @FXML
    private ImageView reservaFoto;
    @FXML
    private Label reservaTexto;
    @FXML
    private Label greenBallTitle;
    @FXML
    private ImageView greenBallFoto;
    @FXML
    private Label greenBallText;
    @FXML
    private Label misReservasTitle;
    @FXML
    private ImageView misReservasFoto;
    @FXML
    private Label misReservasText;
    @FXML
    private Button reservar;
    @FXML
    private Button empresa;
    @FXML
    private Button misReservas;
    
    private Member member;
    private Club greenBall;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userFeatures.setVisible(false);
        
        user.setOnMouseEntered(event -> {
            user.setUnderline(true);
            user.setStyle("-fx-text-fill: lightblue");
            //user.setStyle("-fx-font-weight: bold");
        });

        user.setOnMouseExited(event -> {
            user.setUnderline(false);
            user.setStyle("-fx-text-fill: white");
            //user.setStyle("<font-weight>: regular");
        });
        
        perfil.setOnMouseEntered(event -> {
           bordeFoto.setStyle("-fx-background-color: lightblue");
        });
        
        perfil.setOnMouseExited(event -> {
            bordeFoto.setStyle("-fx-background-color: gray");
        });
        
        //Cambio de colores de los botones
        Image reservaNegro = new Image("images/ReservarNegro.png");
        Image reservaBlanco = new Image("images/ReservarBlanco.png");
        Image verReservasNegro = new Image("images/VerReservasNegro.png");
        Image verReservasBlanco = new Image("images/VerReservasBlanco.png");
        Image greenBallNegro = new Image("images/logoNegro.png");
        Image greenBallBlanco = new Image("images/logoBlanco.png");
        
        reservar.setOnMouseEntered(c ->{
            reservaTitulo.setStyle("-fx-text-fill: #ffffff;");
            reservaFoto.setImage(reservaBlanco);
            reservaTexto.setStyle("-fx-text-fill: #ffffff;");
        });
        
        reservar.setOnMouseExited(c ->{
            reservaTitulo.setStyle("-fx-text-fill: #000000;");
            reservaFoto.setImage(reservaNegro);
            reservaTexto.setStyle("-fx-text-fill: #000000;");
        });
        
        misReservas.setOnMouseEntered(c ->{
            misReservasTitle.setStyle("-fx-text-fill: #ffffff;");
            misReservasFoto.setImage(verReservasBlanco);
            misReservasText.setStyle("-fx-text-fill: #ffffff;");
        });
        
        misReservas.setOnMouseExited(c ->{
            misReservasTitle.setStyle("-fx-text-fill: #000000;");
            misReservasFoto.setImage(verReservasNegro);
            misReservasText.setStyle("-fx-text-fill: #000000;");
        });
        
        empresa.setOnMouseEntered(c ->{
            greenBallTitle.setStyle("-fx-text-fill: #ffffff;");
            greenBallFoto.setImage(greenBallBlanco);
            greenBallText.setStyle("-fx-text-fill: #ffffff;");
        });
        
        empresa.setOnMouseExited(c ->{
            greenBallTitle.setStyle("-fx-text-fill: #000000;");
            greenBallFoto.setImage(greenBallNegro);
            greenBallText.setStyle("-fx-text-fill: #000000;");
        });
            
        empresa.setOnMouseClicked(c -> { empresa();});
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
        MisReservas controller = (MisReservas) JavaFXMLApplication.getController("MisReservas");
        controller.cambiarUser(member);
        controller.actualizarTabla();
        JavaFXMLApplication.setRoot("MisReservas");
    }
    
    @FXML
    private void goReservar(){
        JavaFXMLApplication.setRoot("ReservarPistas");
        ReservarPistas controller = (ReservarPistas) JavaFXMLApplication.getController("ReservarPistas");
        controller.cambioMember(member);
        controller.cambioTarjeta(member.getCreditCard());
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
        userFeatures.setVisible(false);
    }

	public String passUser(){
        return member.getNickName();
    }

    @FXML
    private void goMisDatos(MouseEvent event) {
        
        CambioDatos controller = (CambioDatos) JavaFXMLApplication.getController("CambioDatos");
        controller.cambiarUser(member);
        controller.setDestino("PaginaPrincipal");
        JavaFXMLApplication.setRoot("CambioDatos");
        controller.setFocus();
    }
    
    private void empresa(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sobre nosotros");
        alert.setHeaderText(null);
        alert.setContentText("Green Ball es el lugar perfecto para los amantes del tenis. Con 6 pistas emocionantes, incluyendo hierba, cemento, tierra batida, pádel, resina y una para los más pequeños, ofrecemos diversión para todos. Únete a nuestra comunidad y disfruta de la pasión por el tenis en un ambiente acogedor");
        
        ImageView imageView = new ImageView(new Image("images/BallLogo.png"));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        
        alert.getDialogPane().setGraphic(imageView);
        alert.setHeight(300);
        alert.setWidth(250);
        alert.getDialogPane().setStyle("-fx-background-color: #a4dc8c");
        alert.getDialogPane().getStylesheets().add("styles/EstilosFondo.css");
        alert.showAndWait();
    }
}
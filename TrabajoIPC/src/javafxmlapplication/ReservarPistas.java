package javafxmlapplication;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.*;


public class ReservarPistas implements Initializable {

    @FXML
    private Pane Pista_1;
    @FXML
    private Pane Pista_3;
    @FXML
    private Pane Pista_4;
    @FXML
    private Pane Pista_5;
    @FXML
    private Pane Pista_6;
    @FXML
    private Pane Pista_2;
    @FXML
    private BorderPane idReservarPista;
    Court pista;
    
    
    Club greenBall;
    Member member;
    
    List<Court> pistas = new ArrayList<Court>();
    
    Boolean paid;
    @FXML
    private VBox bordeFoto;
    @FXML
    private ImageView perfil;
    @FXML
    private Label user;
    @FXML
    private Label name;
    @FXML
    private HBox userFeatures;
    @FXML
    private Label cerrarSesion;

     
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try{
            greenBall = Club.getInstance();
            
        }catch(Exception e){
            System.err.println(e.toString());
        }
      
        
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
    private void cambio_1(MouseEvent event) {
            
        JavaFXMLApplication.setRoot("ReservarPistaEspecifica");
        ReservarPistaEspecifica  controller = (ReservarPistaEspecifica) JavaFXMLApplication.getController("ReservarPistaEspecifica");
        
        controller.cambioMember(member);
        pistas =greenBall.getCourts();
        
        
        pista = pistas.get(0);
        controller.CambioTitulo(pista.getName());
        controller.AsignarPista(pista);
        controller.cambioTexto("Una pista de tenis dura de cemento, con una superficie sólida y resistene, lineas bien definidas y un bote rápido y consistente. Perfecto para un juego de potencia y agresividad,"
                + " donde poder desplegar su fuerza en cada golpe. Esta pista es ideal tanto para partidos individuales como para encuentros en pareja.");
        controller.cambioTarjeta(paid);
        controller.cambioFoto(2);       
    }

    @FXML
    private void cambio_2(MouseEvent event) {
              
        JavaFXMLApplication.setRoot("ReservarPistaEspecifica");
        ReservarPistaEspecifica  controller = (ReservarPistaEspecifica) JavaFXMLApplication.getController("ReservarPistaEspecifica");
        
        controller.cambioMember(member);
         pistas =greenBall.getCourts();
        
        
        pista =pistas.get(1);
        controller.CambioTitulo(pista.getName());
        controller.AsignarPista(pista);
        controller.cambioTexto("Una pista de cesped natural con una superficie verde, exhuberante y suave. Perfecto para un juego clásico y técnico, donde los jugadores pueden disfrutar de la belleza y tradición sobre cesped."
                + " Ofrece un bote bajo y deslizante, lo que requiere un juego preciso y único.");
        controller.cambioTarjeta(paid);
        controller.cambioFoto(1);
        
    }

    
    @FXML
    private void cambio_3(MouseEvent event) {
        
        JavaFXMLApplication.setRoot("ReservarPistaEspecifica");
        ReservarPistaEspecifica  controller = (ReservarPistaEspecifica) JavaFXMLApplication.getController("ReservarPistaEspecifica");
        
        controller.cambioMember(member);
         pistas =greenBall.getCourts();
        
        
        pista =pistas.get(2);
        controller.CambioTitulo(pista.getName());
        controller.AsignarPista(pista);
        controller.cambioTexto("Una pista de tenis diseñada especialmente para los niños, con una superficie suave y segura. Perfecto para un juego donde los niños puedan aprender las técnicas básicas de tenis mientras se divierten."
                + " La pista está adapatada a su tamaño y capacidad, fomentando el desarrollo de habilidades motoras y promoviendo un ambiento de juego inclusivo y estimulante.");
        controller.cambioTarjeta(paid);
        controller.cambioFoto(5);
    }

    @FXML
    private void cambio_4(MouseEvent event) {
        JavaFXMLApplication.setRoot("ReservarPistaEspecifica");
        ReservarPistaEspecifica  controller = (ReservarPistaEspecifica) JavaFXMLApplication.getController("ReservarPistaEspecifica");
        
        controller.cambioMember(member);
         pistas =greenBall.getCourts();
        
        
        pista =pistas.get(3);
        controller.CambioTitulo(pista.getName());
        controller.AsignarPista(pista);
        controller.cambioTexto("Una pista de pádel profesional, con una superficie de césped artificial de alta calidad, líneas bien marcadas y un bote rápido y dinámico. Donde los jugadores pueden aprovechar las paredes para realizar golpes de efecto."
                + " Además, cuenta con una estructura cerrada que favorece el juego en equipo y la comunicación entre los jugadores.");
        controller.cambioTarjeta(paid);
        controller.cambioFoto(6);
    }

    @FXML
    private void cambio_5(MouseEvent event) {
       JavaFXMLApplication.setRoot("ReservarPistaEspecifica");
        ReservarPistaEspecifica  controller = (ReservarPistaEspecifica) JavaFXMLApplication.getController("ReservarPistaEspecifica");
        
        controller.cambioMember(member);
         pistas =greenBall.getCourts();
        
        
        pista =pistas.get(4);
        controller.CambioTitulo(pista.getName());
        controller.AsignarPista(pista);
        controller.cambioTexto("Una pista de tenis con una superficie de resina compacta y de longitud moderada, lineas nítidas y bote firme. Perfecto para un juego en el que poder tomar decisiones rápidas en cada jugada."
                + " La superficie de resina ofrece buena tracción, lo que permite una excelente respuesta y control de movimientos.");
        controller.cambioTarjeta(paid);
        controller.cambioFoto(4);
    }

    @FXML
    private void cambio_6(MouseEvent event) {
      JavaFXMLApplication.setRoot("ReservarPistaEspecifica");
        ReservarPistaEspecifica  controller = (ReservarPistaEspecifica) JavaFXMLApplication.getController("ReservarPistaEspecifica");
        
        controller.cambioMember(member);
         pistas =greenBall.getCourts();
        
        
        pista =pistas.get(5);
        controller.CambioTitulo(pista.getName());
        controller.AsignarPista(pista);
        controller.cambioTexto("Una pista de tenis profesional, con una suave superficie de arcilla roja, líneas bien definidas y un bote controlado. Perfecto para juego estratégico y táctico, "
                + " donde los jugadores puedan deslizarse con facilidad y desplegar su habilidad en cada golpe.");
        controller.cambioTarjeta(paid);
        controller.cambioFoto(3);
    }

    @FXML
    private void normal_1(MouseEvent event) {
        
        Pista_1.getStyleClass().remove("grid-cell");
        Pista_1.getStyleClass().remove("hover");
    }

    @FXML
    private void movimiento_1(MouseEvent event) {
        
        Pista_1.getStyleClass().add("grid-cell");
        Pista_1.getStyleClass().add("hover");
    }

    @FXML
    private void normal_3(MouseEvent event) {
         Pista_3.getStyleClass().remove("grid-cell");
        Pista_3.getStyleClass().remove("hover");
    }

    @FXML
    private void movimiento_3(MouseEvent event) {
       Pista_3.getStyleClass().add("grid-cell");
        Pista_3.getStyleClass().add("hover");
    }

    @FXML
    private void normal_4(MouseEvent event) {
         Pista_4.getStyleClass().remove("grid-cell");
        Pista_4.getStyleClass().remove("hover");
    }

    @FXML
    private void movimiento_4(MouseEvent event) {
        Pista_4.getStyleClass().add("grid-cell");
        Pista_4.getStyleClass().add("hover");
    }

    @FXML
    private void normal_5(MouseEvent event) {
        Pista_5.getStyleClass().remove("grid-cell");
        Pista_5.getStyleClass().remove("hover");
    }

    @FXML
    private void movimiento_5(MouseEvent event) {
       Pista_5.getStyleClass().add("grid-cell");
        Pista_5.getStyleClass().add("hover");
    }

    @FXML
    private void normal_6(MouseEvent event) {
         Pista_6.getStyleClass().remove("grid-cell");
        Pista_6.getStyleClass().remove("hover");
    }

    @FXML
    private void movimiento_6(MouseEvent event) {
        Pista_6.getStyleClass().add("grid-cell");
        Pista_6.getStyleClass().add("hover");
    }

    @FXML
    private void normal_2(MouseEvent event) {
        Pista_2.getStyleClass().remove("grid-cell");
        Pista_2.getStyleClass().remove("hover");
    }

    @FXML
    private void movimiento_2(MouseEvent event) {
      Pista_2.getStyleClass().add("grid-cell");
      Pista_2.getStyleClass().add("hover");
    }

    
    public void cambioMember(Member m){
        member = m;
        user.setText(member.getNickName());
        name.setText(member.getName());
        perfil.setImage(member.getImage());
        userFeatures.setVisible(false);
    }
    
     public void cambioTarjeta(String p){
        
       if( p.equals("")){
           paid = false; //no hay tarjeta
       }
       else{
           paid = true;  // si hay tarjeta
       } 
       
       
       
       
    }

    @FXML
    private void Volver_buton(ActionEvent event) {
         JavaFXMLApplication.setRoot("PaginaPrincipal");
        PaginaPrincipal controller = (PaginaPrincipal) JavaFXMLApplication.getController("PaginaPrincipal");
        
        
        
    }

    @FXML
    private void goMisDatos(MouseEvent event) {
        CambioDatos controller = (CambioDatos) JavaFXMLApplication.getController("CambioDatos");
        controller.cambiarUser(member);
        controller.setDestino("ReservarPistas");
        JavaFXMLApplication.setRoot("CambioDatos");
        controller.setFocus();
    }

    @FXML
    private void goInicio(MouseEvent event) {
        JavaFXMLApplication.setRoot("PaginaInicio");
        PaginaInicioController controller = (PaginaInicioController)JavaFXMLApplication.getController("PaginaInicio");
        controller.actualizarTabla();
    }
    
    
}

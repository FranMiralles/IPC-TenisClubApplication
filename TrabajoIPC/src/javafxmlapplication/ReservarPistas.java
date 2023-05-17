package javafxmlapplication;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.*;
/**
 * FXML Controller class
 *
 * @author raulh
 */
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
    
    
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        try{
            greenBall = Club.getInstance();
            
        }catch(Exception e){
            System.err.println(e.toString());
        }
      
    }    
    
    @FXML
    private void cambio_1(MouseEvent event) {
        
        JavaFXMLApplication.setRoot("ReservarPistaEspecifica");
        ReservarPistaEspecifica  controller = (ReservarPistaEspecifica) JavaFXMLApplication.getController("ReservarPistaEspecifica");
        
        controller.cambioMember(member);
         pistas =greenBall.getCourts();
        for(int i = 0; i < pistas.size() ; i++){
            System.out.println(pistas.get(i).getName());
        }
        
        pista =pistas.get(0);
        controller.CambioTitulo(pista.getName());
        controller.AsignarPista(pista);
        
    }

    @FXML
    private void cambio_2(MouseEvent event) {
        
        JavaFXMLApplication.setRoot("ReservarPistaEspecifica");
        ReservarPistaEspecifica  controller = (ReservarPistaEspecifica) JavaFXMLApplication.getController("ReservarPistaEspecifica");
        
        controller.cambioMember(member);
         pistas =greenBall.getCourts();
        for(int i = 0; i < pistas.size() ; i++){
            System.out.println(pistas.get(i).getName());
        }
        
        pista =pistas.get(1);
        controller.CambioTitulo(pista.getName());
        controller.AsignarPista(pista);
        
    }

    
    @FXML
    private void cambio_3(MouseEvent event) {
        JavaFXMLApplication.setRoot("ReservarPistaEspecifica");
        ReservarPistaEspecifica  controller = (ReservarPistaEspecifica) JavaFXMLApplication.getController("ReservarPistaEspecifica");
        
        controller.cambioMember(member);
         pistas =greenBall.getCourts();
        for(int i = 0; i < pistas.size() ; i++){
            System.out.println(pistas.get(i).getName());
        }
        
        pista =pistas.get(2);
        controller.CambioTitulo(pista.getName());
        controller.AsignarPista(pista);
    }

    @FXML
    private void cambio_4(MouseEvent event) {
        JavaFXMLApplication.setRoot("ReservarPistaEspecifica");
        ReservarPistaEspecifica  controller = (ReservarPistaEspecifica) JavaFXMLApplication.getController("ReservarPistaEspecifica");
        
        controller.cambioMember(member);
         pistas =greenBall.getCourts();
        for(int i = 0; i < pistas.size() ; i++){
            System.out.println(pistas.get(i).getName());
        }
        
        pista =pistas.get(3);
        controller.CambioTitulo(pista.getName());
        controller.AsignarPista(pista);
    }

    @FXML
    private void cambio_5(MouseEvent event) {
       JavaFXMLApplication.setRoot("ReservarPistaEspecifica");
        ReservarPistaEspecifica  controller = (ReservarPistaEspecifica) JavaFXMLApplication.getController("ReservarPistaEspecifica");
        
        controller.cambioMember(member);
         pistas =greenBall.getCourts();
        for(int i = 0; i < pistas.size() ; i++){
            System.out.println(pistas.get(i).getName());
        }
        
        pista =pistas.get(4);
        controller.CambioTitulo(pista.getName());
        controller.AsignarPista(pista);
    }

    @FXML
    private void cambio_6(MouseEvent event) {
        JavaFXMLApplication.setRoot("ReservarPistaEspecifica");
        ReservarPistaEspecifica  controller = (ReservarPistaEspecifica) JavaFXMLApplication.getController("ReservarPistaEspecifica");
        
        controller.cambioMember(member);
         pistas =greenBall.getCourts();
        for(int i = 0; i < pistas.size() ; i++){
            System.out.println(pistas.get(i).getName());
        }
        
        pista =pistas.get(5);
        controller.CambioTitulo(pista.getName());
        controller.AsignarPista(pista);
        
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
    }

    @FXML
    private void cambio_1(TouchEvent event) {
    }
    
    
    
}
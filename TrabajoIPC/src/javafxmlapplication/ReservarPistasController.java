/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author raulh
 */
public class ReservarPistasController implements Initializable {

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

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }    



    @FXML
    private void cambio_1(MouseEvent event) {
        JavaFXMLApplication.setRoot("PistaEspecifica");
    }

    @FXML
    private void cambio_3(MouseEvent event) {
        JavaFXMLApplication.setRoot("PistaEspecifica");
    }

    @FXML
    private void cambio_4(MouseEvent event) {
        JavaFXMLApplication.setRoot("PistaEspecifica");
    }

    @FXML
    private void cambio_5(MouseEvent event) {
        JavaFXMLApplication.setRoot("PistaEspecifica");
    }

    @FXML
    private void cambio_6(MouseEvent event) {
        JavaFXMLApplication.setRoot("PistaEspecifica");
    }

    @FXML
    private void cambio_2(MouseEvent event) {
        JavaFXMLApplication.setRoot("PistaEspecifica");
    }

    @FXML
    private void cambio_1(TouchEvent event) {
    }
    
}
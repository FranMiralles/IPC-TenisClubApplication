/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.util.ArrayList;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


public class MisReservas implements Initializable {

    @FXML
    private TableColumn<Booking, LocalDate> FechaColum;
    @FXML
    private TableColumn<Booking, Integer> PistaColum;
    @FXML
    private TableColumn<Booking, LocalDate> EntradaColum;
    @FXML
    private TableColumn<Booking, LocalDate> SalidaColum;
    @FXML
    private TableColumn<Booking, String> PagadaColum;
    @FXML
    private Button AnularBoton;
    @FXML
    private Button ReservarBoton;
    @FXML
    private TableView<Booking> tablaReservas;

    
    public static ObservableList<Booking> misReservas;
    @FXML
    private BorderPane idMisReservas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Booking> reservas = new ArrayList<>();
        //Club club = Club.getInstance();
            
        ArrayList<Booking> reservasUsuario = new ArrayList<>();
            
        //reservas = club.getUserBookings(login);
        misReservas = FXCollections.observableList(reservas);
        FechaColum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        PistaColum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        EntradaColum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        SalidaColum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        PagadaColum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        tablaReservas.setItems(misReservas);
        
        AnularBoton.disableProperty().bind(Bindings.equal(tablaReservas.getSelectionModel().selectedIndexProperty(), -1));
    
    }
    @FXML
    private void AnularReserva_Accion(ActionEvent event) {
        
    }

    @FXML
    private void Reservar_Accion(ActionEvent event) {
        JavaFXMLApplication.setRoot("ReservarPistas");
    }
    
}
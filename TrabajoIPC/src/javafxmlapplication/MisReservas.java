package javafxmlapplication;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


public class MisReservas implements Initializable {

    @FXML
    private TableColumn<Reserva, String> pista;
    @FXML
    private TableColumn<Reserva, String> dia;
    @FXML
    private TableColumn<Reserva, String> hIni;
    @FXML
    private TableColumn<Reserva, String> hFin;
    @FXML
    private TableColumn<Reserva, String> pagado;
    @FXML
    private TableView<Reserva> tabla;

    private Member member;
    private Club greenBall;
    ObservableList<Reserva> bookingList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            greenBall = Club.getInstance();
        }catch(Exception e){
            System.err.println(e.toString());
        }
        
        bookingList = FXCollections.observableArrayList();
        
        pista.setCellValueFactory(new PropertyValueFactory<>("pista"));
        dia.setCellValueFactory(new PropertyValueFactory<>("dia"));
        hIni.setCellValueFactory(new PropertyValueFactory<>("horaIni"));
        hFin.setCellValueFactory(new PropertyValueFactory<>("horaFin"));
        pagado.setCellValueFactory(new PropertyValueFactory<>("pagado"));
        
        //Principio organizar al centro las columnas
        pista.setCellFactory(column -> {
            TableCell<Reserva, String> cell = new TableCell<Reserva, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
        
        dia.setCellFactory(column -> {
            TableCell<Reserva, String> cell = new TableCell<Reserva, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
        
        hIni.setCellFactory(column -> {
            TableCell<Reserva, String> cell = new TableCell<Reserva, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
        
        hFin.setCellFactory(column -> {
            TableCell<Reserva, String> cell = new TableCell<Reserva, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
        
        pagado.setCellFactory(column -> {
            TableCell<Reserva, String> cell = new TableCell<Reserva, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
    }
    
    
    public void cambiarUser(Member m){
        this.member = m;
    }
    
    public void actualizarTabla(){
        List<Booking> lista = greenBall.getUserBookings(member.getNickName());
        for(int i = 0; i < lista.size(); i++){
            Reserva reserva = new Reserva(lista.get(i));
            bookingList.add(reserva);
        }
        tabla.setItems(bookingList);
        tabla.refresh();
    }
    
    @FXML
    private void eliminar(){
        Reserva reserva = tabla.getSelectionModel().getSelectedItem();
        bookingList.remove(reserva);
        try{
            greenBall.removeBooking(reserva.getBooking());
        }catch(Exception e){
            
        }
        tabla.refresh();
    }
    
}
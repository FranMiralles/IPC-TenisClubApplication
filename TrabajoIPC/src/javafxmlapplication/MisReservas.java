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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        pagado.setCellFactory( c -> new ImagenTabCell());
        
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
            ImagenTabCell cell = new ImagenTabCell() {
                
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
        
        tabla.setRowFactory(row -> new TableRow<Reserva>() {
            @Override
            protected void updateItem(Reserva item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null && !empty) {
                    // Aquí puedes personalizar el estilo de la fila según tus requisitos
                    if (item.getColored()) {
                        setStyle("-fx-background-color: #74a464;");  // Cambiar el color de fondo a verde
                    } else {
                        setStyle("-fx-background-color: #e40606;");  // Cambiar el color de fondo a rojo
                    }
                } else {
                    setStyle("");  // Restablecer el estilo predeterminado de la fila
                }
            }
        });
    }
    
    
    public void cambiarUser(Member m){
        this.member = m;
    }
    
    public void actualizarTabla(){
        for(int i = 0; i < bookingList.size(); i++){
            bookingList.remove(i);
        }
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
    
    @FXML
    public void volver(){
        JavaFXMLApplication.setRoot("PaginaPrincipal");
    }
    
    @FXML
    private void pagar(){
        Reserva reserva = tabla.getSelectionModel().getSelectedItem();
        if(reserva.getBooking().getPaid()){
            //Avisar de que ya está pagado
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setContentText("Ya está pagado");
            alert.showAndWait();
        }else{
            if(member.checkHasCreditInfo()){
                //Cambiar a pagado
                System.out.println("Tienes tarjeta de credito");
                reserva.ColoredProperty().setValue(true);
                reserva.getBooking().setPaid(true);
                reserva.PagadoProperty().setValue("images/accept_white.png");
                tabla.refresh();
            }else{
                //Avisar de introducir tarjeta en los datos
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("");
                alert.setContentText("No tienes tarjeta de crédito, indícala");
                alert.showAndWait();
            }
        }
    }
}

//Clase para mostrar imagen en celda en vez de ruta
    class ImagenTabCell extends TableCell<Reserva, String>{
        private ImageView view = new ImageView();
        private Image imagen;
        
        @Override
        protected void updateItem(String t, boolean bln){
            super.updateItem(t, bln);
            if(t == null || bln){
                setText(null);
                setGraphic(null);
            }else{
                imagen = new Image(t, 15, 15, false, true);
                view.setImage(imagen);
                setGraphic(view);
            }
        }
    }
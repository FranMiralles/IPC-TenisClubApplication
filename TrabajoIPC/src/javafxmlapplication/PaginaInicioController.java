package javafxmlapplication;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import model.*;

public class PaginaInicioController implements Initializable {

    @FXML
    private ImageView imagenPista;
    @FXML
    private Label nombrePista;
    @FXML
    private Label descripcion;
    @FXML
    private DatePicker fecha;
    @FXML
    private TableView<Item> tabla;
    @FXML
    private TableColumn<Item, String> columnaIni;
    @FXML
    private TableColumn<Item, String> columnaFin;
    @FXML
    private TableColumn<Item, String> columnaUsuario;
    @FXML
    private TableColumn<Item, String> columnaEstado;
    @FXML
    private ImageView izqFlecha;
    @FXML
    private ImageView derFlecha;
    
    private Image[] imageArray = new Image[6];
    private String[] nombreArray = new String[6];
    private String[] descripcionPista = new String[6];
    int i;
    Club greenBall = null;
    ObservableList<Item> bookingList;
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //Obtención de la instancia Club
        
        try{
            greenBall = Club.getInstance();
        }catch(Exception e){
            System.err.println(e.toString());
        }
        
        //Script que elimine todas las reservas cuyo tiempo ya ha pasado desde que se abre la aplicación
        try{
            ArrayList<Booking> allBookings = greenBall.getBookings();
            for(int i = 0; i < allBookings.size(); i++){
                Booking reserva = allBookings.get(i);
                if(reserva.getMadeForDay().compareTo(LocalDate.now()) < 0){
                    greenBall.removeBooking(reserva);
                }else if(reserva.getMadeForDay().compareTo(LocalDate.now()) == 0){
                    if(reserva.getFromTime().compareTo(LocalTime.now()) < 0){
                        greenBall.removeBooking(reserva);
                    }
                }
            }
        }catch(Exception e){
            System.err.println(e.toString());
        }
        
        
        
        //Parte de la selección de pistas
        imageArray[0] = new Image("images/pista1.png", 250, 150, false, true);
        imageArray[1] = new Image("images/pista2.png", 250, 150, false, true);
        imageArray[2] = new Image("images/pista3.png", 250, 150, false, true);
        imageArray[3] = new Image("images/pista1.png", 250, 150, false, true);
        imageArray[4] = new Image("images/pista2.png", 250, 150, false, true);
        imageArray[5] = new Image("images/pista3.png", 250, 150, false, true);

        
        List<Court> listaPistas = greenBall.getCourts();
        for(int i = 0; i < listaPistas.size(); i++){
            nombreArray[i] = listaPistas.get(i).getName();
        }
        
        
        descripcionPista[0] = "Pista de tierra batida, preparada para amateurs que quieran tener una experiencia cercana a un campo realista de torneo 1";
        descripcionPista[1] = "Pista de tierra batida, preparada para amateurs que quieran tener una experiencia cercana a un campo realista de torneo 2";
        descripcionPista[2] = "Pista de tierra batida, preparada para amateurs que quieran tener una experiencia cercana a un campo realista de torneo 3";
        descripcionPista[3] = "Pista de tierra batida, preparada para amateurs que quieran tener una experiencia cercana a un campo realista de torneo 4";
        descripcionPista[4] = "Pista de tierra batida, preparada para amateurs que quieran tener una experiencia cercana a un campo realista de torneo 5";
        descripcionPista[5] = "Pista de tierra batida, preparada para amateurs que quieran tener una experiencia cercana a un campo realista de torneo 6";
        
        nombrePista.setText(nombreArray[0]);
        imagenPista.setImage(imageArray[0]);
        descripcion.setText(descripcionPista[0]);
        i = 0;
        //Fin parte de la selección de pistas
        
        //Parte de las celdas de la tabla
        columnaIni.setCellValueFactory(new PropertyValueFactory<>("horaIni"));
        columnaFin.setCellValueFactory(new PropertyValueFactory<>("horaFin"));
        columnaUsuario.setCellValueFactory(new PropertyValueFactory<>("nick"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estadoPath"));
        columnaEstado.setCellFactory( c -> new ImagenTabCell());
        
        //Principio organizar al centro las columnas
        columnaIni.setCellFactory(column -> {
            TableCell<Item, String> cell = new TableCell<Item, String>() {
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
        
        columnaFin.setCellFactory(column -> {
            TableCell<Item, String> cell = new TableCell<Item, String>() {
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
        
        columnaUsuario.setCellFactory(column -> {
            TableCell<Item, String> cell = new TableCell<Item, String>() {
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
        
        columnaEstado.setCellFactory(column -> {
            ImagenTabCell cell = new ImagenTabCell() {
                
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
        
        //Fin organizar al centro las columnas
        
        tabla.setRowFactory(row -> new TableRow<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
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
        
        
        bookingList = FXCollections.observableArrayList();
        bookingList.add(new Item("09:00", "10:00", "-", "images/accept_white.png", 9));
        bookingList.add(new Item("10:00", "11:00", "-", "images/accept_white.png", 10));
        bookingList.add(new Item("11:00", "12:00", "-", "images/accept_white.png", 11));
        bookingList.add(new Item("12:00", "13:00", "-", "images/accept_white.png", 12));
        bookingList.add(new Item("13:00", "14:00", "-", "images/accept_white.png", 13));
        bookingList.add(new Item("14:00", "15:00", "-", "images/accept_white.png", 14));
        bookingList.add(new Item("15:00", "16:00", "-", "images/accept_white.png", 15));
        bookingList.add(new Item("16:00", "17:00", "-", "images/accept_white.png", 16));
        bookingList.add(new Item("17:00", "18:00", "-", "images/accept_white.png", 17));
        bookingList.add(new Item("18:00", "19:00", "-", "images/accept_white.png", 18));
        bookingList.add(new Item("19:00", "20:00", "-", "images/accept_white.png", 19));
        bookingList.add(new Item("20:00", "21:00", "-", "images/accept_white.png", 20));
        bookingList.add(new Item("21:00", "22:00", "-", "images/accept_white.png", 21));
        tabla.setItems(bookingList);
        
        //Actualizar la tabla por si el día de hoy han habido reservas
        for(int i = 0; i < bookingList.size(); i++){
            Item item = bookingList.get(i);
            item.setNick("-");
            item.setEstadoPath("images/accept_white.png");
            item.setColored(true);
        }
        List<Booking> listaHoy = greenBall.getCourtBookings(nombrePista.getText(), LocalDate.now());
        for(int i = 0; i < listaHoy.size(); i++){
            Booking reserva = listaHoy.get(i);
            int hora = reserva.getFromTime().getHour() - 9; //Así encontramos donde está en la lista de booking => hora 10 - 9 = celda 1
            Item item = bookingList.get(hora);
            item.setNick(reserva.getMember().getNickName());
            item.setEstadoPath("images/cancel_white.png");
            item.setColored(false);
        }
        tabla.refresh();
        
        //Listener para el datePicker
        fecha.setValue(LocalDate.now());
        fecha.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                for(int i = 0; i < bookingList.size(); i++){
                    Item item = bookingList.get(i);
                    item.setNick("-");
                    item.setEstadoPath("images/accept_white.png");
                    item.setColored(true);
                }
                
                
                LocalDate selectedDate = newValue;
                List<Booking> lista = greenBall.getCourtBookings(nombrePista.getText(), selectedDate);
                for(int i = 0; i < lista.size(); i++){
                    Booking reserva = lista.get(i);
                    int hora = reserva.getFromTime().getHour() - 9; //Así encontramos donde está en la lista de booking => hora 10 - 9 = celda 1
                    Item item = bookingList.get(hora);
                    item.setNick(reserva.getMember().getNickName());
                    item.setEstadoPath("images/cancel_white.png");
                    item.setColored(false);
                }
                tabla.refresh();
            }
        });
        
        //Agrandar flechas si estás encima
        derFlecha.setOnMouseEntered(event -> {
            derFlecha.setFitHeight(40); derFlecha.setFitWidth(40);
            izqFlecha.setFitHeight(40); izqFlecha.setFitWidth(40);
        });
        derFlecha.setOnMouseExited(event -> {
            derFlecha.setFitHeight(35); derFlecha.setFitWidth(35);
            izqFlecha.setFitHeight(35); izqFlecha.setFitWidth(35);
        });
        izqFlecha.setOnMouseEntered(event -> {
            derFlecha.setFitHeight(40); derFlecha.setFitWidth(40);
            izqFlecha.setFitHeight(40); izqFlecha.setFitWidth(40);
        });
        izqFlecha.setOnMouseExited(event -> {
            derFlecha.setFitHeight(35); derFlecha.setFitWidth(35);
            izqFlecha.setFitHeight(35); izqFlecha.setFitWidth(35);
        });
        
        //Pruebas de testing => ya introducida
        /*
        try{
            LocalDate ld = LocalDate.of(2023, 5, 15);
            LocalDateTime lct = LocalDateTime.of(2023, 5, 15, 16, 0, 0);
            LocalTime lt = LocalTime.of(16, 0);
            greenBall.registerBooking(lct, ld, lt, true, listaPistas.get(0), greenBall.getMemberByCredentials("hola", "hola"));
            System.out.println(greenBall.getBookings().size());
        }catch(Exception e){
            
        }*/
        
        
    }    
    
    @FXML
    private void derImage(){
        i++;
        if(i >= 6){ i = 0;}
        nombrePista.setText(nombreArray[i]);
        imagenPista.setImage(imageArray[i]);
        descripcion.setText(descripcionPista[i]);
        
        actualizarTabla();
    }
    
    @FXML
    private void izqImage(){
        i--;
        if(i < 0){ i = 5;}
        nombrePista.setText(nombreArray[i]);
        imagenPista.setImage(imageArray[i]);
        
        actualizarTabla();
    }
    
    private void actualizarTabla(){
        //Actualizar tabla
        for(int i = 0; i < bookingList.size(); i++){
            Item item = bookingList.get(i);
            item.setNick("-");
            item.setEstadoPath("images/accept_white.png");
            item.setColored(true);
        }


        LocalDate selectedDate = fecha.getValue();
        List<Booking> lista = greenBall.getCourtBookings(nombrePista.getText(), selectedDate);
        for(int i = 0; i < lista.size(); i++){
            Booking reserva = lista.get(i);
            int hora = reserva.getFromTime().getHour() - 9; //Así encontramos donde está en la lista de booking => hora 10 - 9 = celda 1
            Item item = bookingList.get(hora);
            item.setNick(reserva.getMember().getNickName());
            item.setEstadoPath("images/cancel_white.png");
            item.setColored(false);
        }
        tabla.refresh();
    }
    
    @FXML
    private void iniciarSesion(){
        JavaFXMLApplication.setRoot("IniciarSesion");
    }
    
    @FXML
    private void registrarse(){
        JavaFXMLApplication.setRoot("RegistroUsuario");
    }
}

    //Clase para mostrar imagen en celda en vez de ruta
    class ImagenTabCell extends TableCell<Item, String>{
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

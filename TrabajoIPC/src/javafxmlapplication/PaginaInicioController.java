package javafxmlapplication;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

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
    @FXML
    private Label iniSesion;
    @FXML
    private Label regist;
    
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
        
        iniSesion.setOnMouseEntered(event -> {
            iniSesion.setTextFill(Color.LIGHTBLUE);
            //iniSesion.setStyle("-fx-font-weight: bold");
        });

        iniSesion.setOnMouseExited(event -> {
            iniSesion.setTextFill(Color.WHITE);
            //iniSesion.setStyle("<font-weight>: regular");
        });
        
        regist.setOnMouseEntered(event -> {
            regist.setUnderline(true);
            regist.setTextFill(Color.LIGHTBLUE);
            //regist.setStyle("-fx-font-weight: bold");
        });

        regist.setOnMouseExited(event -> {
            regist.setUnderline(false);
            regist.setTextFill(Color.WHITE);
            //regist.setStyle("<font-weight>: regular");
        });
        
        //Script que elimine todas las reservas cuyo tiempo ya ha pasado desde que se abre la aplicación
        
        try{
            ArrayList<Booking> allBookings = greenBall.getBookings();
            for(int i = 0; i < allBookings.size(); i++){
                Booking reserva = allBookings.get(i);
                if(reserva.getMadeForDay().compareTo(LocalDate.now()) < 0){
                    greenBall.removeBooking(reserva);
                }
            }
        }catch(Exception e){
            System.err.println(e.toString());
        }
        
        
        
        //Parte de la selección de pistas
        imageArray[0] = new Image("images/pista2.png", 250, 150, false, true);
        imageArray[1] = new Image("images/pista1.png", 250, 150, false, true);
        imageArray[2] = new Image("images/pista5.png", 250, 150, false, true);
        imageArray[3] = new Image("images/pista6.png", 250, 150, false, true);
        imageArray[4] = new Image("images/pista4.png", 250, 150, false, true);
        imageArray[5] = new Image("images/pista3.png", 250, 150, false, true);
        
        List<Court> listaPistas = greenBall.getCourts();

        for(int i = 0; i < listaPistas.size(); i++){
            nombreArray[i] = listaPistas.get(i).getName();
        }
        
        descripcionPista[0] = "Una pista de tenis dura de cemento, con una superficie sólida y resistene, lineas bien definidas y un bote rápido y consistente. Perfecto para un juego de potencia y agresividad, donde poder desplegar su fuerza en cada golpe. Esta pista es ideal tanto para partidos individuales como para encuentros en pareja.";
        descripcionPista[1] = "Una pista de cesped natural con una superficie verde, exhuberante y suave. Perfecto para un juego clásico y técnico, donde los jugadores pueden disfrutar de la belleza y tradición sobre cesped. Ofrece un bote bajo y deslizante, lo que requiere un juego preciso y único.";
        descripcionPista[2] = "Una pista de tenis diseñada especialmente para los niños, con una superficie suave y segura. Perfecto para un juego donde los niños puedan aprender las técnicas básicas de tenis mientras se divierten. La pista está adapatada a su tamaño y capacidad, fomentando el desarrollo de habilidades motoras y promoviendo un ambiento de juego inclusivo y estimulante.";
        descripcionPista[3] = "Una pista de pádel profesional, con una superficie de césped artificial de alta calidad, líneas bien marcadas y un bote rápido y dinámico. Donde los jugadores pueden aprovechar las paredes para realizar golpes de efecto. Además, cuenta con una estructura cerrada que favorece el juego en equipo y la comunicación entre los jugadores.";
        descripcionPista[4] = "Una pista de tenis con una superficie de resina compacta y de longitud moderada, lineas nítidas y bote firme. Perfecto para un juego en el que poder tomar decisiones rápidas en cada jugada. La superficie de resina ofrece buena tracción, lo que permite una excelente respuesta y control de movimientos.";
        descripcionPista[5] = "Una pista de tenis profesional, con una suave superficie de arcilla roja, líneas bien definidas y un bote controlado. Perfecto para juego estratégico y táctico, donde los jugadores puedan deslizarse con facilidad y desplegar su habilidad en cada golpe.";
        
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
        
        //Organizar al centro las columnas
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
        
        
        
        tabla.setRowFactory(row -> new TableRow<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null && !empty) {
                    // Aquí puedes personalizar el estilo de la fila según tus requisitos
                    if (item.getColored()) {
                        setStyle("-fx-background-color: #74a464;");  // Cambiar el color de fondo a verde
                    } else {
                        setStyle("-fx-background-color: #ff4f76;");  // Cambiar el color de fondo a rojo
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
        fecha.setDayCellFactory(picker -> new DateCell(){
            public void updateItem(LocalDate date, boolean empty){
                super.updateItem(date, empty);
                setDisable(date.isBefore(LocalDate.now()));
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
            LocalDate ld = LocalDate.of(2023, 5, 23);
            LocalDateTime lct = LocalDateTime.of(2023, 5, 23, 21, 0, 0);
            LocalTime lt = LocalTime.of(21, 0);
            greenBall.registerBooking(lct, ld, lt, true, listaPistas.get(0), greenBall.getMemberByCredentials("hola", "holahola1"));
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
    
    public void actualizarTabla(){
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
        IniciarSesion controller = (IniciarSesion) JavaFXMLApplication.getController("IniciarSesion");
        controller.setFocus();
    }
    
    @FXML
    private void registrarse(){
        RegistroUsuario controller = (RegistroUsuario) JavaFXMLApplication.getController("RegistroUsuario");
        controller.setDestino("PaginaInicio");
        JavaFXMLApplication.setRoot("RegistroUsuario");
        controller.setFocus();
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

package javafxmlapplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


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
    @FXML
    private Button eliminar;
    @FXML
    private Button pagar;
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
        pagado.setCellFactory( c -> new ImagenTabCellMisReservas());
        
        //Principio organizar al centro las columnas
        pista.setCellFactory(column -> {
            TableCell<Reserva, String> cell = new TableCell<Reserva, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setStyle("-fx-background-color: #a4dc8c;");
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
                        setStyle("-fx-background-color: #a4dc8c;");
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
                        setStyle("-fx-background-color: #a4dc8c;");
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
                        setStyle("-fx-background-color: #a4dc8c;");
                    } else {
                        setText(item);
                    }
                }
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
        
        pagado.setCellFactory(column -> {
            ImagenTabCellMisReservas cell = new ImagenTabCellMisReservas() {
                
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
        
        tabla.setRowFactory(row -> new TableRow<Reserva>() {
            @Override
            protected void updateItem(Reserva item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null && !empty) {
                    if (item.getColored()) {
                        setStyle("-fx-background-color: #74a464;");  // Cambiar el color de fondo a verde
                    } else {
                        setStyle("-fx-background-color: #ff4f76;");  // Cambiar el color de fondo a rojo
                    }
                }
            }
        });
        
        // Disable property
        eliminar.disableProperty().bind(Bindings.equal(tabla.getSelectionModel().selectedIndexProperty(), -1));
        pagar.disableProperty().bind(Bindings.equal(tabla.getSelectionModel().selectedIndexProperty(), -1));
        
        // Barra de usuario
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
        
        userFeatures.setVisible(false);
    }
    
    
    public void cambiarUser(Member m){
        member = m;
        user.setText(member.getNickName());
        name.setText(member.getName());
        perfil.setImage(member.getImage());
        userFeatures.setVisible(false);
    }
    
    public void actualizarTabla(){
        bookingList.remove(0, bookingList.size());
        List<Booking> lista = greenBall.getUserBookings(member.getNickName());
        for(int i = 0; i < lista.size(); i++){
            Reserva reserva = new Reserva(lista.get(i));
            bookingList.add(reserva);
            if(reserva.getBooking().getPaid()){
                reserva.setColored(true);
                reserva.setPagado("images/accept_white.png");
            }else{
                reserva.setColored(false);
                reserva.setPagado("images/cancel_white.png");
            }
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
        PaginaPrincipal controller = (PaginaPrincipal) JavaFXMLApplication.getController("PaginaPrincipal");
        controller.cambiarUser(member);
    }
    
    @FXML
    private void pagar(){
        Reserva reserva = tabla.getSelectionModel().getSelectedItem();
        
        if(reserva.getColored()){
            //Si está pagado
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Pagar");
            alert.setHeaderText(null);
            alert.setContentText("Ya está pagado");
            alert.getDialogPane().setStyle("-fx-background-color: #a4dc8c");
            alert.getDialogPane().getStylesheets().add("styles/EstilosFondo.css");
            alert.showAndWait();
        }else{
            //Si no está pagado
            if(member.checkHasCreditInfo()){
                //Si ya tiene tarjeta de crédito, solo tiene que pagar
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Pagando");
                alert.setHeaderText(null);
                alert.setContentText("Pago realizado con éxito");
                alert.getDialogPane().setStyle("-fx-background-color: #a4dc8c");
                alert.getDialogPane().getStylesheets().add("styles/EstilosFondo.css");
                reserva.getBooking().setPaid(true);
                alert.showAndWait();
                actualizarTabla();
                reserva.getBooking().setPaid(true);
            }else{
                //Avisar de introducir tarjeta en los datos
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pagar");
                alert.setHeaderText(null);

                //Estilo del background
                alert.getDialogPane().setStyle("-fx-background-color: #a4dc8c");
                alert.getDialogPane().getStylesheets().add("styles/EstilosFondo.css");
                alert.setContentText("No tienes tarjeta de crédito, indícala");

                //Poner aceptar y ir a mis datos en el mismo nivel que acpetar original
                ButtonType aceptarButton = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
                ButtonType irAMisDatosButton = new ButtonType("Ir a mis datos", ButtonBar.ButtonData.OTHER);

                alert.getButtonTypes().setAll(aceptarButton, irAMisDatosButton);

                //Instancias de los botones para darles métodos y estilos
                Button aceptar = (Button) alert.getDialogPane().lookupButton(aceptarButton);
                aceptar.setOnAction(c -> alert.close());


                Button irAMisDatos = (Button) alert.getDialogPane().lookupButton(irAMisDatosButton);
                irAMisDatos.setOnAction(c -> {
                    alert.close();
                    goMisDatos();
                });

                alert.showAndWait();
            }
        }
    }

    @FXML
    private void activeToolBar() {
        if(userFeatures.isVisible()){
            userFeatures.setVisible(false);
            
        }else{
            userFeatures.setVisible(true);
        }
    }

    @FXML
    private void goMisDatos() {
        CambioDatos controller = (CambioDatos) JavaFXMLApplication.getController("CambioDatos");
        controller.cambiarUser(member);
        controller.setDestino("MisReservas");
        controller.setFocus();
        JavaFXMLApplication.setRoot("CambioDatos");
    }

    @FXML
    private void goInicio() {
        JavaFXMLApplication.setRoot("PaginaInicio");
        PaginaInicioController controller = (PaginaInicioController)JavaFXMLApplication.getController("PaginaInicio");
        controller.actualizarTabla();
    }
}

//Clase para mostrar imagen en celda en vez de ruta
    class ImagenTabCellMisReservas extends TableCell<Reserva, String>{
        private ImageView view = new ImageView();
        private Image imagen;
        
        @Override
        protected void updateItem(String t, boolean bln){
            super.updateItem(t, bln);
            if(t == null || bln){
                setText(null);
                setGraphic(null);
                setStyle("-fx-background-color: #a4dc8c;");
                
            }else{
                imagen = new Image(t, 15, 15, false, true);
                view.setImage(imagen);
                setGraphic(view);
            }
        }
    }
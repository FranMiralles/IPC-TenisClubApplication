package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.*;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class ReservarPistaEspecifica extends DateCell  implements Initializable {

    @FXML
    private BorderPane idReservarPistaEspecifica;
    @FXML
    private DatePicker calendario;
    @FXML
    private ComboBox<LocalTime> hora_Inicio;
    @FXML
    private ComboBox<LocalTime> hora_Fin;
    @FXML
    private Label titulo ;
    @FXML
    private Button aceptarButton;
    @FXML
    private Label AlertText;
    @FXML
    private ImageView AlertImage;
    @FXML
    private Button borrarButon;
    @FXML
    private Label TextoDePista;
     @FXML
    private ImageView panelFoto;
    
    Club greenBall ;
    Court pista ;
    Member member;
    Boolean paid;
 
    Boolean encontrado;
    
    LocalDate diaActual = null; //dia actual
    LocalTime horaActual = null;//hora actual
    LocalDate diaSel = null ;   //dia seleccionado para la reserva
    LocalTime horaInSel = null; //hora inicial seleccionada para la reserva
    LocalTime aux = null;       //auxiliar para coger horas
    LocalTime horaFinSel = null;//hora final seleccionada
    long minuteRes;             //minutos seleccionador de reserva 1h o 2h
    LocalTime diezpm= null; //10pm excepcion 
    
    LocalTime horaReservadaAux = null;
   
    LocalDateTime horaReserva = null; //hora y fecha actual completa
    
    PauseTransition pause = new PauseTransition(Duration.seconds(4)); //pause de 4 s
    
   public List<Booking> ReservasHechas = new ArrayList<Booking>();
   public List<LocalTime> HorariosReservas = new ArrayList<LocalTime>();
   public List<Booking> reservasUsuario = new ArrayList<Booking>();
   public List<LocalTime> HorariosUsuario= new ArrayList<LocalTime>();
   
    Booking auxBooking = null; //reserva auxiliar
    
   Boolean permitido; 
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
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializacion GreenBall
         try{
            greenBall = Club.getInstance();
        }catch(Exception e){
            System.err.println(e.toString());
            
        }
       //Hora de las 22pm para caso excepcion
         diezpm = LocalTime.of(21, 00);
         
        
         
        //Desactivar Alertas
         AlertImage.setVisible(false);
         AlertText.setVisible(false);        
        
        //DesactivarBotones
        
         aceptarButton.disableProperty().bind(calendario.valueProperty().isNull());
         aceptarButton.disableProperty().bind(Bindings.isNull(hora_Inicio.valueProperty()));
         aceptarButton.disableProperty().bind(Bindings.isNull(hora_Fin.valueProperty()));
         hora_Inicio.disableProperty().bind(Bindings.isNull(calendario.valueProperty()));
         hora_Fin.disableProperty().bind(Bindings.isNull(hora_Inicio.valueProperty()));
         borrarButon.disableProperty().bind(Bindings.isNull(calendario.valueProperty()));
         
         
                   
         
        //Calendario Activo
        calendario.setDayCellFactory((DatePicker picker) -> {
            return new DateCell() {
         @Override
         public void updateItem(LocalDate date, boolean empty) {
        super.updateItem(date, empty);
        LocalDate today = LocalDate.now();
        setDisable(empty || date.compareTo(today) < 0 );
                }
            };
        });
        
        
        
        
        //listener para detectar cambios en la seleccion dia
            calendario.setOnAction(event ->{
                HorariosReservas.clear();
                diaSel = calendario.getValue();
                horaActual  = LocalTime.now();
                diaActual   = LocalDate.now();
                hora_Inicio.getItems().clear();
                
                if(diaSel != null){
                    ReservasHechas = greenBall.getCourtBookings(pista.getName(), diaSel);//Obitiene reservas hechas para ese dia
          
                if(ReservasHechas.size() ==13 ){
                    maximoReservas();
                 
                }else{
                    //Desactivar Alertas
                    AlertImage.setVisible(false);
                    AlertText.setVisible(false);
                }
            
                }
            
            //Bucle para obtener las fechas de las que ya hay reservas hechos
            
                if(!ReservasHechas.isEmpty() ){
                    for(int i = 0; i < ReservasHechas.size(); i++){
                        HorariosReservas.add(ReservasHechas.get(i).getFromTime());
                    
                    }
                }
           
            
                //bucles para añadir los hoarios de inicio 
                for(int i=9; i <22; i++){  
        
                     hora_Inicio.getItems().add(LocalTime.of(i, 00));
                }
                    
                //en caso de que haya reservas elimina los elementos en los que hay reservas
            
                if(!HorariosReservas.isEmpty()){
                //  hora_Inicio.getItems().clear();
                for(int i = 0; i<HorariosReservas.size(); i++){
                     aux = HorariosReservas.get(i);
                     
                    hora_Inicio.getItems().remove(aux);
                }   
            }
            
           
           //Consigue la hora actual
           if(diaActual != null&& horaActual != null){         
            horaReserva =diaActual.atTime(horaActual);
            horaReserva= horaReserva.withNano(0);
            
           
           }
         
        }); 
      
        
        //listener para detectar cambios en la seleccion hora final
        hora_Inicio.setOnAction(event ->{
       
            horaInSel = hora_Inicio.getValue();
            
            
            
            hora_Fin.getItems().clear();
             //Bucle para obtener los horarios del final
           for(int i = 1; i <2; i++){
             if(horaInSel!= null){
                 aux = horaInSel;
          
               hora_Fin.getItems().add(aux.plusHours(1));
               
               if(horaInSel!= diezpm){
                   hora_Fin.getItems().add(aux.plusHours(2));
               }
            }
           }
           
           
       //bucle para eliminar las horas siguientes a las finales a las que hay una reserva
           if(!HorariosReservas.isEmpty()){
              for(int i =0; !hora_Fin.getItems().isEmpty() && i<HorariosReservas.size(); i++){
                  for(int j = 0;!hora_Fin.getItems().isEmpty() && j<2; j++){
                      aux = HorariosReservas.get(i);
                      if(hora_Fin.getItems().get(0).equals(aux)){
                          hora_Fin.getItems().remove(aux.plusHours(1));
                      }
                  }
              }
                
           }   
     
        });
        
        
        //listener para detectar cambios en la seleccion de hora final
         hora_Fin.setOnAction(event ->{
            
             horaFinSel = hora_Fin.getValue();
             
                                              
        });
        
       
        
        //maximo 5 horas para ver
        hora_Inicio.setVisibleRowCount(5);
        hora_Fin.setVisibleRowCount(5);
            
        
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
    private void aceptar(ActionEvent event) {
          
            if(horaReserva != null && diaSel !=null && horaInSel !=null && pista !=null && member !=null && paid!=null){
              minuteRes = horaInSel.until(horaFinSel, java.time.temporal.ChronoUnit.MINUTES);  //saber si es 1h o 2h
            }
            
             //comprobar si se pudede reservar o no porque hay horas anteriores o posteriores
            if(minuteRes == 60){  //se quiere reservar 1H
                permitido = permitido60();
            }
            else{ //se quieren reservar 2 horas
                permitido = permitido120();
            }  
             
            
            if(permitido == true){ //Ya hay reservados más de 2 horas seguidas
                AlertImage.setVisible(true);
                AlertText.setVisible(true);
                AlertText.setText("No se puede reservar más de 2 horas seguidas");
                iniciar();
                
            } 
            else if(minuteRes == 60){ //Solo se ha seleccionado una hora
                  reservar();
              //calendario.setValue(null);
              calendario.requestFocus();
              
              hora_Fin.disableProperty(); 
              
                 
                  alerta();
             iniciar();// inicia todos las cosas poniendolas a null 
             JavaFXMLApplication.setRoot("PaginaPrincipal");
             
              }
            else if(minuteRes == 120){ //2 horas seleccionadas
                 
                  
                  
                  horaFinSel = horaFinSel.minusHours(1);
                 
                  reservar();
                  
                 horaInSel = horaInSel.plusHours(1);
                 
                 horaFinSel =horaInSel.plusHours(1);
                 
                  
                 
                if(diaActual != null&& horaActual != null){         
                    horaReserva =diaActual.atTime(horaActual);
                    horaReserva= horaReserva.withNano(0);
                }
                 reservar();
                   
                //calendario.setValue(null);
                calendario.requestFocus();
                hora_Fin.disableProperty(); 
                
                 alerta();
             iniciar();// inicia todos las cosas poniendolas a null 
             JavaFXMLApplication.setRoot("PaginaPrincipal");
             
             }
             
            
          
          
       HorariosReservas.clear();
        }
    
    
    public void reservar(){
        
        try {
             
             auxBooking =greenBall.registerBooking(horaReserva, diaSel, horaInSel, paid, pista, member);
             auxBooking.setPaid(paid);
             
            ReservasHechas = greenBall.getCourtBookings(pista.getName(), diaSel);//Obitiene reservas hechas para ese dia
            
             
            } catch (ClubDAOException ex) {
              Logger.getLogger(ReservarPistaEspecifica.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       }
    
    
    public void espera4(){
        pause.setOnFinished(event1 -> {
                    AlertImage.setVisible(false);
                    AlertText.setVisible(false);
            
        });
        
        // Iniciar la transición de pausa
        pause.play();
    }
    
    
     public void CambioTitulo(String n){
         
         titulo.setText(n);
         
     }
    
    
    public void AsignarPista(Court p){
        
        pista = p;  
    }
    
    public void cambioMember(Member m){
        member = m;
        user.setText(member.getNickName());
        name.setText(member.getName());
        perfil.setImage(member.getImage());
        userFeatures.setVisible(false);
    }

    @FXML
    private void borrar_Datos() {
       
        calendario.requestFocus();
        calendario.setValue(null);
        hora_Inicio.setValue(null);
              hora_Fin.setValue(null);
              hora_Fin.disableProperty(); 
              aceptarButton.disableProperty();
              
              horaReserva = null;
              
            
    }
    
    
    public void maximoReservas(){
        AlertImage.setVisible(true);
        AlertText.setVisible(true);
        AlertText.setText("No quedan horarios disponibles para: " + diaSel.toString());
        hora_Inicio.disableProperty();
        hora_Fin.disableProperty();
        calendario.setValue(null);
        borrarButon.disableProperty();
        aceptarButton.disableProperty();
        calendario.requestFocus();
        
        
        
         
    }
    
    
    public void cambioTexto(String t){
        TextoDePista.setText(t);
    }
    
    
    public void cambioTarjeta(Boolean p){
        paid = p;
        
    }
    @FXML
    private void volver_Inicio(ActionEvent event) {
        AlertImage.setVisible(false);
        AlertText.setVisible(false);
        JavaFXMLApplication.setRoot("ReservarPistas");
        ReservarPistas controller = (ReservarPistas) JavaFXMLApplication.getController("ReservarPistas");
        controller.cambioMember(member);
        controller.cambioTarjeta(member.getCreditCard());
        borrar_Datos();
    }

    
    private void alerta(){
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Reserva");
        alert.setHeaderText(null);
        
        if(paid){
            alert.setContentText("Reserva realizada con éxito.\nHa sido pagada automáticamente.");
        }
        else{
            alert.setContentText("Reserva realizada con éxito.\nPendiente de pago."); 
        }
        alert.getDialogPane().setStyle("-fx-background-color: #a4dc8c");
        alert.getDialogPane().getStylesheets().add("styles/EstilosFondo.css");
        alert.showAndWait();
    }
    
    
    
    public void iniciar(){
        hora_Inicio.disableProperty();
        hora_Fin.disableProperty();
        calendario.setValue(null);
        borrarButon.disableProperty();
        aceptarButton.disableProperty();
        calendario.requestFocus();
        
        HorariosReservas.clear();
        
    }
    
    public void cambioFoto(int i){
        
        switch (i){
            case 1:
                Image imagen1 = new Image("images/pista1.png");
                panelFoto.setImage(imagen1);
                panelFoto.setFitHeight(150);//alto
                panelFoto.setFitWidth(250);//ancho
                break;
            case 2:
                Image imagen2 = new Image("images/pista2.png");
                panelFoto.setImage(imagen2);
                panelFoto.setFitHeight(150);//alto
                panelFoto.setFitWidth(250);//ancho
                break;
            case 3:
                Image imagen3 = new Image("images/pista3.png");
                panelFoto.setImage(imagen3);
                panelFoto.setFitHeight(150);//alto
                panelFoto.setFitWidth(250);//ancho
                break;
                
            case 4:    
                Image imagen4 = new Image("images/pista4.png");
                panelFoto.setImage(imagen4);
                panelFoto.setFitHeight(150);//alto
                panelFoto.setFitWidth(250);//ancho
                break;
            case 5:
                Image imagen5 = new Image("images/pista5.png");
                panelFoto.setImage(imagen5);
                panelFoto.setFitHeight(150);//alto
                panelFoto.setFitWidth(250);//ancho
                break;
                
            case 6:
                Image imagen6 = new Image("images/pista6.png");
                panelFoto.setImage(imagen6);
                panelFoto.setFitHeight(150);//alto
                panelFoto.setFitWidth(250);//ancho
                break;
        }
        
        
                
        
        
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
    private void goMisDatos(MouseEvent event) {
        CambioDatos controller = (CambioDatos) JavaFXMLApplication.getController("CambioDatos");
        controller.cambiarUser(member);
        controller.setDestino("ReservarPistaEspecifica");
        JavaFXMLApplication.setRoot("CambioDatos");
        controller.setFocus();
        AlertImage.setVisible(false);
        AlertText.setVisible(false);
    }

    @FXML
    private void goInicio(MouseEvent event) {
        JavaFXMLApplication.setRoot("PaginaInicio");
        PaginaInicioController controller = (PaginaInicioController)JavaFXMLApplication.getController("PaginaInicio");
        controller.actualizarTabla();
    }
    
    
    
    public boolean permitido60(){//comprobar, si se quiere reservar 60 minutos si hay 2 reservas ya de el 
     HorariosUsuario.clear();
     encontrado = false;
     reservasUsuario = greenBall.getUserBookings(member.getNickName());
     
    
     
     for(int i = 0; i<reservasUsuario.size(); i++){//bucle para obtener todas las fechas reseervas de ese usuario para ese dia y pista
         auxBooking= reservasUsuario.get(i);  // en HorariosUsuarios las horas de las reservas a las que empiezan 
         
         if(auxBooking.getMadeForDay().equals(diaSel)  && auxBooking.getCourt().equals(pista)){
             HorariosUsuario.add(auxBooking.getFromTime()); 
         }   
     }
     
     
     if(HorariosUsuario.isEmpty()){
         return false;
     }
    
     if(HorariosUsuario.contains(horaInSel.plusHours(1))  && HorariosUsuario.contains(horaInSel.plusHours(2))){
         encontrado = true;
     }
     
     if(HorariosUsuario.contains(horaInSel.minusHours(1)) && HorariosUsuario.contains(horaInSel.minusHours(2))){
         encontrado = true;
     }
     
     if(HorariosUsuario.contains(horaInSel.minusHours(1)) && HorariosUsuario.contains(horaInSel.plusHours(1))){
         encontrado = true;
     }
     
     return encontrado;
    }
    
    public boolean permitido120(){//comprobar si se quiere reservar 120 min si hay 1 reserva ya de el 
        encontrado = false;
        HorariosUsuario.clear();
        reservasUsuario = greenBall.getUserBookings(member.getNickName());
     
        
     
        for(int i = 0; i<reservasUsuario.size(); i++){//bucle para obtener todas las fechas reseervas de ese usuario para ese dia y pista
            auxBooking= reservasUsuario.get(i);  // en HorariosUsuarios las horas de las reservas a las que empiezan 
         
            if(auxBooking.getMadeForDay().equals(diaSel)  && auxBooking.getCourt().equals(pista)){
             HorariosUsuario.add(auxBooking.getFromTime()); 
            }   
        }
        
        if(HorariosUsuario.isEmpty()){//no hay ninguna reserva para ese usurario ese dia, no hay que comprobar
         return false;
        }
        
        if(HorariosUsuario.contains(horaInSel.minusHours(1))){
            encontrado = true;
        }
        
        if(HorariosUsuario.contains(horaInSel.plusHours(2))){
            encontrado = true;
        }
        
        return encontrado;
    }
    
    
} 
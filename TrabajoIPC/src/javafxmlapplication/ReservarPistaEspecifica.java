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


/**
 * FXML Controller class
 *
 * @author raulh
 */
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
    
    
   
    LocalDateTime horaReserva = null; //hora y fecha actual completa
    
    PauseTransition pause = new PauseTransition(Duration.seconds(4)); //pause de 4 s
    
   public List<Booking> ReservasHechas = new ArrayList<Booking>();
   public List<LocalTime> HorariosReservas = new ArrayList<LocalTime>();
   public List<Booking> reservasUsuario = new ArrayList<Booking>();
   public List<LocalTime> HorariosUsuario= new ArrayList<LocalTime>();
   
    Booking auxBooking = null;
    
   Boolean permitido;
    
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
       //Hora de kas 22pm para caso excepcion
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
                diaSel = calendario.getValue();
                horaActual  = LocalTime.now();
                diaActual   = LocalDate.now();
                hora_Inicio.getItems().clear();
                System.out.println(paid);
            if(diaSel != null){
             ReservasHechas = greenBall.getCourtBookings(pista.getName(), diaSel);//Obitiene reservas hechas para ese dia
            System.out.println("nº reservas" + ReservasHechas.size());
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
                System.out.println("horas reservadas :" + HorariosReservas.size());
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
           
           
            
        }); //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa
      
        
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
            
       }

    
    
    @FXML
    private void aceptar(ActionEvent event) {
          if(horaReserva != null && diaSel !=null && horaInSel !=null && pista !=null && member !=null && paid!=null){
              minuteRes = horaInSel.until(horaFinSel, java.time.temporal.ChronoUnit.MINUTES);  //saber si es 1h o 2h

              //greenBall.registerBooking(horaReserva, diaSel, horaInSel, true, pista, member);
              System.out.println("minutos reservados" +minuteRes);
              
             permitido = comprovarReservas();
              
             
            if(permitido == true){ //Ya hay reservados más de 2 horas seguidas
                AlertImage.setVisible(true);
                AlertText.setVisible(true);
                AlertText.setText("No se puede reservar más de 2 horas seguidas");
                hora_Inicio.disableProperty();
                hora_Fin.disableProperty();
                calendario.setValue(null);
                borrarButon.disableProperty();
                aceptarButton.disableProperty();
                calendario.requestFocus();
                
            } 
            else if(minuteRes == 60){ //Solo se ha seleccionado una hora
                  reservar();
              //calendario.setValue(null);
              calendario.requestFocus();
              
              hora_Fin.disableProperty(); 
              //horaInSel = null;
              //horaFinSel = null;
                 // System.out.println("60");
                 
                  alerta();
             iniciar();// inicia todos las cosas poniendolas a null 
             JavaFXMLApplication.setRoot("PaginaPrincipal");
             
              }
            else if(minuteRes == 120){ //2 horas seleccionadas
                 // System.out.println("hora inicio 1 :" +horaInSel.toString());
                  
                  
                  horaFinSel = horaFinSel.minusHours(1);
                  System.out.println("hoara inicio 1 :" +horaInSel.toString());
                  System.out.println("hoara fin 1 : " +horaFinSel.toString());
                  reservar();
                  
                 horaInSel = horaInSel.plusHours(1);
                 
                 horaFinSel =horaInSel.plusHours(1);
                 
                  System.out.println("hoara inicio 2 :" +horaInSel.toString());
                  System.out.println("hoara fin 2 : " +horaFinSel.toString());
                 
                if(diaActual != null&& horaActual != null){         
                    horaReserva =diaActual.atTime(horaActual);
                    horaReserva= horaReserva.withNano(0);
                }
                 reservar();
                   
                //calendario.setValue(null);
                calendario.requestFocus();
                hora_Fin.disableProperty(); 
                //horaInSel = null;
                // horaFinSel = null;
                 alerta();
             iniciar();// inicia todos las cosas poniendolas a null 
             JavaFXMLApplication.setRoot("PaginaPrincipal");
             
             }
             
            
          }
          
       HorariosReservas.clear();
        }
    
    
    public void reservar(){
        
        try {
            //auxBooking =ooking(horaReserva, diaSel,horaInSel)
            //System.out.println("Hora Completo :" + horaReserva.toString());
            //System.out.println("Dia sel :" + diaSel.toString());
            //System.out.println("hora de Inicio :" + horaInSel.toString());
            //System.out.println("hora de Fin :" +horaFinSel.toString());
            //System.out.println("Pista :" + pista.toString());
            //System.out.println("miembto :" + member.getName());
            
            //Booking auxBooking  = new Booking(horaReserva, diaSel, horaInSel, true, pista, member);
             greenBall.registerBooking(horaReserva, diaSel, horaInSel, paid, pista, member);
             
             
             ReservasHechas = greenBall.getCourtBookings(pista.getName(), diaSel);//Obitiene reservas hechas para ese dia
            System.out.println("nº reservas" + ReservasHechas.size());
            System.out.println(ReservasHechas.get(0).toString());
             
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
        
    }

    @FXML
    private void borrar_Datos(ActionEvent event) {
       
        calendario.requestFocus();
        calendario.setValue(null);
        hora_Inicio.setValue(null);
              hora_Fin.setValue(null);
              hora_Fin.disableProperty(); 
              aceptarButton.disableProperty();
              //horaInSel = null;
              //horaFinSel = null;
              horaReserva = null;
              System.out.println(ReservasHechas.size());
            
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
         System.out.println(ReservasHechas.size());
        
        
         
    }
    
    
    public void cambioTexto(String t){
        TextoDePista.setText(t);
    }
    
    
    public void cambioTarjeta(Boolean p){
        paid = p;
        
    }
    @FXML
    private void volver_Inicio(ActionEvent event) {
        JavaFXMLApplication.setRoot("ReservarPistas");
        ReservarPistas controller = (ReservarPistas) JavaFXMLApplication.getController("ReservarPistas");
        controller.cambioMember(member);
        controller.cambioTarjeta(member.getCreditCard());
    }

    
    private void alerta(){
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Reserva");
        alert.setHeaderText(null);
        alert.setContentText("Reserva realizada con éxito");
        alert.getDialogPane().setStyle("-fx-background-color: #a4dc8c");
        alert.getDialogPane().getStylesheets().add("styles/EstilosFondo.css");
        alert.showAndWait();
    }
    
    public boolean comprovarReservas() {
    
    encontrado = false;
    reservasUsuario = greenBall.getUserBookings(member.getNickName());

    if (reservasUsuario.isEmpty()) {
        return false;
    }

    //HorariosUsuario.clear();

    
    for (int i = 0; i < reservasUsuario.size(); i++) {
        auxBooking = reservasUsuario.get(i);
        if(auxBooking.getMadeForDay().equals(diaSel) && auxBooking.getCourt().equals(pista)){
            HorariosUsuario.add(auxBooking.getFromTime()); // Tengo todas las horas a las que ha reservado el usuario
        }
        
    }

    int horasSeguidasPos = 0;
    int horasSeguidasAnt = 0;
    

    for (int i = 0; i<HorariosUsuario.size(); i++) {
        if (HorariosUsuario.get(i).plusHours(1).equals(horaInSel) || HorariosUsuario.get(i).plusHours(2).equals(horaInSel)) {
            horasSeguidasPos++;
        }else if(HorariosUsuario.get(i).minusHours(1).equals(horaInSel) || HorariosUsuario.get(i).minusHours(2).equals(horaInSel)){
            horasSeguidasAnt++;
        }
    }
    
    if(horasSeguidasAnt>= 2 ||horasSeguidasPos >=2 ){
        encontrado = true;
    }
    

    return encontrado;
}

    
    public void iniciar(){
        hora_Inicio.disableProperty();
        hora_Fin.disableProperty();
        calendario.setValue(null);
        borrarButon.disableProperty();
        aceptarButton.disableProperty();
        calendario.requestFocus();
        
    }
} 
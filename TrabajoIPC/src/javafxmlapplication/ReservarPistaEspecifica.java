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
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.*;


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
    private Text titulo ;
    @FXML
    private Button aceptarButton;
    @FXML
    private Label AlertText;
    @FXML
    private ImageView AlertImage;
     
    
    
    Club greenBall ;
    Court pista ;
    Member member;
    Boolean paid;
    
    LocalTime horaActual = null;
    LocalDate diaSel = null ;
    LocalTime horaInSel = null;
    LocalTime horaFinSel = null;
    long minuteRes;
    
   LocalDateTime horaReserva = null;
    
    
     PauseTransition pause = new PauseTransition(Duration.seconds(4));
    
     
     
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
        
         
         //Desactivar Alertas
        AlertImage.setVisible(false);
        AlertText.setVisible(false);
        
        //DesactivarBotones
        
         aceptarButton.disableProperty().bind(calendario.valueProperty().isNull());
         aceptarButton.disableProperty().bind(Bindings.isNull(hora_Inicio.valueProperty()));
         aceptarButton.disableProperty().bind(Bindings.isNull(hora_Fin.valueProperty()));

         hora_Fin.disableProperty().bind(Bindings.isNull(hora_Inicio.valueProperty()));
         
         
         //Escribir en codigo los horaros
        for(int i=9; i <23; i++){ 
           if(i!= 22){
            hora_Inicio.getItems().add(LocalTime.of(i, 00));
            
           }
           
           if(i != 9 ){
                hora_Fin.getItems().add(LocalTime.of(i,00));
            }
        }
         
        
        hora_Inicio.setVisibleRowCount(5);
        hora_Fin.setVisibleRowCount(5);
        
        
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
            
           if(diaSel != null&& horaActual != null){         
            horaReserva =diaSel.atTime(horaActual);
            horaReserva= horaReserva.withNano(0);
            System.out.println("hora y fecha  " + horaReserva);
        
           }
        });
  
        //listener para obtener la fecha Actual
        
        
        
        //listener para detectar cambios en la seleccion de fechas.
        hora_Inicio.setOnAction(event ->{
            horaInSel = hora_Inicio.getValue();
            System.out.println("hora inicial sel" + horaInSel);
        
        });
        
         hora_Fin.setOnAction(event ->{
            horaFinSel = hora_Fin.getValue();
            System.out.println("hora fin sel" + horaFinSel);
        });
        
            
            
        
          
       }

    
    
    private void aceptar(ActionEvent event) {
          if(horaFinSel!= null && horaInSel!= null){
              minuteRes = horaInSel.until(horaFinSel, java.time.temporal.ChronoUnit.MINUTES);  
              System.out.println(pista.getName());
              System.out.println(member.getName());
              
           }   
           
          if(minuteRes <=120 && minuteRes > 0){
              System.out.println(minuteRes);
          }
          else{
              AlertText.setText("Horario escogido no valido ");
              AlertImage.setVisible(true);
              AlertText.setVisible(true);
              hora_Inicio.requestFocus();
              hora_Inicio.setValue(null);
              hora_Fin.setValue(null);
              hora_Fin.disableProperty(); 
              horaInSel = null;
              horaFinSel = null;
              System.out.println(member.getNickName());
              espera4();   
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
    }
}

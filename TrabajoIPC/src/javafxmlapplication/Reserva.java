package javafxmlapplication;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Booking;


public class Reserva{
    private final StringProperty pista = new SimpleStringProperty();
    private final StringProperty dia = new SimpleStringProperty();
    private final StringProperty horaIni = new SimpleStringProperty();
    private final StringProperty horaFin = new SimpleStringProperty();
    private final StringProperty pagado = new SimpleStringProperty();
    private final BooleanProperty colored = new SimpleBooleanProperty();
    private Booking reserva;
    
    public Reserva(Booking booking){
        reserva = booking;
        pista.setValue(booking.getCourt().getName());
        dia.setValue(booking.getMadeForDay().getDayOfMonth() + "/" + booking.getMadeForDay().getMonthValue() + "/" + booking.getMadeForDay().getYear());
        horaIni.setValue(booking.getFromTime().getHour() + ":00");
        horaFin.setValue((booking.getFromTime().getHour() + 1) + ":00");
        if(booking.getPaid()){pagado.setValue("images/accept_white.png"); colored.setValue(true);}
        else {pagado.setValue("images/cancel_white.png");  colored.setValue(false);}
    }
    
    public BooleanProperty ColoredProperty(){
        return colored;
    }
    
    public boolean getColored(){
        return colored.getValue();
    }
    
    public void setColored(boolean colored){
        this.colored.setValue(colored);
    }
    
    public Booking getBooking(){
        return reserva;
    }
    
    public StringProperty PistaProperty(){
        return pista;
    }
    
    public String getPista(){
        return pista.getValue();
    }
    
    public StringProperty DiaProperty(){
        return dia;
    }
    
    public String getDia(){
        return dia.getValue();
    }
    
    public StringProperty HoraIniProperty(){
        return horaIni;
    }
    
    public String getHoraIni(){
        return horaIni.getValue();
    }
    
    public StringProperty HoraFinProperty(){
        return horaFin;
    }
    
    public String getHoraFin(){
        return horaFin.getValue();
    }
    
    
    public StringProperty PagadoProperty(){
        return pagado;
    }
    
    public String getPagado(){
        return pagado.getValue();
    }
    
    public void setPagado(String route){
        pagado.setValue(route);
    }
}
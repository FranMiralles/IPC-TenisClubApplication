package javafxmlapplication;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item{
    
    private final StringProperty horaIni = new SimpleStringProperty();
    private final StringProperty horaFin = new SimpleStringProperty();
    private final StringProperty nick = new SimpleStringProperty();
    private final StringProperty estadoPath = new SimpleStringProperty();
    private final BooleanProperty colored = new SimpleBooleanProperty();
    private int valorInicio;
    
    public Item(String ini, String fin, String nick, String im, int iniInt){
        horaIni.setValue(ini);
        horaFin.setValue(fin);
        this.nick.setValue(nick);
        estadoPath.setValue(im);
        colored.setValue(true);
        valorInicio = iniInt;
    }
    
    public int getValorInicio(){
        return valorInicio;
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
    
    public StringProperty NickProperty(){
        return nick;
    }
    
    public String getNick(){
        return nick.getValue();
    }
    
    public void setNick(String nick){
        this.nick.setValue(nick);
    }
    
    public StringProperty EstadoPathProperty(){
        return estadoPath;
    }
    
    public String getEstadoPath(){
        return estadoPath.getValue();
    }
    
    public void setEstadoPath(String path){
        this.estadoPath.setValue(path);
    }
    
    public StringProperty ColoredProperty(){
        return estadoPath;
    }
    
    public boolean getColored(){
        return colored.getValue();
    }
    
    public void setColored(boolean colored){
        this.colored.setValue(colored);
    }
}
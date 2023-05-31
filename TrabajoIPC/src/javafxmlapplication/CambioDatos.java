package javafxmlapplication;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import model.Club; 
import model.Member;


public class CambioDatos implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField tel;
    @FXML
    private TextField nick;
    @FXML
    private PasswordField pwd;
    @FXML
    private PasswordField pwd1;
    @FXML
    private ImageView foto;
    @FXML
    private TextField tar1;
    @FXML
    private TextField tar2;
    @FXML
    private TextField tar3;
    @FXML
    private TextField tar4;
    @FXML
    private TextField csv;
    @FXML
    private Label userAlert;
    @FXML
    private Label pwdAlert;
    @FXML
    private Label pwd1Alert;
    @FXML
    private ImageView pwdAlertImage;
    @FXML
    private ImageView flechaIzq;
    @FXML
    private ImageView flechaDer;
    @FXML
    private HBox colliderFoto;
    @FXML
    private ImageView userAlertImage;
    @FXML
    private ImageView pwd1AlertImage;
    @FXML
    private ImageView telAlertImage;
    @FXML
    private Label telAlert;
    @FXML
    private ImageView tarAlertImage1;
    @FXML
    private Label tarAlert1;
    @FXML
    private ImageView csvAlertImage;
    @FXML
    private Label csvAlert;
    @FXML
    private ImageView nameAlertImage;
    @FXML
    private Label nameAlert;
    @FXML
    private ImageView apellidosAlertImage;
    @FXML
    private Label apellidosAlert;
    
    private Member member;
    private String destino;
    
    private Image[] imageArray = new Image[12];
    int i;
    private Club greenBall = null;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Obtención de la instancia Club
        try{
            greenBall = Club.getInstance();
        }catch(Exception e){
            System.err.println(e.toString());
        }
        
        nick.setEditable(false);
        nick.setStyle("-fx-background-color: #fcfcfc;"); //no funcioa porqeu entra en conflicto con el css
        
        imageArray[0] = new Image("avatars/default.png", 140, 150, false, true);
        imageArray[1] = new Image("avatars/men.png", 140, 150, false, true);
        imageArray[2] = new Image("avatars/men2.png", 140, 150, false, true);
        imageArray[3] = new Image("avatars/men3.png", 140, 150, false, true);
        imageArray[4] = new Image("avatars/men4.png", 140, 150, false, true);
        imageArray[5] = new Image("avatars/men5.png", 140, 150, false, true);
        imageArray[6] = new Image("avatars/woman.png", 140, 150, false, true);
        imageArray[7] = new Image("avatars/woman2.png", 140, 150, false, true);
        imageArray[8] = new Image("avatars/woman3.png", 140, 150, false, true);
        imageArray[9] = new Image("avatars/woman4.png", 140, 150, false, true);
        imageArray[10] = new Image("avatars/woman5.png", 140, 150, false, true);
        imageArray[11] = new Image("avatars/woman6.png", 140, 150, false, true);
        
        //Este es el setImage que me da error
        foto.setImage(imageArray[0]);
        i = 0;
        
        flechaDer.setOnMouseEntered(event -> {
            flechaDer.setFitWidth(40); flechaDer.setFitHeight(40);
            flechaIzq.setFitWidth(40); flechaIzq.setFitHeight(40);
        });
        
        flechaIzq.setOnMouseEntered(event -> {
            flechaDer.setFitWidth(40); flechaDer.setFitHeight(40);
            flechaIzq.setFitWidth(40); flechaIzq.setFitHeight(40);
        });
        
        flechaDer.setOnMouseExited(event -> {
            flechaDer.setFitWidth(35); flechaDer.setFitHeight(35);
            flechaIzq.setFitWidth(35); flechaIzq.setFitHeight(35);
        });
        
        flechaIzq.setOnMouseExited(event -> {
            flechaDer.setFitWidth(35); flechaDer.setFitHeight(35);
            flechaIzq.setFitWidth(35); flechaIzq.setFitHeight(35);
        });
        
        //Permitir solo letras y espaciado en el nombre
        nombre.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z\\s'\\-áéíúóàèìòùÁÉÍÓÚÀÈÌÒÙäëïöüÄËÏÖÜñÑ]*")) {
                nombre.setText(oldValue);
            }
        });    
        
        //Errores en nombre
        nombre.setOnKeyTyped(event -> cambiarEstiloNombre());
        
        //Errores en nombre cuando cambias de campo y está vacío
        nombre.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                cambiarEstiloNombre();
            }
        });
        
        //Permitir solo letras y espaciado en los apellidos
        apellidos.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z\\s'\\-áéíúóàèìòùÁÉÍÓÚÀÈÌÒÙäëïöüÄËÏÖÜñÑ]*")) {
                apellidos.setText(oldValue);
            }
        });
        
        //Errores en apellidos
        apellidos.setOnKeyTyped(event -> cambiarEstiloApellidos()); 
        
        //Errores en apellidos cuando cambias de campo y está vacío
        apellidos.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                cambiarEstiloApellidos();
            }
        });
        
        //Permitir solo números y longitud máxima de 9 dígitos en teléfono
        tel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 9) {
                tel.setText(oldValue);
            } else if (!newValue.matches("\\d*")) {
                tel.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        //Errores en tel
        tel.setOnKeyTyped(event -> cambiarEstiloTel());
        
        //Errores en tel cuando cambias de campo y está vacío
        tel.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                cambiarEstiloTel();
            }
        });
        
        //Permitir solo números y letras en el campo de contraseña
        pwd.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z0-9]*")) {
                pwd.setText(oldValue);
            }
            checkPasswordRequirements();
        });
        
        //Errores cen pwd cuando se cambie de textField
        pwd.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && checkPasswordRequirements()) {
                cambiarEstiloPwd();
            }
        });
        
        //Errores en pwd
        pwd.setOnKeyTyped(event -> cambiarEstiloPwd());
        
        //Permitir solo números y letras en el campo de confirmar contraseña
        pwd1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z0-9]*")) {
                pwd1.setText(oldValue);
            }
            checkPasswordRequirements();
        });
        
        //Errores cen pwd1 cuando se cambie de textField
        pwd1.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                cambiarEstiloPwd1();
            }
        });
        
        //Errores en pwd1
        pwd1.setOnKeyTyped(event -> cambiarEstiloPwd1());
        
        //Permitir solo números y longitud máxima de 4 dígitos en los campos de la tarjeta
        tar1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 4) {
                tar1.setText(oldValue);
            } else if (!newValue.matches("\\d*")) {
                tar1.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        //Errores en tar1
        tar1.setOnKeyTyped(event -> cambiarEstiloTar1());
        
        tar2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 4) {
                tar2.setText(oldValue);
            } else if (!newValue.matches("\\d*")) {
                tar2.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        //Errores en tar2
        tar2.setOnKeyTyped(event -> cambiarEstiloTar2());
        
        tar3.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 4) {
                tar3.setText(oldValue);
            } else if (!newValue.matches("\\d*")) {
                tar3.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        //Errores en tar3
        tar3.setOnKeyTyped(event -> cambiarEstiloTar3());
        
        tar4.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 4) {
                tar4.setText(oldValue);
            } else if (!newValue.matches("\\d*")) {
                tar4.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        //Errores en tar4
        tar4.setOnKeyTyped(event -> cambiarEstiloTar4());
        
        //Permitir solo números y longitud máxima de 3 dígitos en el campo csv
        csv.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 3) {
                csv.setText(oldValue);
            } else if (!newValue.matches("\\d*")) {
                csv.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        //Errores en csv
        csv.setOnKeyTyped(event -> cambiarEstiloCsv());
    }

    public void setDestino(String s){
        destino = s;
    }
    
    @FXML
    private void aceptar(){
        boolean camposValidos = verificarCampos();
        boolean contrValida = checkPasswordRequirements();
        
        String tarjeta = "";
        int codigoCSV = 0;
        if (camposValidos && contrValida) {
            try{
            if(tar1.getText().isEmpty() && tar2.getText().isEmpty() && tar3.getText().isEmpty() && tar4.getText().isEmpty() && csv.getText().isEmpty()){
                member.setName(nombre.getText().trim());
                member.setSurname(apellidos.getText().trim());
                member.setTelephone(tel.getText());
                member.setPassword(pwd.getText());
                member.setCreditCard(tarjeta);
                member.setSvc(codigoCSV);
                member.setImage(foto.getImage());
            }else{
                member.setName(nombre.getText().trim());
                member.setSurname(apellidos.getText().trim());
                member.setTelephone(tel.getText());
                member.setPassword(pwd.getText());
                member.setCreditCard(tar1.getText() + tar2.getText() + tar3.getText() + tar4.getText());
                member.setSvc(Integer.parseInt(csv.getText()));
                member.setImage(foto.getImage());
            }
            mostrarAlert();
            limpiarCampos();
            
            if(destino.equals("MisReservas")){
                MisReservas controller = (MisReservas) JavaFXMLApplication.getController("MisReservas");
                controller.cambiarUser(member);
            }

            if(destino.equals("PaginaPrincipal")){
                PaginaPrincipal controller = (PaginaPrincipal) JavaFXMLApplication.getController("PaginaPrincipal");
                controller.cambiarUser(member);
            }
            
            if(destino.equals("ReservarPistaEspecifica")){
                ReservarPistaEspecifica controller = (ReservarPistaEspecifica) JavaFXMLApplication.getController("ReservarPistaEspecifica");
                controller.paid = member.checkHasCreditInfo();
                controller.cambioMember(member);
            }
            
            if(destino.equals("ReservarPistas")){
                ReservarPistas controller = (ReservarPistas) JavaFXMLApplication.getController("ReservarPistas");
                controller.paid = member.checkHasCreditInfo();
                controller.cambioMember(member);
            }
            
            JavaFXMLApplication.setRoot(destino);
            }catch(Exception e){
                System.err.println(e.toString());
            }
        }
    }
    
    public void setFocus(){
        nombre.requestFocus();
    }

    @FXML
    private void cancelar() {
        cambiarUser(member);
    }
    
    private void eliminar() {
        limpiarCampos();
        JavaFXMLApplication.setRoot("PaginaPricipal");
    }
    
    @FXML
    private void derImage(){
        i++;
        if(i >= 12){i = 0;}
        foto.setImage(imageArray[i]);
    }
    
    @FXML
    private void izqImage(){
        i--;
        if(i < 0){i = 11;}
        foto.setImage(imageArray[i]);
    }
    
    @FXML
    private void volver(){
        limpiarCampos();
        JavaFXMLApplication.setRoot(destino);
    }
    
    @FXML
    private void buscarImage(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");
        
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null){
            Image image = null;
            try{
                image = new Image("file:" + selectedFile.getAbsolutePath(), 140, 150, false, true);
            }catch(Exception e){
                System.err.println(e.toString());
            }
            
            foto.setImage(image);
        }
        
    }
    
    //Método para comprobar los campos
    private boolean verificarCampos() {
        boolean mandado = false;
        
        String nombreText = nombre.getText().trim();
        String apellidosText = apellidos.getText().trim();
        
        boolean nombreValido = !nombreText.isEmpty();
        boolean apellidosValidos = !apellidosText.isEmpty();
        boolean telValido = tel.getText().length() == 9;
        boolean nickValido = !nick.getText().isEmpty();
        boolean pwdValida = pwd.getText().length() >= 6;
        boolean pwd1Valida = pwd1.getText().equals(pwd.getText());
        boolean tarjetaValida = tar1.getText().length() == 4 &&
                                tar2.getText().length() == 4 &&
                                tar3.getText().length() == 4 &&
                                tar4.getText().length() == 4;
        boolean csvValido = csv.getText().length() == 3;
        
        nameAlertImage.setVisible(!nombreValido);
        nameAlert.setStyle(nombreValido ? "-fx-text-fill: #7c7c7c;" : "-fx-text-fill: #fc0000;");
        if(!mandado && !nombreValido){nombre.requestFocus(); mandado = true;}
        
        apellidosAlertImage.setVisible(!apellidosValidos);
        apellidosAlert.setStyle(apellidosValidos ? "-fx-text-fill: #7c7c7c;" : "-fx-text-fill: #fc0000;");
        if(!mandado && !apellidosValidos){apellidos.requestFocus(); mandado = true;}
        
        pwdAlertImage.setVisible(pwd.getText().isEmpty());
        pwdAlert.setStyle(pwd.getText().isEmpty() ?  "-fx-text-fill: #fc0000;" : "-fx-text-fill: #7c7c7c;");
        if(!mandado && !pwdValida){pwd.requestFocus(); mandado = true;}
        
        pwd1AlertImage.setVisible(pwd1.getText().isEmpty());
        pwd1Alert.setStyle(pwd1.getText().isEmpty() ?  "-fx-text-fill: #fc0000;" : "-fx-text-fill: #7c7c7c;");
        if(!mandado && !pwd1Valida){pwd1.requestFocus(); mandado = true;}
        
        pwd1AlertImage.setVisible(!pwd1.getText().equals(pwd.getText()));
        pwd1Alert.setStyle(!pwd1.getText().equals(pwd.getText()) ?  "-fx-text-fill: #fc0000;" : "-fx-text-fill: #7c7c7c;");
        if(!mandado && !pwd1Valida){pwd1.requestFocus(); mandado = true;}
        
        telAlertImage.setVisible(tel.getText().isEmpty());
        telAlert.setStyle(tel.getText().isEmpty() ?  "-fx-text-fill: #fc0000;" : "-fx-text-fill: #7c7c7c;");
        if(!mandado && !telValido){tel.requestFocus(); mandado = true;}
        
        if(!tar1.getText().isEmpty() || !tar2.getText().isEmpty() || !tar3.getText().isEmpty() || !tar4.getText().isEmpty() || !csv.getText().isEmpty()){
            tarAlertImage1.setVisible(!tarjetaValida);
            tarAlert1.setStyle(tarjetaValida ? "-fx-text-fill: #7c7c7c;" : "-fx-text-fill: #fc0000;");
            if(!mandado && tar1.getText().length() != 4){tar1.requestFocus(); mandado = true;}
            else if(!mandado && tar2.getText().length() != 4){tar2.requestFocus(); mandado = true;}
            else if(!mandado && tar3.getText().length() != 4){tar3.requestFocus(); mandado = true;}
            else if(!mandado && tar4.getText().length() != 4){tar4.requestFocus(); mandado = true;}
            
            csvAlertImage.setVisible(!csvValido);
            csvAlert.setStyle(csvValido ? "-fx-text-fill: #7c7c7c;" : "-fx-text-fill: #fc0000;");
            if(!mandado && csv.getText().length() != 3){csv.requestFocus(); mandado = true;}
            
            return nombreValido && apellidosValidos && telValido && nickValido && pwdValida && pwd1Valida && tarjetaValida && csvValido;
        }
        else{
            tarAlertImage1.setVisible(false);
            tarAlert1.setStyle("-fx-text-fill: #7c7c7c;");

            csvAlertImage.setVisible(false);
            csvAlert.setStyle("-fx-text-fill: #7c7c7c;");
            
            return nombreValido && apellidosValidos && telValido && nickValido && pwdValida && pwd1Valida;
        }
    }
    
    //Mostrar errores en el nombre
    private void cambiarEstiloNombre() {
        String nombreText = nombre.getText();
        boolean nombreValido = !nombreText.isEmpty();

        if (!nombreValido) {
            nameAlertImage.setVisible(true);
            nameAlert.setStyle("-fx-text-fill: #fc0000;");
        } else {
            nameAlertImage.setVisible(false);
            nameAlert.setStyle("-fx-text-fill: #7c7c7c;");
        }
    }
    
    //Mostrar errores en los apellidos
    private void cambiarEstiloApellidos() {
        String apellidosText = apellidos.getText();
        boolean apellidosValidos = !apellidosText.isEmpty();

        if (!apellidosValidos) {
            apellidosAlertImage.setVisible(true);
            apellidosAlert.setStyle("-fx-text-fill: #fc0000;");
        } else {
            apellidosAlertImage.setVisible(false);
            apellidosAlert.setStyle("-fx-text-fill: #7c7c7c;");
        }
    }

    
    //Mostrar errores en la contraseña
    private void cambiarEstiloPwd() {
        String pwdText = pwd.getText();
        boolean pwdValida = pwdText.length() >= 6;

        if (!pwdValida) {
            pwdAlertImage.setVisible(true);
            pwdAlert.setStyle("-fx-text-fill: #fc0000;");
        } else {
            pwdAlertImage.setVisible(false);
            pwdAlert.setStyle("-fx-text-fill: #7c7c7c;");
        }
    }

    //Mostrar errores en confirmar contraseña
    private void cambiarEstiloPwd1() {
        String pwd1Text = pwd1.getText();
        boolean pwdValida = pwd1Text.equals(pwd.getText());

        if (!pwdValida) {
            pwd1AlertImage.setVisible(true);
            pwd1Alert.setStyle("-fx-text-fill: #fc0000;");
        } else {
            pwd1AlertImage.setVisible(false);
            pwd1Alert.setStyle("-fx-text-fill: #7c7c7c;");
        }
    }
    
    
    //Mostrar errores en telefono 
    private void cambiarEstiloTel() {
        String telText = tel.getText();
        boolean telValido = telText.length() == 9;

        if (!telValido) {
            telAlertImage.setVisible(true);
            telAlert.setStyle("-fx-text-fill: #fc0000;");
        } else {
            telAlertImage.setVisible(false);
            telAlert.setStyle("-fx-text-fill: #7c7c7c;");
        }
    }
    
    // Mostrar errores en tarjeta 1
    private void cambiarEstiloTar1() {
        String tar1Text = tar1.getText();
        boolean tar1Valido = (tar1Text.length() == 4 && tar2.getText().length() == 4 && tar3.getText().length() == 4 && tar4.getText().length() == 4) || (tar1Text.length() == 0 && tar2.getText().length() == 0 && tar3.getText().length() == 0 && tar4.getText().length() == 0);

        if (!tar1Valido) {
            tarAlertImage1.setVisible(true);
            tarAlert1.setStyle("-fx-text-fill: #fc0000;");
        } else {
            tarAlertImage1.setVisible(false);
            tarAlert1.setStyle("-fx-text-fill: #7c7c7c;");
        }

        if (!csv.getText().isEmpty() && (tar1.getText().isEmpty() && tar2.getText().isEmpty() && tar3.getText().isEmpty() && tar4.getText().isEmpty())) {
            tarAlertImage1.setVisible(true);
            tarAlert1.setStyle("-fx-text-fill: #fc0000;"); // No reestablecer el mensaje de error de tarjeta si el campo CSV no está vacío
        }
        if (tar1.getText().isEmpty() && tar2.getText().isEmpty() && tar3.getText().isEmpty() && tar4.getText().isEmpty() && csv.getText().isEmpty()) {
            csvAlertImage.setVisible(false);
            csvAlert.setStyle("-fx-text-fill: #7c7c7c;");
        } else if(csv.getText().isEmpty() && (!tar1.getText().isEmpty() || !tar2.getText().isEmpty() || !tar3.getText().isEmpty() || !tar4.getText().isEmpty())){
            csvAlertImage.setVisible(true);
            csvAlert.setStyle("-fx-text-fill: #fc0000;");
        }
    }

    // Mostrar errores en tarjeta 2
    private void cambiarEstiloTar2() {
        String tar2Text = tar2.getText();
        boolean tar2Valido = (tar1.getText().length() == 4 && tar2Text.length() == 4 && tar3.getText().length() == 4 && tar4.getText().length() == 4) || (tar1.getText().length() == 0 && tar2Text.length() == 0 && tar3.getText().length() == 0 && tar4.getText().length() == 0);

        if (!tar2Valido) {
            tarAlertImage1.setVisible(true);
            tarAlert1.setStyle("-fx-text-fill: #fc0000;");
        } else {
            tarAlertImage1.setVisible(false);
            tarAlert1.setStyle("-fx-text-fill: #7c7c7c;");
        }

        if (!csv.getText().isEmpty() && (tar1.getText().isEmpty() && tar2.getText().isEmpty() && tar3.getText().isEmpty() && tar4.getText().isEmpty())) {
            tarAlertImage1.setVisible(true);
            tarAlert1.setStyle("-fx-text-fill: #fc0000;"); // No reestablecer el mensaje de error de tarjeta si el campo CSV no está vacío
        }
        if (tar1.getText().isEmpty() && tar2.getText().isEmpty() && tar3.getText().isEmpty() && tar4.getText().isEmpty() && csv.getText().isEmpty()) {
            csvAlertImage.setVisible(false);
            csvAlert.setStyle("-fx-text-fill: #7c7c7c;");
        } else if(csv.getText().isEmpty() && (!tar1.getText().isEmpty() || !tar2.getText().isEmpty() || !tar3.getText().isEmpty() || !tar4.getText().isEmpty())){
            csvAlertImage.setVisible(true);
            csvAlert.setStyle("-fx-text-fill: #fc0000;");
        }
    }

    // Mostrar errores en tarjeta 3
    private void cambiarEstiloTar3() {
        String tar3Text = tar3.getText();
        boolean tar3Valido = (tar1.getText().length() == 4 && tar2.getText().length() == 4 && tar3Text.length() == 4 && tar4.getText().length() == 4) || (tar1.getText().length() == 0 && tar2.getText().length() == 0 && tar3Text.length() == 0 && tar4.getText().length() == 0);

        if (!tar3Valido) {
            tarAlertImage1.setVisible(true);
            tarAlert1.setStyle("-fx-text-fill: #fc0000;");
        } else {
            tarAlertImage1.setVisible(false);
            tarAlert1.setStyle("-fx-text-fill: #7c7c7c;");
        }

        if (!csv.getText().isEmpty() && (tar1.getText().isEmpty() && tar2.getText().isEmpty() && tar3.getText().isEmpty() && tar4.getText().isEmpty())) {
            tarAlertImage1.setVisible(true);
            tarAlert1.setStyle("-fx-text-fill: #fc0000;"); // No reestablecer el mensaje de error de tarjeta si el campo CSV no está vacío
        }
        if (tar1.getText().isEmpty() && tar2.getText().isEmpty() && tar3.getText().isEmpty() && tar4.getText().isEmpty() && csv.getText().isEmpty()) {
            csvAlertImage.setVisible(false);
            csvAlert.setStyle("-fx-text-fill: #7c7c7c;");
        } else if(csv.getText().isEmpty() && (!tar1.getText().isEmpty() || !tar2.getText().isEmpty() || !tar3.getText().isEmpty() || !tar4.getText().isEmpty())){
            csvAlertImage.setVisible(true);
            csvAlert.setStyle("-fx-text-fill: #fc0000;");
        }
    }

    // Mostrar errores en tarjeta 4
    private void cambiarEstiloTar4() {
        String tar4Text = tar4.getText();
        boolean tar4Valido = (tar1.getText().length() == 4 && tar2.getText().length() == 4 && tar3.getText().length() == 4 && tar4Text.length() == 4) || (tar1.getText().length() == 0 && tar2.getText().length() == 0 && tar3.getText().length() == 0 && tar4Text.length() == 0);

        if (!tar4Valido) {
            tarAlertImage1.setVisible(true);
            tarAlert1.setStyle("-fx-text-fill: #fc0000;");
        } else {
            tarAlertImage1.setVisible(false);
            tarAlert1.setStyle("-fx-text-fill: #7c7c7c;");
        }

        if (!csv.getText().isEmpty() && (tar1.getText().isEmpty() && tar2.getText().isEmpty() && tar3.getText().isEmpty() && tar4.getText().isEmpty())) {
            tarAlertImage1.setVisible(true);
            tarAlert1.setStyle("-fx-text-fill: #fc0000;"); // No reestablecer el mensaje de error de tarjeta si el campo CSV no está vacío
        }
        if (tar1.getText().isEmpty() && tar2.getText().isEmpty() && tar3.getText().isEmpty() && tar4.getText().isEmpty() && csv.getText().isEmpty()) {
            csvAlertImage.setVisible(false);
            csvAlert.setStyle("-fx-text-fill: #7c7c7c;");
        } else if(csv.getText().isEmpty() && (!tar1.getText().isEmpty() || !tar2.getText().isEmpty() || !tar3.getText().isEmpty() || !tar4.getText().isEmpty())){
            csvAlertImage.setVisible(true);
            csvAlert.setStyle("-fx-text-fill: #fc0000;");
        }
    }


    // Mostrar errores en csv
    private void cambiarEstiloCsv() {
        String csvText = csv.getText();
        boolean csvValido = csvText.length() == 3;
        boolean tarVacias = tar1.getText().isEmpty() && tar2.getText().isEmpty() && tar3.getText().isEmpty() && tar4.getText().isEmpty();

        if (!csvValido || (!tarVacias && csv.getText().isEmpty())) {
            csvAlertImage.setVisible(true);
            csvAlert.setStyle("-fx-text-fill: #fc0000;");
        } else {
            csvAlertImage.setVisible(false);
            csvAlert.setStyle("-fx-text-fill: #7c7c7c;");
        }

        if (tarVacias && csvValido) {
            cambiarEstiloTar1();
        } else {
            cambiarEstiloTar1();
        }
    }

    private void limpiarCampos() {
        nombre.clear();
        apellidos.clear();
        tel.clear();
        nick.clear();
        pwd.clear();
        pwd1.clear();
        tar1.clear();
        tar2.clear();
        tar3.clear();
        tar4.clear();
        csv.clear();

        foto.setImage(imageArray[0]);

        nameAlertImage.setVisible(false);
        nameAlert.setStyle("-fx-text-fill: #7c7c7c;");
        apellidosAlertImage.setVisible(false);
        apellidosAlert.setStyle("-fx-text-fill: #7c7c7c;");
        pwdAlertImage.setVisible(false);
        pwdAlert.setStyle("-fx-text-fill: #7c7c7c;");
        pwd1AlertImage.setVisible(false);
        pwd1Alert.setStyle("-fx-text-fill: #7c7c7c;");
        telAlertImage.setVisible(false);
        telAlert.setStyle("-fx-text-fill: #7c7c7c;");
        tarAlertImage1.setVisible(false);
        tarAlert1.setStyle("-fx-text-fill: #7c7c7c;");
        csvAlertImage.setVisible(false);
        csvAlert.setStyle("-fx-text-fill: #7c7c7c;");
    }
    
    // Método para verificar las condiciones de la contraseña
    private boolean checkPasswordRequirements() {
        boolean hasLetter = false;
        boolean hasNumber = false;
        String password = pwd.getText();

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            }

            // Si se cumple ambas condiciones, se sale del ciclo
            if (hasLetter && hasNumber) {
                break;
            }
        }

        pwdAlertImage.setVisible(!hasLetter || !hasNumber);
        pwdAlert.setStyle((hasLetter && hasNumber) ? "-fx-text-fill: #7c7c7c;" : "-fx-text-fill: #fc0000;");
        if (hasLetter && hasNumber){return true;}
        else{return false;}
    }
    
    //Método para crear una alerta
    private void mostrarAlert(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cambio Datos");
        alert.setHeaderText(null);
        alert.setContentText("Datos modificados con éxito");
        alert.getDialogPane().setStyle("-fx-background-color: #a4dc8c");
        alert.getDialogPane().getStylesheets().add("styles/EstilosFondo.css");
        alert.showAndWait();
    }
    
    public void cambiarUser(Member m){
        member = m;
        nombre.setText(member.getName());
        apellidos.setText(member.getSurname());
        nick.setText(member.getNickName());
        pwd.setText(member.getPassword());
        pwd1.setText(member.getPassword());
        tel.setText(member.getTelephone());
        if(member.getCreditCard().isEmpty() && member.getSvc() == 0){
            tar1.setText("");
            tar2.setText("");
            tar3.setText("");
            tar4.setText("");
            csv.setText("");
        }else{
            tar1.setText(member.getCreditCard().substring(0, 4));
            tar2.setText(member.getCreditCard().substring(4, 8));
            tar3.setText(member.getCreditCard().substring(8, 12));
            tar4.setText(member.getCreditCard().substring(12, 16));
            csv.setText(member.getSvc() + "");
        }
        foto.setImage(member.getImage());
    }
}
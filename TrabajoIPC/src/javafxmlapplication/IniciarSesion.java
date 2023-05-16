/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import model.*;


public class IniciarSesion implements Initializable {

    @FXML
    private TextField user;
    @FXML
    private PasswordField pwd;
    @FXML
    private ImageView alertImage;
    @FXML
    private Label alertText;
    
    Club greenBall;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            greenBall = Club.getInstance();
        }catch(Exception e){
            System.err.println(e.toString());
        }
        
        alertImage.setVisible(false);
        
        //Permitir todos los caracteres excepto el espacio en el nickname
        user.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                user.setText(oldValue);
            }
        });
        
        //Errores en nickname no usado
        user.setOnKeyTyped(event -> cambiarEstiloNick());
        
        //Errores en nickname vacío
        user.setOnKeyTyped(event -> cambiarEstiloNickEmpty());
        
        //Errores en nickname cuando se cambie de textField
        user.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                cambiarEstiloNickEmpty();
            }
        });
        
        //Permitir solo números y letras en el campo de contraseña
        pwd.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z0-9]*")) {
                pwd.setText(oldValue);
            }
        });
        
        //Errores en pwd cuando se cambie de textField
        pwd.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                cambiarEstiloPwdEmpty();
            }
        });
    }    
    
    @FXML
    private void aceptar(){
        
        if(!user.getText().isBlank() && !pwd.getText().isBlank()){ 
            if(greenBall.existsLogin(user.getText())){
                Member member = greenBall.getMemberByCredentials(user.getText(), pwd.getText());
                if(member != null){ 
                    reiniciar();
                    
                    JavaFXMLApplication.setRoot("PaginaPrincipal");
                    PaginaPrincipal controller = (PaginaPrincipal) JavaFXMLApplication.getController("PaginaPrincipal");
                    controller.cambiarUser(member);
                }else{
                    alertImage.setVisible(true);
                    alertText.setStyle("-fx-text-fill: #fc0000;");
                    alertText.setText("Contraseña incorrecta para el usuario");
                    pwd.requestFocus();
                }
            }else{
                alertImage.setVisible(true);
                alertText.setStyle("-fx-text-fill: #fc0000;");
                alertText.setText("No existe usuario con ese nombre");
                user.requestFocus();
            }
                
        }else{
            alertImage.setVisible(true);
            alertText.setStyle("-fx-text-fill: #fc0000;");
            if(user.getText().isEmpty()){alertText.setText("Campo usuario vacío");}
            else{alertText.setText("Campo contraseña vacío");}
            if(user.getText().isBlank()){ user.requestFocus();}
            else{ pwd.requestFocus();}
        }
    }
    
    @FXML
    private void registrarse(){
        reiniciar();
        JavaFXMLApplication.setRoot("RegistroUsuario");
        
    }
    
    @FXML
    private void volver(){
        reiniciar();
        JavaFXMLApplication.setRoot("PaginaInicio");
    }
    
    private void reiniciar(){
        user.setText("");
        pwd.setText("");
        alertImage.setVisible(false);
        alertText.setText("Inicia con tus credenciales");
        alertText.setStyle("-fx-text-fill: #7c7c7c;");
    }
    //Mostrar errores en nickname cuando no está registrado
    private void cambiarEstiloNick() {
        boolean nickValido = greenBall.existsLogin(user.getText());

        if (!nickValido) {
            alertImage.setVisible(true);
            alertText.setStyle("-fx-text-fill: #fc0000;");
            alertText.setText("No existe usuario con ese nombre");
        } else {
            alertImage.setVisible(false);
            alertText.setStyle("-fx-text-fill: #7c7c7c;");
            alertText.setText("Inicia con tus credenciales");
        }
    }
    
    //Mostrar errores en nickname cuando está vacío
    private void cambiarEstiloNickEmpty() {
        String nickText = user.getText();
        boolean nickValido = !nickText.isEmpty();

        if (!nickValido) {
            alertImage.setVisible(true);
            alertText.setStyle("-fx-text-fill: #fc0000;");
            alertText.setText("Campo usuario vacío");
        } else {
            alertImage.setVisible(false);
            alertText.setStyle("-fx-text-fill: #7c7c7c;");
            alertText.setText("Inicia con tus credenciales");
        }
    }
    
    //Mostrar errores en pwd cunado está vaío
    private void cambiarEstiloPwdEmpty() {
        String pwdText = pwd.getText();
        boolean pwdValido = !pwdText.isEmpty();

        if (!pwdValido) {
            alertImage.setVisible(true);
            alertText.setStyle("-fx-text-fill: #fc0000;");
            alertText.setText("Campo contraseña vacío");
        } else {
            alertImage.setVisible(false);
            alertText.setStyle("-fx-text-fill: #7c7c7c;");
            alertText.setText("Inicia con tus credenciales");
        }
    }
}

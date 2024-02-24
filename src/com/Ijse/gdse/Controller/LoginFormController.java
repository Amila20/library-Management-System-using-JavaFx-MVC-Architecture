package com.Ijse.gdse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public TextField txtUserName;
    public TextField txtPassword;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
       if( txtUserName.getText().equals("User") & txtPassword.getText().equals("1234")){

           Stage stage=new Stage();
           stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashBord.fxml"))));
           stage.show();
       }else {
           Alert alert=new Alert(Alert.AlertType.ERROR,"Wrong Input");
           alert.show();
       }

    }
}

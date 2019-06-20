import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormWindow {
    private Stage windowform;

public void form(Stage windowform){
    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);

    //LOGIN-BUTTON
    Button login = new Button("LOGIN");
    login.setPrefHeight(40);
    login.setDefaultButton(true);
    login.setPrefWidth(100);
    gridPane.add(login, 0, 1, 2, 1);
    GridPane.setHalignment(login, HPos.CENTER);
    GridPane.setMargin(login, new Insets(20, 0,20,0));
    //REGISTER-BUTTON
    Button register = new Button("Register");
    register.setPrefHeight(40);
    register.setDefaultButton(true);
    register.setPrefWidth(100);
    gridPane.add(register, 0, 2, 2, 1);
    GridPane.setHalignment(register, HPos.CENTER);
    GridPane.setMargin(register, new Insets(1, 0,20,0));

    //HANDLE-BUTTONS
    login.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Login login = new Login();
            login.loginform(windowform);
        }
});
    register.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Register register = new Register();
            register.registerform(windowform);
        }
    });

    Scene scene = new Scene(gridPane, 800, 500);
    // Set the scene in primary stage
    windowform.setScene(scene);

    windowform.show();
}



}

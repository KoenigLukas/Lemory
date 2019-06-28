import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lemory.requests.LoginRequest;
import lemory.schemas.LoginUser;
import lemory.schemas.callbacks.LoginCallback;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class Login {


    public void loginform(Stage windowform){
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10,50,50,50));

        //Adding HBox
        HBox hb = new HBox();
        hb.setPadding(new Insets(20,20,20,30));

        //Adding GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        //Implementing Nodes for GridPane
        Label lblUserName = new Label("Username");
        final TextField txtUserName = new TextField();
        Label lblPassword = new Label("Password");
        final PasswordField pf = new PasswordField();
        Button btnLogin = new Button("Login");
        btnLogin.setDefaultButton(true);
        final Label lblMessage = new Label();
        Button register = new Button("Register");
        Button close = new Button("Close");


        //Adding Nodes to GridPane layout
        gridPane.add(lblUserName, 0, 0);
        gridPane.add(txtUserName, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(pf, 1, 1);
        gridPane.add(btnLogin, 2, 1);
        gridPane.add(lblMessage, 1, 2);
        HBox buttons = new HBox();
        buttons.getChildren().add(register);
        buttons.getChildren().add(close);
        bp.setBottom(buttons);



        //Adding text and DropShadow effect to it
        Text text = new Text("Lemory");
        text.setFont(Font.font ("Verdana", 30));

        //Adding text to HBox
        hb.getChildren().add(text);

        //Add ID's to Nodes
        bp.setId("bp");
        gridPane.setId("root");
        btnLogin.setId("btnLogin");
        text.setId("text");

        //Action for btnLogin
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                checklog(txtUserName.getText(),pf.getText(),windowform);
            }
        });

        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Register register = new Register();
                register.registerform(windowform);
            }
        });

        //Add HBox and GridPane layout to BorderPane Layout
        bp.setTop(hb);
        bp.setCenter(gridPane);

        //Adding BorderPane to the scene and loading CSS
        Scene scene = new Scene(bp);
        //scene.getStylesheets().add(getClass().getClassLoader().getResource("login.css").toExternalForm());
        windowform.setScene(scene);
        windowform.setResizable(false);
    }

    private void checklog(String username,String password,Stage window) {
        password= password;
        LoginUser loginUser = new LoginUser(username, password);

        LoginRequest loginRequest = new LoginRequest(loginUser);
        System.out.println(username  + "   :   " + password);

        LoginCallback loginCallback = null;
        try {
            loginCallback = loginRequest.loginUser();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        if (!loginCallback.isSuccess()) {
            System.out.println(loginCallback.getMessage()); //ERROR MESSAGE
        }
        if (loginCallback.isSuccess()) {
            System.out.println(loginCallback.getToken()); //LOGIN TOKEN
            printtoken(loginCallback.getToken());

//            Menu menu = new Menu();
//            menu.menuchoose(window);
            Remenu remenu = new Remenu();
            remenu.choosemenu(window);
        }
    }
    private void printtoken(String token){
        try (BufferedWriter br= new BufferedWriter(new FileWriter("token.tok"))){
            br.write(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

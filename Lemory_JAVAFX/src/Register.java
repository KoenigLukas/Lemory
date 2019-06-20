import lemory.exceptions.DateMissMatchException;
import lemory.exceptions.EmailMissMatchException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;
import lemory.requests.AvailabilityCheck;
import lemory.requests.RegisterRequest;
import lemory.schemas.RegisterUser;
import lemory.schemas.callbacks.LoginCallback;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register {
    String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", Pattern.CASE_INSENSITIVE);
    DatePicker checkInDatePicker;
    public void registerform(Stage windowform){
        windowform.setMinHeight(195);
        windowform.setMinWidth(300);
        int amount=0;

        GridPane gridPane=createRegistrationFormPane();
        addUIControls(gridPane,windowform);
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage
        windowform.setScene(scene);
    }

    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane,Stage window) {
        // Add Header
        Label headerLabel = new Label("Registration Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Username Label
        Label usernamelabel = new Label("Username : ");
        gridPane.add(usernamelabel, 0,1);

        // Add Username Text Field
        TextField usernamefield = new TextField();
        usernamefield.setPrefHeight(40);
        gridPane.add(usernamefield, 1,1);
        // Add Password Label
        Label passwordlabel = new Label("Password : ");
        gridPane.add(passwordlabel, 0,2);

        // Add Password Text Field
        TextField passwordfield = new PasswordField();
        passwordfield.setPrefHeight(40);
        gridPane.add(passwordfield, 1,2);

        // Add E-Mail Label
        Label emaillabel = new Label("E-Mail : ");
        gridPane.add(emaillabel, 0,3);

        // Add E-Mail Text Field
        TextField emailfield = new TextField();
        emailfield.setPrefHeight(40);
        gridPane.add(emailfield, 1,3);

        // Add FirstName Label
        Label firstnameLabel = new Label("First-Name : ");
        gridPane.add(firstnameLabel, 0,4);

        // Add FirstName Text Field
        TextField firstnameField = new TextField();
        firstnameField.setPrefHeight(40);
        gridPane.add(firstnameField, 1,4);

        // Add LastName Label
        Label lastnameLabel = new Label("Last-Name : ");
        gridPane.add(lastnameLabel, 0,5);

        // Add LastName Text Field
        TextField lastnameField = new TextField();
        lastnameField.setPrefHeight(40);
        gridPane.add(lastnameField, 1,5);

        //Add Birthdate Label
        Label birthdateLabel = new Label("Birt-Date : ");
        gridPane.add(birthdateLabel, 0,6);
        //Add Birtdate Picker
        checkInDatePicker = new DatePicker();
        gridPane.add(checkInDatePicker, 1, 6);
        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 7, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(checkInDatePicker.getValue());
                if(usernamefield.getText().isEmpty() || usernamefield.getText().length()<3 || usernamefield.getText().length()>15) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your Username | Min 3 MAX 15 |");
                    return;
                }
                if(passwordfield.getText().isEmpty() || passwordfield.getText().length()<5 || passwordfield.getText().length()>30) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your Password | Min 5 MAX 30 |");
                    return;
                }
                if(emailfield.getText().isEmpty() || !validateemail(emailfield.getText())) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your E-Mail");
                    return;
                }
                if(firstnameField.getText().isEmpty() || firstnameField.getText().length()<2 || firstnameField.getText().length()>15) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your First-name | Min 2 MAX 15 |");
                    return;
                }
                if(lastnameField.getText().isEmpty() || lastnameField.getText().length()<2 || lastnameField.getText().length()>15) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your Last-Name | Min 2 MAX 15 |");
                    return;
                }
                if(checkInDatePicker.getValue() == null) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter Birth-Date");
                    return;
                }
                String birthdate= checkInDatePicker.getValue() + "";
                registeruser3(usernamefield.getText(), passwordfield.getText(),emailfield.getText(),firstnameField.getText(),lastnameField.getText(),birthdate,window);
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "You are now registered " + usernamefield.getText());
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static boolean validateemail(String emailstr){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailstr);
        return matcher.find();
    }


    private void registeruser3(String username, String password,String email,String first_name,String last_name,String birth_date,Stage window){
        AvailabilityCheck check = new AvailabilityCheck();

        boolean email_available = false;
        try {
            email_available = check.checkEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean username_available = false;
        try {
            username_available = check.checkUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RegisterUser user = null;
        try {
            user = new RegisterUser(email_available,username_available,username,password,email,first_name,last_name,birth_date);
        } catch (EmailMissMatchException e) {
            e.printStackTrace();
        } catch (DateMissMatchException e) {
            e.printStackTrace();
        }

        RegisterRequest register = null;
        try {
            register = new RegisterRequest(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginCallback login = null;
        try {
            login = register.registerUser();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!login.isSuccess()){
            System.out.println(login.getMessage()); //ERROR MESSAGE
        }
        if(login.isSuccess()){
            System.out.println(login.getToken()); //LOGIN TOKEN
            Login loginpage = new Login();
            loginpage.loginform(window);
        }
    }
}

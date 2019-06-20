
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Window;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Memory extends Application {

    Stage window;
    GridPane root;
    int amount;
    GameLogic gl;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private DatePicker checkInDatePicker;
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        registerform();
        window.setTitle("Memory");
        Scene scene;
        root = new GridPane();
        gl = new GameLogic(root,window,16);

        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }





    public int registerform(){
        window = new Stage();
        window.setMinHeight(195);
        window.setMinWidth(300);
        gl = new GameLogic();
        int amount=0;

        GridPane gridPane=createRegistrationFormPane();
        addUIControls(gridPane);
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage
        window.setScene(scene);

        window.showAndWait();
        return amount;
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

    private void addUIControls(GridPane gridPane) {
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
                Register reg= new Register(usernamefield.getText(),passwordfield.getText(),emailfield.getText(),firstnameField.getText(),lastnameField.getText(),birthdate);
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Welcome " + usernamefield.getText());
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


}

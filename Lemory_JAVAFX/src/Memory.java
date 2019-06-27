
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Locale;


public class Memory extends Application {

    Stage window;
    GridPane root;
    GameLogic gl;

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        FormWindow formWindow=new FormWindow();
        formWindow.form(window);
    }

    public void startmemory(Stage window){
        window.setTitle("Memory");
        root = new GridPane();
        gl = new GameLogic(root,window,16);
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.showAndWait();
    }

}

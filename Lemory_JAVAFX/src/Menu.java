import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import lemory.requests.GetScoreRequest;
import lemory.schemas.callbacks.GetScoreCallback;
import java.io.IOException;

public class Menu {
    public void menuchoose(Stage windowform){
//        set Window Size
        windowform.setMinHeight(195);
        windowform.setMinWidth(300);

//        create Scene & Hbox & Vbox
        Scene scene;
        VBox vb = new VBox();
        HBox hbstats = new HBox();

//        Lemory & Menu text & add Child
        Text lemory = new Text("Lemory");
        lemory.setFont(Font.font ("Verdana", 30));
        Text menu = new Text("Menu");
        menu.setFont(Font.font ("Verdana", 30));

        vb.getChildren().add(lemory);
        vb.getChildren().add(menu);

//        create Button
        Button btnPlay = new Button("Play");
//      Close-Game Button
        Button close = new Button();
        close.setText("Close Game");


        vb.getChildren().add(btnPlay);
        vb.getChildren().add(close);

//        Get Score & Display
        Label timename = new Label("Time:");
        Label time = new Label();
        ReadToken rt = new ReadToken();
        GetScoreRequest scoreRequest = null;
        try {
            scoreRequest = new GetScoreRequest(rt.token());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No token");
        }

        GetScoreCallback getScoreCallback = null;

        try {
            getScoreCallback = scoreRequest.getScore();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!getScoreCallback.isSuccess()){
            System.out.println("Success:"+getScoreCallback.isSuccess()+" Message:"+getScoreCallback.getMessage());
        }
        else{
            System.out.println("Success:"+getScoreCallback.isSuccess()+" Win percentage:"+getScoreCallback.getWon()+" Average Time:"+getScoreCallback.getTime());
            time.setText(""+getScoreCallback.getTime());
        }


//        Add Labes To Hbox & adds Hbox to vb
        hbstats.getChildren().add(timename);
        hbstats.getChildren().add(time);
        vb.getChildren().add(hbstats);

        scene = new Scene(vb);
        windowform.setScene(scene);

//        Button Handler
        btnPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Memory memory = new Memory();
                try {
                    memory.startmemory(windowform);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

    }

}

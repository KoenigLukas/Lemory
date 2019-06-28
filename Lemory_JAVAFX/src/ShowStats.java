import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lemory.requests.DeleteStatsRequest;
import lemory.requests.GetBestTimeRequest;
import lemory.requests.GetScoreRequest;
import lemory.requests.GetTimesPlayedRequest;
import lemory.schemas.callbacks.BasicCallback;
import lemory.schemas.callbacks.GetBestTimeCallback;
import lemory.schemas.callbacks.GetScoreCallback;
import lemory.schemas.callbacks.GetTimesPlayedCallback;

import java.io.IOException;

public class ShowStats {

    public void printStats(Stage windowform) throws IOException {
        Scene scene;
        VBox vb = new VBox();
        vb.setPrefSize(800,600);
        HBox timevertical = new HBox();
        HBox gamesplayedvertical = new HBox(0);
        HBox besttimevertical = new HBox();
        HBox buttons = new HBox();

//        TextLabes
        Label avgtimename = new Label("Average Time: ");
        Label avgtime = new Label();
        Label besttimename = new Label("Best Time: ");
        Label besttime = new Label();
        Label gamesname = new Label("Games Played: ");
        Label games = new Label();

//        Buttons erstellen
        Button back = new Button("Back To Menu");
        Button delete = new Button("Reset Stats");

//        GET TOKEN
        ReadToken tk = new ReadToken();

        String token=tk.token();

//        Times Played
        GetTimesPlayedRequest timesPlayedRequest = new GetTimesPlayedRequest(token);

        GetTimesPlayedCallback timesPlayedCallback = null;


        try {
            timesPlayedCallback = timesPlayedRequest.getTimesPlayed();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(!timesPlayedCallback.isSuccess()){
            System.out.println("Success:"+timesPlayedCallback.isSuccess()+" Message:"+timesPlayedCallback.getMessage());
        }
        else{
            System.out.println("Success:"+timesPlayedCallback.isSuccess()+" Times Played:"+timesPlayedCallback.getPlayed());
            games.setText(""+timesPlayedCallback.getPlayed());
        }
//        GET Average Time
        GetScoreRequest scoreRequest = null;
        scoreRequest = new GetScoreRequest(token);

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
            avgtime.setText(""+getScoreCallback.getTime());
        }

//        GET Best Time
        GetBestTimeRequest bestTimeRequest = new GetBestTimeRequest(token);

        GetBestTimeCallback bestTimeCallback = null;

        try {
            bestTimeCallback = bestTimeRequest.getBestTime();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!getScoreCallback.isSuccess()){
            System.out.println("Success:"+bestTimeCallback.isSuccess()+" Message:"+bestTimeCallback.getMessage());
        }
        else{
            System.out.println("Success:"+bestTimeCallback.isSuccess()+" Best Time:"+bestTimeCallback.getTime());
            besttime.setText(""+bestTimeCallback.getTime());
        }


//        Adds label to Hbox/Vbox

        timevertical.getChildren().add(avgtimename);
        timevertical.getChildren().add(avgtime);
        vb.getChildren().add(timevertical);

        besttimevertical.getChildren().add(besttimename);
        besttimevertical.getChildren().add(besttime);
        vb.getChildren().add(besttimevertical);

        gamesplayedvertical.getChildren().add(gamesname);
        gamesplayedvertical.getChildren().add(games);
        vb.getChildren().add(gamesplayedvertical);

//        Button Handler
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Remenu remenu = new Remenu();
                remenu.choosemenu(windowform);
            }
        });
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DeleteStatsRequest deleteStatsRequest = null;

                    deleteStatsRequest = new DeleteStatsRequest(token);

                BasicCallback deleteSubmitCallback = null;
                try {
                    deleteSubmitCallback = deleteStatsRequest.deleteStats();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Success:"+deleteSubmitCallback.isSuccess()+" Message:"+deleteSubmitCallback.getMessage());
            }
        });

//        Scene & Add Button
        buttons.getChildren().add(back);
        buttons.getChildren().add(delete);
        vb.getChildren().add(buttons);

        scene = new Scene(vb);
        windowform.setScene(scene);
    }


}


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lemory.requests.SubmitScoreRequest;
import lemory.schemas.SubmitScore;
import lemory.schemas.callbacks.BasicCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GameLogic{
    private static Stage window;
    private static Stage mainwindow;
    private static GridPane root;
    private ArrayList<MemoryButton> buttons;
    private static int counter=0;
    private static int amount=0;
    private static boolean chooser = true;
    private static int checker=0;
    private static int samememchecker=0;
    private static int player=0;
    private static int temp=0;
    private static Text wplayer = new Text();
    private static Text winner  = new Text();
    private static Text pPlayer1 = new Text();
    private static Text pPlayer2 = new Text();
    private static int[] spielerg = new int[2];
    private static MemoryButton[] buttonflipped;

    public GameLogic(GridPane root,Stage window, int amount){
        setRoot(root);
        spielerg[0]=0;
        spielerg[1]=0;
        mainwindow=window;
        setAmount(amount);
        buttonflipped = new MemoryButton[2];
        init(amount);
    }

    public GameLogic(){
    }

    public void init(int amount){
        Time time = new Time();
        time.printTime();
        if(amount==0){
            closeGame();
        }
        this.buttons= new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            MemoryButton mb = new MemoryButton(i%amount/2);
            mb.addEventHandler(ActionEvent.ACTION, mb);
            buttons.add(mb);
        }
        Collections.shuffle(buttons);

        int k=0;
        for(int j=0;j<Math.sqrt(amount);j++){
            for(int h=1;h<=Math.sqrt(amount);h++){
                buttons.get(k).setPrefSize(144,136);
                root.add(buttons.get(k),j,h);
                buttons.get(k).flip();
                k++;
            }
        }
        //Which Player
        wplayer.setText("Your Turn");
        wplayer.setFont(new Font("Arial",16));
        root.add(wplayer,0,6);
        //Player 1 Points
        pPlayer1.setText("You : " + spielerg[0]);
        pPlayer1.setFont(new Font("Arial",16));
        root.add(pPlayer1,0,0);
//        //Player 2 Points
//        pPlayer2.setText("Player 2 : " + spielerg[1]);
//        pPlayer2.setFont(new Font("Arial",16));
//        root.add(pPlayer2,((int)Math.sqrt(amount))-1,0);
    }

    public GridPane getRoot() {
        return root;
    }

    public void setRoot(GridPane root) {
        this.root = root;
    }

    public static void setAmount(int amount) {
        GameLogic.amount = amount;
    }

//    public void whichplayer(){
//        chooser = !chooser;
//        if(chooser){
//            player=1;
//        }else{
//            player=2;
//        }
//        //wplayer.setText("Player " + player + " is on move");
//    }


    public void getpoints(){
        if(chooser) {
            spielerg[0]++;
        }
//        }else{
//            spielerg[1]++;
//        }
    }

    public void flipordelete(MemoryButton mb){
        if(checker<2) {
            buttonflipped[checker] = mb;
            checker++;
            mb.flip();
        }else if(checker>=2){
            checker++;
        }
        if(checker>=2) {
            if (buttonflipped[0].getIdentification() == buttonflipped[1].getIdentification()) {
                getpoints();
                counter++;
                buttonflipped[0]=null;
                buttonflipped[1]=null;
                checker=0;
                samememchecker=1;
            }else{
                samememchecker=0;
            }
            if(checker>=3) {
                MemoryButton mb2 = mb;
                mb2.flip();
                buttonflipped[0].flip();
                buttonflipped[0]=null;
                buttonflipped[1].flip();
                buttonflipped[1]=null;
                buttonflipped[0]=mb2;
                checker=1;
            }
        }

        //Change Player
//        if(checker==2 && samememchecker!=1 && counter != 8){
//            //whichplayer();
//        }

        //Player 1 Points
        pPlayer1.setText("You : " + spielerg[0]);

        //Player 2 Points
        pPlayer2.setText("Player 2 : " + spielerg[1]);

        //Choose Winner
        if(counter==8){
            getwinner();
        }
    }



    public void getwinner(){

//        if(spielerg[0]>spielerg[1]){
//            temp=1;
//        }else if(spielerg[0]<spielerg[1]){
//            temp=2;
//        }else{
//            temp=0;
//        }
//
//        if(temp!=0){
//            winner.setText("Player " + temp + " wins!");
//        }else{
//            winner.setText("Draw");
//        }
        winner.setText("You have won!");
        winner.setFont(new Font("Arial",16));
        root.add(winner,((int)Math.sqrt(amount))-1,((int)Math.sqrt(amount))+1);
        restartorcloseGame();

    }

    public void closeGame(){
        System.exit(0);
    }

    public void restartGame(){
        window.close();
        root.getChildren().clear();
        counter=0;
        chooser = true;
        checker=0;
        samememchecker=0;
        player=0;
        temp=0;
        wplayer.setText("");
        winner.setText("");
        pPlayer1.setText("");
        pPlayer2.setText("");
        spielerg[0]=0;
        spielerg[1]=0;
        buttonflipped[0]=null;
        buttonflipped[1]=null;
        init(amount);
    }

    public void restartorcloseGame(){
//        Send Score
        Time time = new Time();
        ReadToken readToken = new ReadToken();
        int time1 =time.readTime();
        String token = null;
        try {
            token = readToken.token();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubmitScore score = new SubmitScore(true, time1);
        SubmitScoreRequest submitScore = new SubmitScoreRequest(score, token);
        BasicCallback submitCallback = null;

        try {
            submitCallback = submitScore.sbumitScore();
        } catch (IOException var17) {
            var17.printStackTrace();
            System.out.println("Fick di kenig");
        }
//        Create Window
        window = new Stage();
        window.setTitle("Restart or Close Game");
        window.setMinHeight(195);
        window.setMinWidth(300);

        VBox layout = new VBox(2);
        Text howlong= new Text("How long it took you: "+time1);
        layout.setAlignment(Pos.CENTER);
        Scene scene2 = new Scene(layout,200,200);
        layout.getChildren().add(howlong);
        //Restart Button
        Button restart = new Button();
        restart.setText("Restart Game");
        restart.setOnAction(e -> restartGame());
        layout.getChildren().add(restart);
        //Close-Game Button
        Button close = new Button();
        close.setText("Close Game");
        close.setOnAction(e -> closeGame());
        layout.getChildren().add(close);
        //Menu Button
        Button menu = new Button();
        menu.setText("Back To Menu");
        menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Remenu remenu = new Remenu();
                remenu.choosemenu(window);
                mainwindow.close();
            }
        });
        layout.getChildren().add(menu);
        //Show Window
        window.setScene(scene2);
        window.showAndWait();
    }

}

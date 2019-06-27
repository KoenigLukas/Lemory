import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import lemory.requests.DeleteStatsRequest;
import lemory.schemas.callbacks.BasicCallback;

public class Remenu {

    public void choosemenu(Stage windowform){

        Scene scene;
        Pane root = new Pane();
        root.setPrefSize(800, 600);

        try (InputStream is = Files.newInputStream(Paths.get("./images/bg.jpg"))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(860);
            img.setFitHeight(600);
            root.getChildren().add(img);
        }
        catch (IOException e) {
            System.out.println("Couldn't load image"+e);
        }
        Title title = new Title("L E M O R Y");
        title.setTranslateX(75);
        title.setTranslateY(200);

        MenuItem itemExit = new MenuItem("EXIT",99,windowform);
        itemExit.setOnMouseClicked(event -> System.exit(0));

        MenuBox menu = new MenuBox(
                new MenuItem("Play Singleplayer", 0,windowform),
                new MenuItem("Play Multiplayer",1, windowform),
                new MenuItem("Reset Stats",2,windowform),
                new MenuItem("Stats",3,windowform),
                itemExit);
        menu.setTranslateX(100);
        menu.setTranslateY(300);

        root.getChildren().addAll(title, menu);
        scene = new Scene(root);
        windowform.setScene(scene);


    }

    private static class Title extends StackPane {
        public Title(String name) {
            Rectangle bg = new Rectangle(250, 60);
            bg.setStroke(Color.WHITE);
            bg.setStrokeWidth(2);
            bg.setFill(null);

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 50));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
        }
    }

    private static class MenuBox extends VBox {
        public MenuBox(MenuItem... items) {
            getChildren().add(createSeparator());

            for (MenuItem item : items) {
                getChildren().addAll(item, createSeparator());
            }
        }

        private Line createSeparator() {
            Line sep = new Line();
            sep.setEndX(200);
            sep.setStroke(Color.DARKGREY);
            return sep;
        }
    }

    private static class MenuItem extends StackPane {
        public MenuItem(String name, int option, Stage windowform) {
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                    new Stop(0, Color.DARKVIOLET),
                    new Stop(0.1, Color.BLACK),
                    new Stop(0.9, Color.BLACK),
                    new Stop(1, Color.DARKVIOLET)
            });

            Rectangle bg = new Rectangle(200, 30);
            bg.setOpacity(0.4);

            Text text = new Text(name);
            text.setFill(Color.DARKGREY);
            text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 22));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setFill(gradient);
                text.setFill(Color.WHITE);
            });


            setOnMouseExited(event -> {
                bg.setFill(Color.BLACK);
                text.setFill(Color.DARKGREY);
            });

            setOnMousePressed(event -> {
                bg.setFill(Color.DARKVIOLET);
            });

            setOnMouseReleased(event -> {
                bg.setFill(gradient);
                switch (option){
                    case 0: Memory memory = new Memory();
                            memory.startmemory(windowform);
                            break;
                    case 1: break;
                    case 2: ReadToken readToken = new ReadToken();
                        DeleteStatsRequest deleteStatsRequest = null;
                        try {
                            deleteStatsRequest = new DeleteStatsRequest(readToken.token());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        BasicCallback deleteSubmitCallback = null;
                            try {
                                deleteSubmitCallback = deleteStatsRequest.deleteStats();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            System.out.println("Success:"+deleteSubmitCallback.isSuccess()+" Message:"+deleteSubmitCallback.getMessage());
                            break;
                    case 3: break;

                }
            });
        }
    }


}

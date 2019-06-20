
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import static java.lang.Thread.sleep;

public class MemoryButton extends Button implements EventHandler<ActionEvent> {
    private int id;
    private boolean visible;
    GameLogic gl = new GameLogic();
    ImageView imgv;
    ImageView back;
    String text;
    String[] arr = {"Deutsch","Mathe","Geschichte","Sport","Englisch","Informatik","Zeichnen","Religion"};
    public MemoryButton(int identification) {
        this.id = identification;
        this.visible = true;
        this.text=arr[id];
//        imgv=new ImageView("./Images/" + String.valueOf(this.id) + ".png");
            back=new ImageView("./Images/back.png");

    }

    public boolean getvisible() {
        return visible;
    }

    public void flip() {
        this.visible = !visible;
        if(visible){
            this.setGraphic(null);
            this.setText(text);
        }else{
            this.setGraphic(back);
            this.setText("");
        }
    }

    public int getIdentification() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryButton)) return false;

        MemoryButton that = (MemoryButton) o;

        return getIdentification() == that.getIdentification();
    }

    @Override
    public void handle(ActionEvent event) {
        gl.flipordelete(this);
    }
}

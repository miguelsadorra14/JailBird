package softwaredesign;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

public class Tracker extends VBox implements Observer {
    double value;
    ProgressBar pb = new ProgressBar();
    public Tracker(String name) {
        value = 1.0;
        pb.setProgress(value);
        Label text = new Label(name);
        text.setStyle("-fx-font-size: 1.8em; -fx-text-fill: white;");
        this.getChildren().addAll(text, pb);
        this.setAlignment(Pos.CENTER);
    }

    @Override
    public void update(int val) {
        this.value = ((double) val) / 100;
        if(this.value < 0 ){
            this.value = 0;
        }
        pb.setProgress(this.value);
    }
}

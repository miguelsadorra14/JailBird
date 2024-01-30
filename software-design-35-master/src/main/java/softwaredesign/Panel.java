package softwaredesign;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;

public class Panel {
    public List<myButton> buttons;
    public Tracker hungerTracker = new Tracker("Hunger");
    public Tracker sleepTracker = new Tracker("Sleep");
    public Tracker moodTracker = new Tracker("Mood");
    public Tracker hygieneTracker = new Tracker("Hygiene");
    public Tracker healthTracker = new Tracker("Health");
    private final VBox sidebarLeft;
    private final VBox sidebarRight;
    private final HBox bottom;
    public Panel() {
        sidebarLeft = new VBox(20);
        sidebarRight = new VBox(20);

        sidebarLeft.setPadding(new Insets(10, 50, 50, 50));
        sidebarLeft.setAlignment(Pos.CENTER);

        sidebarRight.setPadding(new Insets(10, 50, 50, 50));
        sidebarRight.setAlignment(Pos.CENTER);

        bottom = new HBox();

        buttons = List.of(new myButton(""), new myButton(""), new myButton(""), new myButton(""), new myButton(""));

        sidebarLeft.getChildren().addAll(buttons.get(0), buttons.get(1), buttons.get(2));
        sidebarRight.getChildren().addAll(buttons.get(3), buttons.get(4));
    }
    public VBox getRight() {
        sidebarRight.setBackground(Background.fill(Color.DIMGRAY));
        return sidebarRight;
    }
    public VBox getLeft() {
        sidebarLeft.setBackground(Background.fill(Color.DIMGRAY));
        return sidebarLeft;
    }
    public HBox getBottom() {
        bottom.setPrefHeight(180);
        bottom.setBackground(Background.fill(Color.DIMGRAY));
        bottom.setSpacing(40);
        bottom.setAlignment(Pos.CENTER);
        return bottom;
    }
    public void setBottom(Node input) {
        bottom.getChildren().clear();
        bottom.getChildren().add(input);
    }
    public void setButton(int index, String text, EventHandler<MouseEvent> event) {
        buttons.get(index).setFunction(event);
        buttons.get(index).rename(text);
    }
    public void setTracker() {
        bottom.getChildren().clear();
        bottom.getChildren().addAll(hungerTracker, hygieneTracker, sleepTracker, moodTracker, healthTracker);
    }
}

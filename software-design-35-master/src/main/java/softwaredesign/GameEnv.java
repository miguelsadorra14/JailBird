package softwaredesign;


import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class GameEnv extends HBox{
    private static GameEnv instance;
    boolean isCharImage = true;
    private boolean pushupDone;
    private Character character;
    public int score;
    private Label scoreGUI;
    private Label timerLabel;
    public ImageView characterModel;
    private final Image cellbg = new Image(getClass().getResource("Cell.jpeg").toExternalForm());
    private final Image backyardbg =  new Image(getClass().getResource("backyardBackground.png").toExternalForm());
    private final BackgroundImage cell = new BackgroundImage(cellbg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true));
    private final BackgroundImage backyard = new BackgroundImage(backyardbg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true));
    private GameEnv() {

    }

    public void placeCharacter(Character myCharacter) {
        this.character = myCharacter;
        characterModel = new ImageView(this.character.charImage);
        characterModel.setFitWidth(200);
        characterModel.setFitHeight(200);

        this.getChildren().add(characterModel);
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(cell));
    }
    public static synchronized GameEnv getInstance() {
        if (instance == null) {
            instance = new GameEnv();
        }
        return instance;
    }
    public void playMinigame(IntegerProperty timeSeconds) {
        score = 0;
        scoreGUI = new Label("SCORE:\n 0");
        timerLabel = new Label();
        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 2em; -fx-text-align: right; -fx-font-weight: bold;");
        timerLabel.setTextAlignment(TextAlignment.RIGHT);
        scoreGUI.setStyle("-fx-font-size: 2em; -fx-font-weight: bold;");
        scoreGUI.setTextAlignment(TextAlignment.CENTER);
        changeToYard();
    }
    public void doPushUp() {
        characterModel.setImage(this.character.charPushUp);
        if (pushupDone) {
            score += 1;
            scoreGUI.setText("SCORE: \n" + score);
            character.increaseMood();
            pushupDone = false;
        }
    }
    public void doPushDown() {
        characterModel.setImage(this.character.charPushDown);
        pushupDone = true;
    }
    private void changeToYard() {
        this.getChildren().clear();
        this.setBackground(new Background(backyard));
        characterModel.setImage(this.character.charPushUp);
        this.getChildren().addAll(scoreGUI, characterModel, timerLabel);
    }
    public void changeToCell() {
        this.getChildren().clear();
        this.setBackground(new Background(cell));
        characterModel.setImage(this.character.charImage);
        this.getChildren().add(characterModel);
    }
    public void specialAbility(){
        if(isCharImage){
            characterModel.setImage(this.character.specialImage);
            isCharImage = false;
        }
        else{
            characterModel.setImage(this.character.charImage);
            isCharImage = true;
        }
    }
    public Character getCharacter() {
        return this.character;
    }
    public void clearCharacter(){
        this.getChildren().clear();
    }
}

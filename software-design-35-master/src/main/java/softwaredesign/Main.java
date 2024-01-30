package softwaredesign;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class Main extends Application {
    private BorderPane root;
    private String chosenCharType;
    private Character myCharacter;
    private GameEnv myGameEnv;
    private Panel myPanel;
    private static final Integer STARTTIME = 15;
    private static final Integer CHECKTIME = 2;
    @Override
    public void start(Stage stage) throws IOException {
        root = new BorderPane();
        titleState();

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        FadeTransition titlePageFade = new FadeTransition(Duration.seconds(1), root);

        delay.setOnFinished(e -> titlePageFade.play());
        delay.play();

        titlePageFade.setFromValue(1.0);
        titlePageFade.setToValue(0.0);
        titlePageFade.setCycleCount(1);
        titlePageFade.setOnFinished(titleEvent -> {
            FadeTransition fade2 = new FadeTransition(Duration.seconds(1.5), root);
            fade2.setFromValue(0.0);
            fade2.setToValue(1.0);
            fade2.play();
            startCustomizePage();
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(CHECKTIME), e ->{
            if(myCharacter != null) {
                if(myCharacter.isAlive) {
                    myCharacter.updateHP();
                    myCharacter.updateVitals();
                }
                else {
                    System.out.println("YOUR PRISONER DIED\nGAMVE OVER");
                    myGameEnv.clearCharacter();
                    myCharacter = null;
                    root.getChildren().clear();

                    FadeTransition gameOverFade = new FadeTransition(Duration.seconds(1), root);
                    gameOverFade.setFromValue(1.0);
                    gameOverFade.setToValue(0.5);
                    gameOverFade.setCycleCount(1);
                    gameOverFade.setOnFinished(gameOverEvent -> {
                        FadeTransition fade2 = new FadeTransition(Duration.seconds(0.5), root);
                        fade2.setFromValue(0.5);
                        fade2.setToValue(1.0);
                        fade2.play();
                        gameOverState();
                    });
                    gameOverFade.play();
                }
            }

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Scene scene = new Scene(root);
        stage.setTitle("Jailbird!");
        stage.getIcons().add(new Image(getClass().getResource("Buff.png").toExternalForm()));
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void startCustomizePage() {
        CustomizationPage selectCharacter = new CustomizationPage();
        myPanel = new Panel();

        Button selectButton = new Button("Select");
        selectButton.setPrefSize(500, 100);
        selectButton.setStyle("-fx-font-size: 3em;");
        myPanel.setBottom(selectButton);

        root.setBottom(myPanel.getBottom());
        root.setLeft(myPanel.getLeft());
        root.setRight(myPanel.getRight());
        root.setCenter(selectCharacter);

        selectButton.setOnAction(event -> {
            System.out.println("CONFIRMED Character: " + selectCharacter.chosenCharacter);

            if (selectCharacter.chosenCharacter != null) {
                chosenCharType = selectCharacter.chosenCharacter;
                CharacterFactory charFactory = new CharacterFactory();
                myCharacter = charFactory.createCharacter(chosenCharType);

                startGame();
            }
        });
    }
    private Label authorIdentity(String name){
        Label nameLabel = new Label(name);
        nameLabel.setTextFill(Color.WHITE);
        Font font = new Font("Comic Sans MS", 14);
        nameLabel.setFont(font);
        return nameLabel;
    }
    private void titleState() {
        ImageView titleImageView = new ImageView(new Image(getClass().getResource("JailBirdTitle.png").toExternalForm()));
        titleImageView.setFitWidth(600);
        titleImageView.setFitHeight(300);
        HBox titleHBox = new HBox(titleImageView);
        titleHBox.setAlignment(Pos.TOP_CENTER);

        Label groupLabel = authorIdentity("\u00a9 Asian Persuasion - Group 35:");
        Label TAlabel = authorIdentity("TA: Ariana Vargas Pastor");
        Label member1 = authorIdentity("Alvaro Pratama Maharto/2734663");
        Label member2 = authorIdentity("Mahmoud Ashtar/2696767");
        Label member3 = authorIdentity("Michael Evan Sutanto/2720871");
        Label member4 = authorIdentity("Miguel Sadorra/2728578");

        VBox membersVBox = new VBox(5);
        membersVBox.getChildren().addAll(groupLabel, TAlabel, member1, member2, member3, member4);
        membersVBox.setAlignment(Pos.CENTER);

        VBox titleContainer = new VBox(titleHBox, membersVBox);
        titleContainer.setAlignment(Pos.CENTER);

        root.setCenter(titleContainer);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    }

    private void gameOverState(){
        ImageView gameOverImage = new ImageView(new Image(getClass().getResource("gameOver.png").toExternalForm()));
        gameOverImage.setFitWidth(400);
        gameOverImage.setFitHeight(300);
        HBox imageHBox = new HBox(gameOverImage);
        imageHBox.setAlignment(Pos.CENTER);

        Button playAgainButton = new Button("Play Again");
        playAgainButton.setPrefSize(150, 50);
        playAgainButton.setStyle("-fx-background-color: black");
        playAgainButton.setFont(new Font("Arial", 20));
        playAgainButton.setTextFill(Color.WHITE);
        playAgainButton.setOnAction(event -> startCustomizePage());

        Button exitButton = new Button("Exit");
        exitButton.setPrefSize(150, 50);
        exitButton.setStyle("-fx-background-color: black");
        exitButton.setFont(new Font("Arial", 20));
        exitButton.setTextFill(Color.WHITE);
        exitButton.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });

        HBox gameOverButtons = new HBox(20, playAgainButton, exitButton);
        gameOverButtons.setAlignment(Pos.CENTER);

        VBox gameOverContainer = new VBox(imageHBox, gameOverButtons);
        gameOverContainer.setAlignment(Pos.CENTER);

        root.setCenter(gameOverContainer);
        root.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, null, null)));
    }

    public void startGame(){
        myGameEnv = GameEnv.getInstance();
        myGameEnv.placeCharacter(myCharacter);
        myGameEnv.changeToCell();
        root.setCenter(myGameEnv);

        myPanel.setButton(0, "Eat", (e -> {
            System.out.println("eat");
            myGameEnv.getCharacter().feed();
        }));
        myPanel.setButton(1, "Play Minigame", (e -> {
            myPanel.setButton(0, "Push Down" ,(event2-> myGameEnv.doPushDown()));
            myPanel.setButton(1, "Go Back",(event2-> startGame()));
            myPanel.setButton(2, "",(event2-> System.out.println("empty button")));
            myPanel.setButton(3, "Push Up",(event2-> myGameEnv.doPushUp()));
            myPanel.setButton(4, "" ,(event2-> System.out.println("empty button")));

            // Set the timer for the minigame
            IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), event -> {
                        timeSeconds.set(timeSeconds.get() - 1);
                    })
            );
            timeline.setCycleCount(STARTTIME);
            timeline.setOnFinished(event2 -> startGame());
            timeline.play();
            myGameEnv.playMinigame(timeSeconds);
        }));
        myPanel.setButton(2, "Clean", (e -> {
            System.out.println("clean");
            myGameEnv.getCharacter().clean();
        }));
        myPanel.setButton(3, "Sleep", (e -> {
            System.out.println("sleep");
            myGameEnv.getCharacter().sleep();
        }));
        myPanel.setButton(4, "Special Ability", (e -> {
            System.out.println("Special");
            myGameEnv.specialAbility();
        }));

        myPanel.setTracker();

        root.setBottom(myPanel.getBottom());
        root.setLeft(myPanel.getLeft());
        root.setRight(myPanel.getRight());

        myCharacter.hungerVital.attach(myPanel.hungerTracker);
        myCharacter.hygieneVital.attach(myPanel.hygieneTracker);
        myCharacter.sleepVital.attach(myPanel.sleepTracker);
        myCharacter.moodVital.attach(myPanel.moodTracker);
        myCharacter.healthVital.attach(myPanel.healthTracker);
    }

    public static void main(String[] args) {
        launch();
    }
}
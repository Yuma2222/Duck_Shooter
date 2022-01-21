package com.example.duck;

import com.example.duck.Model.CONFIG;
import com.example.duck.Model.CloudyButton;
import com.example.duck.Model.DuckButton;
import com.example.duck.Model.score;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class DuckShooter extends Application {
    private Stage ourStage;
    private final StackPane gamePane = new StackPane();
    private final AtomicInteger time = new AtomicInteger(0);
    private final Button timer = new Button();

    Timeline spawning = new Timeline(new KeyFrame(Duration.seconds(2), actionEvent -> spawnDuck()));
    Timeline clouds = new Timeline(new KeyFrame(Duration.seconds(10), actionEvent -> gamePane.getChildren().add(new CloudyButton())));
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
        timer.setText(String.valueOf(time.getAndIncrement()));
        areWeDead();
    }));

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        this.ourStage = stage;
        setBackground();
        setTimer();
        spawning.setCycleCount(Timeline.INDEFINITE);
        timeline.setCycleCount(Timeline.INDEFINITE);
        clouds.setCycleCount(4);
        spawning.play();
        timeline.play();
        clouds.play();
        this.ourStage.setFullScreen(true);
        this.ourStage.setScene(new Scene(gamePane, 1000, 1000));
        this.ourStage.setTitle("Duck Shooter");
        this.ourStage.show();
    }

    public void spawnDuck() {
        List<Integer> possibleDucks = List.of(5, 5, 5, 5, 5, 10, 10, 10, 15, 15, 20);
        Integer duckLives = possibleDucks.get(new Random().nextInt(9));
        gamePane.getChildren().add(new DuckButton(new AtomicInteger(duckLives)));
    }

    public void setTimer() {
        timer.setFont(new Font(24.0));
        timer.setBackground(Background.EMPTY);
        gamePane.getChildren().add(timer);
    }

    public void setBackground() {
        gamePane.setBackground(new Background(new BackgroundImage(new Image("file:src/main/resources/Image/background.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    public void areWeDead() {
        if (CONFIG.CONFIG.LIVES <= 1) {
            timeline.stop();
            StackPane endGamePane = new StackPane();
            endGamePane.getChildren().add(returnHighScoresButton());
            endGamePane.getChildren().add(returnExitButton());
            ourStage.close();
            ourStage.setScene(new Scene(endGamePane, 1000, 1000));
            ourStage.setFullScreen(true);
            ourStage.show();
        }
    }

    public Button returnExitButton() {
        Button button = new Button("Exit");
        button.setPrefSize(500,500);
        button.setTranslateX(-500);
        button.setOnMouseClicked(mouseEvent -> ourStage.close());
        return button;
    }

    public Button returnHighScoresButton() {
        Button button = new Button("High Scores");
        button.setPrefSize(500,500);
        button.setTranslateX(500);
        button.setOnMouseClicked(mouseEvent -> {
            StackPane highScores = new StackPane();
            ListView<score> listView = new ListView<>();
            listView.getItems().add(new score(time.get(),"User"));
            highScores.getChildren().add(listView);
            ourStage.close();
            ourStage.setScene(new Scene(highScores, 1000, 1000));
            ourStage.setFullScreen(true);
            ourStage.show();
        });

        return button;
    }


}
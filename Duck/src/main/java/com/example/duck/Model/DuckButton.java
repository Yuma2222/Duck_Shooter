package com.example.duck.Model;

import javafx.animation.PathTransition;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class DuckButton extends Button {
    private AtomicInteger lives;
    private PathTransition transition = new PathTransition();

    public DuckButton(AtomicInteger lives) {
        this.lives = lives;
        this.setBackground(Background.EMPTY);
        this.setOnMouseClicked(mouseEvent -> {
            lives.getAndDecrement();

            if (lives.get() > 0) {
                updateImageView();
            } else {
                die();
            }
        });

        createTransition();
        updateImageView();

        this.toBack();
        this.getGraphic().toBack();
    }

    public void updateImageView() {
        switch (lives.get()) {
            case 5, 10, 15, 20 -> {
                ImageView imageView = new ImageView("file:src/main/resources/Image/" + lives.get() + "_lives_duck.png");
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                this.setGraphic(imageView);
            }
        }
    }

    public void createTransition() {
        transition.setNode(this);
        transition.setPath(createPolyLine());
        transition.setDuration(Duration.seconds(10));
        transition.setOnFinished(actionEvent -> {
            CONFIG.CONFIG.LIVES--;
            this.die();
        });
        transition.play();
    }

    public Polyline createPolyLine() {
        Polyline polyline = new Polyline();
        double y = new Random().nextFloat(400);
        polyline.getPoints().addAll(-1100.0, y, 1500.0, y);

        return polyline;

    }

    public void die() {
        try {
            transition.stop();
            ((Pane) this.getParent()).getChildren().remove(this);
        } catch (Exception ignored) {
        }
    }
}

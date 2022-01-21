package com.example.duck.Model;

import javafx.animation.PathTransition;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

import java.util.Random;

public class CloudyButton extends Button {

    public CloudyButton() {
        this.setBackground(Background.EMPTY);
        this.toFront();

        ImageView imageView = new ImageView("file:src/main/resources/Image/cloud.png");
        imageView.setFitWidth(725);
        imageView.setFitHeight(250);
        this.setGraphic(imageView);
        this.getGraphic().toFront();
        createTransition();
    }

    public void createTransition() {
        PathTransition transition = new PathTransition();
        transition.setNode(this);
        transition.setPath(createPolyLine());
        transition.setDuration(Duration.seconds(4));
        transition.setAutoReverse(true);
        transition.setCycleCount(100);
        transition.play();
    }

    public Polyline createPolyLine(){
        Polyline polyline = new Polyline();
        double x = new Random().nextFloat(2000) - 1000;
        polyline.getPoints().addAll(x, 50.0, x, 350.0);

        return polyline;
    }
}

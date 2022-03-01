package com.mashton;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class JavaFxAsteroids extends Application {
    public static final String WINDOW_TITLE = "A S T E R O I D S";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    int score;

    @Override
    public void start(Stage mainStage) {
        mainStage.setTitle(WINDOW_TITLE);
        mainStage.setResizable(false);

        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root);
        mainStage.setScene(mainScene);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext context = canvas.getGraphicsContext2D();
        root.setCenter(canvas);

        context.setFill(Color.BLUE);
        context.fillRect(0,0, WIDTH, HEIGHT);

        // handles continuous inputs (as long as key is pressed)
        ArrayList<String> keyPressedList = new ArrayList<>();

        // handles discrete inputs (one per key press)
        ArrayList<String> keyJustPressedList = new ArrayList<>();

        mainScene.setOnKeyPressed(
                (KeyEvent event) -> {
                    String keyName = event.getCode().toString();
                    //avoid adding duplicates to list
                    if (!keyPressedList.contains(keyName)) {
                        keyPressedList.add(keyName);
                        keyJustPressedList.add(keyName);
                    }
                }
        );

        mainScene.setOnKeyReleased(
                (KeyEvent event) -> {
                    String keyName = event.getCode().toString();
                    if (keyPressedList.contains(keyName)) {
                        keyPressedList.remove(keyName);
                    }
                }
        );

        Sprite background = new Sprite("Space003-opengameart-800x600.png");
        background.position.set(400,300);

        Sprite spaceShip = new Sprite("DurrrSpaceShip-opengameart-50x50.png");
        spaceShip.position.set(100,300);

        ArrayList<Sprite> laserList = new ArrayList<>();
        ArrayList<Sprite> asteroidList = new ArrayList<>();

        int asteroidCount = 6;
        for (int n = 0; n < asteroidCount; n++) {
           Sprite asteroid = new Sprite("asteroid-opengameeart-100x100.png");
           double x = 500 * Math.random() + 300; // 300-800
           double y = 400 * Math.random() + 100; // 100-500
           asteroid.position.set(x,y);
           double angle = 360 * Math.random();
           asteroid.velocity.setLength(50);
           asteroid.velocity.setAngleInDegrees(angle);
           asteroidList.add(asteroid);
        }

        score = 0;
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long nanoTime) {
                // process user input
                if (keyPressedList.contains("LEFT")) {
                    spaceShip.rotationInDegrees -= 3;
                }

                if (keyPressedList.contains("RIGHT")) {
                    spaceShip.rotationInDegrees += 3;
                }

                if (keyPressedList.contains("UP")) {
                    spaceShip.velocity.setLength(150);
                    spaceShip.velocity.setAngleInDegrees(spaceShip.rotationInDegrees);
                } else {
                    spaceShip.velocity.setLength(0); // todo: original game had decelerate!!! do this later...
                }

                if (keyJustPressedList.contains("SPACE")) {
                    Sprite laser = new Sprite("orb_red-opengameart-10x10.png");
                    laser.position.set(spaceShip.position.x, spaceShip.position.y);
                    laser.velocity.setLength(400);
                    laser.velocity.setAngleInDegrees(spaceShip.rotationInDegrees);
                    laserList.add(laser);
                }

                //after processing user input, clear the just pressed list
                keyJustPressedList.clear();

                spaceShip.update(1/60.0);

                for (Sprite asteroid : asteroidList) {
                    asteroid.update(1/60.0);
                }

                // update lasers; destroy if 2 seconds passed
                for (int n=0; n <laserList.size(); n++) {
                    Sprite laser = laserList.get(n);
                    laser.update(1/60.0);
                    if (laser.elapseTimeSeconds > 2) {
                        laserList.remove(n);
                    }
                }
                
                // when laser overlaps asteroid, remove both
                for (int laserNum = 0; laserNum < laserList.size(); laserNum++) {
                    Sprite laser = laserList.get(laserNum);
                    for (int asteroidNum = 0; asteroidNum < asteroidList.size(); asteroidNum++) {
                        Sprite asteroid = asteroidList.get(asteroidNum);
                        if (laser.overlaps(asteroid)) {
                            laserList.remove(laserNum);
                            asteroidList.remove(asteroidNum);
                            score += 100;
                        }
                    }
                }

                background.render(context);
                spaceShip.render(context);
                for (Sprite laser : laserList) {
                    laser.render(context);
                }
                for (Sprite asteroid : asteroidList) {
                    asteroid.render(context);
                }

                // draw score on screen
                context.setFill(Color.WHITE);
                context.setStroke(Color.GREEN);
                context.setFont(new Font("Arial Black", 48));
                context.setLineWidth(3);
                String text = "Score: " + score;
                int textX = 500;
                int textY = 50;
                context.fillText(text, textX, textY);
                context.strokeText(text, textX, textY);
            }
        };

        gameLoop.start();
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

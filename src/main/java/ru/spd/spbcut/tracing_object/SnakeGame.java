package ru.spd.spbcut.tracing_object;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.spd.spbcut.tracing_object.game.Snake;
import ru.spd.spbcut.tracing_object.util.Constant;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The " SnakeGame " class is the entry point to the program
 * and implements the creation of an interface on " Java FX "
 * */
public class SnakeGame extends Application {

    private static final Logger logger =  LogManager.getLogger(SnakeGame.class);

    private double speed = 1.0;
    private Timeline timeline;

    /**
     * Entry point in application
     * @param args  is a values entered at startup
     * */
    public static void main(String[] args) {
        launch(SnakeGame.class);
    }


    /**
     * The " init " method initializes object SnakeGame
     * */
    @Override
    public void init() throws Exception {
        logger.info("Run application initialization ... call method Init in super class");
        super.init();
    }

    /**
     * The " start " method start  SnakeGame
     * @param primaryStage is a primary stage in application
     * */
    @Override
    public void start(Stage primaryStage) {

        logger.info("Application started...");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(1);
        gridPane.setVgap(1);

        addMeshStage(gridPane);

        primaryStage = setStageValue(primaryStage);
        Snake snake = new Snake(gridPane);
        Slider speedSlider = getSlider();

        Label speedLabel = new Label("Speed: " + speedSlider.getValue());

        // Обработчик изменения значения слайдера
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            speed = setScale(newValue.doubleValue());
            speedLabel.setText("Speed: " + speed);
            timeline.setRate(speed);
        });

        HBox controlBox = new HBox(10, new Label("Speed:"), speedSlider, speedLabel);
        controlBox.setAlignment(Pos.CENTER);
        controlBox.setPadding(new Insets(10));

        // Создаем таймлайн с базовой скоростью
        timeline = new Timeline(new KeyFrame(Constant.BASE_MOVE_INTERVAL, event -> {
            snake.move();
            snake.drawSnake();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setRate(speed);  // Устанавливаем начальную скорость
        timeline.play();

        primaryStage.setOnCloseRequest(event -> timeline.stop());  // Останавливаем таймлайн при закрытии окна

        VBox root = new VBox(controlBox, gridPane);
        root.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * The " Slider " method is a getter for a speed slider in a  SnakeGame
     * */
    private static Slider getSlider() {
        Slider speedSlider = new Slider(0.5, 3.0, 1.0);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(0.5);
        speedSlider.setBlockIncrement(0.1);
        return speedSlider;
    }

    /**
     * The " addMeshStage " method is a creation mesh with sell in primary stage
     * */
    private static void addMeshStage(GridPane gridPane) {
        logger.debug("Create object Cell in stage");

        for (int row = 0; row < Constant.GRID_SIZE; row++) {
            for (int col = 0; col < Constant.GRID_SIZE; col++) {
                Rectangle cell = new Rectangle(Constant.CELL_SIZE, Constant.CELL_SIZE);
                cell.setFill(Color.WHITE);
                cell.setStroke(Color.WHITE);

                gridPane.add(cell, col, row);
            }
        }
    }

    /**
     * The " setStageValue " method is setter for primary stage
     * */
    private static Stage setStageValue( Stage primaryStage ) {

        primaryStage.setTitle("Snake game");
        primaryStage.setResizable(false);

        return primaryStage;
    }

    /**
    * The "stop"  method is a run super.stop()
    * */
    @Override
    public void stop() throws Exception {
        logger.info("Application stopped... call method Stop in super class");
        super.stop();
    }

    /**
     * The "setScale" is a method in rounding Double value  (ex. 2.0000  > 2.00)
     * */
    private Double setScale(Double speedSnake) {
        logger.debug("Speed snake before= " + speedSnake);
        BigDecimal bigDecimal = new BigDecimal(speedSnake);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);

        logger.debug("Speed snake after= " + bigDecimal.doubleValue());

        return bigDecimal.doubleValue();
    }

}

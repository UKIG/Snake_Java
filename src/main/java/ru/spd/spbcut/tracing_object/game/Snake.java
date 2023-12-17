package ru.spd.spbcut.tracing_object.game;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spd.spbcut.tracing_object.util.CellStage;
import ru.spd.spbcut.tracing_object.util.Constant;

import java.util.LinkedList;

/**
 * The class " Snake " is a class that implements an object for
 * moving around the program scene according to the laws of light reflection
 * */
public class Snake {
    private static final Logger logger = LogManager.getLogger(Snake.class);
    private LinkedList<CellStage> bodySnake;
    private GridPane gridPane;
    private int directionRows;
    private int directionColumns;

    /**
     * Constructor in class Snake
     * @param gridPane is a param insert grid panel
     * */
    public Snake(GridPane gridPane) {
        logger.debug("Create snake");

        this.bodySnake = new LinkedList<>();
        this.gridPane = gridPane;
        this.directionRows = 0;
        this.directionColumns = 1;

        initSnake();
    }


    /**
     * The " initSnake " method initializes and adds the length of
     * the snake by adding to the " bodySnake " objects class " CellStage "
     * */
    public void initSnake() {
        int initialLength = Constant.DEFAULT_LENGTH_SNAKE;
        int initialRows = Constant.GRID_SIZE / 2;
        int initialColumns = Constant.GRID_SIZE / 2;

        for (int i = 0; i < initialLength; i++) {
            bodySnake.add(new CellStage(initialRows, initialColumns - i, Color.GREEN));
        }

        logger.debug("Init snake = " + this.toString());

        drawSnake();
    }

    /**
     * The " move " method implements the movement of a snake along a given scene field
     * */
    public void move() {
        CellStage headSnake = bodySnake.getFirst();

        int newHeadRow = headSnake.getRowsCell() + directionRows;
        int newHeadCol = headSnake.getColumnsSell() + directionColumns;

        // Обработка отскока от стен
        if ((newHeadRow < 0) || (newHeadRow > Constant.GRID_SIZE)
                || (newHeadCol < 0) || (newHeadCol >= Constant.GRID_SIZE)) {

            directionRows = -directionRows;
            directionColumns = -directionColumns;

            // Пересчет новых координат для головы
            newHeadRow = headSnake.getRowsCell() + directionRows;
            newHeadCol = headSnake.getColumnsSell() + directionColumns;

            // Обрабатываем отскок так, чтобы голова змейки не выходила за пределы поля
            if (newHeadRow < 0) {
                newHeadRow = 0;
            } else if (newHeadRow >= Constant.GRID_SIZE) {
                newHeadRow = Constant.GRID_SIZE - 1;
            }

            if (newHeadCol < 0) {
                newHeadCol = 0;
            } else if (newHeadCol >= Constant.GRID_SIZE) {
                newHeadCol = Constant.GRID_SIZE - 1;
            }
        }

        // Добавляем новую голову
        bodySnake.addFirst(new CellStage(newHeadRow, newHeadCol, Color.GREEN));

        // Убираем хвост
        CellStage lastTail = bodySnake.removeLast();
        clearCell(lastTail);
    }

    /**
     * The " drawSnake " method implements the movement of a snake
     * across the scene field in accordance with a given
     * movement logic that does not depend on the observer
     * */
    public void drawSnake() {
        for (CellStage cellStage : bodySnake) {

            Rectangle rectangle = new Rectangle(Constant.CELL_SIZE, Constant.CELL_SIZE);
            rectangle.setFill(cellStage.getColor());
            rectangle.setStroke(Color.GRAY);

            gridPane.add(rectangle, cellStage.getColumnsSell(), cellStage.getRowsCell());
        }
    }

    /**
     * @param cellStage this param a cell in stage
     * The " clearCell " method implements the purification of cells passed by the snake
     * */
    private void clearCell(CellStage cellStage) {
        gridPane.getChildren().removeIf(node ->
                GridPane.getColumnIndex(node) == cellStage.getColumnsSell()
                        && GridPane.getRowIndex(node) == cellStage.getRowsCell());
    }

    /**
     * The " toString " method is a method that displays
     * the state of an object as a textual representation
     * */
    @Override
    public String toString() {
        return "Snake{" +
                "bodySnake=" + bodySnake +
                ", gridPane=" + gridPane +
                ", directionRows=" + directionRows +
                ", directionColumns=" + directionColumns +
                '}';
    }
}

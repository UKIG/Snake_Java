package ru.spd.spbcut.tracing_object.util;

import javafx.scene.paint.Color;

/**
 * This class implements objects for creating a scene field
 * */
public class CellStage {

    private int rowsCell;
    private int columnsSell;
    private Color colorSell;

    /**
     * This a constructor class  "CellStage"
     * @param rowsCell is a Location in stage
     * @param columnsSell  is a Location in stage
     * @param colorSell  it is a color Cell in stage
     * */
    public CellStage(int rowsCell, int columnsSell, Color colorSell) {
        this.rowsCell = rowsCell;
        this.columnsSell = columnsSell;
        this.colorSell = colorSell;
    }

    /**
     * This a private constructor class  "CellStage"
     * */
    private CellStage() {

    }

    /**
     * Method "getColor" is a getter for colorSell
     * */
    public Color getColor() {
        return colorSell;
    }

    /**
     * Method "getRowsCell" is a getter for rowsCell
     * */
    public int getRowsCell() {
        return rowsCell;
    }

    /**
     * Method "setRowsCell" is a setter for rowsCell
     * */
    public void setRowsCell(int rowsCell) {
        this.rowsCell = rowsCell;
    }

    /**
     * Method "getColumnsSell" is a getter for columnsSell
     * */
    public int getColumnsSell() {
        return columnsSell;
    }

    /**
     * Method "setColumnsSell" is a setter for columnsSell
     * */
    public void setColumnsSell(int columnsSell) {
        this.columnsSell = columnsSell;
    }

    /**
     * The " toString " method is a method that displays the state of an object as a textual representation
     * */
    @Override
    public String toString() {
        return "CellStage{" +
                "rowsCell=" + rowsCell +
                ", columnsSell=" + columnsSell +
                ", colorSell=" + colorSell +
                '}';
    }
}

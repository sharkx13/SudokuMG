package glembin.mateusz.sudoku;

import android.util.Log;

/**
 * Created by Mateusz Glembin on 2014-10-25.
 */
public class SudokuField {
    private final static String TAG = "SudokuField";
    private int value;
    private int position_x;
    private int position_y;

    public SudokuField(int position_x, int position_y) {
        value = 0;
        this.position_x = position_x;
        this.position_y = position_y;
    }

    public int getPositionX() {
        return position_x;
    }

    public int getPositionY() {
        return position_y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if(value >= 0 && value <=9) {
            this.value = value;
        } else {
            Log.v(TAG, "Niewlasciwa wartosc value: " + value + " " + position_x + " " + position_y);
            this.value = 0;
        }
    }
}

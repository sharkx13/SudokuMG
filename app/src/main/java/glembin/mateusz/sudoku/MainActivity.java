package glembin.mateusz.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    Sudoku sudoku;
    int width, height, x_pos, y_pos;
    int sel_width, sel_height, sel_x_pos, sel_y_pos;
    float touch_x, touch_y;
    int selectedField_x, selectedField_y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        selectedField_x = 0;
        selectedField_y = 0;

        if (savedInstanceState != null) {
            Log.v(TAG, "Tworze obiekt z parceli");
            sudoku = savedInstanceState.getParcelable("glembin.mateusz.sudoku.Sudoku");
        } else {
            Log.v(TAG, "Nie bylo zapisanej sesji, tworze nowy obiekt sudoku(0)");
            sudoku = new Sudoku();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v(TAG, "Zapisuje obiekt");
        if (sudoku != null) {
            outState.putParcelable("glembin.mateusz.sudoku.Sudoku", sudoku);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.v(TAG, "Odtwarzam obiekt");
        sudoku = savedInstanceState.getParcelable("glembin.mateusz.sudoku.Sudoku");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // pobieranie rozmiarow i polozenia ImageView z plansza
        View iv = findViewById(R.id.sudokuBoard);
        int[] locationOnScreen = new int[2];
        iv.getLocationOnScreen(locationOnScreen);
        width = iv.getWidth();
        height = iv.getHeight();
        x_pos = locationOnScreen[0];
        y_pos = locationOnScreen[1];
        Log.v(TAG, "x_pos: " + x_pos + ", y_pos: " + y_pos
                + ", width: " + width + ", height: " + height);

        // pobieranie rozmiarow i polozenia ImageView z wybieraniem liczby
        iv = findViewById(R.id.sudokuBar);
        iv.getLocationOnScreen(locationOnScreen);
        sel_x_pos = locationOnScreen[0];
        sel_y_pos = locationOnScreen[1];
        sel_width = iv.getWidth();
        sel_height = iv.getHeight();
        Log.v(TAG, "s_x: " + sel_x_pos + ", s_y: " + sel_y_pos +
                ", s_w: " + sel_width + ", s_h: " + sel_height);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    public void AboutAppMenuItemOnClick(MenuItem item) {
        Intent aboutAppIntent = new Intent(this, AboutAppActivity.class);
        startActivity(aboutAppIntent);
    }

    public void HelpMenuItemOnClick(MenuItem item) {
        Intent helpMenuIntent = new Intent(this, HelpActivity.class);
        startActivity(helpMenuIntent);
    }

    public void NewSudokuMenuItemOnClick(MenuItem item) {
        Intent sudokuIntent = new Intent(this, NewSudokuActivity.class);
        startActivity(sudokuIntent);
    }

    public void SolveMenuItemOnClick(MenuItem item) {
        Intent solveIntent = new Intent(this, SolveActivity.class);
        startActivity(solveIntent);
    }

    /** zaznacza pola */
    private void markField(int field_x, int field_y) {
        selectedField_x = field_x;
        selectedField_y = field_y;
        Log.v(TAG, "markField - x: " + field_x + ", y: " + field_y);
        Toast.makeText(this, "x: " + field_x + ", y: " + field_y, Toast.LENGTH_SHORT).show();

        //TODO narysowac zaznaczenie na ekranie
    }

    /** odznacza pola */
    private void unmarkField() {
        selectedField_y = 0;
        selectedField_y = 0;
        Log.v(TAG, "unmarkField");
        Toast.makeText(this, "odznaczam pole", Toast.LENGTH_SHORT).show();

        // TODO usunac zaznaczenie

    }

    /** obsluga wyboru numeru na sudokuBar */
    private void setNumber(int number) {
        Log.v(TAG, "setNumber");
        if (selectedField_y == 0 && selectedField_x == 0) {
            Log.v(TAG, "setNumber: pole nie zostalo zaznaczone");
            return;
        } else {
            Toast.makeText(this, "wybrano liczbe: " + number, Toast.LENGTH_SHORT).show();
        }
        //if (sudoku.setFieldValue(number, selectedField_x, selectedField_y)) {
            //TODO wygrana
        //}
        unmarkField();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            touch_x = event.getX();
            touch_y = event.getY();
            Log.v(TAG, "x: " + touch_x + ", y: " + touch_y);
            Log.v(TAG, String.valueOf(0.111 * width + y_pos));

            // uzytkownik wybiera liczbe
            if (touch_y < (sel_height + sel_y_pos) && touch_y > sel_y_pos) {
                if (touch_x < (0.111 * sel_width)) setNumber(1);
                else if (touch_x < (0.222 * sel_width)) setNumber(2);
                else if (touch_x < (0.333 * sel_width)) setNumber(3);
                else if (touch_x < (0.444 * sel_width)) setNumber(4);
                else if (touch_x < (0.555 * sel_width)) setNumber(5);
                else if (touch_x < (0.666 * sel_width)) setNumber(6);
                else if (touch_x < (0.777 * sel_width)) setNumber(7);
                else if (touch_x < (0.888 * sel_width)) setNumber(8);
                else if (touch_x < (sel_width)) setNumber(9);
            }
            // uzytkownik wybiera pole
            else if (touch_y < (0.111 * height + y_pos)) {
                if (touch_x < (0.111 * width)) markField(1, 1);
                else if (touch_x < (0.222 * width)) markField(2, 1);
                else if (touch_x < (0.333 * width)) markField(3, 1);
                else if (touch_x < (0.444 * width)) markField(4, 1);
                else if (touch_x < (0.555 * width)) markField(5, 1);
                else if (touch_x < (0.666 * width)) markField(6, 1);
                else if (touch_x < (0.777 * width)) markField(7, 1);
                else if (touch_x < (0.888 * width)) markField(8, 1);
                else if (touch_x < (width)) markField(9, 1);
            } else if (touch_y < (0.222 * height + y_pos)) {
                if (touch_x < (0.111 * width)) markField(1, 2);
                else if (touch_x < (0.222 * width)) markField(2, 2);
                else if (touch_x < (0.333 * width)) markField(3, 2);
                else if (touch_x < (0.444 * width)) markField(4, 2);
                else if (touch_x < (0.555 * width)) markField(5, 2);
                else if (touch_x < (0.666 * width)) markField(6, 2);
                else if (touch_x < (0.777 * width)) markField(7, 2);
                else if (touch_x < (0.888 * width)) markField(8, 2);
                else if (touch_x < (width)) markField(9, 2);
            } else if (touch_y < (0.333 * height + y_pos)) {
                if (touch_x < (0.111 * width)) markField(1, 3);
                else if (touch_x < (0.222 * width)) markField(2, 3);
                else if (touch_x < (0.333 * width)) markField(3, 3);
                else if (touch_x < (0.444 * width)) markField(4, 3);
                else if (touch_x < (0.555 * width)) markField(5, 3);
                else if (touch_x < (0.666 * width)) markField(6, 3);
                else if (touch_x < (0.777 * width)) markField(7, 3);
                else if (touch_x < (0.888 * width)) markField(8, 3);
                else if (touch_x < (width)) markField(9, 3);
            } else if (touch_y < (0.444 * height + y_pos)) {
                if (touch_x < (0.111 * width)) markField(1, 4);
                else if (touch_x < (0.222 * width)) markField(2, 4);
                else if (touch_x < (0.333 * width)) markField(3, 4);
                else if (touch_x < (0.444 * width)) markField(4, 4);
                else if (touch_x < (0.555 * width)) markField(5, 4);
                else if (touch_x < (0.666 * width)) markField(6, 4);
                else if (touch_x < (0.777 * width)) markField(7, 4);
                else if (touch_x < (0.888 * width)) markField(8, 4);
                else if (touch_x < (width)) markField(9, 4);
            } else if (touch_y < (0.555 * height + y_pos)) {
                if (touch_x < (0.111 * width)) markField(1, 5);
                else if (touch_x < (0.222 * width)) markField(2, 5);
                else if (touch_x < (0.333 * width)) markField(3, 5);
                else if (touch_x < (0.444 * width)) markField(4, 5);
                else if (touch_x < (0.555 * width)) markField(5, 5);
                else if (touch_x < (0.666 * width)) markField(6, 5);
                else if (touch_x < (0.777 * width)) markField(7, 5);
                else if (touch_x < (0.888 * width)) markField(8, 5);
                else if (touch_x < (width)) markField(9, 5);
            } else if (touch_y < (0.666 * height + y_pos)) {
                if (touch_x < (0.111 * width)) markField(1, 6);
                else if (touch_x < (0.222 * width)) markField(2, 6);
                else if (touch_x < (0.333 * width)) markField(3, 6);
                else if (touch_x < (0.444 * width)) markField(4, 6);
                else if (touch_x < (0.555 * width)) markField(5, 6);
                else if (touch_x < (0.666 * width)) markField(6, 6);
                else if (touch_x < (0.777 * width)) markField(7, 6);
                else if (touch_x < (0.888 * width)) markField(8, 6);
                else if (touch_x < (width)) markField(9, 6);
            } else if (touch_y < (0.777 * height + y_pos)) {
                if (touch_x < (0.111 * width)) markField(1, 7);
                else if (touch_x < (0.222 * width)) markField(2, 7);
                else if (touch_x < (0.333 * width)) markField(3, 7);
                else if (touch_x < (0.444 * width)) markField(4, 7);
                else if (touch_x < (0.555 * width)) markField(5, 7);
                else if (touch_x < (0.666 * width)) markField(6, 7);
                else if (touch_x < (0.777 * width)) markField(7, 7);
                else if (touch_x < (0.888 * width)) markField(8, 7);
                else if (touch_x < (width)) markField(9, 7);
            } else if (touch_y < (0.888 * height + y_pos)) {
                if (touch_x < (0.111 * width)) markField(1, 8);
                else if (touch_x < (0.222 * width)) markField(2, 8);
                else if (touch_x < (0.333 * width)) markField(3, 8);
                else if (touch_x < (0.444 * width)) markField(4, 8);
                else if (touch_x < (0.555 * width)) markField(5, 8);
                else if (touch_x < (0.666 * width)) markField(6, 8);
                else if (touch_x < (0.777 * width)) markField(7, 8);
                else if (touch_x < (0.888 * width)) markField(8, 8);
                else if (touch_x < (width)) markField(9, 8);
            } else if (touch_y < (height + y_pos)) {
                if (touch_x < (0.111 * width)) markField(1, 9);
                else if (touch_x < (0.222 * width)) markField(2, 9);
                else if (touch_x < (0.333 * width)) markField(3, 9);
                else if (touch_x < (0.444 * width)) markField(4, 9);
                else if (touch_x < (0.555 * width)) markField(5, 9);
                else if (touch_x < (0.666 * width)) markField(6, 9);
                else if (touch_x < (0.777 * width)) markField(7, 9);
                else if (touch_x < (0.888 * width)) markField(8, 9);
                else if (touch_x < (width)) markField(9, 9);
            } else {
                // jezeli zadnych z tych eventow to kasujemy zaznaczenie
                if (selectedField_x != 0 && selectedField_y != 0) {
                    setNumber(0);
                } else {
                    unmarkField();
                }
            }
        }

        return super.onTouchEvent(event);
    }
}

package glembin.mateusz.sudoku;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.prefs.Preferences;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    Sudoku sudoku;
    int width;
    int x_pos, y_pos;
    float touch_x;
    float touch_y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);



        if(savedInstanceState != null) {
            Log.v(TAG, "Tworze obiekt z parceli");
            sudoku = savedInstanceState.getParcelable("glembin.mateusz.sudoku.Sudoku");
        }
        else {
            Log.v(TAG, "Nie bylo zapisanej sesji, tworze nowy obiekt sudoku(0)");
            sudoku = new Sudoku();
        }
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v(TAG, "Zapisuje obiekt");
        if(sudoku != null) {
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
        View iv = (ImageView) findViewById(R.id.sudokuBoard);
        int[] locationOnScreen = new int[2];
        iv.getLocationOnScreen(locationOnScreen);
        width = iv.getWidth();

        x_pos = locationOnScreen[0];
        y_pos = locationOnScreen[1];
        Log.v(TAG, "x_pos: " + x_pos);
        Log.v(TAG, "y_pos: " + y_pos);

        //DisplayMetrics dm = new DisplayMetrics();
        //this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        //width = dm.widthPixels;

        Log.v(TAG, "width " + width);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            touch_x = event.getX();
            touch_y = event.getY();
            Log.v(TAG, "x: " + touch_x + ", y: " + touch_y);
            Log.v(TAG, String.valueOf(0.111*width + y_pos));
            if(touch_y<(0.111*width+y_pos)) {
                if (touch_x<(0.111*width)) {
                    Toast.makeText(this, "1:1", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.222*width)) {
                    Toast.makeText(this, "1:2", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.333*width)) {
                    Toast.makeText(this, "1:3", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.444*width)) {
                    Toast.makeText(this, "1:4", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.555*width)) {
                    Toast.makeText(this, "1:5", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.666*width)) {
                    Toast.makeText(this, "1:6", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.777*width)) {
                    Toast.makeText(this, "1:7", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.888*width)) {
                    Toast.makeText(this, "1:8", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(width)) {
                    Toast.makeText(this, "1:9", Toast.LENGTH_SHORT).show();
                }
            }
            else if(touch_y<(0.222*width+y_pos)) {
                if (touch_x<(0.111*width)) {
                    Toast.makeText(this, "2:1", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.222*width)) {
                    Toast.makeText(this, "2:2", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.333*width)) {
                    Toast.makeText(this, "2:3", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.444*width)) {
                    Toast.makeText(this, "2:4", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.555*width)) {
                    Toast.makeText(this, "2:5", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.666*width)) {
                    Toast.makeText(this, "2:6", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.777*width)) {
                    Toast.makeText(this, "2:7", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.888*width)) {
                    Toast.makeText(this, "2:8", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(width)) {
                    Toast.makeText(this, "2:9", Toast.LENGTH_SHORT).show();
                }
            }
            else if(touch_y<(0.333*width+y_pos)) {
                if (touch_x<(0.111*width)) {
                    Toast.makeText(this, "3:1", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.222*width)) {
                    Toast.makeText(this, "3:2", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.333*width)) {
                    Toast.makeText(this, "3:3", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.444*width)) {
                    Toast.makeText(this, "3:4", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.555*width)) {
                    Toast.makeText(this, "3:5", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.666*width)) {
                    Toast.makeText(this, "3:6", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.777*width)) {
                    Toast.makeText(this, "3:7", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.888*width)) {
                    Toast.makeText(this, "3:8", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(width)) {
                    Toast.makeText(this, "3:9", Toast.LENGTH_SHORT).show();
                }
            }
            else if(touch_y<(0.444*width+y_pos)) {
                if (touch_x<(0.111*width)) {
                    Toast.makeText(this, "4:1", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.222*width)) {
                    Toast.makeText(this, "4:2", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.333*width)) {
                    Toast.makeText(this, "4:3", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.444*width)) {
                    Toast.makeText(this, "4:4", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.555*width)) {
                    Toast.makeText(this, "4:5", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.666*width)) {
                    Toast.makeText(this, "4:6", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.777*width)) {
                    Toast.makeText(this, "4:7", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.888*width)) {
                    Toast.makeText(this, "4:8", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(width)) {
                    Toast.makeText(this, "4:9", Toast.LENGTH_SHORT).show();
                }
            }
            else if(touch_y<(0.555*width+y_pos)) {
                if (touch_x<(0.111*width)) {
                    Toast.makeText(this, "5:1", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.222*width)) {
                    Toast.makeText(this, "5:2", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.333*width)) {
                    Toast.makeText(this, "5:3", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.444*width)) {
                    Toast.makeText(this, "5:4", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.555*width)) {
                    Toast.makeText(this, "5:5", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.666*width)) {
                    Toast.makeText(this, "5:6", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.777*width)) {
                    Toast.makeText(this, "5:7", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.888*width)) {
                    Toast.makeText(this, "5:8", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(width)) {
                    Toast.makeText(this, "5:9", Toast.LENGTH_SHORT).show();
                }
            }
            else if(touch_y<(0.666*width+y_pos)) {
                if (touch_x<(0.111*width)) {
                    Toast.makeText(this, "6:1", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.222*width)) {
                    Toast.makeText(this, "6:2", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.333*width)) {
                    Toast.makeText(this, "6:3", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.444*width)) {
                    Toast.makeText(this, "6:4", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.555*width)) {
                    Toast.makeText(this, "6:5", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.666*width)) {
                    Toast.makeText(this, "6:6", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.777*width)) {
                    Toast.makeText(this, "6:7", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.888*width)) {
                    Toast.makeText(this, "6:8", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(width)) {
                    Toast.makeText(this, "6:9", Toast.LENGTH_SHORT).show();
                }
            }
            else if(touch_y<(0.777*width+y_pos)) {
                if (touch_x<(0.111*width)) {
                    Toast.makeText(this, "7:1", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.222*width)) {
                    Toast.makeText(this, "7:2", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.333*width)) {
                    Toast.makeText(this, "7:3", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.444*width)) {
                    Toast.makeText(this, "7:4", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.555*width)) {
                    Toast.makeText(this, "7:5", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.666*width)) {
                    Toast.makeText(this, "7:6", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.777*width)) {
                    Toast.makeText(this, "7:7", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.888*width)) {
                    Toast.makeText(this, "7:8", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(width)) {
                    Toast.makeText(this, "7:9", Toast.LENGTH_SHORT).show();
                }
            }
            else if(touch_y<(0.888*width+y_pos)) {
                if (touch_x<(0.111*width)) {
                    Toast.makeText(this, "8:1", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.222*width)) {
                    Toast.makeText(this, "8:2", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.333*width)) {
                    Toast.makeText(this, "8:3", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.444*width)) {
                    Toast.makeText(this, "8:4", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.555*width)) {
                    Toast.makeText(this, "8:5", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.666*width)) {
                    Toast.makeText(this, "8:6", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.777*width)) {
                    Toast.makeText(this, "8:7", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.888*width)) {
                    Toast.makeText(this, "8:8", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(width)) {
                    Toast.makeText(this, "8:9", Toast.LENGTH_SHORT).show();
                }
            }
            else if(touch_y<(width+y_pos)) {
                if (touch_x<(0.111*width)) {
                    Toast.makeText(this, "9:1", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.222*width)) {
                    Toast.makeText(this, "9:2", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.333*width)) {
                    Toast.makeText(this, "9:3", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.444*width)) {
                    Toast.makeText(this, "9:4", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.555*width)) {
                    Toast.makeText(this, "9:5", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.666*width)) {
                    Toast.makeText(this, "9:6", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.777*width)) {
                    Toast.makeText(this, "9:7", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(0.888*width)) {
                    Toast.makeText(this, "9:8", Toast.LENGTH_SHORT).show();
                }
                else if (touch_x<(width)) {
                    Toast.makeText(this, "9:9", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onTouchEvent(event);
    }
}

package glembin.mateusz.sudoku;

import android.util.Log;

/**
 * Created by Mateusz Glembin on 2014-10-25.
 */
public class Sudoku {
    private static final String TAG = "Sudoku";
    private SudokuField [][] sudokuFields;

    /** konstruktor, tworzy pola planszy, losuje plansze o podanym poziomie trudnosci */
    public Sudoku(int difficulty) {
        // tworzenie tablicy elementow SudokuField
        sudokuFields = new SudokuField[9][9];
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                // inicjacja elementow
                sudokuFields[i][j] = new SudokuField(i,j);
            }
        }

        switch(difficulty) {
            case 0:
                makePuzzleEasy();
            case 1:
                makePuzzleMed();
            case 2:
                makePuzzleHard();
            case 3:
                makePuzzleInsane();
            default:
                Log.v(TAG, "Zly numer poziomu trudnosci");
                makePuzzleEasy();

        }
    }

    /** tworzenie planszy o bardzo duzym poziomie trudnosci */
    private void makePuzzleInsane() {
        //TODO
        Log.v(TAG, "not implemented");
    }

    /** tworzenie planszy o duzym poziomie trudnosci */
    private void makePuzzleHard() {
        //TODO
        Log.v(TAG, "not implemented");
    }

    /** tworzenie planszy o srednim poziomie trudnosci */
    private void makePuzzleMed() {
        //TODO
        Log.v(TAG, "not implemented");
    }

    /** tworzenie planszy o latwym poziomie trudnosci */
    private void makePuzzleEasy() {
        //TODO
        Log.v(TAG, "not implemented");
    }

    /** ustawia wartosc konkretnego pola */
    public void setFieldValue(int value, int x, int y) {
        sudokuFields[x][y].setValue(value);
    }

    public int getFieldValue(int x, int y) {
        return sudokuFields[x][y].getValue();
    }

    public boolean testWin() {
        // sprawdzanie czy wszystkie pola sa zajete przez liczby
        for (int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if (sudokuFields[i][j].getValue() == 0) return false;
            }
        }
        // sprawdzanie poprawnosci linii
        // TODO
        for (int i=0; i<9; i++) {
            int tmp = sudokuFields[i][0].getValue();
            for (int j=1; j<9; j++) {
                if (sudokuFields[i][j].getValue() == tmp) return false;
            }
        }

        //sprawdzanie poprawnosci blokow
        // TODO

        return true;
    }
}

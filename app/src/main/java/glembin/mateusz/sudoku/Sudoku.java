package glembin.mateusz.sudoku;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Mateusz Glembin on 2014-10-25.
 */
public class Sudoku implements Parcelable {
    private static final String TAG = "Sudoku";

    private int difficulty;
    private int fieldsBase; //ilosc zajetych pol przez generator
    private int fieldsUser; //ilosc zajmowanych pol przez gracza
    private int [][] sudokuFields;

    /* PARCEL INTERFACE */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(difficulty);
        dest.writeInt(fieldsBase);
        dest.writeInt(fieldsUser);
        for(int i=0; i<9; i++) {
            dest.writeIntArray(sudokuFields[i]);
        }
    }

    public static final Parcelable.Creator<Sudoku> CREATOR = new Parcelable.Creator<Sudoku>(){
        public Sudoku createFromParcel(Parcel in) {
            return new Sudoku(in);
        }
        public Sudoku[] newArray (int size) {
            return new Sudoku[size];
        }
    };

    /* KONTRUKTORY */
    /** konstruktor, tworzy pola planszy, losuje plansze */
    public Sudoku(Parcel in) {
        difficulty = in.readInt();
        fieldsBase = in.readInt();
        fieldsUser = in.readInt();
        sudokuFields = new int[9][9];
        for (int i = 0; i < 9; i++) {
            in.readIntArray(sudokuFields[i]);
        }
    }

    public Sudoku() {
        // tworzenie pustej planszy
        sudokuFields = new int[9][9];
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) sudokuFields[i][j] = 0;
        }
        fieldsUser = 0;
        difficulty = 0;
        // musi ustawic fieldsBase
        makePuzzle(difficulty);
    }

    public Sudoku(int diff) {
        // tworzenie pustej planszy
        sudokuFields = new int[9][9];
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) sudokuFields[i][j] = 0;
        }
        fieldsUser = 0;
        difficulty = diff;
        // musi ustawic fieldsBase
        makePuzzle(difficulty);
    }

    public Sudoku(int diff, int seed) {
        Log.v(TAG, "Sudoku(diff,seed) - not implemented");
        throw new RuntimeException();
    }

    /* PUBLICZNE FUNKCJE */
    /* ustawia wartosc konkretnego pola, zwraca false jak nie ma wygranej*/
    public boolean setFieldValue(int value, int x, int y) {
        // sprawdzamy poprawnosc danych
        if ((value>=0 && value <=9) && (x>=0 && x<=8) && (y>=0 && y<=8)) {
            // zapis na pole puste
            if (sudokuFields[x][y] == 0) {
                // wartosci niezerowej
                if (value > 0) {
                    sudokuFields[x][y] = value;
                    // +1 do licznika
                    fieldsUser++;
                }
                //wartosci zerowej - bez sensu cokolwiek robic
                //else {}
            // zapis na pelne pole
            } else {
                // wartosci zerowej
                if (value == 0) {
                    sudokuFields[x][y] = 0;
                    // -1 do licznika
                    fieldsUser--;
                }
                // zmiana wartosci na inna
                else {
                    sudokuFields[x][y] = value;
                }
            }
        } else {
            // przy zlych wartosciach walimy loga i exception
            Log.v(TAG, "setFieldValue - zla wartosc: " + value + " " + x + " " + y);
            throw new RuntimeException();
        }
        // sprawdzamy czy uzytkownik wypelnil cala plansze
        if (fieldsUser+fieldsBase != 9*9) return false;
        return testWin();
    }

    public int getFieldValue(int x, int y) {
        if(x>=0 && x<=8 && y>=0 && y<=8) {
            return sudokuFields[x][y];
        }
        else {
            Log.v(TAG, "getFieldValue - zla wartosc: " + x + " " + y);
            throw new RuntimeException();
        }
    }

    public int getUsedFields() {
        return fieldsBase+fieldsUser;
    }

    /* PRYWATNE FUNKCJE */
    // TODO
    private void makePuzzle(int difficulty) {
            fieldsBase = 20;
            Log.v(TAG, "not implemented");
    }

    private boolean testWin() {
        // sprawdzanie czy wszystkie pola sa zajete przez liczby
        for (int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if (sudokuFields[i][j] == 0) {
                    Log.v(TAG, "testWin - wszystkie pola nie byly zajete");
                    throw new RuntimeException();
                }
            }
        }
        // i - wartosc w tabeli, sprawdzanie linii
        for (int y=0; y<9; y++) {   // linie poziome
            for (int i = 1; i <= 9; i++) {
                for (int x = 0; x < 9; x++) {
                    if (sudokuFields[x][y] == i) continue;
                    if (x == 8) return false;
                }
            }
        }
        for (int x=0; x<9; x++) {   // linie pionowe
            for (int i = 1; i <= 9; i++) {
                for (int y = 0; y < 9; y++) {
                    if (sudokuFields[x][y] == i) continue;
                    if (y == 8) return false;
                }
            }
        }
        // wszystko ok - zwracamy true
        return true;
    }
}

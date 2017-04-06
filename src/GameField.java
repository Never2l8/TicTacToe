import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nina on 3/20/17.
 */
public class GameField {
    CellStateEnum[][] field;
    int winNumber;
    FieldStateEnum fieldState;
    TurnEnum turn;

    public GameField(GameSettings initSettings) {
        int size = initSettings.fieldSize;
        this.field = new CellStateEnum[size][size];
        this.winNumber = initSettings.winNumber;
        fieldState = FieldStateEnum.GAME;
        turn = TurnEnum.CROSSES;
    }

    public GameField() {
        this(new GameSettings());
    }

    public void init() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = CellStateEnum.EMPTY;
            }
        }
    }

    public void initStd() {
        for (CellStateEnum[] row : field) {
            Arrays.fill(row, CellStateEnum.EMPTY);
        }
    }

    public void printStep() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                printCell(field[i][j]);
            }
            System.out.println();
        }
        System.out.println("__________________________________");

    }


    public boolean placeSymbol(int row, int column) {
        if (field[row - 1][column - 1] != CellStateEnum.EMPTY)
            return false;
        if (turn == TurnEnum.CROSSES)
            field[row - 1][column - 1] = CellStateEnum.CROSS;
        else if (turn == TurnEnum.NOUGHTS)
            field[row - 1][column - 1] = CellStateEnum.NOUGHT;
        return true;
    }

    private void printCell(CellStateEnum cell) {
        if (cell == CellStateEnum.EMPTY)
            System.out.print("|_|");
        if (cell == CellStateEnum.CROSS)
            System.out.print("X");
        if (cell == CellStateEnum.NOUGHT)
            System.out.print("O");
    }

    public void printWinner() {
        if (fieldState != FieldStateEnum.GAME) {
            printStep();
        }
        if (fieldState == FieldStateEnum.CROSSES_WON)
            System.out.println("CROSSES won!");
        if (fieldState == FieldStateEnum.NOUGHTS_WON)
            System.out.println("NOUGHTS won!");
        if (fieldState == FieldStateEnum.DRAW)
            System.out.println("Draw");
    }

    public void printTurn() {
        if (fieldState == FieldStateEnum.GAME) {
            if (turn == TurnEnum.CROSSES)
                System.out.println("Crosses turn");
            if (turn == TurnEnum.NOUGHTS)
                System.out.println("Noughts turn");
        }
    }

    public void fieldStateUpdate() {
        int size = field.length;
        CellStateEnum check;
        FieldStateEnum won;
        if (turn == TurnEnum.CROSSES) {
            check = CellStateEnum.CROSS;
            won = FieldStateEnum.CROSSES_WON;
        } else {
            check = CellStateEnum.NOUGHT;
            won = FieldStateEnum.NOUGHTS_WON;
        }
        // HORIZONTAL CHECK
        for (int i = 0; i < size; i++) {
            int counter = 0;
            for (int j = 0; j < size; j++) {
                if (field[i][j] == check) counter++;
                else counter = 0;
            }
            if (counter == winNumber) {
                fieldState = won;
                break;
            }
        }
        // VERTICAL CHECK
        for (int j = 0; j < size; j++) {
            int counter = 0;
            for (int i = 0; i < size; i++) {
                if (field[i][j] == check) counter++;
                else counter = 0;
            }
            if (counter == winNumber) {
                fieldState = won;
                break;
            }
        }
        // DIAGONAL CHECK
        List<Pair<Integer, Integer>> leftColumnCoordinates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            leftColumnCoordinates.add(new Pair<>(i, 0));
        }
        List<Pair<Integer, Integer>> topRowCoordinates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            topRowCoordinates.add(new Pair<>(0, i));
        }
        List<Pair<Integer, Integer>> botRowCoordinates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            botRowCoordinates.add(new Pair<>(size - 1, i));
        }

        for (Pair<Integer, Integer> coords : leftColumnCoordinates) {
            Integer j = coords.getValue();
            Integer i = coords.getKey();
            int counter = 0;
            for (; i < size; i++) {
                if (field[i][j] == check) counter++;
                else counter = 0;
                j++;
            }
            if (counter == winNumber) fieldState = won;
        }
        for (Pair<Integer, Integer> coords : topRowCoordinates) {
            Integer j = coords.getValue();
            Integer i = coords.getKey();
            int counter = 0;
            for (; j < size; i++) {
                if (field[i][j] == check) counter++;
                else counter = 0;
                j++;
            }
            if (counter == winNumber) fieldState = won;
        }
        for (Pair<Integer, Integer> coords : leftColumnCoordinates) {
            Integer j = coords.getValue();
            Integer i = coords.getKey();
            int counter = 0;
            for (; i >= 0; i--) {
                if (field[i][j] == check) counter++;
                else counter = 0;
                j++;
            }
            if (counter == winNumber) fieldState = won;
        }
        for (Pair<Integer, Integer> coords : botRowCoordinates) {
            Integer j = coords.getValue();
            Integer i = coords.getKey();
            int counter = 0;
            for (; j < size; i--) {
                if (field[i][j] == check) counter++;
                else counter = 0;
                j++;
            }
            if (counter == winNumber) fieldState = won;
        }
    }

    public void switchTurn() {
        if (turn == TurnEnum.CROSSES) turn = TurnEnum.NOUGHTS;
        else turn = TurnEnum.CROSSES;
    }
}

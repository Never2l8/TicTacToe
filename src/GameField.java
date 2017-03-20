import java.util.Arrays;

/**
 * Created by nina on 3/20/17.
 */
public class GameField {
    CellStateEnum[][] field;
    int winNumber;
    FieldStateEnum fieldState;
    TurnEnum turn;

    public GameField(int fieldSize, int winNumber) {
        this.field = new CellStateEnum[fieldSize][fieldSize];
        this.winNumber = winNumber;
        fieldState = FieldStateEnum.GAME;
        turn = TurnEnum.CROSSES;

    }

    public GameField() {
        this(3, 3);
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
        printWinner();
        printTurn();
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
            System.out.print(" ");
        if (cell == CellStateEnum.CROSS)
            System.out.print("X");
        if (cell == CellStateEnum.NOUGHT)
            System.out.print("O");
    }

    private void printWinner() {
        if (fieldState == FieldStateEnum.CROSSES_WON)
            System.out.print("Cross won!");
        if (fieldState == FieldStateEnum.NOUGHTS_WON)
            System.out.print("Zero won!");
        if (fieldState == FieldStateEnum.DRAW)
            System.out.print("Draw");
    }

    private void printTurn() {
        if (fieldState == FieldStateEnum.GAME) {
            if (turn == TurnEnum.CROSSES)
                System.out.print("Crosses turn");
            if (turn == TurnEnum.NOUGHTS)
                System.out.print("Noughts turn");
        }
    }


}

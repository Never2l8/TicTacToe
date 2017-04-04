import java.util.Arrays;

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
            System.out.print("#");
        if (cell == CellStateEnum.CROSS)
            System.out.print("X");
        if (cell == CellStateEnum.NOUGHT)
            System.out.print("O");
    }

    public void printWinner() {
        if (fieldState == FieldStateEnum.CROSSES_WON)
            System.out.println("CROSSES won!");
        if (fieldState == FieldStateEnum.NOUGHTS_WON)
            System.out.println("NOUGHTS won!");
        if (fieldState == FieldStateEnum.DRAW)
            System.out.println("Draw");
    }

    private void printTurn() {
        if (fieldState == FieldStateEnum.GAME) {
            if (turn == TurnEnum.CROSSES)
                System.out.println("Crosses turn");
            if (turn == TurnEnum.NOUGHTS)
                System.out.println("Noughts turn");
        }
    }

    public void fieldStateUpdate() {
        fieldState = FieldStateEnum.GAME;
    }

    public void switchTurn(){
        if(turn == TurnEnum.CROSSES) turn = TurnEnum.NOUGHTS;
        else turn = TurnEnum.CROSSES;
    }
}

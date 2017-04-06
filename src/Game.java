import java.util.Objects;
import java.util.Scanner;

/**
 * Created by nina on 3/20/17.
 */
public class Game {
    private GameSettings settings;
    private GameField gameField;

    public void mainLoop() {
        //Главный цикл игры. Он бесконечный. Выход из него только по вводу пользователя.
        //В начале внутри цикла должна получить у пользователя размер поля и winnumber.
        // Должна проверить, что они не содержат противоречий:
        // виннамбер не больше сайза, оба они не отрицательные и не ноль, размер поля не единица или не 2х2
        //Варианты организации ввода:
        //а)в случае неверного ввода заменить значение на дефолтные;
        //б) повторение внутреннего цикла ввода до тех пор пока не будут введены корректные значения;
        //После ввода создать игровое поле соответствующего размера.
        //Начать выполнение игрового цикла: передавая ход от игрока к игроку, проверяя корректность хода.
        //Дать возможность повторить ход, если он был некорректный.
        //Циклики выделить в отдельные функции.
        while (true) {
            initField();
            turnLoop();

        }

    }

    private void initField() {
        settings = new GameSettings();
        do {
            printFirstMenuScreen();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your choice:");
            String input = scanner.next();
            if (Objects.equals(input, "1")) {
                gameField = new GameField();
                gameField.init();
                break;
            }
            if (Objects.equals(input, "2")) {
                customizeSettings();
                gameField = new GameField(settings);
                gameField.init();
                break;
            }
            if (Objects.equals(input, "0")) {
                System.out.println("Thank you! Bye!");
                System.exit(0);
            }

            System.out.println("Sorry, you entered a wrong number. Please try again.");
        }
        while (true);
    }

    private void printFirstMenuScreen() {
        System.out.println("Greetings!");
        System.out.println("To start game with default settings enter 1");
        System.out.println("To customize settings enter 2");
        System.out.println("To exit enter 0");

    }

    private void customizeSettings() {
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter field size. It must be greater than two and not greater than 10: ");
            String input = scanner.next();
            int fieldSize;
            try {
                fieldSize = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid value. Try again.");
                continue;
            }
            if (fieldSize <= 2 || fieldSize > 10) {
                System.out.println("Invalid value. It must be greater than two and not greater than 10. Try again: ");
                continue;
            }

            settings.fieldSize = fieldSize;
            System.out.print("Please enter win number. It must be greater than three but not greater than field size: ");
            input = scanner.next();
            int winNumber;
            try {
                winNumber = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid value. Try again.");
                continue;
            }
            if (winNumber < 3 || winNumber > fieldSize) {
                System.out.println("Invalid value. It must be greater than three but not greater than field size. Try again: ");
                continue;
            }
            settings.winNumber = winNumber;
            break;
        }
        while (true);
    }

    private void turnLoop() {
        do {
            gameField.printStep();
            gameField.printTurn();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your coordinates by separating them with a comma: ");
            String input = scanner.next();
            input = input.replaceAll("\\s+", "");
            String[] coordinatesStr = input.split(",");
            if (!(coordinatesStr.length == 2)) {
                System.out.println("Error message");
                continue;
            }
            int x;
            try {
                x = Integer.parseInt(coordinatesStr[0]);
            } catch (NumberFormatException e) {
                System.out.println("Error message");
                continue;
            }
            int y;
            try {
                y = Integer.parseInt(coordinatesStr[1]);
            } catch (NumberFormatException e) {
                System.out.println("Error message");
                continue;
            }
            if (x < 1 || x > settings.fieldSize || y < 1 || y > settings.fieldSize) {
                System.out.println("Error message");
                continue;
            }
            if (gameField.field[y-1][x-1] != CellStateEnum.EMPTY) {
                System.out.println("Error message");
                continue;
            }
            gameField.placeSymbol(y, x);
            gameField.fieldStateUpdate();
            gameField.printWinner();
            gameField.switchTurn();
        } while (gameField.fieldState == FieldStateEnum.GAME);


    }




}


class GameSettings {
    public int fieldSize = 3, winNumber = 3;
}

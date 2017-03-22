import java.io.IOException;
import java.util.Objects;

/**
 * Created by nina on 3/20/17.
 */
public class Game {
    private GameSettings settings;

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
            initSettings();

        }

    }

    private void initSettings() {
        settings = new GameSettings();

        do {
            printFirstMenuScreen();
            System.out.print("Enter your choice:");
            String input = System.console().readLine();
            if (Objects.equals(input, "1")) {
                //TODO start the game
            }
            if (Objects.equals(input, "2")) {
                try {
                    Runtime.getRuntime().exec("clear");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                customizeSettings();
            }
            if (Objects.equals(input, "0")) {
                System.exit(0);
            }
            try {
                Runtime.getRuntime().exec("clear");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Sorry, you entered a wrong number. Please try again.");
        } while (true);
    }

    private void printFirstMenuScreen() {
        System.out.println("Greetings!");
        System.out.println("To start game with default settings enter 1");
        System.out.println("To customize settings enter 2");
        System.out.println("To exit enter 0");

    }

    private void customizeSettings() {
        do {


            System.out.println("Please enter field size. It must be greater than two. ");
            String input = System.console().readLine();
            int fieldSize;
            try {
                fieldSize = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid value. Try again.");
                continue;
            }
            settings.fieldSize = fieldSize;
            System.out.println("Please enter win number. It must be greater than three but not greater than field size.");
            input = System.console().readLine();
            int winNumber;
            try {
                winNumber = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid value. Try again.");
                continue;
            }
            settings.winNumber = winNumber;
        }
        while (true);

    }
}

class GameSettings {
    public int fieldSize = 3, winNumber = 3;
}

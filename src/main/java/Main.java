import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите команду (help для получения списка команд): ");
            String command = scanner.nextLine();

            if (command.equals(Commands.help.toString())) {
                System.out.println(Commands.ДОБАВИТЬ_МАГАЗИН + "\n" + Commands.ДОБАВИТЬ_ТОВАР + "\n"
                        + Commands.ВЫСТАВИТЬ_ТОВАР + "\n" + Commands.СТАТИСТИКА_ТОВАРОВ);
            }

            if (command.equals(Commands.ДОБАВИТЬ_МАГАЗИН.toString())) {
                addShop();
            }

            if (command.equals(Commands.ДОБАВИТЬ_ТОВАР.toString())) {
                addGood();
            }

            if (command.equals(Commands.ВЫСТАВИТЬ_ТОВАР.toString())) {
                merchGood();
            }

            if (command.equals(Commands.СТАТИСТИКА_ТОВАРОВ.toString())) {
                getStatistics();
            }
        }
    }
    private static void addShop() {


    }

    private static void addGood() {


    }

    private static void merchGood() {


    }

    private static void getStatistics() {


    }

}

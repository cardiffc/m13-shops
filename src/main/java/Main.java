import ch.qos.logback.classic.Logger;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Scanner;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

public class Main {
    private static String databaseName = "local";
    private static String host = "127.0.0.1";
    private static int port = 27017;
    private static String shops = "shops";
    private static String goods = "goods";

    public static void main(String[] args) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);

        MongoCollection<Document> shopsCollection = Operations.getCollection(shops, host, port, databaseName);
        MongoCollection<Document> goodsCollection = Operations.getCollection(goods, host, port, databaseName);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите команду (help для получения списка команд): ");
            String command = scanner.nextLine();

            String[] newAction = command.split(" ");

            if (!Operations.checkCommand(newAction[0])) {
                Operations.getErrorMessage();
            }

            if (newAction[0].equals(Commands.help.toString())) {

                if (newAction.length != 1) {
                    Operations.getErrorMessage();
                } else {
                    Operations.getHelp();
                }
            }

            if (newAction[0].equals(Commands.ДОБАВИТЬ_МАГАЗИН.toString())) {
                if (newAction.length != 2) {
                    Operations.getErrorMessage();
                } else {
                    Operations.addShop(newAction, shopsCollection);
                }
            }

            if (newAction[0].equals(Commands.ДОБАВИТЬ_ТОВАР.toString())) {
                if (newAction.length != 3) {
                    Operations.getErrorMessage();
                } else {
                    Operations.addGood(newAction, goodsCollection);
                }
            }

            if (newAction[0].equals(Commands.ВЫСТАВИТЬ_ТОВАР.toString())) {
                if (newAction.length != 3) {
                    Operations.getErrorMessage();
                } else {
                    Operations.merchGood(newAction, shopsCollection, goodsCollection);
                }
            }

            if (newAction[0].equals(Commands.СТАТИСТИКА_ТОВАРОВ.toString())) {
                if (newAction.length != 1) {
                    Operations.getErrorMessage();
                } else {
                    Operations.getStatistics(shopsCollection);
                }
            }
        }
    }



}

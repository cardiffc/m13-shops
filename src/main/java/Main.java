import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

public class Main {
    private static String databaseName = "local";
    private static String host = "127.0.0.1";
    private static int port = 27017;
    private static String shops = "shops";
    private static String goods = "goods";

    public static void main(String[] args) {
        MongoCollection<Document> shopsCollection = getCollection(shops);
        MongoCollection<Document> goodsCollection = getCollection(goods);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите команду (help для получения списка команд): ");
            String command = scanner.nextLine();

            String[] newAction = command.split(" ");

            if (!checkCommand(newAction[0])) {
                getErrorMessage();
            }

            if (newAction[0].equals(Commands.help.toString())) {

                if (newAction.length != 1) {
                    getErrorMessage();
                } else {
                    getHelp();
                }
            }

            if (newAction[0].equals(Commands.ДОБАВИТЬ_МАГАЗИН.toString())) {
                if (newAction.length != 2) {
                    getErrorMessage();
                } else {
                    addShop(newAction, shopsCollection);
                }
            }

            if (newAction[0].equals(Commands.ДОБАВИТЬ_ТОВАР.toString())) {
                if (newAction.length != 3) {
                    getErrorMessage();
                } else {
                    addGood();
                }
            }

            if (newAction[0].equals(Commands.ВЫСТАВИТЬ_ТОВАР.toString())) {
                if (newAction.length != 3) {
                    getErrorMessage();
                } else {
                    merchGood();
                }
            }

            if (newAction[0].equals(Commands.СТАТИСТИКА_ТОВАРОВ.toString())) {
                if (newAction.length != 1) {
                    getErrorMessage();
                } else {
                    getStatistics();
                }
            }
        }
    }

    private static boolean checkCommand(String command) {
        String[] possibleCommands = Commands.getNames(Commands.class);
        for (int i = 0; i < possibleCommands.length ; i++) {
            if (command.equals(possibleCommands[i])) {
                return true;
            }
        }
        return false;
    }

    private static void getErrorMessage() {
        System.out.println("Неверная команда!!!");
    }
    private static void addShop(String[] action, MongoCollection<Document> shops) {
       Document newShop = new Document();
       newShop.append("name", action[1]);
       shops.insertOne(newShop);
    }

    private static void addGood() {


    }

    private static void merchGood() {


    }

    private static void getStatistics() {


    }

    private static void getHelp() {
        System.out.println(Commands.ДОБАВИТЬ_МАГАЗИН + "\n" + Commands.ДОБАВИТЬ_ТОВАР + "\n"
                + Commands.ВЫСТАВИТЬ_ТОВАР + "\n" + Commands.СТАТИСТИКА_ТОВАРОВ);
    }

    private static MongoCollection<Document> getCollection (String collection) {
        MongoClient mongoClient = new MongoClient(host, port);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> newCollection = database.getCollection(collection);
        newCollection.drop();
        return newCollection;
    }


}

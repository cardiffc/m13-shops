import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;
import org.json.simple.JSONObject;

public class Operations {
    protected static boolean checkCommand(String command) {
        String[] possibleCommands = Commands.getNames(Commands.class);
        for (int i = 0; i < possibleCommands.length ; i++) {
            if (command.equals(possibleCommands[i])) {
                return true;
            }
        }
        return false;
    }

    protected static void getErrorMessage() {
        System.out.println("Неверная команда!!!");
    }

    protected static void addShop(String[] action, MongoCollection<Document> shops) {
        Document newShop = new Document()
                .append("name", action[1]);
        shops.insertOne(newShop);
    }

    protected static void addGood(String[] action, MongoCollection<Document> goods) {

        Document newGood = new Document()
                .append("name", action[1])
                .append("price", Integer.parseInt(action[2]));
        goods.insertOne(newGood);
    }

    protected static void merchGood(String[] action, MongoCollection<Document> shops, MongoCollection<Document> goods) {

        FindIterable<Document> goodExist = goods.find(new Document("name", action[2]));
        if (goodExist.first() != null) {
            JSONObject filter = new JSONObject();
            filter.put("name", action[1]);
            JSONObject updater = new JSONObject();
            updater.put("good", action[2]);
            BsonDocument queryFilter = BsonDocument.parse(filter.toString());
            BsonDocument queryRes = BsonDocument.parse("{$push: " + updater.toString() + "}");
            System.out.println((shops.updateOne(queryFilter, queryRes).getMatchedCount() > 0) ?
                    "Товар успешно выставлен!"
                    : "Нет такого магазина!");
        } else {
            System.out.println("Нет такого товара!");
        }
    }

    protected static void getHelp() {
        System.out.println(Commands.ДОБАВИТЬ_МАГАЗИН + "\n" + Commands.ДОБАВИТЬ_ТОВАР + "\n"
                + Commands.ВЫСТАВИТЬ_ТОВАР + "\n" + Commands.СТАТИСТИКА_ТОВАРОВ);
    }

    protected static MongoCollection<Document> getCollection (String collection, String host, int port, String databaseName) {
        MongoClient mongoClient = new MongoClient(host, port);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> newCollection = database.getCollection(collection);
        newCollection.drop();
        return newCollection;
    }

    protected static void getStatistics() {


    }


}

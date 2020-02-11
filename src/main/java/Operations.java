import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.model.Aggregates;
import static com.mongodb.client.model.Aggregates.*;
import com.mongodb.client.model.Projections;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.io.BsonOutput;
import org.json.simple.JSONObject;
import static com.mongodb.client.model.Accumulators.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class Operations {
    protected static boolean checkCommand(String command) {
        String[] possibleCommands = Commands.getNames(Commands.class);
        for (int i = 0; i < possibleCommands.length; i++) {
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
        if (shops.find(eq("name", action[1])).first() == null
                || goods.find(eq("name", action[2])).first() == null) {
            System.out.println("Не найден товар или магазин!");
        } else {
            shops.updateOne(eq("name", action[1]), new Document("$push", new Document("good", action[2])));
            System.out.println("Товар успешно выставлен!");
        }
    }

    protected static void getHelp () {
        System.out.println(Commands.ДОБАВИТЬ_МАГАЗИН + "\n" + Commands.ДОБАВИТЬ_ТОВАР + "\n"
                + Commands.ВЫСТАВИТЬ_ТОВАР + "\n" + Commands.СТАТИСТИКА_ТОВАРОВ);
    }

    protected static MongoCollection<Document> getCollection (String collection, String host,int port, String
            databaseName){
        MongoClient mongoClient = new MongoClient(host, port);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> newCollection = database.getCollection(collection);
       // newCollection.drop();
        return newCollection;
    }

    protected static void getStatistics (MongoCollection<Document> shops) {
   //   { $lookup: { from:"goods", localField:"good", foreignField:"name", as:"list" } }, {$unwind: {path:"$list"}}, {$group: { _id: {name:"$name"}, avgprice: {$avg: "$list.price"} } }])

        AggregateIterable<Document> newDoc = shops.aggregate(Arrays.asList(lookup("goods","good","name","list"), unwind("$list"),
                group("$name", avg("avgprice", "$list.price"))));

        newDoc.forEach((Block<? super Document>) doc -> {
            System.out.println("Магазин : " + doc.get("_id") + " средняя цена : " + doc.get("avgprice"));

        });

        AggregateIterable<Document> countDoc = shops.aggregate(Arrays.asList(lookup("goods","good","name","list")));
        countDoc.forEach((Block<? super Document>) doc -> {
            System.out.println(doc.get("list"));

        });


       // 1b2c163098be(mongod-4.2.0) local> db.shops.aggregate([{ $lookup: { from:"goods", localField:"good", foreignField:"name", as:"list" } }])


        //System.out.println(newDoc.toString());


    }


}


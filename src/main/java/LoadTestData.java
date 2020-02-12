import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Arrays;

public class LoadTestData {
    protected static void loadData (MongoCollection<Document> shops, MongoCollection<Document> goods) {

        System.out.println("LOADING TEST DATA!");

        String[] shop1 = {"ДОБАВИТЬ_МАГАЗИН", "Пятерочка"};
        String[] shop2 = {"ДОБАВИТЬ_МАГАЗИН", "Дикси"};
        String[] shop3 = {"ДОБАВИТЬ_МАГАЗИН", "Магнит"};
        String[] shop4 = {"ДОБАВИТЬ_МАГАЗИН", "Перекресток"};
        String[][] allShops = {shop1,shop2,shop3,shop4};

        Arrays.stream(allShops).forEach(shop -> Operations.addShop(shop, shops));

        String[] prod1 = {"ДОБАВИТЬ_ТОВАР","Сигареты","190"};
        String[] prod2 = {"ДОБАВИТЬ_ТОВАР","Вафли","15"};
        String[] prod3 = {"ДОБАВИТЬ_ТОВАР","Хлеб","67"};
        String[] prod4 = {"ДОБАВИТЬ_ТОВАР","Икра","350"};
        String[] prod5 = {"ДОБАВИТЬ_ТОВАР","Дефлопе","1024"};
        String[] prod6 = {"ДОБАВИТЬ_ТОВАР","Курица","876"};
        String[] prod7 = {"ДОБАВИТЬ_ТОВАР","Тухлые_сосиски","10"};
        String[][] allProds = {prod1,prod2,prod3,prod4,prod5,prod6,prod7};

        Arrays.stream(allProds).forEach(prod-> Operations.addGood(prod, goods));

        String[] merch1 = {"ВЫСТАВИТЬ_ТОВАР","Пятерочка","Сигареты"};
        String[] merch2 = {"ВЫСТАВИТЬ_ТОВАР","Пятерочка","Вафли"};
        String[] merch3 = {"ВЫСТАВИТЬ_ТОВАР","Пятерочка","Хлеб"};
        String[] merch4 = {"ВЫСТАВИТЬ_ТОВАР","Дикси","Сигареты"};
        String[] merch5 = {"ВЫСТАВИТЬ_ТОВАР","Дикси","Курица"};
        String[] merch6 = {"ВЫСТАВИТЬ_ТОВАР","Дикси","Хлеб"};
        String[] merch7 = {"ВЫСТАВИТЬ_ТОВАР","Дикси","Тухлые_сосиски"};
        String[] merch8 = {"ВЫСТАВИТЬ_ТОВАР","Магнит","Сигареты"};
        String[] merch9 = {"ВЫСТАВИТЬ_ТОВАР","Магнит","Хлеб"};
        String[] merch10 = {"ВЫСТАВИТЬ_ТОВАР","Перекресток","Сигареты"};
        String[] merch11 = {"ВЫСТАВИТЬ_ТОВАР","Перекресток","Вафли"};
        String[] merch12 = {"ВЫСТАВИТЬ_ТОВАР","Перекресток","Хлеб"};
        String[] merch13 = {"ВЫСТАВИТЬ_ТОВАР","Перекресток","Икра"};
        String[] merch14 = {"ВЫСТАВИТЬ_ТОВАР","Перекресток","Дефлопе"};
        String[] merch15 = {"ВЫСТАВИТЬ_ТОВАР","Перекресток","Курица"};
        String[] merch16 = {"ВЫСТАВИТЬ_ТОВАР","Перекресток","Тухлые_сосиски"};
        String[][] allMerch = {merch1,merch2,merch3,merch4,merch5,merch6,merch7,merch8,merch9,merch10,merch11,merch12
                                ,merch13,merch14,merch15,merch16};

        Arrays.stream(allMerch).forEach(merch -> Operations.merchGood(merch,shops,goods));

        System.out.println("TEST DATA LOADED! \n*******************************************************************");
    }
}

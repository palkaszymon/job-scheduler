package pl.palkaszymon;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class JSONFileParser {
    public static List<Order> getOrdersFromFile(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filePath));
        JSONArray ordersList = (JSONArray) obj;
        ArrayList<Order> orders = new ArrayList<>();
        for (Object order : ordersList) {
            JSONObject jsonOrder = (JSONObject) order;
            Order newOrder = new Order(
                    (String) jsonOrder.get("orderId"),
                    BigDecimal.valueOf(Double.parseDouble((String) jsonOrder.get("orderValue"))),
                    Duration.parse((String) jsonOrder.get("pickingTime")),
                    LocalTime.parse((String) jsonOrder.get("completeBy"))

            );
            orders.add(newOrder);
        }
        return orders;
    }

    public static List<Picker> getPickersFromFile(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filePath));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray pickersList = (JSONArray) jsonObject.get("pickers");
        ArrayList<Picker> pickers = new ArrayList<>();
        for (Object pickerId : pickersList) {
            pickers.add(new Picker((String) pickerId));
        }

        return pickers;
    }

    public static LocalTime[] getTimesFromFile(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filePath));
        JSONObject jsonObject = (JSONObject) obj;
        LocalTime startTime = LocalTime.parse((String) jsonObject.get("pickingStartTime"));
        LocalTime endTime = LocalTime.parse((String) jsonObject.get("pickingEndTime"));
        return new LocalTime[]{startTime, endTime};

    }
}


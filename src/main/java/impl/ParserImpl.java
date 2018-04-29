package impl;

import api.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

public class ParserImpl implements Parser {

    public void parseOutput(String arg, Individual solution) {
    }

    public InputData parseInput(String path) throws IOException, ParseException, java.text.ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        JSONObject jsonInput = (JSONObject) obj;

        final int maxGeneration = getInt(jsonInput, "maxGeneration");
        final int populationSize = getInt(jsonInput, "populationSize");
        final Date productionStartDate = parseDate((String) jsonInput.get("productionStartDate"));
        final Date productionEndDate = parseDate((String) jsonInput.get("productionStartDate"));
        final Set<String> operations = parseStringSet((JSONArray) jsonInput.get("operations"));
        final Set<String> rawMaterials = parseStringSet((JSONArray) jsonInput.get("rawMaterials"));
        final List<Product> products = parseProducts((JSONArray) jsonInput.get("products"));
        final List<Delivery> deliveries = parseDeliveries((JSONArray) jsonInput.get("deliveries"));
        final List<Order> orders = parseOrders((JSONArray) jsonInput.get("orders"), products);

        return new InputData(productionStartDate, productionEndDate, operations, rawMaterials, orders, products,
                deliveries, maxGeneration, populationSize);
    }


    private List<Delivery> parseDeliveries(JSONArray array) throws java.text.ParseException {
        List<Delivery> deliveries = new ArrayList<Delivery>();
        for (Object objectOrder : array) {
            deliveries.add(parseDelivery((JSONObject) objectOrder));
        }
        return deliveries;
    }

    private Delivery parseDelivery(JSONObject objectOrder) throws java.text.ParseException {
        final int id = getInt(objectOrder, "id");
        final Date dueDate = parseDate((String) objectOrder.get("date"));
        final Map<String, Integer> materialsDelivered = new HashMap<String, Integer>();
        JSONObject bomJSON = (JSONObject) objectOrder.get("materialsDelivered");
        for (Object keyObject : bomJSON.keySet()) {
            String key = (String) keyObject;
            int qty = getInt(bomJSON, key);
            materialsDelivered.put(key, qty);
        }
        return new Delivery(id, dueDate, materialsDelivered);

    }

    private List<Product> parseProducts(JSONArray array) {
        List<Product> products = new ArrayList<Product>();
        for (Object objectOrder : array) {
            products.add(parseProduct((JSONObject) objectOrder));
        }
        return products;
    }

    private Product parseProduct(JSONObject objectProduct) {
        final int id = getInt(objectProduct, "id");
        JSONObject bomJSON = (JSONObject) objectProduct.get("bom");
        final Map<String, Integer> bom = new HashMap<String, Integer>();
        for (Object keyObject : bomJSON.keySet()) {
            String key = (String) keyObject;
            int qty = getInt(bomJSON, key);
            bom.put(key, qty);
        }
        JSONArray operationTimesJSON = (JSONArray) objectProduct.get("operationTimes");
        final int[] operationTimes = new int[operationTimesJSON.size()];
        for (int i = 0; i < operationTimes.length; i++) {
            operationTimes[i] = ((Long) operationTimesJSON.get(i)).intValue();
        }
        return new Product(id, bom, operationTimes);
    }

    private int getInt(JSONObject jsonObject, String keyVal) {
        return ((Long) jsonObject.get(keyVal)).intValue();
    }

    private List<Order> parseOrders(JSONArray array, List<Product> products) throws java.text.ParseException {
        List<Order> orders = new ArrayList<Order>();
        for (Object objectOrder : array) {
            orders.add(parseOrder((JSONObject) objectOrder, products));
        }
        return orders;
    }

    private Order parseOrder(JSONObject objectOrder, List<Product> products) throws java.text.ParseException {
        final int id = getInt(objectOrder, "id");
        final Date dueDate = parseDate((String) objectOrder.get("dueDate"));
        final int qty = getInt(objectOrder, "qty");
        final int productId = getInt(objectOrder, "productId");

        Product product = products.stream().filter(p -> p.getId() == productId).findFirst().get();
        int timeFirst = IntStream.of(product.getOperationTimes()).sum();
        int delay = product.getOperationTimes()[0];
        for (int j = 1; j < product.getOperationTimes().length; j++) {
            if (product.getOperationTimes()[j] > product.getOperationTimes()[j - 1]+delay){
                delay += product.getOperationTimes()[j] - delay;
            }
        }
        int totalTime = timeFirst + (qty - 1) * delay;
        return new Order(id, dueDate, qty, productId, totalTime);
    }

    private Set<String> parseStringSet(JSONArray array) {
        Set<String> elements = new HashSet<String>();
        for (Object element : array) {
            elements.add((String) element);
        }
        return Collections.unmodifiableSet(elements);
    }

    private Date parseDate(String dateString) throws java.text.ParseException {
        //2018-06-04T00:00:00
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        Date date = format.parse(dateString);
        return date;
    }
}

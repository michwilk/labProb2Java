package api;

import java.util.Map;

public class Product {
    private final int id;
    //mapa surowiec-> ilosc ktore jest potrzebne do wykonania tego produktu
    private final  Map<String, Integer> bom;
    //czasy operacji dla kazdej z maszyn
    private final int[] operationTimes;

    public Product(int id, Map<String, Integer> bom, int[] operationTimes) {
        this.id = id;
        this.bom = bom;
        this.operationTimes = operationTimes;
    }

    public int getId() {
        return id;
    }

    public Map<String, Integer> getBom() {
        return bom;
    }

    public int[] getOperationTimes() {
        return operationTimes;
    }
}

package api;

import java.util.Date;
import java.util.Map;

public class Delivery {
    private final int id;
    private final Date date;
    //Mapa NazwaSurowca->Dostaczona ilsoc
    private final Map<String,Integer> materialsDelivered;

    public Delivery(int id, Date date, Map<String, Integer> materialsDelivered) {
        this.id = id;
        this.date = date;
        this.materialsDelivered = materialsDelivered;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Map<String, Integer> getMaterialsDelivered() {
        return materialsDelivered;
    }
}

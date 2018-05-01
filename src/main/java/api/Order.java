package api;

import java.util.Date;


public class Order {
    private final int id;
    private final Date dueDate;
    private final int productId;
    private final int qty;
    //czas trwania zamowienia (w godzinach)
    private final int duration;

    public Order(int id, Date dueDate, int qty, int productId, int duration) {
        this.id = id;
        this.dueDate = dueDate;
        this.qty = qty;
        this.productId = productId;
        this.duration = duration;

    }

    public int getDuration() {
        return duration;
    }

    public int getId() {
        return id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getQty() {
        return qty;
    }

    public int getProductId() {
        return productId;
    }
}

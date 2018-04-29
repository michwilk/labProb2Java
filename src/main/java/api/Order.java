package api;

import java.util.Date;


public class Order {
    private final int id;
    private final Date dueDate;
    private final int qty;
    private final int productId;
    private final int duration; //czas trwania kazdego z zamowien (w godzinach)

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

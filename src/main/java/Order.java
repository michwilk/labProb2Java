import java.util.Date;


public class Order {
    private final int id;
    private final Date dueDate;
    private final int qty;
    private final int productId;

    public Order(int id, Date dueDate, int qty, int productId) {
        this.id = id;
        this.dueDate = dueDate;
        this.qty = qty;
        this.productId = productId;
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

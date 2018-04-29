import java.util.Date;
import java.util.Set;

public class InputData {
    private final Date productionStartDate;
    private final Set<String> operations;
    private final Set<String> rawMaterials;
    private final Set<Order> orders;
    private final Set<Product> products;
    private final Set<Delivery> deliveres;
    private final int maxGeneration;
    private final int populationSize;

    public InputData(Date productionStartDate, Set<String> operations, Set<String> rawMaterials,
                     Set<Order> orders, Set<Product> products, Set<Delivery> deliveres,
                     int maxGeneration, int populationSize) {
        this.productionStartDate = productionStartDate;
        this.operations = operations;
        this.rawMaterials = rawMaterials;
        this.orders = orders;
        this.products = products;
        this.deliveres = deliveres;
        this.maxGeneration = maxGeneration;
        this.populationSize = populationSize;
    }


    public Date getProductionStartDate() {
        return productionStartDate;
    }

    public Set<String> getOperations() {
        return operations;
    }

    public Set<String> getRawMaterials() {
        return rawMaterials;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Set<Delivery> getDeliveres() {
        return deliveres;
    }

    public int getMaxGeneration() {
        return maxGeneration;
    }

    public int getPopulationSize() {
        return populationSize;
    }
}

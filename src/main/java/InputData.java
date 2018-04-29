import java.util.Date;
import java.util.List;
import java.util.Set;

public class InputData {
    private final Date productionStartDate;
    private final Set<String> operations;
    private final Set<String> rawMaterials;
    private final List<Order> orders;
    private final List<Product> products;
    private final List<Delivery> deliveres;
    private final int maxGeneration;
    private final int populationSize;
    private final int durations[];

    public InputData(Date productionStartDate, Set<String> operations, Set<String> rawMaterials,
                     List<Order> orders, List<Product> products, List<Delivery> deliveres,
                     int maxGeneration, int populationSize, int[] durations) {
        this.productionStartDate = productionStartDate;
        this.operations = operations;
        this.rawMaterials = rawMaterials;
        this.orders = orders;
        this.products = products;
        this.deliveres = deliveres;
        this.maxGeneration = maxGeneration;
        this.populationSize = populationSize;
        this.durations = durations;
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

    public List<Order> getOrders() {
        return orders;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Delivery> getDeliveres() {
        return deliveres;
    }

    public int getMaxGeneration() {
        return maxGeneration;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int[] getDurations() {
        return durations;
    }
}

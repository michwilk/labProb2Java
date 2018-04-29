package api;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class InputData {
    private final Date productionStartDate;
    private final Date productionEndDate;
    private final Set<String> operations;
    private final Set<String> rawMaterials;
    private final List<Order> orders;
    private final List<Product> products;
    private final List<Delivery> deliveries;
    private final int maxGeneration;
    private final int populationSize;

    public InputData(Date productionStartDate, Date productionEndDate, Set<String> operations, Set<String> rawMaterials,
                     List<Order> orders, List<Product> products, List<Delivery> deliveries,
                     int maxGeneration, int populationSize) {
        this.productionStartDate = productionStartDate;
        this.productionEndDate = productionEndDate;
        this.operations = operations;
        this.rawMaterials = rawMaterials;
        this.orders = orders;
        this.products = products;
        this.deliveries = deliveries;
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

    public List<Order> getOrders() {
        return orders;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public int getMaxGeneration() {
        return maxGeneration;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public Date getProductionEndDate() {
        return productionEndDate;
    }
}

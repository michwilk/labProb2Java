import java.util.Map;

public class Product {
    private final int id;
    private final  Map<String, Integer> bom;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id == product.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

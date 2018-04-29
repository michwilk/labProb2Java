package impl;

import api.Delivery;
import api.Individual;
import api.InputData;
import api.Order;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SuppliesValidator {
    public boolean validateSupplies(Individual individual, InputData data) {
        //posortuj po dacie rozpoczecia eventy dodawania i odejmowania
        // a pozniej zdejmuj ze stosu a jak jestuje mne to rzucaj wyjatkie

        //todo a nie odwrocona kolejnosc?
        PriorityQueue<InventoryEvent> inventoryEventsQueue = new PriorityQueue<>();

        for (Delivery delivery : data.getDeliveries()) {
            InventoryEvent event = new InventoryEvent(delivery.getDate(), delivery.getMaterialsDelivered());
            inventoryEventsQueue.add(event);
        }

        for (int i = 0; i < data.getOrders().size(); i++) {
            Order order = data.getOrders().get(i);
            final Map<String, Integer> materialAdjustment = new HashMap<>();
            Map<String, Integer> bom = data.getProducts().stream().filter(p -> p.getId() == order.getProductId()).findFirst().get().getBom();
            for (String material : bom.keySet()) {
                materialAdjustment.put(material, -bom.get(material) * order.getQty());
            }
            InventoryEvent event = new InventoryEvent(individual.getBeginningDates()[i], materialAdjustment);
            inventoryEventsQueue.add(event);
        }

        Map<String, Integer> InventoryState = new HashMap<>();
        for (String material : data.getRawMaterials()) {
            InventoryState.put(material, 0);
        }
        while (!inventoryEventsQueue.isEmpty()) {
            Map<String, Integer> materialAdjustment = inventoryEventsQueue.poll().materialAdjustment;
            for (String material : materialAdjustment.keySet()) {
                int newState = InventoryState.get(material) + materialAdjustment.get(material);
                if (newState < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    class InventoryEvent implements Comparable<InventoryEvent> {
        private final Date date;
        private final Map<String, Integer> materialAdjustment;

        public InventoryEvent(Date date, Map<String, Integer> materialAdjustment) {
            this.date = date;
            this.materialAdjustment = materialAdjustment;
        }

        public Date getDate() {
            return date;
        }

        public Map<String, Integer> getMaterialAdjustment() {
            return materialAdjustment;
        }

        @Override
        public int compareTo(InventoryEvent o) {
            return this.date.compareTo(o.getDate());
        }
    }
}

package api;

import impl.OrderSchedule;

import java.util.Collections;
import java.util.List;

/**
 * Osobnik - przkladowe rozwiazanie problemu
 */
public class Individual {
    //harmonogram dla kazdego zamowienia
    private final List<OrderSchedule> orderSchedules;
    //Ocena osobnika - ilosc godzin od rozpoczenia do zakonczenia wsystkich zamownien
    //czym mniejsza tym lepiej
    private final int score;

    public Individual(List<OrderSchedule> orderSchedules, int score) {
        this.orderSchedules = Collections.unmodifiableList(orderSchedules);
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public List<OrderSchedule> getOrderSchedules() {
        return orderSchedules;
    }


    public OrderSchedule getScheduleForOrderWithId(int orderId) {
        for (OrderSchedule orderSchedule : orderSchedules) {
            if (orderSchedule.getId() == orderId) {
                return orderSchedule;
            }
        }
        throw new IllegalArgumentException("No schedule for order with this id.");
    }
}

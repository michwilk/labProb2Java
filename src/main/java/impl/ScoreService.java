package impl;

import api.InputData;

import java.util.Date;
import java.util.List;

import static impl.Util.hoursDifference;

public class ScoreService {
    /**
     * Ocena harmonogramu(potencjalnego rozwiazania) to ilosc  godzin od rozpoczcia prodockcji
     * do zakocznenia ostatniego z jego zamownien(Order).
     * Czy mniejsza ocena tym lepiej.
     * Do obliczenia tej wartosci potrzeba daty rozpoczecia produkcji,
     * oraz daty zakonczenia ostatniego taska
     *
     * @param orderSchedule
     * @param data
     * @return
     */
    public int assessOrdersSchedule(List<OrderSchedule> orderSchedule, InputData data) {
        Date endOfLastOrder = orderSchedule.stream().map(OrderSchedule::getEndDate).max(Date::compareTo).get();
        //todo sprawdzic to
        return hoursDifference(endOfLastOrder, data.getProductionStartDate());
    }
}

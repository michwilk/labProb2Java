package impl;

import api.Individual;
import api.InputData;

import java.util.*;


//uzuwany czy okresy realizacji zamownien nie zachodza na siebie
// (po generowaniu ale tez po krzyzowaniu )
public class OverLappingValidator {
    public boolean validateOverLapping(Individual individual, InputData data) {
        List<OrderSchedule> shallowCopy = new ArrayList<>(individual.getOrderSchedules());
        //todo sprawdzczy sortuje od najwczesniejszej
        //posortujemy plytka kopie
        Collections.sort(shallowCopy, Comparator.comparing(OrderSchedule::getStartDate));
        for (int i = 0; i < shallowCopy.size() - 1; i++) {
            //jesli czas zakonczenia poprzedniego zamowienia jest po czasie rozpoczecia nastepnego w kolei
            //wtedy osobnik jest niewazny
            if (shallowCopy.get(i).getEndDate().after(shallowCopy.get(i + 1).getStartDate())) {
                return false;
            }
        }
        return true;
    }
}

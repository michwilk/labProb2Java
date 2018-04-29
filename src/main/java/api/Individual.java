package api;

import java.util.Date;

public class Individual {
    //przykladowe rozwiazanie
    private final Date[] beginningDates;//daty  rozpoczecia zamowien

    public Individual(Date[] beginningDates) {
        this.beginningDates = beginningDates;
    }

    public Date[] getBeginningDates() {
        return beginningDates;
    }

}

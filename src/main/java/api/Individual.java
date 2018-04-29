package api;

import java.util.Date;

public class Individual {
    //przykladowe rozwiazanie

    private final Date[] beginningDates;//daty  rozpoczecia zamowien
    private final int[] durations; //czas trwania kazdego z zamowien (w godzinach)

    public Individual(Date[] beginningDates, int[] durations) {
        this.beginningDates = beginningDates;
        this.durations = durations;
    }

    public Date[] getBeginningDates() {
        return beginningDates;
    }

    public int[] getDurations() {
        return durations;
    }
}

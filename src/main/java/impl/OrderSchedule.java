package impl;

import java.util.Date;

import static impl.Util.MILLI_TO_HOUR;

public class OrderSchedule {
    private final int id;
    private final int duration;
    private final Date startDate;
    private final Date endDate;

    public OrderSchedule(int id, int duration, Date startDate) {
        this.id = id;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = new Date(startDate.getTime() + duration * MILLI_TO_HOUR);
    }

    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "OrderSchedule{" +
                "id=" + id +
                ", duration=" + duration +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

package hotel.util;

import java.sql.Date;

public class DateRange {
    private Date begin;
    private Date end;

    public DateRange() {

    }

    public DateRange(Date begin, Date end) {
        validateRange(begin, end);
        this.begin = begin;
        this.end = end;
    }

    public static void validateRange(Date begin, Date end) {
        if (begin.compareTo(end) == 1 || begin.compareTo(end) == 0) {
            throw new IllegalArgumentException();
        }
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        if (end == null) {
            this.begin = begin;
            return;
        }
        validateRange(begin, end);
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        if (begin == null) {
            this.end = end;
            return;
        }
        validateRange(begin, end);
        this.end = end;
    }

    @Override
    public String toString() {
        return "DateRange [begin=" + begin + ", end=" + end + "]";
    }

}

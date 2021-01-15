package hotel.util;

import hotel.exception.handler.ApplicationException;
import hotel.exception.handler.DateValidationException;
import hotel.model.Room;

import java.sql.Date;

public class SupportingUtils {
    private SupportingUtils() {

    }

    public static double calkulateTotalPriceForRoom(String beginDate, String endDate, Room room)
            throws ApplicationException {
        try {
            Date bDate = Date.valueOf(beginDate);
            Date eDate = Date.valueOf(endDate);
            int daysQuantity = eDate.toLocalDate().getDayOfYear() - bDate.toLocalDate().getDayOfYear();
            return daysQuantity * room.getPrice();
        } catch (IllegalArgumentException ex) {
            throw new ApplicationException();
        }
    }

    public static DateRange getDateRange(String before, String end) throws DateValidationException {

        DateRange dateRange = null;
        try {
            Date bDate = Date.valueOf(before);
            Date eDate = Date.valueOf(end);
            dateRange = new DateRange(bDate, eDate);
        } catch (IllegalArgumentException ex) {
            throw new DateValidationException();
        }
        return dateRange;
    }
}

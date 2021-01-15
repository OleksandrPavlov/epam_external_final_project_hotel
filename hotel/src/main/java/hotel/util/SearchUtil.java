package hotel.util;

import hotel.exception.handler.ValidationException;
import hotel.model.Book;
import hotel.model.Room;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SearchUtil {
    private static final int DAY_RANGE = 30;
    private static final DateRange VALID_DATE_RANGE = new DateRange(
            (Date.valueOf((LocalDate.now().atStartOfDay()).toLocalDate())),
            Date.valueOf((LocalDate.now().plusDays(DAY_RANGE).atStartOfDay()).toLocalDate()));

    private static final Predicate<Room> FREE_ROOM_ON_CURRENT_MOMENT = room -> {
        Date currentDate = Date.valueOf(LocalDate.now());
        if (room.getBooks().size() == 0) {
            return true;
        }
        for (Book book : room.getBooks()) {
            if (FreeTimePlaceFinder.isInnerPoint(currentDate, book.getDateRange())) {
                return false;
            }
        }
        return true;
    };

    public static List<Room> getFreeRoom(List<Room> roomList) {
        if (Objects.isNull(roomList)) {
            return null;
        }
        List<Room> freeRoomList = roomList.stream().filter(FREE_ROOM_ON_CURRENT_MOMENT).collect(Collectors.toList());

        return freeRoomList;
    }

    public static List<Room> getFreeRoomOnCurrentMoment(List<Room> roomList) {
        return roomList;

    }

    public static List<Room> findAllFreeRoomsByRange(List<Room> roomList, DateRange dateRange)
            throws ValidationException {
        if (dateRange == null || roomList == null || roomList.size() == 0) {
            throw new IllegalArgumentException();
        }
        // checking if inner range located inside of external range
        if (!FreeTimePlaceFinder.isInnerField(dateRange, VALID_DATE_RANGE)) {
            throw new IllegalArgumentException();
        }

        List<Room> freeRoomsList = new ArrayList<>();
        for (Room room : roomList) {
            if (room.getAvailability() == 0) {
                continue;
            }
            if (room.getBooks().size() == 0) {
                freeRoomsList.add(room);
                continue;
            }

            FreeTimePlaceFinder freeTimePlaceFinder = new FreeTimePlaceFinder(VALID_DATE_RANGE);
            List<DateRange> rangeList = new ArrayList<>();
            for (Book book : room.getBooks()) {
                rangeList.add(book.getDateRange());
            }
            freeTimePlaceFinder.setInnerRangeList(rangeList);
            List<DateRange> freeRanges = freeTimePlaceFinder.getAllFreeRanges();
            if (!(freeRanges == null)) {
                for (DateRange range : freeRanges) {
                    if (FreeTimePlaceFinder.isInnerField(dateRange, range)) {
                        freeRoomsList.add(room);
                    }
                }
            }
        }
        return freeRoomsList.size() == 0 ? null : freeRoomsList;
    }

    public static final DateRange getCurrentDateRestriction() {
        LocalDate currentDate = LocalDate.now();
        DateRange dateRange = new DateRange(Date.valueOf(currentDate), Date.valueOf(currentDate.plusDays(DAY_RANGE)));
        return dateRange;
    }

}

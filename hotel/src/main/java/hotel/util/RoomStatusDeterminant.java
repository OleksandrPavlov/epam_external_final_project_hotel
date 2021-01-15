package hotel.util;

import hotel.model.Book;
import hotel.model.Room;
import hotel.model.Status;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class RoomStatusDeterminant {
    public static List<Room> setStatusOnCurrentDate(List<Room> roomList) {
        Date currentDate = Date.valueOf(LocalDate.now());
        for (Room room : roomList) {
            List<Book> bookList = room.getBooks();
            if (room.getAvailability() == 0) {
                room.setStatus(new Status(4, "unavailable"));
                continue;
            }
            if (bookList.size() == 0) {
                room.setStatus(new Status(1, "free"));
                continue;
            }
            boolean isBusy = false;
            for (Book book : bookList) {
                if (currentDate.compareTo(book.getDateRange().getBegin()) == 0
                        || currentDate.compareTo(book.getDateRange().getBegin()) == 1) {
                    isBusy = true;
                    break;
                }
            }
            if (isBusy) {
                room.setStatus(new Status(3, "occupied"));
            } else {
                room.setStatus(new Status(2, "booked"));
            }

        }

        return roomList;
    }
}

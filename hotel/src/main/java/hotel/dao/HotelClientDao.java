package hotel.dao;

import hotel.exception.handler.ApplicationException;
import hotel.model.Book;
import hotel.model.ClientRequest;
import hotel.model.ManagerResponse;
import hotel.model.Room;

import java.sql.SQLException;
import java.util.List;

public interface HotelClientDao extends HotelDao {
    boolean addClientRequest(ClientRequest clientRequest) throws ApplicationException;

    List<Room> getRoomsByClientId(int id) throws ApplicationException, SQLException;

    int addNewBook(Book book) throws ApplicationException;

    List<ManagerResponse> getOffersByClientId(int id) throws ApplicationException;

    int getNumberOfOffersByClientId(int id) throws ApplicationException;

    void deleteOfferByRoomIdBeginDate(int roomId, String beginDate) throws ApplicationException;

    void updateBookToPaid(int roomId, String beginDate) throws ApplicationException;

    int getCountOfBooks(Room room) throws ApplicationException;


}

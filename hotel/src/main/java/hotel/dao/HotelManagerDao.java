package hotel.dao;

import hotel.exception.handler.ApplicationException;
import hotel.model.*;

import java.sql.SQLException;
import java.util.List;

public interface HotelManagerDao extends HotelDao {
    List<Room> getAllRooms(String sort) throws ApplicationException;

    void addRoom(Room room) throws ApplicationException, SQLException;

    Room getRoomById(int id) throws ApplicationException;

    boolean editRoom(Room room) throws ApplicationException;

    boolean deleteRoom(int roomId) throws ApplicationException;

    int getQuantityOfUnviwedRequests() throws ApplicationException;

    List<Book> getBooks() throws ApplicationException;

    List<ClientRequest> getAllClientRequests() throws ApplicationException, SQLException;

    ClientRequest getClientRequestById(int i) throws ApplicationException, SQLException;

    void offerRoom(int clientRequestId, ManagerResponse managerResponse) throws ApplicationException;


    int getUsersCountByActiveStatus(String activeStatus) throws ApplicationException;

    int changeUserActiveStatus(int user, String activeStatus) throws SQLException;

    List<User> getUsersByActiveStatus(String activeStatus) throws ApplicationException;
}

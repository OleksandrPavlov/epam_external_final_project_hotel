package hotel.service;

import hotel.exception.handler.ApplicationException;
import hotel.exception.handler.ValidationException;
import hotel.model.Book;
import hotel.model.ClientRequest;
import hotel.model.Room;
import hotel.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface HotelManagerService {
    List<Room> getAllRooms(String sort) throws ApplicationException;

    void addRoom(Room room) throws ApplicationException;

    Room getRoomById(int id) throws ApplicationException;

    boolean editRoom(Room room) throws ApplicationException;

    boolean deleteRoom(int roomId) throws ApplicationException;

    int getQuantityOfUnViewedRequests() throws ApplicationException;

    List<ClientRequest> getClientRequests() throws ApplicationException;

    List<Book> getBookList() throws ApplicationException;

    List<Room> getFreeRooms() throws ApplicationException;

    ClientRequest getClientRequestById(String id) throws ApplicationException;

    void offerRoom(HttpServletRequest request) throws ApplicationException, ValidationException;

    List<User> getWaitStatusUserList() throws ApplicationException;

    int GetWaitingUsersCount() throws ApplicationException;

    void disableUser(int userId) throws ApplicationException;

    void enableUser(int userId) throws ApplicationException;

}

package hotel.service;

import hotel.exception.handler.ApplicationException;
import hotel.exception.handler.AuthenticationException;
import hotel.exception.handler.ValidationException;
import hotel.model.Room;
import hotel.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CommonService {
    User login(HttpServletRequest request, HttpServletResponse response)
            throws ApplicationException, AuthenticationException;

    User register(HttpServletRequest request) throws ApplicationException, ValidationException;

    List<Room> getFreeRoomOnCurrentMoment() throws ApplicationException;

    List<Room> getFreeRoomByRange(HttpServletRequest request, List<Room> allRooms) throws ValidationException;

    Room getRoomById(HttpServletRequest id) throws ApplicationException;

    void removeAllStitchedBooks() throws ApplicationException;

    void removeOverdueRequests() throws ApplicationException;

    void removeOverdueResponses() throws ApplicationException;

    void addIdentifier(int userId, String identifier) throws ApplicationException;

    User checkUserInBaseByIdentifier(String identifier) throws ApplicationException;

    void removeIdentifier(User user) throws ApplicationException;

    void registerManager(User user) throws ApplicationException;


}

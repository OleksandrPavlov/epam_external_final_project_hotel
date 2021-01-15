package hotel.dao;

import hotel.exception.handler.ApplicationException;
import hotel.model.User;

import java.sql.SQLException;

public interface CommonHotelDao extends HotelDao {

    User getUserByLogin(String login) throws ApplicationException;

    void deleteOverdueBooks() throws ApplicationException;

    void deleteOverdueRequests() throws ApplicationException;

    void deleteOverdueResponses() throws ApplicationException;

    int addUser(User user) throws SQLException;

    void addIdentifier(int userId, String identifier) throws ApplicationException;

    User getUserByAccessKey(String key) throws ApplicationException;

    void throwOffIdentifier(int userId) throws ApplicationException;

    int addToPendingList(int userId, int statusId) throws ApplicationException, SQLException;

    int getPendedUserStatus(int userId) throws SQLException;

}

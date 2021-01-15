package hotel.dao.impl;

import hotel.dao.CommonHotelDao;
import hotel.exception.handler.ApplicationException;
import hotel.factory.ConnectionUtil;
import hotel.factory.ResultSetHandlerFactory;
import hotel.model.User;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class CommonHotelDaoImpl implements CommonHotelDao {

    private static final String QUERY_TO_FIND_USER_BY_LOGIN = "SELECT * FROM users as u INNER JOIN user_roles as ur WHERE u.role_id=ur.id AND u.login=?";
    private static final String DELETE_OVERDUE_BOOKS = "DELETE FROM booked_rooms WHERE curdate()>=DATE_ADD(reserve_date,Interval 2 DAY)";
    private static final String DELETE_OVERDUE_REQUESTS = "DELETE FROM client_request WHERE CURDATE()>settlement_date;";
    private static final String DELETE_OVERDUE_RESPONSES = "DELETE FROM manager_response WHERE CURDATE()>begin_date;";
    private static final String ADD_USER = "INSERT INTO users (name,surname,patronimic,login,password,mail,phone,role_id,activeStatus) VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String ADD_IDENTIFIER = "UPDATE users SET access_key=? WHERE id=?";
    private static final String GET_USER_BY_IDENTIFIER = "SELECT * FROM users as u INNER JOIN user_roles as ur WHERE u.role_id=ur.id AND u.access_key=?";
    private static final String SET_ACCESS_KEY_NULL = "UPDATE users SET access_key=NULL WHERE id=?";
    private static final String ADD_TO_PENDING_LIST = "INSERT INTO pmgrlist VALUES(?,?,DEFAULT);";
    private static final String GET_PENDED_USER_STATUS = "SELECT statusId FROM pmgrlist WHERE id=?";
    private final QueryRunner queryRunner = new QueryRunner();

    public User getUserByLogin(String login) throws ApplicationException {
        User user;
        Connection con = ConnectionUtil.getConnection();
        try {
            user = queryRunner.query(con, QUERY_TO_FIND_USER_BY_LOGIN, ResultSetHandlerFactory.USER_RESULT_SET_HANDLER,
                    login);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException();
        }
        return user;
    }

    @Override
    public void deleteOverdueBooks() throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        try {
           queryRunner.execute(con, DELETE_OVERDUE_BOOKS);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException();
        }
    }

    @Override
    public void deleteOverdueRequests() throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        try {
           queryRunner.update(con, DELETE_OVERDUE_REQUESTS);
        } catch (SQLException e) {
            throw new ApplicationException();
        }

    }

    @Override
    public void deleteOverdueResponses() throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        try {
          queryRunner.update(con, DELETE_OVERDUE_RESPONSES);
        } catch (SQLException e) {
            throw new ApplicationException();
        }
    }

    @Override
    public int addUser(User user) throws SQLException {
        Connection con = ConnectionUtil.getConnection();
        int key;

        key = queryRunner.insert(con, ADD_USER, ResultSetHandlerFactory.ONE_INTEGER_NUMBER, user.getName(), user.getSurname(), user.getPatronimic(),
                user.getLogin(), user.getPassword(), user.getMail(), user.getPhone(), user.getRoleId(), user.getActiveStatus());
        return key;
    }

    @Override
    public void addIdentifier(int userId, String identifier) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        int changes;
        try {
            changes = queryRunner.update(con, ADD_IDENTIFIER, identifier, userId);
            if (changes == 0) {
                throw new ApplicationException();
            }
        } catch (SQLException e) {
            throw new ApplicationException();
        }

    }

    @Override
    public User getUserByAccessKey(String key) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        User user;
        try {
            user = queryRunner.query(con, GET_USER_BY_IDENTIFIER, ResultSetHandlerFactory.USER_RESULT_SET_HANDLER, key);
        } catch (SQLException e) {
            throw new ApplicationException();
        }
        return user;
    }

    @Override
    public void throwOffIdentifier(int userId) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        try {
            queryRunner.update(con, SET_ACCESS_KEY_NULL, userId);
        } catch (SQLException e) {
            throw new ApplicationException();
        }
    }

    @Override
    public int addToPendingList(int userId, int statusId) throws SQLException {
        Connection con = ConnectionUtil.getConnection();
        return queryRunner.insert(con, ADD_TO_PENDING_LIST, ResultSetHandlerFactory.ONE_INTEGER_NUMBER, userId, statusId);
    }

    @Override
    public int getPendedUserStatus(int userId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        return queryRunner.query(connection, GET_PENDED_USER_STATUS, ResultSetHandlerFactory.ONE_INTEGER_NUMBER, userId);
    }
}

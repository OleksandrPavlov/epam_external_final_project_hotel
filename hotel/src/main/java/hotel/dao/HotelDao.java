package hotel.dao;

import hotel.factory.ResultSetHandlerFactory;
import hotel.model.Room;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface HotelDao {
    ResultSetHandler<List<Room>> GET_ALL_ROOMS_RESULT_SET_HANDLER = ResultSetHandlerFactory.ROOMS_RESULT_SET_HANDLER;

    default void shutDownConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

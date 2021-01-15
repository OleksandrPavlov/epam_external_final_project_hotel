package hotel.resultset.handler;

import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OneRawResultSetHandler<T> implements ResultSetHandler<T> {

    private final ResultSetHandler<T> oneRawResultSetHandler;

    public OneRawResultSetHandler(ResultSetHandler<T> oneRawResultSetHandler) {

        this.oneRawResultSetHandler = oneRawResultSetHandler;
    }

    public T handle(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return oneRawResultSetHandler.handle(rs);
        }
        return null;
    }

}

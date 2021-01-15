package hotel.resultset.handler;

import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MultyRawResultSetHandler<T> implements ResultSetHandler<List<T>> {
    private final ResultSetHandler<T> oneRawResultSetHandler;

    public MultyRawResultSetHandler(ResultSetHandler<T> oneRawResultSetHandler) {
        this.oneRawResultSetHandler = oneRawResultSetHandler;
    }

    public List<T> handle(ResultSet rs) throws SQLException {
        List<T> modelList = new ArrayList<T>();
        while (rs.next()) {
            modelList.add(oneRawResultSetHandler.handle(rs));
        }
        return modelList.size() > 0 ? modelList : null;
    }

}

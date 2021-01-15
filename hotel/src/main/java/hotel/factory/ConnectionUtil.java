package hotel.factory;

import java.sql.Connection;

public class ConnectionUtil {
    private static final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>();

    public static Connection getConnection() {
        return CONNECTION.get();
    }

    public static void setConnection(Connection con) {
        CONNECTION.set(con);
    }

    public static void removeConnection() {
        CONNECTION.remove();
    }
}

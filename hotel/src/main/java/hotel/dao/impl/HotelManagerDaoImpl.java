package hotel.dao.impl;

import hotel.dao.HotelManagerDao;
import hotel.exception.handler.ApplicationException;
import hotel.factory.ConnectionUtil;
import hotel.factory.ResultSetHandlerFactory;
import hotel.model.*;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class HotelManagerDaoImpl implements HotelManagerDao {
    private final QueryRunner queryRunner = new QueryRunner();
    private static final String GET_ALL_ROOMS_QUERY = "SELECT r.id, r.price, r.seats_number,"
            + "r.picture_ref,r.area, r.availability, cl.class_id, cl.class_name, fas.facility_id,"
            + " fas.facility_name, rd.rus, rd.en, r.short_name_rus, r.short_name_en, r.room_number"
            + " FROM rooms as r INNER JOIN room_classes AS cl " + "INNER JOIN room_facility AS rfas "
            + "INNER JOIN facilities AS fas " + "INNER JOIN room_description AS rd WHERE r.class_id = cl.class_id "
            + "AND rfas.room_id=r.id " + "AND rfas.facility_id= fas.facility_id " + "AND rd.room_id=r.id ";
    private static final String ADD_ROOM = "INSERT INTO rooms (price, seats_number, class_id, availability, picture_ref,area,"
            + "short_name_rus,short_name_en, room_number)  VALUES (?,?,?,?,?,?,n?,?,?);";

    private static final String ADD_DESCRIPTION = "INSERT INTO room_description (room_id,rus,en) VALUES(?,?,?)";

    private static final String ADD_FACILITIES_TO_ROOM = "INSERT INTO room_facility (room_id,facility_id) VALUES(?,?)";

    private static final String GET_ROOM_BY_ID = "SELECT r.id, r.price, r.seats_number,"
            + "r.picture_ref,r.area, r.availability, cl.class_id, cl.class_name, fas.facility_id,"
            + " fas.facility_name, rd.rus, rd.en, r.short_name_rus, r.short_name_en, r.room_number" + " FROM rooms as r"
            + " INNER JOIN room_classes AS cl " + "INNER JOIN room_facility AS rfas " + "INNER JOIN facilities AS fas "
            + "INNER JOIN room_description AS rd " + "WHERE r.class_id = cl.class_id " + "AND rfas.room_id=r.id "
            + "AND rfas.facility_id= fas.facility_id " + "AND rd.room_id=r.id AND r.id=?";
    private static final String UPDATE_ROOM_QUERY = "UPDATE rooms SET price=?, seats_number=?, picture_ref=?, "
            + "area=?, class_id=?, short_name_rus=N?, short_name_en=?, room_number=? WHERE id=?";
    private static final String UPDATE_DESCRIPTION_QUERY = "UPDATE room_description SET rus=N?, en=? WHERE room_id=?";
    private static final String ADD_FACILITIES_QUERY = "INSERT INTO room_facility (room_id,facility_id) VALUES(?,?)";
    private static final String DELETE_ROOM_QUERY = "DELETE FROM rooms WHERE id=?";
    private static final String DELETE_FACILITIES_BY_ROOM_ID = "DELETE FROM room_facility WHERE room_id=?";

    private static final String GET_ONLY_ROOM_BY_ID = "SELECT * FROM rooms WHERE id=?";
    private static final String GET_COUNT_ANVIEWED_REQUESTS = "SELECT COUNT(*) FROM client_request WHERE viewed=0";
    private static final String GET_BOOKS = "SELECT * FROM booked_rooms";
    private static final String GET_FACILITY_BY_REQUEST_ID = "SELECT * FROM cl_req_fac AS crf INNER JOIN facilities AS fac WHERE crf.client_req_id=? AND crf.facility_id=fac.facility_id";
    private static final String GET_CLIENT_REQUESTS = "SELECT * FROM client_request AS cr Inner JOIN room_classes AS rc INNER JOIN users AS u WHERE cr.apartment_class=rc.class_id AND cr.client_id=u.id";
    private static final String GET_CLIENT_REQUEST_BY_ID = "SELECT * FROM client_request AS cr Inner JOIN room_classes AS rc INNER JOIN users AS u WHERE cr.apartment_class=rc.class_id AND cr.client_id=u.id AND cr.client_request_id=?";
    private static final String OFFER_ROOM = "INSERT INTO manager_response (client_id,room_id,begin_date,end_date) VALUES(?,?,?,?)";
    private static final String DELETE_REQUEST_BY_ID = "DELETE FROM client_request WHERE client_request_id=?";
    private static final String GET_ALL_USERS_BY_ACTIVE_STATUS = "SELECT * FROM users as u INNER JOIN user_roles as ur WHERE u.role_id=ur.id AND activeStatus=?";
    private static final String GET_USERS_BY_ACTIVE_STATUS_COUNT = "SELECT COUNT(1) FROM hotel.users WHERE activeStatus=?";
    private static final String CHANGE_USER_ACTIVE_STATUS = "UPDATE users SET activeStatus = ? WHERE id=?;";

    @Override
    public List<Room> getAllRooms(String sort) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        List<Room> rooms = null;
        try {
            rooms = queryRunner.query(con, getSortingQuery(sort), GET_ALL_ROOMS_RESULT_SET_HANDLER);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException(e);
        }
        return rooms;
    }

    @Override
    public void addRoom(Room room) throws ApplicationException, SQLException {
        Connection con = ConnectionUtil.getConnection();
        PreparedStatement ps = null;

        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement(ADD_ROOM, Statement.RETURN_GENERATED_KEYS);
            con.setSavepoint("sp1");
            ps.setDouble(1, room.getPrice());
            ps.setInt(2, room.getSeatNumber());
            ps.setInt(3, room.getRoomClass().getClassId());
            ps.setInt(4, room.getAvailability());
            ps.setString(5, room.getpRef());
            ps.setInt(6, room.getArea());
            ps.setString(7, room.getShortNameRus());
            ps.setString(8, room.getShortNameEn());
            ps.setInt(9, room.getRoomNumber());

            // Executing Update of rooms
            ps.executeUpdate();

            // Get default generated key of room
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                room.setId(rs.getInt(1));
                addDescription(room, ps, con);
                addFacilities(room, ps, con);
            }

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
            throw new ApplicationException();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }

    }

    private void addDescription(Room room, PreparedStatement ps, Connection con) throws SQLException {
        ps = con.prepareStatement(ADD_DESCRIPTION);
        ps.setInt(1, room.getId());
        ps.setString(2, room.getDescription().getRusVersion());
        ps.setString(3, room.getDescription().getEnVersion());
        ps.executeUpdate();
    }

    private void addFacilities(Room room, PreparedStatement ps, Connection con) throws SQLException {
        ps = con.prepareStatement(ADD_FACILITIES_TO_ROOM);
        for (Facility fas : room.getFacilities()) {
            ps.setInt(1, room.getId());
            ps.setInt(2, fas.getFacilityId());
            ps.executeUpdate();
        }
    }

    @Override
    public Room getRoomById(int id) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        List<Room> room = null;
        try {
            room = queryRunner.query(con, GET_ROOM_BY_ID, ResultSetHandlerFactory.ROOMS_RESULT_SET_HANDLER, id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException(e);

        }
        if (Objects.nonNull(room)) {
            for (Room r : room) {
                return r;
            }
        }
        return null;
    }

    @Override
    public boolean editRoom(Room room) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        try {
            if (!ifRoomExist(room.getId(), con, ps)) {
                return false;
            }
        } catch (SQLException e2) {
            throw new ApplicationException(e2);
        }
        try {
            ps = con.prepareStatement(UPDATE_ROOM_QUERY);
            con.setAutoCommit(false);
            ps.setDouble(1, room.getPrice());
            ps.setInt(2, room.getSeatNumber());
            ps.setString(3, room.getpRef());
            ps.setInt(4, room.getArea());
            ps.setInt(5, room.getRoomClass().getClassId());
            ps.setString(6, room.getShortNameRus());
            ps.setString(7, room.getShortNameEn());
            ps.setInt(8, room.getRoomNumber());
            ps.setInt(9, room.getId());
            ps.executeUpdate();
            editDescription(room, ps, con);
            editFacility(room, ps, con);
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new ApplicationException(e1);
            }
            e.printStackTrace();
        }
        return true;
    }

    private void editDescription(Room room, PreparedStatement ps, Connection con) throws SQLException {
        ps = con.prepareStatement(UPDATE_DESCRIPTION_QUERY);
        ps.setString(1, room.getDescription().getRusVersion());
        ps.setString(2, room.getDescription().getEnVersion());
        ps.setInt(3, room.getId());
        ps.executeUpdate();
    }

    private void editFacility(Room room, PreparedStatement ps, Connection con) throws SQLException {
        deleteFacilitiesByRoomId(room, ps, con);
        ps = con.prepareStatement(ADD_FACILITIES_QUERY);
        for (Facility fac : room.getFacilities()) {
            ps.setInt(1, room.getId());
            ps.setInt(2, fac.getFacilityId());
            ps.executeUpdate();
        }
    }

    private void deleteFacilitiesByRoomId(Room room, PreparedStatement ps, Connection con) throws SQLException {
        ps = con.prepareStatement(DELETE_FACILITIES_BY_ROOM_ID);
        ps.setInt(1, room.getId());
        ps.executeUpdate();
    }

    private boolean ifRoomExist(int id, Connection con, PreparedStatement ps) throws SQLException {
        ps = con.prepareStatement(GET_ONLY_ROOM_BY_ID);
        ps.setInt(1, id);
        return ps.executeQuery().next();
    }

    @Override
    public boolean deleteRoom(int roomId) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        boolean deleted = false;
        try {
            PreparedStatement ps = con.prepareStatement(DELETE_ROOM_QUERY);
            ps.setInt(1, roomId);
            deleted = ps.execute();
        } catch (SQLException e) {
            throw new ApplicationException();
        }
        return deleted;
    }

    private String getSortingQuery(String sort) {
        if (Objects.isNull(sort)) {
            return GET_ALL_ROOMS_QUERY;
        }
        switch (sort) {
            case "price_sort":
                return GET_ALL_ROOMS_QUERY + "ORDER BY r.price";
            case "seat_number_sort":
                return GET_ALL_ROOMS_QUERY + "ORDER BY r.seats_number";
            case "class_sort":
                return GET_ALL_ROOMS_QUERY + "ORDER BY r.class_id";
            default:
                return GET_ALL_ROOMS_QUERY;
        }
    }

    @Override
    public int getQuantityOfUnviwedRequests() throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(GET_COUNT_ANVIEWED_REQUESTS);
            if (rs.next()) {
                int a = rs.getInt(1);
                return a;
            }
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
        return 0;
    }

    @Override
    public List<Book> getBooks() throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        try {
            List<Book> list = queryRunner.query(con, GET_BOOKS, ResultSetHandlerFactory.BOOK_RSH);
            return list;
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    public List<ClientRequest> getAllClientRequests() throws ApplicationException, SQLException {
        Connection con = ConnectionUtil.getConnection();
        List<ClientRequest> clientRequestList = null;
        try {
            con.setAutoCommit(false);
            clientRequestList = queryRunner.query(con, GET_CLIENT_REQUESTS, ResultSetHandlerFactory.CLIENT_REQUEST_RSH);
            if (clientRequestList != null) {
                for (ClientRequest clientRequest : clientRequestList) {
                    clientRequest.setFacilities(getFacilitiesByClientRequestId(clientRequest.getId(), con));
                }
                con.commit();
            }
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }
        return clientRequestList;
    }

    private List<Facility> getFacilitiesByClientRequestId(int id, Connection con) throws SQLException {
        List<Facility> facList = null;
        facList = queryRunner.query(con, GET_FACILITY_BY_REQUEST_ID, ResultSetHandlerFactory.FACILITY_RSH, id);
        return facList;
    }

    @Override
    public ClientRequest getClientRequestById(int id) throws ApplicationException, SQLException {
        Connection con = ConnectionUtil.getConnection();
        List<ClientRequest> clientRequest = null;
        clientRequest = queryRunner.query(con, GET_CLIENT_REQUEST_BY_ID, ResultSetHandlerFactory.CLIENT_REQUEST_RSH,
                id);
        if (clientRequest != null) {
            clientRequest.get(0).setFacilities(getFacilitiesByClientRequestId(clientRequest.get(0).getId(), con));
        }
        return clientRequest != null ? clientRequest.get(0) : null;
    }

    @Override
    public void offerRoom(int clientRequestId, ManagerResponse managerResponse) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(OFFER_ROOM);
            ps.setInt(1, managerResponse.getClientId());
            ps.setInt(2, managerResponse.getRoomId());
            ps.setString(3, managerResponse.getDateRange().getBegin().toString());
            ps.setString(4, managerResponse.getDateRange().getEnd().toString());
            ps.execute();
            deleteRequest(clientRequestId, con);
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new ApplicationException();
        }
    }

    public void deleteRequest(int id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ps = con.prepareStatement(DELETE_REQUEST_BY_ID);
        ps.setInt(1, id);
        ps.execute();
    }

    @Override
    public int getUsersCountByActiveStatus(String activeStatus) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        int pendedUserCount;
        try {
            pendedUserCount = queryRunner.query(con, GET_USERS_BY_ACTIVE_STATUS_COUNT, ResultSetHandlerFactory.ONE_INTEGER_NUMBER, activeStatus);
        } catch (SQLException throwable) {
            throw new ApplicationException(throwable);
        }
        return pendedUserCount;
    }

    @Override
    public int changeUserActiveStatus(int userId, String statusId) throws SQLException {
        Connection con = ConnectionUtil.getConnection();
        return queryRunner.update(con, CHANGE_USER_ACTIVE_STATUS, statusId, userId);
    }

    @Override
    public List<User> getUsersByActiveStatus(String activeStatus) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        List<User> users = null;
        try {
            users = queryRunner.query(con, GET_ALL_USERS_BY_ACTIVE_STATUS, ResultSetHandlerFactory.USER_LIST_RESULT_SET_HANDLER, activeStatus);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException(e);
        }
        return users;
    }
}


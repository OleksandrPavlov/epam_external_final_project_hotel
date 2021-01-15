package hotel.dao.impl;

import hotel.dao.HotelClientDao;
import hotel.exception.handler.ApplicationException;
import hotel.factory.ConnectionUtil;
import hotel.factory.ResultSetHandlerFactory;
import hotel.model.*;
import hotel.util.SupportingUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HotelClientDaoImpl implements HotelClientDao {
    private static final QueryRunner queryRunner = new QueryRunner();
    private static final String ADD_CLIENT_REQUEST = "INSERT INTO client_request (client_id,seats_number,night_number,max_price,apartment_class,settlement_date,comment) VALUES(?,?,?,?,?,?,?)";
    private static final String ADD_CLIENT_REQUEST_FACILITY = "INSERT INTO cl_req_fac (client_req_id,facility_id) VALUES(?,?)";
    private static final String GET_BOOKS_BY_CLIENT_ID = "SELECT * FROM booked_rooms WHERE client_id=?";
    private static final String GET_ROOM_BY_ID = "SELECT r.id, r.price, r.seats_number,"
            + "r.picture_ref,r.area, r.availability, cl.class_id, cl.class_name, fas.facility_id,"
            + " fas.facility_name, rd.rus, rd.en, r.short_name_rus, r.short_name_en, r.room_number" + " FROM rooms as r"
            + " INNER JOIN room_classes AS cl " + "INNER JOIN room_facility AS rfas " + "INNER JOIN facilities AS fas "
            + "INNER JOIN room_description AS rd " + "WHERE r.class_id = cl.class_id " + "AND rfas.room_id=r.id "
            + "AND rfas.facility_id= fas.facility_id " + "AND rd.room_id=r.id AND r.id=?";
    private static final String ADD_BOOK = "INSERT INTO booked_rooms (client_id, room_id, book_start, book_end, paid,total) VALUES(?,?,?,?,'n',?)";
    private static final String GET_OFFERS = "SELECT * FROM manager_response WHERE client_id=?";
    private static final String COUNT_OFFERS = "SELECT COUNT(*)  FROM manager_response WHERE client_id=?";
    private static final String DELETE_OFFER = "DELETE FROM manager_response WHERE room_id=? AND begin_date=?";
    private static final String PAY_BOOK = "UPDATE booked_rooms SET paid='y' WHERE room_id=? AND book_start=?";

    private static final String GET_COUNT_OF_BOOKS_FOR_ROOM = "SELECT COUNT(*)  FROM booked_rooms WHERE room_id=?";

    @Override
    public boolean addClientRequest(ClientRequest clientRequest) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        try (PreparedStatement ps = con.prepareStatement(ADD_CLIENT_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            ps.setInt(1, clientRequest.getClientId());
            ps.setInt(2, clientRequest.getSeatsNumber());
            ps.setInt(3, clientRequest.getNightNumber());
            ps.setDouble(4, clientRequest.getMaxPrice());
            ps.setInt(5, clientRequest.getRoomClass().getClassId());
            ps.setString(6, clientRequest.getSettlementDate().toString());
            ps.setString(7, clientRequest.getComment());
            ps.execute();
            ResultSet id = ps.getGeneratedKeys();
            if (id.next()) {
                addRequestFacility(id.getInt(1), clientRequest, con);
            } else {
                throw new SQLException();
            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
        return true;
    }

    private void addRequestFacility(int requestId, ClientRequest clientRequest, Connection connection)
            throws SQLException {
        PreparedStatement ps = connection.prepareStatement(ADD_CLIENT_REQUEST_FACILITY);
        for (Facility fac : clientRequest.getFacilities()) {
            ps.setInt(1, requestId);
            ps.setInt(2, fac.getFacilityId());
            ps.execute();
        }
    }

    @Override
    public List<Room> getRoomsByClientId(int id) throws ApplicationException, SQLException {
        Connection con = ConnectionUtil.getConnection();
        List<Book> bookList = queryRunner.query(con, GET_BOOKS_BY_CLIENT_ID, ResultSetHandlerFactory.BOOK_RSH, id);
        if (bookList == null) {
            return null;
        }
        return assignBooksForRooms(bookList);
    }

    // This method will find in DB relevant rooms for existing books
    private List<Room> assignBooksForRooms(List<Book> bookList) throws SQLException, ApplicationException {
        if (bookList == null) {
            throw new IllegalArgumentException();
        }
        if (bookList.size() == 0) {
            return null;
        }
        List<Room> currentRoomList = getRoomsByIdList(getRoomIdList(bookList));
        for (Room room : currentRoomList) {

            List<Book> currentBookList = bookList.stream()
                    .filter(book -> room.getId() == book.getRoomId())
                    .collect(Collectors.toList());
            room.setBooks(currentBookList);
        }
        return currentRoomList;
    }

    private List<Integer> getRoomIdList(List<Book> bookList) {

        int currentId = 0;
        List<Integer> listId = new ArrayList<>();
        for (Book book : bookList) {
            currentId = book.getRoomId();
            boolean consistOf = false;
            for (Integer curId : listId) {
                if (currentId == curId) {
                    consistOf = true;
                }
            }
            if (!consistOf) {
                listId.add(currentId);
            }
        }
        return listId;
    }

    private List<Room> getRoomsByIdList(List<Integer> idList) throws SQLException, ApplicationException {
        List<Room> roomList = null;
        for (Integer id : idList) {
            if (roomList == null) {
                roomList = new ArrayList<>();
            }
            roomList.add(getRoomById(id));
        }
        return roomList;
    }

    private Room getRoomById(int id) throws SQLException, ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        List<Room> room = queryRunner.query(con, GET_ROOM_BY_ID, ResultSetHandlerFactory.ROOMS_RESULT_SET_HANDLER, id);
        if (room != null) {
            room.get(0).setPop(getCountOfBooks(room.get(0)));
        }
        return room != null ? room.get(0) : null;
    }

    @Override
    public int addNewBook(Book book) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();

        int changes = 0;
        try {
            PreparedStatement ps = con.prepareStatement(ADD_BOOK);
            ps.setInt(1, book.getClient_id());
            ps.setInt(2, book.getRoomId());
            ps.setString(3, book.getDateRange().getBegin().toString());
            ps.setString(4, book.getDateRange().getEnd().toString());
            ps.setDouble(5, book.getTotal());
            changes = ps.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
        return changes;
    }

    @Override
    public List<ManagerResponse> getOffersByClientId(int id) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        List<ManagerResponse> mrl = null;
        try {
            mrl = queryRunner.query(con, GET_OFFERS, ResultSetHandlerFactory.OFFER_RSH, id);
            if (mrl != null) {
                for (ManagerResponse mr : mrl) {
                    mr.setRoom(getRoomById(mr.getRoomId()));
                    mr.setTotal(SupportingUtils.calkulateTotalPriceForRoom(
                            mr.getDateRange().getBegin().toLocalDate().toString(),
                            mr.getDateRange().getEnd().toLocalDate().toString(), mr.getRoom()));
                }
            }
        } catch (SQLException | IllegalArgumentException e) {
            throw new ApplicationException();
        }
        return mrl;
    }

    @Override
    public int getNumberOfOffersByClientId(int id) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement st = con.prepareStatement(COUNT_OFFERS);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
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
    public void deleteOfferByRoomIdBeginDate(int roomId, String beginDate) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        try {
            queryRunner.execute(con, DELETE_OFFER, roomId, beginDate);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException();
        }
    }

    @Override
    public void updateBookToPaid(int roomId, String beginDate) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        try {
            queryRunner.execute(con, PAY_BOOK, roomId, beginDate);
        } catch (SQLException e) {
            throw new ApplicationException();
        }
    }

    @Override
    public int getCountOfBooks(Room room) throws ApplicationException {
        Connection con = ConnectionUtil.getConnection();
        int count = 0;
        try {
            PreparedStatement ps = con.prepareStatement(GET_COUNT_OF_BOOKS_FOR_ROOM);
            ps.setInt(1, room.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new ApplicationException();
        }
        return count;
    }

}

package hotel.factory;

import hotel.model.*;
import hotel.util.DateRange;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ResultSetHandlerFactory {
    private ResultSetHandlerFactory() {

    }


    public static ResultSetHandler<User> USER_RESULT_SET_HANDLER = (ResultSet rs) -> {
        User user = null;
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setSurname(rs.getString(3));
            user.setPatronimic(rs.getString(4));
            user.setLogin(rs.getString(5));
            user.setPassword(rs.getString(6));
            user.setMail(rs.getString(7));
            user.setPhone(rs.getString(8));
            user.setGender(rs.getInt(9));
            user.setRegisterDate(rs.getDate(10));
            user.setRole(rs.getString(15));
            user.setActiveStatus(rs.getString("activeStatus"));
        }
        return user;
    };
    public static ResultSetHandler<List<User>> USER_LIST_RESULT_SET_HANDLER = (rs) -> {
        List<User> userList = new ArrayList<>();
        User currentUser = null;
        do {
            currentUser = USER_RESULT_SET_HANDLER.handle(rs);
            if (Objects.nonNull(currentUser)) {
                userList.add(currentUser);
            }
        } while (Objects.nonNull(currentUser));
        return userList;
    };
    public static ResultSetHandler<List<Room>> ROOMS_RESULT_SET_HANDLER = rs -> {
        if (rs == null) {
            return null;
        }
        HashMap<Integer, List<Facility>> facilties = new HashMap<>();

        List<Room> rooms = new ArrayList<>();

        while (rs.next()) {

            Facility facility = new Facility(rs.getInt("facility_id"), rs.getString("facility_name"));
            Room room = new Room();
            room.setId(rs.getInt(1));
            room.setPrice(rs.getDouble(2));
            room.setSeatNumber(rs.getInt(3));
            room.setRoomClass(new RoomClass(rs.getInt(7), rs.getString(8)));
            room.setAvailability(rs.getInt(6));
            room.setpRef(rs.getString(4));
            room.setArea(rs.getInt(5));
            room.setDescription(new RoomDescription(rs.getString(11), rs.getString(12)));
            room.setShortNameRus(rs.getString(13));
            room.setShortNameEn(rs.getString(14));
            room.setRoomNumber(rs.getInt(15));
            rooms.add(room);

            List<Facility> temp = facilties.get(room.getId());
            if (Objects.isNull(temp)) {
                List<Facility> tempList = new ArrayList<>();
                tempList.add(facility);
                facilties.put(room.getId(), tempList);
            } else {
                facilties.get(room.getId()).add(facility);
            }
        }
        if (rooms.size() == 0) {
            return null;
        }
        for (Room r : rooms) {
            r.setFacilities(facilties.get(r.getId()));
        }
        // Deleting dublicats with saving input order
        List<Room> finalList = new ArrayList<>();
        while (rooms.size() > 0) {
            finalList.add(rooms.get(0));
            Room tR = rooms.get(0);
            rooms.removeIf(el ->
                    tR.getId() == el.getId()
            );
        }
        return finalList;
    };
    // This will return dateRanges for Rooms reservation
    public ResultSetHandler<List<DateRange>> GET_BOOK_RANGE = rs -> {
        return null;
    };

    public static ResultSetHandler<List<Book>> BOOK_RSH = rs -> {
        List<Book> book = new ArrayList<>();
        while (rs.next()) {
            Book b = new Book();
            b.setClient_id(rs.getInt(1));
            b.setRoomId(rs.getInt(2));
            Date begin = Date.valueOf(rs.getDate(3).toLocalDate().atStartOfDay().toLocalDate());
            Date end = Date.valueOf(rs.getDate(4).toLocalDate().atStartOfDay().toLocalDate());
            b.setDateRange(new DateRange(begin, end));
            b.setReserveDate(rs.getTimestamp(6));
            b.setTotal(rs.getDouble(7));

            b.setPaid(rs.getString(5));
            book.add(b);
        }
        if (book.size() > 0) {
            return book;
        }
        return null;
    };

    public static ResultSetHandler<List<ClientRequest>> CLIENT_REQUEST_RSH = rs -> {
        List<ClientRequest> clientRequestList = null;
        while (rs.next()) {
            if (clientRequestList == null) {
                clientRequestList = new ArrayList<>();
            }
            ClientRequest clientRequest = new ClientRequest();
            clientRequest.setId(rs.getInt(1));
            clientRequest.setClientId(rs.getInt(2));
            clientRequest.setSeatsNumber(rs.getInt(3));
            clientRequest.setNightNumber(rs.getInt(4));
            clientRequest.setMaxPrice(rs.getDouble(5));
            clientRequest.setRoomClass(new RoomClass(rs.getInt(6), rs.getString(11)));
            clientRequest.setSettlementDate(rs.getDate(7));
            clientRequest.setComment(rs.getString(8));
            clientRequest.setViewed(rs.getInt(9));
            clientRequest.setClientName(rs.getString(13));
            clientRequest.setClientSurname(rs.getString(14));
            clientRequestList.add(clientRequest);
        }
        return clientRequestList;
    };

    public static ResultSetHandler<List<Facility>> FACILITY_RSH = rs -> {
        List<Facility> facilityList = null;
        if (rs != null) {
            while (rs.next()) {
                if (facilityList == null) {
                    facilityList = new ArrayList<>();
                }
                facilityList.add(new Facility(rs.getInt(3), rs.getString(4)));
            }
        }
        return facilityList;
    };
    public static ResultSetHandler<List<ManagerResponse>> OFFER_RSH = rs -> {
        if (rs == null) {
            return null;
        }
        List<ManagerResponse> managerResponses = null;
        while (rs.next()) {
            if (managerResponses == null) {
                managerResponses = new ArrayList<>();
            }
            ManagerResponse managerResponse = new ManagerResponse();
            managerResponse.setClientId(rs.getInt(1));
            managerResponse.setRoomId(rs.getInt(2));
            managerResponse.setDateRange(new DateRange(rs.getDate(3), rs.getDate(4)));
            managerResponses.add(managerResponse);
        }
        return managerResponses;
    };


    public static ResultSetHandler<Integer> ONE_INTEGER_NUMBER = rs -> {
        return rs.next() ? rs.getInt(1) : 0;
    };

}

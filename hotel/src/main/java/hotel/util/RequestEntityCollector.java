package hotel.util;

import hotel.constant.Constants;
import hotel.exception.handler.ApplicationException;
import hotel.exception.handler.DateValidationException;
import hotel.exception.handler.ValidationException;
import hotel.model.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RequestEntityCollector {
    private RequestEntityCollector() {

    }

    public static Room getRoom(HttpServletRequest request) throws ValidationException {
        Room room = new Room();
        try {
            if (request.getParameter(Constants.ROOM_ID) != null) {
                room.setId(Integer.parseInt(request.getParameter(Constants.ROOM_ID)));
            }
            room.setPrice(Double.parseDouble(request.getParameter(Constants.ROOM_PRICE_ATTRIBUTE)));
            room.setSeatNumber(Integer.parseInt(request.getParameter(Constants.ROOM_SEAT_NUMBER_ATTRIBUTE)));
            room.setpRef(request.getParameter(Constants.ROOM_PICTURE_ATTRIBUTE));
            room.setArea(Integer.parseInt(request.getParameter(Constants.ROOM_AREA_ATTRIBUTE)));
            String status = request.getParameter(Constants.ROOM_STATUS_ATTRIBUTE);
            if (Objects.nonNull(status)) {
                room.setStatus(getStatus(Integer.parseInt(status)));
            }
            room.setAvailability(Integer.parseInt(request.getParameter(Constants.ROOM_AVAILABILITY)));
            room.setRoomNumber(Integer.parseInt(request.getParameter(Constants.ROOM_NUMBER_ATTRIBUTE)));
            room.setRoomClass(getRoomClass(Integer.parseInt(request.getParameter(Constants.ROOM_CLASS_ATTRIBUTE))));
            room.setFacilities(new ArrayList<Facility>());
            for (String fas : request.getParameterValues(Constants.ROOM_FACILITY_ATTRIBUTE)) {
                room.getFacilities().add(getFacility(Integer.parseInt(fas)));
            }
            room.setDescription(new RoomDescription(request.getParameter(Constants.ROOM_DESC_RUS_ATTRIBUTE),
                    request.getParameter(Constants.ROOM_DESC_EN_ATTRIBUTE)));
            room.setShortNameRus(request.getParameter(Constants.ROOM_SHORT_DESC_RUS_ATTRIBUTE));
            room.setShortNameEn(request.getParameter(Constants.ROOM_SHORT_DESC_EN_ATTRIBUTE));
        } catch (NumberFormatException ex) {
            throw new ValidationException(Constants.WRONG_INPUT_EXCEPTION, ex);
        }
        return room;
    }

    public static ClientRequest getClientRequest(HttpServletRequest request) throws ValidationException {
        ClientRequest clientRequest = new ClientRequest();
        User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER_SESSION_ATTRIBUTE);
        try {
            clientRequest.setClientId(user.getId());
            clientRequest.setMaxPrice(Double.parseDouble(request.getParameter(Constants.REQ_MAX_PRICE)));
            clientRequest.setComment(request.getParameter(Constants.REQ_COMMENT));
            clientRequest.setNightNumber(Integer.parseInt(request.getParameter(Constants.REQ_NIGHT_QUANTITY)));
            clientRequest
                    .setRoomClass(getRoomClass(Integer.parseInt(request.getParameter(Constants.ROOM_CLASS_ATTRIBUTE))));
            clientRequest.setSeatsNumber(Integer.parseInt(request.getParameter(Constants.ROOM_SEAT_NUMBER_ATTRIBUTE)));
            clientRequest.setSettlementDate(extractDate(request));
            clientRequest.setComment(request.getParameter(Constants.REQ_COMMENT));
            clientRequest.setFacilities(getFacilities(request));
            Validator.clientRequestValidate(clientRequest);
        } catch (NumberFormatException ex) {
            throw new ValidationException(Constants.WRONG_INPUT_EXCEPTION, ex);
        } catch (IllegalArgumentException ex) {
            throw new ValidationException(Constants.WRONG_INPUT_EXCEPTION, ex);
        }
        return clientRequest;
    }

    private static Status getStatus(int id) {
        switch (id) {
            case 1:
                return new Status(id, "free");
            case 2:
                return new Status(id, "booked");
            case 3:
                return new Status(id, "occupied");
            case 4:
                return new Status(id, "unavailable");
            default:
                // This is temporarily exception while i'll come up with some better
                throw new NumberFormatException();
        }
    }

    private static RoomClass getRoomClass(int id) {
        switch (id) {
            case 1:
                return new RoomClass(id, "econom");
            case 2:
                return new RoomClass(id, "standard");
            case 3:
                return new RoomClass(id, "improved");

            case 4:
                return new RoomClass(id, "luxury");
            default:
                // This is temporarily exception while i'll come up with some better
                throw new NumberFormatException();
        }
    }

    private static Facility getFacility(int id) {
        switch (id) {
            case 1:
                return new Facility(id, "wi-fi");
            case 2:
                return new Facility(id, "condition");
            case 3:
                return new Facility(id, "tv");
            case 4:
                return new Facility(id, "cooller");
            default:
                // This is temporarily exception while i'll come up with some better
                throw new NumberFormatException();
        }
    }

    private static List<Facility> getFacilities(HttpServletRequest request) throws ValidationException {
        List<Facility> fac = new ArrayList<>();
        if (request.getParameterValues(Constants.ROOM_FACILITY_ATTRIBUTE) == null) {
            throw new ValidationException();
        }
        for (String id : request.getParameterValues(Constants.ROOM_FACILITY_ATTRIBUTE)) {
            int d_id = Integer.parseInt(id);
            fac.add(getFacility(d_id));
        }
        if (fac.size() < 0) {
            // Access empty input in future
            throw new ValidationException("You forgot about facilities!", null);
        }
        return fac;
    }

    private static Date extractDate(HttpServletRequest request) {
        Date date = Date.valueOf(request.getParameter(Constants.REQ_SETTLEMENT_DATE));
        return date;
    }

    public static Book getBook(HttpServletRequest request)
            throws ApplicationException, DateValidationException, ValidationException {
        User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER_SESSION_ATTRIBUTE);
        if (user == null) {
            throw new ApplicationException();
        }

        Book book = new Book();
        book.setClient_id(user.getId());
        book.setDateRange(new DateRange(Date.valueOf(request.getParameter(Constants.BEGIN_DATE)),
                Date.valueOf(request.getParameter(Constants.END_DATE))));
        Validator.relevanceDateRange(book.getDateRange());
        String id = request.getParameter(Constants.ROOM_ID);
        book.setRoomId(Integer.parseInt(id));
        try {
            book.setTotal(Double.parseDouble(request.getParameter(Constants.TOTAL_PRICE)));
        } catch (NumberFormatException ex) {
            throw new ValidationException();
        }
        return book;
    }

    public static ManagerResponse managerResponse(HttpServletRequest request) throws ValidationException {
        String roomId = request.getParameter(Constants.ROOM_ID);
        String clientId = request.getParameter(Constants.CLIENT_ID);
        String begin = request.getParameter(Constants.BEGIN_DATE);
        String end = request.getParameter(Constants.END_DATE);
        DateRange responseRange = null;
        ManagerResponse managerResponse = null;
        try {
            responseRange = SupportingUtils.getDateRange(begin, end);
            int roomIdd = Integer.parseInt(roomId);
            int clientIdd = Integer.parseInt(clientId);
            managerResponse = new ManagerResponse();
            managerResponse.setRoomId(roomIdd);
            managerResponse.setClientId(clientIdd);
            managerResponse.setDateRange(responseRange);

        } catch (DateValidationException | NumberFormatException ex) {
            throw new ValidationException();
        }
        return managerResponse;
    }

    public static User getUserFromRegisterForm(HttpServletRequest request) throws ValidationException {
        User user = new User();
        String name = request.getParameter(Constants.USER_NAME);
        String surname = request.getParameter(Constants.USER_SURNAME);
        String patronimic = request.getParameter(Constants.USER_PATRONIMIC);
        String mail = request.getParameter(Constants.USER_MAIL);
        String phone = request.getParameter(Constants.USER_PHONE);
        String login = request.getParameter(Constants.USER_LOGIN);
        String password = request.getParameter(Constants.USER_PASSWORD);
        String roleId = request.getParameter(Constants.USER_ROLE_ID);

        user.setName(name);
        user.setSurname(surname);
        user.setPatronimic(patronimic);
        user.setMail(mail);
        user.setLogin(login);
        user.setPassword(password);
        user.setPhone(phone);
        user.setRoleId(Integer.parseInt(roleId));

        Validator.registrationUserValidation(user);

        return user;
    }

}

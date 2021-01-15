package hotel.service.impl;

import hotel.constant.Constants;
import hotel.dao.HotelManagerDao;
import hotel.exception.handler.ApplicationException;
import hotel.exception.handler.ValidationException;
import hotel.factory.ConnectionUtil;
import hotel.model.*;
import hotel.service.HotelManagerService;
import hotel.util.RequestEntityCollector;
import hotel.util.RoomStatusDeterminant;
import hotel.util.SearchUtil;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class HotelManagerServiceImpl implements HotelManagerService {
    private static final Comparator<Room> STATUS_COMPARE = (el1, el2) -> {
        if (el1.getStatus().getStatusId() > el2.getStatus().getStatusId()) {
            return 1;
        }
        return el1.getStatus().getStatusId() < el2.getStatus().getStatusId() ? -1 : 0;
    };

    private final HotelManagerDao hotelManagerDao;
    private final BasicDataSource basicDataSource;

    public HotelManagerServiceImpl(BasicDataSource bds, HotelManagerDao hmd) {
        hotelManagerDao = hmd;
        basicDataSource = bds;
    }

    @Override
    public List<Room> getFreeRooms() throws ApplicationException {
        List<Room> roomList = getAllRooms("");
        return SearchUtil.getFreeRoom(roomList);
    }

    @Override
    public List<Room> getAllRooms(String sort) throws ApplicationException {
        List<Room> rooms;
        List<Book> books;
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            rooms = hotelManagerDao.getAllRooms(sort);
            books = getBookList();
            mergeRoomBook(rooms, books);
            RoomStatusDeterminant.setStatusOnCurrentDate(rooms);
            if (Objects.nonNull(sort) && sort.equals("status_sort")) {
                rooms.sort(STATUS_COMPARE);
            }
        } catch (SQLException ex) {
            throw new ApplicationException(ex);
        } finally {
            try {
                ConnectionUtil.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionUtil.removeConnection();
        }
        return rooms;
    }

    @Override
    public List<Book> getBookList() throws ApplicationException {
        List<Book> books = null;

        try {
            books = hotelManagerDao.getBooks();
        } catch (ApplicationException e) {
            e.printStackTrace();
            throw new ApplicationException(e);
        } finally {
            try {
                ConnectionUtil.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationException(e);
            }
        }
        return books;
    }

    private void mergeRoomBook(List<Room> roomList, List<Book> bookList) {
        if (bookList != null && roomList != null) {
            for (Room room : roomList) {
                for (Book book : bookList) {
                    if (room.getId() == book.getRoomId()) {
                        room.getBooks().add(book);
                    }
                }
            }
        }
    }

    @Override
    public void addRoom(Room room) throws ApplicationException {
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            hotelManagerDao.addRoom(room);
        } catch (SQLException e) {
            throw new ApplicationException(e);
        } finally {
            try {
                ConnectionUtil.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionUtil.removeConnection();
        }
    }

    @Override
    public Room getRoomById(int id) throws ApplicationException {
        Room room = null;
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            room = hotelManagerDao.getRoomById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionUtil.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionUtil.removeConnection();
        }
        return room;
    }

    @Override
    public boolean editRoom(Room room) throws ApplicationException {
        boolean edited = false;
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            edited = hotelManagerDao.editRoom(room);
        } catch (SQLException e) {
            // pack this exception
            e.printStackTrace();
        } finally {
            try {
                ConnectionUtil.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionUtil.removeConnection();
        }
        return edited;
    }

    @Override
    public boolean deleteRoom(int roomId) throws ApplicationException {
        boolean deleted = false;
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            deleted = hotelManagerDao.deleteRoom(roomId);
        } catch (SQLException e) {
            throw new ApplicationException();
        } finally {
            try {
                ConnectionUtil.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionUtil.removeConnection();
        }
        return deleted;
    }

    @Override
    public int getQuantityOfUnViewedRequests() throws ApplicationException {
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            return hotelManagerDao.getQuantityOfUnviwedRequests();
        } catch (SQLException e) {
            throw new ApplicationException(e);
        } finally {
            try {
                ConnectionUtil.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionUtil.removeConnection();
        }

    }

    @Override
    public List<ClientRequest> getClientRequests() throws ApplicationException {
        List<ClientRequest> clientRequestList = null;
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            clientRequestList = hotelManagerDao.getAllClientRequests();
        } catch (SQLException e) {
            throw new ApplicationException();
        } finally {
            try {
                ConnectionUtil.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionUtil.removeConnection();
        }
        return clientRequestList;
    }

    @Override
    public ClientRequest getClientRequestById(String id) throws ApplicationException {
        ClientRequest clientRequest = null;
        try {
            int idd = Integer.parseInt(id);
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            clientRequest = hotelManagerDao.getClientRequestById(idd);
        } catch (SQLException | NumberFormatException e) {
            throw new ApplicationException();
        } finally {
            try {
                ConnectionUtil.getConnection().close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
            ConnectionUtil.removeConnection();
        }
        return clientRequest;
    }

    @Override
    public void offerRoom(HttpServletRequest request) throws ApplicationException, ValidationException {
        ManagerResponse managerResponse = null;
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            String clientId = request.getParameter(Constants.CLIENT_REQUEST_ID);
            managerResponse = RequestEntityCollector.managerResponse(request);
            if (managerResponse != null) {
                hotelManagerDao.offerRoom(Integer.parseInt(clientId), managerResponse);
            } else {
                throw new ApplicationException();
            }
        } catch (ValidationException | ApplicationException | SQLException | NumberFormatException e) {
            if (e instanceof ValidationException) {
                throw new ValidationException();
            } else if (e instanceof SQLException) {
                throw new ApplicationException();
            } else if (e instanceof NumberFormatException) {
                throw new ApplicationException();
            } else {
                throw new ApplicationException();
            }
        } finally {
            try {
                ConnectionUtil.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionUtil.removeConnection();

        }

    }

    @Override
    public List<User> getWaitStatusUserList() throws ApplicationException {
        List<User> pendedUsers = null;
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            pendedUsers = hotelManagerDao.getUsersByActiveStatus(Constants.WAITING_USER);
        } catch (SQLException | ApplicationException throwable) {
            throw new ApplicationException(throwable);
        } finally {
            try {
                ConnectionUtil.getConnection().close();
            } catch (SQLException throwables) {
                throw new ApplicationException(throwables);
            }
        }
        return pendedUsers;
    }

    @Override
    public int GetWaitingUsersCount() throws ApplicationException {
        int count = 0;
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            count = hotelManagerDao.getUsersCountByActiveStatus(Constants.WAITING_USER);
        } catch (SQLException throwables) {
            throw new ApplicationException(throwables);
        } finally {
            try {
                ConnectionUtil.getConnection().close();
            } catch (SQLException throwables) {
                throw new ApplicationException(throwables);
            }
        }
        return count;
    }

    @Override
    public void disableUser(int userId) throws ApplicationException {
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            hotelManagerDao.changeUserActiveStatus(userId, Constants.DISABLED_USER);
        } catch (SQLException throwables) {
            throw new ApplicationException(throwables);
        } finally {
            try {
                ConnectionUtil.getConnection().close();
                ConnectionUtil.removeConnection();
            } catch (SQLException throwables) {
                throw new ApplicationException(throwables);
            }
        }
    }

    @Override
    public void enableUser(int userId) throws ApplicationException {
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            hotelManagerDao.changeUserActiveStatus(userId, Constants.ENABLED_USER);
        } catch (SQLException throwables) {
            throw new ApplicationException(throwables);
        } finally {
            try {
                ConnectionUtil.getConnection().close();
                ConnectionUtil.removeConnection();
            } catch (SQLException throwables) {
                throw new ApplicationException(throwables);
            }
        }
    }
}

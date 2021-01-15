package hotel.service.impl;

import hotel.constant.Constants;
import hotel.dao.CommonHotelDao;
import hotel.dao.HotelManagerDao;
import hotel.exception.handler.ApplicationException;
import hotel.exception.handler.AuthenticationException;
import hotel.exception.handler.ValidationException;
import hotel.factory.ConnectionUtil;
import hotel.model.Room;
import hotel.model.User;
import hotel.service.CommonService;
import hotel.util.DateRange;
import hotel.util.RequestEntityCollector;
import hotel.util.SearchUtil;
import hotel.util.WebUtils;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommonServiceImpl implements CommonService {
    private final BasicDataSource basicDataSource;
    private final CommonHotelDao commonHotelDao;
    private final HotelManagerDao hotelManagerDao;

    public CommonServiceImpl(BasicDataSource bds, CommonHotelDao chd, HotelManagerDao hotelManagerDao) {
        basicDataSource = bds;
        commonHotelDao = chd;
        this.hotelManagerDao = hotelManagerDao;
    }

    public User login(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, ApplicationException {
        String login = request.getParameter(Constants.LOGIN_PARAMETER);
        String password = request.getParameter(Constants.PASSWORD_PARAMETER);
        String remember = request.getParameter(Constants.REMEMBER);
        User user;

        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            user = commonHotelDao.getUserByLogin(login);
            if (Objects.isNull(user)) {
                throw new AuthenticationException("The user does not exist!");
            }
                if (!(user.getLogin().equals(login) && user.getPassword().equals(password))) {
                    throw new AuthenticationException();
                }
                if (!user.getActiveStatus().equals(Constants.ENABLED_USER)) {
                    throw new AuthenticationException();
                }
                if (Objects.nonNull(remember)) {
                    String identifier = request.getSession().getId();
                    addIdentifier(user.getId(), identifier);
                    WebUtils.setCookie(response, "authentification", identifier, 60);
                }

        } catch (SQLException | ApplicationException | AuthenticationException ex) {
            if (ex instanceof AuthenticationException) {
                throw new AuthenticationException();
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
        return user;
    }

    @Override
    public List<Room> getFreeRoomOnCurrentMoment() throws ApplicationException {
        List<Room> roomList;
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            roomList = hotelManagerDao.getAllRooms("sort");
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
        if (roomList.size() == 0) {
            return null;
        }
        return SearchUtil.getFreeRoom(roomList);
    }

    @Override
    public List<Room> getFreeRoomByRange(HttpServletRequest request, List<Room> rooms) throws ValidationException {
        List<Room> freeRoomList = null;
        try {
            Date begin = Date.valueOf(request.getParameter(Constants.BEGIN_DATE));

            if (begin.compareTo(Date.valueOf(LocalDate.now())) == -1) {
                throw new ValidationException();
            }

            Date end = Date.valueOf(request.getParameter(Constants.END_DATE));
            DateRange dateRange = new DateRange(begin, end);
            freeRoomList = SearchUtil.findAllFreeRoomsByRange(rooms, dateRange);
        } catch (IllegalArgumentException ex) {
            throw new ValidationException(Constants.WRONG_DATE_RANGE, ex);
        }

        return freeRoomList;
    }

    @Override
    public Room getRoomById(HttpServletRequest req) throws ApplicationException {
        String roomId = req.getParameter(Constants.ROOM_ID);
        Room room = null;
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            room = hotelManagerDao.getRoomById(Integer.parseInt(roomId));
            if (room == null) {
                throw new ApplicationException();
            }
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

        return room;
    }

    @Override
    public void removeAllStitchedBooks() throws ApplicationException {

        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            commonHotelDao.deleteOverdueBooks();
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

    }

    @Override
    public void removeOverdueRequests() throws ApplicationException {
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            commonHotelDao.deleteOverdueRequests();
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

    }

    @Override
    public void removeOverdueResponses() throws ApplicationException {
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            commonHotelDao.deleteOverdueResponses();
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
    }

    @Override
    public User register(HttpServletRequest request) throws ApplicationException, ValidationException {
        User user = null;
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            user = RequestEntityCollector.getUserFromRegisterForm(request);
            commonHotelDao.addUser(user);
        } catch (SQLException e) {
            throw new ApplicationException();
        } catch (ValidationException ex) {
            throw new ValidationException();
        }
        return user;
    }

    @Override
    public void addIdentifier(int userId, String identifier) throws ApplicationException {
        commonHotelDao.addIdentifier(userId, identifier);
    }

    @Override
    public User checkUserInBaseByIdentifier(String identifier) throws ApplicationException {
        User user = null;
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            user = commonHotelDao.getUserByAccessKey(identifier);
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
        return user;
    }

    @Override
    public void removeIdentifier(User user) throws ApplicationException {

        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            commonHotelDao.throwOffIdentifier(user.getId());
        } catch (SQLException e) {
            throw new ApplicationException();
        }
    }

    @Override
    public void registerManager(User user) throws ApplicationException {
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            user.setActiveStatus(Constants.WAITING_USER);
            commonHotelDao.addUser(user);
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

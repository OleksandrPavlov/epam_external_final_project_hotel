package hotel.service.impl;

import hotel.constant.Constants;
import hotel.dao.HotelClientDao;
import hotel.exception.handler.ApplicationException;
import hotel.factory.ConnectionUtil;
import hotel.model.*;
import hotel.service.HotelClientService;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class HotelClientServiceImpl implements HotelClientService {
    private final BasicDataSource basicDataSource;
    private final HotelClientDao hotelClientDao;

    public HotelClientServiceImpl(BasicDataSource basicDataSource, HotelClientDao hotelClientDao) {

        this.basicDataSource = basicDataSource;
        this.hotelClientDao = hotelClientDao;
    }

    @Override
    public boolean sendRequest(ClientRequest clientRequest) throws ApplicationException {
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            return hotelClientDao.addClientRequest(clientRequest);
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
    public List<Room> getClientBooks(Integer id) throws ApplicationException {
        if (id == null) {
            throw new ApplicationException();
        }
        List<Room> roomsList = null;
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            roomsList = hotelClientDao.getRoomsByClientId(id);
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
        return roomsList;
    }

    @Override
    public int toBook(Book book) throws ApplicationException {
        int suc = 0;
        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            suc = hotelClientDao.addNewBook(book);
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
        return suc;
    }

    @Override
    public List<ManagerResponse> getOffersByClientId(HttpServletRequest request) throws ApplicationException {
        List<ManagerResponse> mrl = null;
        try {
            User e = (User) request.getSession().getAttribute(Constants.CURRENT_USER_SESSION_ATTRIBUTE);
            int id = e.getId();
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            mrl = hotelClientDao.getOffersByClientId(id);

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
        return mrl;
    }

    @Override
    public int countOffers(HttpServletRequest request) throws ApplicationException {
        User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER_SESSION_ATTRIBUTE);

        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            if (user != null) {
                return hotelClientDao.getNumberOfOffersByClientId(user.getId());
            }
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
        return 0;
    }

    @Override
    public void deleteOffer(HttpServletRequest request) throws ApplicationException {
        String roomId = request.getParameter(Constants.ROOM_ID);
        String beginDate = request.getParameter(Constants.BEGIN_DATE);
        try {
            if (Objects.nonNull(roomId) && Objects.nonNull(beginDate)) {
                ConnectionUtil.setConnection(basicDataSource.getConnection());
                hotelClientDao.deleteOfferByRoomIdBeginDate(Integer.parseInt(roomId), beginDate);
            }
        } catch (NumberFormatException | SQLException ex) {
            throw new ApplicationException();
        } finally {
            if (Objects.nonNull(ConnectionUtil.getConnection())) {
                try {
                    ConnectionUtil.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ConnectionUtil.removeConnection();
            }
        }

    }

    @Override
    public void payForRoom(HttpServletRequest request) throws ApplicationException {
        String roomId = request.getParameter(Constants.ROOM_ID);
        String beginDate = request.getParameter(Constants.BEGIN_DATE);

        try {
            ConnectionUtil.setConnection(basicDataSource.getConnection());
            hotelClientDao.updateBookToPaid(Integer.parseInt(roomId), beginDate);
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
    }

    @Override
    public void rejectOffer(HttpServletRequest request) throws ApplicationException {
        deleteOffer(request);
    }

    @Override
    public int getCountOfBooks(Room room) throws ApplicationException {
        return hotelClientDao.getCountOfBooks(room);
    }

}

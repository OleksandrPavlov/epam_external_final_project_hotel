package hotel.service;

import hotel.constant.Constants;
import hotel.dao.CommonHotelDao;
import hotel.dao.HotelClientDao;
import hotel.dao.HotelManagerDao;
import hotel.dao.impl.CommonHotelDaoImpl;
import hotel.dao.impl.HotelClientDaoImpl;
import hotel.dao.impl.HotelManagerDaoImpl;
import hotel.service.impl.CommonServiceImpl;
import hotel.service.impl.HotelClientServiceImpl;
import hotel.service.impl.HotelManagerServiceImpl;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import java.sql.SQLException;
import java.util.Objects;

public class ServiceManager {
    private final BasicDataSource basicDataSource;
    private final CommonHotelDao commonHotelDao;
    private final HotelManagerDao hotelManagerDao;
    private final HotelClientDao hotelClientDao;
    private final CommonService commonService;
    private final HotelManagerService hotelManagerService;
    private final HotelClientService hotelClientService;

    private ServiceManager(BasicDataSource dataSource) {
        this.basicDataSource = dataSource;
        commonHotelDao = new CommonHotelDaoImpl();
        hotelManagerDao = new HotelManagerDaoImpl();
        hotelClientDao = new HotelClientDaoImpl();
        commonService = new CommonServiceImpl(basicDataSource, commonHotelDao, hotelManagerDao);
        hotelManagerService = new HotelManagerServiceImpl(basicDataSource, hotelManagerDao);
        hotelClientService = new HotelClientServiceImpl(basicDataSource, hotelClientDao);
    }

    public CommonService getCommonService() {
        return commonService;
    }

    public HotelManagerService getHotelManagerService() {
        return hotelManagerService;
    }

    public HotelClientService getHotelClientService() {
        return hotelClientService;
    }

    public static synchronized ServiceManager getInstance(ServletContext sc, BasicDataSource dataSource) {
        if (Objects.isNull(sc.getAttribute(Constants.SERVICE_MANAGER))) {
            sc.setAttribute(Constants.SERVICE_MANAGER, new ServiceManager(dataSource));
        }
        return (ServiceManager) sc.getAttribute(Constants.SERVICE_MANAGER);
    }

    public void poolShutDown() {
        try {
            basicDataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

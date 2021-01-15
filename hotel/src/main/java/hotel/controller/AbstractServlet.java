package hotel.controller;

import hotel.constant.Constants;
import hotel.service.CommonService;
import hotel.service.HotelClientService;
import hotel.service.HotelManagerService;
import hotel.service.ServiceManager;
import hotel.util.DateRange;
import hotel.util.SearchUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AbstractServlet extends HttpServlet {

    private static final long serialVersionUID = -3897558471189786253L;
    private static final String ATRIBUTE_NAME = "innerPage";

    ServiceManager serviceManager;

    public void forwardToPage(HttpServletRequest req, HttpServletResponse resp, String framePath, String innerPath)
            throws ServletException, IOException {
        if (innerPath != null) {
            req.setAttribute(ATRIBUTE_NAME, innerPath);
        }
        req.getRequestDispatcher(framePath).forward(req, resp);
    }

    @Override
    public void init() {
        serviceManager = (ServiceManager) getServletContext().getAttribute(Constants.SERVICE_MANAGER);
    }

    protected CommonService getCommonService() {
        return serviceManager.getCommonService();
    }

    protected HotelManagerService getHotelManagerService() {
        return serviceManager.getHotelManagerService();
    }

    protected HotelClientService getHotelClientService() {
        return serviceManager.getHotelClientService();
    }

    protected void notificationIncrement(HttpServletRequest request) {

        String successNote = (String) request.getSession().getAttribute(Constants.SUCCESS);
        if (successNote != null) {
            if (Constants.BOOK_SUCCESS.equals(successNote)) {
                request.setAttribute(Constants.SUCCESS, Constants.BOOK_SUCCESS);
            }
            if (Constants.REQUEST_SUCCESSFULLY_SENDED.equals(successNote)) {
                request.setAttribute(Constants.SUCCESS, Constants.REQUEST_SUCCESSFULLY_SENDED);
            }
            if (Constants.OFFER_SUCCESS.equals(successNote)) {
                request.setAttribute(Constants.SUCCESS, Constants.OFFER_SUCCESS);
            }
            if (Constants.PAY_SUCCESS.equals(successNote)) {
                request.setAttribute(Constants.SUCCESS, Constants.PAY_SUCCESS);
            }
            if (Constants.REGISTER_SUCCESS.equals(successNote)) {
                request.setAttribute(Constants.SUCCESS, Constants.REGISTER_SUCCESS);
            }
            if (Constants.REJECT_OFFER_SUCCESS.equals(successNote)) {
                request.setAttribute(Constants.SUCCESS, Constants.REJECT_OFFER_SUCCESS);
            }
            if (Constants.ADD_ROOM_SUCCESS.equals(successNote)) {
                request.setAttribute(Constants.SUCCESS, Constants.ADD_ROOM_SUCCESS);
            }
            if (Constants.MANAGER_REGISTRATION_SUCCESS.equals(successNote)) {
                request.setAttribute(Constants.SUCCESS, Constants.MANAGER_REGISTRATION_SUCCESS);
            }
            if (Constants.CLIENT_REGISTRATION_SUCCESS.equals(successNote)) {
                request.setAttribute(Constants.SUCCESS, Constants.CLIENT_REGISTRATION_SUCCESS);
            }
        }

        String failNote = (String) request.getSession().getAttribute(Constants.FAIL);
        if (failNote != null) {
            if (Constants.BOOK_FAIL.equals(failNote)) {
                request.setAttribute(Constants.FAIL, Constants.BOOK_FAIL);
            }
            if (Constants.REQ_VALIDATION_FAIL.equals(failNote)) {
                request.setAttribute(Constants.FAIL, Constants.REQ_VALIDATION_FAIL);
            }
            if (Constants.OFFER_FAIL.equals(failNote)) {
                request.setAttribute(Constants.FAIL, Constants.OFFER_FAIL);
            }
            if (Constants.BOOKING_ACCESS_FAIL.equals(failNote)) {
                request.setAttribute(Constants.FAIL, Constants.BOOKING_ACCESS_FAIL);
            }
            if (Constants.REGISTER_FAIL.equals(failNote)) {
                request.setAttribute(Constants.FAIL, Constants.REGISTER_FAIL);
            }
            if (Constants.AUTHENTIFICATION_FAIL.equals(failNote)) {
                request.setAttribute(Constants.FAIL, Constants.AUTHENTIFICATION_FAIL);
            }
            if (Constants.ADD_ROOM_FAIL.equals(failNote)) {
                request.setAttribute(Constants.FAIL, Constants.ADD_ROOM_FAIL);
            }
            if (Constants.ADD_ROOM_VALIDATION_FAIL.equals(failNote)) {
                request.setAttribute(Constants.FAIL, Constants.ADD_ROOM_VALIDATION_FAIL);
            }

        }

        String emptyNote = (String) request.getSession().getAttribute(Constants.EMPTY);

        if (emptyNote != null) {
            if (Constants.EMPTY_NO_ONE_ROOM.equals(emptyNote)) {
                request.setAttribute(Constants.EMPTY, Constants.EMPTY_NO_ONE_ROOM);
            }

        }
        request.getSession().removeAttribute(Constants.EMPTY);
        request.getSession().removeAttribute(Constants.FAIL);
        request.getSession().removeAttribute(Constants.SUCCESS);
    }

    protected void setCurrentDateRestriction(HttpServletRequest request) {
        DateRange restrictRange = SearchUtil.getCurrentDateRestriction();
        request.setAttribute(Constants.DATE_RESTRICT_MIN_ATTR, restrictRange.getBegin());
        request.setAttribute(Constants.DATE_RESTRICT_MAX_ATTR, restrictRange.getEnd());
    }


}

package hotel.controller.manager;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;
import hotel.exception.handler.DateValidationException;
import hotel.exception.handler.ValidationException;
import hotel.model.Room;
import hotel.util.DateRange;
import hotel.util.SearchUtil;
import hotel.util.SupportingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/manager/result")
public class ResponseFinder extends AbstractServlet {
    private static final long serialVersionUID = -2856576084714152066L;
    private static final String RESULT_PAGE = "/WEB-INF/view/manager/result.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCurrentDateRestriction(req);
        List<Room> allRooms = null;
        List<Room> freeRooms = null;
        String begin = req.getParameter(Constants.BEGIN_DATE);
        String end = req.getParameter(Constants.END_DATE);
        try {
            DateRange dateRange = SupportingUtils.getDateRange(begin, end);
            allRooms = getHotelManagerService().getAllRooms("");
            freeRooms = SearchUtil.findAllFreeRoomsByRange(allRooms, dateRange);
        } catch (ApplicationException | DateValidationException | ValidationException e) {
            e.printStackTrace();
        }
        req.setAttribute(Constants.CURRENT_ROOMS_REQUEST_ATTRIBUTE, freeRooms);
        req.setAttribute(Constants.END_DATE, end);
        req.setAttribute(Constants.BEGIN_DATE, begin);
        forwardToPage(req, resp, PathConstants.PATH_TO_MANAGER_WRAPPER_PAGE, RESULT_PAGE);
    }

}

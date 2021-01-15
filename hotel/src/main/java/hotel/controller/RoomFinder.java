package hotel.controller;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.exception.handler.ApplicationException;
import hotel.exception.handler.ValidationException;
import hotel.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home/find")
public class RoomFinder extends AbstractServlet {
    private static final String PATH_TO_FIND_RESULT_PAGE = "/WEB-INF/view/main/find_result.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCurrentDateRestriction(req);
        List<Room> freeRooms = null;
        List<Room> allRooms = null;
        try {
            allRooms = getHotelManagerService().getAllRooms("");
            freeRooms = getCommonService().getFreeRoomByRange(req, allRooms);
        } catch (ValidationException e) {
            req.setAttribute(Constants.VALIDATION_EXCEPTION, "Пожалуйста выберите корректный диапазон");
            forwardToPage(req, resp, PathConstants.HOME_SERVLET, null);
            return;
        } catch (ApplicationException e) {
            resp.sendRedirect(req.getContextPath() + PathConstants.EXCEPTION_SERVLET);
        }
        req.setAttribute(Constants.BEGIN_DATE, req.getParameter(Constants.BEGIN_DATE));
        req.setAttribute(Constants.END_DATE, req.getParameter(Constants.END_DATE));
        req.setAttribute(Constants.CURRENT_ROOMS_REQUEST_ATTRIBUTE, freeRooms);
        forwardToPage(req, resp, PathConstants.PATH_TO_MAIN_WRAPPER_PAGE, PATH_TO_FIND_RESULT_PAGE);
    }

}

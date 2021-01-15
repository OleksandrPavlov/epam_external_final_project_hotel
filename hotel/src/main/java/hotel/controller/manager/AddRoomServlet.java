package hotel.controller.manager;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;
import hotel.exception.handler.ValidationException;
import hotel.util.RequestEntityCollector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manager/home/addroom")
public class AddRoomServlet extends AbstractServlet {

    /**
     *
     */
    private static final long serialVersionUID = 7961473976201077196L;
    private static final String PATH_TO_ADDING_ROOM_PAGE = "/WEB-INF/view/manager/add_room.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        notificationIncrement(req);
        forwardToPage(req, resp, PathConstants.PATH_TO_MANAGER_WRAPPER_PAGE, PATH_TO_ADDING_ROOM_PAGE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getHotelManagerService().addRoom(RequestEntityCollector.getRoom(req));
        } catch (ValidationException | ApplicationException e) {
            if (e instanceof ApplicationException) {
                req.getSession().setAttribute(Constants.FAIL, Constants.ADD_ROOM_FAIL);
                resp.sendRedirect(req.getContextPath() + PathConstants.ADD_ROOM_SERVLET);
                return;
            }
            req.getSession().setAttribute(Constants.FAIL, Constants.ADD_ROOM_VALIDATION_FAIL);
            resp.sendRedirect(req.getContextPath() + PathConstants.ADD_ROOM_SERVLET);
            return;
        }

        req.getSession().setAttribute(Constants.SUCCESS, Constants.ADD_ROOM_SUCCESS);
        resp.sendRedirect(req.getContextPath() + PathConstants.MANAGER_HOME_SERVLET);
    }

}

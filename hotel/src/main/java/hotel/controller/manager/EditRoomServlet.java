package hotel.controller.manager;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;
import hotel.exception.handler.ValidationException;
import hotel.model.Room;
import hotel.util.RequestEntityCollector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manager/home/editroom")
public class EditRoomServlet extends AbstractServlet {

    /**
     *
     */
    private static final long serialVersionUID = -3321623881703681429L;
    private static final String PATH_TO_EDIT_PAGE = "/WEB-INF/view/manager/edit_page.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Room room = null;
        try {
            room = getHotelManagerService().getRoomById(Integer.parseInt(req.getParameter("id")));
        } catch (NumberFormatException | ApplicationException e) {
            e.printStackTrace();
        }
        if (room != null) {
            req.setAttribute(Constants.ROOM, room);
            req.setAttribute(Constants.ROOM_ID, req.getParameter(Constants.ROOM_ID));
            forwardToPage(req, resp, PathConstants.PATH_TO_MANAGER_WRAPPER_PAGE, PATH_TO_EDIT_PAGE);
        } else {
            resp.sendRedirect(req.getContextPath() + "/manager/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Room room = null;
        try {
            room = RequestEntityCollector.getRoom(req);
            if (getHotelManagerService().editRoom(room)) {
                //req.setAttribute(Constants.SUCCESS_EDIT_ATTRIBUTE, Constants.EDIT_SUCCESS_MESSEGE);
            } else {
                //req.setAttribute(Constants.FAIL_EDIT_ATTRIBUTE, Constants.EDIT_FAIL_MESSEGE);
            }
        } catch (ValidationException | ApplicationException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + PathConstants.MANAGER_EDIT_SERVLET);
    }

}

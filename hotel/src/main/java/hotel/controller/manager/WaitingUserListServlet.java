package hotel.controller.manager;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;
import hotel.model.User;
import hotel.service.HotelManagerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = PathConstants.MANAGER_WAIT_LIST_SERVLET)
public class WaitingUserListServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HotelManagerService hotelManagerService = getHotelManagerService();
        List<User> pendedUserList = null;
        try {
            pendedUserList = hotelManagerService.getWaitStatusUserList();
        } catch (ApplicationException e) {
            throw new ServletException(e);
        }
        req.setAttribute(Constants.waiting_users, pendedUserList);
        forwardToPage(req, resp, PathConstants.PATH_TO_MANAGER_WRAPPER_PAGE, PathConstants.PENDED_MANAGER_LIST_PAGE);
    }
}

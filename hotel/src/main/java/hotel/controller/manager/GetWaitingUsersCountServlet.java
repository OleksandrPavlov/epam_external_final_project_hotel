package hotel.controller.manager;

import hotel.constant.Constants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/manager/pend-man-count")
public class GetWaitingUsersCountServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int count = getHotelManagerService().GetWaitingUsersCount();
            req.setAttribute(Constants.PEN_MAN_COUNT, count);
        } catch (ApplicationException e) {

        }
    }
}

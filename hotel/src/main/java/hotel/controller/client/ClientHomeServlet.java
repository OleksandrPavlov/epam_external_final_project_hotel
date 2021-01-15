package hotel.controller.client;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;
import hotel.model.Room;
import hotel.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/client/home")
public class ClientHomeServlet extends AbstractServlet {
    /**
     *
     */
    private static final long serialVersionUID = -4217609594233218430L;
    private static final String PATH_TO_CLIENT_ROOM_PAGE = "/WEB-INF/view/client/client_home.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Room> clientBookedRooms = null;
        User currentUser = (User) req.getSession().getAttribute(Constants.CURRENT_USER_SESSION_ATTRIBUTE);
        Integer clientId = null;
        int offerCount;
        if (currentUser != null) {
            clientId = currentUser.getId();
        }

        try {
            clientBookedRooms = getHotelClientService().getClientBooks(clientId);
            offerCount = getHotelClientService().countOffers(req);
        } catch (ApplicationException e) {
            resp.sendRedirect(req.getContextPath() + PathConstants.EXCEPTION_SERVLET);
            return;
        }

        notificationIncrement(req);
        req.setAttribute(Constants.OFFER_COUNT, offerCount);
        req.setAttribute(Constants.CURRENT_BOOKS, clientBookedRooms);
        forwardToPage(req, resp, PathConstants.PATH_TO_MAIN_WRAPPER_PAGE, PATH_TO_CLIENT_ROOM_PAGE);
    }


}

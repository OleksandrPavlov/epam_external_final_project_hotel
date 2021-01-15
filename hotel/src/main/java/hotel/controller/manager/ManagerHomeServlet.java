package hotel.controller.manager;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;
import hotel.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/manager/home")
public class ManagerHomeServlet extends AbstractServlet {
    private static final String PATH_TO_MANAGER_HOME_PAGE = "/WEB-INF/view/manager/home.jsp";
    private static final long serialVersionUID = 1807155373449593131L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        notificationIncrement(req);
        List<Room> rooms = null;
        String sort = req.getParameter(Constants.SORT_ATTRIBUTE);
        int quantityUnviwedRequest = 0;
        try {
            rooms = getHotelManagerService().getAllRooms(sort);
            quantityUnviwedRequest = getHotelManagerService().getQuantityOfUnViewedRequests();
        } catch (ApplicationException e) {
            resp.sendRedirect(req.getContextPath() + PathConstants.EXCEPTION_SERVLET);
        }
        if (Objects.nonNull(rooms)) {
            if (sort != null) {
                req.setAttribute(Constants.SORT_ATTRIBUTE, sort);

            }
            req.getRequestDispatcher("/manager/pend-man-count").include(req, resp);
            req.setAttribute(Constants.CURRENT_ROOMS_REQUEST_ATTRIBUTE, rooms);
            req.setAttribute(Constants.COUNT_ANV_REQUESTS, quantityUnviwedRequest);
            forwardToPage(req, resp, PathConstants.PATH_TO_MANAGER_WRAPPER_PAGE, PATH_TO_MANAGER_HOME_PAGE);
        }
    }


}

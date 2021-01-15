package hotel.controller;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.exception.handler.ApplicationException;
import hotel.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@WebServlet("/home")
public class HomeServlet extends AbstractServlet {
    private static final long serialVersionUID = 6208434391413940728L;
    private static final String PATH_TO_HOME_PAGE = "/WEB-INF/view/main/home.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCurrentDateRestriction(req);
        List<Room> freeRooms = null;
        try {
            freeRooms = getHotelManagerService().getFreeRooms();
        } catch (ApplicationException e) {
            resp.sendRedirect(req.getContextPath() + PathConstants.EXCEPTION_SERVLET);
        }

        if (Objects.nonNull(freeRooms)) {

            req.setAttribute(Constants.CURRENT_ROOMS_REQUEST_ATTRIBUTE, freeRooms);
            req.setAttribute(Constants.CURRENT_DATE, Date.valueOf(LocalDate.now()));
            forwardToPage(req, resp, PathConstants.PATH_TO_MAIN_WRAPPER_PAGE, PATH_TO_HOME_PAGE);
        }

    }


}

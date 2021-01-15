package hotel.controller.manager;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manager/home/deleteroom")
public class DeleteRoomServlet extends AbstractServlet {

    /**
     *
     */
    private static final long serialVersionUID = -2713072017686818543L;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int roomId = Integer.parseInt(req.getParameter(Constants.ROOM_ID)); /// ADD
        boolean deleted = false;
        try {
            deleted = getHotelManagerService().deleteRoom(roomId);
        } catch (ApplicationException e) {
            // Here add notification
            e.printStackTrace();
        } /// CHECKING
        /// NFE
        resp.sendRedirect(req.getContextPath() + PathConstants.MANAGER_HOME_SERVLET);

    }

}

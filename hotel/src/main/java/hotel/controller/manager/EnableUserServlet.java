package hotel.controller.manager;

import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/manager/pended/approve")
public class EnableUserServlet extends AbstractServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getHotelManagerService().enableUser(Integer.parseInt(req.getParameter("userId")));
        } catch (ApplicationException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + PathConstants.MANAGER_WAIT_LIST_SERVLET);
    }
}

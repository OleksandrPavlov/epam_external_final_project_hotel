package hotel.controller;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/distributor")
public class RoleDistributor extends AbstractServlet {

    private static final long serialVersionUID = -7750621777171778261L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Constants.CURRENT_USER_SESSION_ATTRIBUTE);
        switch (user.getRole()) {
            case "manager":
                resp.sendRedirect(req.getContextPath() + PathConstants.MANAGER_HOME_SERVLET);
                break;
            case "client":
                resp.sendRedirect(req.getContextPath() + PathConstants.CLIENT_HOME_SERVLET);
                break;
        }
    }

}

package hotel.controller.registration;

import hotel.constant.Constants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;
import hotel.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogOutServlet extends AbstractServlet {

    private static final long serialVersionUID = -1073146792696988874L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Constants.CURRENT_USER_SESSION_ATTRIBUTE);
        try {
            getCommonService().removeIdentifier(user);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        req.getSession().removeAttribute(Constants.CURRENT_USER_SESSION_ATTRIBUTE);
        resp.sendRedirect(req.getContextPath() + "/home");
    }

}

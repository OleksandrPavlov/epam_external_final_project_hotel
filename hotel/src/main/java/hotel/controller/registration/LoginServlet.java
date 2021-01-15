package hotel.controller.registration;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;
import hotel.exception.handler.AuthenticationException;
import hotel.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/home/login")
public class LoginServlet extends AbstractServlet {
    private static final String PATH_TO_LOGIN_INNER_PAGE = "/WEB-INF/view/login_register/login.jsp";
    private static final long serialVersionUID = 8722810746565388944L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        notificationIncrement(req);
        forwardToPage(req, resp, PathConstants.PATH_TO_MAIN_WRAPPER_PAGE, PATH_TO_LOGIN_INNER_PAGE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        try {
            user = getCommonService().login(req, resp);
        } catch (AuthenticationException | ApplicationException ex) {
            if (ex instanceof AuthenticationException) {
                req.getSession().setAttribute(Constants.FAIL, Constants.AUTHENTIFICATION_FAIL);
                resp.sendRedirect(req.getContextPath() + PathConstants.LOGIN_SERVLET);
                return;
            } else {
                forwardToPage(req, resp, PathConstants.PATH_TO_EXCEPTION_PAGE, null);
            }
        }
        if (Objects.nonNull(user)) {
            req.getSession().setAttribute(Constants.CURRENT_USER_SESSION_ATTRIBUTE, user);
            resp.sendRedirect(req.getContextPath() + PathConstants.DISTRIBUTOR_SERVLET);
        }
    }

}

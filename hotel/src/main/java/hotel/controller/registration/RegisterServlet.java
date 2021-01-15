package hotel.controller.registration;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;
import hotel.exception.handler.ValidationException;
import hotel.model.User;
import hotel.util.RequestEntityCollector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends AbstractServlet {
    /**
     *
     */
    private static final long serialVersionUID = 8984676598444553964L;
    private static final String PATH_TO_REGISTER_PAGE = "/WEB-INF/view/login_register/register.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage(req, resp, PathConstants.PATH_TO_MAIN_WRAPPER_PAGE, PATH_TO_REGISTER_PAGE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            User user = RequestEntityCollector.getUserFromRegisterForm(req);
            if (user.getRoleId() == 1) {
                registerManager(user);
                session.setAttribute(Constants.SUCCESS, Constants.MANAGER_REGISTRATION_SUCCESS);
            } else {
                getCommonService().register(req);
                session.setAttribute(Constants.SUCCESS, Constants.CLIENT_REGISTRATION_SUCCESS);
            }
        } catch (ApplicationException | ValidationException e) {
            session.setAttribute(Constants.FAIL, Constants.REGISTER_FAIL);
            resp.sendRedirect(req.getContextPath() + PathConstants.REGISTER_SERVLET);
            return;
        }
        resp.sendRedirect(req.getContextPath() + PathConstants.LOGIN_SERVLET);
    }

    private void registerManager(User user) throws ApplicationException {
        getCommonService().registerManager(user);
    }

}

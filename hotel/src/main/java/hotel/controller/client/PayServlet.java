package hotel.controller.client;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/client/pay")
public class PayServlet extends AbstractServlet {

    /**
     *
     */
    private static final long serialVersionUID = -4231141494652637255L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getHotelClientService().payForRoom(req);
        } catch (ApplicationException e) {
            req.getSession().setAttribute(Constants.FAIL, Constants.PAY_FAIL);
            resp.sendRedirect(req.getContextPath() + PathConstants.CLIENT_HOME_SERVLET);
            return;
        }
        req.getSession().setAttribute(Constants.SUCCESS, Constants.PAY_SUCCESS);
        resp.sendRedirect(req.getContextPath() + PathConstants.CLIENT_HOME_SERVLET);
    }

}

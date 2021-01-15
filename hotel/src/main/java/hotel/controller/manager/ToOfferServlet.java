package hotel.controller.manager;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;
import hotel.exception.handler.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manager/offer")
public class ToOfferServlet extends AbstractServlet {

    private static final long serialVersionUID = 4029980368840515987L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getHotelManagerService().offerRoom(req);
        } catch (ApplicationException | ValidationException e) {
            req.getSession().setAttribute(Constants.FAIL, Constants.OFFER_FAIL);
            resp.sendRedirect(req.getContextPath() + PathConstants.CLIENT_REQ_LIST_SERVLET);
            return;
        }
        req.getSession().setAttribute(Constants.SUCCESS, Constants.OFFER_SUCCESS);
        resp.sendRedirect(req.getContextPath() + PathConstants.CLIENT_REQ_LIST_SERVLET);
    }
}

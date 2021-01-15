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

@WebServlet("/offer/reject")
public class RejectOfferServlet extends AbstractServlet {

    /**
     *
     */
    private static final long serialVersionUID = 7025012114955077815L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getHotelClientService().rejectOffer(req);
        } catch (ApplicationException e) {
            resp.sendRedirect(req.getContextPath() + PathConstants.EXCEPTION_SERVLET);
            return;
        }
        req.getSession().setAttribute(Constants.SUCCESS, Constants.REJECT_OFFER_SUCCESS);
        resp.sendRedirect(req.getContextPath() + PathConstants.OFFERS_SERVLET);
    }

}

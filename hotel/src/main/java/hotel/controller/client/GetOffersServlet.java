package hotel.controller.client;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;
import hotel.model.ManagerResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/client/home/offers")
public class GetOffersServlet extends AbstractServlet {
    /**
     *
     */
    private static final long serialVersionUID = 5640930011684630813L;
    private static final String OFFERS_PAGE = "/WEB-INF/view/client/offers.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ManagerResponse> mrl = null;
        try {
            mrl = getHotelClientService().getOffersByClientId(req);
        } catch (ApplicationException e) {
            resp.sendRedirect(req.getContextPath() + PathConstants.EXCEPTION_SERVLET);
        }
        req.setAttribute(Constants.OFFERS, mrl);
        forwardToPage(req, resp, PathConstants.PATH_TO_MAIN_WRAPPER_PAGE, OFFERS_PAGE);
    }


}

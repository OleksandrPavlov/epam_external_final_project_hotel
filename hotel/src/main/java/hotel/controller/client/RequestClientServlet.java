package hotel.controller.client;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;
import hotel.exception.handler.ValidationException;
import hotel.model.ClientRequest;
import hotel.util.RequestEntityCollector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/client/home/request")
public class RequestClientServlet extends AbstractServlet {

    private static final long serialVersionUID = 5548356274919759583L;
    private static final String PATH_TO_REQUEST_FORM_PAGE = "/WEB-INF/view/client/client_request.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        notificationIncrement(req);
        setCurrentDateRestriction(req);
        forwardToPage(req, resp, PathConstants.PATH_TO_MAIN_WRAPPER_PAGE, PATH_TO_REQUEST_FORM_PAGE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean success = false;
        try {
            ClientRequest clientRequest = RequestEntityCollector.getClientRequest(req);
            success = getHotelClientService().sendRequest(clientRequest);
        } catch (ValidationException | ApplicationException e) {
            req.getSession().setAttribute(Constants.FAIL, Constants.REQ_VALIDATION_FAIL);
            resp.sendRedirect(req.getContextPath() + PathConstants.REQUEST_CLIENT_SERVLET);
            return;
        }
        if (success) {
            req.getSession().setAttribute(Constants.SUCCESS, Constants.REQUEST_SUCCESSFULLY_SENDED);
            resp.sendRedirect(req.getContextPath() + PathConstants.CLIENT_HOME_SERVLET);
        } else {

        }
    }

}

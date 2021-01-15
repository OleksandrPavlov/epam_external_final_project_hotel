package hotel.controller.manager;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;
import hotel.model.ClientRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/manager/home/requestList/processing")
public class ApplicationProcessing extends AbstractServlet {
    private static final String PROCESSING_PAGE = "/WEB-INF/view/manager/process.jsp";
    private static final long serialVersionUID = 1910376029432427552L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCurrentDateRestriction(req);

        Object clientId = req.getSession().getAttribute(Constants.CLIENT_REQUEST_ID);
        String clientRequestId = req.getParameter(Constants.CLIENT_REQUEST_ID);
        ClientRequest clientRequest = null;
        if (Objects.isNull(clientId)) {
            req.getSession().setAttribute(Constants.CLIENT_REQUEST_ID, clientRequestId);
        }
        if (Objects.isNull(clientRequestId)) {
            clientRequestId = (String) req.getSession().getAttribute(Constants.CLIENT_REQUEST_ID);
        }

        try {
            clientRequest = getHotelManagerService().getClientRequestById(clientRequestId);
            if (clientRequest == null) {
                req.setAttribute(Constants.FAIL, Constants.OBJECT_DOES_NOT_EXIST);
                resp.sendRedirect(req.getContextPath() + PathConstants.CLIENT_REQ_LIST_SERVLET);
                return;
            }
            req.getSession().setAttribute(Constants.CURRENT_CLIENT_REQUEST_ATTRIBUTE, clientRequest);
        } catch (ApplicationException e) {

        }
        forwardToPage(req, resp, PathConstants.PATH_TO_MANAGER_WRAPPER_PAGE, PROCESSING_PAGE);
    }

}

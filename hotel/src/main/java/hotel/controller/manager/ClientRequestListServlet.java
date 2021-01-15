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
import java.util.List;

@WebServlet("/manager/home/requestList")
public class ClientRequestListServlet extends AbstractServlet {
    private static final long serialVersionUID = 1037033552470621428L;
    private static final String PATH_TO_CLIENT_REQUEST_LIST_PAGE = "/WEB-INF/view/manager/request_list.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ClientRequest> clientRequestList = null;
        notificationIncrement(req);

        try {
            clientRequestList = getHotelManagerService().getClientRequests();
        } catch (ApplicationException e) {
            resp.sendRedirect(req.getContextPath() + PathConstants.EXCEPTION_SERVLET);
            return;
        }
        req.setAttribute(Constants.CLIENT_REQUESTS, clientRequestList);
        forwardToPage(req, resp, PathConstants.PATH_TO_MANAGER_WRAPPER_PAGE, PATH_TO_CLIENT_REQUEST_LIST_PAGE);
    }


}

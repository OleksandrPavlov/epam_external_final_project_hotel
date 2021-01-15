package hotel.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exception")
public class ExceptionServlet extends AbstractServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1108343489443994762L;
    private static final String PATH_TO_EXCEPTION_PAGE = "WEB-INF/view/exceptions/exception.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage(req, resp, PATH_TO_EXCEPTION_PAGE, null);
    }


}

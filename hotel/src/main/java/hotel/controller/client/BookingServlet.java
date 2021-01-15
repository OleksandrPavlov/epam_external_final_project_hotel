package hotel.controller.client;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.controller.AbstractServlet;
import hotel.exception.handler.ApplicationException;
import hotel.exception.handler.DateValidationException;
import hotel.exception.handler.ValidationException;
import hotel.model.Book;
import hotel.model.Room;
import hotel.util.RequestEntityCollector;
import hotel.util.SupportingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/client/book")
public class BookingServlet extends AbstractServlet {
    private static final String PATH_TO_BOOK_PAGE = "/WEB-INF/view/client/booking.jsp";
    private static final long serialVersionUID = 5501054087517351669L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Room room;
        String beginDate = req.getParameter(Constants.BEGIN_DATE);
        String endDate = req.getParameter(Constants.END_DATE);
        req.setAttribute(Constants.BEGIN_DATE, beginDate);
        req.setAttribute(Constants.END_DATE, endDate);

        try {
            room = getCommonService().getRoomById(req);
            if (room != null) {
                req.setAttribute(Constants.TOTAL_PRICE,
                        SupportingUtils.calkulateTotalPriceForRoom(beginDate, endDate, room));
                req.setAttribute(Constants.CURRENT_ROOM_ATTRIBUTE, room);
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        forwardToPage(req, resp, PathConstants.PATH_TO_MAIN_WRAPPER_PAGE, PATH_TO_BOOK_PAGE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Book book = RequestEntityCollector.getBook(req);
            int suc = getHotelClientService().toBook(book);
            if (suc > 0) {
                req.getSession().setAttribute(Constants.SUCCESS, Constants.BOOK_SUCCESS);
            }
            String from = req.getParameter("from");
            if (Objects.nonNull(from)) {
                if ("offer".equals(from)) {
                    getHotelClientService().deleteOffer(req);
                }
            }
        } catch (ApplicationException | ValidationException e) {
            req.getSession().setAttribute(Constants.FAIL, Constants.BOOK_FAIL);
        } catch (DateValidationException ex) {
            req.getSession().setAttribute(Constants.FAIL, Constants.BOOK_FAIL_DATE);
        }
        resp.sendRedirect(req.getContextPath() + PathConstants.CLIENT_HOME_SERVLET);

    }

}

package hotel.filter;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "clientAccess")
public class ClientRoomFilter extends AbstractFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER_SESSION_ATTRIBUTE);
        if (Objects.nonNull(user) && user.getRole().equals("client")) {
            chain.doFilter(request, resp);
        } else {
            request.getSession().setAttribute(Constants.FAIL, Constants.BOOKING_ACCESS_FAIL);
            resp.sendRedirect(request.getContextPath() + PathConstants.LOGIN_SERVLET);
        }

    }

}

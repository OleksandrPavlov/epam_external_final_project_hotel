package hotel.filter;

import hotel.constant.Constants;
import hotel.constant.PathConstants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "alreadyLogin")
public class AlreadyLogin extends AbstractFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        if (Objects.nonNull(request.getSession().getAttribute(Constants.CURRENT_USER_SESSION_ATTRIBUTE))) {
            resp.sendRedirect(request.getContextPath() + PathConstants.DISTRIBUTOR_SERVLET);
        } else {
            chain.doFilter(request, resp);
        }
    }

}

package hotel.filter;

import hotel.constant.Constants;
import hotel.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "managerAccess")
public class ManagerRoomFilter extends AbstractFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER_SESSION_ATTRIBUTE);
        if (Objects.isNull(user) || !user.getRole().equals("manager")) {
            resp.sendRedirect(request.getContextPath() + "/home");
        } else {
            chain.doFilter(request, resp);
        }

    }

}

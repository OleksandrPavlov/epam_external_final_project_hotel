package hotel.filter;

import hotel.constant.Constants;
import hotel.constant.PathConstants;
import hotel.exception.handler.ApplicationException;
import hotel.model.User;
import hotel.service.ServiceManager;
import hotel.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "rememberFilter")
public class RememberFilter extends AbstractFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        ServiceManager ms = (ServiceManager) request.getServletContext().getAttribute(Constants.SERVICE_MANAGER);
        Cookie cookie = WebUtils.findCookie(request, "authentification");
        String identifier = null;
        if (Objects.isNull(cookie)) {
            chain.doFilter(request, resp);
        } else {
            User user = null;
            identifier = cookie.getValue();
            try {
                user = ms.getCommonService().checkUserInBaseByIdentifier(identifier);
                if (Objects.nonNull(user)) {
                    request.getSession().setAttribute(Constants.CURRENT_USER_SESSION_ATTRIBUTE, user);
                    resp.sendRedirect(request.getContextPath() + PathConstants.DISTRIBUTOR_SERVLET);
                    return;
                } else {
                    chain.doFilter(request, resp);
                }

            } catch (ApplicationException e) {
                e.printStackTrace();
            }

        }

    }

}

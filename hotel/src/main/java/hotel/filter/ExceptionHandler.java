package hotel.filter;

import hotel.constant.PathConstants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "exceptionHandler")
public class ExceptionHandler extends AbstractFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, resp);
        } catch (Throwable throwable) {
            resp.sendRedirect(request.getContextPath() + PathConstants.EXCEPTION_SERVLET);
        }
    }
}

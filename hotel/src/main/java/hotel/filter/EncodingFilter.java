package hotel.filter;

import hotel.constant.Constants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "encodingFilter")
public class EncodingFilter extends AbstractFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String lan = request.getParameter(Constants.LANGUAGE);
        if (lan != null) {
            request.getSession().setAttribute(Constants.LANGUAGE, lan);
        }
        chain.doFilter(request, resp);
    }
}

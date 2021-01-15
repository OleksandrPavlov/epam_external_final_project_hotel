package hotel.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
    public static Cookie findCookie(HttpServletRequest request, String nameCookie) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie coc : cookies) {
                if (coc.getName().equals(nameCookie)) {
                    if (coc.getValue() != null && !"".equals(coc.getValue())) {
                        return coc;
                    }
                }
            }
        }
        return null;
    }

    public static Cookie findCookie(HttpServletRequest request, String nameCookie, String value) {
        Cookie cookie = findCookie(request, value);
        if (cookie != null) {
            if (cookie.getValue().equals(value)) {
                return cookie;
            }
        }
        return null;
    }

    public static void setCookie(HttpServletResponse response, String nameCookie, String value, int age) {
        Cookie cookie = new Cookie(nameCookie, value);
        cookie.setMaxAge(age);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    private WebUtils() {

    }
}

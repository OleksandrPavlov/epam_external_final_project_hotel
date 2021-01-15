package hotel.constant;

public class PathConstants {
    private PathConstants() {

    }

    //liners
    public static final String PATH_TO_EXCEPTION_PAGE = "/WEB-INF/view/exceptions/exception.jsp";
    public static final String PENDED_MANAGER_LIST_PAGE = "/WEB-INF/view/manager/pendedUsers.jsp";
    //wrappers
    public static final String PATH_TO_MAIN_WRAPPER_PAGE = "/WEB-INF/view/wrappers/main_wrapper.jsp";
    public static final String PATH_TO_MANAGER_WRAPPER_PAGE = "/WEB-INF/view/wrappers/manager_wrapper.jsp";
    //servlets
    public static final String MANAGER_HOME_SERVLET = "/manager/home";
    public static final String MANAGER_EDIT_SERVLET = "/manager/home/editroom";
    public static final String CLIENT_REQ_LIST_SERVLET = "/manager/home/requestList";
    public static final String ADD_ROOM_SERVLET = "/manager/home/addroom";
    public static final String MANAGER_WAIT_LIST_SERVLET = "/manager/manager-wait-list";
    public static final String CLIENT_HOME_SERVLET = "/client/home";
    public static final String REQUEST_CLIENT_SERVLET = "/client/home/request";
    public static final String OFFERS_SERVLET = "/client/home/offers";
    public static final String DISTRIBUTOR_SERVLET = "/distributor";
    public static final String EXCEPTION_SERVLET = "/exception";
    public static final String HOME_SERVLET = "/home";
    public static final String LOGIN_SERVLET = "/home/login";
    public static final String REGISTER_SERVLET = "/register";
    public static final String APPLICATION_PROPERTIES_FILE = "application.properties";



}

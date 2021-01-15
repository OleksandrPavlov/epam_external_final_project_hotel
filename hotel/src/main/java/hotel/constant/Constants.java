package hotel.constant;

public class Constants {

    public static final String SERVICE_MANAGER = "SERVICE_MANAGER";

    // Session atributes
    public static final String CURRENT_USER_SESSION_ATTRIBUTE = "currentUser";
    public static final String CURRENT_ROOMS_REQUEST_ATTRIBUTE = "currentRooms";
    public static final String CURRENT_CLIENT_REQUEST_ATTRIBUTE = "currentClientRequest";
    public static final String LANGUAGE = "language";
    // Request attributes
    // Sorting
    public static final String SORT_ATTRIBUTE = "sort";

    // Manager request attributes
    public static final String COUNT_ANV_REQUESTS = "canreq";
    public static final String CLIENT_REQUESTS = "clientRequests";
    public static final String CLIENT_REQUEST_ID = "client_request_id";
    public static final String CLIENT_ID = "client_id";
    public static final String PEN_MAN_COUNT = "penManCount";

    // Room

    public static final String ROOM = "room";
    public static final String ROOM_ID = "id";
    public static final String ROOM_PRICE_ATTRIBUTE = "price";
    public static final String ROOM_SEAT_NUMBER_ATTRIBUTE = "seat_number";
    public static final String ROOM_PICTURE_ATTRIBUTE = "pic";
    public static final String ROOM_AREA_ATTRIBUTE = "area";
    public static final String ROOM_STATUS_ATTRIBUTE = "status";
    public static final String ROOM_CLASS_ATTRIBUTE = "class";
    public static final String ROOM_FACILITY_ATTRIBUTE = "facility";
    public static final String ROOM_SHORT_DESC_RUS_ATTRIBUTE = "sh_desc_rus";
    public static final String ROOM_SHORT_DESC_EN_ATTRIBUTE = "sh_desc_en";
    public static final String ROOM_DESC_RUS_ATTRIBUTE = "desc_rus";
    public static final String ROOM_DESC_EN_ATTRIBUTE = "desc_en";
    public static final String ROOM_NUMBER_ATTRIBUTE = "room_number";
    public static final String ROOM_AVAILABILITY = "availability";
    public static final String CURRENT_ROOM_ATTRIBUTE = "currentRoom";
    public static final String TOTAL_PRICE = "total_price";

    // Client request attributes names
    public static final String REQ_MAX_PRICE = "max_price";
    public static final String REQ_NIGHT_QUANTITY = "night_quantity";
    public static final String REQ_SETTLEMENT_DATE = "settl_date";
    public static final String REQ_COMMENT = "comment";
    public static final String CURRENT_BOOKS = "current_books";
    public static final String OFFERS = "offers";
    public static final String OFFER_COUNT = "offer_count";

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String EMPTY = "emptys";
    public static final String waiting_users = "waiting_users";


    // Common requests attributes names
    public static final String BEGIN_DATE = "begin_date";
    public static final String END_DATE = "end_date";
    public static final String CURRENT_DATE = "current_date";
    public static final String DATE_RESTRICT_MIN_ATTR = "min_date";
    public static final String DATE_RESTRICT_MAX_ATTR = "max_date";
    public static final String LOGIN_PARAMETER = "login";
    public static final String PASSWORD_PARAMETER = "password";
    public static final String REMEMBER = "remember";
    public static final String MANAGER = "manager";
    public static final String REGISTERED_USER_ROLE_ID = "registered_user_role";
    public static final String USER_REQUESTING_REGISTRATION = "userRequestingRegistration";
    // RegistrationPage
    public static final String USER_NAME = "user_name";
    public static final String USER_SURNAME = "user_surname";
    public static final String USER_PATRONIMIC = "user_patronimic";
    public static final String USER_MAIL = "user_mail";
    public static final String USER_PHONE = "user_phone";
    public static final String USER_LOGIN = "user_login";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_ROLE_ID = "role_id";
    // Notifications
    public static final String VALIDATION_EXCEPTION = "validation_exception";
    public static final String REQUEST_SUCCESSFULLY_SENDED = "req_sended";
    public static final String REQ_VALIDATION_FAIL = "req_not_valid";
    public static final String OFFER_SUCCESS = "offer_success";
    public static final String OFFER_FAIL = "offer_fail";
    public static final String BOOKING_ACCESS_FAIL = "access_book_fail";
    public static final String BOOK_FAIL_DATE = "book_fail_date";
    public static final String OBJECT_DOES_NOT_EXIST = "ob_does_not_exist";
    public static final String PAY_FAIL = "pay_fail";
    public static final String PAY_SUCCESS = "pay_success";
    public static final String REJECT_OFFER_SUCCESS = "rej_off_suc";
    public static final String ADD_ROOM_FAIL = "add_room_fail";
    public static final String ADD_ROOM_VALIDATION_FAIL = "add_room_validation_fail";
    public static final String ADD_ROOM_SUCCESS = "add_room_success";

    // Registration Page Notifications
    public static final String REGISTER_FAIL = "registration_fail";
    public static final String REGISTER_SUCCESS = "registration_success";

    // Login Page Notifications
    public static final String AUTHENTIFICATION_FAIL = "authentification_fail";

    // Exception messeges
    public static final String WRONG_INPUT_EXCEPTION = "You entered wrong value";
    public static final String WRONG_DATE_RANGE = "You entered wrong range!";

    // Notificationb Request Attributes
    public static final String BOOK_FORBID = "book_forbid";
    public static final String BOOK_SUCCESS = "book_success";
    public static final String BOOK_FAIL = "book_fail";
    public static final String SEARCH_FAIL = "search-fail";
    public static final String EMPTY_NO_ONE_ROOM = "no_one_room";
    //User active statuses
    public static final String ENABLED_USER = "enabled";
    public static final String DISABLED_USER = "disabled";
    public static final String WAITING_USER = "waiting";


    public static final String MANAGER_REGISTRATION_SUCCESS = "managerRegistrationSuccess";

    public static final String CLIENT_REGISTRATION_SUCCESS = "clientRegistrationSuccess";
}

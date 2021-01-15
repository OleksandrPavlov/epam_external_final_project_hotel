package hotel.util;

import hotel.exception.handler.DateValidationException;
import hotel.exception.handler.ValidationException;
import hotel.model.ClientRequest;
import hotel.model.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Validator {
    private Validator() {

    }

    private static final String NAME_REG = "^[a-zA-Zа-яёА-ЯЁЇії]+$";
    private static final String PHONE_REG = "^\\d+$";
    private static final String MAIL_REG = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_REG = "(?=.*[A-Za-z].*)(?=.*[0-9].*)[A-Za-z0-9]{7,32}";

    public static void clientRequestValidate(ClientRequest clientRequest) throws ValidationException {
        if (clientRequest.getFacilities().size() == 0) {
            throw new ValidationException();
        }
        if (clientRequest.getMaxPrice() > 100000 || clientRequest.getMaxPrice() < 1) {
            throw new ValidationException();
        }
        if (clientRequest.getNightNumber() > 30 || clientRequest.getNightNumber() < 1) {
            throw new ValidationException();
        }
    }

    public static void relevanceDateRange(DateRange dateRange) throws DateValidationException {
        if (dateRange == null) {
            throw new IllegalArgumentException();
        }
        Date currentDate = Date.valueOf(LocalDate.now());
        if (dateRange.getBegin().compareTo(currentDate) < 0) {
            throw new DateValidationException();
        }
    }

    public static void registrationUserValidation(User user) throws ValidationException {
        nameValidation(user.getName());
        nameValidation(user.getSurname());
        nameValidation(user.getPatronimic());
        passwordValidation(user.getLogin());
        phoneValidation(user.getPhone());
        mailValidation(user.getMail());
        passwordValidation(user.getPassword());
        roleValidation(user.getRoleId());
    }

    private static void nameValidation(String value) throws ValidationException {
        if (Objects.isNull(value) || !value.matches(NAME_REG)) {
            throw new ValidationException();
        }
    }

    private static void phoneValidation(String phone) throws ValidationException {
        if (Objects.isNull(phone) || !phone.matches(PHONE_REG)) {
            throw new ValidationException();
        }
    }

    private static void mailValidation(String mail) throws ValidationException {
        if (Objects.isNull(mail) || !mail.matches(MAIL_REG)) {
            throw new ValidationException();
        }
    }

    private static void passwordValidation(String password) throws ValidationException {
        if (Objects.isNull(password) || !password.matches(PASSWORD_REG)) {
            throw new ValidationException();
        }
    }

    private static void roleValidation(int value) throws ValidationException {
        if (value != 1 && value != 2) {
            throw new ValidationException();
        }
    }


}
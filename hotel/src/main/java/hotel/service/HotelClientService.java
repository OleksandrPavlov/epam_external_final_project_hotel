package hotel.service;

import hotel.exception.handler.ApplicationException;
import hotel.model.Book;
import hotel.model.ClientRequest;
import hotel.model.ManagerResponse;
import hotel.model.Room;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface HotelClientService {
    boolean sendRequest(ClientRequest clientRequest) throws ApplicationException;

    List<Room> getClientBooks(Integer id) throws ApplicationException;

    int toBook(Book book) throws ApplicationException;

    List<ManagerResponse> getOffersByClientId(HttpServletRequest request) throws ApplicationException;

    int countOffers(HttpServletRequest request) throws ApplicationException;

    void deleteOffer(HttpServletRequest request) throws ApplicationException;

    void payForRoom(HttpServletRequest request) throws ApplicationException;

    void rejectOffer(HttpServletRequest request) throws ApplicationException;

    int getCountOfBooks(Room room) throws ApplicationException;

}

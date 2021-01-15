package hotel.model;

import hotel.util.DateRange;

import java.sql.Timestamp;

public class Book {
    private int client_id;
    private int roomId;
    private DateRange dateRange;
    private Timestamp reserveDate;
    private String paid;
    private double total;

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int id) {
        this.roomId = id;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    @Override
    public String toString() {
        return "Book [client_id=" + client_id + ", roomId=" + roomId + ", dateRange=" + dateRange + ", reserveDate="
                + reserveDate + ", paid=" + paid + ", total=" + total + ", pop=" + "]";
    }

    public Timestamp getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Timestamp reserveDate) {

        this.reserveDate = reserveDate;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}

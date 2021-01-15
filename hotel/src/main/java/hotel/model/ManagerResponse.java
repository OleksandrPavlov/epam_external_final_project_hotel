package hotel.model;

import hotel.util.DateRange;

public class ManagerResponse {
    private int roomId;
    private int clientId;
    private DateRange dateRange;
    private Room room;
    private double total;

    @Override
    public String toString() {
        return "ManagerResponse [roomId=" + roomId + ", clientId=" + clientId + ", dateRange=" + dateRange + ", room="
                + room + ", total=" + total + "]";
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}

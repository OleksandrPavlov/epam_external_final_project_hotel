package hotel.model;

import java.sql.Date;
import java.util.List;

public class ClientRequest {
    private int id;
    private int clientId;
    private int seatsNumber;
    private int nightNumber;
    private double maxPrice;
    private RoomClass roomClass;
    private Date settlementDate;
    private String comment;
    private List<Facility> facilities;
    private int viewed;
    private String clientName;
    private String clientSurname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public int getNightNumber() {
        return nightNumber;
    }

    public void setNightNumber(int nightNumber) {
        this.nightNumber = nightNumber;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ClientRequest [id=" + id + ", clientId=" + clientId + ", seatsNumber=" + seatsNumber + ", nightNumber="
                + nightNumber + ", maxPrice=" + maxPrice + ", roomClass=" + roomClass + ", settlementDate="
                + settlementDate + ", comment=" + comment + ", facilities=" + facilities + ", viewed=" + viewed
                + ", clientName=" + clientName + ", clientSurname=" + clientSurname + "]";
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

}

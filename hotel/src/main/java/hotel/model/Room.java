package hotel.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int id;
    private int area;
    private double price;
    private int seatNumber;
    private RoomDescription description;
    private List<Facility> facilities;
    private RoomClass roomClass;
    private Status status;
    private String pRef;
    private String shortNameRus;
    private String shortNameEn;
    private int roomNumber;
    private int availability;
    private List<Book> books = new ArrayList<>();
    private int pop;


    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "Room [id=" + id + ", area=" + area + ", price=" + price + ", seatNumber=" + seatNumber
                + ", description=" + description + ", facilities=" + facilities + ", roomClass=" + roomClass
                + ", status=" + status + ", pRef=" + pRef + ", shortNameRus=" + shortNameRus + ", shortNameEn="
                + shortNameEn + ", roomNumber=" + roomNumber + ", availability=" + availability + ", books=" + books
                + ", pop=" + pop + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Room other = (Room) obj;
        return id == other.id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public RoomDescription getDescription() {
        return description;
    }

    public void setDescription(RoomDescription description) {
        this.description = description;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getpRef() {
        return pRef;
    }

    public void setpRef(String pRef) {
        this.pRef = pRef;
    }

    public String getShortNameRus() {
        return shortNameRus;
    }

    public void setShortNameRus(String shortNameRus) {
        this.shortNameRus = shortNameRus;
    }

    public String getShortNameEn() {
        return shortNameEn;
    }

    public void setShortNameEn(String shortNameEn) {
        this.shortNameEn = shortNameEn;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

}

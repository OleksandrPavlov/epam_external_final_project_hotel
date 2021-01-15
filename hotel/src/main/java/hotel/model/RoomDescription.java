package hotel.model;

public class RoomDescription {
    private String rusVersion;
    private String enVersion;

    public RoomDescription(String rusVersion, String enVErsion) {
        super();
        this.rusVersion = rusVersion;
        this.enVersion = enVErsion;
    }

    public String getRusVersion() {
        return rusVersion;
    }

    public void setRusVersion(String rusVersion) {
        this.rusVersion = rusVersion;
    }

    public String getEnVersion() {
        return enVersion;
    }

    public void setEnVersion(String enVersion) {
        this.enVersion = enVersion;
    }

    @Override
    public String toString() {
        return "RoomDescription [rusVersion=" + rusVersion + ", enVersion=" + enVersion + "]";
    }
}

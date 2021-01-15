package hotel.model;

public class RoomClass {
    private int classId;
    private String className;

    public RoomClass(int id, String className) {
        classId = id;
        this.className = className;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "RoomClass [classId=" + classId + ", className=" + className + "]";
    }

}

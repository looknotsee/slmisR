package finalproject2;

public class Course {
    private String classID;
    private String className;
    private String classTime;
    private String classDay;

    public Course(String classID, String className, String classTime, String classDay) {
        this.classID = classID;
        this.className = className;
        this.classTime = classTime;
        this.classDay = classDay;
    }

    public String getClassID() {return classID; }
    public String getclassName() {return className; }
    public String getclassTime() {return classTime; }
    public String getclassDay() {return classDay; }

    @Override
    public String toString() {
        return className + " - " + classTime + "(" + classDay + ")";
    }
}

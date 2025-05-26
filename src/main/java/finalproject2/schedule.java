package finalproject2;

import javafx.beans.property.SimpleStringProperty;

public class schedule {
    
    private final SimpleStringProperty className;
    private final SimpleStringProperty classTime;

    public schedule(String className, String classTime) {
        this.className = new SimpleStringProperty(className);
        this.classTime = new SimpleStringProperty(classTime);
    }

    public String getClassName() {
        return className.get();
    }

    public void setClassName(String className) {
        this.className.set(className);
    }

    public String getClassTime() {
        return classTime.get();
    }

    public void setClassTime(String classTime) {
        this.classTime.set(classTime);
    }
}

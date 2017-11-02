package msn.ratemytextbook;

import java.io.Serializable;

public class Course implements Serializable {
    public String courseName;

    protected Course () {
        this.courseName = "";
    }

    public Course (String c) {
        this.courseName = c;
    }

    public String getCourseName() { return courseName; }

    public void setCourseName(String courseName) { this.courseName = courseName; }
}

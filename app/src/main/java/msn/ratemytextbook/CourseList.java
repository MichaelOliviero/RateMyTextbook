/*package msn.ratemytextbook;

import java.util.Map;
import java.util.TreeMap;

public class CourseList {

    protected TreeMap<Course, String> courseList;

    public CourseList() {
        courseList = new TreeMap<Course, String>();
    }

    public void addCourse(Course c) {
        courseList.put(c , c.getCourse());
    }

    public TreeMap<Course, String> getCourseList() {
        return courseList;
    }

    public Course getCourse(String c) {
        Course key = null;
        String value = "";
        for(Map.Entry<Course,String> entry : courseList.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            System.out.println(key + " => " + value);
            if (value.equals(c)) { break; }
        }
        return key;
    }
}*/

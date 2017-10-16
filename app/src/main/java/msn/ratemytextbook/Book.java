package msn.ratemytextbook;

/**
 * Created by Mico on 2017-10-13.
 */

public class Book {

    protected String Author;
    protected String Course;
    protected int CCode;
    protected String Name;
    protected int Rating;

    public Book() {
        Author = "";
        Course = "";
        CCode = 0;
        Name = "";
        Rating = 5;
    }

    public Book(String a, String c, int cc, String n, int r) {
        Author = a;
        Course = c;
        CCode = cc;
        Name = n;
        Rating = r;
    }

    public String getAuthor() {
        return Author;
    }

    public String getCourse() {
        return Course;
    }

    public int getCCode() {
        return CCode;
    }

    public String getName() {
        return Name;
    }

    public int getRating() {
        return Rating;
    }

    public void setAuthor(String a) {
        Author = a;
    }

    public void setCourse(String c) {
        Course = c;
    }

    public void setCCode(int cc) {
        CCode = cc;
    }

    public void setName(String n) {
        Name = n;
    }

    public void setRating(int r) {
        Rating = r;
    }
}

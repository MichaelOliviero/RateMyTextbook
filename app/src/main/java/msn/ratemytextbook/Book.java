package msn.ratemytextbook;

import java.io.Serializable;

/**
 * Created by Mico on 2017-10-13.
 */

public class Book extends Object implements Serializable {

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

    public String getStringBook() {
        String bString;
        String cc = String.valueOf(this.getCCode());
        bString = "Book: " + this.getName() + "\nBy: " + this.getAuthor() + "\n" +
                    this.getCourse() + cc + " - " + this.getRating() + " Stars";
        return bString;
    }

    public String getAuthor() {
        return this.Author;
    }

    public String getCourse() {
        return this.Course;
    }

    public int getCCode() {
        return this.CCode;
    }

    public String getName() {
        return this.Name;
    }

    public int getRating() {
        return this.Rating;
    }

    public void setAuthor(String a) {
        this.Author = a;
    }

    public void setCourse(String c) { this.Course = c; }

    public void setCCode(int cc) {
        this.CCode = cc;
    }

    public void setName(String n) {
        this.Name = n;
    }

    public void setRating(int r) {
        this.Rating = r;
    }

}

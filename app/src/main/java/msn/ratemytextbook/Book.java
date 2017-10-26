package msn.ratemytextbook;

import java.io.Serializable;

public class Book extends Object implements Serializable {

    protected String Name;
    protected String Author;
    protected String Course;
    protected int CCode;
    protected int Rating;

    public Book() {
        Name = "";
        Author = "";
        Course = "";
        CCode = 0;
        Rating = 5;
    }

    public Book(String n, String a, String c, int cc, int r) {
        Name = n;
        Author = a;
        Course = c;
        CCode = cc;
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

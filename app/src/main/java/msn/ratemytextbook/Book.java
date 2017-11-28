package msn.ratemytextbook;

import com.google.firebase.database.ValueEventListener;

public class Book {

    public String bookTitle;
    public String bookAuthor;
    public String bookCourse;
    public int bookCCode;
    public float bookRating;
    public float numRating;
    public float totalRating;

    protected Book() {
        bookTitle = "";
        bookAuthor = "";
        bookCourse = "";
        bookCCode = 0;
        bookRating = 5;
        numRating = 0;
        totalRating = 0;
    }

    protected Book(String t, String a, String c, int cc, int r) {
        bookTitle = t;
        bookAuthor = a;
        bookCourse = c;
        bookCCode = cc;
        bookRating = r;
    }

    public String getBookTitle() { return bookTitle; }
    public String getBookAuthor() { return bookAuthor; }
    public String getBookCourse() { return bookCourse; }
    public int getBookCCode() { return bookCCode; }
    public float getBookRating() { return bookRating; }
    public float getTotalRating() { return totalRating; }
    public float getnumRating() { return numRating; }

    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor; }
    public void setBookCourse(String bookCourse) { this.bookCourse = bookCourse; }
    public void setBookCCode(int bookCCode) { this.bookCCode = bookCCode; }
    public void setBookRating(float userRating){
        this.totalRating += userRating;
        this.numRating++;
        this.bookRating = this.totalRating/(this.numRating);
    }
}

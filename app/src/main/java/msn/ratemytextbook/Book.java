package msn.ratemytextbook;

public class Book {

    public String bookTitle;
    public String bookAuthor;
    public String bookCourse;
    public int bookCCode;
    public int bookRating;

    protected Book() {
        bookTitle = "";
        bookAuthor = "";
        bookCourse = "";
        bookCCode = 0;
        bookRating = 5;
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
    public int getBookRating() { return bookRating; }

    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor; }
    public void setBookCourse(String bookCourse) { this.bookCourse = bookCourse; }
    public void setBookCCode(int bookCCode) { this.bookCCode = bookCCode; }
    public void setBookRating(int bookRating) { this.bookRating = bookRating; }
}

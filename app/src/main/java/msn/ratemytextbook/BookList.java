package msn.ratemytextbook;

import java.io.Serializable;
import java.util.ArrayList;

public class BookList implements Serializable {

    public ArrayList<Book> bookArrayList;

    protected BookList() {
        this.bookArrayList = new ArrayList<Book>();
    }

    public void addBook(Book b) { this.bookArrayList.add(b); }

    public ArrayList<Book> getBookArrayList() {
        return bookArrayList;
    }
    public Book getBook(int i) { return bookArrayList.get(i); }
}

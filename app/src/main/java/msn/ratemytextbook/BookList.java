package msn.ratemytextbook;

import java.util.ArrayList;

public class BookList {

    private ArrayList<Book> bookArrayList;

    private BookList() {
        bookArrayList = new ArrayList<Book>();
    }

    public ArrayList<Book> getBookArrayList() {
        return bookArrayList;
    }

    public void setBookArrayList(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }
}

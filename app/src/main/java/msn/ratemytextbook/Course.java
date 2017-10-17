package msn.ratemytextbook;

import java.util.ArrayList;

/**
 * Created by Stein on 2017-10-16.
 */

public class Course {

    ArrayList<Book> bookList = new ArrayList<Book>();
    protected String CCode;
    protected String Course;

    public Course() {
        CCode = "";
        Course = "";
    }

    public void addBook(Book b){
        if(bookList.contains(b)){
            return;
        }
        else{
            bookList.add(b);
        }
    }
}

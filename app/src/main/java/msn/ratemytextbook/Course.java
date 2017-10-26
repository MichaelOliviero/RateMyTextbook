/*package msn.ratemytextbook;

import java.util.ArrayList;

public class Course implements Comparable<Course>, java.io.Serializable {

    ArrayList<Book> bookList;
    protected String Course;

    public Course() {
        Course = "";
        bookList = new ArrayList<Book>();
    }

    public void addBook(Book b){
        if(bookList.contains(b)){
            return;
        }else{
            bookList.add(b);
        }
    }

    public void printInfo() {
        System.out.println("Course: " + getCourse());
        String str = "";
        for (Book b: bookList) {
            str += b.getName();
        }
        System.out.println("Books: " + str);
    }

    public void setCourse(String c) { Course = c; }

    public String getCourse() { return Course; }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public int compareTo(Course o) {

        int comparison = this.getCourse().compareTo(o.getCourse());
        if (comparison != 0) {
            return comparison;
        }else{
            return 0;
        }
    }

}*/

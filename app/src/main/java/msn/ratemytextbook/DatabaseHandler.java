package msn.ratemytextbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper implements Serializable {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "database";

    // books table name
    private static final String TABLE_BOOKS = "books";

    // courses table name
    private static final String TABLE_COURSES = "courses";


    // Books Table Columns names
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_COURSE = "course";
    private static final String KEY_COURSECODE = "course_code";
    private static final String KEY_RATING = "rating";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Books table
        String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOKS + "("
                + KEY_TITLE + " TEXT PRIMARY KEY,"
                + KEY_AUTHOR + " TEXT,"
                + KEY_COURSE + " TEXT,"
                + KEY_COURSECODE + " INTEGER,"
                + KEY_RATING + " INTEGER" + ")";
        db.execSQL(CREATE_BOOKS_TABLE);

        // Courses table
        String CREATE_COURSES_TABLE = "CREATE TABLE " + TABLE_COURSES + "("
                + KEY_COURSE + " TEXT PRIMARY KEY" + ")";
        db.execSQL(CREATE_COURSES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);

        // Create tables again
        onCreate(db);
    }

    // addBook()
    // Adding new book
    public void addBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getName()); // Book Title
        values.put(KEY_AUTHOR, book.getAuthor()); // Book Author
        values.put(KEY_COURSE, book.getCourse()); // Book Course
        values.put(KEY_COURSECODE, book.getCCode()); // Book Course Code
        values.put(KEY_RATING, book.getRating()); // Book Rating

        // Inserting Row
        db.insert(TABLE_BOOKS, null, values);
        db.close(); // Closing database connection
    }

    // addCourse()
    // Adding new course
    public void addCourse(String course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COURSE, course); // Book Title

        // Inserting Row
        db.insert(TABLE_COURSES, null, values);
        db.close(); // Closing database connection
    }

    // getBook()
    // Getting single book by title
    public Book getBook(String title) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BOOKS, new String[] { KEY_TITLE,
                        KEY_AUTHOR, KEY_COURSE, KEY_COURSECODE, KEY_RATING }, KEY_TITLE + "=?",
                new String[] { String.valueOf(title) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Book book = new Book(cursor.getString(0), cursor.getString(1),
                                cursor.getString(2), (Integer.parseInt(cursor.getString(3))),
                                    (Integer.parseInt(cursor.getString(4))));

        // return book
        return book;
    }

    // getCourse()
    // Getting single course by title
    public String getCourse(String title) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_COURSES, new String[] { KEY_COURSE }, KEY_COURSE + "=?",
                new String[] { String.valueOf(title) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        String course = cursor.getString(0);

        // return course
        return course;
    }

    // getAllBooks()
    // Getting All Books
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<Book>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BOOKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setName(cursor.getString(0));
                book.setAuthor(cursor.getString(1));
                book.setCourse(cursor.getString(2));
                book.setCCode(Integer.parseInt(cursor.getString(3)));
                book.setRating(Integer.parseInt(cursor.getString(4)));
                // Adding book to list
                bookList.add(book);
            } while (cursor.moveToNext());
        }

        // return book list
        return bookList;
    }

    // getAllCourses()
    // Getting All Courses
    public List<String> getAllCourses() {
        List<String> courseList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_COURSES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String course = cursor.getString(0);
                // Adding course to list
                courseList.add(course);
            } while (cursor.moveToNext());
        }

        // return course list
        return courseList;
    }

    // getBookCount()
    // Getting book Count
    public int getBookCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BOOKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // getCourseCount()
    // Getting course Count
    public int getCourseCount() {
        String countQuery = "SELECT  * FROM " + TABLE_COURSES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // updateBook()
    // Updating single book
    public int updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getName()); // Book Title
        values.put(KEY_AUTHOR, book.getAuthor()); // Book Author
        values.put(KEY_COURSE, book.getCourse()); // Book Course
        values.put(KEY_COURSECODE, book.getCCode()); // Book Course Code
        values.put(KEY_RATING, book.getRating()); // Book Rating

        // updating row
        return db.update(TABLE_BOOKS, values, KEY_TITLE + " = ?",
                new String[] { String.valueOf(book.getName()) });
    }

    // deleteBook()
    // Deleting single book
    public void deleteBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOKS, KEY_TITLE + " = ?",
                new String[] { String.valueOf(book.getName()) });
        db.close();
    }

    // deleteCourse()
    // Deleting single course
    public void deleteCourse(String course) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSES, KEY_COURSE + " = ?",
                new String[] { course });
        db.close();
    }
}

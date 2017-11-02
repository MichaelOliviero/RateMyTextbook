package msn.ratemytextbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DBListAdapter extends ArrayAdapter {


    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef = db.getReference("bookList");
    List<Book> mlist;

    public DBListAdapter (Context context, int resource, List<Book> list) {
        super(context,resource);
        mlist = list;
    }

    static class LayoutHandler {
        TextView title, author, course, course_code;
        RatingBar rating;
    }

    @Override
    public int getCount() {return mlist.size(); }


    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mview = convertView;
        LayoutHandler layoutHandler;
        if (mview == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mview = layoutInflater.inflate(R.layout.row_book, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.title = (TextView) mview.findViewById(R.id.book_title);
            layoutHandler.author = (TextView) mview.findViewById(R.id.book_author);
            layoutHandler.course = (TextView) mview.findViewById(R.id.book_course);
            layoutHandler.course_code = (TextView) mview.findViewById(R.id.book_course_code);
            layoutHandler.rating = (RatingBar) mview.findViewById(R.id.book_rating);
            mview.setTag(layoutHandler);
        }else {
            layoutHandler = (LayoutHandler) mview.getTag();
        }
        Book book = (Book)this.getItem(position);
        layoutHandler.title.setText(book.getBookTitle());
        layoutHandler.author.setText("By: " + book.getBookAuthor());
        layoutHandler.course.setText("Course: " + book.getBookCourse());
        layoutHandler.course_code.setText(String.valueOf(book.getBookCCode()));
        layoutHandler.rating.setRating(book.getBookRating());
        return mview;
    }
}

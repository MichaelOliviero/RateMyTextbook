package msn.ratemytextbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Pallavi Singh on 2017-11-27.
 */

public class marketAdapter extends ArrayAdapter {


    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef = db.getReference("marketListMP");
    List<MarketBook> mlist;

    public marketAdapter (Context context, int resource, List<MarketBook> list) {
        super(context,resource);
        mlist = list;
    }

    static class LayoutHandler {
        TextView title, author, course, course_code, email, number, price;
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
            mview = layoutInflater.inflate(R.layout.row_market, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.title = (TextView) mview.findViewById(R.id.book_title_m);
            layoutHandler.author = (TextView) mview.findViewById(R.id.book_author_m);
            layoutHandler.course = (TextView) mview.findViewById(R.id.book_course_code_m);
            layoutHandler.course_code = (TextView) mview.findViewById(R.id.book_course_code_m);
            layoutHandler.email = (TextView) mview.findViewById(R.id.seller_email_m);
            layoutHandler.number = (TextView) mview.findViewById(R.id.seller_number_m);
            layoutHandler.price = (TextView) mview.findViewById(R.id.book_price_m);
            mview.setTag(layoutHandler);
        }else {
            layoutHandler = (LayoutHandler) mview.getTag();
        }
        MarketBook book = (MarketBook)this.getItem(position);
        layoutHandler.title.setText(book.getBookTitle());
        layoutHandler.author.setText("By: " + book.getBookAuthor());
        layoutHandler.course.setText("Course: " + book.getBookCourse());
        layoutHandler.course_code.setText(String.valueOf(book.getBookCCode()));
        layoutHandler.email.setText("Seller email: " + book.getSellerEmail());
        layoutHandler.number.setText("Phone number: " + book.getSellerNumber());
        layoutHandler.price.setText("Price: "+ book.getPrice());

        return mview;
    }
}

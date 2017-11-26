package msn.ratemytextbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCourseFragment extends Fragment{

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("courseList");

    public AddCourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_course, container, false);

        String Name = null;
        String Author = null;
        String Course = null;
        int CCode = 0;
        int Rating = 0;

        TextView tvAuthor = (TextView)view.findViewById(R.id.Title);
        tvAuthor.setText("Name: \t" + Name);

        TextView tvName = (TextView)view.findViewById(R.id.Author );
        tvName.setText("Author: \t" + Author);

        TextView tvCourse = (TextView)view.findViewById(R.id.Course );
        tvCourse.setText("Course: \t" + Course);

        TextView tvCCode = (TextView)view.findViewById(R.id.CCode );
        tvCCode.setText("Course Code: \t" + CCode);

        RatingBar rate_bar = (RatingBar)view.findViewById(R.id.ratingBar);
        rate_bar.setRating(Float.parseFloat("5.0"));

        return view;
    }

}


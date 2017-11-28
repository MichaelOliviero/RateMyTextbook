package msn.ratemytextbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddCourseFragment extends Fragment{

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("bookList");

    public Button button;

    public AddCourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_course, container, false);

        String Title = "fake Title";
        String Author = null;
        String Course = null;
        int CCode = 0;
        float Rating = 0;

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Title = bundle.getString("Title");
            Author = bundle.getString("Author");
            Course = bundle.getString("Course");
            CCode = bundle.getInt( "CCode" );
            Rating = bundle.getFloat( "Rating" );
        }

        TextView tvAuthor = (TextView)view.findViewById(R.id.Title);
        tvAuthor.setText("Name: \t" + Title);
        TextView tvName = (TextView)view.findViewById(R.id.Author );
        tvName.setText("Author: \t" + Author);
        TextView tvCourse = (TextView)view.findViewById(R.id.Course );
        tvCourse.setText("Course: \t" + Course);
        TextView tvCCode = (TextView)view.findViewById(R.id.CCode );
        tvCCode.setText("Course Code: \t" + CCode);
        TextView tvRating = (TextView)view.findViewById(R.id.Rating );
        tvRating.setText("Rating: \t" + Rating);
        final RatingBar rate_bar = (RatingBar)view.findViewById(R.id.ratingBar);
        rate_bar.setRating(Rating);

        button = (Button) view.findViewById(R.id.submitRating);
        final String finalTitle = Title;
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final float userRating = rate_bar.getRating();
                myRef.orderByChild("bookTitle").equalTo(finalTitle).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                            final Book b = areaSnapshot.getValue(Book.class);
                            if(b.getBookTitle().equals( finalTitle )) {
                                float totalRating = b.getTotalRating();
                                float numRating = b.getnumRating();
                                float bookRating = b.getBookRating();
                                System.out.println( "totalRating " + totalRating );
                                System.out.println( "numRating " + numRating );
                                System.out.println( "bookRating \n\n\n " + bookRating );

                                totalRating += userRating;
                                numRating++;
                                bookRating = (float) Math.rint( totalRating / numRating );

                                areaSnapshot.getRef().child( "totalRating" ).setValue( totalRating );
                                areaSnapshot.getRef().child( "numRating" ).setValue( numRating );
                                areaSnapshot.getRef().child( "bookRating" ).setValue( bookRating );
                                break;
                            }
                        }
                        return;
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        System.out.println( "Failed to read value: " + error.toException() );
                    }
                });

                Fragment fragment = new HomeFragment();
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, fragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }



}


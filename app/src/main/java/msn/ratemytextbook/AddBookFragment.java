package msn.ratemytextbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddBookFragment extends Fragment{

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("bookList");
    DatabaseReference spinnerRef = database.getReference("courseList");

    public Button button;

    public AddBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        spinnerRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<String> courses = new ArrayList<String>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    String areaName = areaSnapshot.child("courseName").getValue(String.class);
                    courses.add(areaName);
                }

                Spinner spinner = (Spinner) view.findViewById(R.id.Course_spn);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        R.layout.support_simple_spinner_dropdown_item,
                        courses);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("Failed to read value: " + error.toException());
            }
        });

        button = (Button) view.findViewById(R.id.submit_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book inputBook = new Book();
                int CCode;

                //grab text from the text box
                EditText inputName = (EditText) getView().findViewById(R.id.bookTitle_id);
                EditText inputAuthor = (EditText) getView().findViewById(R.id.bookAuthor_id);
                Spinner inputCourse = (Spinner) getView().findViewById(R.id.Course_spn);
                EditText inputCCode = (EditText) getView().findViewById(R.id.bookCCode_id);

                if (TextUtils.isEmpty(inputName.getText().toString())) {
                    inputName.setError("Please input a title");
                    return;
                }else if (inputName.getText().toString().length() > 25) {
                    inputName.setError("The max character count is: 25");
                    return;
                }
                if (TextUtils.isEmpty(inputAuthor.getText().toString())) {
                    inputAuthor.setError("Please input an author's name");
                    return;
                }else if (inputAuthor.getText().toString().length() > 25) {
                    inputAuthor.setError("The max character count is: 25");
                    return;
                }
                if (TextUtils.isEmpty(inputCCode.getText().toString())) {
                    inputCCode.setError("Please input a course code");
                    return;
                }else if (inputCCode.getText().toString().length() > 4) {
                    inputCCode.setError("Please input a valid course code");
                    return;
                }
                try {
                    CCode = Integer.parseInt(inputCCode.getText().toString());
                } catch (NumberFormatException e) {
                    inputCCode.setError("Please input a valid course code");
                    return;
                }

                //set the books variables
                inputBook.setBookTitle(inputName.getText().toString());
                inputBook.setBookAuthor(inputAuthor.getText().toString());
                inputBook.setBookCourse(inputCourse.getSelectedItem().toString());
                inputBook.setBookCCode(CCode);
                inputBook.numRating = 0;
                inputBook.totalRating = 0;
                // Rating is defaulted to 5 stars


                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        BookList value = dataSnapshot.getValue(BookList.class);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        System.out.println("Failed to read value: " + error.toException());
                    }
                });
                myRef.push().setValue(inputBook);
                inputName.setText(null);
                inputAuthor.setText(null);
                inputCCode.setText(null);

                Toast.makeText(getView().getContext(), "Book added!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
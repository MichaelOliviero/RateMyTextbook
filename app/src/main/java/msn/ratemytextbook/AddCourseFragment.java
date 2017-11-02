package msn.ratemytextbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCourseFragment extends Fragment{

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("courseList");

    public Button button2;

    public AddCourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_course, container, false);

        button2 = (Button) view.findViewById(R.id.submit_btn2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course c = new Course();

                // Grab text from the text box
                EditText inputCourse2 = (EditText) getView().findViewById(R.id.new_courseName);

                if(TextUtils.isEmpty(inputCourse2.getText().toString())) {
                    inputCourse2.setError("Please input a course");
                    return;
                }else if (inputCourse2.getText().toString().length() > 4) {
                    inputCourse2.setError("Please use the 4 letter abbreviation of your course");
                    return;
                }

                c.setCourseName(inputCourse2.getText().toString().toUpperCase());

                myRef.push().setValue(c);
                inputCourse2.setText(null);

                Toast.makeText(getView().getContext(), "Course added!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}

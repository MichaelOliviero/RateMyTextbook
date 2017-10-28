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

import java.util.List;

public class AddBookFragment extends Fragment{

    DatabaseHandler db = (DatabaseHandler) MainActivity.myBundle.get("database");
    public Button button;

    public AddBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.Course_spn);

        List<String> courses = db.getAllCourses();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        R.layout.support_simple_spinner_dropdown_item,
                        courses);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        button = (Button) view.findViewById(R.id.submit_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book inputBook = new Book();
                int CCode;

                //grab text from the text box
                EditText inputName = (EditText) getView().findViewById(R.id.bookName);
                EditText inputAuthor = (EditText) getView().findViewById(R.id.Author);
                Spinner inputCourse = (Spinner) getView().findViewById(R.id.Course_spn);
                EditText inputCCode = (EditText) getView().findViewById(R.id.CCode);

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
                inputBook.setName(inputName.getText().toString());
                inputBook.setAuthor(inputAuthor.getText().toString());
                inputBook.setCourse(inputCourse.getSelectedItem().toString());
                inputBook.setCCode(CCode);
                // Rating is defaulted to 5 stars

                db.addBook(inputBook);
                inputName.setText(null);
                inputAuthor.setText(null);
                inputCCode.setText(null);

                Toast.makeText(getView().getContext(), "Book added!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
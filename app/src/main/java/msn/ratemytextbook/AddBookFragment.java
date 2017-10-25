package msn.ratemytextbook;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddBookFragment extends Fragment{
    public AddBookFragment() {
        // Required empty public constructor
    }

    public Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_book, container, false);
        button = (Button) view.findViewById(R.id.submit_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book inputBook = new Book();

                //grab text from the text box
                EditText inputName = (EditText) getView().findViewById(R.id.bookName);
                EditText inputAuthor = (EditText) getView().findViewById(R.id.Author);
                Spinner inputCourse = (Spinner) getView().findViewById(R.id.Course_spn);
                EditText inputCCode = (EditText) getView().findViewById(R.id.CCode);

                if(TextUtils.isEmpty(inputName.getText().toString())) {
                    inputName.setError("Please input a title");
                    return;
                }
                if(TextUtils.isEmpty(inputAuthor.getText().toString())) {
                    inputAuthor.setError("Please input an author's name");
                    return;
                }
                if(TextUtils.isEmpty(inputCCode.getText().toString())) {
                    inputCCode.setError("Please input a course code");
                    return;
                }

                //set the books variables
                inputBook.setName(inputName.getText().toString());
                inputBook.setAuthor(inputAuthor.getText().toString());
                inputBook.setCourse(inputCourse.getSelectedItem().toString());
                inputBook.setCCode(Integer.parseInt(inputCCode.getText().toString()));
                // Rating is defaulted to 5 stars

                System.out.println("BOOK MADE");
                System.out.println(inputBook.getStringBook());

                if (!(inputBook.getName().equals(""))) {
                    MainActivity.myBundle.putSerializable("newbook", inputBook);
                }

                CourseList tmplist = (CourseList) MainActivity.myBundle.get("mainList");
                boolean trigger = (tmplist.getCourseList().isEmpty());
                if (trigger){
                    // if there is no course
                    // .: add a new course
                    Course c = new Course();
                    c.setCourse(inputBook.getCourse());
                    c.addBook(inputBook);
                    c.printInfo();
                    tmplist.addCourse(c);
                }else{
                    // add book to existing course
                    tmplist.getCourse(inputBook.getCourse()).addBook(inputBook);
                    tmplist.getCourse(inputBook.getCourse()).printInfo();
                }
                System.out.println(tmplist);

                //create book = done
                //check if course exists in courselist
                //yes - add to existing course
                //no - create new course and add course to courselist


            }
        });

        return view;
    }
}
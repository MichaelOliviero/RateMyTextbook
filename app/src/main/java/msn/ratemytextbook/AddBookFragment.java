package msn.ratemytextbook;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import msn.ratemytextbook.Book;
import msn.ratemytextbook.R;

public class AddBookFragment extends Fragment {
    public AddBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        /*final Button button = (Button) getView().findViewById(R.id.Submit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Book inputBook = new Book();

                String Name = "";
                String Author = "";
                String Course = "";
                int CCode = 0;

                //grab text from the text box
                EditText inputName = (EditText) getView().findViewById(R.id.bookName);
                EditText inputAuthor = (EditText) getView().findViewById(R.id.Author );
                EditText inputCourse = (EditText) getView().findViewById(R.id.Course);
                EditText inputCCode = (EditText) getView().findViewById(R.id.CCode);

                //set them to local variables
                Name = inputName.getText().toString();
                Author = inputName.getText().toString();
                Course = inputName.getText().toString();
                //need to add Course code

                System.out.println("please fking work");

                inputBook.setName(Name);
                inputBook.setAuthor(Author);
                inputBook.setCourse(Course);
                inputBook.setCCode(CCode);


            }

            //return inflater.inflate(R.layout.fragment_add_book, container, false);
        });*/
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
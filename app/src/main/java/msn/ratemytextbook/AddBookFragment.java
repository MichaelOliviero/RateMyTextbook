package msn.ratemytextbook;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import msn.ratemytextbook.Book;
import msn.ratemytextbook.R;

public class AddBookFragment extends Fragment{
    public AddBookFragment() {
        // Required empty public constructor
    }

    public Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);
        button = (Button) view.findViewById(R.id.submit_btn);
        //button.setOnClickListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book inputBook = new Book();

                //grab text from the text box
                EditText inputName = (EditText) getView().findViewById(R.id.bookName);
                EditText inputAuthor = (EditText) getView().findViewById(R.id.Author);
                EditText inputCourse = (EditText) getView().findViewById(R.id.Course);
                EditText inputCCode = (EditText) getView().findViewById(R.id.CCode);

                //set the books variables
                inputBook.setName(inputName.getText().toString());
                inputBook.setAuthor(inputAuthor.getText().toString());
                inputBook.setCourse(inputCourse.getText().toString());
                //input course code

                System.out.println("BOOK MADE");
                System.out.println(inputBook);
            }
        });

        return view;
    }
}
package msn.ratemytextbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        final Button button = (Button) findViewById(R.id.Submit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Book inputBook = new Book();

                String Name = "";
                String Author = "";
                String Course = "";
                int CCode = 0;

                //grab text from the text box
                EditText inputName = (EditText) findViewById(R.id.bookName);
                EditText inputAuthor = (EditText) findViewById(R.id.Author );
                EditText inputCourse = (EditText) findViewById(R.id.Course);
                EditText inputCCode = (EditText) findViewById(R.id.Ccode);

                //set them to local variables
                Name = inputName.getText().toString();
                Author = inputName.getText().toString();
                Course = inputName.getText().toString();
                //need to add Course code

                inputBook.setName(Name);
                inputBook.setAuthor(Author);
                inputBook.setCourse(Course);
                inputBook.setCCode(CCode);

            }
        });
    }
}

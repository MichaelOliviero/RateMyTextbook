package msn.ratemytextbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class BookShower extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_book_shower );

        DatabaseHandler db = (DatabaseHandler) MainActivity.myBundle.get( "database" );
        List<Book> list = db.getAllBooks();
        String Name;
        String Author;
        String Course;
        int CCode;
        int Rating;

        Name = getIntent().getExtras().getString("Name");
        Author = getIntent().getExtras().getString("Author");
        Course = getIntent().getExtras().getString("Course");
        //System.out.println(getIntent().getExtras().getInt(  ))
        CCode = getIntent().getExtras().getInt("CCode");
        Rating = Integer.valueOf(getIntent().getExtras().getInt("Rating"));

        TextView tvAuthor = (TextView)findViewById(R.id.Name );
        tvAuthor.setText("Name: \t" + Name);

        TextView tvName = (TextView)findViewById(R.id.Author );
        tvName.setText("Author: \t" + Author);

        TextView tvCourse = (TextView)findViewById(R.id.Course );
        tvCourse.setText("Course: \t" + Course);

        TextView tvCCode = (TextView)findViewById(R.id.CCode );
        tvCCode.setText("Course Code: \t" + CCode);

        RatingBar rate_bar = (RatingBar) findViewById(R.id.ratingBar);
        rate_bar.setRating(Float.parseFloat("5.0"));
    }

    public void switchBack(View view) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

}

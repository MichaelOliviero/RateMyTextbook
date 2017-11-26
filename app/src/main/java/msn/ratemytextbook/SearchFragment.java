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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("bookList");
    public Button button_search;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        final ListView lv = (ListView) view.findViewById(R.id.bookList_search);
        button_search = (Button) view.findViewById(R.id.submit_btn_search);
        button_search.setOnClickListener(new View.OnClickListener() {
           /* spinnerRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    final List<String> choices = new ArrayList<String>();
                    choices.add("Author");
                    choices.add("Course");
                    choices.add("Title");
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
            });*/




            @Override
            public void onClick(View v) {
                final EditText search = (EditText) getView().findViewById(R.id.new_search);
                final Spinner author = (Spinner) getView().findViewById(R.id.method);
                if(TextUtils.isEmpty(search.getText().toString())) {
                    search.setError("Please input a Title");
                    return;
                }else if (search.getText().toString().length() > 25) {
                    search.setError("The max character count is: 25");
                    return;
                }
                if(TextUtils.isEmpty(author.getSelectedItem().toString())) {
                    search.setError("Please Enter either Title, Course name or Author");
                    return;
                }
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int t=0;
                        BookList bl = new BookList();//
                        for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                            Book b = areaSnapshot.getValue(Book.class);
                            if (author.getSelectedItem().toString().toLowerCase().equals("title")) {
                                if (b.getBookTitle()!=null) {
                                    if (b.getBookTitle().toLowerCase().equals(search.getText().toString().toLowerCase())) {
                                        bl.addBook(b);
                                        t++;
                                    }
                                }
                            }else if (author.getSelectedItem().toString().toLowerCase().equals("author")) {
                                if (b.getBookAuthor()!=null) {
                                    if (b.getBookAuthor().toLowerCase().equals(search.getText().toString().toLowerCase())) {
                                        bl.addBook(b);
                                        t++;
                                    }
                                }
                            }else if (author.getSelectedItem().toString().toLowerCase().equals("course")) {
                                if (b.getBookCourse()!=null) {
                                    if (b.getBookCourse().toLowerCase().equals(search.getText().toString().toLowerCase())) {
                                        bl.addBook(b);
                                        t++;
                                    }
                                }
                            }
                        }
                        if(t==0) {
                            search.setError("No Results");
                            return;
                        }
                        if (getActivity() != null) {
                            final DBListAdapter lvadapter = new DBListAdapter(
                                    getActivity(),
                                    android.R.layout.simple_list_item_2,
                                    bl.getBookArrayList()
                            );
                            lv.setAdapter(lvadapter);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        System.out.println("Failed to read value: " + error.toException());
                    }
                });
            }
        });
        return view;
    }
}



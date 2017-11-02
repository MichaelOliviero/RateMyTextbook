package msn.ratemytextbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef = db.getReference("bookList");

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        final ListView lv = (ListView) view.findViewById(R.id.bookList_mainMenu);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                BookList bl = new BookList();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    Book b = areaSnapshot.getValue(Book.class);
                    bl.addBook(b);
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

        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("This is the Author: \n \n");
                Book b = (Book) lvadapter.getItem(position);
                System.out.println(b.getAuthor());

                //((MainActivity) getActivity()).sendData(b);
                Intent sendString = new Intent(getActivity(), BookShower.class);
                sendString.putExtra("Name", b.getName());
                sendString.putExtra("Author", b.getAuthor());
                sendString.putExtra("Course", b.getCourse());
                sendString.putExtra("CCode", b.getCCode());
                sendString.putExtra("Rating", b.getRating());

                startActivity(sendString);



                /*final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.drawerLayout, new DisplayBook(), "NewFragmentTag");
                ft.addToBackStack(null);
                ft.commit();*/


        //    }
        //});

        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Book value = dataSnapshot.getValue(Book.class);
                //System.out.println(value.getName() + value.getAuthor());

                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("Failed to read value");
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/


        return view;
    }


}

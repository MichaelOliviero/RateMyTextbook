/**
 * Created by Pallavi Singh on 2017-11-21.
 */
package msn.ratemytextbook;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

public class MarketPlace extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("marketListMP");
    DatabaseReference spinnerRef = database.getReference("courseList");

    public Button button;

    public MarketPlace() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marketplace, container, false);

        final ListView lv = (ListView) view.findViewById(R.id.listView1);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                MarketBookList bl = new MarketBookList();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    MarketBook b = areaSnapshot.getValue(MarketBook.class);
                    bl.addBook(b);
                }

                if (getActivity() != null) {
                    final marketAdapter lvadapter = new marketAdapter(
                            getActivity(),
                            android.R.layout.simple_list_item_1,
                            bl.getBookArrayList());
                    lv.setAdapter(lvadapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("Failed to read value: " + error.toException());
            }
        });
        return view;
    }
}




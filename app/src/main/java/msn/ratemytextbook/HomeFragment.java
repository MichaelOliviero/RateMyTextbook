package msn.ratemytextbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HomeFragment extends Fragment {

    DatabaseHandler db = (DatabaseHandler) MainActivity.myBundle.get("database");

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ListView lv = (ListView) view.findViewById(R.id.bookList_mainMenu);

        DBListAdapter lvadapter = new DBListAdapter(
                getActivity(),
                android.R.layout.simple_list_item_2,
                db.getAllBooks()
        );

        lv.setAdapter(lvadapter);

        return view;
    }

}

package msn.ratemytextbook;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class DisplayBook extends Fragment {

    DatabaseHandler db = (DatabaseHandler) MainActivity.myBundle.get("database");

    public DisplayBook() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        System.out.println("am i being made? \n \n \n");

        /*final ListView lv = (ListView) view.findViewById(R.id.bookList_mainMenu);

        DBListAdapter lvadapter = new DBListAdapter(
                getActivity(),
                android.R.layout.simple_list_item_2,
                db.getAllBooks()
        );

        lv.setAdapter(lvadapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("WRONG ONE");
                //Object o = lv.getItemAtPosition(position);
                // prestationEco str = (prestationEco)o; //As you are using Default String Adapter
                // Toast.makeText(getBaseContext(),str.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
        */
        return view;
    }

}

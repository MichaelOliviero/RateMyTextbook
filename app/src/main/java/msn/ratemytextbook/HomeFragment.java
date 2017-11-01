package msn.ratemytextbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

        final ListView lv = (ListView) view.findViewById(R.id.bookList_mainMenu);

        final DBListAdapter lvadapter = new DBListAdapter(
                getActivity(),
                android.R.layout.simple_list_item_2,
                db.getAllBooks()
        );

        lv.setAdapter(lvadapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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


            }
        });

        return view;
    }


}

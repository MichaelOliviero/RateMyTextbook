package msn.ratemytextbook;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        CourseList tmplist = (CourseList) MainActivity.myBundle.get("mainList");

        try {
            ArrayList<String> tmp2 = new ArrayList<String>();

            for(Map.Entry<Course,String> entry : tmplist.getCourseList().entrySet()) {
                Course key = entry.getKey();
                for (Book b: key.getBookList()) {
                    tmp2.add(b.getStringBook());
                }

            }

            ListView lv = (ListView) view.findViewById(R.id.bookList_mainMenu);

            ArrayAdapter<String> lvadapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    tmp2
            );

            lv.setAdapter(lvadapter);

        }catch (NullPointerException e) {
            System.out.println("It looks like there arent any books");
        }

        return view;
    }

}

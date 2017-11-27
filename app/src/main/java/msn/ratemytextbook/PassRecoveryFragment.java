package msn.ratemytextbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class PassRecoveryFragment extends Fragment {

    public Button button1;
    public TextView button2;
    private FirebaseAuth mAuth;

    public PassRecoveryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passrecovery, container, false);

        mAuth = FirebaseAuth.getInstance();

        button1 = (Button) view.findViewById(R.id.submit_btn8);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Grab text from the text box
                EditText inputUser = (EditText) getView().findViewById(R.id.emailRecov);
                String user = inputUser.getText().toString();

                mAuth.sendPasswordResetEmail(user);
                Toast.makeText(getView().getContext(), "Email sent to: " + user, Toast.LENGTH_SHORT).show();
                inputUser.setText(null);
                user = "";
            }
        });

        button2 = (TextView) view.findViewById(R.id.submit_btn9);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Grab text from the text box
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main_container, new LogInFragment());
                ft.commit();
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Log In");
            }
        });

        return view;
    }
}

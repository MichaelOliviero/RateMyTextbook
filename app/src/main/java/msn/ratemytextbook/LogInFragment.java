package msn.ratemytextbook;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInFragment extends Fragment {

    public Button button3;
    public TextView button4;
    public TextView button5;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    public LogInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawerLayout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        progressDialog = new ProgressDialog(this.getContext());

        button3 = (Button) view.findViewById(R.id.submit_btn3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth = FirebaseAuth.getInstance();

                // Grab text from the text box
                EditText inputUser = (EditText) getView().findViewById(R.id.accName);
                EditText inputPass = (EditText) getView().findViewById(R.id.accPass);
                String user = inputUser.getText().toString();
                String pass = inputPass.getText().toString();

                progressDialog.setMessage("Logging in...");
                progressDialog.show();

                LoginUser(user,pass, progressDialog);

            }
        });

        button4 = (TextView) view.findViewById(R.id.submit_btn4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Grab text from the text box
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main_container, new RegisterFragment());
                ft.commit();
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Register");
            }
        });

        button5 = (TextView) view.findViewById(R.id.submit_btn7);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Grab text from the text box
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main_container, new PassRecoveryFragment());
                ft.commit();
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Password Reset");
            }
        });

        return view;
    }

    private void LoginUser(String email, final String pass, final ProgressDialog pd) {
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener((MainActivity) getActivity(), new OnCompleteListener<AuthResult>() {
        @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.main_container, new HomeFragment());
                    ft.commit();
                    ((MainActivity) getActivity()).getSupportActionBar().setTitle("Home");
                    DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawerLayout);
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                } else {
                    Toast.makeText(getView().getContext(), "Authentication failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
                pd.hide();
            }
        });
    }
}

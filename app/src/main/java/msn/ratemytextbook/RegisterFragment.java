package msn.ratemytextbook;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

public class RegisterFragment extends Fragment {

    public Button button5;
    public TextView button6;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this.getContext());

        button5 = (Button) view.findViewById(R.id.submit_btn5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Grab text from the text box
                EditText inputUser = (EditText) getView().findViewById(R.id.newaccName);
                EditText inputPass = (EditText) getView().findViewById(R.id.newaccPass);
                String user = inputUser.getText().toString();
                String pass = inputPass.getText().toString();

                progressDialog.setMessage("Registering User...");
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(user, pass)
                        .addOnCompleteListener((MainActivity)getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getView().getContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                    ft.replace(R.id.main_container, new LogInFragment());
                                    ft.commit();
                                    ((MainActivity) getActivity()).getSupportActionBar().setTitle("Log In");
                                } else {
                                    Toast.makeText(getView().getContext(), "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                                progressDialog.hide();
                            }
                        });
            }
        });

        button6 = (TextView) view.findViewById(R.id.submit_btn6);
        button6.setOnClickListener(new View.OnClickListener() {
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

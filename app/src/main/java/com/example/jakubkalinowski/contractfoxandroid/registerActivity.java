package com.example.jakubkalinowski.contractfoxandroid;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

//firebase deprecated library
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//firebase deprecated library

public class registerActivity extends AppCompatActivity {

//    private static final String TAG = "authListener_TAG!!" ;
//    //Firebase Reference
//    //  Firebase ref = new Firebase("https://contractfox.firebaseio.com/");
//    DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
//
//    // [START declare_auth]
//    private FirebaseAuth mAuth;
//    // [END declare_auth]
//
//    // [START declare_auth_listener]
//    private FirebaseAuth.AuthStateListener mAuthListener;
//    // [END declare_auth_listener]

    private Toolbar topToolbar;

    //variables for all the components of the activity
    private EditText mEmailAddress;
    private EditText mPassword;
    private EditText mRepeatPassword;

    //string values
    private String mEmailAddressValue;
    private String mPasswordValue;
    private String mRepeatPasswordValue;

    private Button mNextButton;

    //variables for extracting values from components
    private String emailInput;
    private String passwordInput;
    private String repeatPasswordInput;
    private Boolean contractor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Setting the toolbar
        topToolbar = (Toolbar) findViewById(R.id.registration_toolbar__);
        setSupportActionBar(topToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        topToolbar.setTitle("IOO");

        //this part is for hint animation
        TextInputLayout emailAddressWrapper = (TextInputLayout) findViewById(R.id.email_address_text_input);
        TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.password_textInput);
        TextInputLayout repeatPasswordWrapper = (TextInputLayout) findViewById(R.id.repeat_password_textInput);
        emailAddressWrapper.setHint("Email Address");
        passwordWrapper.setHint("Password");
        repeatPasswordWrapper.setHint("Repeat Password");

        //initializing activity components
        mEmailAddress = (EditText) findViewById(R.id.email_address);
        mPassword = (EditText) findViewById(R.id.password);
        mRepeatPassword = (EditText) findViewById(R.id.repeat_password);
        //mContractor = (CheckBox) findViewById(R.id.contractor_checkbox);



        mNextButton = (Button) findViewById(R.id.next_button_register_activity);





        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mEmailAddressValue = mEmailAddress.getText().toString();
                mPasswordValue = mPassword.getText().toString();
                mRepeatPasswordValue = mRepeatPassword.getText().toString();

                //Passing arguements from Fragment to activity
                Bundle bundle = new Bundle();
                bundle.putString("emailAddress",mEmailAddressValue);
                bundle.putString("password",mPasswordValue);
                bundle.putString("repeatpassword",mRepeatPasswordValue);

                Fragment homeownerRegisterFragment = new RegisterHomeownerFragment();
                homeownerRegisterFragment.setArguments(bundle);


                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                    // Replace the contents of the container with the new fragment
                ft.add(R.id.register_activity_framelayout, homeownerRegisterFragment,
                        "UserProfileFragment");
                    // or ft.replace(R.id.your_placeholder, new FooFragment());
                    // Complete the changes added above

                ft.addToBackStack(null);
                ft.commit();


                //This here works
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.add(R.id.login_activity_frameLayout, new SignUpFragment(), "first_fragment");
//                ft.addToBackStack("back_to_main_activity").commit();
            }
        });

}

    @Override
    protected void onStart() {
        super.onStart();

    }

    /**
     * To check if user enters and confirms his/her password
     *
     * @param password
     * @param repeatPassword
     * @return boolean checking both passwords match for confirmation
     */
    public boolean checkPassWordAndConfirmPassword(String password, String repeatPassword) {
        boolean pstatus = false;
        if (repeatPassword != null && password != null) {
            if (password.equals(repeatPassword)) {
                pstatus = true;
            }
        }
        return pstatus;
    }

    /**
     * To check email confirming email pattern
     *
     * @param email
     * @return boolean checking email validation
     */
    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }







    /**
     * This register method is used for loggin in
     * @param email_address
     * @param password -> minimum 6 characters required upon testing to make sure user actually
     *                    registers
     */
//    public void register(String email_address, String password){
//
//        //once user is signed in, data is saved
//        mAuth.createUserWithEmailAndPassword(email_address, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
//                        // If sign in fails, display a message to the user. If sign in succeeds
//                        // the auth state listener will be notified and logic to handle the
//                        // signed in user can be handled in the listener.
//                        if (!task.isSuccessful()) {
//                            Toast.makeText(registerActivity.this, R.string.auth_failed,
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                        // [END_EXCLUDE]
//                    }
//                });
//    }


}
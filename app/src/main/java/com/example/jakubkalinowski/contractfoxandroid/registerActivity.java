package com.example.jakubkalinowski.contractfoxandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

//firebase deprecated library
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//firebase deprecated library

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;
import java.util.regex.Pattern;

public class registerActivity extends AppCompatActivity {

    private static final String TAG = "authListener_TAG!!" ;
    //Firebase Reference
  //  Firebase ref = new Firebase("https://contractfox.firebaseio.com/");
    FirebaseDatabase mFirebaseDatabaseReference = FirebaseDatabase.getInstance();


    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]





    //variables for all the components of the activity
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmailAddress;
    private EditText mPassword;
    private EditText mRepeatPassword;
    private EditText mAddress;
    private CheckBox mContractor;
    private Button mSignUpButton;

    //variables for extracting values from components
    private String firstNameInput;
    private String lastNameInput;
    private String emailInput;
    private String passwordInput;
    private String repeatPasswordInput;
    private String addressInput;
    private Boolean contractor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        mAuth = FirebaseAuth.getInstance();
        //this part is for hint animation
        TextInputLayout firstNameWrapper = (TextInputLayout) findViewById(R.id.first_name_textInput);
        TextInputLayout lastNameWrapper = (TextInputLayout) findViewById(R.id.last_name_textInput);
        TextInputLayout emailAddressWrapper = (TextInputLayout) findViewById(R.id.email_address_text_input);
        TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.password_textInput);
        TextInputLayout repeatPasswordWrapper = (TextInputLayout) findViewById(R.id.repeat_password_textInput);
        TextInputLayout addressWrapper = (TextInputLayout) findViewById(R.id.address_textInput);
        firstNameWrapper.setHint("First Name");
        lastNameWrapper.setHint("Last Name");
        emailAddressWrapper.setHint("Email Address");
        passwordWrapper.setHint("Password");
        repeatPasswordWrapper.setHint("Repeat Password");
        addressWrapper.setHint("Address");

    }

    @Override
    protected void onStart() {
        super.onStart();
        //get authStateListenerInfo
        mAuth.addAuthStateListener(mAuthListener);


        //initializing activity components
        mFirstName = (EditText) findViewById(R.id.first_name);
        mLastName = (EditText) findViewById(R.id.last_name);
        mEmailAddress = (EditText) findViewById(R.id.email_address);
        mPassword = (EditText) findViewById(R.id.password);
        mRepeatPassword = (EditText) findViewById(R.id.repeat_password);
        mAddress = (EditText) findViewById(R.id.address);
        mContractor = (CheckBox) findViewById(R.id.contractor_checkbox);
        mSignUpButton = (Button) findViewById(R.id.sign_up_button_register_activity);

        //setting hints for animation


        /**
         * Action for 'mSignUpButton'
         * mFirstName, mLastName etc. and Firebase will be dealt with here
         */

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * TASKS TO BE COMPLETED
                 * - implement password checker
                 * - implement regex checker
                 * - implement exisisting usernames checker
                 * -
                 */

                Toast.makeText(getApplicationContext(), "SIGN UP Button is clicked", Toast.LENGTH_SHORT).show();

                Boolean isExistingUsername = false;
                firstNameInput = mFirstName.getText().toString();
                lastNameInput = mLastName.getText().toString();
                repeatPasswordInput = mRepeatPassword.getText().toString();
                addressInput = mAddress.getText().toString();
                contractor = mContractor.isChecked();



                emailInput = mEmailAddress.getText().toString();
                passwordInput = mPassword.getText().toString();
                register(emailInput, passwordInput);
             //   final User newUser = new User(firstNameInput,lastNameInput,emailInput, passwordInput, addressInput, contractor);

//                ref.createUser(newUser.getEmailAddress(), newUser.getPassword(), new Firebase.ValueResultHandler<Map<String, Object>>() {
//                    @Override
//                    public void onSuccess(Map<String, Object> result) {
//                        Toast.makeText(getApplicationContext(), "account created", Toast.LENGTH_LONG).show();
//                        /**
//                         * backup method if boolean throws issues
//                         Map <String, String> newUserMap = new HashMap<String, String>();
//                         newUserMap.put("firstName", newUser.getFirstName());
//                         newUserMap.put("lastName", newUser.getLastName());
//                         newUserMap.put("emailAddress", newUser.getEmailAddress());
//                         newUserMap.put(,"password", newUser.getPassword());
//                         newUserMap.put("address", newUser.getAddress());
//                         newUserMap.put("contractor", newUser.getContractor().toString());
//                         ref.child("users").child((String) result.get("uid")).setValue(newUserMap);
//                         *
//                         */
//                        ref.child("users").child((String) result.get("uid")).setValue(newUser);
//                        Snackbar snackbar = Snackbar.make((LinearLayout) findViewById(R.id.sign_up_layout_id), "Profile Created", Snackbar.LENGTH_LONG);
//                        snackbar.show();
//                    }
//                    @Override
//                    public void onError(FirebaseError firebaseError) {
//                        Log.e("ERROR TAG", "didnt work but got through firebase reference!!!: " + emailInput);
//                    }
//                });
             //   Intent i = new Intent(registerActivity.this, DrawerActivity.class);
              //  startActivity(i);
        }
        });


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

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



    //user gets registered using user's email and password, then data saving gets handled
    //callback declared in the onCreateMethod
    public void register(String email_address, String password){

        mAuth.createUserWithEmailAndPassword(email_address, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(registerActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [END_EXCLUDE]
                    }
                });
    }


}
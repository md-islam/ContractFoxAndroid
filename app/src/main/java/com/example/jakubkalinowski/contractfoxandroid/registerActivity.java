package com.example.jakubkalinowski.contractfoxandroid;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.CheckBox;
import android.widget.EditText;

import com.firebase.client.Firebase;

import java.util.regex.Pattern;

public class registerActivity extends AppCompatActivity {

    //Firebase Reference
    Firebase ref = new Firebase("https://contractfox.firebaseio.com/");

    //variables for all the components of the activity
    private EditText mEmailAddress;
    private EditText mPassword;
    private EditText mRepeatPassword;
    private CheckBox mContractor;

    //variables for extracting values from components
    private String emailInput;
    private String passwordInput;
    private String repeatPasswordInput;
    private Boolean contractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //this part is for hint animation
        TextInputLayout emailAddressWrapper = (TextInputLayout) findViewById(R.id.email_address_text_input);
        TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.password_textInput);
        TextInputLayout repeatPasswordWrapper = (TextInputLayout) findViewById(R.id.repeat_password_textInput);
        emailAddressWrapper.setHint("Email Address");
        passwordWrapper.setHint("Password");
        repeatPasswordWrapper.setHint("Repeat Password");

    }

    @Override
    protected void onStart() {
        super.onStart();
        //initializing activity components
        mEmailAddress = (EditText) findViewById(R.id.email_address);
        mPassword = (EditText) findViewById(R.id.password);
        mRepeatPassword = (EditText) findViewById(R.id.repeat_password);
        mContractor = (CheckBox) findViewById(R.id.contractor_checkbox);


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

    // OLD STUFF BEFORE MD'S CHANGES

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//
//        //this part is for hint animation
//        TextInputLayout firstNameWrapper = (TextInputLayout) findViewById(R.id.first_name_textInput);
//        TextInputLayout lastNameWrapper = (TextInputLayout) findViewById(R.id.last_name_textInput);
//        TextInputLayout emailAddressWrapper = (TextInputLayout) findViewById(R.id.email_address_text_input);
//        TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.password_textInput);
//        TextInputLayout repeatPasswordWrapper = (TextInputLayout) findViewById(R.id.repeat_password_textInput);
//        TextInputLayout addressWrapper = (TextInputLayout) findViewById(R.id.address_textInput);
//        firstNameWrapper.setHint("First Name");
//        lastNameWrapper.setHint("Last Name");
//        emailAddressWrapper.setHint("Email Address");
//        passwordWrapper.setHint("Password");
//        repeatPasswordWrapper.setHint("Repeat Password");
//        addressWrapper.setHint("Address");
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Firebase.setAndroidContext(this);
//
//        //initializing activity components
//        mFirstName = (EditText) findViewById(R.id.first_name);
//        mLastName = (EditText) findViewById(R.id.last_name);
//        mEmailAddress = (EditText) findViewById(R.id.email_address);
//        mPassword = (EditText) findViewById(R.id.password);
//        mRepeatPassword = (EditText) findViewById(R.id.repeat_password);
//        mAddress = (EditText) findViewById(R.id.address);
//        mContractor = (CheckBox) findViewById(R.id.contractor_checkbox);
//        mSignUpButton = (Button) findViewById(R.id.sign_up_button_register_activity);
//
//        //setting hints for animation
//
//
//        /**
//         * Action for 'mSignUpButton'
//         * mFirstName, mLastName etc. and Firebase will be dealt with here
//         */
//
//        mSignUpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                /**
//                 * TASKS TO BE COMPLETED
//                 * - implement password checker
//                 * - implement regex checker
//                 * - implement exisisting usernames checker
//                 * -
//                 */
//
//                Toast.makeText(getApplicationContext(), "SIGN UP Button is clicked", Toast.LENGTH_LONG).show();
//
//                Boolean isExistingUsername = false;
//                firstNameInput = mFirstName.getText().toString();
//                lastNameInput = mLastName.getText().toString();
//                emailInput = mEmailAddress.getText().toString();
//                passwordInput = mPassword.getText().toString();
//                repeatPasswordInput = mRepeatPassword.getText().toString();
//                addressInput = mAddress.getText().toString();
//                contractor = mContractor.isChecked();
//
//
//                final User newUser = new User(firstNameInput,lastNameInput,emailInput, passwordInput, addressInput, contractor);
//
//                ref.createUser(newUser.getEmailAddress(), newUser.getPassword(), new Firebase.ValueResultHandler<Map<String, Object>>() {
//                    @Override
//                    public void onSuccess(Map<String, Object> result) {
//                        Toast.makeText(getApplicationContext(), "account created", Toast.LENGTH_LONG).show();
//
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
//
//
//
//                        ref.child("users").child((String) result.get("uid")).setValue(newUser);
//
//                        Snackbar snackbar = Snackbar.make((LinearLayout) findViewById(R.id.sign_up_layout_id), "Profile Created", Snackbar.LENGTH_LONG);
//                        snackbar.show();
//
//                    }
//
//                    @Override
//                    public void onError(FirebaseError firebaseError) {
//
//                        Log.e("ERROR TAG", "didnt work but got through firebase reference!!!: " + emailInput);
//                    }
//                });
//
//                // register button transfers to main screen
//                Intent i = new Intent(registerActivity.this, DrawerActivity.class);
//                //i.putExtra("firebaseURL", "https://allmythings2016.firebaseio.com/");
////                i.putExtra("userEmail", email);
//                startActivity(i);
//
//
//            }
//        });
//
//
//    }
//
//    /**
//     * To check if user enters and confirms his/her password
//     *
//     * @param password
//     * @param repeatPassword
//     * @return boolean checking both passwords match for confirmation
//     */
//    public boolean checkPassWordAndConfirmPassword(String password, String repeatPassword) {
//        boolean pstatus = false;
//        if (repeatPassword != null && password != null) {
//            if (password.equals(repeatPassword)) {
//                pstatus = true;
//            }
//        }
//        return pstatus;
//    }
//
//    /**
//     * To check email confirming email pattern
//     *
//     * @param email
//     * @return boolean checking email validation
//     */
//    private boolean isValidEmail(String email) {
//        Pattern pattern = Patterns.EMAIL_ADDRESS;
//        return pattern.matcher(email).matches();
//    }
//}
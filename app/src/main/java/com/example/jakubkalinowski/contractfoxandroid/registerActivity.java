package com.example.jakubkalinowski.contractfoxandroid;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.jakubkalinowski.contractfoxandroid.interfaces.Communicator;

import java.util.regex.Pattern;

public class registerActivity extends AppCompatActivity implements Communicator {


    private android.support.v7.widget.Toolbar topToolbar;

    //variables for all the components of the activity
    private EditText mEmailAddress;
    private EditText mPassword;
    private EditText mRepeatPassword;
    private Toolbar toolbar;
    private TextView mToolBarTextViewTitle;
    private TextInputLayout emailAddressWrapper;
    private TextInputLayout passwordWrapper;
    private TextInputLayout repeatPasswordWrapper;

    //string values
    private String mEmailAddressValue;
    private String mPasswordValue;
    private String mRepeatPasswordValue;

    private Button mNextButton;

    //variables for extracting values from components
    private Boolean mContractorBoolean = false;


    //fragment Manager global variable
    FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = (Toolbar)findViewById(R.id.register_activity_toolbar);
        mToolBarTextViewTitle = (TextView) findViewById(R.id.toolBar_register_activity_textView);
        setTopToolBar("Sign Up");
        //this part is for hint animation
        emailAddressWrapper = (TextInputLayout) findViewById(R.id.email_address_text_input);
        passwordWrapper = (TextInputLayout) findViewById(R.id.password_textInput);
        repeatPasswordWrapper = (TextInputLayout) findViewById(R.id.repeat_password_textInput);
        emailAddressWrapper.setHint("Email Address");
        passwordWrapper.setHint("Password");
        repeatPasswordWrapper.setHint("Repeat Password");

        //initializing activity components
        mEmailAddress = (EditText) findViewById(R.id.email_address);
        mPassword = (EditText) findViewById(R.id.password);
        mRepeatPassword = (EditText) findViewById(R.id.repeat_password);
        mNextButton = (Button) findViewById(R.id.next_button_register_activity);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         submitForm();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();



    }





    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton_homewoner_register_activity:
                if (checked) {
                    mContractorBoolean = false;
                }
                break;
            case R.id.radioButton_contractor_register_activity:
                if (checked) {
                    mContractorBoolean = true;
                }
                break;
        }
    }


    /**
     * This data is recieved from Contractor Signup  and HomeOwner signup Fragments respectively
     * and sends it back to Address Fragment
     */
    @Override
    public void respond(Bundle recievedBundle, String fragmentTag) {

        Fragment AddressRegisterFragment = new Address_Fragment();
        AddressRegisterFragment.setArguments(recievedBundle);

        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.addToBackStack(null);
        if (fragmentTag.equals("ContractorRegisterProfileFragmentTAG")){
            ft.replace(R.id.contractor_fragment_register_framelayout, AddressRegisterFragment
                    , "AddressFragment");
            ft.commit();
        }else if(fragmentTag.equals("HomeownerRegisterProfileFragmentTAG")){
            ft.replace(R.id.homeowner_fragment_register_framelayout,
                    AddressRegisterFragment, "AddressFragment");
            ft.commit();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    /**
     * This is necessary because for when a navigation is done from fragment to parent activity.
     * This pethod is called from a child fragment (or a the last fragment added to the stack)
     */
    public void setTopToolBar(String text){

        setSupportActionBar(toolbar);
        mToolBarTextViewTitle.setText(text);
    }



    //==========FORM VALIDATION SECTION==============// [START]


    public void submitForm(){
        if(!validateEmail()){
            return;
        }
        if(!validatePassword()){
            return;
        }

        mEmailAddressValue = mEmailAddress.getText().toString();
        mPasswordValue = mPassword.getText().toString();
        mRepeatPasswordValue = mRepeatPassword.getText().toString();

        //Passing arguements from Fragment to activity
        Bundle bundle = new Bundle();
        bundle.putString("emailAddress", mEmailAddressValue);
        bundle.putString("password", mPasswordValue);
        bundle.putString("repeatpassword", mRepeatPasswordValue);
        bundle.putBoolean("typeBoolean", mContractorBoolean);


        if (mContractorBoolean == true) {

            Fragment contractorRegisterFragment = new RegisterContractorFragment();
            contractorRegisterFragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.addToBackStack("ContractorRegisterProfileFragment");
            ft.replace(R.id.register_activity_framelayout, contractorRegisterFragment,
                    "ContractorRegisterProfileFragmentTAG");
            ft.commit();

        } else {

            Fragment homeownerRegisterFragment = new RegisterHomeownerFragment();
            homeownerRegisterFragment.setArguments(bundle);
            // Begin the transaction
            mFragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
            ft.addToBackStack("HomeownerRegisterProfileFragment");
            ft.replace(R.id.register_activity_framelayout, homeownerRegisterFragment,
                    "HomeownerRegisterProfileFragmentTAG");
            // or ft.replace(R.id.your_placeholder, new FooFragment());
            // Complete the changes added above
            ft.commit();

        }
    }


    private boolean validateEmail() {
        String email = mEmailAddress.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {
            emailAddressWrapper.setError(getString(R.string.register_activity_email_error));
            requestFocus(mEmailAddress);
            return false;
        } else {
            emailAddressWrapper.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword(){
        String password = mPassword.getText().toString().trim();
        String repeatPassword = mRepeatPassword.getText().toString().trim();
        if(password.isEmpty() || repeatPassword.isEmpty() || !isValidPassword(password,
                repeatPassword)){
            passwordWrapper.setError(getString(R.string.register_activity_password_error));
            repeatPasswordWrapper.setError(getString(R.string
                    .register_activity_repeat_password_error));
            requestFocus(mPassword);
            return false;
        }else{
            passwordWrapper.setErrorEnabled(false);
            repeatPasswordWrapper.setErrorEnabled(false);
        }

        return true;
    }

    /**
     * To check if user enters and confirms his/her password
     *
     * @param password
     * @param repeatPassword
     * @return boolean checking both passwords match for confirmation
     */
    public boolean isValidPassword(String password, String repeatPassword) {
        boolean pstatus = false;
        if (repeatPassword != null && password != null) {


            if (password.length() >= 6 && password.equals(repeatPassword)) {
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
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.
                EMAIL_ADDRESS.matcher(email).matches();
    }



    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.email_address:
                    validateEmail();
                    break;
                case R.id.password:
                    validatePassword();
                    break;
                case R.id.repeat_password:
                    validatePassword();
                    break;
            }
        }
    }

    //==========FORM VALIDATION SECTION==============// [END]






}
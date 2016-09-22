package com.example.jakubkalinowski.contractfoxandroid;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    //Firebase Reference
    //Firebase ref = new Firebase("https://contractfox.firebaseio.com/");

    //variables for all the components of the activity
    private EditText mEmailAddress;
    private EditText mPassword;
    private Button mSignInButton;
    private Button mRegisterButton;
    TextView forgotPass;
    final private LinearLayout.LayoutParams etm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);



    //variables for extracting values from components
    private String emailInput;
    private String passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        forgotPass = (TextView) findViewById(R.id.forgotPassword);
        TextInputLayout emailAddressWrapper = (TextInputLayout) findViewById(R.id.email_wrapper_login_activity);
        TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.password_wrapper_login_activity);
        emailAddressWrapper.setHint("Email");
        passwordWrapper.setHint("Password");
        //Firebase.setAndroidContext(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        etm.setMargins(50,50,50,50);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendResetPassword();
            }
        });
    }

    private void sendResetPassword(){

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
        alertDialog.setTitle("Reset Password");
        alertDialog.setMessage("Enter your email and phone number to receive a temporary password.");
        LinearLayout linear = new LinearLayout(getApplicationContext());
//        TextView textEmail = new TextView(getApplicationContext());
//        textEmail.setText("Email: ");
        EditText editEmail = new EditText(getApplicationContext());
        editEmail.setHint("Email");
        editEmail.setHintTextColor(0xFFBCBCBC);
        editEmail.setTextColor(0xFFBCBCBC);

        EditText editPhone = new EditText(getApplicationContext());
        editPhone.setHint("Phone");
        editPhone.setHintTextColor(0xFFBCBCBC);
        editPhone.setTextColor(0xFFBCBCBC);

        editEmail.setLayoutParams(etm);
        editPhone.setLayoutParams(etm);

//        Button send = new Button(getApplicationContext());
//        send.setText("Submit");
        alertDialog.setNegativeButton("Submit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        //send.setLayoutParams(etm);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.addView(editEmail);
        linear.addView(editPhone);
        // linear.addView(send);
        alertDialog.setView(linear);
        alertDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Firebase.setAndroidContext(this);
        mEmailAddress = (EditText)findViewById(R.id.email_text_input_login_activity);
        mPassword = (EditText)findViewById(R.id.password_textInput_login_activity);
        mSignInButton = (Button) findViewById(R.id.sign_in_button_login_activity);
        mRegisterButton = (Button) findViewById(R.id.register_button_login_activity);

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmailAddress.getText().toString();
                String password = mPassword.getText().toString();

//                ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
//
//                    @Override
//                    public void onAuthenticated(AuthData authData) {
//                        Log.d("LOGIN TAG!!!!!!!", "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
//                        Toast.makeText(getApplicationContext(), "LOG IN SUCCESSFUL!!!!", Toast.LENGTH_LONG).show();
//
//                        //storing user data
////                        Map<String, String> map = new HashMap<String, String>();
////                        map.put("provider", authData.getProvider());
////                        if (authData.getProviderData().containsKey("displayName")) {
////                            map.put("displayName", authData.getProviderData().get("displayName").toString());
////                        }
////                        FBref.child("users").child(authData.getUid()).setValue(map);
//                        //
//
//                        Intent i = new Intent(LoginActivity.this, DrawerActivity.class);
//                        //i.putExtra("firebaseURL", "https://allmythings2016.firebaseio.com/");
//                        i.putExtra("userEmail", email);
//                        startActivity(i);
//                    }
//
//                    @Override
//                    public void onAuthenticationError(FirebaseError firebaseError) {
//                        // there was an error
//                        Toast.makeText(getApplicationContext(), "LOG IN UNSUCCESSFUL!!!!", Toast.LENGTH_LONG).show();
//                        Log.e("ERROR TAG", "didnt work but got through firebase reference!!!: ");
//                    }
//                });
            }

        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startSignUp = new Intent(LoginActivity.this, registerActivity.class);
                startActivity(startSignUp);
            }
        });

    }


}

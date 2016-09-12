package com.example.jakubkalinowski.contractfoxandroid;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterHomeownerFragment extends Fragment {
    //textWrappers
    private TextInputLayout mFirstNameWrapper;
    private TextInputLayout mLastNameWrapper;
    private TextInputLayout mPhoneWrapper;
    private TextInputLayout mEmailWrapper;
    private TextInputLayout mPasswordWrapper;


    //inputTexts
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mPhoneEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;


    public RegisterHomeownerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Homeowner Profile");
        return inflater.inflate(R.layout.fragment_register_homeowner, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFirstNameWrapper = (TextInputLayout) view.
                findViewById(R.id.first_name_textInput_wrapper_homeowner_register_fragment);
        mLastNameWrapper = (TextInputLayout) view.
                findViewById(R.id.last_name_textInput_wrapper_fragment_homeownerRegister);
        mPhoneWrapper = (TextInputLayout) view.
                findViewById(R.id.phone_Input_wrapper_fragment_homeownerRegister);
        mEmailWrapper = (TextInputLayout) view.
                findViewById(R.id.email_Input_wrapper_fragment_homeownerRegister);
        mPasswordWrapper = (TextInputLayout) view.findViewById(R.id
                                .password_wrapper_homeowner_register_fragment);

        mFirstNameWrapper.setHint("First Name");
        mLastNameWrapper.setHint("Last Name");
        mPhoneWrapper.setHint("Phone");

        mEmailWrapper.setHint("Email Address");
        mPasswordWrapper.setHint("Password");


        mFirstNameEditText = (EditText) view.findViewById(R.
                id.first_name_edit_text_fragment_homeowner_register);
        mLastNameEditText = (EditText) view.findViewById(R.id.
                last_name_edittextfield_fragment_homeOwnerRegister_fragment);
        mPhoneEditText = (EditText) view.findViewById(R.id.
                phone_edittextfield_fragment_homeOwnerRegister_fragment);
        mEmailEditText = (EditText) view.findViewById(R.id.
                email_edittextfield_fragment_homeOwnerRegister_fragment);
        mPasswordEditText = (EditText) view.
                findViewById(R.id.password_textInput_homeowner_register_fragment);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

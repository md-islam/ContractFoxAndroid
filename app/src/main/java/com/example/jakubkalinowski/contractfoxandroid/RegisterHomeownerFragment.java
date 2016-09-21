package com.example.jakubkalinowski.contractfoxandroid;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterHomeownerFragment extends Fragment {
    //textWrappers
    private TextInputLayout mFirstNameWrapper;
    private TextInputLayout mLastNameWrapper;
    private TextInputLayout mPhoneWrapper;

    //inputTexts
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mPhoneEditText;


    //buttons
    private Button mNextButton;

    //imageView
    private CircleImageView mCircleProfileImageView;
    private Bitmap mProfileImageBitmap;


    //string values
    private String mEmailValueFromPrevious;
    private String mPasswordValueFromPrevious;
    private String mFirstNameValue;
    private String mLastNameValue;
    private String mPhoneValue;

    public RegisterHomeownerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Homeowner Profile");

        mEmailValueFromPrevious = getArguments().getString("emailAddress");
        mPasswordValueFromPrevious = getArguments().getString("password");

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

        mFirstNameWrapper.setHint("First Name");
        mLastNameWrapper.setHint("Last Name");
        mPhoneWrapper.setHint("Phone");

        mNextButton = (Button) view.findViewById(R.id.next_button_fragment_homeowner_register);

        mCircleProfileImageView = (CircleImageView)view.
                findViewById(R.id.profile_image_homeowner_fragment);

        mFirstNameEditText = (EditText) view.findViewById(R.
                id.first_name_edit_text_fragment_homeowner_register);
        mLastNameEditText = (EditText) view.findViewById(R.id.
                last_name_edittextfield_fragment_homeOwnerRegister_fragment);
        mPhoneEditText = (EditText) view.findViewById(R.id.
                phone_edittextfield_fragment_homeOwnerRegister_fragment);




        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //handle profile image

                byte[] profileImageBytedata = getCircleImageViewByteData();
                mFirstNameValue = mFirstNameEditText.getText().toString();
                mLastNameValue = mLastNameEditText.getText().toString();
                mPhoneValue = mPhoneEditText.getText().toString();

                //[Setting the bundle of arguements to pass to address]-START
                Bundle bundleToPass = new Bundle();
                bundleToPass.putString("emailAddress", mEmailValueFromPrevious);
                bundleToPass.putString("password", mPasswordValueFromPrevious);
                bundleToPass.putString("firstname",mFirstNameValue);
                bundleToPass.putString("lastname", mLastNameValue);
                bundleToPass.putString("phone", mPhoneValue);
                bundleToPass.putByteArray("profileImageData",profileImageBytedata);

                Fragment AddressRegisterFragment = new Address_Fragment();
                AddressRegisterFragment.setArguments(bundleToPass);
                //[Setting the bundle of arguements to pass to address]-END


                //[Setting up the Address Fragment] - START
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.homeowner_fragment_register_framelayout,
                        AddressRegisterFragment,"PrimaryAddressForHomeowner");
                ft.addToBackStack(null);
                ft.commit();
                //[Setting up the Address Fragment] - END
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public byte[] getCircleImageViewByteData(){

        mCircleProfileImageView.setDrawingCacheEnabled(true);
        mCircleProfileImageView.buildDrawingCache();
        mProfileImageBitmap = mCircleProfileImageView.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        mProfileImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        return data;
    }
}
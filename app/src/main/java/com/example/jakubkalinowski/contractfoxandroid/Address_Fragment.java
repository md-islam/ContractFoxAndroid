package com.example.jakubkalinowski.contractfoxandroid;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class Address_Fragment extends Fragment {

    //textWrappers
    private TextInputLayout mStreetAddressWrapper;
    private TextInputLayout mUnitAptWrapper;
    private TextInputLayout mCityWrapper;
    private TextInputLayout mStateWrapper;
    private TextInputLayout mZipWrapper;

    //inputTexts
    private EditText mStreetAddressEditText;
    private EditText mUnitAptEditText;
    private EditText mCityEditText;
    private EditText mStateEditText;
    private EditText mZipEditText;


    //registerButton
    private Button mRegisterButton;

    //Strings from previous framgents
    private String mEmailValueFromPrevious;
    private String mPasswordValueFromPrevious;
    private String mFirstNameValueFromPrevious;
    private String mLastNameValueFromPrevious;
    private String mPhoneValueFromPrevious;
    private byte[] mBytesArrayFromPrevious;


    //Strings from currentFragment
    private String mStreetValue;
    private String mUnitAptValue;
    private String mCityValue;
    private String mStateValue;
    private String mZipValue;


    private String TAG = "FirebaseTag";




    //FIREBASE ESSENTIALS to login
    //auth instance variable
    private FirebaseAuth mAuth;

    //listener to listen to authState Changes
    private FirebaseAuth.AuthStateListener mAuthListener;

    //reference to realtime database
    private DatabaseReference mDatabase;


    //reference to storage database
    private FirebaseStorage mStorage;
    //refference to profilepicture storage
    private StorageReference mProfileImageStorageRef;



    public Address_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //get them bundle values here.
        mEmailValueFromPrevious = getArguments().getString("emailAddress");
        mPasswordValueFromPrevious = getArguments().getString("password");
        mFirstNameValueFromPrevious = getArguments().getString("firstname");
        mLastNameValueFromPrevious = getArguments().getString("lastname");
        mPhoneValueFromPrevious = getArguments().getString("phone");
        mBytesArrayFromPrevious = getArguments().getByteArray("profileImageData");

        return inflater.inflate(R.layout.fragment_address_layout, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mStreetAddressWrapper = (TextInputLayout)
                view.findViewById(R.id.street_address_wrapper_fragment_address_homeowner);
        mUnitAptWrapper = (TextInputLayout)
                view.findViewById(R.id.unit_apt_wrapper_register_address_homeowner_fragment);
        mCityWrapper = (TextInputLayout)
                view.findViewById(R.id.city_wrapper_fragment_homeownerRegisterAddress);
        mStateWrapper = (TextInputLayout)
                view.findViewById(R.id.state_wrapper_fragment_homeownerRegisterAddress);
        mZipWrapper = (TextInputLayout)
                view.findViewById(R.id.zipCode_wrapper_fragment_homeownerRegisterAddress);

        mStreetAddressWrapper.setHint("Street Address");
        mUnitAptWrapper.setHint("Unit/Apt #");
        mStateWrapper.setHint("State");
        mCityWrapper.setHint("City");
        mZipWrapper.setHint("Zip code");

        mRegisterButton = (Button)
                view.findViewById(R.id.sign_up_button_registerHomeownerAddressButton);

        mStreetAddressEditText = (EditText)
                view.findViewById(R.id.street_address_edit_text_fragment_homeowner_register_address);
        mUnitAptEditText = (EditText)
                view.findViewById(R.id.unit_apt_editText_register_address_homeowner_fragment);
        mCityEditText = (EditText)
                view.findViewById(R.id.city_edittextfield_fragment_homeOwnerAddress_fragment);
        mStateEditText = (EditText)
                view.findViewById(R.id.state_edittextfield_fragment_homeOwnerAddress_fragment);
        mZipEditText = (EditText)
                view.findViewById(R.id.zipCode_edittextfield_fragment_homeOwnerRegister_fragment);


        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //public Address(String streetAddress, String city, String state, String zipCode, String unit_Apt_no)
                mStreetValue = mStreetAddressEditText.getText().toString();
                mUnitAptValue = mUnitAptEditText.getText().toString();
                mCityValue = mCityEditText.getText().toString();
                mStateValue = mStateEditText.getText().toString();
                mZipValue = mZipEditText.getText().toString();


                //needs a null handler here


                mAuth.createUserWithEmailAndPassword(mEmailValueFromPrevious,mPasswordValueFromPrevious)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getActivity(), R.string.auth_failed,
                                            Toast.LENGTH_SHORT).show();
                                }
                                else{

                                }



                                // ...
                            }
                        });



            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         //gets firebase current authorization instance, can use this to get current user
        mAuth = FirebaseAuth.getInstance();

        //Firebase realtime Database Reference
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Firebase storage reference
        mStorage = FirebaseStorage.getInstance();

        //
        mProfileImageStorageRef = mStorage.getReferenceFromUrl
                ("gs://contract-fox.appspot.com/users/");




        //setting the authlister always listening for changes
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in after first time creation
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Address address = new Address(mStreetValue, mUnitAptValue,
                            mCityValue, mStateValue, mZipValue);
//                    Member new_member = new Contractor(firstNameInput, lastNameInput, emailInput,
//                            null,addressInput, contractor, "Example Description",
//                            "Example Specialization", 22,
//                            new Address("501 Murphy Ranch","Milpitas","State","95035","106"),
//                            skillset);
                    String signedIn_userID_key = user.getUid().toString();
                    Member new_member = new Homeowner(mFirstNameValueFromPrevious,
                            mLastNameValueFromPrevious, mEmailValueFromPrevious,
                            mPhoneValueFromPrevious, false, address);
                    mDatabase.child("contractors").child(signedIn_userID_key).
                            setValue(new_member);



                    //This section of code uploads picture to authorized user--[START]
                    mProfileImageStorageRef = mStorage.getReferenceFromUrl
                            ("gs://contract-fox.appspot.com/users/"+signedIn_userID_key+
                                    "/profilePicture.jpg");


                    //2 lines of code below essentially uploads image to firebase.
                    UploadTask uploadTask = mProfileImageStorageRef.
                            putBytes(mBytesArrayFromPrevious);

                    //Attaching callback methods listening for failures and successes
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        }
                    });

                    //This section of code uploads pictures to authorized user--[END]



                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(mAuthListener);
    }
}

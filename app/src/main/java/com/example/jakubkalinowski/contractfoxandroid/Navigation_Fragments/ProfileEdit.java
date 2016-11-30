package com.example.jakubkalinowski.contractfoxandroid.Navigation_Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.jakubkalinowski.contractfoxandroid.Contractor;
import com.example.jakubkalinowski.contractfoxandroid.Homeowner;
import com.example.jakubkalinowski.contractfoxandroid.Member;
import com.example.jakubkalinowski.contractfoxandroid.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interfaces
 * to handle interaction events.
 * Use the {@link ProfileEdit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileEdit extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //intent set up for gallery pick
    private Intent mPickImageIntent;
    private static int IMG_RESULT = 1;
    private String option;

    String param = "kj";
    private static final String TAG = "Firebase_TAG!!" ;

    //[Firebase_variable]**
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener; //signed_in state listener object

    private static DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance()
            .getReference();

    private StorageReference mProfilePicPath;
    private StorageReference mLogoImgPath;

    private OnFragmentInteractionListener mListener;
    private Member m;

    // Storage Permissions variables--This is requird for android 6.0 Marshmallow
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    //UI component variables [start]
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mAddressEditText;
    private EditText mCityEditText;
    private EditText mPostalCodeEditText;
    private EditText mPhoneNumberEditText;
    private EditText mCompanyNameEditText;
    private EditText mWebsiteURLEditText;

    private CircleImageView mProfilePic;
    private CircleImageView mLogoImg;
    private Bitmap mProfilePicBitmap;
    private Bitmap mLogoImageBitmap;



    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference profileRef;
    private StorageReference logoRef;
    // integer for request code
    private static final int GALLERY_INTENT = 2;

    //UI components [END]
    public ProfileEdit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileEdit.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileEdit newInstance(String param1, String param2) {
        ProfileEdit fragment = new ProfileEdit();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://contract-fox.appspot.com");





        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    byte[] profilePictureBytedata = getCircleProfilePictureViewByteData();
                    mProfilePicPath = FirebaseStorage.getInstance().getReference("ProfilePictures").child(user.getUid().toString()).child("profilepic.jpeg");
                    mProfilePicPath.putBytes(profilePictureBytedata);
//
                    byte[] logoImageBytedata = getCircleLogoImageViewByteData();
                    mLogoImgPath = FirebaseStorage.getInstance().getReference("LogoImages").child(user.getUid().toString()).child("logoimg.jpeg");
                    mLogoImgPath.putBytes(logoImageBytedata);

                    // Profile info edit
                    mFirebaseDatabaseReference
                            .child("users").child(user.getUid().toString())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.child("contractorOption").getValue().equals(true)){

                                        //need null handlers here
                                        Contractor m = (Contractor)dataSnapshot.getValue(Contractor.class);
                                        mFirstNameEditText.setText(m.getFirstName());
                                        mLastNameEditText.setText(m.getLastName());
                                        mAddressEditText.setText(m.getAddress().toString());
                                        mCityEditText.setText(m.getAddress().getCity());
                                        mPostalCodeEditText.setText(m.getAddress().getZipCode());
                                        mPhoneNumberEditText.setText(m.getPhoneNo());
                                        mCompanyNameEditText.setText(m.getBusinessWebsiteURL());
                                        mWebsiteURLEditText.setText(m.getEmailAddress());
                                    }
                                    else{
                                        Homeowner m = (Homeowner)dataSnapshot.getValue(Homeowner.class);

                                        mFirstNameEditText.setText(m.getFirstName());
                                        mLastNameEditText.setText(m.getLastName());
                                        mAddressEditText.setText(m.getAddress().toString());
                                        mCityEditText.setText(m.getAddress().getCity());
                                        mPostalCodeEditText.setText(m.getAddress().getZipCode());
                                        mPhoneNumberEditText.setText(m.getPhoneNo());
                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //these fields are declared as global variables to be used in callback functions
        //for firebase data retrievl. If possible, find a way for data retrieval to happen
        // before oncreateVIew.
        View root = inflater.inflate(R.layout.fragment_profile_edit, container, false);
        //mFirstNameEditText.setText(mFirstName_Textbox_Value);
        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interfaces must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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

//    public void setFirstName(String uid){
//
//
//        //there is a callback function inside here. To use outside variables inside the callback
//        //functions, the variable inside should be either final or global variable.
//        //Still trying to figure out the best way to handle callback functions.
//        mFirebaseDatabaseReference
//                .child("contractors").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Member m = dataSnapshot.getValue(Contractor.class);
//                firstName.setText(m.getEmailAddress());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFirstNameEditText = (EditText) view.
                findViewById(R.id.firstName_editProfile_Fragment);
        mLastNameEditText = (EditText) view.findViewById(R.id.lasttName_editProfile_Fragment);
        mAddressEditText = (EditText) view.findViewById(R.id.address_editProfile_Fragment);
        mCityEditText = (EditText) view.findViewById(R.id.city_editProfile_Fragment);
        mPostalCodeEditText = (EditText)
                view.findViewById(R.id.postal_code_editProfile_fragment);
        mPhoneNumberEditText = (EditText)
                view.findViewById(R.id.phone_edit_profile_fragment);

        mCompanyNameEditText = (EditText)
                view.findViewById(R.id.company_name_editText_editProfile_fragment);
        mWebsiteURLEditText = (EditText)
                view.findViewById(R.id.website_url_editText_editProfile_fragment);

        mProfilePic = (CircleImageView) view.findViewById(R.id.upload_pic_btn);
        mLogoImg = (CircleImageView) view.findViewById(R.id.upload_logo_btn);

        // Profile Photo & Logo upload
        mProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_PICK);
//
//                i.setType("image/*");
//
//                startActivityForResult(i, GALLERY_INTENT);
                verifyStoragePermissions(getActivity());
                showEditProfilePictureDialog();
            }
        });

        mLogoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyStoragePermissions(getActivity());
                showEditLogoImageDialog();
            }
        });

    }

    //This piece of code gets ByteArrayData from whatever the image circle IS//still needs
    // refinement
    public byte[] getCircleProfilePictureViewByteData() {

        mProfilePic.setDrawingCacheEnabled(true);
        mProfilePic.buildDrawingCache();
        mProfilePicBitmap = mProfilePic.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        mProfilePicBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data1 = baos.toByteArray();
        return data1;
    }
    public byte[] getCircleLogoImageViewByteData() {

        mLogoImg.setDrawingCacheEnabled(true);
        mLogoImg.buildDrawingCache();
        mLogoImageBitmap = mLogoImg.getDrawingCache();
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        mLogoImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos2);
        byte[] data2 = baos2.toByteArray();
        return data2;
    }

    //This piece of code pops up the dialog window
    //Found from https://github.com/afollestad/material-dialogs
    public void showEditProfilePictureDialog() {
        option = "profile";
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext())
                .title("Set Profile Picture").positiveText("CAMERA").neutralText("DEVICE STORAGE")
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        mPickImageIntent = new Intent(Intent.ACTION_PICK, MediaStore
                                .Images.Media.EXTERNAL_CONTENT_URI);
                        mPickImageIntent.putExtra("option", option);
                        startActivityForResult(mPickImageIntent, IMG_RESULT);
                    }
                });
        MaterialDialog md = builder.build();
        md.show();
    }
    public void showEditLogoImageDialog() {
        option = "logo";
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext())
                .title("Set Logo Image").positiveText("CAMERA").neutralText("DEVICE STORAGE")
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        mPickImageIntent = new Intent(Intent.ACTION_PICK, MediaStore
                                .Images.Media.EXTERNAL_CONTENT_URI);
                        mPickImageIntent.putExtra("option", option);
                        startActivityForResult(mPickImageIntent, IMG_RESULT);
                    }
                });
        MaterialDialog md = builder.build();
        md.show();
    }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

//            if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
//                final Uri uri = data.getData();
//
//                if (option.equals("profile")) {
//                    mProfilePicPath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Glide.with(getActivity())
//                                    .using(new FirebaseImageLoader())
//                                    .load(mProfilePicPath)
//                                    .into(mProfilePic);
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                        }
//                    });
//                }
//                else {
//                    mLogoImgPath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Glide.with(getActivity())
//                                    .using(new FirebaseImageLoader())
//                                    .load(mLogoImgPath)
//                                    .into(mLogoImg);
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                        }
//                    });
//                }
//            }
//        }



            try {
                if (requestCode == IMG_RESULT && resultCode == Activity.RESULT_OK && null != data) {
                    Uri URI = data.getData();
                    String[] FILE = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getActivity().getContentResolver().query(URI,
                            FILE, null, null, null);

                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(FILE[0]);
                    String imageDecode = cursor.getString(columnIndex);

                    //compressing the image
                    Bitmap imageToCompress = BitmapFactory.decodeFile(imageDecode);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    imageToCompress.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    cursor.close();


                    //TODO: Test this logic
                    //image is compressed and then set to imageView/
                    if (option.equals("profile")) {
                        mProfilePic.setImageBitmap(BitmapFactory.decodeStream(
                                new ByteArrayInputStream(out.toByteArray())));
                    } else {
                        mLogoImg.setImageBitmap(BitmapFactory.decodeStream(
                                new ByteArrayInputStream(out.toByteArray())));
                    }
                }
            } catch (Exception e) {
                Toast.makeText(getActivity().
                        getApplicationContext(), "Something went wrong, try again", Toast.LENGTH_SHORT).show();
            }
        }

        //this piece of code below is to make sure app has permissions to access device photos/contents
        //This is new in Android 6.0 Marshmallow. It's called Runtime permissions.
        //Specifying permissions in Android Manifest file is not enough.
    public static void verifyStoragePermissions(Activity activity) {
        //check if we have read or write permissions
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.
                WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.
                READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager
                .PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }
}



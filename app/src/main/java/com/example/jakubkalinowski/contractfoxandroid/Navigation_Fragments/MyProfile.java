package com.example.jakubkalinowski.contractfoxandroid.Navigation_Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jakubkalinowski.contractfoxandroid.Member;
import com.example.jakubkalinowski.contractfoxandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyProfile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Member member;

    public MyProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static MyProfile newInstance(String param1, String param2) {
        MyProfile fragment = new MyProfile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        if (member.getContractorOption().equals(true)) {
            rootView = inflater.inflate(R.layout.searchview_detail, container, false);
        } else {
            rootView = inflater.inflate(R.layout.fragment_home_owner_profile, container, false);
        }
        return rootView;
    }

}

// OLD MYPROFILE CLASS. REVERT IF NECESSARY !!!!!

//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link MyProfile.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link MyProfile#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class MyProfile extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    //Passing in Member
////    Member member = new Member();
//
//    Button call, message, website, directions, availability, estimate;
//
//    public MyProfile() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment MyProfile.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static MyProfile newInstance(String param1, String param2) {
//        MyProfile fragment = new MyProfile();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        //TODO: If the "contractorOption is checked than Contractor's Profile needs to be displayed, else display HomeOwner's Profile.
//        // Inflate the layout for this fragment
//
//        View rootView;
//
//        // contractorOption needs to be retreived from the firebase
////        if (member.getContractorOption().equals(true)) {
//            rootView = inflater.inflate(R.layout.fragment_contractor_profile, container, false);
//
//            // Buttons for both Profiles
//            call = (Button) rootView.findViewById(R.id.call_button);
//            message = (Button) rootView.findViewById(R.id.message_button);
//            directions = (Button) rootView.findViewById(R.id.directions_button);
//
//            call.setOnClickListener(callListener);
//            message.setOnClickListener(messageListener);
//            website.setOnClickListener(websiteListener);
//            //directions.setOnClickListener(directionsListener);
//
//
////            return rootView;
////        } else {
////            rootView = inflater.inflate(R.layout.fragment_home_owner_profile, container, false);
////
////            // Buttons for both Profiles
////            call = (Button) rootView.findViewById(R.id.call_button);
////            message = (Button) rootView.findViewById(R.id.message_button);
////            directions = (Button) rootView.findViewById(R.id.directions_button);
////            // Buttons for Contractor's Profile Only
////            website = (Button) rootView.findViewById(R.id.website_button);
////            availability = (Button) rootView.findViewById(R.id.view_availability_button);
////            estimate = (Button) rootView.findViewById(R.id.estimate_button);
////
////            return rootView;
////        }
//        return rootView; // delete after uncommenting
//    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    // Action for Message Button
//    View.OnClickListener messageListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            // message action here
//        }
//    };
//
//    // Action for Call Button
//    View.OnClickListener callListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            // TODO: the tel number needs to be fetched from the user's database.
//            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "8453327029"));
//            startActivity(intent);
//        }
//    };
//
//
//    // Action for Directions Button
////    View.OnClickListener directionsListener = new View.OnClickListener() {
////        //TODO: fetch user's address and use it here to pass it onto the next activity
////        @Override
////        public void onClick(View view) {
////            Intent intent = new Intent(getActivity().getApplicationContext(), GoogleDirectionsActivity.class);
////            startActivity(intent);
////        }
////    };
//
//
//    // Action for Website Button
//    View.OnClickListener websiteListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent intent = new Intent();
//            intent.setAction(Intent.ACTION_VIEW);
//            intent.addCategory(Intent.CATEGORY_BROWSABLE);
//            // TODO: the url needs to be fetched from the user's database.
//            intent.setData(Uri.parse("http://www.google.com"));
//            startActivity(intent);
//        }
//    };
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//
//}

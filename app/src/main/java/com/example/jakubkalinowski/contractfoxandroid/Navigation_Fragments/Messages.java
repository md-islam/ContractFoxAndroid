package com.example.jakubkalinowski.contractfoxandroid.Navigation_Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jakubkalinowski.contractfoxandroid.DrawerActivity;
import com.example.jakubkalinowski.contractfoxandroid.Estimate;
import com.example.jakubkalinowski.contractfoxandroid.EstimateActivity;
import com.example.jakubkalinowski.contractfoxandroid.MessageQue;
import com.example.jakubkalinowski.contractfoxandroid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Messages.OnFragmentInteractionListener} interfaces
 * to handle interaction events.
 * Use the {@link Messages#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Messages extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    DatabaseReference allMessageReferencesDatabaseReference;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static  String idForAllmessages ;
    EditText textArea ;
    TextView receiverName;
    Button send ;
    DrawerActivity da =new DrawerActivity();

    LinearLayout.LayoutParams sendingParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    LinearLayout.LayoutParams receivingParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView receiving ;

    String [] actions = { "Delete", "Copy text", "Forward", "View message details", "IDK"};

    Calendar c = Calendar.getInstance();

    LinearLayout parent ;
    private OnFragmentInteractionListener mListener;

    public Messages() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Messages.
     */
    // TODO: Rename and change types and number of parameters
    public static Messages newInstance() {
        Messages fragment = new Messages();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("ladimer", "oncreate");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


            allMessageReferencesDatabaseReference = FirebaseDatabase.getInstance()
                    .getReference().child("allMessages");

            allMessageReferencesDatabaseReference
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Iterable<DataSnapshot> dataSnapshotsList = dataSnapshot.getChildren();

                            for (DataSnapshot snapshot : dataSnapshotsList) {

                                if(snapshot.getValue().toString().equals( da.getCurrentUserId()+ MessageQue.clickedContacsID) ||
                                        snapshot.getValue().toString().equals( MessageQue.clickedContacsID +da.getCurrentUserId() ) ){
                                    idForAllmessages = snapshot.getValue().toString();
                                }
                            }
                            Log.i("guesswhere", "callingcheckmess");
                            checkMEssages();

                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("thatidclicke", MessageQue.clickedContacsID );
        Log.d("thatidcurrent", da.getCurrentUserId() );
        View root = inflater.inflate(R.layout.fragment_messages, container, false);
        send = (Button)root.findViewById(R.id.sendbutton_ID);
        receiverName = (TextView) root.findViewById(R.id.receiverName_ID);
        textArea = (EditText) root.findViewById(R.id.messageAreaa_ID);
        textArea.setText("\n");

        receiverName.setText(MessageQue.receiversName);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(textArea.getText().toString());
                textArea.setText("\n");
            }
        });






        // set margins for the message frames.
        sendingParams.setMargins( 100, 50 , 5, 50 );//l,t,r,b


        receivingParams.setMargins(5,50,100,50);

        parent = (LinearLayout) root.findViewById(R.id.parentLinear_ID);
        return root ;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);





        String message = " hello, my name is ladimer. How are you?";
        String message1 = " hello, my naghfghgfhgfhhhghghgfhfhme is ladimer. How are you?";
        String message2 = " hello, my hgfhghgfname is ladimer. How are you?";
        String message3 = " hello, ";

        // harcoded messages just for testing purposes.
//        parent.addView(sendingMessageHelper(message));
//        parent.addView(sendingMessageHelper(message1));
//        parent.addView(receivingMessageHelper(message));
//        parent.addView(sendingMessageHelper(message2));
//        parent.addView(receivingMessageHelper(message3));
//        parent.addView(sendingMessageHelper(message3));
//        parent.addView(receivingMessageHelper(message1));
//        parent.addView(loadingMessageHelper( " some date " , message));
//        parent.addView(loadingMessageHelper( " some date " , message));
//        parent.addView(loadingMessageHelper( " some date " , message));

    }

    private FrameLayout loadingMessageHelper(String dateDB, String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());

        FrameLayout sendingFrame = new FrameLayout(getActivity());
        sendingFrame.setLayoutParams(sendingParams);
        sendingFrame.setForegroundGravity(0x05);

        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);

        TextView sendingMessage =  new TextView(getActivity());
        TextView date =  new TextView(getActivity());

        sendingMessage.setPadding(20,10,10,10);
        date.setPadding(20,10,10,10);

        sendingMessage.setText(message);

        int seconds = c.get(Calendar.SECOND);
        date.setText( dateDB);

        ll.addView(date);
        ll.addView(sendingMessage);

        sendingFrame.addView(ll );
        sendingFrame.setBackgroundResource(R.drawable.border_2);

        sendingFrame.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an action")
                        .setItems(actions, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item

                            }
                        });
                builder.create();
                builder.show();

                return false;
            }
        });
        return sendingFrame;

    }


    public void sendMessage(String message){
        parent.addView(sendingMessageHelper(message));
    }

    public FrameLayout sendingMessageHelper (String message){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());

        FrameLayout sendingFrame = new FrameLayout(getActivity());
        sendingFrame.setLayoutParams(sendingParams);
        sendingFrame.setForegroundGravity(0x05);

        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);

        TextView sendingMessage =  new TextView(getActivity());
        TextView date =  new TextView(getActivity());

        sendingMessage.setPadding(20,10,10,10);
        date.setPadding(20,10,10,10);

        sendingMessage.setText(message);

        int seconds = c.get(Calendar.SECOND);
        date.setText( currentDateandTime);

        ll.addView(date);
        ll.addView(sendingMessage);

        sendingFrame.addView(ll );
        sendingFrame.setBackgroundResource(R.drawable.border_2);

        sendingFrame.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an action")
                        .setItems(actions, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item

                            }
                        });
                builder.create();
                builder.show();

                return false;
            }
        });
        return sendingFrame;
    }


    public FrameLayout receivingMessageHelper(String message){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());

        FrameLayout sendingFrame = new FrameLayout(getActivity());
        sendingFrame.setLayoutParams(receivingParams);
        sendingFrame.setForegroundGravity(0x03);

        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);

        TextView sendingMessage =  new TextView(getActivity());
        TextView date =  new TextView(getActivity());

        sendingMessage.setPadding(20,10,10,10);
        date.setPadding(20,10,10,10);

        sendingMessage.setText(message);

        int seconds = c.get(Calendar.SECOND);
        date.setText( currentDateandTime);


        ll.addView(date);
        ll.addView(sendingMessage);

        sendingFrame.addView(ll );
        sendingFrame.setBackgroundResource(R.drawable.border_3);

        sendingFrame.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an action")
                        .setItems(actions, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item

                            }
                        });
                builder.create();
                builder.show();

                return false;
            }
        });

        return sendingFrame;
    }



    private void checkMEssages() {


        allMessageReferencesDatabaseReference.child(idForAllmessages)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Log.i("guesswhere", "checkmessage");
                        Map <String , String> messageNameListMap = (Map)dataSnapshot.getValue();

                        Iterator it = messageNameListMap.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();

                            parent.addView(loadingMessageHelper(pair.getKey().toString() , pair.getValue().toString()));

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }






    // havent touched anything below this line.

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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

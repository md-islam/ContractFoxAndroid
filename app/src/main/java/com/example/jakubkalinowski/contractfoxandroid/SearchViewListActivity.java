package com.example.jakubkalinowski.contractfoxandroid;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jakubkalinowski.contractfoxandroid.dummy.DummyContent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

///**
// * An activity representing a list of Items. This activity
// * has different presentations for handset and tablet-size devices. On
// * handsets, the activity presents a list of items, which when touched,
// * lead to a {@link SearchViewDetailActivity} representing
// * item details. On tablets, the activity presents the list of items and
// * item details side-by-side using two vertical panes.
// */
public class SearchViewListActivity extends AppCompatActivity {

    //[Firebase_variable]**
    private FirebaseAuth mAuth;
   // private FirebaseAuth.AuthStateListener mAuthListener; //signed_in state listener object
    private DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance()
            .getReference();


    public static String[] data = {"company 1", "company 2","Ladimer"};
    public static List<String> ITEMSs = Arrays.asList(data);
    public static List<String> ITEMS = ITEMSs;
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    EditText searchBar ;
    List<String> names = new ArrayList<>();
    Button searchButton;
    String searchedContent ;
    RatingBar stars ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();



//                    mFirebaseDatabaseReference
//                            .child("users").addListenerForSingleValueEvent (new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            Iterable<DataSnapshot> dataSnapshotsList = dataSnapshot.getChildren();
//
//                        for (DataSnapshot snapshot : dataSnapshotsList) {
//                            Log.d("check-", "inside for");
//                            if (snapshot.child("contractorOption").getValue().equals(true)) {
//                                Log.d("check-", "inside if..");
//                                names.add("hey");
//                                 Toast.makeText(getApplicationContext(), "silly rabit",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });




                        setContentView(R.layout.activity_searchview_list);
        //hides the keyboard upon opening the activity.
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        searchBar = (EditText) findViewById(R.id.searchBarInList_ID);
        searchButton = (Button) findViewById(R.id.searchButtonInList);

        stars = (RatingBar) findViewById(R.id.ratingStars_ID);

        savedInstanceState = getIntent().getExtras();
        if(savedInstanceState != null){
            searchedContent = savedInstanceState.getString("content");
            searchBar.setText(searchedContent);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Contacting contractors ...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        View recyclerView = findViewById(R.id.searchview_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.contractor_profile_activity_layout) != null) {
//         if (findViewById(R.id.searchview_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }//onCreate

    public void backgroundThread (){


//        class DeleteImagesTask extends AsyncTask<Void, Void, Void> {
//            @Override
//            protected Object doInBackground(Object[] objects) {
//                Log.d("check", "inside background");
//                mFirebaseDatabaseReference
//                        .child("users").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        Iterable<DataSnapshot> dataSnapshotsList =  dataSnapshot.getChildren();
//
//                        for (DataSnapshot snapshot : dataSnapshotsList) {
//                            if (snapshot.child("contractorOption").getValue().equals(true)){
//                                names.add("hey");
//                                // Toast.makeText(getApplicationContext(), "silly rabit",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Object o) {
//                super.onPostExecute(o);
//                Log.d("check", "postexecute");
//                if(names.isEmpty()){
//                    Toast.makeText(getApplicationContext(), "its empty",Toast.LENGTH_SHORT).show();
//
//                }
//                for(String n : names){
//                    Toast.makeText(getApplicationContext(), "silly rabit",Toast.LENGTH_SHORT).show();
//                }
//            }
//        } ;
        class background extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... params) {
                Log.d("check-", "inside background");
                mFirebaseDatabaseReference
                        .child("users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> dataSnapshotsList =  dataSnapshot.getChildren();

                        for (DataSnapshot snapshot : dataSnapshotsList) {
                            if (snapshot.child("contractorOption").getValue().equals(true)){
                                names.add("hey");
                                // Toast.makeText(getApplicationContext(), "silly rabit",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                return null;
            }
            @Override
            protected void onPostExecute(Void param) {
                Log.d("check", "postexecute");
                if(names.isEmpty()){
                    Toast.makeText(getApplicationContext(), "its empty",Toast.LENGTH_SHORT).show();

                }else{
                    for(String n : names){
                        Toast.makeText(getApplicationContext(), "silly rabit",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }
        new background().execute();


    }//backgroundthread

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(ITEMS));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        // public CardView cardView;

        private final List<String> mValues;

        public SimpleItemRecyclerViewAdapter(List<String> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.searchview_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            // holder.mItem = mValues.get(position);
            holder.companyName.setText(mValues.get(position).toString());
            //  holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        //ADDED FOR TESTING
//                        Context context = v.getContext();
//                        Intent intent = new Intent(context, ContractorProfileActivity.class);
////                        Intent intent = new Intent(context, SearchViewDetailActivity.class);
//                        intent.putExtra(Estimate.ARG_ITEM_ID, holder.mItem.id);
//                        context.startActivity(intent);

                        //REMOVED FOR TESTING
                        Bundle arguments = new Bundle();
                        arguments.putString(Estimate.ARG_ITEM_ID, holder.mItem.id);
                        Estimate fragment = new Estimate();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.contractor_profile, fragment) // TESTING
                                .replace(R.id.searchview_detail_container, fragment) // TESTING
                                .commit();

                    } else {
                        Context context = v.getContext();

                        // Intent intent = new Intent(context, SearchViewDetailActivity.class);
                        //intent.putExtra(holder.companyName.getText(), 0 );
                        // intent.putExtra("name", holder.companyName.getText().toString() );

                        Intent intent = new Intent(context, ContractorProfileActivity.class);
////                        Intent intent = new Intent(context, SearchViewDetailActivity.class);
                        intent.putExtra(Estimate.ARG_ITEM_ID, holder.mItem.id);
//                        intent.putExtra(DisplayProfile.ARG_ITEM_ID, holder.mItem.id);
//
                        context.startActivity(intent);
                    }
                }
            });
        }



        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView companyName;
            //public final TextView mContentView;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                companyName = (TextView) view.findViewById(R.id.companyName_ID);
                // mContentView = (TextView) view.findViewById(R.id.content);
                companyName.setText("ladi");
            }
//
//            @Override
//            public String toString() {
//                return super.toString() + " '" + mContentView.getText() + "'";
//            }
        }
    }// end of inner class
}

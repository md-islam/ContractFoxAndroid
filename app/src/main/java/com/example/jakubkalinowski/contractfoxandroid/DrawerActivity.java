package com.example.jakubkalinowski.contractfoxandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jakubkalinowski.contractfoxandroid.Navigation_Fragments.ContractorScheduleFragment;
import com.example.jakubkalinowski.contractfoxandroid.Navigation_Fragments.Home;
import com.example.jakubkalinowski.contractfoxandroid.Navigation_Fragments.Messages;
import com.example.jakubkalinowski.contractfoxandroid.Navigation_Fragments.MyProfile;
import com.example.jakubkalinowski.contractfoxandroid.Navigation_Fragments.ProfileEdit;
import com.example.jakubkalinowski.contractfoxandroid.homePage_Fragments.BackYard;
import com.example.jakubkalinowski.contractfoxandroid.homePage_Fragments.Exterior;
import com.example.jakubkalinowski.contractfoxandroid.homePage_Fragments.Interior;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;


public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,

        MyProfile.OnFragmentInteractionListener, ProfileEdit.OnFragmentInteractionListener,Home.OnFragmentInteractionListener,

        Exterior.OnFragmentInteractionListener, Interior.OnFragmentInteractionListener,
        BackYard.OnFragmentInteractionListener{

    public static FragmentTransaction ft;

    static String currentUserId ;
    static String currentUserFirstName  ="kladimer";
    LinearLayout tab1, tab2, tab3, tab4 ;
    Button exteriorButton , interiorButton, backyardButton , searchButton;
    EditText searchBar;
    //TabHost th;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        searchButton = (Button) findViewById(R.id.mainSearchButton);
        searchButton.setOnClickListener(searchListerner);
        searchBar = (EditText) findViewById(R.id.searchBar_ID);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //NEW CHANGES ADDED BY MD'S FILE

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayFragment(R.id.homee);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            currentUserId = user.getUid();
            //currentUserFirstName = user.getProviderData().get(5).toString();

//            Log.i("ladimmm" ,user.getDisplayName());
//            Log.i("ladimmm" ,currentUserId);
        } else {
            // No user is signed in
            Log.i("ladimmm" ,"not signed in !!");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        displayFragment(item.getItemId());

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String getCurrentUserId(){
        return currentUserId;
    }

    public void displayFragment( int viewId){

        Fragment fragment = null;
        String title = "";

        switch (viewId) {
            case R.id.nav_profile_edit:
                fragment = new ProfileEdit();
                title = "Edit Profile";
                break;

            case R.id.nav_myprofile:
                fragment = new MyProfile();
                title = "My Profile";
                break;
            case R.id.messages:
//                fragment = new MessageQue();
//                title = "Messagese";
                Intent i = new Intent(this, MessageQue.class);
                startActivity(i);
                break;

//            case 123:
//                fragment = new Messages();
//                title = "Messagese";
//                break;

            case R.id.homee:
                fragment = new Home();
                title = "Home";
                break;
            case R.id.contractor_availability_:
                //fragment work here
                fragment = new ContractorScheduleFragment();
                title = "contractorschedulefragmentTag";
                break;

            case R.id.log_out:
                // Log out action here
                Toast.makeText(getApplicationContext(), "Log Out " , Toast.LENGTH_LONG).show(); //testing/debugging
                FirebaseAuth.getInstance().signOut();
                this.finish();
                break;
        }


        if(viewId != R.id.messages){


            if (fragment != null) {
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.displayArea_ID, fragment, title);
                ft.commit();
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);



    }

    //Onclick listener for search
    View.OnClickListener searchListerner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // search logic takes place/deligated here.

            Intent i = new Intent(getApplicationContext(), SearchViewListActivity.class);
            //think of a clever way to reuse code here.
            // i.putExtra(searchBar.getText().toString() ,true);
            i.putExtra("serachedItem" , searchBar.getText().toString() );
            startActivity(i);
        }
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public String passedData(String s1, String s2){
        return null;
    }



}

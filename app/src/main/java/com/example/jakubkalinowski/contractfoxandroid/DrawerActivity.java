package com.example.jakubkalinowski.contractfoxandroid;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jakubkalinowski.contractfoxandroid.Navigation_Fragments.Home;
import com.example.jakubkalinowski.contractfoxandroid.Navigation_Fragments.Messages;
import com.example.jakubkalinowski.contractfoxandroid.Navigation_Fragments.MyProfile;
import com.example.jakubkalinowski.contractfoxandroid.Navigation_Fragments.ProfileEdit;
import com.example.jakubkalinowski.contractfoxandroid.homePage_Fragments.BackYard;
import com.example.jakubkalinowski.contractfoxandroid.homePage_Fragments.Exterior;
import com.example.jakubkalinowski.contractfoxandroid.homePage_Fragments.Interior;
import com.google.firebase.auth.FirebaseAuth;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Messages.OnFragmentInteractionListener,
        MyProfile.OnFragmentInteractionListener, ProfileEdit.OnFragmentInteractionListener,Home.OnFragmentInteractionListener,
        Exterior.OnFragmentInteractionListener, Interior.OnFragmentInteractionListener,
        BackYard.OnFragmentInteractionListener{

    public static FragmentTransaction ft;

    LinearLayout tab1, tab2, tab3, tab4 ;
    Button exteriorButton , interiorButton, backyardButton;
   //TabHost th;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

//        tab1 = (LinearLayout) findViewById(R.id.tab1);
//        tab2 = (LinearLayout) findViewById(R.id.tab2);
//        tab3 = (LinearLayout) findViewById(R.id.tab3);
//        tab4 = (LinearLayout) findViewById(R.id.tab4);
//        exteriorButton = (Button)findViewById(R.id.exteriorButton);

        //exterior code







        // List of Notifications
//        String[] notification = {"Upcoming appointment reminder", "New review posted", "Price estimate request", "Price estimate request"}; //for demo purposes
//        ListAdapter notificationAdapter = new CustomAdapter(this, notification);
//        ListView notificationListView = (ListView) findViewById(R.id.notificationListView);
//        notificationListView.setAdapter(notificationAdapter);
//
//        // Post-Click actions
//        notificationListView.setOnItemClickListener(
//            new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    String notification = String.valueOf(parent.getItemAtPosition(position));
//                    // Silly display of notification for demo purposes
//                    Toast.makeText(DrawerActivity.this, notification, Toast.LENGTH_LONG).show();
//                }
//            }
//        );


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



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

    // OLD STUFF REPLACED BY MD'S FILE

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//
//        displayFragment(R.id.homee);
//    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
////            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
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

    public  void displayFragment( int viewId){

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
                fragment = new Messages();
                title = "Messagese";
                break;
            case R.id.homee:
                fragment = new Home();
                title = "Home";
                break;

            case R.id.log_out:
                // Log out action here
                Toast.makeText(getApplicationContext(), "Log Out " , Toast.LENGTH_LONG).show(); //testing/debugging
                FirebaseAuth.getInstance().signOut();
                this.finish();
                break;
        }

        if (fragment != null) {
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.displayArea_ID, fragment, title);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

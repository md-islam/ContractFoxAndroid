package com.example.jakubkalinowski.contractfoxandroid;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    LinearLayout tab1, tab2, tab3, tab4 ;
   //TabHost th;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        tab1 = (LinearLayout) findViewById(R.id.tab1);
        tab2 = (LinearLayout) findViewById(R.id.tab2);
        tab3 = (LinearLayout) findViewById(R.id.tab3);
        tab4 = (LinearLayout) findViewById(R.id.tab4);


        //Add stuff in tab1 here --------------------------------
        TextView t = new TextView(getApplicationContext());
        t.setText("This is tab 1");
        tab1.addView(t);

        // -------------------


        //Add stuff in tab2 here --------------------------------

        TextView t2 = new TextView(getApplicationContext());
        t.setText("This is tab 2");
        tab2.addView(t2);


        // List of Notifications
        String[] notification = {"Upcoming appointment reminder", "New review posted", "Price estimate request", "Price estimate request"}; //for demo purposes
        ListAdapter notificationAdapter = new CustomAdapter(this, notification);
        ListView notificationListView = (ListView) findViewById(R.id.notificationListView);
        notificationListView.setAdapter(notificationAdapter);

        // Post-Click actions
        notificationListView.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String notification = String.valueOf(parent.getItemAtPosition(position));
                    // Silly display of notification for demo purposes
                    Toast.makeText(DrawerActivity.this, notification, Toast.LENGTH_LONG).show();
                }
            }
        );




        //End of Notifications



        // -------------------


        //Add stuff in tab3 here --------------------------------


        TextView t3 = new TextView(getApplicationContext());
        t.setText("This is tab 3");
        tab3.addView(t3);
        // -------------------

        //Add stuff in tab4 here --------------------------------
        TextView t4= new TextView(getApplicationContext());
        t.setText("This is tab 4");
        tab4.addView(t4);

        // -------------------





        // Set up TabHost
        TabHost th = (TabHost)findViewById(R.id.tabHost);
        th.setup();
        //----Set up a tab
        TabHost.TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab1); //this will hold the content of the tab
        specs.setIndicator("Home"); //this is the name of the tab
        th.addTab(specs); //add tab to TabHost
        //-----//
        //----Set up a tab
        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tab2); //this will hold the content of the tab
        specs.setIndicator("Notifications"); //this is the name of the tab
        th.addTab(specs); //add tab to TabHost
        //-----//
        //----Set up a tab
        specs = th.newTabSpec("tag3");
        specs.setContent(R.id.tab3); //this will hold the content of the tab
        specs.setIndicator("Messages"); //this is the name of the tab
        th.addTab(specs); //add tab to TabHost
        //-----//
        //----Set up a tab
        specs = th.newTabSpec("tag4");
        specs.setContent(R.id.tab4); //this will hold the content of the tab
        specs.setIndicator("Questions"); //this is the name of the tab
        th.addTab(specs); //add tab to TabHost
        //-----//

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);








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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

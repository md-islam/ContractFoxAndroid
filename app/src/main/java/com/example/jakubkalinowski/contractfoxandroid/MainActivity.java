package com.example.jakubkalinowski.contractfoxandroid;

import android.app.TabActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        //----Set up a tab
        specs = th.newTabSpec("tag5");
        specs.setContent(R.id.tab5); //this will hold the content of the tab
        specs.setIndicator("Profile"); //this is the name of the tab
        th.addTab(specs); //add tab to TabHost
        //-----//
    }

}

package com.example.raymondlin.wildlifediscoveryprototype;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainMenuActivity extends TabActivity {
    /** Called when the activity is first created. */

    TabHost tabHost;
    int tab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        tabHost = getTabHost();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                tab = 1;
            } else {
                tab = extras.getInt("GO_TO_TAB");
            }
        } else {
            tab = (Integer) savedInstanceState.getSerializable("GO_TO_TAB");
        }

        TabSpec addspec = tabHost.newTabSpec("Add Encounter");
        // setting Title and Icon for the Tab
        addspec.setIndicator("Add Encounter");
        Intent addIntent = new Intent(this, AddEncounterActivity.class);
        addIntent.putExtra("tookpic", "false");
        addspec.setContent(addIntent);

        TabSpec viewspec = tabHost.newTabSpec("View All Encounters");
        viewspec.setIndicator("View All Encounters");
        Intent viewIntent = new Intent(this, ViewEncountersActivity.class);
        viewspec.setContent(viewIntent);

        TabSpec alertspec = tabHost.newTabSpec("Alerts");
        alertspec.setIndicator("Alerts");
        Intent alertIntent = new Intent(this, ViewAlerts.class);
        alertspec.setContent(alertIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(addspec);
        tabHost.addTab(viewspec);
        tabHost.addTab(alertspec);

        tabHost.setCurrentTab(tab);
    }

    public void switchTab(int tab){
        tabHost.setCurrentTab(tab);
    }
}

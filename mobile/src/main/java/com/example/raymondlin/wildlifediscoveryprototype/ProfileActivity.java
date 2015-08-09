package com.example.raymondlin.wildlifediscoveryprototype;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by raymondlin on 7/29/15.
 */
public class ProfileActivity extends Activity {

    public ListView listView ;
    public ProfileDB mydb;
    public ArrayList<String> imageUriStrList = new ArrayList<String>();
    public Location currentLocation;
    public CustomListArrayListAdapter adapter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);




        mydb = new ProfileDB(this);


        MyLocation.LocationResultt locationResult = new MyLocation.LocationResultt(){
            @Override
            public void gotLocation(Location location){
                if (location == null) {
                    Log.v("!!!!!!!!", "cant get current location");
                }
                Log.v("00000000", "on create!!!");
                adapter = new CustomListArrayListAdapter(ProfileActivity.this, mydb.getAllNames(), mydb.getAllIdentifications(), mydb.getAllLocations(), mydb.getAllNotes(), mydb.getAllPhotos(), location);

                // Update the new location
                adapter.notifyDataSetChanged();

                listView = (ListView)findViewById(R.id.listview);
                listView.setAdapter(adapter);

                // Start polling the current when in the login window
                startService(new Intent(ProfileActivity.this, LocationPollService.class));


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        String Slecteditem = mydb.getAllNames().get(+position);
                        //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        Intent detail_intent = new Intent(ProfileActivity.this, ViewEncountersDetailActivity.class);
                        detail_intent.putExtra("ENCOUNTER_ID", mydb.getAllIDs().get(+position));
                        detail_intent.putExtra("ENCOUNTER_NAME", mydb.getAllNames().get(+position));
                        detail_intent.putExtra("ENCOUNTER_TIME", mydb.getAllTimes().get(+position));
                        detail_intent.putExtra("ENCOUNTER_NOTE", mydb.getAllNotes().get(+position));
                        detail_intent.putExtra("ENCOUNTER_LOCATION", mydb.getAllLocations().get(+position));
                        detail_intent.putExtra("ENCOUNTER_PHOTO", mydb.getAllPhotos().get(+position));

                        startActivity(detail_intent);
                    }
                });
            }
        };
        MyLocation myLocation = new MyLocation();
        myLocation.getLocation(this, locationResult);


        // Scroll View
        ListView lv = (ListView) findViewById(R.id.listview);
        lv.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();

        MyLocation.LocationResultt locationResult = new MyLocation.LocationResultt(){
            @Override
            public void gotLocation(Location location){
                if (location == null) {
                    Log.v("!!!!!!!!", "cant get current location");
                }
                Log.v("00000000", "on create!!!");
                ArrayList<String> nameList = new ArrayList<String>();
                ArrayList<String> identificationList = new ArrayList<String>();
                ArrayList<String> locationList = new ArrayList<String>();
                ArrayList<String> noteList = new ArrayList<String>();
                ArrayList<String> photoList = new ArrayList<String>();
                for (String name : mydb.getAllNames()) {
                    nameList.add(name);
                }
                for (String identification : mydb.getAllIdentifications()) {
                    identificationList.add(identification);
                }
                for (String l : mydb.getAllLocations()) {
                    locationList.add(l);
                }
                for (String note : mydb.getAllNotes()) {
                    noteList.add(note);
                }
                for (String photo : mydb.getAllPhotos()) {
                    photoList.add(photo);
                }

//              CustomListArrayListAdapter adapter=new CustomListArrayListAdapter(ViewEncountersActivity.this, mydb.getAllNames(), mydb.getAllLocations(), mydb.getAllNotes(), mydb.getAllPhotos(), location);
                adapter = new CustomListArrayListAdapter(ProfileActivity.this, nameList, identificationList, locationList, noteList, photoList, location);

                // Update the new location
                adapter.notifyDataSetChanged();

                listView = (ListView)findViewById(R.id.listview);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        String Slecteditem = mydb.getAllNames().get(+position);
                        //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        Intent detail_intent = new Intent(ProfileActivity.this, ViewEncountersDetailActivity.class);
                        detail_intent.putExtra("ENCOUNTER_ID", mydb.getAllIDs().get(+position));
                        detail_intent.putExtra("ENCOUNTER_NAME", mydb.getAllNames().get(+position));
                        detail_intent.putExtra("ENCOUNTER_TIME", mydb.getAllTimes().get(+position));
                        detail_intent.putExtra("ENCOUNTER_IDENTIFICATION", mydb.getAllIdentifications().get(+position));
                        detail_intent.putExtra("ENCOUNTER_NOTE", mydb.getAllNotes().get(+position));
                        detail_intent.putExtra("ENCOUNTER_LOCATION", mydb.getAllLocations().get(+position));
                        detail_intent.putExtra("ENCOUNTER_PHOTO", mydb.getAllPhotos().get(+position));

                        startActivity(detail_intent);
                    }
                });
            }
        };
        MyLocation myLocation = new MyLocation();
        myLocation.getLocation(this, locationResult);


        // Scroll View
        ListView lv = (ListView) findViewById(R.id.listview);
        lv.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    @Override
    public void onStop () {
        super.onStop();
        mydb.close();
    }
}



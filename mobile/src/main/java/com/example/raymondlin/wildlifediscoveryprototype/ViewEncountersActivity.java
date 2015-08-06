package com.example.raymondlin.wildlifediscoveryprototype;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by raymondlin on 7/29/15.
 */
public class ViewEncountersActivity extends Activity {

    public ListView listView ;
    public DBHelper mydb;
    public ArrayList<String> imageUriStrList = new ArrayList<String>();
    public Location currentLocation;
    public CustomListArrayListAdapter adapter;

    Integer[] imgid={
            R.drawable.animal_1,
            R.drawable.animal_2,
            R.drawable.animal_3,
            R.drawable.animal_4,
            R.drawable.animal_5,
            R.drawable.animal_6,
            R.drawable.animal_7,
            R.drawable.animal_8,
            R.drawable.animal_9,
            R.drawable.animal_10,
            R.drawable.animal_11,
            R.drawable.animal_12,
            R.drawable.animal_13,
            R.drawable.animal_14,
            R.drawable.animal_15,
            R.drawable.animal_16,
            R.drawable.animal_17,
            R.drawable.animal_18,
            R.drawable.animal_19,
            R.drawable.animal_20,

    };


    public ArrayList<String> getImageUriStrList() {
        Uri image_1 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_1) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_1) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_1) );
        Uri image_2 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_2) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_2) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_2));
        Uri image_3 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_3) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_3) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_3) );
        Uri image_4 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_4) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_4) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_4) );
        Uri image_5 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_5) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_5) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_5) );
        Uri image_6 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_6) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_6) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_6) );
        Uri image_7 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_7) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_7) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_7) );
        Uri image_8 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_8) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_8) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_8) );
        Uri image_9 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_9) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_10) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_10) );
        Uri image_10 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_10) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_10) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_10) );
        Uri image_11 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_11) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_11) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_11) );
        Uri image_12 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_12) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_12) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_12) );
        Uri image_13 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_13) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_13) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_13) );
        Uri image_14 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_14) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_14) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_14) );
        Uri image_15 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_15) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_15) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_15) );
        Uri image_16 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_16) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_16) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_16) );
        Uri image_17 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_17) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_17) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_17) );
        Uri image_18 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_18) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_18) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_18) );
        Uri image_19 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_19) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_19) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_19) );
        Uri image_20 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.animal_20) + '/' +
                getResources().getResourceTypeName(R.drawable.animal_20) + '/' +
                getResources().getResourceEntryName(R.drawable.animal_20));

        String image_1_str = image_1.toString();
        String image_2_str = image_2.toString();
        String image_3_str = image_3.toString();
        String image_4_str = image_4.toString();
        String image_5_str = image_5.toString();
        String image_6_str = image_6.toString();
        String image_7_str = image_7.toString();
        String image_8_str = image_8.toString();
        String image_9_str = image_9.toString();
        String image_10_str = image_10.toString();
        String image_11_str = image_11.toString();
        String image_12_str = image_12.toString();
        String image_13_str = image_13.toString();
        String image_14_str = image_14.toString();
        String image_15_str = image_15.toString();
        String image_16_str = image_16.toString();
        String image_17_str = image_17.toString();
        String image_18_str = image_18.toString();
        String image_19_str = image_19.toString();
        String image_20_str = image_20.toString();

        imageUriStrList.add(image_1_str);
        imageUriStrList.add(image_2_str);
        imageUriStrList.add(image_3_str);
        imageUriStrList.add(image_4_str);
        imageUriStrList.add(image_5_str);
        imageUriStrList.add(image_6_str);
        imageUriStrList.add(image_7_str);
        imageUriStrList.add(image_8_str);
        imageUriStrList.add(image_9_str);
        imageUriStrList.add(image_10_str);
        imageUriStrList.add(image_11_str);
        imageUriStrList.add(image_12_str);
        imageUriStrList.add(image_13_str);
        imageUriStrList.add(image_14_str);
        imageUriStrList.add(image_15_str);
        imageUriStrList.add(image_16_str);
        imageUriStrList.add(image_17_str);
        imageUriStrList.add(image_18_str);
        imageUriStrList.add(image_19_str);
        imageUriStrList.add(image_20_str);

        return imageUriStrList;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_encounters);




        mydb = new DBHelper(this);
//        this.getImageUriStrList();
//        mydb.insertEntry("Cat",        "05:13", "True", "it thinks about something", "37.87365081,-122.25695372", imageUriStrList.get(0));
//        mydb.insertEntry("Dogge",      "07:35", "False", "the dogge sitting on the grass", "37.8889442,-122.28727341", imageUriStrList.get(1));
//        mydb.insertEntry("Leopard",    "15:43", "False", "some shit is going on", "37.86806107,-122.26098776", imageUriStrList.get(2));
//        mydb.insertEntry("Dog",        "12:22", "True", "really cute white dog", "37.86939926,-122.27158785", imageUriStrList.get(3));
//        mydb.insertEntry("Squirrel",   "06:12", "True", "squirrel eating nuts", "37.88349111,-122.25779057", imageUriStrList.get(4));
//        mydb.insertEntry("Rabbit",     "08:29", "False", "that rabbit is snow white", "37.87625921,-122.26236105", imageUriStrList.get(5));
//        mydb.insertEntry("??????",      "03:55", "True", "koala needs sleep", "37.88105233,-122.25178242", imageUriStrList.get(6));
//        mydb.insertEntry("Mice",       "16:31", "False", "it has really big eyes", "37.87224494,-122.25656748", imageUriStrList.get(7));
//        mydb.insertEntry("RabiitDog",  "18:10", "False", "a rabbit with dog face", "37.88904581,-122.2539711", imageUriStrList.get(8));
//        mydb.insertEntry("Leopard",    "22:36", "False", "what a innocent leopard", "37.8709915,-122.26358414", imageUriStrList.get(9));
//        mydb.insertEntry("Tiger",      "10:27", "False", "this tiger looks humble", "37.87365081,-122.25695372", imageUriStrList.get(10));
//        mydb.insertEntry("Rabbit",     "05:53", "False", "rabbit is in its nest, looks comfortable", "37.8889442,-122.28727341", imageUriStrList.get(11));
//        mydb.insertEntry("Sealion",    "07:45", "True", "baby sealion is sooo cute!", "37.86628242,-122.29113579", imageUriStrList.get(12));
//        mydb.insertEntry("PolarBear",  "14:06", "False", "it looks little tried, take a rest then", "37.86797637,-122.25040913", imageUriStrList.get(13));
//        mydb.insertEntry("Mongoose",   "12:20", "False", "a bunch of mongoose in a family", "37.87390488,-122.23654747", imageUriStrList.get(14));
//        mydb.insertEntry("Pig",        "13:11", "False", "cute pig baby, don't harm it", "37.88989253,-122.27062225", imageUriStrList.get(15));
//        mydb.insertEntry("Kangaroo",   "15:19", "True", "kangaroos are fighting each other", "37.85547413,-122.26650238", imageUriStrList.get(16));
//        mydb.insertEntry("Panda",      "14:43", "True", "rare panda found!!", "37.87562405,-122.25617051", imageUriStrList.get(17));
//        mydb.insertEntry("Butterfly",  "17:00", "False", "it looks so beautiful~", "37.88032406,-122.26019382", imageUriStrList.get(18));
//        mydb.insertEntry("Wolf",       "09:57", "False", "white wolf resting", "37.86843373,-122.26169586", imageUriStrList.get(19));


        MyLocation.LocationResultt locationResult = new MyLocation.LocationResultt(){
            @Override
            public void gotLocation(Location location){
                if (location == null) {
                    Log.v("!!!!!!!!", "cant get current location");
                }
                Log.v("00000000", "on create!!!");
//                ArrayList<String> nameList = new ArrayList<String>();
//                ArrayList<String> locationList = new ArrayList<String>();
//                ArrayList<String> noteList = new ArrayList<String>();
//                ArrayList<String> photoList = new ArrayList<String>();
//                for (String name : mydb.getAllNames()) {
//                    nameList.add(name);
//                }
//                for (String l : mydb.getAllLocations()) {
//                    locationList.add(l);
//                }
//                for (String note : mydb.getAllNotes()) {
//                    noteList.add(note);
//                }
//                for (String photo : mydb.getAllPhotos()) {
//                    photoList.add(photo);
//                }

//              CustomListArrayListAdapter adapter=new CustomListArrayListAdapter(ViewEncountersActivity.this, mydb.getAllNames(), mydb.getAllLocations(), mydb.getAllNotes(), mydb.getAllPhotos(), location);
                adapter = new CustomListArrayListAdapter(ViewEncountersActivity.this, mydb.getAllNames(), mydb.getAllIdentifications(), mydb.getAllLocations(), mydb.getAllNotes(), mydb.getAllPhotos(), location);

                // Update the new location
                adapter.notifyDataSetChanged();

                listView = (ListView)findViewById(R.id.listview);
                listView.setAdapter(adapter);

                // Start polling the current when in the login window
                startService(new Intent(ViewEncountersActivity.this, LocationPollService.class));


                listView.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        String Slecteditem = mydb.getAllNames().get(+position);
                        //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        Intent detail_intent = new Intent(ViewEncountersActivity.this, ViewEncountersDetailActivity.class);
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
                adapter = new CustomListArrayListAdapter(ViewEncountersActivity.this, nameList, identificationList, locationList, noteList, photoList, location);

                // Update the new location
                adapter.notifyDataSetChanged();

                listView = (ListView)findViewById(R.id.listview);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        String Slecteditem = mydb.getAllNames().get(+position);
                        //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        Intent detail_intent = new Intent(ViewEncountersActivity.this, ViewEncountersDetailActivity.class);
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
}


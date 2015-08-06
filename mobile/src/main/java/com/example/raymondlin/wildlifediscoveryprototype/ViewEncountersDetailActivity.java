package com.example.raymondlin.wildlifediscoveryprototype;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class ViewEncountersDetailActivity extends FragmentActivity implements LocationListener {

    public GoogleMap googleMap;
    public LatLng myPosition;

    public String nameStr;
    public String timeStr;
    public String identificationStr;
    public String noteStr;
    public String photoStr;
    public String locationStr;
    public int id;

    public TextView textName;
    public TextView textTime;
    public TextView textNote;
    public ImageView imageHolder;
    public Button editButton;

    public int editButtonID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_encounters_detail);



        Bundle extras = getIntent().getExtras();

        textName = (TextView) findViewById(R.id.name_edit);
        textTime = (TextView) findViewById(R.id.time_edit);
        textNote = (TextView) findViewById(R.id.note_edit);
        imageHolder = (ImageView) findViewById(R.id.image_view);

        if (extras.getString("ENCOUNTER_NAME") != null) {
            nameStr = extras.getString("ENCOUNTER_NAME");
            textName.setText(nameStr);
        }
        if (extras.getString("ENCOUNTER_TIME") != null) {
            timeStr = extras.getString("ENCOUNTER_TIME");
            textTime.setText(timeStr);
        }
        if (extras.getString("ENCOUNTER_IDENTIFICATION").equals("True") || extras.getString("ENCOUNTER_IDENTIFICATION").equals("Updated")) {
            identificationStr = extras.getString("ENCOUNTER_IDENTIFICATION");

            Button editButton = (Button) findViewById(R.id.edit_button);
            editButton.setVisibility(View.VISIBLE);
//            // Create a edit button
//            //add LInearLayout
//            RelativeLayout myRelativeLayout =(RelativeLayout) findViewById(R.id.detail_relativelayout);
//
//            //add LayoutParams
//            //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(100, 100);
//
//
//            // add Button
//            editButton = new Button(this);
//            editButton.setText("Edit");
//            editButtonID = View.generateViewId();
//
//            editButton.setId(100001);
//
//            //add the Button to RelativeLayout
//            myRelativeLayout.addView(editButton, params);
        } else {
            Button editButton = (Button) findViewById(R.id.edit_button);
            editButton.setVisibility(View.INVISIBLE);
        }
        if (extras.getString("ENCOUNTER_NOTE") != null) {
            noteStr = extras.getString("ENCOUNTER_NOTE");
            textNote.setText(noteStr);
        }
        if (extras.getString("ENCOUNTER_PHOTO") != null) {
            photoStr = extras.getString("ENCOUNTER_PHOTO");
            imageHolder.setImageURI(Uri.parse(photoStr));
        }

        id = extras.getInt("ENCOUNTER_ID");
        Log.v("~~~~~~~~~~", "Primary Key: " + id);


        Log.v("------", "hello--------------------");
        if (extras.getString("NEW_NAME") != null) {
            textName.setText(extras.getString("NEW_NAME"));
            Log.v("~~~~~~~~", "new name: " + extras.getString("NEW_NAME"));
            // Update the database
        } else {
            Log.v("!!!!!!!!!", "new-name not received");
        }
        if (extras.getString("NEW_TIME") != null) {
            textName.setText(extras.getString("NEW_TIME"));
            // Update the database
        }
        if (extras.getString("NEW_NOTE") != null) {
            textName.setText(extras.getString("NEW_NOTE"));
            // Update the database
        }




        // =============================== Google Map ==============================================
        locationStr = extras.getString("ENCOUNTER_LOCATION");
        String[] locationArray = extras.getString("ENCOUNTER_LOCATION").split(",");
        double encounterLatitude = Double.parseDouble(locationArray[0]);
        double encounterLongitude = Double.parseDouble(locationArray[1]);

        LatLng animalPosition = new LatLng(encounterLatitude, encounterLongitude);

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        // Getting GoogleMap object from the fragment
        googleMap = fm.getMap();

        // Enabling MyLocation Layer of Google Map
        googleMap.setMyLocationEnabled(true);

        // Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            // Getting latitude of the current location
            double latitude = location.getLatitude();

            // Getting longitude of the current location
            double longitude = location.getLongitude();

            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(latitude, longitude);

            myPosition = new LatLng(latitude, longitude);

            MarkerOptions currentMarker = new MarkerOptions().position(myPosition).title("You");
            MarkerOptions animalMarker = new MarkerOptions().position(animalPosition).title(extras.getString("ENCOUNTER_NAME"));

            ArrayList<MarkerOptions> markers = new ArrayList<MarkerOptions>();
            markers.add(currentMarker);
            markers.add(animalMarker);

            googleMap.addMarker(animalMarker);
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (MarkerOptions marker : markers) {
                builder.include(marker.getPosition());
            }
            LatLngBounds bounds = builder.build();

            int padding = 100; // offset from edges of the map in pixels
            final CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);


            googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    googleMap.moveCamera(cu); } });
        } else {
            Log.v("Detailed View!!!", "cant find location detailed");
        }
        // =============================== Google Map ==============================================

//      // Smaple Insertion to the database
//      DBHelper mydb = new DBHelper(this);
//      mydb.insertEntry("Newwwwwwww", "09:57", "white wolf resting", "37.86843373,-122.26169586", Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
//                getResources().getResourcePackageName(R.drawable.animal_2) + '/' +
//                getResources().getResourceTypeName(R.drawable.animal_2) + '/' +
//                getResources().getResourceEntryName(R.drawable.animal_2)).toString());


        Button button = (Button) findViewById(R.id.edit_button);
        if (button.getVisibility() == View.VISIBLE) {
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent edit_intent = new Intent(ViewEncountersDetailActivity.this, ViewEncountersDetailEditActivity.class);
                    edit_intent.putExtra("ENCOUNTER_NAME", nameStr);
                    edit_intent.putExtra("ENCOUNTER_TIME", timeStr);
                    edit_intent.putExtra("ENCOUNTER_NOTE", noteStr);
                    edit_intent.putExtra("ENCOUNTER_LOCATION", locationStr);
                        Log.v("++++++++++", "photo to be sent: " + photoStr);
                    edit_intent.putExtra("ENCOUNTER_PHOTO", photoStr);
                    startActivity(edit_intent);
                }
            });
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void onResume () {
        super.onResume();
        onNewIntent(getIntent());
        Log.v("!!!!!!!!", "fuck, I was late!!!");
        Bundle extras = getIntent().getExtras();

        if (extras.getString("NEW_NAME") != null) {
            textName.setText(extras.getString("NEW_NAME"));
            Log.v("~~~~~~~~", "new name: " + extras.getString("NEW_NAME"));
            // Update the database
        } else {
            Log.v("!!!!!!!!!", "new-name not received");
        }
        if (extras.getString("NEW_TIME") != null) {
            textTime.setText(extras.getString("NEW_TIME"));
            // Update the database
        }
        if (extras.getString("NEW_NOTE") != null) {
            textNote.setText(extras.getString("NEW_NOTE"));
            // Update the database
        }

        if (extras.getString("NEW_NAME") != null || extras.getString("NEW_TIME")!= null || extras.getString("NEW_NOTE") != null) {


            DBHelper mydb = new DBHelper(this);
            SQLiteDatabase db = mydb.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DBHelper.FeedEntry.COLUMN_NAME_NAME, extras.getString("NEW_NAME"));
            values.put(DBHelper.FeedEntry.COLUMN_NAME_TIME, extras.getString("NEW_TIME"));
            values.put(DBHelper.FeedEntry.COLUMN_NAME_IDENTIFICATION, "Updated");
            values.put(DBHelper.FeedEntry.COLUMN_NAME_NOTE, extras.getString("NEW_NOTE"));
            db.update("entries", values,  DBHelper.FeedEntry._ID + " =" + id, null);
        }
    }

//    @Override
//    public void onBackPressed()
//    {
//        super.onBackPressed();
//        startActivity(new Intent(ViewEncountersDetailActivity.this, ViewEncountersActivity.class));
//        finish();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_encounters_detail, menu);
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

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

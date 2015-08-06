package com.example.raymondlin.wildlifediscoveryprototype;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class ViewEncountersDetailEditActivity extends FragmentActivity implements LocationListener {

    private EditText nameEdit;
    private EditText timeEdit;
    private EditText noteEdit;
    private String locationStr;
    private ImageView imageHolder;

    public GoogleMap googleMap;
    public LatLng myPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_encounters_detail_edit);

        this.nameEdit   = (EditText)findViewById(R.id.name_edit);
        this.timeEdit   = (EditText)findViewById(R.id.time_edit);
        this.noteEdit   = (EditText)findViewById(R.id.note_edit);
        imageHolder = (ImageView) findViewById(R.id.image_view);


        Bundle extras = getIntent().getExtras();
        this.nameEdit.setText(extras.getString("ENCOUNTER_NAME"));
        this.timeEdit.setText(extras.getString("ENCOUNTER_TIME"));
        this.noteEdit.setText(extras.getString("ENCOUNTER_NOTE"));
        this.locationStr = extras.getString("ENCOUNTER_LOCATION");


        //Log.v("+++++++++", "photo received: " + extras.getString("ENCOUNTER_PHOTO"));
        imageHolder.setImageURI(Uri.parse(extras.getString("ENCOUNTER_PHOTO")));



        // =============================== Google Map ==============================================
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


        Button button = (Button) findViewById(R.id.done_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                EditText nameEdit   = (EditText)findViewById(R.id.name_edit);
//                EditText timeEdit   = (EditText)findViewById(R.id.time_edit);
//                EditText noteEdit   = (EditText)findViewById(R.id.note_edit);

                Intent done_intent = new Intent(ViewEncountersDetailEditActivity.this, ViewEncountersDetailActivity.class);
                done_intent.putExtra("NEW_NAME", nameEdit.getText().toString());
                done_intent.putExtra("NEW_TIME", timeEdit.getText().toString());
                done_intent.putExtra("NEW_NOTE", noteEdit.getText().toString());
                done_intent.putExtra("ENCOUNTER_LOCATION", locationStr);

                done_intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(done_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_encounters_detail_edit, menu);
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

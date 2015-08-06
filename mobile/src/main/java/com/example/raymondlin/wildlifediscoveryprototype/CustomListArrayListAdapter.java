package com.example.raymondlin.wildlifediscoveryprototype;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raymondlin.wildlifediscoveryprototype.R;
import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CustomListArrayListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> itemname;
    private final ArrayList<String> itemidentification;
    private final ArrayList<String> itemnote;
    private final ArrayList<String> itemphoto;
    private final ArrayList<String> itemlocation;
    private final Location myLocation;

    public CustomListArrayListAdapter(Activity context, ArrayList<String> itemname, ArrayList<String> itemidentification, ArrayList<String> itemlocation, ArrayList<String> itemnote, ArrayList<String> itemphoto, Location myLocation) {
        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.itemname = itemname;
        this.itemidentification = itemidentification;
        this.itemlocation = itemlocation;
        this.itemnote = itemnote;
        this.itemphoto = itemphoto;
        this.myLocation = myLocation;
    }

    public double getDistance(double lat, double lng) {
        Location encounterLocation = new Location("Encounter Location");
        encounterLocation.setLatitude(lat);
        encounterLocation.setLongitude(lng);
        if (myLocation != null){
            return myLocation.distanceTo(encounterLocation);
        } else {
            return 0;
        }
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname.get(position));
        imageView.setImageURI(Uri.parse(itemphoto.get(position)));
        // extratxt.setText(itemnote.get(position));

        String[] locationArray = itemlocation.get(position).split(",");
        double encounterLatitude = Double.parseDouble(locationArray[0]);
        double encounterLongitude = Double.parseDouble(locationArray[1]);

        DecimalFormat df = new DecimalFormat("#.00");
        double distance = getDistance(encounterLatitude, encounterLongitude);

        String desStr = null;
        if (itemidentification.get(position).equals("True")) {
            desStr = df.format(distance) + " Meters" + "           Need Identification";
        } else if (itemidentification.get(position).equals("False")){
            desStr = df.format(distance) + " Meters";
        } else if (itemidentification.get(position).equals("Updated")) {
            desStr = df.format(distance) + " Meters" + "                    Identified";
        }
        extratxt.setText(desStr);

        return rowView;
    }
}
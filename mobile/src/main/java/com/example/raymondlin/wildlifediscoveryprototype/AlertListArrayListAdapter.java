package com.example.raymondlin.wildlifediscoveryprototype;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raymondlin.wildlifediscoveryprototype.R;

import java.util.ArrayList;

public class AlertListArrayListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    public final ArrayList<String> itemname;
    public final ArrayList<String> itemnote;
    public final ArrayList<Integer> itemradius;
    public final ArrayList<Integer> itemID;

    public AlertDB alertdb;

    public AlertListArrayListAdapter(Activity context, ArrayList<String> itemname, ArrayList<String> itemnote, ArrayList<Integer> itemradius, ArrayList<Integer> itemID) {
        super(context, R.layout.alertlist, itemname);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.itemname = itemname;
        this.itemnote = itemnote;
        this.itemradius = itemradius;
        this.itemID = itemID;
        notifyDataSetChanged();
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.alertlist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        TextView radiustxt = (TextView) rowView.findViewById(R.id.radiusView);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname.get(position));
        String radius = "Within " + itemradius.get(position).toString() + " Meters Away";
        radiustxt.setText(radius);
        extratxt.setText(itemnote.get(position));
        return rowView;

//        ArrayList<String> nameList = new ArrayList<String>();
//        ArrayList<Integer> radiusList = new ArrayList<Integer>();
//        ArrayList<String> noteList = new ArrayList<String>();
//        for (String name : itemname) {
//            nameList.add(name);
//        }
//        for (Integer radius : itemradius) {
//            radiusList.add(radius);
//        }
//        for (String note : itemnote) {
//            noteList.add(note);
//        }
//
//        txtTitle.setText(nameList.get(position));
//        String radius = "Within " + radiusList.get(position).toString() + " Meters Away";
//        radiustxt.setText(radius);
//        extratxt.setText(noteList.get(position));
//
//        Button deleteButton = (Button) rowView.findViewById(R.id.delete_button);
//        deleteButton.setTag(position);
//
//        alertdb = new AlertDB(context);
//
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                final Integer index = (Integer) v.getTag();
//                Log.v("DDDDDDDDD", "delete button pressed with index: " + index);
//                for (String name: alertdb.getAllAnimals()) {
//                    Log.v("DDDDDDDDD", "before: " + name);
//                }
//                alertdb.deleteAlert(alertdb.getAllID().get(index));
//                notifyDataSetChanged();
//                v.setAdapter(new AlertListArrayListAdapter(this, mResId, mItemsList));
//                for (String name: alertdb.getAllAnimals()) {
//                    Log.v("DDDDDDDDD", "after: " + name);
//                }
//            }
//        });
//
//        return rowView;
    }
}


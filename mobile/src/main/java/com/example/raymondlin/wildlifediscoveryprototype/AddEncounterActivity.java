package com.example.raymondlin.wildlifediscoveryprototype;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by raymondlin on 7/29/15.
 */
public class AddEncounterActivity extends Activity {

    Button mButton;
    EditText time;
    EditText date;
    EditText location;
    EditText species;
    EditText note;
    String photoString;
    ImageButton rb;
    //RadioButton rb2;
    CheckBox checkb;



    EditText time1;
    EditText date1;
    EditText location1;
    EditText species1;
    EditText note1;
    boolean che;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_encounter);
        Bundle extras = getIntent().getExtras();
        String tookp = extras.getString("tookpic");
        Log.v("tag", tookp + " it says");
        time1   = (EditText)findViewById(R.id.editText4);
        date1   = (EditText)findViewById(R.id.editText5);
        location1 = (EditText)findViewById(R.id.editText8);
        species1 = (EditText)findViewById(R.id.editText10);
        note1 = (EditText)findViewById(R.id.editText9);
        if (tookp.equals("true")) {
            photoString = extras.getString("i");
            Uri myUri = Uri.parse(extras.getString("i"));
            ImageView imgView = (ImageView) findViewById(R.id.imageView);
            imgView.setImageURI(myUri);


            String time = extras.getString("time");
            String date = extras.getString("date");
            String location = extras.getString("location");

            String species = extras.getString("species");
            String note = extras.getString("note");
            String check = extras.getString("needsid");
            Log.v("tag", "check: " + check);
            if (check.equals("true")){
                che = true;
            }


            time1.setText(time);
            date1.setText(date);
            location1.setText(location);
            if (che) {
                species1.setText("Unknown");
            } else {
                species1.setText(species);
            }
            note1.setText(note);




        }
        android.util.Log.i("tag", "here");
        Calendar calobj = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        time1.setText(sdf.format(calobj.getTime()));

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        //get current date time with Date()
        Date d = new Date();

        date1.setText(dateFormat.format(d));
        rb = (ImageButton)findViewById(R.id.camera);
        final Intent i = new Intent(this, TakePhoto.class);

        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("species", species1.getText().toString());
                i.putExtra("time", time1.getText().toString());
                i.putExtra("date", date1.getText().toString());
                i.putExtra("note", note1.getText().toString());
                i.putExtra("location", location1.getText().toString());
                i.putExtra("tookpic", "true");
                if (che){
                    i.putExtra("needsid", "true");
                    Log.v("tag", "put in true to photo");
                } else {
                    i.putExtra("needsid", "false");
                    Log.v("tag", "put in false to photo");
                }
                startActivity(i);
            }
        });

        //rb2 = (RadioButton)findViewById(R.id.radioButton2);
        checkb = (CheckBox)findViewById(R.id.checkBox);
        //if (checkb.isChecked()) {
        // che = true;
        // Log.v("tag", "che is true");
        //}
        checkb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    che = true;
                    Log.v("tag", "che is true");

                }

            }
        });




        mButton = (Button)findViewById(R.id.button4);
        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        done();
                    }
                });
    }

//    public void onRadioButtonClicked(View view) {
//        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.radioButton:
//                if (checked)
//                    rb.toggle();
//                    Intent i = new Intent(this, TakePhoto.class);
//                    i.putExtra("species", species1.getText().toString());
//                    i.putExtra("time", time1.getText().toString());
//                    i.putExtra("date", date1.getText().toString());
//                    i.putExtra("note", note1.getText().toString());
//                    i.putExtra("location", location1.getText().toString());
//                    i.putExtra("tookpic", "true");
//                    if (che){
//                        i.putExtra("needsid", "true");
//                        Log.v("tag", "put in true to photo");
//                    } else {
//                        i.putExtra("needsid", "false");
//                        Log.v("tag", "put in false to photo");
//
//                    }
//                    startActivity(i);
//
//                    break;
//            case R.id.radioButton2:
//                if (checked)
//
//                    che = true;
//                    Log.v("tag", "che is true");
//
//
//                    break;
//        }
//    }

    public void done(){
        time   = (EditText)findViewById(R.id.editText4);
        date   = (EditText)findViewById(R.id.editText5);
        location = (EditText)findViewById(R.id.editText8);
        species = (EditText)findViewById(R.id.editText10);
        note = (EditText)findViewById(R.id.editText9);


        //DBHelper db = new DBHelper(this);
        // db.insertEncounter(species.getText().toString(), time.getText().toString() + " " + date.getText().toString(), note.getText().toString(), location.getText().toString(), photoString);



        Intent i = new Intent(this, ReviewEncounter.class);
        i.putExtra("species", species.getText().toString());
        i.putExtra("time", time.getText().toString());
        i.putExtra("date", date.getText().toString());
        i.putExtra("note", note.getText().toString());
        i.putExtra("location", location.getText().toString());
        i.putExtra("photo", photoString);
        if (che) {
            i.putExtra("needsid", "true");
            Log.v("tag", "needsid is true");
        } else{
            i.putExtra("needsid", "false");
            Log.v("tag", "needsid is false");
        }
        startActivity(i);

    }
}

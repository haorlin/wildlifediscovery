package com.example.raymondlin.wildlifediscoveryprototype;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ReviewEncounter extends ActionBarActivity {

    public String time;
    public String date;
    public String location;
    public String photo;
    public String note;
    public String species;
    public Button mButton;
    public Button back;
    public String needsid;
    DBHelper db;
    ProfileDB db2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_encounter);

        Bundle extras = getIntent().getExtras();
        time = extras.getString("time");
        date = extras.getString("date");
        location = extras.getString("location");
        photo = extras.getString("photo");
        species = extras.getString("species");
        note = extras.getString("note");
        needsid = extras.getString("needsid");

        TextView s = (TextView) findViewById(R.id.textView18);
        TextView n = (TextView) findViewById(R.id.textView19);
        TextView d = (TextView) findViewById(R.id.textView16);
        TextView l = (TextView) findViewById(R.id.textView17);
        TextView t = (TextView) findViewById(R.id.textView15);
        if (needsid.equals("true")){
            s.setText("Unknown");
        } else {
            s.setText(species);
        }
        n.setText(note);
        d.setText(date);
        t.setText(time);
        l.setText(location);


        Uri myUri = Uri.parse(photo);
        ImageView imgView = (ImageView) findViewById(R.id.imageView2);
        imgView.setImageURI(myUri);

        final Intent doneAdding = new Intent(this, MainMenuActivity.class);
        doneAdding.putExtra("GO_TO_TAB", 1);

        mButton = (Button)findViewById(R.id.button5);
        mButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        add();
                        startActivity(doneAdding);
                        Toast.makeText(ReviewEncounter.this, "Encounter successfully added", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

        back = (Button)findViewById(R.id.button6);
        back.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        goBack();


                    }
                });



    }

    public void goBack(){
        finish();
    }

    public void add(){
        db = new DBHelper(this);
        db2 = new ProfileDB(this);
        //.......................................need change identification field
        String b = "";
        if (needsid.equals("true")){
            b = "True";
        } else{
            b = "False";
        }
        db.insertEntry(species, time, b, note, "37.86628242,-122.29113579", photo);
        db2.insertEntry(species, time, b, note, "37.86628242,-122.29113579", photo);
//        "Sealion",    "07:45", "baby sealion is sooo cute!", "37.86628242,-122.29113579", imageUriStrList.get(12)


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_review_encounter, menu);
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
    public void onStop () {
        super.onStop();
        db.close();
        db2.close();
    }
}


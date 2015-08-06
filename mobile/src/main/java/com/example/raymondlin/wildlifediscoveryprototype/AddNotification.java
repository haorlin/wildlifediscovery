package com.example.raymondlin.wildlifediscoveryprototype;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by raymondlin on 7/31/15.
 */
public class AddNotification extends Activity {


    AlertDB mydb;

    Button back, set;
    EditText ed4, ed5, ed6;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_main);

        final Intent main_menu = new Intent(this, MainMenuActivity.class);
        main_menu.putExtra("GO_TO_TAB", 2);

        back = (Button)findViewById(R.id.button3);
        set = (Button)findViewById(R.id.button4);

        ed4 = (EditText)findViewById(R.id.editText4);
        ed5 = (EditText)findViewById(R.id.editText5);
        ed6 = (EditText)findViewById(R.id.editText6);

        mydb = new AlertDB(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed4.setText("");
                ed5.setText("");
                ed6.setText("");

                startActivity(main_menu);
                finish();
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ed4.getText().toString().length() > 0 && ed5.getText().toString().length() > 0 && ed6.getText().toString().length() > 0) {

                    String animal_name = ed4.getText().toString();
                    int radius = Integer.parseInt(ed5.getText().toString());
                    String note = ed6.getText().toString();

                    mydb.insertAlert(animal_name, note, radius);


                    Toast.makeText(AddNotification.this, "Alert has been set", Toast.LENGTH_LONG).show();
                    startActivity(main_menu);
                    finish();

                } else {
                    Toast.makeText(AddNotification.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

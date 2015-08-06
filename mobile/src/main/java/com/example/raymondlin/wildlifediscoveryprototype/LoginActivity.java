package com.example.raymondlin.wildlifediscoveryprototype;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    EditText ed1, ed2;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);




        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        Button login_button = (Button) findViewById(R.id.button);
        Button register_button = (Button) findViewById(R.id.button2);

        final Intent register = new Intent(this, RegisterActivity.class);
        final Intent main_menu = new Intent(this, MainMenuActivity.class);

        register_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                startActivity(register);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1 = (EditText)findViewById(R.id.editText);
                ed2 = (EditText)findViewById(R.id.editText2);
                username = ed1.getText().toString();
                password = ed2.getText().toString();

                if (!sharedpreferences.contains(username)) {
                    Toast.makeText(LoginActivity.this, "This username does not exist, please register", Toast.LENGTH_LONG).show();
                } else if (sharedpreferences.getString(username, "no").equals(password)) {
                    Toast.makeText(LoginActivity.this, "Successful Log In", Toast.LENGTH_LONG).show();
                    startActivity(main_menu);
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong Password, Please Try Again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}

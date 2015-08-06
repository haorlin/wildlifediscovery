package com.example.raymondlin.wildlifediscoveryprototype;

/**
 * Created by raymondlin on 7/21/15.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends ActionBarActivity {
    EditText ed1,ed2,ed3;
    Button b1, b2;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);

        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);

        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ed1.getText().toString();
                String password = ed2.getText().toString();
                String email = ed3.getText().toString();

                if (sharedpreferences.contains(username)) {
                    Toast.makeText(RegisterActivity.this, "Username already exists, please choose another", Toast.LENGTH_LONG).show();
                } else {
                    if (password.length() < 4) {
                        Toast.makeText(RegisterActivity.this, "Password must be 8 characters or more", Toast.LENGTH_LONG).show();
                    } else if (!email.contains("@")) {
                        Toast.makeText(RegisterActivity.this, "Invalid Email", Toast.LENGTH_LONG).show();
                    } else {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(username, password);
                        editor.commit();
                        Toast.makeText(RegisterActivity.this, "You have successfully registered", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

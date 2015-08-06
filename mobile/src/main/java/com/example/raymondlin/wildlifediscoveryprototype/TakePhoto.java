package com.example.raymondlin.wildlifediscoveryprototype;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TakePhoto extends ActionBarActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    public String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    public Uri my;
    Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        dispatchTakePictureIntent();
        extras = getIntent().getExtras();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_take_photo, menu);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            android.util.Log.i("tag", "got to on Activity Result");


            // Bitmap imageBitmap = (Bitmap) extras.get("data");
            // mImageView.setImageBitmap(imageBitmap);
            //mImageView.setImageURI(mCurrentPhotoPath);
            Intent i = new Intent(this, AddEncounterActivity.class);

            i.putExtra("i", my.toString());

            String time = extras.getString("time");
            String date = extras.getString("date");
            String location = extras.getString("location");

            String species = extras.getString("species");
            String note = extras.getString("note");
            String needsid = extras.getString("needsid");

            i.putExtra("time", time);
            i.putExtra("date", date);
            i.putExtra("location", location);
            i.putExtra("species", species);
            i.putExtra("note", note);
            i.putExtra("tookpic", "true");
            i.putExtra("needsid", needsid);


            startActivity(i);


        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

                my = Uri.fromFile(photoFile);
                android.util.Log.i("tag", "done with dispatch");
                // Intent i = new Intent(this, makeTweet.class);
                //   i.putExtra("imageUri", myUri.toString());
                //  startActivity(i);

            }
        }
    }
}
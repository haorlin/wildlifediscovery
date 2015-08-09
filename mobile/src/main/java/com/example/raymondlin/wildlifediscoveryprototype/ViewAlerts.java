//package com.example.raymondlin.wildlifediscoveryprototype;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.PendingIntent;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.support.v4.app.NotificationCompat;
//import android.support.v4.app.NotificationManagerCompat;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.Toast;
//
///**
// * Created by raymondlin on 7/31/15.
// */
//public class ViewAlerts extends Activity {
//
//    AlertDB mydb;
//    ListView listView ;
//    Button add, back;
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.view_alerts);
//
//
////        Button sendButton = (Button) findViewById(R.id.send_button);
////        sendButton.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                // TODO Auto-generated method stub
////                // createWearNotification();
////                startDetection();
////            }
////        });
//
//
//        final Intent addAlert = new Intent(this, AddNotification.class);
//        add = (Button)findViewById(R.id.add);
//
//        mydb = new AlertDB(this);
//
//        final Intent main_menu = new Intent(this, MainMenuActivity.class);
//        main_menu.putExtra("GO_TO_TAB", 2);
//
//        final AlertListArrayListAdapter adapter=new AlertListArrayListAdapter(this, mydb.getAllAnimals(), mydb.getAllNotes(), mydb.getAllRadius(), mydb.getAllRadius());
//        listView = (ListView)findViewById(R.id.listview);
//        listView.setAdapter(adapter);
//
//
//
//        // Scroll View
//        ListView lv = (ListView) findViewById(R.id.listview);
//        lv.setOnTouchListener(new View.OnTouchListener() {
//            // Setting on Touch Listener for handling the touch inside ScrollView
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // Disallow the touch request for parent scroll on touch of child view
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
//
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                startActivity(addAlert);
//            }
//        });
//
//
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//
////                final Integer int_id = adapter.itemID.get(position);
//                final Integer int_id = position;
//
//                new AlertDialog.Builder(ViewAlerts.this)
//                        .setTitle("Delete entry")
//                        .setMessage("Are you sure you want to delete this entry?")
//                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // continue with delete
////                                mydb.deleteAlert(int_id);
//                                Toast.makeText(ViewAlerts.this, "Alert has been deleted", Toast.LENGTH_LONG).show();
//                                adapter.remove(adapter.getItem(int_id));
//                                adapter.notifyDataSetChanged();
//
//                            }
//                        })
//                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // do nothing
//                            }
//                        })
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .show();
//
//                return true;
//            }
//        });
//    }
//
//    protected void startDetection() {
//        Intent detectionIntent = new Intent(this, MyCurrentLocationIntentService.class);
//        startService(detectionIntent);
//    }
//
//
//    protected void createWearNotification(){
//        int notificationId = 2;
//        Intent mainIntent = new Intent(this, MainActivity.class);
//        PendingIntent viewPendingIntent =
//                PendingIntent.getActivity(this, 0, mainIntent, 0);
//        // Build an intent
//
//        Log.v("Wear Notification", "Start build others' notification");
//
//        NotificationCompat.WearableExtender extender = new NotificationCompat.WearableExtender();
//        extender.setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.hero));
//        extender.setHintHideIcon(true);
//
//        NotificationCompat.Builder notificationBuilder =
//                new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.drawable.hero)
//                        .setContentTitle("Someone else!!")
//                        .setContentIntent(viewPendingIntent);
//        notificationBuilder.extend(extender);
//
//        notificationBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
//        notificationBuilder.setContentText("Peter is also excited!");
//        notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.hero));
//
//        // Get an instance of the NotificationManager service
//        NotificationManagerCompat notificationManager =
//                NotificationManagerCompat.from(this);
//
//        // Build the notification and issues it with notification manager.
//        notificationManager.notify(notificationId, notificationBuilder.build());
//        //
//        Log.v("Wear Notification", "Other's tweet Notication Built");
//    }
//}

package com.example.raymondlin.wildlifediscoveryprototype;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by raymondlin on 7/31/15.
 */
public class ViewAlerts extends Activity {

    AlertDB mydb;
    ListView listView ;
    ImageButton add, back;
    TextView empty, title;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_alerts);



        final Intent addAlert = new Intent(this, AddNotification.class);
        add = (ImageButton)findViewById(R.id.ImageButton1);

        mydb = new AlertDB(this);

        final Intent main_menu = new Intent(this, MainMenuActivity.class);
        main_menu.putExtra("GO_TO_TAB", 2);


        empty = (TextView) findViewById(R.id.empty);
        title = (TextView) findViewById(R.id.cc);

        Typeface tf = Typeface.createFromAsset(getAssets(), "RobotoTTF/Roboto-Black.ttf");
        empty.setTypeface(tf);
        title.setTypeface(tf);
        empty.setVisibility(View.INVISIBLE);

        final AlertListArrayListAdapter adapter=new AlertListArrayListAdapter(this, mydb.getAllAnimals(), mydb.getAllNotes(), mydb.getAllRadius(), mydb.getAllRadius());
        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);

        // Scroll View
        ListView lv = (ListView) findViewById(R.id.listview);
        lv.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(addAlert);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ViewAlerts.this, "Long hold to delete alert", Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

//                final Integer int_id = adapter.itemID.get(position);

                final Integer int_id = position;
                final String animal = adapter.getItem(position).toString();
                final Integer delete_id = mydb.findID(animal).get(0);

                new AlertDialog.Builder(ViewAlerts.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete

                                Log.v("animal name@@@@@@", animal);


                                mydb.deleteAlert(delete_id);
                                Toast.makeText(ViewAlerts.this, "Alert has been deleted", Toast.LENGTH_LONG).show();
                                adapter.remove(adapter.getItem(int_id));
                                adapter.notifyDataSetChanged();

                                if (mydb.getAllID().isEmpty()) {
                                    empty.setVisibility(View.VISIBLE);
                                }

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                return true;
            }
        });

        if (mydb.getAllID().isEmpty()) {
            empty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStop () {
        super.onStop();
        mydb.close();
    }



}


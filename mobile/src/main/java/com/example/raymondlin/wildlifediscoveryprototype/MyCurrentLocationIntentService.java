package com.example.raymondlin.wildlifediscoveryprototype;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyCurrentLocationIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "com.example.raymondlin.wildlifediscoveryprototype.action.FOO";
    public static final String ACTION_BAZ = "com.example.raymondlin.wildlifediscoveryprototype.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "com.example.raymondlin.wildlifediscoveryprototype.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.raymondlin.wildlifediscoveryprototype.extra.PARAM2";

    public MyCurrentLocationIntentService() {
        super("MyCurrentLocationIntentService");
    }

    public ArrayList<String> animalName;
    public String animalLocaiton;

    @Override
    protected void onHandleIntent(Intent intent) {
//        if (intent != null) {
//            final String action = intent.getAction();
//            if (ACTION_FOO.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//                handleActionFoo(param1, param2);
//            } else if (ACTION_BAZ.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//                handleActionBaz(param1, param2);
//            }
//        }

        MyLocation.LocationResultt locationResult = new MyLocation.LocationResultt(){
            @Override
            public void gotLocation(Location location){
                DBHelper mydb = new DBHelper(MyCurrentLocationIntentService.this);
                AlertDB alertdb = new AlertDB(MyCurrentLocationIntentService.this);
                HashMap<String, String> nameLocationMap = new HashMap<String, String>();
                HashMap<String, Double> nameDistanceMap = new HashMap<String, Double>();
                HashMap<String, Integer> nameRadiusMap = new HashMap<String, Integer>();

                for (int i = 0; i < alertdb.getAllAnimals().size(); i++) {
                    String name = alertdb.getAllAnimals().get(i);
                    if (mydb.getAllNames().contains(name)) {
                        // Radius of encounter in the alert list
                        nameRadiusMap.put(name, alertdb.getAllRadius().get(i));
                        // Location of encounter in All Encounter
                        nameLocationMap.put(name, mydb.getAllLocations().get(mydb.getAllNames().indexOf(name)));
                    }
                }

                for (String name : nameLocationMap.keySet()) {
                    String[] locationArray = nameLocationMap.get(name).split(",");
                    double encounterLatitude = Double.parseDouble(locationArray[0]);
                    double encounterLongitude = Double.parseDouble(locationArray[1]);

                    Location encounterLocation = new Location("Encounter Location");
                    encounterLocation.setLatitude(encounterLatitude);
                    encounterLocation.setLongitude(encounterLongitude);
                    double distance;
                    distance = location.distanceTo(encounterLocation);
                    nameDistanceMap.put(name, distance);
                }

                for (String name : nameRadiusMap.keySet()) {
                    if (nameDistanceMap.get(name) <= nameRadiusMap.get(name)) {

                        // ============== Send notification request to the watch ===================
                        int notificationId = 2;
                        Intent mainIntent = new Intent(MyCurrentLocationIntentService.this, MainActivity.class);
                        PendingIntent viewPendingIntent =
                                PendingIntent.getActivity(MyCurrentLocationIntentService.this, 0, mainIntent, 0);
                        // Build an intent

                        Log.v("Wear Notification", "Start build others' notification");

                        NotificationCompat.WearableExtender extender = new NotificationCompat.WearableExtender();
                        extender.setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.hero));
                        extender.setHintHideIcon(true);

                        NotificationCompat.Builder notificationBuilder =
                                new NotificationCompat.Builder(MyCurrentLocationIntentService.this)
                                        .setSmallIcon(R.drawable.hero)
                                        .setContentTitle(name + "in Radius: " + nameRadiusMap.get(name) + " m")
                                        .setContentIntent(viewPendingIntent);
                        notificationBuilder.extend(extender);

                        notificationBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
                        notificationBuilder.setContentText(name + "in Radius: " + nameRadiusMap.get(name) + " m");
                        notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.hero));

                        // Get an instance of the NotificationManager service
                        NotificationManagerCompat notificationManager =
                                NotificationManagerCompat.from(MyCurrentLocationIntentService.this);

                        // Build the notification and issues it with notification manager.
                        notificationManager.notify(notificationId, notificationBuilder.build());
                        //
                        Log.v("Wear Notification", "Other's tweet Notication Built");
                        // ============== Send notification request to the watch ===================
                    }
                }


            }
        };
        MyLocation myLocation = new MyLocation();
        myLocation.getLocation(this, locationResult);
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.i("LocalService", "Received start id " + startId + ": " + intent);
//        // We want this service to continue running until it is explicitly
//        // stopped, so return sticky.
//        return START_STICKY;
//    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

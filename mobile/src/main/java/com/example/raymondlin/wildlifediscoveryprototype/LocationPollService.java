package com.example.raymondlin.wildlifediscoveryprototype;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class LocationPollService extends Service {
    public LocationPollService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Query the database and show alarm if it applies

        // Here you can return one of some different constants.
        // This one in particular means that if for some reason
        // this service is killed, we don't want to start it
        // again automatically
        Log.v("AAAAAAAAAAAA", "get current location..!");
        pollLocation();
        stopSelf();

        return START_NOT_STICKY;
    }

    public void pollLocation() {
        MyLocation.LocationResultt locationResult = new MyLocation.LocationResultt(){
            @Override
            public void gotLocation(Location location){
                DBHelper mydb = new DBHelper(LocationPollService.this);
                AlertDB alertdb = new AlertDB(LocationPollService.this);
                HashMap<String, String> nameLocationMap = new HashMap<String, String>();
                HashMap<String, Double> nameDistanceMap = new HashMap<String, Double>();
                HashMap<String, Integer> nameRadiusMap = new HashMap<String, Integer>();
                HashMap<String, String> namePhotoMap = new HashMap<String, String>();
                HashMap<String, String> nameNoteMap = new HashMap<String, String>();
                HashMap<String, String> nameTimeMap = new HashMap<String, String>();

                // Get notification about one specific for only one time
                HashSet<String> dupNameSet  = new HashSet<String>();

                for (int i = 0; i < alertdb.getAllAnimals().size(); i++) {
                    String name = alertdb.getAllAnimals().get(i);
                    if (!dupNameSet.contains(name)) {
                        if (mydb.getAllNames().contains(name)) {
                            // Radius of encounter in the alert list
                            nameRadiusMap.put(name, alertdb.getAllRadius().get(i));
                            // Location of encounter in All Encounter
                            nameLocationMap.put(name, mydb.getAllLocations().get(mydb.getAllNames().indexOf(name)));
                            namePhotoMap.put(name, mydb.getAllPhotos().get(mydb.getAllNames().indexOf(name)));
                            nameNoteMap.put(name, mydb.getAllNotes().get(mydb.getAllNames().indexOf(name)));
                            nameTimeMap.put(name, mydb.getAllTimes().get(mydb.getAllNames().indexOf(name)));
                        }
                    }
                }

                for (String name : nameLocationMap.keySet()) {
                    if (!dupNameSet.contains(name)) {
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
                }

                for (String name : nameRadiusMap.keySet()) {

                    if (nameDistanceMap.get(name) <= nameRadiusMap.get(name) && !dupNameSet.contains(name)) {

                        // ============== Send notification request to the watch ===================
                        int notificationId = 2;
                        Intent mainIntent = new Intent(LocationPollService.this, MainActivity.class);
                        PendingIntent viewPendingIntent =
                                PendingIntent.getActivity(LocationPollService.this, 0, mainIntent, 0);
                        // Build an intent

                        Log.v("Wear Notification", "Start build others' notification");
                        Uri photoUri = Uri.parse(namePhotoMap.get(name));
                        Bitmap photoBitmap = null;
                        try {
                            photoBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoUri);
                        } catch (IOException ioEx) {

                        }

                        NotificationCompat.WearableExtender extender = new NotificationCompat.WearableExtender();
                        extender.setBackground(photoBitmap);
                        extender.setHintHideIcon(true);

                        NotificationCompat.Builder notificationBuilder =
                                new NotificationCompat.Builder(LocationPollService.this)
                                        .setSmallIcon(R.drawable.hero)
                                        .setContentTitle(name + " in " + nameRadiusMap.get(name) + " m")
                                        .setContentIntent(viewPendingIntent);
                        notificationBuilder.extend(extender);

                        notificationBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
                        notificationBuilder.setContentText(nameTimeMap.get(name) + " " + nameNoteMap.get(name));
                        notificationBuilder.setLargeIcon(photoBitmap);


                        // Get an instance of the NotificationManager service
                        NotificationManagerCompat notificationManager =
                                NotificationManagerCompat.from(LocationPollService.this);

                        // Build the notification and issues it with notification manager.
                        notificationManager.notify(notificationId, notificationBuilder.build());
                        //
                        Log.v("Wear Notification", "Other's tweet Notication Built");
                        // ============== Send notification request to the watch ===================
                        dupNameSet.add(name);
                        alertdb.deleteAlert(alertdb.getAllID().get(alertdb.getAllAnimals().indexOf(name)));
                    }
                }


            }
        };
        MyLocation myLocation = new MyLocation();
        myLocation.getLocation(this, locationResult);
    }



    @Override
    public void onDestroy() {
        // I want to restart this service again in one hour
        final Handler h = new Handler();
        final int delay = 1000 * 15; //milliseconds

        Log.v("AAAAAAAAAA", "service destoried");
        h.postDelayed(new Runnable(){
            public void run(){
                //do something
                //PendingIntent.getService(LocationPollService.this, 0, new Intent(LocationPollService.this, LocationPollService.class), 0);
                Intent intent = new Intent(LocationPollService.this, LocationPollService.class);
                startService(intent);
                h.postDelayed(this, delay);
            }
        }, delay);
    }
}

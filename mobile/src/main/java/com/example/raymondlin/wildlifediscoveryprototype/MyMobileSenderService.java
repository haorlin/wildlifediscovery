package com.example.raymondlin.wildlifediscoveryprototype;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chengxianghu on 7/9/15.
 */
public class MyMobileSenderService extends IntentService {
    private static final String TAG = "MyMobileSenderService";
    private GoogleApiClient mGoogleApiClient;
    public static final String CAPABILITY_NAME = "wear_noti";
    public static final String RECEIVER_SERVICE_PATH = "/wear-receiver-service";
    private String nodeId = null;

    public MyMobileSenderService() {
        super(MyMobileSenderService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        // Step 2: Creating a GoogleApiClient
        this.mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                        // Do something
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        // Do something
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                        // Do something
                    }
                })
                .addApi(Wearable.API)
                .build();
        this.mGoogleApiClient.connect();


        // Step 3: Finding Nodes with Capabilities
        // capResult: a lists of Nodes with the capability that was asked for.
        CapabilityApi.GetCapabilityResult capResult =
                Wearable.CapabilityApi.getCapability(
                        mGoogleApiClient, CAPABILITY_NAME,
                        CapabilityApi.FILTER_REACHABLE)
                        .await();

        for (Node node:capResult.getCapability().getNodes()){
            Wearable.MessageApi.sendMessage(mGoogleApiClient, node.getId(), RECEIVER_SERVICE_PATH, new byte[3]);}
        Log.d(TAG, "Message Sent");

    }

    private void setupVoiceTranscription() {
        CapabilityApi.CapabilityListener capabilityListener =
                new CapabilityApi.CapabilityListener() {
                    @Override
                    public void onCapabilityChanged(CapabilityInfo capabilityInfo) {
                        updateTranscriptionCapability(capabilityInfo);
                    }
                };

        Wearable.CapabilityApi.addCapabilityListener(
                mGoogleApiClient,
                capabilityListener,
                CAPABILITY_NAME);
    }

    private void updateTranscriptionCapability(CapabilityInfo capabilityInfo) {
        Set<Node> connectedNodes = capabilityInfo.getNodes();

        nodeId = pickBestNodeId(connectedNodes);
    }

    private String pickBestNodeId(Set<Node> nodes) {
        String bestNodeId = null;
        // Find a nearby node or pick one arbitrarily
        for (Node node : nodes) {
            if (node.isNearby()) {
                return node.getId();
            }
            bestNodeId = node.getId();
        }
        return bestNodeId;
    }

    private void requestTranscription(byte[] byteData) {
        if (nodeId != null) {
            Wearable.MessageApi.sendMessage(mGoogleApiClient, nodeId,
                    RECEIVER_SERVICE_PATH, byteData);
        } else {
            // // Unable to retrieve node with transcription capability
        }
    }

    private Collection<String> getNodes() {
        HashSet<String> results = new HashSet<String>();
        NodeApi.GetConnectedNodesResult nodes =
                Wearable.NodeApi.getConnectedNodes(mGoogleApiClient).await();
        for (Node node : nodes.getNodes()) {
            results.add(node.getId());
        }
        return results;
    }

}


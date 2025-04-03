package com.bluebinaries.carrental.services.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import com.bluebinaries.carrental.R;
import com.bluebinaries.carrental.services.CarRentalService;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMService extends FirebaseMessagingService {

    private final CarRentalService mCarService;

    private static final String LOG_TAG = "FCMService";

    public FCMService() {
        mCarService = CarRentalService.getInstance();
    }

    public void initialise() {
        // initialises Firebase Messaging service
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (!remoteMessage.getData().isEmpty()) {
            Log.d(LOG_TAG, "Message data payload: " + remoteMessage.getData());

            String message = remoteMessage.getData().get("message");
            showNotification(message);
        }

        // If the message contains a notification payload
        if (remoteMessage.getNotification() != null) {
            Log.d(LOG_TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            // You can send a notification here using NotificationManager
        }
    }

    @Override
    public void onNewToken(String token) {
        // This method is called when a new token is generated for your device.
        Log.d(LOG_TAG, "New Token: " + token);
        // You can send this token to your server to associate it with the user or device.
    }

    public void showNotification(String message) {
        // Code to send notification (using Firebase Cloud FCMService)
        // Need to set up a Cloud Function or backend to send notifications
        Notification notification = new Notification.Builder(mCarService.getContext())
                .setContentTitle("Car Rental App")
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

}

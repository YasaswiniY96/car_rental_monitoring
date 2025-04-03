package com.bluebinaries.carrental.services.locationprovider;

import android.location.Location;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;

import com.bluebinaries.carrental.services.CarRentalService;
import com.bluebinaries.carrental.util.PermissionUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;

public class LocationProviderService {

    private CarRentalService mCarService;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private LocationRequest mLocationRequest;

    private static final String LOG_TAG = "LocationProviderService";

    public void initialise() {
        mCarService = CarRentalService.getInstance();

        requestLocationUpdates();
        startLocationUpdates();

    }

    private void requestLocationUpdates() {
        if(PermissionUtils.isLocationPermissionEnabled()) {
//            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.getMainLooper());
        }
    }

    private void startLocationUpdates() {

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mCarService.getContext());
        // Create LocationRequest
        mLocationRequest = new LocationRequest()
                .setInterval(1000)  // 1 second interval
                .setFastestInterval(500)  // Fastest update interval (milliseconds)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if (!locationResult.getLocations().isEmpty()) {
                    Location location = locationResult.getLastLocation();
                    if(location != null) {
                        float speed = location.getSpeed() * 3.6f;  // Convert m/s to km/h
                        Log.d(LOG_TAG, "Current Speed: " + speed + " km/h");
                        checkSpeed(speed);
                    }
                }
            }
        };

    }

    private void checkSpeed(float speed) {
        if (speed > mCarService.getSpeedLimit()) {
            Log.d(LOG_TAG , "SPEED Limit exceeded : "+speed);
            sendSpeedAlert(speed);
        }
    }

    private void sendSpeedAlert(float speed) {
        // Here you can trigger a Firebase Notification or update Firebase database
        String message = "Speed exceeded: " + speed + " km/h";

        Log.d(LOG_TAG, "Sending speed alert : " + message);
        // Send notification using Firebase Cloud FCMService (FCM)
        mCarService.sendSpeedAlert(speed, message);
    }

    public void unregister() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }
}

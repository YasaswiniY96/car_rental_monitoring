package com.bluebinaries.carrental.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.bluebinaries.carrental.model.CarData;
import com.bluebinaries.carrental.model.UserData;
import com.bluebinaries.carrental.services.firebase.FCMService;
import com.bluebinaries.carrental.services.firebase.FFirestoreService;
import com.bluebinaries.carrental.services.firebase.FRTDBService;
import com.bluebinaries.carrental.services.locationprovider.LocationProviderService;
import com.bluebinaries.carrental.util.TimeUtils;

public class CarRentalService extends Service {

    private static  CarRentalService mService = null;

    private Context mContext;
    private LocationProviderService mLocationProvider;
    private FCMService mFcmService;
    private FRTDBService mFrtdbService;
    private FFirestoreService mFFirestoreService;

    private UserData mUserData;
    private CarData mCarData;
    private TimeUtils TimeUtils;

    private CarRentalService() {
    }

    public static CarRentalService getInstance() {
        if(mService == null){
            mService = new CarRentalService();
        }
        return mService;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getContext().getApplicationContext();
        initialise();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unregister();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public Context getContext() {
        return mContext;
    }

    private void initialise() {
        mUserData = new UserData();
        mCarData = new CarData();

        mLocationProvider = new LocationProviderService();
        mLocationProvider.initialise();

        mFcmService = new FCMService();
        mFrtdbService = new FRTDBService();
        mFFirestoreService = new FFirestoreService();
    }

    public void sendSpeedAlert(float speed, String alertMessage) {
        //send notification message to FCMService and store in FRTDB
        mFcmService.showNotification(alertMessage);
        mFrtdbService.pushSpeedAlertToDatabase(getDriverID(), speed, getTimeStamp());
    }

    public String getDriverID() {
        return mUserData.getDriverID();
    }

    public float getSpeedLimit() {
        return mCarData.getSpeedLimit();
    }

    public long getTimeStamp() {
        return TimeUtils.getTimeStamp();
    }

    private void unregister() {
        mLocationProvider.unregister();
    }
}

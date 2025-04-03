package com.bluebinaries.carrental.services.firebase;

import com.bluebinaries.carrental.model.SpeedAlert;
import com.bluebinaries.carrental.services.CarRentalService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FRTDBService {

    private CarRentalService mCarService;

    public void initialise() {
        // initialises Firebase RealTime Database

        mCarService = CarRentalService.getInstance();
    }

    public void pushSpeedAlertToDatabase(String driverId, float speed, long timestamp) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        SpeedAlert speedAlert = new SpeedAlert(driverId, speed, timestamp);
        database.child("speed_alerts").push().setValue(speedAlert);
    }

}

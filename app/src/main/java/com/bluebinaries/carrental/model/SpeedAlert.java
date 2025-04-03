package com.bluebinaries.carrental.model;

public class SpeedAlert {
    private String driverId;
    private float speed;
    private long timeStamp;
    public SpeedAlert(String driverId, float speed, long timeStamp) {
        this.driverId = driverId;
        this.speed = speed;
        this.timeStamp = timeStamp;
    }
}

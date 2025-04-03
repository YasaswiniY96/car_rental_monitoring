package com.bluebinaries.carrental.util;

public final class TimeUtils {

    private static final String LOG_TAG = "TimeUtils";

    private TimeUtils() {
        // Private constructor to prevent instantiation
    }

    public static long getTimeStamp() {
        return System.currentTimeMillis();
    }
}

package com.boes.guideproject.core;

public class GuideMarker {

    private final int guidePosition;
    private final double latitude;
    private final double longitude;

    public GuideMarker(int guidePosition, double latitude, double longitude) {
        this.guidePosition = guidePosition;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getPosition() {
        return guidePosition;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}

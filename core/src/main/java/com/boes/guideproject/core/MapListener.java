package com.boes.guideproject.core;

public interface MapListener {
    void onMarkerClick(int guidePosition, double latitude, double longitude);
    void onMapClick();
}

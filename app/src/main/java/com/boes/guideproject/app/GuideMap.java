package com.boes.guideproject.app;


public interface GuideMap {
    void addMarker(String title, double latitude, double longitude);
    void setCameraTo(double latitude, double longitude, int zoom);
}

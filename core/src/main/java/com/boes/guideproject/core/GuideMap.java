package com.boes.guideproject.core;

public interface GuideMap {
    void addMarker(String title, double latitude, double longitude);
    void centerAt(double latitude, double longitude);
    void setGuideMarkerStyle();
    void setPlaceMarkerStyle();
}

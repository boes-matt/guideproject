package com.boes.guideproject.core;

public interface GuideMap {
    void addMarker(int guidePosition, String title, double latitude, double longitude);
    void centerAt(double latitude, double longitude);
    void animateTo(double latitude, double longitude);
    void animateToGuidePosition(int guidePosition);

    enum MarkerStyle {GUIDE, PLACE}
    void setMarkerStyle(MarkerStyle style);

    void setMapListener(MapListener mapListener);
}

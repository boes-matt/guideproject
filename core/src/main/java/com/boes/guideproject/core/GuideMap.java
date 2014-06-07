package com.boes.guideproject.core;

public interface GuideMap {
    void setMarker(int guidePosition, String title, double latitude, double longitude);
    void centerAt(int guidePosition);
    void animateTo(int guidePosition);

    enum MarkerStyle {GUIDE, PLACE}
    void setMarkerStyle(MarkerStyle style);

    void setPadding(int left, int top, int right, int bottom);

    void setMapListener(MapListener mapListener);
}

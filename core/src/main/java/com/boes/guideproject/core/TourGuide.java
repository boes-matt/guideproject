package com.boes.guideproject.core;

public class TourGuide implements GuideListener {

    GuideMap map;

    public TourGuide(GuideMap map) {
        this.map = map;
    }

    @Override
    public void addPlace(int position, String title, double latitude, double longitude) {
        if (position == 0) {
            map.setGuideMarkerStyle();
            map.addMarker(title, latitude, longitude);
            map.centerAt(latitude, longitude);
        } else {
            map.setPlaceMarkerStyle();
            map.addMarker(title, latitude, longitude);
        }
    }

}

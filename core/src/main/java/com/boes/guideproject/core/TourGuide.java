package com.boes.guideproject.core;

public class TourGuide implements GuideListener {

    GuideMap map;

    public TourGuide(GuideMap map) {
        this.map = map;
    }

    @Override
    public void addPlace(int position, String title, double latitude, double longitude) {
        map.addMarker(title, latitude, longitude);
        if (position == 0) map.centerAt(latitude, longitude);
    }

}

package com.boes.guideproject.core;

public class TourGuide implements
        GuideListener,
        MapListener {

    GuideMap guideMap;

    public TourGuide(GuideMap guideMap) {
        this.guideMap = guideMap;
    }

    @Override
    public void setGuide(String title, double latitude, double longitude) {
        guideMap.setMarkerStyle(GuideMap.MarkerStyle.GUIDE);
        guideMap.addMarker(title, latitude, longitude);
        guideMap.centerAt(latitude, longitude);
    }

    @Override
    public void setPlace(int position, String title, double latitude, double longitude) {
        guideMap.setMarkerStyle(GuideMap.MarkerStyle.PLACE);
        guideMap.addMarker(title, latitude, longitude);
    }

    @Override
    public void onMapClick() {

    }

}

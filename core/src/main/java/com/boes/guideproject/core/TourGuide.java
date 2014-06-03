package com.boes.guideproject.core;

public class TourGuide implements
        GuideListener,
        MapListener {

    GuideMap guideMap;
    GuideCards guideCards;

    public TourGuide(GuideMap guideMap, GuideCards guideCards) {
        this.guideMap = guideMap;
        this.guideCards = guideCards;
    }

    @Override
    public void setGuide(String title, double latitude, double longitude) {
        guideMap.setMarkerStyle(GuideMap.MarkerStyle.GUIDE);
        guideMap.addMarker(0, title, latitude, longitude);
        guideMap.centerAt(latitude, longitude);
        guideCards.setCard(0, title);
    }

    @Override
    public void setPlace(int guidePosition, String title, double latitude, double longitude) {
        guideMap.setMarkerStyle(GuideMap.MarkerStyle.PLACE);
        guideMap.addMarker(guidePosition, title, latitude, longitude);
        guideCards.setCard(guidePosition, title);
    }

    @Override
    public void onMarkerClick(int guidePosition, double latitude, double longitude) {
        guideMap.centerAt(latitude, longitude);
        guideCards.showCard(guidePosition);
    }

    @Override
    public void onMapClick() {
        guideCards.hide();
    }

}

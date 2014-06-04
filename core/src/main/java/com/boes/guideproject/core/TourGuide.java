package com.boes.guideproject.core;

public class TourGuide implements
        GuideListener,
        MapListener,
        GuideCardsListener {

    GuideMap guideMap;
    GuideCards guideCards;

    public static TourGuide build(GuideMap guideMap, GuideCards guideCards) {
        TourGuide tourGuide = new TourGuide(guideMap, guideCards);
        guideMap.setMapListener(tourGuide);
        guideCards.setGuideCardsListener(tourGuide);
        return tourGuide;
    }

    private TourGuide(GuideMap guideMap, GuideCards guideCards) {
        this.guideMap = guideMap;
        this.guideCards = guideCards;
    }

    @Override
    public void setGuide(String title, double latitude, double longitude) {
        guideMap.setMarkerStyle(GuideMap.MarkerStyle.GUIDE);
        guideMap.addMarker(0, title, latitude, longitude);
        guideMap.centerAt(latitude, longitude);
        guideCards.setCard(0, title);
        guideCards.showCard(0);
    }

    @Override
    public void setPlace(int guidePosition, String title, double latitude, double longitude) {
        guideMap.setMarkerStyle(GuideMap.MarkerStyle.PLACE);
        guideMap.addMarker(guidePosition, title, latitude, longitude);
        guideCards.setCard(guidePosition, title);
    }

    @Override
    public void onMarkerClick(int guidePosition, double latitude, double longitude) {
        guideMap.animateTo(latitude, longitude);
        guideCards.showCard(guidePosition);
    }

    @Override
    public void onMapClick() {
        guideCards.hide();
    }

    @Override
    public void onCardSelected(int guidePosition) {
        guideMap.animateToGuidePosition(guidePosition);
    }

}

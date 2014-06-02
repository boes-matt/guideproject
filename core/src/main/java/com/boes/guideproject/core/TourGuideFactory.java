package com.boes.guideproject.core;

public class TourGuideFactory {

    public static TourGuide build(GuideMap map) {
        TourGuide tourGuide = new TourGuide(map);
        map.setMapListener(tourGuide);
        return tourGuide;
    }

}

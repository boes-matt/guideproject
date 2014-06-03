package com.boes.guideproject.core;

public class TourGuideFactory {

    public static TourGuide build(GuideMap guideMap, GuideCards guideCards) {
        TourGuide tourGuide = new TourGuide(guideMap, guideCards);
        guideMap.setMapListener(tourGuide);
        return tourGuide;
    }

}

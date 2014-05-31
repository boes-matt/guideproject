package com.boes.guideproject.core;

public class DebugGuideService implements GuideService {

    GuideListener guideListener;

    public DebugGuideService(GuideListener guideListener) {
        this.guideListener = guideListener;
    }

    @Override
    public void getGuideWithId(String id) {
        guideListener.addPlace(0, "San Francisco", 37.7833, 122.4167);
    }

}

package com.boes.guideproject.core;

public class DebugGuideService implements GuideService {

    GuideListener guideListener;

    public DebugGuideService(GuideListener guideListener) {
        this.guideListener = guideListener;
    }

    @Override
    public void getGuideWithId(String id) {
        guideListener.setGuide("Stanford University", 37.42991957045861, -122.16942820698023);
        guideListener.setPlace(1, "Hoover Tower", 37.42786575240984, -122.16690257191658);
        guideListener.setPlace(2, "d.school", 37.42609810319539, -122.17188913375138);
        guideListener.setPlace(3, "Rodin Sculpture Garden", 37.43236309856673, -122.1709620952606);
    }

}

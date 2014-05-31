package com.boes.guideproject.core;

public class TourGuideController implements GuideController {

    GuideService db;
    GuideMap map;

    public TourGuideController(GuideService db, GuideMap map) {
        this.db = db;
        this.map = map;
    }

    public void beginGuide(String id) {
        db.getGuideWithId(id);
    }

    @Override
    public void addPlace(int position, String title, double latitude, double longitude) {
        map.addMarker(title, latitude, longitude);
        if (position == 0) map.centerAt(latitude, longitude);
    }

}

package com.boes.guideproject.core.test;

import com.boes.guideproject.core.GuideController;
import com.boes.guideproject.core.GuideMap;
import com.boes.guideproject.core.GuideService;
import com.boes.guideproject.core.TourGuideController;

import org.junit.Test;
import org.mockito.Mockito;

public class TourGuideControllerTest {

    private final GuideService db = Mockito.mock(GuideService.class);
    private final GuideMap map = Mockito.mock(GuideMap.class);
    private final GuideController tour = new TourGuideController(db, map);

    @Test
    public void getsGuideWhenTourBegins() {
        tour.beginGuide("123");
        Mockito.verify(db).getGuideWithId("123");
    }

    @Test
    public void addsMarkerOnMapWhenPlaceReceived() {
        tour.addPlace(5, "Golden Gate", 12, 34);
        Mockito.verify(map).addMarker("Golden Gate", 12, 34);
        Mockito.verifyNoMoreInteractions(map);
    }

    @Test
    public void centersMarkerOnMapWhenFirstPlaceReceived() {
        tour.addPlace(0, "Golden Gate", 12, 34);
        Mockito.verify(map).addMarker("Golden Gate", 12, 34);
        Mockito.verify(map).centerAt(12, 34);
    }

}

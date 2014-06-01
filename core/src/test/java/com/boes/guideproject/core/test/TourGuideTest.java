package com.boes.guideproject.core.test;

import com.boes.guideproject.core.GuideMap;
import com.boes.guideproject.core.TourGuide;

import org.junit.Test;
import org.mockito.Mockito;

public class TourGuideTest {

    private final GuideMap map = Mockito.mock(GuideMap.class);
    private final TourGuide tour = new TourGuide(map);

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

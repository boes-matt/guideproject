package com.boes.guideproject.core.test;

import com.boes.guideproject.core.GuideMap;
import com.boes.guideproject.core.TourGuide;
import com.boes.guideproject.core.TourGuideFactory;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyDouble;

public class TourGuideTest {

    private final GuideMap guideMap = Mockito.mock(GuideMap.class);
    private final TourGuide tourGuide = TourGuideFactory.build(guideMap);

    @Test
    public void setsItselfAsMapListener() {
        Mockito.verify(guideMap).setMapListener(tourGuide);
    }

    @Test
    public void centersMarkerOnMapWhenGuideSet() {
        tourGuide.setGuide("Golden Gate", 12, 34);
        Mockito.verify(guideMap).setMarkerStyle(GuideMap.MarkerStyle.GUIDE);
        Mockito.verify(guideMap).addMarker("Golden Gate", 12, 34);
        Mockito.verify(guideMap).centerAt(12, 34);
    }

    @Test
    public void addsMarkerOnMapWhenPlaceSet() {
        tourGuide.setPlace(5, "Golden Gate", 12, 34);
        Mockito.verify(guideMap).setMarkerStyle(GuideMap.MarkerStyle.PLACE);
        Mockito.verify(guideMap).addMarker("Golden Gate", 12, 34);
        Mockito.verify(guideMap, Mockito.never()).centerAt(anyDouble(), anyDouble());
    }

    @Test
    public void centersMarkerOnMarkerClick() {
        // TODO
    }

    @Test
    public void showsGuideCardOnMarkerClick() {
        // TODO
    }

    @Test
    public void hidesGuideCardsOnMapClick() {
        tourGuide.onMapClick();
        // TODO Mockito.verify ...
    }

}

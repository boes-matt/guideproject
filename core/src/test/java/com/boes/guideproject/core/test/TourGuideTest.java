package com.boes.guideproject.core.test;

import com.boes.guideproject.core.GuideCards;
import com.boes.guideproject.core.GuideMap;
import com.boes.guideproject.core.TourGuide;
import com.boes.guideproject.core.TourGuideFactory;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyDouble;

public class TourGuideTest {

    private final GuideMap guideMap = Mockito.mock(GuideMap.class);
    private final GuideCards guideCards = Mockito.mock(GuideCards.class);
    private final TourGuide tourGuide = TourGuideFactory.build(guideMap, guideCards);

    @Test
    public void setsItselfAsMapListener() {
        Mockito.verify(guideMap).setMapListener(tourGuide);
    }

    @Test
    public void centersMarkerOnMapWhenGuideSet() {
        tourGuide.setGuide("San Francisco", 12, 34);
        Mockito.verify(guideMap).setMarkerStyle(GuideMap.MarkerStyle.GUIDE);
        Mockito.verify(guideMap).addMarker(0, "San Francisco", 12, 34);
        Mockito.verify(guideMap).centerAt(12, 34);
    }

    @Test
    public void addsMarkerOnMapWhenPlaceSet() {
        tourGuide.setPlace(5, "Golden Gate", 12, 34);
        Mockito.verify(guideMap).setMarkerStyle(GuideMap.MarkerStyle.PLACE);
        Mockito.verify(guideMap).addMarker(5, "Golden Gate", 12, 34);
        Mockito.verify(guideMap, Mockito.never()).centerAt(anyDouble(), anyDouble());
    }

    @Test
    public void setsGuideCardWhenGuideSet() {
        tourGuide.setGuide("San Francisco", 12, 34);
        Mockito.verify(guideCards).setCard(0, "San Francisco");
    }

    @Test
    public void setsGuideCardWhenPlaceSet() {
        tourGuide.setPlace(5, "Golden Gate", 12, 34);
        Mockito.verify(guideCards).setCard(5, "Golden Gate");
    }

    @Test
    public void centersMarkerAndShowsGuideCardOnMarkerClick() {
        tourGuide.onMarkerClick(0, 12, 34);
        Mockito.verify(guideMap).centerAt(12, 34);
        Mockito.verify(guideCards).showCard(0);
    }

    @Test
    public void hidesGuideCardsOnMapClick() {
        tourGuide.onMapClick();
        Mockito.verify(guideCards).hide();
    }

}

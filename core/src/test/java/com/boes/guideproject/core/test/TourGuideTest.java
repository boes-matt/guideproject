package com.boes.guideproject.core.test;

import com.boes.guideproject.core.GuideCards;
import com.boes.guideproject.core.GuideMap;
import com.boes.guideproject.core.TourGuide;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyInt;

public class TourGuideTest {

    private final GuideMap guideMap = Mockito.mock(GuideMap.class);
    private final GuideCards guideCards = Mockito.mock(GuideCards.class);
    private final TourGuide tourGuide = TourGuide.build(guideMap, guideCards);

    @Test
    public void setsItselfAsMapListener() {
        Mockito.verify(guideMap).setMapListener(tourGuide);
    }

    @Test
    public void setsItselfAsCardListener() {
        Mockito.verify(guideCards).setGuideCardsListener(tourGuide);
    }

    @Test
    public void centersMarkerOnMapWhenGuideSet() {
        tourGuide.setGuide("San Francisco", 12, 34);
        Mockito.verify(guideMap).setMarkerStyle(GuideMap.MarkerStyle.GUIDE);
        Mockito.verify(guideMap).setMarker(0, "San Francisco", 12, 34);
        Mockito.verify(guideMap).centerAt(0);
    }

    @Test
    public void addsMarkerOnMapWhenPlaceSet() {
        tourGuide.setPlace(5, "Golden Gate", 12, 34);
        Mockito.verify(guideMap).setMarkerStyle(GuideMap.MarkerStyle.PLACE);
        Mockito.verify(guideMap).setMarker(5, "Golden Gate", 12, 34);
        Mockito.verify(guideMap, Mockito.never()).centerAt(anyInt());
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
    public void animatesToMarkerAndShowsGuideCardOnMarkerClick() {
        tourGuide.onMarkerClick(0);
        Mockito.verify(guideMap).animateTo(0);
        Mockito.verify(guideCards).showCard(0);
    }

    @Test
    public void hidesGuideCardsOnMapClick() {
        tourGuide.onMapClick();
        Mockito.verify(guideCards).hide();
    }

    @Test
    public void animatesToMarkerOnCardSelect() {
        tourGuide.onCardSelected(3);
        Mockito.verify(guideMap).animateTo(3);
    }

}

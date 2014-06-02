package com.boes.guideproject.core.test;

import com.boes.guideproject.core.DebugGuideService;
import com.boes.guideproject.core.GuideListener;
import com.boes.guideproject.core.GuideService;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

public class DebugGuideServiceTest {

    private final GuideListener guideListener = Mockito.mock(GuideListener.class);
    private final GuideService db = new DebugGuideService(guideListener);

    @Test
    public void notifiesGuideListenerWithGuidePlaces() {
        db.getGuideWithId("123");

        // e.g. guideListener.setGuide("San Francisco", 37.7833, 122.4167)
        Mockito.verify(guideListener).setGuide(anyString(), anyDouble(), anyDouble());

        // e.g. guideListener.setPlace(0, "Golden Gate", 38.7833, 121.4167)
        Mockito.verify(guideListener, Mockito.atLeastOnce()).setPlace(anyInt(), anyString(), anyDouble(), anyDouble());
    }

}

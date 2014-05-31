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
    public void notifiesGuideListenerWhenGuideReceived() {
        db.getGuideWithId("123");

        // e.g. guideListener.addPlace(0, "San Francisco", 37.7833, 122.4167)
        Mockito.verify(guideListener).addPlace(anyInt(), anyString(), anyDouble(), anyDouble());
    }

}

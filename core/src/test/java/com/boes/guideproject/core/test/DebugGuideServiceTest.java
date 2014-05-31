package com.boes.guideproject.core.test;

import com.boes.guideproject.core.DebugGuideService;
import com.boes.guideproject.core.GuideListener;
import com.boes.guideproject.core.GuideService;

import org.junit.Test;
import org.mockito.Mockito;

public class DebugGuideServiceTest {

    private final GuideListener guideListener = Mockito.mock(GuideListener.class);
    private final GuideService db = new DebugGuideService(guideListener);

    @Test
    public void notifiesGuideListenerWhenGuideReceived() {
        db.getGuideWithId("123");
        Mockito.verify(guideListener).addPlace(0, "San Francisco", 37.7833, 122.4167);
    }

}

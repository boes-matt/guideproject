package com.boes.guideproject.app.test;

import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;

import com.boes.guideproject.app.GuideCardPager;
import com.boes.guideproject.app.TestActivity;

import org.mockito.Mockito;

public class GuideCardPagerTest extends ActivityInstrumentationTestCase2<TestActivity> {

    private final ViewPager pager = Mockito.mock(ViewPager.class);
    private final GuideCardPager guideCards = new GuideCardPager(pager);

    public GuideCardPagerTest() {
        super(TestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testShowsCard() {
        guideCards.showCard(3);
        Mockito.verify(pager).setCurrentItem(3);
    }

}

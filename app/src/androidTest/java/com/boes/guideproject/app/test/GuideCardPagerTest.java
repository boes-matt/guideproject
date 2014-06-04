package com.boes.guideproject.app.test;

import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.boes.guideproject.app.CardAdapter;
import com.boes.guideproject.app.GuideCardPager;
import com.boes.guideproject.app.TestActivity;

import org.mockito.Mockito;

import java.util.List;

public class GuideCardPagerTest extends ActivityInstrumentationTestCase2<TestActivity> {

    private final ViewPager pager = Mockito.mock(ViewPager.class);
    private final List<String> cards = Mockito.mock(List.class);
    private final CardAdapter adapter = Mockito.mock(CardAdapter.class);
    private final GuideCardPager guideCards = new GuideCardPager(pager, cards, adapter);

    public GuideCardPagerTest() {
        super(TestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testSetsCard() {
        guideCards.setCard(2, "Golden Gate");
        Mockito.verify(cards).add(2, "Golden Gate");
        Mockito.verify(adapter).notifyDataSetChanged();
    }

    public void testShowsCard() {
        guideCards.showCard(3);
        Mockito.verify(pager).setVisibility(View.VISIBLE);
        Mockito.verify(pager).setCurrentItem(3, false);
    }

    public void testHidesGuideCards() {
        guideCards.hide();
        Mockito.verify(pager).setVisibility(View.GONE);
    }

}

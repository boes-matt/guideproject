package com.boes.guideproject.app.test.unit;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.boes.guideproject.app.GuideCardFragmentAdapter;
import com.boes.guideproject.app.ViewPagerGuideCards;
import com.boes.guideproject.core.GuideCardsListener;

import junit.framework.TestCase;

import org.mockito.Mockito;

public class ViewPagerGuideCardsTest extends TestCase {

    private final ViewPager pager = Mockito.mock(ViewPager.class);
    private final GuideCardFragmentAdapter adapter = Mockito.mock(GuideCardFragmentAdapter.class);
    private final ViewPagerGuideCards guideCards = new ViewPagerGuideCards(pager, adapter);

    private final GuideCardsListener guideCardsListener = Mockito.mock(GuideCardsListener.class);

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        guideCards.setGuideCardsListener(guideCardsListener);
    }

    public void testSetsCard() {
        guideCards.setCard(2, "Golden Gate");
        Mockito.verify(adapter).setCard(2, "Golden Gate");
    }

    public void testShowsCard() {
        guideCards.showCard(3);
        Mockito.verify(pager).setVisibility(View.VISIBLE);
        Mockito.verify(pager).setCurrentItem(3, false);
    }

    public void testHidesGuideCards() {
        guideCards.hide();
        Mockito.verify(pager).setVisibility(View.INVISIBLE);
    }

    public void testNotifiesGuideCardsListenerOnCardSelected() {
        guideCards.onPageSelected(3);
        Mockito.verify(guideCardsListener).onCardSelected(3);
    }

}

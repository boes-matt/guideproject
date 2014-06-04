package com.boes.guideproject.app.test;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.boes.guideproject.app.GuideCardFragmentAdapter;
import com.boes.guideproject.app.ViewPagerGuideCards;

import junit.framework.TestCase;

import org.mockito.Mockito;

public class ViewPagerGuideCardsTest extends TestCase {

    private final ViewPager pager = Mockito.mock(ViewPager.class);
    private final GuideCardFragmentAdapter adapter = Mockito.mock(GuideCardFragmentAdapter.class);
    private final ViewPagerGuideCards guideCards = new ViewPagerGuideCards(pager, adapter);

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
        Mockito.verify(pager).setVisibility(View.GONE);
    }

}

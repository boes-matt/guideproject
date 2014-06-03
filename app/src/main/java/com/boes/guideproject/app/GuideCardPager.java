package com.boes.guideproject.app;

import android.support.v4.view.ViewPager;

import com.boes.guideproject.core.GuideCards;

public class GuideCardPager implements GuideCards {

    ViewPager pager;

    public GuideCardPager(ViewPager pager) {
        this.pager = pager;
    }

    @Override
    public void setCard(int guidePosition, String title) {

    }

    @Override
    public void showCard(int guidePosition) {
        pager.setCurrentItem(guidePosition);
    }

    @Override
    public void hide() {

    }

}

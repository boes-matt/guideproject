package com.boes.guideproject.app;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.boes.guideproject.core.GuideCardAdapter;
import com.boes.guideproject.core.GuideCards;
import com.boes.guideproject.core.GuideCardsListener;

public class ViewPagerGuideCards implements
        GuideCards,
        ViewPager.OnPageChangeListener {

    ViewPager pager;
    GuideCardAdapter adapter;
    GuideCardsListener guideCardsListener;

    public ViewPagerGuideCards(ViewPager pager, GuideCardFragmentAdapter adapter) {
        this.pager = pager;
        this.pager.setOnPageChangeListener(this);
        this.pager.setAdapter(adapter);
        this.adapter = adapter;
        this.guideCardsListener = null;
    }

    @Override
    public void setCard(int guidePosition, String title) {
        adapter.setCard(guidePosition, title);
    }

    @Override
    public void showCard(int guidePosition) {
        pager.setCurrentItem(guidePosition, false);
        pager.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide() {
        pager.setVisibility(View.GONE);
    }

    @Override
    public void setGuideCardsListener(GuideCardsListener guideCardsListener) {
        this.guideCardsListener = guideCardsListener;
    }

    @Override
    public void onPageSelected(int position) {
        if (guideCardsListener != null) guideCardsListener.onCardSelected(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}

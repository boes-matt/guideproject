package com.boes.guideproject.app;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.boes.guideproject.core.GuideCardAdapter;
import com.boes.guideproject.core.GuideCards;

public class ViewPagerGuideCards implements GuideCards {

    ViewPager pager;
    GuideCardAdapter adapter;

    public ViewPagerGuideCards(ViewPager pager, GuideCardFragmentAdapter adapter) {
        this.pager = pager;
        this.pager.setAdapter(adapter);
        this.adapter = adapter;
    }

    @Override
    public void setCard(int guidePosition, String title) {
        adapter.setCard(guidePosition, title);
    }

    @Override
    public void showCard(int guidePosition) {
        pager.setVisibility(View.VISIBLE);
        pager.setCurrentItem(guidePosition, false);
    }

    @Override
    public void hide() {
        pager.setVisibility(View.GONE);
    }

}

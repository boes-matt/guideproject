package com.boes.guideproject.app;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.boes.guideproject.core.GuideCards;

import java.util.List;

public class GuideCardPager implements GuideCards {

    ViewPager pager;
    List<String> cards;
    CardAdapter adapter;

    public GuideCardPager(ViewPager pager, List<String> cards, CardAdapter adapter) {
        this.pager = pager;
        this.cards = cards;
        this.adapter = adapter;
        this.pager.setAdapter(this.adapter);
    }

    @Override
    public void setCard(int guidePosition, String title) {
        cards.add(guidePosition, title);
        adapter.notifyDataSetChanged();
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

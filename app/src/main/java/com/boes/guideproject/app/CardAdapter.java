package com.boes.guideproject.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

public class CardAdapter extends FragmentStatePagerAdapter {

    List<String> cards;

    public CardAdapter(FragmentManager fm, List<String> cards) {
        super(fm);
        this.cards = cards;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("CardAdapter", "New card fragment: " + cards.get(position));
        return CardFragment.newInstance(cards.get(position));
    }

    @Override
    public int getCount() {
        return cards.size();
    }

}

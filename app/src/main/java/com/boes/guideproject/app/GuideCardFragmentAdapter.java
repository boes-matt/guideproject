package com.boes.guideproject.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.boes.guideproject.core.GuideCardAdapter;

import java.util.List;

public class GuideCardFragmentAdapter extends FragmentStatePagerAdapter implements GuideCardAdapter {

    List<String> cards;
    int[] images = {R.drawable.stanford,
                    R.drawable.hoover,
                    R.drawable.dschool,
                    R.drawable.rodin};

    public GuideCardFragmentAdapter(FragmentManager fm, List<String> cards) {
        super(fm);
        this.cards = cards;
    }

    @Override
    public void setCard(int guidePosition, String title) {
        cards.add(guidePosition, title);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return GuideCardImage.newInstance(images[position]);
    }

    @Override
    public int getCount() {
        return cards.size();
    }

}

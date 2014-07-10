package com.boes.guideproject.app;

import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.boes.guideproject.core.DebugGuideService;
import com.boes.guideproject.core.GuideCards;
import com.boes.guideproject.core.GuideMap;
import com.boes.guideproject.core.GuideService;
import com.boes.guideproject.core.TourGuide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DebugGuideActivity extends BaseGuideActivity {

    @Override
    GuideService provideGuideService() {
        return new DebugGuideService(TourGuide.build(getGuideMap(), getGuideCards()));
    }

    private GuideCards getGuideCards() {
        FragmentManager fm = getSupportFragmentManager();

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setPageMargin(getPxForDimen(R.dimen.pager_page_margin));
        List<String> cards = new ArrayList<String>();

        GuideCardFragmentAdapter adapter = new GuideCardFragmentAdapter(fm, cards);

        return new ViewPagerGuideCards(pager, adapter);
    }

    private GuideMap getGuideMap() {
        final FragmentManager fm = getSupportFragmentManager();
        final SupportMapFragment fragment = (SupportMapFragment) fm.findFragmentById(R.id.map_fragment);

        final GoogleMap googleMap = fragment.getMap();
        HashMap<Integer, Marker> markerMap = new HashMap<Integer, Marker>();
        IconGenerator iconFactory = new IconGenerator(this);

        return new GoogleGuideMap(googleMap, markerMap, iconFactory);
    }

    private int getPxForDimen(int id) {
        Resources res = getResources();
        return res.getDimensionPixelSize(id);
    }

}

package com.boes.guideproject.app;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.boes.guideproject.core.DebugGuideService;
import com.boes.guideproject.core.GuideCards;
import com.boes.guideproject.core.GuideMap;
import com.boes.guideproject.core.GuideMarker;
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
        FragmentManager fm = getSupportFragmentManager();

        SupportMapFragment fragment = (SupportMapFragment) fm.findFragmentById(R.id.map_fragment);
        GoogleMap googleMap = fragment.getMap();
        HashMap<Marker, GuideMarker> markerMap = new HashMap<Marker, GuideMarker>();
        IconGenerator iconFactory = new IconGenerator(this);

        GuideMap guideMap = new GoogleGuideMap(googleMap, markerMap, iconFactory);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        List<String> cards = new ArrayList<String>();
        GuideCardFragmentAdapter adapter = new GuideCardFragmentAdapter(fm, cards);

        GuideCards guideCards = new ViewPagerGuideCards(pager, adapter);

        return new DebugGuideService(TourGuide.build(guideMap, guideCards));
    }

}

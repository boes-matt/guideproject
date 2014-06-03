package com.boes.guideproject.app;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.boes.guideproject.core.DebugGuideService;
import com.boes.guideproject.core.GuideCards;
import com.boes.guideproject.core.GuideMap;
import com.boes.guideproject.core.GuideMarker;
import com.boes.guideproject.core.GuideService;
import com.boes.guideproject.core.TourGuideFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.ui.IconGenerator;

import java.util.WeakHashMap;

public class DebugGuideActivity extends BaseGuideActivity {

    @Override
    GuideService provideGuideService() {
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment fragment = (SupportMapFragment) fm.findFragmentById(R.id.map_fragment);
        GoogleMap googleMap = fragment.getMap();

        GuideMap guideMap = new GoogleGuideMap(googleMap, new WeakHashMap<Marker, GuideMarker>(), new IconGenerator(this));
        GuideCards guideCards = new GuideCardPager(new ViewPager(this));

        return new DebugGuideService(TourGuideFactory.build(guideMap, guideCards));
    }

}

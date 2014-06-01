package com.boes.guideproject.app;

import android.support.v4.app.FragmentManager;

import com.boes.guideproject.core.DebugGuideService;
import com.boes.guideproject.core.GuideMap;
import com.boes.guideproject.core.GuideService;
import com.boes.guideproject.core.TourGuide;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.maps.android.ui.IconGenerator;

public class DebugGuideActivity extends BaseGuideActivity {

    @Override
    GuideService provideGuideService() {
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment fragment = (SupportMapFragment) fm.findFragmentById(R.id.map_fragment);
        GuideMap map = new GoogleGuideMap(new IconGenerator(this), fragment.getMap());

        return new DebugGuideService(new TourGuide(map));
    }

}

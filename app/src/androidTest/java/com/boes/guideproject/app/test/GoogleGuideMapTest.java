package com.boes.guideproject.app.test;

import android.app.Instrumentation;
import android.support.v4.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;

import com.boes.guideproject.app.GoogleGuideMap;
import com.boes.guideproject.app.TestActivity;
import com.boes.guideproject.core.MapListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.ui.IconGenerator;

import org.mockito.Mockito;

public class GoogleGuideMapTest extends ActivityInstrumentationTestCase2<TestActivity> {

    private final MapListener mapListener = Mockito.mock(MapListener.class);
    private GoogleGuideMap guideMap;

    public GoogleGuideMapTest() {
        super(TestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TestActivity activity = getActivity();
        SupportMapFragment mapFragment = new SupportMapFragment();
        FragmentManager fm = activity.getSupportFragmentManager();
        fm.beginTransaction().add(mapFragment, "mapFragment").commit();

        Instrumentation instrumentation = getInstrumentation();
        instrumentation.waitForIdleSync();
        final GoogleMap googleMap = mapFragment.getMap();
        final IconGenerator iconFactory = new IconGenerator(activity);
        instrumentation.runOnMainSync(new Runnable() { @Override public void run() {
            guideMap = new GoogleGuideMap(googleMap, iconFactory);
            guideMap.setMapListener(mapListener);
        } });
    }

    public void testNotifiesMapListenerOnMapClick() {
        guideMap.onMapClick(new LatLng(12, 23));
        Mockito.verify(mapListener).onMapClick();
    }

}

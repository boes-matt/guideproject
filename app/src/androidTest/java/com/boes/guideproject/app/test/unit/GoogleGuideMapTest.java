package com.boes.guideproject.app.test.unit;

import android.app.Instrumentation;
import android.support.v4.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;

import com.boes.guideproject.app.GoogleGuideMap;
import com.boes.guideproject.app.TestActivity;
import com.boes.guideproject.core.GuideMarker;
import com.boes.guideproject.core.MapListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.ui.IconGenerator;

import org.mockito.Mockito;

import java.util.HashMap;

public class GoogleGuideMapTest extends ActivityInstrumentationTestCase2<TestActivity> {

    Instrumentation instrumentation;

    private final MapListener mapListener = Mockito.mock(MapListener.class);
    private HashMap<Marker, GuideMarker> markerMap;
    private GoogleGuideMap guideMap;

    public GoogleGuideMapTest() {
        super(TestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        instrumentation = getInstrumentation();

        TestActivity activity = getActivity();
        SupportMapFragment mapFragment = new SupportMapFragment();
        FragmentManager fm = activity.getSupportFragmentManager();
        fm.beginTransaction().add(mapFragment, "mapFragment").commit();

        instrumentation.waitForIdleSync();

        final GoogleMap googleMap = mapFragment.getMap();
        markerMap = new HashMap<Marker, GuideMarker>();
        final IconGenerator iconFactory = new IconGenerator(activity);

        instrumentation.runOnMainSync(new Runnable() { @Override public void run() {
            guideMap = new GoogleGuideMap(googleMap, markerMap, iconFactory);
            guideMap.setMapListener(mapListener);
        } });
    }

    public void testNotifiesMapListenerOnMarkerClick() {
        instrumentation.runOnMainSync(new Runnable() { @Override public void run() {
            guideMap.addMarker(7, "Golden Gate", 12, 34);
            assertTrue(markerMap.size() == 1);
            Marker marker = markerMap.keySet().iterator().next();
            guideMap.onMarkerClick(marker);
        } });

        Mockito.verify(mapListener).onMarkerClick(7, 12, 34);
    }

    public void testNotifiesMapListenerOnMapClick() {
        guideMap.onMapClick(new LatLng(12, 23));
        Mockito.verify(mapListener).onMapClick();
    }

}

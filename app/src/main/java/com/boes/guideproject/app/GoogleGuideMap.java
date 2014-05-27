package com.boes.guideproject.app;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleGuideMap extends SupportMapFragment implements GuideMap {

    GoogleMap map;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        map = getMap();
    }

    @Override
    public void addMarker(String title, double latitude, double longitude) {
        MarkerOptions opts = new MarkerOptions()
                .title(title)
                .snippet("(" + latitude + ", " + longitude + ")")
                .position(new LatLng(latitude, longitude));
        map.addMarker(opts);
    }

    @Override
    public void setCameraTo(double latitude, double longitude, int zoom) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), zoom));
    }

}

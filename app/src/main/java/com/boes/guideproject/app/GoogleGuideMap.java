package com.boes.guideproject.app;

import android.graphics.Bitmap;

import com.boes.guideproject.core.GuideMap;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

public class GoogleGuideMap implements GuideMap {

    private final static int DEFAULT_ZOOM = 15;

    IconGenerator iconFactory;
    GoogleMap map;

    public GoogleGuideMap(IconGenerator iconFactory, GoogleMap map) {
        this.iconFactory = iconFactory;
        this.map = map;
    }

    @Override
    public void addMarker(String title, double latitude, double longitude) {
        Bitmap bitmap = iconFactory.makeIcon(title);
        MarkerOptions opts = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .position(new LatLng(latitude, longitude));
        map.addMarker(opts);
    }

    @Override
    public void centerAt(double latitude, double longitude) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), DEFAULT_ZOOM));
    }

    @Override
    public void setGuideMarkerStyle() {
        iconFactory.setStyle(IconGenerator.STYLE_BLUE);
    }

    @Override
    public void setPlaceMarkerStyle() {
        iconFactory.setStyle(IconGenerator.STYLE_GREEN);
    }

}
